/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.api.extensions;

import org.eclipse.jetty.websocket.api.BatchMode;
import org.eclipse.jetty.websocket.api.WriteCallback;
import org.eclipse.jetty.websocket.api.extensions.Frame;

public interface OutgoingFrames {
    public void outgoingFrame(Frame var1, WriteCallback var2, BatchMode var3);
}

