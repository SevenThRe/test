/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.server;

import org.eclipse.jetty.websocket.server.pathmap.PathSpec;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

public interface MappedWebSocketCreator {
    public void addMapping(String var1, WebSocketCreator var2);

    @Deprecated
    public void addMapping(PathSpec var1, WebSocketCreator var2);

    public void addMapping(org.eclipse.jetty.http.pathmap.PathSpec var1, WebSocketCreator var2);

    public WebSocketCreator getMapping(String var1);

    public boolean removeMapping(String var1);
}

