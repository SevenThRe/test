/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.embeddedserver.jetty;

import info.journeymap.shaded.kotlin.spark.embeddedserver.EmbeddedServer;
import info.journeymap.shaded.kotlin.spark.embeddedserver.jetty.JettyServerFactory;
import info.journeymap.shaded.kotlin.spark.embeddedserver.jetty.SocketConnectorFactory;
import info.journeymap.shaded.kotlin.spark.embeddedserver.jetty.websocket.WebSocketHandlerWrapper;
import info.journeymap.shaded.kotlin.spark.embeddedserver.jetty.websocket.WebSocketServletContextHandlerFactory;
import info.journeymap.shaded.kotlin.spark.ssl.SslStores;
import info.journeymap.shaded.org.slf4j.Logger;
import info.journeymap.shaded.org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class EmbeddedJettyServer
implements EmbeddedServer {
    private static final int SPARK_DEFAULT_PORT = 4567;
    private static final String NAME = "Spark";
    private final JettyServerFactory serverFactory;
    private final Handler handler;
    private Server server;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Map<String, WebSocketHandlerWrapper> webSocketHandlers;
    private Optional<Integer> webSocketIdleTimeoutMillis;

    public EmbeddedJettyServer(JettyServerFactory serverFactory, Handler handler) {
        this.serverFactory = serverFactory;
        this.handler = handler;
    }

    @Override
    public void configureWebSockets(Map<String, WebSocketHandlerWrapper> webSocketHandlers, Optional<Integer> webSocketIdleTimeoutMillis) {
        this.webSocketHandlers = webSocketHandlers;
        this.webSocketIdleTimeoutMillis = webSocketIdleTimeoutMillis;
    }

    @Override
    public int ignite(String host, int port, SslStores sslStores, int maxThreads, int minThreads, int threadIdleTimeoutMillis) throws Exception {
        if (port == 0) {
            try (ServerSocket s = new ServerSocket(0);){
                port = s.getLocalPort();
            }
            catch (IOException e) {
                this.logger.error("Could not get first available port (port set to 0), using default: {}", (Object)4567);
                port = 4567;
            }
        }
        this.server = this.serverFactory.create(maxThreads, minThreads, threadIdleTimeoutMillis);
        ServerConnector connector = sslStores == null ? SocketConnectorFactory.createSocketConnector(this.server, host, port) : SocketConnectorFactory.createSecureSocketConnector(this.server, host, port, sslStores);
        this.server = connector.getServer();
        this.server.setConnectors(new Connector[]{connector});
        ServletContextHandler webSocketServletContextHandler = WebSocketServletContextHandlerFactory.create(this.webSocketHandlers, this.webSocketIdleTimeoutMillis);
        if (webSocketServletContextHandler == null) {
            this.server.setHandler(this.handler);
        } else {
            ArrayList<Handler> handlersInList = new ArrayList<Handler>();
            handlersInList.add(this.handler);
            if (webSocketServletContextHandler != null) {
                handlersInList.add(webSocketServletContextHandler);
            }
            HandlerList handlers = new HandlerList();
            handlers.setHandlers(handlersInList.toArray(new Handler[handlersInList.size()]));
            this.server.setHandler(handlers);
        }
        this.logger.info("== {} has ignited ...", (Object)NAME);
        this.logger.info(">> Listening on {}:{}", (Object)host, (Object)port);
        this.server.start();
        return port;
    }

    @Override
    public void join() throws InterruptedException {
        this.server.join();
    }

    @Override
    public void extinguish() {
        this.logger.info(">>> {} shutting down ...", (Object)NAME);
        try {
            if (this.server != null) {
                this.server.stop();
            }
        }
        catch (Exception e) {
            this.logger.error("stop failed", e);
            System.exit(100);
        }
        this.logger.info("done");
    }

    @Override
    public int activeThreadCount() {
        if (this.server == null) {
            return 0;
        }
        return this.server.getThreadPool().getThreads() - this.server.getThreadPool().getIdleThreads();
    }
}

