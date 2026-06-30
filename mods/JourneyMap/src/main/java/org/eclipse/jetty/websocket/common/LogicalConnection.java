/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common;

import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import org.eclipse.jetty.io.ByteBufferPool;
import org.eclipse.jetty.websocket.api.SuspendToken;
import org.eclipse.jetty.websocket.api.WebSocketPolicy;
import org.eclipse.jetty.websocket.api.extensions.IncomingFrames;
import org.eclipse.jetty.websocket.api.extensions.OutgoingFrames;
import org.eclipse.jetty.websocket.common.io.IOState;

public interface LogicalConnection
extends OutgoingFrames,
SuspendToken {
    public void close();

    public void close(int var1, String var2);

    public void disconnect();

    public ByteBufferPool getBufferPool();

    public Executor getExecutor();

    public long getIdleTimeout();

    public IOState getIOState();

    public InetSocketAddress getLocalAddress();

    public long getMaxIdleTimeout();

    public WebSocketPolicy getPolicy();

    public InetSocketAddress getRemoteAddress();

    public boolean isOpen();

    public boolean isReading();

    public void setMaxIdleTimeout(long var1);

    public void setNextIncomingFrames(IncomingFrames var1);

    public SuspendToken suspend();

    public String getId();
}

