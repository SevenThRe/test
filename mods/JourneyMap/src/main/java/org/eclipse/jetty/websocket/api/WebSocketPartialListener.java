/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.api;

import java.nio.ByteBuffer;
import org.eclipse.jetty.websocket.api.WebSocketConnectionListener;

public interface WebSocketPartialListener
extends WebSocketConnectionListener {
    public void onWebSocketPartialBinary(ByteBuffer var1, boolean var2);

    public void onWebSocketPartialText(String var1, boolean var2);
}

