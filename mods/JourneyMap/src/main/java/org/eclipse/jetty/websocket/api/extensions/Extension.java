/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.api.extensions;

import org.eclipse.jetty.websocket.api.extensions.ExtensionConfig;
import org.eclipse.jetty.websocket.api.extensions.IncomingFrames;
import org.eclipse.jetty.websocket.api.extensions.OutgoingFrames;

public interface Extension
extends IncomingFrames,
OutgoingFrames {
    public ExtensionConfig getConfig();

    public String getName();

    public boolean isRsv1User();

    public boolean isRsv2User();

    public boolean isRsv3User();

    public void setNextIncomingFrames(IncomingFrames var1);

    public void setNextOutgoingFrames(OutgoingFrames var1);
}

