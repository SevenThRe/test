/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.message;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import org.eclipse.jetty.websocket.common.message.MessageAppender;
import org.eclipse.jetty.websocket.common.message.MessageInputStream;

public class MessageReader
extends InputStreamReader
implements MessageAppender {
    private final MessageInputStream stream;

    public MessageReader(MessageInputStream stream) {
        super((InputStream)stream, StandardCharsets.UTF_8);
        this.stream = stream;
    }

    @Override
    public void appendFrame(ByteBuffer payload, boolean isLast) throws IOException {
        this.stream.appendFrame(payload, isLast);
    }

    @Override
    public void messageComplete() {
        this.stream.messageComplete();
    }
}

