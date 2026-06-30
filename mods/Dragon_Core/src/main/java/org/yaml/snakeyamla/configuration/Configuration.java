/*
 * Decompiled with CFR 0.152.
 */
package org.yaml.snakeyamla.configuration;

import java.util.Map;
import org.yaml.snakeyamla.configuration.ConfigurationOptions;
import org.yaml.snakeyamla.configuration.ConfigurationSection;

public interface Configuration
extends ConfigurationSection {
    @Override
    public void addDefault(String var1, Object var2);

    public void addDefaults(Map<String, Object> var1);

    public void addDefaults(Configuration var1);

    public void setDefaults(Configuration var1);

    public Configuration getDefaults();

    public ConfigurationOptions options();
}

