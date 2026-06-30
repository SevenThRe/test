/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.api.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.eclipse.jetty.websocket.api.BatchMode;

@Documented
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE})
public @interface WebSocket {
    public int inputBufferSize() default -2;

    public int maxBinaryMessageSize() default -2;

    public int maxIdleTime() default -2;

    public int maxTextMessageSize() default -2;

    public BatchMode batchMode() default BatchMode.AUTO;
}

