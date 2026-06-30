/*
 * Decompiled with CFR 0.152.
 */
package org.yaml.snakeyamla.configuration;

import org.yaml.snakeyamla.configuration.ConfigurationOptions;
import org.yaml.snakeyamla.configuration.MemoryConfiguration;
import org.yaml.snakeyamla.configuration.file.FileConfiguration;

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

