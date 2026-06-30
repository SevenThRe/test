/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.api;

import org.eclipse.jetty.websocket.api.Session;

public interface WebSocketConnectionListener {
    public void onWebSocketClose(int var1, String var2);

    public void onWebSocketConnect(Session var1);

    public void onWebSocketError(Throwable var1);
}

