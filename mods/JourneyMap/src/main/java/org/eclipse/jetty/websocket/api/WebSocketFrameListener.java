/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.api;

import org.eclipse.jetty.websocket.api.WebSocketConnectionListener;
import org.eclipse.jetty.websocket.api.extensions.Frame;

public interface WebSocketFrameListener
extends WebSocketConnectionListener {
    public void onWebSocketFrame(Frame var1);
}

