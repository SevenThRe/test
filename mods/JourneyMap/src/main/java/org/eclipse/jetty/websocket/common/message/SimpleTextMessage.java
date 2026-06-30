/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.message;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.eclipse.jetty.util.Utf8StringBuilder;
import org.eclipse.jetty.websocket.common.events.EventDriver;
import org.eclipse.jetty.websocket.common.message.MessageAppender;

public class SimpleTextMessage
implements MessageAppender {
    private final EventDriver onEvent;
    protected final Utf8StringBuilder utf;
    private int size = 0;
    protected boolean finished;

    public SimpleTextMessage(EventDriver onEvent) {
        this.onEvent = onEvent;
        this.utf = new Utf8StringBuilder(1024);
        this.size = 0;
        this.finished = false;
    }

    @Override
    public void appendFrame(ByteBuffer payload, boolean isLast) throws IOException {
        if (this.finished) {
            throw new IOException("Cannot append to finished buffer");
        }
        if (payload == null) {
            return;
        }
        this.onEvent.getPolicy().assertValidTextMessageSize(this.size + payload.remaining());
        this.size += payload.remaining();
        this.utf.append(payload);
    }

    @Override
    public void messageComplete() {
        this.finished = true;
        this.onEvent.onTextMessage(this.utf.toString());
    }
}

