/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.events;

import org.eclipse.jetty.websocket.api.WebSocketPolicy;
import org.eclipse.jetty.websocket.common.events.EventDriver;

public interface EventDriverImpl {
    public EventDriver create(Object var1, WebSocketPolicy var2) throws Throwable;

    public String describeRule();

    public boolean supports(Object var1);
}

