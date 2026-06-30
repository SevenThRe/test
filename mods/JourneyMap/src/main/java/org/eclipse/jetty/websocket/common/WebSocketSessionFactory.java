/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common;

import java.net.URI;
import org.eclipse.jetty.websocket.common.LogicalConnection;
import org.eclipse.jetty.websocket.common.SessionFactory;
import org.eclipse.jetty.websocket.common.WebSocketSession;
import org.eclipse.jetty.websocket.common.events.EventDriver;
import org.eclipse.jetty.websocket.common.events.JettyAnnotatedEventDriver;
import org.eclipse.jetty.websocket.common.events.JettyListenerEventDriver;
import org.eclipse.jetty.websocket.common.scopes.WebSocketContainerScope;

public class WebSocketSessionFactory
implements SessionFactory {
    private final WebSocketContainerScope containerScope;

    public WebSocketSessionFactory(WebSocketContainerScope containerScope) {
        this.containerScope = containerScope;
    }

    @Override
    public boolean supports(EventDriver websocket) {
        return websocket instanceof JettyAnnotatedEventDriver || websocket instanceof JettyListenerEventDriver;
    }

    @Override
    public WebSocketSession createSession(URI requestURI, EventDriver websocket, LogicalConnection connection) {
        return new WebSocketSession(this.containerScope, requestURI, websocket, connection);
    }
}

