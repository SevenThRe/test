/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.api.extensions;

import org.eclipse.jetty.websocket.api.extensions.Frame;

public interface IncomingFrames {
    public void incomingError(Throwable var1);

    public void incomingFrame(Frame var1);
}

