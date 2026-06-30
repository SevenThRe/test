/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.scopes;

import org.eclipse.jetty.websocket.common.WebSocketSession;
import org.eclipse.jetty.websocket.common.scopes.WebSocketContainerScope;

public interface WebSocketSessionScope {
    public WebSocketSession getWebSocketSession();

    public WebSocketContainerScope getContainerScope();
}

