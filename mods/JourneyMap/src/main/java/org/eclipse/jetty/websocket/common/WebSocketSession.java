/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import org.eclipse.jetty.io.ByteBufferPool;
import org.eclipse.jetty.io.Connection;
import org.eclipse.jetty.util.annotation.ManagedAttribute;
import org.eclipse.jetty.util.annotation.ManagedObject;
import org.eclipse.jetty.util.component.ContainerLifeCycle;
import org.eclipse.jetty.util.component.Dumpable;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.util.thread.ThreadClassLoaderScope;
import org.eclipse.jetty.websocket.api.BatchMode;
import org.eclipse.jetty.websocket.api.CloseException;
import org.eclipse.jetty.websocket.api.CloseStatus;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.SuspendToken;
import org.eclipse.jetty.websocket.api.UpgradeRequest;
import org.eclipse.jetty.websocket.api.UpgradeResponse;
import org.eclipse.jetty.websocket.api.WebSocketBehavior;
import org.eclipse.jetty.websocket.api.WebSocketException;
import org.eclipse.jetty.websocket.api.WebSocketPolicy;
import org.eclipse.jetty.websocket.api.extensions.ExtensionFactory;
import org.eclipse.jetty.websocket.api.extensions.Frame;
import org.eclipse.jetty.websocket.api.extensions.IncomingFrames;
import org.eclipse.jetty.websocket.api.extensions.OutgoingFrames;
import org.eclipse.jetty.websocket.common.CloseInfo;
import org.eclipse.jetty.websocket.common.ConnectionState;
import org.eclipse.jetty.websocket.common.LogicalConnection;
import org.eclipse.jetty.websocket.common.RemoteEndpointFactory;
import org.eclipse.jetty.websocket.common.WebSocketRemoteEndpoint;
import org.eclipse.jetty.websocket.common.events.EventDriver;
import org.eclipse.jetty.websocket.common.io.IOState;
import org.eclipse.jetty.websocket.common.scopes.WebSocketContainerScope;
import org.eclipse.jetty.websocket.common.scopes.WebSocketSessionScope;

@ManagedObject(value="A Jetty WebSocket Session")
public class WebSocketSession
extends ContainerLifeCycle
implements Session,
RemoteEndpointFactory,
WebSocketSessionScope,
IncomingFrames,
Connection.Listener,
IOState.ConnectionStateListener {
    private static final Logger LOG = Log.getLogger(WebSocketSession.class);
    private static final Logger LOG_OPEN = Log.getLogger(WebSocketSession.class.getName() + "_OPEN");
    private final WebSocketContainerScope containerScope;
    private final URI requestURI;
    private final LogicalConnection connection;
    private final EventDriver websocket;
    private final Executor executor;
    private final WebSocketPolicy policy;
    private ClassLoader classLoader;
    private ExtensionFactory extensionFactory;
    private RemoteEndpointFactory remoteEndpointFactory;
    private String protocolVersion;
    private Map<String, String[]> parameterMap = new HashMap<String, String[]>();
    private RemoteEndpoint remote;
    private IncomingFrames incomingHandler;
    private OutgoingFrames outgoingHandler;
    private UpgradeRequest upgradeRequest;
    private UpgradeResponse upgradeResponse;
    private CompletableFuture<Session> openFuture;

    public WebSocketSession(WebSocketContainerScope containerScope, URI requestURI, EventDriver websocket, LogicalConnection connection) {
        Objects.requireNonNull(containerScope, "Container Scope cannot be null");
        Objects.requireNonNull(requestURI, "Request URI cannot be null");
        this.classLoader = Thread.currentThread().getContextClassLoader();
        this.containerScope = containerScope;
        this.requestURI = requestURI;
        this.websocket = websocket;
        this.connection = connection;
        this.executor = connection.getExecutor();
        this.outgoingHandler = connection;
        this.incomingHandler = websocket;
        this.connection.getIOState().addListener(this);
        this.policy = websocket.getPolicy();
        this.addBean(this.connection);
        this.addBean(this.websocket);
    }

    @Override
    public void close() {
        this.close(1000, null);
    }

    @Override
    public void close(CloseStatus closeStatus) {
        this.close(closeStatus.getCode(), closeStatus.getPhrase());
    }

    @Override
    public void close(int statusCode, String reason) {
        this.connection.close(statusCode, reason);
    }

    @Override
    public void disconnect() {
        this.connection.disconnect();
        this.notifyClose(1006, "Harsh disconnect");
    }

    public void dispatch(Runnable runnable) {
        this.executor.execute(runnable);
    }

    @Override
    protected void doStart() throws Exception {
        Iterator<RemoteEndpointFactory> iter;
        if (LOG.isDebugEnabled()) {
            LOG.debug("starting - {}", this);
        }
        if ((iter = ServiceLoader.load(RemoteEndpointFactory.class).iterator()).hasNext()) {
            this.remoteEndpointFactory = iter.next();
        }
        if (this.remoteEndpointFactory == null) {
            this.remoteEndpointFactory = this;
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("Using RemoteEndpointFactory: {}", this.remoteEndpointFactory);
        }
        super.doStart();
    }

    @Override
    protected void doStop() throws Exception {
        if (LOG.isDebugEnabled()) {
            LOG.debug("stopping - {}", this);
        }
        try {
            this.close(1001, "Shutdown");
        }
        catch (Throwable t) {
            LOG.debug("During Connection Shutdown", t);
        }
        super.doStop();
    }

    @Override
    public void dump(Appendable out, String indent) throws IOException {
        this.dumpThis(out);
        out.append(indent).append(" +- incomingHandler : ");
        if (this.incomingHandler instanceof Dumpable) {
            ((Dumpable)((Object)this.incomingHandler)).dump(out, indent + "    ");
        } else {
            out.append(this.incomingHandler.toString()).append(System.lineSeparator());
        }
        out.append(indent).append(" +- outgoingHandler : ");
        if (this.outgoingHandler instanceof Dumpable) {
            ((Dumpable)((Object)this.outgoingHandler)).dump(out, indent + "    ");
        } else {
            out.append(this.outgoingHandler.toString()).append(System.lineSeparator());
        }
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
        WebSocketSession other = (WebSocketSession)obj;
        return !(this.connection == null ? other.connection != null : !this.connection.equals(other.connection));
    }

    public ByteBufferPool getBufferPool() {
        return this.connection.getBufferPool();
    }

    public ClassLoader getClassLoader() {
        return this.getClass().getClassLoader();
    }

    public LogicalConnection getConnection() {
        return this.connection;
    }

    @Override
    public WebSocketContainerScope getContainerScope() {
        return this.containerScope;
    }

    public ExtensionFactory getExtensionFactory() {
        return this.extensionFactory;
    }

    @Override
    public long getIdleTimeout() {
        return this.connection.getMaxIdleTimeout();
    }

    @ManagedAttribute(readonly=true)
    public IncomingFrames getIncomingHandler() {
        return this.incomingHandler;
    }

    @Override
    public InetSocketAddress getLocalAddress() {
        return this.connection.getLocalAddress();
    }

    @ManagedAttribute(readonly=true)
    public OutgoingFrames getOutgoingHandler() {
        return this.outgoingHandler;
    }

    @Override
    public WebSocketPolicy getPolicy() {
        return this.policy;
    }

    @Override
    public String getProtocolVersion() {
        return this.protocolVersion;
    }

    @Override
    public RemoteEndpoint getRemote() {
        ConnectionState state;
        if (LOG_OPEN.isDebugEnabled()) {
            LOG_OPEN.debug("[{}] {}.getRemote()", new Object[]{this.policy.getBehavior(), this.getClass().getSimpleName()});
        }
        if ((state = this.connection.getIOState().getConnectionState()) == ConnectionState.OPEN || state == ConnectionState.CONNECTED) {
            return this.remote;
        }
        throw new WebSocketException("RemoteEndpoint unavailable, current state [" + (Object)((Object)state) + "], expecting [OPEN or CONNECTED]");
    }

    @Override
    public InetSocketAddress getRemoteAddress() {
        return this.remote.getInetSocketAddress();
    }

    public URI getRequestURI() {
        return this.requestURI;
    }

    @Override
    public UpgradeRequest getUpgradeRequest() {
        return this.upgradeRequest;
    }

    @Override
    public UpgradeResponse getUpgradeResponse() {
        return this.upgradeResponse;
    }

    @Override
    public WebSocketSession getWebSocketSession() {
        return this;
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.connection == null ? 0 : this.connection.hashCode());
        return result;
    }

    @Override
    public void incomingError(Throwable t) {
        this.websocket.incomingError(t);
    }

    @Override
    public void incomingFrame(Frame frame) {
        ClassLoader old = Thread.currentThread().getContextClassLoader();
        try {
            Thread.currentThread().setContextClassLoader(this.classLoader);
            if (this.connection.getIOState().isInputAvailable()) {
                this.incomingHandler.incomingFrame(frame);
            }
        }
        finally {
            Thread.currentThread().setContextClassLoader(old);
        }
    }

    @Override
    public boolean isOpen() {
        if (this.connection == null) {
            return false;
        }
        return this.connection.isOpen();
    }

    @Override
    public boolean isSecure() {
        if (this.upgradeRequest == null) {
            throw new IllegalStateException("No valid UpgradeRequest yet");
        }
        URI requestURI = this.upgradeRequest.getRequestURI();
        return "wss".equalsIgnoreCase(requestURI.getScheme());
    }

    public void notifyClose(int statusCode, String reason) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("notifyClose({},{})", statusCode, reason);
        }
        this.websocket.onClose(new CloseInfo(statusCode, reason));
    }

    public void notifyError(Throwable cause) {
        if (this.openFuture != null && !this.openFuture.isDone()) {
            this.openFuture.completeExceptionally(cause);
        }
        this.incomingError(cause);
    }

    @Override
    public void onClosed(Connection connection) {
    }

    @Override
    public void onOpened(Connection connection) {
        if (LOG_OPEN.isDebugEnabled()) {
            LOG_OPEN.debug("[{}] {}.onOpened()", new Object[]{this.policy.getBehavior(), this.getClass().getSimpleName()});
        }
        this.open();
    }

    @Override
    public void onConnectionStateChange(ConnectionState state) {
        switch (state) {
            case CLOSED: {
                IOState ioState = this.connection.getIOState();
                CloseInfo close = ioState.getCloseInfo();
                this.notifyClose(close.getStatusCode(), close.getReason());
                try {
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("{}.onSessionClosed()", this.containerScope.getClass().getSimpleName());
                    }
                    this.containerScope.onSessionClosed(this);
                }
                catch (Throwable t) {
                    LOG.ignore(t);
                }
                break;
            }
            case CONNECTED: {
                try {
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("{}.onSessionOpened()", this.containerScope.getClass().getSimpleName());
                    }
                    this.containerScope.onSessionOpened(this);
                    break;
                }
                catch (Throwable t) {
                    LOG.ignore(t);
                }
            }
        }
    }

    @Override
    public WebSocketRemoteEndpoint newRemoteEndpoint(LogicalConnection connection, OutgoingFrames outgoingFrames, BatchMode batchMode) {
        return new WebSocketRemoteEndpoint(connection, this.outgoingHandler, this.getBatchMode());
    }

    public void open() {
        if (LOG_OPEN.isDebugEnabled()) {
            LOG_OPEN.debug("[{}] {}.open()", new Object[]{this.policy.getBehavior(), this.getClass().getSimpleName()});
        }
        if (this.remote != null) {
            return;
        }
        try (ThreadClassLoaderScope scope = new ThreadClassLoaderScope(this.classLoader);){
            this.connection.getIOState().onConnected();
            this.remote = this.remoteEndpointFactory.newRemoteEndpoint(this.connection, this.outgoingHandler, this.getBatchMode());
            if (LOG_OPEN.isDebugEnabled()) {
                LOG_OPEN.debug("[{}] {}.open() remote={}", new Object[]{this.policy.getBehavior(), this.getClass().getSimpleName(), this.remote});
            }
            this.websocket.openSession(this);
            this.connection.getIOState().onOpened();
            if (LOG.isDebugEnabled()) {
                LOG.debug("open -> {}", this.dump());
            }
            if (this.openFuture != null) {
                this.openFuture.complete(this);
            }
        }
        catch (CloseException ce) {
            LOG.warn(ce);
            this.close(ce.getStatusCode(), ce.getMessage());
        }
        catch (Throwable t) {
            LOG.warn(t);
            int statusCode = 1011;
            if (this.policy.getBehavior() == WebSocketBehavior.CLIENT) {
                statusCode = 1008;
            }
            this.close(statusCode, t.getMessage());
        }
    }

    public void setExtensionFactory(ExtensionFactory extensionFactory) {
        this.extensionFactory = extensionFactory;
    }

    public void setFuture(CompletableFuture<Session> fut) {
        this.openFuture = fut;
    }

    @Override
    public void setIdleTimeout(long ms) {
        this.connection.setMaxIdleTimeout(ms);
    }

    public void setOutgoingHandler(OutgoingFrames outgoing) {
        this.outgoingHandler = outgoing;
    }

    @Deprecated
    public void setPolicy(WebSocketPolicy policy) {
    }

    public void setUpgradeRequest(UpgradeRequest request) {
        this.upgradeRequest = request;
        this.protocolVersion = request.getProtocolVersion();
        this.parameterMap.clear();
        if (request.getParameterMap() != null) {
            for (Map.Entry<String, List<String>> entry : request.getParameterMap().entrySet()) {
                List<String> values = entry.getValue();
                if (values != null) {
                    this.parameterMap.put(entry.getKey(), values.toArray(new String[values.size()]));
                    continue;
                }
                this.parameterMap.put(entry.getKey(), new String[0]);
            }
        }
    }

    public void setUpgradeResponse(UpgradeResponse response) {
        this.upgradeResponse = response;
    }

    @Override
    public SuspendToken suspend() {
        return this.connection.suspend();
    }

    public BatchMode getBatchMode() {
        return BatchMode.AUTO;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("WebSocketSession[");
        builder.append("websocket=").append(this.websocket);
        builder.append(",behavior=").append((Object)this.policy.getBehavior());
        builder.append(",connection=").append(this.connection);
        builder.append(",remote=").append(this.remote);
        builder.append(",incoming=").append(this.incomingHandler);
        builder.append(",outgoing=").append(this.outgoingHandler);
        builder.append("]");
        return builder.toString();
    }

    public static interface Listener {
        public void onOpened(WebSocketSession var1);

        public void onClosed(WebSocketSession var1);
    }
}

