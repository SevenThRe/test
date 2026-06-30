/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.configuration.file;

import eos.moe.ancientdream.yaml.snakeyaml.configuration.MemoryConfiguration;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.MemoryConfigurationOptions;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.file.FileConfiguration;

public class FileConfigurationOptions
extends MemoryConfigurationOptions {
    private String header = null;
    private boolean copyHeader = true;

    protected FileConfigurationOptions(MemoryConfiguration configuration) {
        super(configuration);
    }

    @Override
    public FileConfiguration configuration() {
        return super.configuration();
    }

    @Override
    public FileConfigurationOptions copyDefaults(boolean value) {
        super.copyDefaults(value);
        return this;
    }

    @Override
    public FileConfigurationOptions pathSeparator(char value) {
        super.pathSeparator(value);
        return this;
    }

    public String header() {
        return this.header;
    }

    public FileConfigurationOptions header(String value) {
        this.header = value;
        return this;
    }

    public boolean copyHeader() {
        return this.copyHeader;
    }

    public FileConfigurationOptions copyHeader(boolean value) {
        this.copyHeader = value;
        return this;
    }
}

