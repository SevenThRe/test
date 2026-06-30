/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.server;

import info.journeymap.shaded.org.javax.servlet.ServletContext;
import info.journeymap.shaded.org.javax.servlet.http.HttpServletRequest;
import info.journeymap.shaded.org.javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.io.ByteBufferPool;
import org.eclipse.jetty.io.EndPoint;
import org.eclipse.jetty.io.MappedByteBufferPool;
import org.eclipse.jetty.server.ConnectionFactory;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnection;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.util.DecoratedObjectFactory;
import org.eclipse.jetty.util.StringUtil;
import org.eclipse.jetty.util.component.ContainerLifeCycle;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.util.thread.ScheduledExecutorScheduler;
import org.eclipse.jetty.util.thread.Scheduler;
import org.eclipse.jetty.websocket.api.InvalidWebSocketException;
import org.eclipse.jetty.websocket.api.WebSocketException;
import org.eclipse.jetty.websocket.api.WebSocketPolicy;
import org.eclipse.jetty.websocket.api.extensions.ExtensionFactory;
import org.eclipse.jetty.websocket.api.util.QuoteUtil;
import org.eclipse.jetty.websocket.common.LogicalConnection;
import org.eclipse.jetty.websocket.common.SessionFactory;
import org.eclipse.jetty.websocket.common.WebSocketSession;
import org.eclipse.jetty.websocket.common.WebSocketSessionFactory;
import org.eclipse.jetty.websocket.common.events.EventDriver;
import org.eclipse.jetty.websocket.common.events.EventDriverFactory;
import org.eclipse.jetty.websocket.common.extensions.ExtensionStack;
import org.eclipse.jetty.websocket.common.extensions.WebSocketExtensionFactory;
import org.eclipse.jetty.websocket.common.scopes.WebSocketContainerScope;
import org.eclipse.jetty.websocket.server.HandshakeRFC6455;
import org.eclipse.jetty.websocket.server.WebSocketHandshake;
import org.eclipse.jetty.websocket.server.WebSocketServerConnection;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class WebSocketServerFactory
extends ContainerLifeCycle
implements WebSocketCreator,
WebSocketContainerScope,
WebSocketServletFactory {
    private static final Logger LOG = Log.getLogger(WebSocketServerFactory.class);
    private final ClassLoader contextClassloader;
    private final Map<Integer, WebSocketHandshake> handshakes = new HashMap<Integer, WebSocketHandshake>();
    private final Scheduler scheduler = new ScheduledExecutorScheduler();
    private final List<WebSocketSession.Listener> listeners = new CopyOnWriteArrayList<WebSocketSession.Listener>();
    private final String supportedVersions;
    private final WebSocketPolicy defaultPolicy;
    private final EventDriverFactory eventDriverFactory;
    private final ByteBufferPool bufferPool;
    private final WebSocketExtensionFactory extensionFactory;
    private final ServletContext context;
    private final List<SessionFactory> sessionFactories = new ArrayList<SessionFactory>();
    private final List<Class<?>> registeredSocketClasses = new ArrayList();
    private Executor executor;
    private DecoratedObjectFactory objectFactory;
    private WebSocketCreator creator;

    public WebSocketServerFactory(ServletContext context) {
        this(context, WebSocketPolicy.newServerPolicy(), (ByteBufferPool)new MappedByteBufferPool());
    }

    public WebSocketServerFactory(ServletContext context, ByteBufferPool bufferPool) {
        this(context, WebSocketPolicy.newServerPolicy(), bufferPool);
    }

    public WebSocketServerFactory(ServletContext context, WebSocketPolicy policy) {
        this(context, policy, (ByteBufferPool)new MappedByteBufferPool());
    }

    public WebSocketServerFactory(ServletContext context, WebSocketPolicy policy, ByteBufferPool bufferPool) {
        this(Objects.requireNonNull(context, ServletContext.class.getName()), policy, null, null, bufferPool);
    }

    protected WebSocketServerFactory(WebSocketPolicy policy, Executor executor, ByteBufferPool bufferPool) {
        this(null, policy, new DecoratedObjectFactory(), executor, bufferPool);
    }

    private WebSocketServerFactory(ServletContext context, WebSocketPolicy policy, DecoratedObjectFactory objectFactory, Executor executor, ByteBufferPool bufferPool) {
        this.context = context;
        this.objectFactory = objectFactory;
        this.executor = executor;
        this.handshakes.put(13, new HandshakeRFC6455());
        this.addBean(this.scheduler);
        this.addBean(bufferPool);
        this.contextClassloader = Thread.currentThread().getContextClassLoader();
        this.defaultPolicy = policy;
        this.eventDriverFactory = new EventDriverFactory(this);
        this.bufferPool = bufferPool;
        this.extensionFactory = new WebSocketExtensionFactory(this);
        this.sessionFactories.add(new WebSocketSessionFactory(this));
        this.creator = this;
        ArrayList<Integer> versions = new ArrayList<Integer>();
        for (int v : this.handshakes.keySet()) {
            versions.add(v);
        }
        Collections.sort(versions, Collections.reverseOrder());
        StringBuilder rv = new StringBuilder();
        Iterator iterator2 = versions.iterator();
        while (iterator2.hasNext()) {
            int v = (Integer)iterator2.next();
            if (rv.length() > 0) {
                rv.append(", ");
            }
            rv.append(v);
        }
        this.supportedVersions = rv.toString();
    }

    public void addSessionListener(WebSocketSession.Listener listener) {
        this.listeners.add(listener);
    }

    public void removeSessionListener(WebSocketSession.Listener listener) {
        this.listeners.remove(listener);
    }

    @Override
    public boolean acceptWebSocket(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return this.acceptWebSocket(this.getCreator(), request, response);
    }

    @Override
    public boolean acceptWebSocket(WebSocketCreator creator, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ClassLoader old = Thread.currentThread().getContextClassLoader();
        try {
            Thread.currentThread().setContextClassLoader(this.contextClassloader);
            ServletUpgradeRequest sockreq = new ServletUpgradeRequest(request);
            ServletUpgradeResponse sockresp = new ServletUpgradeResponse(response);
            Object websocketPojo = creator.createWebSocket(sockreq, sockresp);
            if (sockresp.isCommitted()) {
                boolean bl = false;
                return bl;
            }
            if (websocketPojo == null) {
                sockresp.sendError(503, "Endpoint Creation Failed");
                boolean bl = false;
                return bl;
            }
            websocketPojo = this.getObjectFactory().decorate(websocketPojo);
            HttpConnection connection = (HttpConnection)request.getAttribute("org.eclipse.jetty.server.HttpConnection");
            EventDriver driver = this.eventDriverFactory.wrap(websocketPojo);
            boolean bl = this.upgrade(connection, sockreq, sockresp, driver);
            return bl;
        }
        catch (URISyntaxException e) {
            throw new IOException("Unable to accept websocket due to mangled URI", e);
        }
        finally {
            Thread.currentThread().setContextClassLoader(old);
        }
    }

    public void addSessionFactory(SessionFactory sessionFactory) {
        if (this.sessionFactories.contains(sessionFactory)) {
            return;
        }
        this.sessionFactories.add(sessionFactory);
    }

    private WebSocketSession createSession(URI requestURI, EventDriver websocket, LogicalConnection connection) {
        if (websocket == null) {
            throw new InvalidWebSocketException("Unable to create Session from null websocket");
        }
        for (SessionFactory impl : this.sessionFactories) {
            if (!impl.supports(websocket)) continue;
            try {
                return impl.createSession(requestURI, websocket, connection);
            }
            catch (Throwable e) {
                throw new InvalidWebSocketException("Unable to create Session", e);
            }
        }
        throw new InvalidWebSocketException("Unable to create Session: unrecognized internal EventDriver type: " + websocket.getClass().getName());
    }

    @Override
    public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp) {
        if (this.registeredSocketClasses.size() < 1) {
            throw new WebSocketException("No WebSockets have been registered with the factory.  Cannot use default implementation of WebSocketCreator.");
        }
        if (this.registeredSocketClasses.size() > 1) {
            LOG.warn("You have registered more than 1 websocket object, and are using the default WebSocketCreator! Using first registered websocket.", new Object[0]);
        }
        Class<?> firstClass = this.registeredSocketClasses.get(0);
        try {
            return this.objectFactory.createInstance(firstClass);
        }
        catch (IllegalAccessException | InstantiationException e) {
            throw new WebSocketException("Unable to create instance of " + firstClass, e);
        }
    }

    @Override
    protected void doStart() throws Exception {
        if (this.objectFactory == null && this.context != null) {
            this.objectFactory = (DecoratedObjectFactory)this.context.getAttribute(DecoratedObjectFactory.ATTR);
            if (this.objectFactory == null) {
                throw new IllegalStateException("Unable to find required ServletContext attribute: " + DecoratedObjectFactory.ATTR);
            }
        }
        if (this.executor == null && this.context != null) {
            ContextHandler contextHandler = ContextHandler.getContextHandler(this.context);
            this.executor = contextHandler.getServer().getThreadPool();
        }
        Objects.requireNonNull(this.objectFactory, DecoratedObjectFactory.class.getName());
        Objects.requireNonNull(this.executor, Executor.class.getName());
        super.doStart();
    }

    @Override
    public ByteBufferPool getBufferPool() {
        return this.bufferPool;
    }

    @Override
    public WebSocketCreator getCreator() {
        return this.creator;
    }

    @Override
    public Executor getExecutor() {
        return this.executor;
    }

    @Override
    public DecoratedObjectFactory getObjectFactory() {
        return this.objectFactory;
    }

    public EventDriverFactory getEventDriverFactory() {
        return this.eventDriverFactory;
    }

    @Override
    public ExtensionFactory getExtensionFactory() {
        return this.extensionFactory;
    }

    public Collection<WebSocketSession> getOpenSessions() {
        return this.getBeans(WebSocketSession.class);
    }

    @Override
    public WebSocketPolicy getPolicy() {
        return this.defaultPolicy;
    }

    @Override
    public SslContextFactory getSslContextFactory() {
        return null;
    }

    @Override
    public boolean isUpgradeRequest(HttpServletRequest request, HttpServletResponse response) {
        String upgrade = request.getHeader("Upgrade");
        if (upgrade == null) {
            return false;
        }
        if (!"websocket".equalsIgnoreCase(upgrade)) {
            return false;
        }
        String connection = request.getHeader("Connection");
        if (connection == null) {
            return false;
        }
        boolean foundUpgradeToken = false;
        Iterator<String> iter = QuoteUtil.splitAt(connection, ",");
        while (iter.hasNext()) {
            String token = iter.next();
            if (!"upgrade".equalsIgnoreCase(token)) continue;
            foundUpgradeToken = true;
            break;
        }
        if (!foundUpgradeToken) {
            return false;
        }
        if (!"GET".equalsIgnoreCase(request.getMethod())) {
            return false;
        }
        if (!"HTTP/1.1".equals(request.getProtocol())) {
            LOG.debug("Not a 'HTTP/1.1' request (was [" + request.getProtocol() + "])", new Object[0]);
            return false;
        }
        return true;
    }

    @Override
    public void onSessionOpened(WebSocketSession session) {
        this.addManaged(session);
        this.notifySessionListeners(listener -> listener.onOpened(session));
    }

    @Override
    public void onSessionClosed(WebSocketSession session) {
        this.removeBean(session);
        this.notifySessionListeners(listener -> listener.onClosed(session));
    }

    private void notifySessionListeners(Consumer<WebSocketSession.Listener> consumer) {
        for (WebSocketSession.Listener listener : this.listeners) {
            try {
                consumer.accept(listener);
            }
            catch (Throwable x) {
                LOG.info("Exception while invoking listener " + listener, x);
            }
        }
    }

    @Override
    public void register(Class<?> websocketPojo) {
        this.registeredSocketClasses.add(websocketPojo);
    }

    @Override
    public void setCreator(WebSocketCreator creator) {
        this.creator = creator;
    }

    private boolean upgrade(HttpConnection http, ServletUpgradeRequest request, ServletUpgradeResponse response, EventDriver driver) throws IOException {
        WebSocketHandshake handshaker;
        if (!"websocket".equalsIgnoreCase(request.getHeader("Upgrade"))) {
            throw new IllegalStateException("Not a 'WebSocket: Upgrade' request");
        }
        if (!"HTTP/1.1".equals(request.getHttpVersion())) {
            throw new IllegalStateException("Not a 'HTTP/1.1' request");
        }
        int version = request.getHeaderInt("Sec-WebSocket-Version");
        if (version < 0) {
            version = request.getHeaderInt("Sec-WebSocket-Draft");
        }
        if ((handshaker = this.handshakes.get(version)) == null) {
            StringBuilder warn = new StringBuilder();
            warn.append("Client ").append(request.getRemoteAddress());
            warn.append(" (:").append(request.getRemotePort());
            warn.append(") User Agent: ");
            String ua = request.getHeader("User-Agent");
            if (ua == null) {
                warn.append("[unset] ");
            } else {
                warn.append('\"').append(StringUtil.sanitizeXmlString(ua)).append("\" ");
            }
            warn.append("requested WebSocket version [").append(version);
            warn.append("], Jetty supports version");
            if (this.handshakes.size() > 1) {
                warn.append('s');
            }
            warn.append(": [").append(this.supportedVersions).append("]");
            LOG.warn(warn.toString(), new Object[0]);
            response.setHeader("Sec-WebSocket-Version", this.supportedVersions);
            response.sendError(400, "Unsupported websocket version specification");
            return false;
        }
        ExtensionStack extensionStack = new ExtensionStack(this.getExtensionFactory());
        if (response.isExtensionsNegotiated()) {
            extensionStack.negotiate(response.getExtensions());
        } else {
            extensionStack.negotiate(request.getExtensions());
        }
        EndPoint endp = http.getEndPoint();
        Connector connector = http.getConnector();
        Executor executor = connector.getExecutor();
        ByteBufferPool bufferPool = connector.getByteBufferPool();
        WebSocketServerConnection wsConnection = new WebSocketServerConnection(endp, executor, this.scheduler, driver.getPolicy(), bufferPool);
        extensionStack.setPolicy(driver.getPolicy());
        extensionStack.configure(wsConnection.getParser());
        extensionStack.configure(wsConnection.getGenerator());
        if (LOG.isDebugEnabled()) {
            LOG.debug("HttpConnection: {}", http);
            LOG.debug("WebSocketConnection: {}", wsConnection);
        }
        WebSocketSession session = this.createSession(request.getRequestURI(), driver, wsConnection);
        session.setUpgradeRequest(request);
        response.setExtensions(extensionStack.getNegotiatedExtensions());
        session.setUpgradeResponse(response);
        wsConnection.addListener(session);
        wsConnection.setNextIncomingFrames(extensionStack);
        extensionStack.setNextIncoming(session);
        session.setOutgoingHandler(extensionStack);
        extensionStack.setNextOutgoing(wsConnection);
        session.addManaged(extensionStack);
        this.addManaged(session);
        if (session.isFailed()) {
            throw new IOException("Session failed to start");
        }
        request.setServletAttribute("org.eclipse.jetty.server.HttpConnection.UPGRADE", wsConnection);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Handshake Response: {}", handshaker);
        }
        if (this.getSendServerVersion(connector)) {
            response.setHeader("Server", HttpConfiguration.SERVER_VERSION);
        }
        handshaker.doHandshakeResponse(request, response);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Websocket upgrade {} {} {} {}", request.getRequestURI(), version, response.getAcceptedSubProtocol(), wsConnection);
        }
        return true;
    }

    private boolean getSendServerVersion(Connector connector) {
        HttpConfiguration httpConf;
        ConnectionFactory connFactory = connector.getConnectionFactory(HttpVersion.HTTP_1_1.asString());
        if (connFactory == null) {
            return false;
        }
        if (connFactory instanceof HttpConnectionFactory && (httpConf = ((HttpConnectionFactory)connFactory).getHttpConfiguration()) != null) {
            return httpConf.getSendServerVersion();
        }
        return false;
    }
}

