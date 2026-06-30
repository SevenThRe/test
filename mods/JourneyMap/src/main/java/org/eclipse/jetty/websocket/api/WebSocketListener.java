/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.api;

import org.eclipse.jetty.websocket.api.WebSocketConnectionListener;

public interface WebSocketListener
extends WebSocketConnectionListener {
    public void onWebSocketBinary(byte[] var1, int var2, int var3);

    public void onWebSocketText(String var1);
}

