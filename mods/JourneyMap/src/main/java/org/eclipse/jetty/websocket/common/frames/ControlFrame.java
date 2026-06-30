/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.frames;

import java.nio.ByteBuffer;
import java.util.Arrays;
import org.eclipse.jetty.util.BufferUtil;
import org.eclipse.jetty.websocket.api.ProtocolException;
import org.eclipse.jetty.websocket.common.WebSocketFrame;

public abstract class ControlFrame
extends WebSocketFrame {
    public static final int MAX_CONTROL_PAYLOAD = 125;

    public ControlFrame(byte opcode) {
        super(opcode);
    }

    @Override
    public void assertValid() {
        if (this.isControlFrame()) {
            if (this.getPayloadLength() > 125) {
                throw new ProtocolException("Desired payload length [" + this.getPayloadLength() + "] exceeds maximum control payload length [" + 125 + "]");
            }
            if ((this.finRsvOp & 0x80) == 0) {
                throw new ProtocolException("Cannot have FIN==false on Control frames");
            }
            if ((this.finRsvOp & 0x40) != 0) {
                throw new ProtocolException("Cannot have RSV1==true on Control frames");
            }
            if ((this.finRsvOp & 0x20) != 0) {
                throw new ProtocolException("Cannot have RSV2==true on Control frames");
            }
            if ((this.finRsvOp & 0x10) != 0) {
                throw new ProtocolException("Cannot have RSV3==true on Control frames");
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        ControlFrame other = (ControlFrame)obj;
        if (this.data == null ? other.data != null : !this.data.equals(other.data)) {
            return false;
        }
        if (this.finRsvOp != other.finRsvOp) {
            return false;
        }
        if (!Arrays.equals(this.mask, other.mask)) {
            return false;
        }
        return this.masked == other.masked;
    }

    @Override
    public boolean isControlFrame() {
        return true;
    }

    @Override
    public boolean isDataFrame() {
        return false;
    }

    @Override
    public WebSocketFrame setPayload(ByteBuffer buf) {
        if (buf != null && buf.remaining() > 125) {
            throw new ProtocolException("Control Payloads can not exceed 125 bytes in length.");
        }
        return super.setPayload(buf);
    }

    @Override
    public ByteBuffer getPayload() {
        if (super.getPayload() == null) {
            return BufferUtil.EMPTY_BUFFER;
        }
        return super.getPayload();
    }
}

