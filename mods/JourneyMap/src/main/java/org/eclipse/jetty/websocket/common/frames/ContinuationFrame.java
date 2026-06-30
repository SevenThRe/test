/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.frames;

import java.nio.ByteBuffer;
import org.eclipse.jetty.util.StringUtil;
import org.eclipse.jetty.websocket.api.extensions.Frame;
import org.eclipse.jetty.websocket.common.frames.DataFrame;

public class ContinuationFrame
extends DataFrame {
    public ContinuationFrame() {
        super((byte)0);
    }

    @Override
    public ContinuationFrame setPayload(ByteBuffer buf) {
        super.setPayload(buf);
        return this;
    }

    public ContinuationFrame setPayload(byte[] buf) {
        return this.setPayload(ByteBuffer.wrap(buf));
    }

    public ContinuationFrame setPayload(String message) {
        return this.setPayload(StringUtil.getUtf8Bytes(message));
    }

    @Override
    public Frame.Type getType() {
        return Frame.Type.CONTINUATION;
    }
}

