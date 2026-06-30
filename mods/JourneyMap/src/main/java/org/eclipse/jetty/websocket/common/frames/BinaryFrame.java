/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.frames;

import java.nio.ByteBuffer;
import org.eclipse.jetty.util.StringUtil;
import org.eclipse.jetty.websocket.api.extensions.Frame;
import org.eclipse.jetty.websocket.common.frames.DataFrame;

public class BinaryFrame
extends DataFrame {
    public BinaryFrame() {
        super((byte)2);
    }

    @Override
    public BinaryFrame setPayload(ByteBuffer buf) {
        super.setPayload(buf);
        return this;
    }

    public BinaryFrame setPayload(byte[] buf) {
        this.setPayload(ByteBuffer.wrap(buf));
        return this;
    }

    public BinaryFrame setPayload(String payload) {
        this.setPayload(StringUtil.getUtf8Bytes(payload));
        return this;
    }

    @Override
    public Frame.Type getType() {
        if (this.getOpCode() == 0) {
            return Frame.Type.CONTINUATION;
        }
        return Frame.Type.BINARY;
    }
}

