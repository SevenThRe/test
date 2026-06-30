/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.io;

import java.io.EOFException;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import org.eclipse.jetty.io.ByteBufferPool;
import org.eclipse.jetty.io.EndPoint;
import org.eclipse.jetty.util.BufferUtil;
import org.eclipse.jetty.util.IteratingCallback;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.websocket.api.BatchMode;
import org.eclipse.jetty.websocket.api.WriteCallback;
import org.eclipse.jetty.websocket.api.extensions.Frame;
import org.eclipse.jetty.websocket.common.Generator;
import org.eclipse.jetty.websocket.common.frames.BinaryFrame;

public class FrameFlusher {
    public static final BinaryFrame FLUSH_FRAME = new BinaryFrame();
    private static final Logger LOG = Log.getLogger(FrameFlusher.class);
    private final ByteBufferPool bufferPool;
    private final EndPoint endpoint;
    private final int bufferSize;
    private final Generator generator;
    private final int maxGather;
    private final Object lock = new Object();
    private final Deque<FrameEntry> queue = new ArrayDeque<FrameEntry>();
    private final Flusher flusher;
    private final AtomicBoolean closed = new AtomicBoolean();
    private volatile Throwable failure;

    public FrameFlusher(ByteBufferPool bufferPool, Generator generator, EndPoint endpoint, int bufferSize, int maxGather) {
        this.bufferPool = bufferPool;
        this.endpoint = endpoint;
        this.bufferSize = bufferSize;
        this.generator = Objects.requireNonNull(generator);
        this.maxGather = maxGather;
        this.flusher = new Flusher(maxGather);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void close() {
        if (this.closed.compareAndSet(false, true)) {
            LOG.debug("{} closing {}", this);
            EOFException eof = new EOFException("Connection has been closed locally");
            this.flusher.failed(eof);
            ArrayList<FrameEntry> entries = new ArrayList<FrameEntry>();
            Iterator iterator2 = this.lock;
            synchronized (iterator2) {
                entries.addAll(this.queue);
                this.queue.clear();
            }
            for (FrameEntry entry : entries) {
                this.notifyCallbackFailure(entry.callback, eof);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void enqueue(Frame frame, WriteCallback callback, BatchMode batchMode) {
        if (this.closed.get()) {
            this.notifyCallbackFailure(callback, new EOFException("Connection has been closed locally"));
            return;
        }
        if (this.flusher.isFailed()) {
            this.notifyCallbackFailure(callback, this.failure);
            return;
        }
        FrameEntry entry = new FrameEntry(frame, callback, batchMode);
        Object object = this.lock;
        synchronized (object) {
            switch (frame.getOpCode()) {
                case 9: {
                    this.queue.offerFirst(entry);
                    break;
                }
                case 8: {
                    this.closed.set(true);
                    this.queue.offer(entry);
                    break;
                }
                default: {
                    this.queue.offer(entry);
                }
            }
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("{} queued {}", this, entry);
        }
        this.flusher.iterate();
    }

    protected void notifyCallbackFailure(WriteCallback callback, Throwable failure) {
        block3: {
            try {
                if (callback != null) {
                    callback.writeFailed(failure);
                }
            }
            catch (Throwable x) {
                if (!LOG.isDebugEnabled()) break block3;
                LOG.debug("Exception while notifying failure of callback " + callback, x);
            }
        }
    }

    protected void notifyCallbackSuccess(WriteCallback callback) {
        block3: {
            try {
                if (callback != null) {
                    callback.writeSuccess();
                }
            }
            catch (Throwable x) {
                if (!LOG.isDebugEnabled()) break block3;
                LOG.debug("Exception while notifying success of callback " + callback, x);
            }
        }
    }

    protected void onFailure(Throwable x) {
        LOG.warn(x);
    }

    public String toString() {
        ByteBuffer aggregate = this.flusher.aggregate;
        return String.format("%s[queueSize=%d,aggregateSize=%d,failure=%s]", this.getClass().getSimpleName(), this.queue.size(), aggregate == null ? 0 : aggregate.position(), this.failure);
    }

    private class FrameEntry {
        private final Frame frame;
        private final WriteCallback callback;
        private final BatchMode batchMode;
        private ByteBuffer headerBuffer;

        private FrameEntry(Frame frame, WriteCallback callback, BatchMode batchMode) {
            this.frame = Objects.requireNonNull(frame);
            this.callback = callback;
            this.batchMode = batchMode;
        }

        private ByteBuffer generateHeaderBytes() {
            this.headerBuffer = FrameFlusher.this.generator.generateHeaderBytes(this.frame);
            return this.headerBuffer;
        }

        private void generateHeaderBytes(ByteBuffer buffer) {
            FrameFlusher.this.generator.generateHeaderBytes(this.frame, buffer);
        }

        private void release() {
            if (this.headerBuffer != null) {
                FrameFlusher.this.generator.getBufferPool().release(this.headerBuffer);
                this.headerBuffer = null;
            }
        }

        public String toString() {
            return String.format("%s[%s,%s,%s,%s]", new Object[]{this.getClass().getSimpleName(), this.frame, this.callback, this.batchMode, FrameFlusher.this.failure});
        }
    }

    private class Flusher
    extends IteratingCallback {
        private final List<FrameEntry> entries;
        private final List<ByteBuffer> buffers;
        private ByteBuffer aggregate;
        private BatchMode batchMode;

        public Flusher(int maxGather) {
            this.entries = new ArrayList<FrameEntry>(maxGather);
            this.buffers = new ArrayList<ByteBuffer>(maxGather * 2 + 1);
        }

        private IteratingCallback.Action batch() {
            if (this.aggregate == null) {
                this.aggregate = FrameFlusher.this.bufferPool.acquire(FrameFlusher.this.bufferSize, true);
                if (LOG.isDebugEnabled()) {
                    LOG.debug("{} acquired aggregate buffer {}", FrameFlusher.this, this.aggregate);
                }
            }
            for (int i = 0; i < this.entries.size(); ++i) {
                FrameEntry entry = this.entries.get(i);
                entry.generateHeaderBytes(this.aggregate);
                ByteBuffer payload = entry.frame.getPayload();
                if (!BufferUtil.hasContent(payload)) continue;
                BufferUtil.append(this.aggregate, payload);
            }
            if (LOG.isDebugEnabled()) {
                LOG.debug("{} aggregated {} frames: {}", FrameFlusher.this, this.entries.size(), this.entries);
            }
            this.succeeded();
            return IteratingCallback.Action.SCHEDULED;
        }

        @Override
        protected void onCompleteSuccess() {
        }

        @Override
        public void onCompleteFailure(Throwable x) {
            for (FrameEntry entry : this.entries) {
                FrameFlusher.this.notifyCallbackFailure(entry.callback, x);
                entry.release();
            }
            this.entries.clear();
            FrameFlusher.this.failure = x;
            FrameFlusher.this.onFailure(x);
        }

        private IteratingCallback.Action flush() {
            if (!BufferUtil.isEmpty(this.aggregate)) {
                this.buffers.add(this.aggregate);
                if (LOG.isDebugEnabled()) {
                    LOG.debug("{} flushing aggregate {}", FrameFlusher.this, this.aggregate);
                }
            }
            for (int i = 0; i < this.entries.size(); ++i) {
                FrameEntry entry = this.entries.get(i);
                if (entry.frame == FLUSH_FRAME) continue;
                this.buffers.add(entry.generateHeaderBytes());
                ByteBuffer payload = entry.frame.getPayload();
                if (!BufferUtil.hasContent(payload)) continue;
                this.buffers.add(payload);
            }
            if (LOG.isDebugEnabled()) {
                LOG.debug("{} flushing {} frames: {}", FrameFlusher.this, this.entries.size(), this.entries);
            }
            if (this.buffers.isEmpty()) {
                this.releaseAggregate();
                this.succeedEntries();
                return IteratingCallback.Action.IDLE;
            }
            FrameFlusher.this.endpoint.write(this, this.buffers.toArray(new ByteBuffer[this.buffers.size()]));
            this.buffers.clear();
            return IteratingCallback.Action.SCHEDULED;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        protected IteratingCallback.Action process() throws Exception {
            int space = this.aggregate == null ? FrameFlusher.this.bufferSize : BufferUtil.space(this.aggregate);
            BatchMode currentBatchMode = BatchMode.AUTO;
            Object object = FrameFlusher.this.lock;
            synchronized (object) {
                while (this.entries.size() <= FrameFlusher.this.maxGather && !FrameFlusher.this.queue.isEmpty()) {
                    int payloadLength;
                    int approxFrameLength;
                    FrameEntry entry = (FrameEntry)FrameFlusher.this.queue.poll();
                    currentBatchMode = BatchMode.max(currentBatchMode, entry.batchMode);
                    if (entry.frame == FLUSH_FRAME) {
                        currentBatchMode = BatchMode.OFF;
                    }
                    if ((approxFrameLength = 28 + (payloadLength = BufferUtil.length(entry.frame.getPayload()))) > FrameFlusher.this.bufferSize >> 2) {
                        currentBatchMode = BatchMode.OFF;
                    }
                    if ((space -= approxFrameLength) <= 0) {
                        currentBatchMode = BatchMode.OFF;
                    }
                    this.entries.add(entry);
                }
            }
            if (LOG.isDebugEnabled()) {
                LOG.debug("{} processing {} entries: {}", FrameFlusher.this, this.entries.size(), this.entries);
            }
            if (this.entries.isEmpty()) {
                if (this.batchMode != BatchMode.AUTO) {
                    this.releaseAggregate();
                    return IteratingCallback.Action.IDLE;
                }
                LOG.debug("{} auto flushing", FrameFlusher.this);
                return this.flush();
            }
            this.batchMode = currentBatchMode;
            return currentBatchMode == BatchMode.OFF ? this.flush() : this.batch();
        }

        private void releaseAggregate() {
            if (this.aggregate != null && BufferUtil.isEmpty(this.aggregate)) {
                FrameFlusher.this.bufferPool.release(this.aggregate);
                this.aggregate = null;
            }
        }

        @Override
        public void succeeded() {
            this.succeedEntries();
            super.succeeded();
        }

        private void succeedEntries() {
            for (int i = 0; i < this.entries.size(); ++i) {
                FrameEntry entry = this.entries.get(i);
                FrameFlusher.this.notifyCallbackSuccess(entry.callback);
                entry.release();
            }
            this.entries.clear();
        }
    }
}

