/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.io;

import java.io.EOFException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import org.eclipse.jetty.io.AbstractConnection;
import org.eclipse.jetty.io.ByteBufferPool;
import org.eclipse.jetty.io.Connection;
import org.eclipse.jetty.io.EndPoint;
import org.eclipse.jetty.util.BufferUtil;
import org.eclipse.jetty.util.component.ContainerLifeCycle;
import org.eclipse.jetty.util.component.Dumpable;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.util.thread.Scheduler;
import org.eclipse.jetty.websocket.api.BatchMode;
import org.eclipse.jetty.websocket.api.CloseException;
import org.eclipse.jetty.websocket.api.SuspendToken;
import org.eclipse.jetty.websocket.api.WebSocketPolicy;
import org.eclipse.jetty.websocket.api.WriteCallback;
import org.eclipse.jetty.websocket.api.extensions.ExtensionConfig;
import org.eclipse.jetty.websocket.api.extensions.Frame;
import org.eclipse.jetty.websocket.common.CloseInfo;
import org.eclipse.jetty.websocket.common.ConnectionState;
import org.eclipse.jetty.websocket.common.Generator;
import org.eclipse.jetty.websocket.common.LogicalConnection;
import org.eclipse.jetty.websocket.common.Parser;
import org.eclipse.jetty.websocket.common.io.FrameFlusher;
import org.eclipse.jetty.websocket.common.io.IOState;

public abstract class AbstractWebSocketConnection
extends AbstractConnection
implements LogicalConnection,
Connection.UpgradeTo,
IOState.ConnectionStateListener,
Dumpable {
    private final AtomicBoolean closed = new AtomicBoolean();
    private static final Logger LOG = Log.getLogger(AbstractWebSocketConnection.class);
    private static final Logger LOG_OPEN = Log.getLogger(AbstractWebSocketConnection.class.getName() + "_OPEN");
    private static final Logger LOG_CLOSE = Log.getLogger(AbstractWebSocketConnection.class.getName() + "_CLOSE");
    private static final int MIN_BUFFER_SIZE = 28;
    private final ByteBufferPool bufferPool;
    private final Scheduler scheduler;
    private final Generator generator;
    private final Parser parser;
    private final WebSocketPolicy policy;
    private final AtomicBoolean suspendToken;
    private final FrameFlusher flusher;
    private final String id;
    private List<ExtensionConfig> extensions;
    private boolean isFilling;
    private ByteBuffer prefillBuffer;
    private ReadMode readMode = ReadMode.PARSE;
    private IOState ioState;
    private Stats stats = new Stats();

    public AbstractWebSocketConnection(EndPoint endp, Executor executor, Scheduler scheduler, WebSocketPolicy policy, ByteBufferPool bufferPool) {
        super(endp, executor);
        this.id = String.format("%s:%d->%s:%d", endp.getLocalAddress().getAddress().getHostAddress(), endp.getLocalAddress().getPort(), endp.getRemoteAddress().getAddress().getHostAddress(), endp.getRemoteAddress().getPort());
        this.policy = policy;
        this.bufferPool = bufferPool;
        this.generator = new Generator(policy, bufferPool);
        this.parser = new Parser(policy, bufferPool);
        this.scheduler = scheduler;
        this.extensions = new ArrayList<ExtensionConfig>();
        this.suspendToken = new AtomicBoolean(false);
        this.ioState = new IOState();
        this.ioState.addListener(this);
        this.flusher = new Flusher(bufferPool, this.generator, endp);
        this.setInputBufferSize(policy.getInputBufferSize());
        this.setMaxIdleTimeout(policy.getIdleTimeout());
    }

    @Override
    public Executor getExecutor() {
        return super.getExecutor();
    }

    @Override
    public void close() {
        if (LOG_CLOSE.isDebugEnabled()) {
            LOG_CLOSE.debug("close()", new Object[0]);
        }
        this.close(new CloseInfo());
    }

    @Override
    public void close(int statusCode, String reason) {
        if (LOG_CLOSE.isDebugEnabled()) {
            LOG_CLOSE.debug("close({},{})", statusCode, reason);
        }
        this.close(new CloseInfo(statusCode, reason));
    }

    private void close(CloseInfo closeInfo) {
        if (this.closed.compareAndSet(false, true)) {
            this.outgoingFrame(closeInfo.asFrame(), new OnCloseLocalCallback(closeInfo), BatchMode.OFF);
        }
    }

    @Override
    public void disconnect() {
        if (LOG_CLOSE.isDebugEnabled()) {
            LOG_CLOSE.debug("{} disconnect()", new Object[]{this.policy.getBehavior()});
        }
        this.disconnect(false);
    }

    private void disconnect(boolean onlyOutput) {
        if (LOG_CLOSE.isDebugEnabled()) {
            LOG_CLOSE.debug("{} disconnect({})", new Object[]{this.policy.getBehavior(), onlyOutput ? "outputOnly" : "both"});
        }
        this.flusher.close();
        EndPoint endPoint = this.getEndPoint();
        if (LOG_CLOSE.isDebugEnabled()) {
            LOG_CLOSE.debug("Shutting down output {}", endPoint);
        }
        endPoint.shutdownOutput();
        if (!onlyOutput) {
            if (LOG_CLOSE.isDebugEnabled()) {
                LOG_CLOSE.debug("Closing {}", endPoint);
            }
            endPoint.close();
        }
    }

    protected void execute(Runnable task) {
        block2: {
            try {
                this.getExecutor().execute(task);
            }
            catch (RejectedExecutionException e) {
                if (!LOG.isDebugEnabled()) break block2;
                LOG.debug("Job not dispatched: {}", task);
            }
        }
    }

    @Override
    public void fillInterested() {
        this.stats.countFillInterestedEvents.incrementAndGet();
        super.fillInterested();
    }

    @Override
    public ByteBufferPool getBufferPool() {
        return this.bufferPool;
    }

    public List<ExtensionConfig> getExtensions() {
        return this.extensions;
    }

    public Generator getGenerator() {
        return this.generator;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public long getIdleTimeout() {
        return this.getEndPoint().getIdleTimeout();
    }

    @Override
    public IOState getIOState() {
        return this.ioState;
    }

    @Override
    public long getMaxIdleTimeout() {
        return this.getEndPoint().getIdleTimeout();
    }

    public Parser getParser() {
        return this.parser;
    }

    @Override
    public WebSocketPolicy getPolicy() {
        return this.policy;
    }

    @Override
    public InetSocketAddress getRemoteAddress() {
        return this.getEndPoint().getRemoteAddress();
    }

    public Scheduler getScheduler() {
        return this.scheduler;
    }

    public Stats getStats() {
        return this.stats;
    }

    @Override
    public boolean isOpen() {
        return !this.closed.get();
    }

    @Override
    public boolean isReading() {
        return this.isFilling;
    }

    @Override
    public void onClose() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("{} onClose()", new Object[]{this.policy.getBehavior()});
        }
        super.onClose();
        this.ioState.onDisconnected();
        this.flusher.close();
    }

    @Override
    public void onConnectionStateChange(ConnectionState state) {
        if (LOG_CLOSE.isDebugEnabled()) {
            LOG_CLOSE.debug("{} Connection State Change: {}", new Object[]{this.policy.getBehavior(), state});
        }
        switch (state) {
            case OPEN: {
                if (BufferUtil.hasContent(this.prefillBuffer)) {
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("Parsing Upgrade prefill buffer ({} remaining)", this.prefillBuffer.remaining());
                    }
                    this.parser.parse(this.prefillBuffer);
                }
                if (LOG.isDebugEnabled()) {
                    LOG.debug("OPEN: normal fillInterested", new Object[0]);
                }
                this.fillInterested();
                break;
            }
            case CLOSED: {
                if (LOG_CLOSE.isDebugEnabled()) {
                    LOG_CLOSE.debug("CLOSED - wasAbnormalClose: {}", this.ioState.wasAbnormalClose());
                }
                if (this.ioState.wasAbnormalClose()) {
                    CloseInfo abnormal = new CloseInfo(1001, "Abnormal Close - " + this.ioState.getCloseInfo().getReason());
                    this.outgoingFrame(abnormal.asFrame(), new OnDisconnectCallback(false), BatchMode.OFF);
                    break;
                }
                this.disconnect(false);
                break;
            }
            case CLOSING: {
                if (LOG_CLOSE.isDebugEnabled()) {
                    LOG_CLOSE.debug("CLOSING - wasRemoteCloseInitiated: {}", this.ioState.wasRemoteCloseInitiated());
                }
                if (!this.ioState.wasRemoteCloseInitiated()) break;
                CloseInfo close = this.ioState.getCloseInfo();
                this.outgoingFrame(close.asFrame(), new OnCloseLocalCallback(new OnDisconnectCallback(true), close), BatchMode.OFF);
            }
        }
    }

    @Override
    public void onFillable() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("{} onFillable()", new Object[]{this.policy.getBehavior()});
        }
        this.stats.countOnFillableEvents.incrementAndGet();
        ByteBuffer buffer = this.bufferPool.acquire(this.getInputBufferSize(), true);
        try {
            this.isFilling = true;
            this.readMode = this.readMode == ReadMode.PARSE ? this.readParse(buffer) : this.readDiscard(buffer);
        }
        finally {
            this.bufferPool.release(buffer);
        }
        if (this.readMode != ReadMode.EOF && !this.suspendToken.get()) {
            this.fillInterested();
        } else {
            this.isFilling = false;
        }
    }

    @Override
    protected void onFillInterestedFailed(Throwable cause) {
        LOG.ignore(cause);
        this.stats.countFillInterestedEvents.incrementAndGet();
        super.onFillInterestedFailed(cause);
    }

    protected void setInitialBuffer(ByteBuffer prefilled) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("set Initial Buffer - {}", BufferUtil.toDetailString(prefilled));
        }
        this.prefillBuffer = prefilled;
    }

    private void notifyError(Throwable t) {
        this.getParser().getIncomingFramesHandler().incomingError(t);
    }

    @Override
    public void onOpen() {
        if (LOG_OPEN.isDebugEnabled()) {
            LOG_OPEN.debug("[{}] {}.onOpened()", new Object[]{this.policy.getBehavior(), this.getClass().getSimpleName()});
        }
        super.onOpen();
        this.ioState.onOpened();
    }

    @Override
    protected boolean onReadTimeout() {
        IOState state = this.getIOState();
        ConnectionState cstate = state.getConnectionState();
        if (LOG_CLOSE.isDebugEnabled()) {
            LOG_CLOSE.debug("{} Read Timeout - {}", new Object[]{this.policy.getBehavior(), cstate});
        }
        if (cstate == ConnectionState.CLOSED) {
            if (LOG_CLOSE.isDebugEnabled()) {
                LOG_CLOSE.debug("onReadTimeout - Connection Already CLOSED", new Object[0]);
            }
            return true;
        }
        try {
            this.notifyError(new SocketTimeoutException("Timeout on Read"));
        }
        finally {
            this.close(1001, "Idle Timeout");
        }
        return false;
    }

    @Override
    public void outgoingFrame(Frame frame, WriteCallback callback, BatchMode batchMode) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("outgoingFrame({}, {})", frame, callback);
        }
        this.flusher.enqueue(frame, callback, batchMode);
    }

    private ReadMode readDiscard(ByteBuffer buffer) {
        EndPoint endPoint = this.getEndPoint();
        try {
            while (true) {
                int filled;
                if ((filled = endPoint.fill(buffer)) == 0) {
                    return ReadMode.DISCARD;
                }
                if (filled < 0) {
                    if (LOG_CLOSE.isDebugEnabled()) {
                        LOG_CLOSE.debug("read - EOF Reached (remote: {})", this.getRemoteAddress());
                    }
                    return ReadMode.EOF;
                }
                if (!LOG_CLOSE.isDebugEnabled()) continue;
                LOG_CLOSE.debug("Discarded {} bytes - {}", filled, BufferUtil.toDetailString(buffer));
            }
        }
        catch (IOException e) {
            LOG.ignore(e);
            return ReadMode.EOF;
        }
        catch (Throwable t) {
            LOG.ignore(t);
            return ReadMode.DISCARD;
        }
    }

    private ReadMode readParse(ByteBuffer buffer) {
        EndPoint endPoint = this.getEndPoint();
        try {
            while (true) {
                int filled;
                if ((filled = endPoint.fill(buffer)) < 0) {
                    LOG.debug("read - EOF Reached (remote: {})", this.getRemoteAddress());
                    this.ioState.onReadFailure(new EOFException("Remote Read EOF"));
                    return ReadMode.EOF;
                }
                if (filled == 0) {
                    return ReadMode.PARSE;
                }
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Filled {} bytes - {}", filled, BufferUtil.toDetailString(buffer));
                }
                this.parser.parse(buffer);
            }
        }
        catch (IOException e) {
            LOG.warn(e);
            this.close(1002, e.getMessage());
            return ReadMode.DISCARD;
        }
        catch (CloseException e) {
            LOG.debug(e);
            this.close(e.getStatusCode(), e.getMessage());
            return ReadMode.DISCARD;
        }
        catch (Throwable t) {
            LOG.warn(t);
            this.close(1006, t.getMessage());
            return ReadMode.DISCARD;
        }
    }

    @Override
    public void resume() {
        if (this.suspendToken.getAndSet(false) && !this.isReading()) {
            this.fillInterested();
        }
    }

    public void setExtensions(List<ExtensionConfig> extensions) {
        this.extensions = extensions;
    }

    @Override
    public void setInputBufferSize(int inputBufferSize) {
        if (inputBufferSize < 28) {
            throw new IllegalArgumentException("Cannot have buffer size less than 28");
        }
        super.setInputBufferSize(inputBufferSize);
    }

    @Override
    public void setMaxIdleTimeout(long ms) {
        this.getEndPoint().setIdleTimeout(ms);
    }

    @Override
    public SuspendToken suspend() {
        this.suspendToken.set(true);
        return this;
    }

    @Override
    public String dump() {
        return ContainerLifeCycle.dump(this);
    }

    @Override
    public void dump(Appendable out, String indent) throws IOException {
        out.append(this.toString()).append(System.lineSeparator());
    }

    @Override
    public String toConnectionString() {
        return String.format("%s@%x[ios=%s,f=%s,g=%s,p=%s]", this.getClass().getSimpleName(), this.hashCode(), this.ioState, this.flusher, this.generator, this.parser);
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        EndPoint endp = this.getEndPoint();
        if (endp != null) {
            result = 31 * result + endp.getLocalAddress().hashCode();
            result = 31 * result + endp.getRemoteAddress().hashCode();
        }
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        AbstractWebSocketConnection other = (AbstractWebSocketConnection)obj;
        EndPoint endp = this.getEndPoint();
        EndPoint otherEndp = other.getEndPoint();
        return !(endp == null ? otherEndp != null : !endp.equals(otherEndp));
    }

    @Override
    public void onUpgradeTo(ByteBuffer prefilled) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("onUpgradeTo({})", BufferUtil.toDetailString(prefilled));
        }
        this.setInitialBuffer(prefilled);
    }

    private static enum ReadMode {
        PARSE,
        DISCARD,
        EOF;

    }

    public static class Stats {
        private AtomicLong countFillInterestedEvents = new AtomicLong(0L);
        private AtomicLong countOnFillableEvents = new AtomicLong(0L);
        private AtomicLong countFillableErrors = new AtomicLong(0L);

        public long getFillableErrorCount() {
            return this.countFillableErrors.get();
        }

        public long getFillInterestedCount() {
            return this.countFillInterestedEvents.get();
        }

        public long getOnFillableCount() {
            return this.countOnFillableEvents.get();
        }
    }

    public class OnCloseLocalCallback
    implements WriteCallback {
        private final WriteCallback callback;
        private final CloseInfo close;

        public OnCloseLocalCallback(WriteCallback callback, CloseInfo close) {
            this.callback = callback;
            this.close = close;
        }

        public OnCloseLocalCallback(CloseInfo close) {
            this(null, close);
        }

        @Override
        public void writeFailed(Throwable x) {
            try {
                if (this.callback != null) {
                    this.callback.writeFailed(x);
                }
            }
            finally {
                this.onLocalClose();
            }
        }

        @Override
        public void writeSuccess() {
            try {
                if (this.callback != null) {
                    this.callback.writeSuccess();
                }
            }
            finally {
                this.onLocalClose();
            }
        }

        private void onLocalClose() {
            if (LOG_CLOSE.isDebugEnabled()) {
                LOG_CLOSE.debug("Local Close Confirmed {}", this.close);
            }
            if (this.close.isAbnormal()) {
                AbstractWebSocketConnection.this.ioState.onAbnormalClose(this.close);
            } else {
                AbstractWebSocketConnection.this.ioState.onCloseLocal(this.close);
            }
        }
    }

    public class OnDisconnectCallback
    implements WriteCallback {
        private final boolean outputOnly;

        public OnDisconnectCallback(boolean outputOnly) {
            this.outputOnly = outputOnly;
        }

        @Override
        public void writeFailed(Throwable x) {
            AbstractWebSocketConnection.this.disconnect(this.outputOnly);
        }

        @Override
        public void writeSuccess() {
            AbstractWebSocketConnection.this.disconnect(this.outputOnly);
        }
    }

    private class Flusher
    extends FrameFlusher {
        private Flusher(ByteBufferPool bufferPool, Generator generator, EndPoint endpoint) {
            super(bufferPool, generator, endpoint, AbstractWebSocketConnection.this.getPolicy().getMaxBinaryMessageBufferSize(), 8);
        }

        @Override
        protected void onFailure(Throwable x) {
            AbstractWebSocketConnection.this.notifyError(x);
            if (AbstractWebSocketConnection.this.ioState.wasAbnormalClose()) {
                LOG.ignore(x);
                return;
            }
            if (LOG.isDebugEnabled()) {
                LOG.debug("Write flush failure", x);
            }
            AbstractWebSocketConnection.this.ioState.onWriteFailure(x);
        }
    }
}

