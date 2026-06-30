/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.embeddedserver.jetty.websocket;

import info.journeymap.shaded.kotlin.spark.embeddedserver.jetty.websocket.WebSocketHandlerWrapper;
import java.util.Objects;

public class WebSocketHandlerClassWrapper
implements WebSocketHandlerWrapper {
    private final Class<?> handlerClass;

    public WebSocketHandlerClassWrapper(Class<?> handlerClass) {
        Objects.requireNonNull(handlerClass, "WebSocket handler class cannot be null");
        WebSocketHandlerWrapper.validateHandlerClass(handlerClass);
        this.handlerClass = handlerClass;
    }

    @Override
    public Object getHandler() {
        try {
            return this.handlerClass.newInstance();
        }
        catch (IllegalAccessException | InstantiationException ex) {
            throw new RuntimeException("Could not instantiate websocket handler", ex);
        }
    }
}

