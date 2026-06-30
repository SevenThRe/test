/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.frames;

import org.eclipse.jetty.websocket.api.extensions.Frame;
import org.eclipse.jetty.websocket.common.WebSocketFrame;

public class DataFrame
extends WebSocketFrame {
    protected DataFrame(byte opcode) {
        super(opcode);
    }

    public DataFrame(Frame basedOn) {
        this(basedOn, false);
    }

    public DataFrame(Frame basedOn, boolean continuation) {
        super(basedOn.getOpCode());
        this.copyHeaders(basedOn);
        if (continuation) {
            this.setOpCode((byte)0);
        }
    }

    @Override
    public void assertValid() {
    }

    @Override
    public boolean isControlFrame() {
        return false;
    }

    @Override
    public boolean isDataFrame() {
        return true;
    }

    public void setIsContinuation() {
        this.setOpCode((byte)0);
    }
}

