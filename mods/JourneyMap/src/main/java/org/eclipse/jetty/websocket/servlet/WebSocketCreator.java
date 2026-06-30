/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.servlet;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;

public interface WebSocketCreator {
    public Object createWebSocket(ServletUpgradeRequest var1, ServletUpgradeResponse var2);
}

