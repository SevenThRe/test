/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.io.payload;

import java.nio.ByteBuffer;
import org.eclipse.jetty.websocket.api.extensions.Frame;

public interface PayloadProcessor {
    public void process(ByteBuffer var1);

    public void reset(Frame var1);
}

