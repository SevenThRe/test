/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.embeddedserver.jetty.websocket;

import info.journeymap.shaded.kotlin.spark.embeddedserver.jetty.websocket.WebSocketHandlerWrapper;
import java.util.Objects;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

public class WebSocketCreatorFactory {
    public static WebSocketCreator create(WebSocketHandlerWrapper handlerWrapper) {
        return new SparkWebSocketCreator(handlerWrapper.getHandler());
    }

    static class SparkWebSocketCreator
    implements WebSocketCreator {
        private final Object handler;

        private SparkWebSocketCreator(Object handler) {
            this.handler = Objects.requireNonNull(handler, "handler cannot be null");
        }

        @Override
        public Object createWebSocket(ServletUpgradeRequest request, ServletUpgradeResponse response) {
            return this.handler;
        }

        Object getHandler() {
            return this.handler;
        }
    }
}

