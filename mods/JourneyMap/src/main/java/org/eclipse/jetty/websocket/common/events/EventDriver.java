/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.events;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import org.eclipse.jetty.websocket.api.BatchMode;
import org.eclipse.jetty.websocket.api.WebSocketPolicy;
import org.eclipse.jetty.websocket.api.extensions.Frame;
import org.eclipse.jetty.websocket.api.extensions.IncomingFrames;
import org.eclipse.jetty.websocket.common.CloseInfo;
import org.eclipse.jetty.websocket.common.WebSocketSession;

public interface EventDriver
extends IncomingFrames {
    public WebSocketPolicy getPolicy();

    public WebSocketSession getSession();

    public BatchMode getBatchMode();

    public void onBinaryFrame(ByteBuffer var1, boolean var2) throws IOException;

    public void onBinaryMessage(byte[] var1);

    public void onClose(CloseInfo var1);

    public void onConnect();

    public void onContinuationFrame(ByteBuffer var1, boolean var2) throws IOException;

    public void onError(Throwable var1);

    public void onFrame(Frame var1);

    public void onInputStream(InputStream var1) throws IOException;

    public void onPing(ByteBuffer var1);

    public void onPong(ByteBuffer var1);

    public void onReader(Reader var1) throws IOException;

    public void onTextFrame(ByteBuffer var1, boolean var2) throws IOException;

    public void onTextMessage(String var1);

    public void openSession(WebSocketSession var1);
}

