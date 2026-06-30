/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.configuration;

import eos.moe.ancientdream.yaml.snakeyaml.configuration.ConfigurationOptions;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.MemoryConfiguration;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.file.FileConfiguration;

public class MemoryConfigurationOptions
extends ConfigurationOptions {
    protected MemoryConfigurationOptions(MemoryConfiguration configuration) {
        super(configuration);
    }

    @Override
    public FileConfiguration configuration() {
        return (FileConfiguration)super.configuration();
    }

    @Override
    public MemoryConfigurationOptions copyDefaults(boolean value) {
        super.copyDefaults(value);
        return this;
    }

    @Override
    public MemoryConfigurationOptions pathSeparator(char value) {
        super.pathSeparator(value);
        return this;
    }
}

