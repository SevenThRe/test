/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.configuration;

import eos.moe.ancientdream.yaml.snakeyaml.configuration.ConfigurationOptions;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.ConfigurationSection;
import java.util.Map;

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

