/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.api;

import java.nio.ByteBuffer;
import org.eclipse.jetty.websocket.api.WebSocketConnectionListener;

public interface WebSocketPingPongListener
extends WebSocketConnectionListener {
    public void onWebSocketPing(ByteBuffer var1);

    public void onWebSocketPong(ByteBuffer var1);
}

