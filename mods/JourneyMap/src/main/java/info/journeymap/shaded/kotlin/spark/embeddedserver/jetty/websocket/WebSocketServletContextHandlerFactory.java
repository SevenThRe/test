/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.embeddedserver.jetty.websocket;

import info.journeymap.shaded.kotlin.spark.embeddedserver.jetty.websocket.WebSocketCreatorFactory;
import info.journeymap.shaded.kotlin.spark.embeddedserver.jetty.websocket.WebSocketHandlerWrapper;
import info.journeymap.shaded.org.slf4j.Logger;
import info.journeymap.shaded.org.slf4j.LoggerFactory;
import java.util.Map;
import java.util.Optional;
import org.eclipse.jetty.http.pathmap.PathSpec;
import org.eclipse.jetty.http.pathmap.ServletPathSpec;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.server.NativeWebSocketConfiguration;
import org.eclipse.jetty.websocket.server.WebSocketUpgradeFilter;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

public class WebSocketServletContextHandlerFactory {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketServletContextHandlerFactory.class);

    public static ServletContextHandler create(Map<String, WebSocketHandlerWrapper> webSocketHandlers, Optional<Integer> webSocketIdleTimeoutMillis) {
        ServletContextHandler webSocketServletContextHandler = null;
        if (webSocketHandlers != null) {
            try {
                webSocketServletContextHandler = new ServletContextHandler(null, "/", true, false);
                WebSocketUpgradeFilter webSocketUpgradeFilter = WebSocketUpgradeFilter.configureContext(webSocketServletContextHandler);
                if (webSocketIdleTimeoutMillis.isPresent()) {
                    webSocketUpgradeFilter.getFactory().getPolicy().setIdleTimeout(webSocketIdleTimeoutMillis.get().intValue());
                }
                NativeWebSocketConfiguration webSocketConfiguration = (NativeWebSocketConfiguration)webSocketServletContextHandler.getServletContext().getAttribute(NativeWebSocketConfiguration.class.getName());
                for (String path : webSocketHandlers.keySet()) {
                    WebSocketCreator webSocketCreator = WebSocketCreatorFactory.create(webSocketHandlers.get(path));
                    webSocketConfiguration.addMapping((PathSpec)new ServletPathSpec(path), webSocketCreator);
                }
            }
            catch (Exception ex) {
                logger.error("creation of websocket context handler failed.", ex);
                webSocketServletContextHandler = null;
            }
        }
        return webSocketServletContextHandler;
    }
}

