/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.events.annotated;

import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Method;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.common.events.annotated.CallableMethod;

public class OptionalSessionCallableMethod
extends CallableMethod {
    private final boolean wantsSession;
    private final boolean streaming;

    public OptionalSessionCallableMethod(Class<?> pojo, Method method) {
        super(pojo, method);
        boolean foundConnection = false;
        boolean foundStreaming = false;
        if (this.paramTypes != null) {
            for (Class paramType : this.paramTypes) {
                if (Session.class.isAssignableFrom(paramType)) {
                    foundConnection = true;
                }
                if (!Reader.class.isAssignableFrom(paramType) && !InputStream.class.isAssignableFrom(paramType)) continue;
                foundStreaming = true;
            }
        }
        this.wantsSession = foundConnection;
        this.streaming = foundStreaming;
    }

    public void call(Object obj, Session connection, Object ... args2) {
        if (this.wantsSession) {
            Object[] fullArgs = new Object[args2.length + 1];
            fullArgs[0] = connection;
            System.arraycopy(args2, 0, fullArgs, 1, args2.length);
            this.call(obj, fullArgs);
        } else {
            this.call(obj, args2);
        }
    }

    public boolean isSessionAware() {
        return this.wantsSession;
    }

    public boolean isStreaming() {
        return this.streaming;
    }

    @Override
    public String toString() {
        return String.format("%s[%s]", this.getClass().getSimpleName(), this.method.toGenericString());
    }
}

