/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.events;

import java.util.concurrent.ConcurrentHashMap;
import org.eclipse.jetty.websocket.api.WebSocketPolicy;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.common.events.EventDriver;
import org.eclipse.jetty.websocket.common.events.EventDriverImpl;
import org.eclipse.jetty.websocket.common.events.JettyAnnotatedEventDriver;
import org.eclipse.jetty.websocket.common.events.JettyAnnotatedMetadata;
import org.eclipse.jetty.websocket.common.events.JettyAnnotatedScanner;

public class JettyAnnotatedImpl
implements EventDriverImpl {
    private ConcurrentHashMap<Class<?>, JettyAnnotatedMetadata> cache = new ConcurrentHashMap();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public EventDriver create(Object websocket, WebSocketPolicy policy) {
        Class<?> websocketClass = websocket.getClass();
        JettyAnnotatedImpl jettyAnnotatedImpl = this;
        synchronized (jettyAnnotatedImpl) {
            JettyAnnotatedMetadata metadata = this.cache.get(websocketClass);
            if (metadata == null) {
                JettyAnnotatedScanner scanner = new JettyAnnotatedScanner();
                metadata = scanner.scan(websocketClass);
                this.cache.put(websocketClass, metadata);
            }
            return new JettyAnnotatedEventDriver(policy, websocket, metadata);
        }
    }

    @Override
    public String describeRule() {
        return "class is annotated with @" + WebSocket.class.getName();
    }

    @Override
    public boolean supports(Object websocket) {
        WebSocket anno = websocket.getClass().getAnnotation(WebSocket.class);
        return anno != null;
    }

    public String toString() {
        return String.format("%s [cache.count=%d]", this.getClass().getSimpleName(), this.cache.size());
    }
}

