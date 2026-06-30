/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common;

import org.eclipse.jetty.websocket.api.BatchMode;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.extensions.OutgoingFrames;
import org.eclipse.jetty.websocket.common.LogicalConnection;

public interface RemoteEndpointFactory {
    public RemoteEndpoint newRemoteEndpoint(LogicalConnection var1, OutgoingFrames var2, BatchMode var3);
}

