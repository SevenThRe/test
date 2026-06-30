/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.server;

import java.io.IOException;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;

public interface WebSocketHandshake {
    public void doHandshakeResponse(ServletUpgradeRequest var1, ServletUpgradeResponse var2) throws IOException;
}

