/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.io.payload;

import java.nio.ByteBuffer;
import org.eclipse.jetty.websocket.api.extensions.Frame;
import org.eclipse.jetty.websocket.common.io.payload.PayloadProcessor;

public class DeMaskProcessor
implements PayloadProcessor {
    private byte[] maskBytes;
    private int maskInt;
    private int maskOffset;

    @Override
    public void process(ByteBuffer payload) {
        int remaining;
        if (this.maskBytes == null) {
            return;
        }
        int maskInt = this.maskInt;
        int start = payload.position();
        int end = payload.limit();
        int offset = this.maskOffset;
        while ((remaining = end - start) > 0) {
            if (remaining >= 4 && (offset & 3) == 0) {
                payload.putInt(start, payload.getInt(start) ^ maskInt);
                start += 4;
                offset += 4;
                continue;
            }
            payload.put(start, (byte)(payload.get(start) ^ this.maskBytes[offset & 3]));
            ++start;
            ++offset;
        }
        this.maskOffset = offset;
    }

    public void reset(byte[] mask) {
        this.maskBytes = mask;
        int maskInt = 0;
        if (mask != null) {
            for (byte maskByte : mask) {
                maskInt = (maskInt << 8) + (maskByte & 0xFF);
            }
        }
        this.maskInt = maskInt;
        this.maskOffset = 0;
    }

    @Override
    public void reset(Frame frame) {
        this.reset(frame.getMask());
    }
}

