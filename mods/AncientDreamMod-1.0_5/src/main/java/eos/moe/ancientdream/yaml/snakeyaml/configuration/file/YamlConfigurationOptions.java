/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.Validate
 */
package eos.moe.ancientdream.yaml.snakeyaml.configuration.file;

import eos.moe.ancientdream.yaml.snakeyaml.configuration.file.FileConfigurationOptions;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.file.YamlConfiguration;
import org.apache.commons.lang3.Validate;

public class YamlConfigurationOptions
extends FileConfigurationOptions {
    private int indent = 2;

    protected YamlConfigurationOptions(YamlConfiguration configuration) {
        super(configuration);
    }

    @Override
    public YamlConfiguration configuration() {
        return (YamlConfiguration)super.configuration();
    }

    @Override
    public YamlConfigurationOptions copyDefaults(boolean value) {
        super.copyDefaults(value);
        return this;
    }

    @Override
    public YamlConfigurationOptions pathSeparator(char value) {
        super.pathSeparator(value);
        return this;
    }

    @Override
    public YamlConfigurationOptions header(String value) {
        super.header(value);
        return this;
    }

    @Override
    public YamlConfigurationOptions copyHeader(boolean value) {
        super.copyHeader(value);
        return this;
    }

    public int indent() {
        return this.indent;
    }

    public YamlConfigurationOptions indent(int value) {
        Validate.isTrue((value >= 2 ? 1 : 0) != 0, (String)"Indent must be at least 2 characters", (Object[])new Object[0]);
        Validate.isTrue((value <= 9 ? 1 : 0) != 0, (String)"Indent cannot be greater than 9 characters", (Object[])new Object[0]);
        this.indent = value;
        return this;
    }
}

