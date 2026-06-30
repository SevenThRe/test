/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import org.eclipse.jetty.util.BufferUtil;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.websocket.api.BatchMode;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.WriteCallback;
import org.eclipse.jetty.websocket.api.extensions.OutgoingFrames;
import org.eclipse.jetty.websocket.common.BlockingWriteCallback;
import org.eclipse.jetty.websocket.common.LogicalConnection;
import org.eclipse.jetty.websocket.common.WebSocketFrame;
import org.eclipse.jetty.websocket.common.frames.BinaryFrame;
import org.eclipse.jetty.websocket.common.frames.ContinuationFrame;
import org.eclipse.jetty.websocket.common.frames.DataFrame;
import org.eclipse.jetty.websocket.common.frames.PingFrame;
import org.eclipse.jetty.websocket.common.frames.PongFrame;
import org.eclipse.jetty.websocket.common.frames.TextFrame;
import org.eclipse.jetty.websocket.common.io.FrameFlusher;
import org.eclipse.jetty.websocket.common.io.FutureWriteCallback;

public class WebSocketRemoteEndpoint
implements RemoteEndpoint {
    private static final WriteCallback NOOP_CALLBACK = new WriteCallback(){

        @Override
        public void writeSuccess() {
        }

        @Override
        public void writeFailed(Throwable x) {
        }
    };
    private static final Logger LOG = Log.getLogger(WebSocketRemoteEndpoint.class);
    private static final int ASYNC_MASK = 65535;
    private static final int BLOCK_MASK = 65536;
    private static final int STREAM_MASK = 131072;
    private static final int PARTIAL_TEXT_MASK = 262144;
    private static final int PARTIAL_BINARY_MASK = 524288;
    private final LogicalConnection connection;
    private final OutgoingFrames outgoing;
    private final AtomicInteger msgState = new AtomicInteger();
    private final BlockingWriteCallback blocker = new BlockingWriteCallback();
    private volatile BatchMode batchMode;

    public WebSocketRemoteEndpoint(LogicalConnection connection, OutgoingFrames outgoing) {
        this(connection, outgoing, BatchMode.AUTO);
    }

    public WebSocketRemoteEndpoint(LogicalConnection connection, OutgoingFrames outgoing, BatchMode batchMode) {
        if (connection == null) {
            throw new IllegalArgumentException("LogicalConnection cannot be null");
        }
        this.connection = connection;
        this.outgoing = outgoing;
        this.batchMode = batchMode;
    }

    private void blockingWrite(WebSocketFrame frame) throws IOException {
        try (BlockingWriteCallback.WriteBlocker b = this.blocker.acquireWriteBlocker();){
            this.uncheckedSendFrame(frame, b);
            b.block();
        }
    }

    private boolean lockMsg(MsgType type) {
        while (true) {
            int state = this.msgState.get();
            switch (type) {
                case BLOCKING: {
                    if ((state & 0xC0000) != 0) {
                        throw new IllegalStateException(String.format("Partial message pending %x for %s", new Object[]{state, type}));
                    }
                    if ((state & 0x10000) != 0) {
                        throw new IllegalStateException(String.format("Blocking message pending %x for %s", new Object[]{state, type}));
                    }
                    if (!this.msgState.compareAndSet(state, state | 0x10000)) break;
                    return state == 0;
                }
                case ASYNC: {
                    if ((state & 0xC0000) != 0) {
                        throw new IllegalStateException(String.format("Partial message pending %x for %s", new Object[]{state, type}));
                    }
                    if ((state & 0xFFFF) == 65535) {
                        throw new IllegalStateException(String.format("Too many async sends: %x", state));
                    }
                    if (!this.msgState.compareAndSet(state, state + 1)) break;
                    return state == 0;
                }
                case STREAMING: {
                    if ((state & 0xC0000) != 0) {
                        throw new IllegalStateException(String.format("Partial message pending %x for %s", new Object[]{state, type}));
                    }
                    if ((state & 0x20000) != 0) {
                        throw new IllegalStateException(String.format("Already streaming %x for %s", new Object[]{state, type}));
                    }
                    if (!this.msgState.compareAndSet(state, state | 0x20000)) break;
                    return state == 0;
                }
                case PARTIAL_BINARY: {
                    if (state == 524288) {
                        return false;
                    }
                    if (state == 0 && this.msgState.compareAndSet(0, state | 0x80000)) {
                        return true;
                    }
                    throw new IllegalStateException(String.format("Cannot send %s in state %x", new Object[]{type, state}));
                }
                case PARTIAL_TEXT: {
                    if (state == 262144) {
                        return false;
                    }
                    if (state == 0 && this.msgState.compareAndSet(0, state | 0x40000)) {
                        return true;
                    }
                    throw new IllegalStateException(String.format("Cannot send %s in state %x", new Object[]{type, state}));
                }
            }
        }
    }

    private void unlockMsg(MsgType type) {
        while (true) {
            int state = this.msgState.get();
            switch (type) {
                case BLOCKING: {
                    if ((state & 0x10000) == 0) {
                        throw new IllegalStateException(String.format("Not Blocking in state %x", state));
                    }
                    if (!this.msgState.compareAndSet(state, state & 0xFFFEFFFF)) break;
                    return;
                }
                case ASYNC: {
                    if ((state & 0xFFFF) == 0) {
                        throw new IllegalStateException(String.format("Not Async in %x", state));
                    }
                    if (!this.msgState.compareAndSet(state, state - 1)) break;
                    return;
                }
                case STREAMING: {
                    if ((state & 0x20000) == 0) {
                        throw new IllegalStateException(String.format("Not Streaming in state %x", state));
                    }
                    if (!this.msgState.compareAndSet(state, state & 0xFFFDFFFF)) break;
                    return;
                }
                case PARTIAL_BINARY: {
                    if (this.msgState.compareAndSet(524288, 0)) {
                        return;
                    }
                    throw new IllegalStateException(String.format("Not Partial Binary in state %x", state));
                }
                case PARTIAL_TEXT: {
                    if (this.msgState.compareAndSet(262144, 0)) {
                        return;
                    }
                    throw new IllegalStateException(String.format("Not Partial Text in state %x", state));
                }
            }
        }
    }

    @Override
    public InetSocketAddress getInetSocketAddress() {
        if (this.connection == null) {
            return null;
        }
        return this.connection.getRemoteAddress();
    }

    private Future<Void> sendAsyncFrame(WebSocketFrame frame) {
        FutureWriteCallback future = new FutureWriteCallback();
        this.uncheckedSendFrame(frame, future);
        return future;
    }

    @Override
    public void sendBytes(ByteBuffer data) throws IOException {
        this.lockMsg(MsgType.BLOCKING);
        try {
            this.connection.getIOState().assertOutputOpen();
            if (LOG.isDebugEnabled()) {
                LOG.debug("sendBytes with {}", BufferUtil.toDetailString(data));
            }
            this.blockingWrite(new BinaryFrame().setPayload(data));
        }
        finally {
            this.unlockMsg(MsgType.BLOCKING);
        }
    }

    @Override
    public Future<Void> sendBytesByFuture(ByteBuffer data) {
        this.lockMsg(MsgType.ASYNC);
        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("sendBytesByFuture with {}", BufferUtil.toDetailString(data));
            }
            Future<Void> future = this.sendAsyncFrame(new BinaryFrame().setPayload(data));
            return future;
        }
        finally {
            this.unlockMsg(MsgType.ASYNC);
        }
    }

    @Override
    public void sendBytes(ByteBuffer data, WriteCallback callback) {
        this.lockMsg(MsgType.ASYNC);
        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("sendBytes({}, {})", BufferUtil.toDetailString(data), callback);
            }
            this.uncheckedSendFrame(new BinaryFrame().setPayload(data), callback == null ? NOOP_CALLBACK : callback);
        }
        finally {
            this.unlockMsg(MsgType.ASYNC);
        }
    }

    public void uncheckedSendFrame(WebSocketFrame frame, WriteCallback callback) {
        try {
            BatchMode batchMode = BatchMode.OFF;
            if (frame.isDataFrame()) {
                batchMode = this.getBatchMode();
            }
            this.connection.getIOState().assertOutputOpen();
            this.outgoing.outgoingFrame(frame, callback, batchMode);
        }
        catch (IOException e) {
            callback.writeFailed(e);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void sendPartialBytes(ByteBuffer fragment, boolean isLast) throws IOException {
        boolean first = this.lockMsg(MsgType.PARTIAL_BINARY);
        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("sendPartialBytes({}, {})", BufferUtil.toDetailString(fragment), isLast);
            }
            DataFrame frame = first ? new BinaryFrame() : new ContinuationFrame();
            frame.setPayload(fragment);
            frame.setFin(isLast);
            this.blockingWrite(frame);
        }
        finally {
            if (isLast) {
                this.unlockMsg(MsgType.PARTIAL_BINARY);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void sendPartialString(String fragment, boolean isLast) throws IOException {
        boolean first = this.lockMsg(MsgType.PARTIAL_TEXT);
        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("sendPartialString({}, {})", fragment, isLast);
            }
            DataFrame frame = first ? new TextFrame() : new ContinuationFrame();
            frame.setPayload(BufferUtil.toBuffer(fragment, StandardCharsets.UTF_8));
            frame.setFin(isLast);
            this.blockingWrite(frame);
        }
        finally {
            if (isLast) {
                this.unlockMsg(MsgType.PARTIAL_TEXT);
            }
        }
    }

    @Override
    public void sendPing(ByteBuffer applicationData) throws IOException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("sendPing with {}", BufferUtil.toDetailString(applicationData));
        }
        this.sendAsyncFrame(new PingFrame().setPayload(applicationData));
    }

    @Override
    public void sendPong(ByteBuffer applicationData) throws IOException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("sendPong with {}", BufferUtil.toDetailString(applicationData));
        }
        this.sendAsyncFrame(new PongFrame().setPayload(applicationData));
    }

    @Override
    public void sendString(String text) throws IOException {
        this.lockMsg(MsgType.BLOCKING);
        try {
            TextFrame frame = new TextFrame().setPayload(text);
            if (LOG.isDebugEnabled()) {
                LOG.debug("sendString with {}", BufferUtil.toDetailString(frame.getPayload()));
            }
            this.blockingWrite(frame);
        }
        finally {
            this.unlockMsg(MsgType.BLOCKING);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Future<Void> sendStringByFuture(String text) {
        this.lockMsg(MsgType.ASYNC);
        try {
            TextFrame frame = new TextFrame().setPayload(text);
            if (LOG.isDebugEnabled()) {
                LOG.debug("sendStringByFuture with {}", BufferUtil.toDetailString(frame.getPayload()));
            }
            Future<Void> future = this.sendAsyncFrame(frame);
            return future;
        }
        finally {
            this.unlockMsg(MsgType.ASYNC);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void sendString(String text, WriteCallback callback) {
        this.lockMsg(MsgType.ASYNC);
        try {
            TextFrame frame = new TextFrame().setPayload(text);
            if (LOG.isDebugEnabled()) {
                LOG.debug("sendString({},{})", BufferUtil.toDetailString(frame.getPayload()), callback);
            }
            this.uncheckedSendFrame(frame, callback == null ? NOOP_CALLBACK : callback);
        }
        finally {
            this.unlockMsg(MsgType.ASYNC);
        }
    }

    @Override
    public BatchMode getBatchMode() {
        return this.batchMode;
    }

    @Override
    public void setBatchMode(BatchMode batchMode) {
        this.batchMode = batchMode;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void flush() throws IOException {
        this.lockMsg(MsgType.ASYNC);
        try (BlockingWriteCallback.WriteBlocker b = this.blocker.acquireWriteBlocker();){
            this.uncheckedSendFrame(FrameFlusher.FLUSH_FRAME, b);
            b.block();
        }
        finally {
            this.unlockMsg(MsgType.ASYNC);
        }
    }

    public String toString() {
        return String.format("%s@%x[batching=%b]", new Object[]{this.getClass().getSimpleName(), this.hashCode(), this.getBatchMode()});
    }

    private static enum MsgType {
        BLOCKING,
        ASYNC,
        STREAMING,
        PARTIAL_TEXT,
        PARTIAL_BINARY;

    }
}

