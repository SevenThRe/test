/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.server;

import java.io.IOException;
import org.eclipse.jetty.websocket.common.AcceptHash;
import org.eclipse.jetty.websocket.server.WebSocketHandshake;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;

public class HandshakeRFC6455
implements WebSocketHandshake {
    public static final int VERSION = 13;

    @Override
    public void doHandshakeResponse(ServletUpgradeRequest request, ServletUpgradeResponse response) throws IOException {
        String key = request.getHeader("Sec-WebSocket-Key");
        if (key == null) {
            throw new IllegalStateException("Missing request header 'Sec-WebSocket-Key'");
        }
        response.setHeader("Upgrade", "WebSocket");
        response.addHeader("Connection", "Upgrade");
        response.addHeader("Sec-WebSocket-Accept", AcceptHash.hashKey(key));
        request.complete();
        response.setStatusCode(101);
        response.complete();
    }
}

