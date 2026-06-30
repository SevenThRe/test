/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.events.annotated;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.websocket.common.util.ReflectUtils;

public class CallableMethod {
    private static final Logger LOG = Log.getLogger(CallableMethod.class);
    protected final Class<?> pojo;
    protected final Method method;
    protected Class<?>[] paramTypes;

    public CallableMethod(Class<?> pojo, Method method) {
        Objects.requireNonNull(pojo, "Pojo cannot be null");
        Objects.requireNonNull(method, "Method cannot be null");
        this.pojo = pojo;
        this.method = method;
        this.paramTypes = method.getParameterTypes();
    }

    public Object call(Object obj, Object ... args2) {
        if (this.pojo == null || this.method == null) {
            LOG.warn("Cannot execute call: pojo={}, method={}", this.pojo, this.method);
            return null;
        }
        if (obj == null) {
            String err = String.format("Cannot call %s on null object", this.method);
            LOG.warn(new RuntimeException(err));
            return null;
        }
        if (args2.length < this.paramTypes.length) {
            throw new IllegalArgumentException("Call arguments length [" + args2.length + "] must always be greater than or equal to captured args length [" + this.paramTypes.length + "]");
        }
        try {
            return this.method.invoke(obj, args2);
        }
        catch (Throwable t) {
            String err = this.formatMethodCallError(args2);
            throw this.unwrapRuntimeException(err, t);
        }
    }

    private RuntimeException unwrapRuntimeException(String err, Throwable t) {
        Throwable ret = t;
        while (ret instanceof InvocationTargetException) {
            ret = ((InvocationTargetException)ret).getCause();
        }
        if (ret instanceof RuntimeException) {
            return (RuntimeException)ret;
        }
        return new RuntimeException(err, ret);
    }

    public String formatMethodCallError(Object ... args2) {
        StringBuilder err = new StringBuilder();
        err.append("Cannot call method ");
        err.append(ReflectUtils.toString(this.pojo, this.method));
        err.append(" with args: [");
        boolean delim = false;
        for (Object arg : args2) {
            if (delim) {
                err.append(", ");
            }
            if (arg == null) {
                err.append("<null>");
            } else {
                err.append(arg.getClass().getName());
            }
            delim = true;
        }
        err.append("]");
        return err.toString();
    }

    public Method getMethod() {
        return this.method;
    }

    public Class<?>[] getParamTypes() {
        return this.paramTypes;
    }

    public Class<?> getPojo() {
        return this.pojo;
    }

    public String toString() {
        return String.format("%s[%s]", this.getClass().getSimpleName(), this.method.toGenericString());
    }
}

