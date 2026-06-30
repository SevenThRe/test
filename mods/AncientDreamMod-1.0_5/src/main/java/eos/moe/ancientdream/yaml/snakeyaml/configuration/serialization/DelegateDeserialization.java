/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.configuration.serialization;

import eos.moe.ancientdream.yaml.snakeyaml.configuration.serialization.ConfigurationSerializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE})
public @interface DelegateDeserialization {
    public Class<? extends ConfigurationSerializable> value();
}

