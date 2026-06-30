/*
 * Decompiled with CFR 0.152.
 */
package org.yaml.snakeyamla.configuration.serialization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.yaml.snakeyamla.configuration.serialization.ConfigurationSerializable;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE})
public @interface DelegateDeserialization {
    public Class<? extends ConfigurationSerializable> value();
}

