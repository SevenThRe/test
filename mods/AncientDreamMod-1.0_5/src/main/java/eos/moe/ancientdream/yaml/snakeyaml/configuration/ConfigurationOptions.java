/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.configuration;

import eos.moe.ancientdream.yaml.snakeyaml.configuration.Configuration;

public class ConfigurationOptions {
    private char pathSeparator = (char)46;
    private boolean copyDefaults = false;
    private final Configuration configuration;

    protected ConfigurationOptions(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration configuration() {
        return this.configuration;
    }

    public char pathSeparator() {
        return this.pathSeparator;
    }

    public ConfigurationOptions pathSeparator(char value) {
        this.pathSeparator = value;
        return this;
    }

    public boolean copyDefaults() {
        return this.copyDefaults;
    }

    public ConfigurationOptions copyDefaults(boolean value) {
        this.copyDefaults = value;
        return this;
    }
}

