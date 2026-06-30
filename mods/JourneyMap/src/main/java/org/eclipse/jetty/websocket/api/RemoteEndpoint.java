/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.api;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.concurrent.Future;
import org.eclipse.jetty.websocket.api.BatchMode;
import org.eclipse.jetty.websocket.api.WriteCallback;

public interface RemoteEndpoint {
    public void sendBytes(ByteBuffer var1) throws IOException;

    public Future<Void> sendBytesByFuture(ByteBuffer var1);

    public void sendBytes(ByteBuffer var1, WriteCallback var2);

    public void sendPartialBytes(ByteBuffer var1, boolean var2) throws IOException;

    public void sendPartialString(String var1, boolean var2) throws IOException;

    public void sendPing(ByteBuffer var1) throws IOException;

    public void sendPong(ByteBuffer var1) throws IOException;

    public void sendString(String var1) throws IOException;

    public Future<Void> sendStringByFuture(String var1);

    public void sendString(String var1, WriteCallback var2);

    public BatchMode getBatchMode();

    public void setBatchMode(BatchMode var1);

    public InetSocketAddress getInetSocketAddress();

    public void flush() throws IOException;
}

