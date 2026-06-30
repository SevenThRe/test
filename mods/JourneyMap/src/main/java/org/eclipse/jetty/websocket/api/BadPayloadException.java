/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.api;

import org.eclipse.jetty.websocket.api.CloseException;

public class BadPayloadException
extends CloseException {
    public BadPayloadException(String message) {
        super(1007, message);
    }

    public BadPayloadException(String message, Throwable t) {
        super(1007, message, t);
    }

    public BadPayloadException(Throwable t) {
        super(1007, t);
    }
}

