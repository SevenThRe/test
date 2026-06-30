/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.embeddedserver.jetty.websocket;

import org.eclipse.jetty.websocket.api.WebSocketListener;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

public interface WebSocketHandlerWrapper {
    public Object getHandler();

    public static void validateHandlerClass(Class<?> handlerClass) {
        boolean valid;
        boolean bl = valid = WebSocketListener.class.isAssignableFrom(handlerClass) || handlerClass.isAnnotationPresent(WebSocket.class);
        if (!valid) {
            throw new IllegalArgumentException("WebSocket handler must implement 'WebSocketListener' or be annotated as '@WebSocket'");
        }
    }
}

