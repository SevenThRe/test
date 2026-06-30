/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.Validate
 */
package org.yaml.snakeyamla.configuration;

import java.util.Map;
import org.apache.commons.lang3.Validate;
import org.yaml.snakeyamla.configuration.Configuration;
import org.yaml.snakeyamla.configuration.ConfigurationSection;
import org.yaml.snakeyamla.configuration.MemoryConfigurationOptions;
import org.yaml.snakeyamla.configuration.MemorySection;

public class MemoryConfiguration
extends MemorySection
implements Configuration {
    protected Configuration defaults;
    protected MemoryConfigurationOptions options;

    public MemoryConfiguration() {
    }

    public MemoryConfiguration(Configuration defaults) {
        this.defaults = defaults;
    }

    @Override
    public void addDefault(String path, Object value) {
        Validate.notNull((Object)path, (String)"Path may not be null", (Object[])new Object[0]);
        if (this.defaults == null) {
            this.defaults = new MemoryConfiguration();
        }
        this.defaults.set(path, value);
    }

    @Override
    public void addDefaults(Map<String, Object> defaults) {
        Validate.notNull(defaults, (String)"Defaults may not be null", (Object[])new Object[0]);
        for (Map.Entry<String, Object> entry : defaults.entrySet()) {
            this.addDefault(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void addDefaults(Configuration defaults) {
        Validate.notNull((Object)defaults, (String)"Defaults may not be null", (Object[])new Object[0]);
        this.addDefaults(defaults.getValues(true));
    }

    @Override
    public void setDefaults(Configuration defaults) {
        Validate.notNull((Object)defaults, (String)"Defaults may not be null", (Object[])new Object[0]);
        this.defaults = defaults;
    }

    @Override
    public Configuration getDefaults() {
        return this.defaults;
    }

    @Override
    public ConfigurationSection getParent() {
        return null;
    }

    @Override
    public MemoryConfigurationOptions options() {
        if (this.options == null) {
            this.options = new MemoryConfigurationOptions(this);
        }
        return this.options;
    }
}

