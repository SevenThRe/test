/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.api;

public interface WriteCallback {
    public void writeFailed(Throwable var1);

    public void writeSuccess();
}

