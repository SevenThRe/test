/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.embeddedserver.jetty.websocket;

import info.journeymap.shaded.kotlin.spark.embeddedserver.jetty.websocket.WebSocketHandlerWrapper;
import java.util.Objects;

public class WebSocketHandlerInstanceWrapper
implements WebSocketHandlerWrapper {
    private final Object handler;

    public WebSocketHandlerInstanceWrapper(Object handler) {
        Objects.requireNonNull(handler, "WebSocket handler cannot be null");
        WebSocketHandlerWrapper.validateHandlerClass(handler.getClass());
        this.handler = handler;
    }

    @Override
    public Object getHandler() {
        return this.handler;
    }
}

