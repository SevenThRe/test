/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.events;

import org.eclipse.jetty.websocket.api.WebSocketConnectionListener;
import org.eclipse.jetty.websocket.api.WebSocketListener;
import org.eclipse.jetty.websocket.api.WebSocketPolicy;
import org.eclipse.jetty.websocket.common.events.EventDriver;
import org.eclipse.jetty.websocket.common.events.EventDriverImpl;
import org.eclipse.jetty.websocket.common.events.JettyListenerEventDriver;

public class JettyListenerImpl
implements EventDriverImpl {
    @Override
    public EventDriver create(Object websocket, WebSocketPolicy policy) {
        WebSocketConnectionListener listener = (WebSocketConnectionListener)websocket;
        return new JettyListenerEventDriver(policy, listener);
    }

    @Override
    public String describeRule() {
        return "class implements " + WebSocketListener.class.getName();
    }

    @Override
    public boolean supports(Object websocket) {
        return websocket instanceof WebSocketConnectionListener;
    }
}

