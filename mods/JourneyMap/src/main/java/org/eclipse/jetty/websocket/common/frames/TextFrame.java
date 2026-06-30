/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.frames;

import java.nio.ByteBuffer;
import org.eclipse.jetty.util.BufferUtil;
import org.eclipse.jetty.util.StringUtil;
import org.eclipse.jetty.websocket.api.extensions.Frame;
import org.eclipse.jetty.websocket.common.frames.DataFrame;

public class TextFrame
extends DataFrame {
    public TextFrame() {
        super((byte)1);
    }

    @Override
    public Frame.Type getType() {
        if (this.getOpCode() == 0) {
            return Frame.Type.CONTINUATION;
        }
        return Frame.Type.TEXT;
    }

    public TextFrame setPayload(String str) {
        this.setPayload(ByteBuffer.wrap(StringUtil.getUtf8Bytes(str)));
        return this;
    }

    @Override
    public String getPayloadAsUTF8() {
        if (this.data == null) {
            return null;
        }
        return BufferUtil.toUTF8String(this.data);
    }
}

