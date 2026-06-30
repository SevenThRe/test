/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common;

import java.net.URI;
import org.eclipse.jetty.websocket.common.LogicalConnection;
import org.eclipse.jetty.websocket.common.WebSocketSession;
import org.eclipse.jetty.websocket.common.events.EventDriver;

public interface SessionFactory {
    public boolean supports(EventDriver var1);

    public WebSocketSession createSession(URI var1, EventDriver var2, LogicalConnection var3);
}

