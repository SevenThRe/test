/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.api;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import org.eclipse.jetty.websocket.api.CloseStatus;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.SuspendToken;
import org.eclipse.jetty.websocket.api.UpgradeRequest;
import org.eclipse.jetty.websocket.api.UpgradeResponse;
import org.eclipse.jetty.websocket.api.WebSocketPolicy;

public interface Session
extends Closeable {
    @Override
    public void close();

    public void close(CloseStatus var1);

    public void close(int var1, String var2);

    public void disconnect() throws IOException;

    public long getIdleTimeout();

    public InetSocketAddress getLocalAddress();

    public WebSocketPolicy getPolicy();

    public String getProtocolVersion();

    public RemoteEndpoint getRemote();

    public InetSocketAddress getRemoteAddress();

    public UpgradeRequest getUpgradeRequest();

    public UpgradeResponse getUpgradeResponse();

    public boolean isOpen();

    public boolean isSecure();

    public void setIdleTimeout(long var1);

    public SuspendToken suspend();
}

