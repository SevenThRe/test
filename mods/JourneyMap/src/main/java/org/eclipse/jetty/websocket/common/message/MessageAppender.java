/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.message;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface MessageAppender {
    public void appendFrame(ByteBuffer var1, boolean var2) throws IOException;

    public void messageComplete();
}

