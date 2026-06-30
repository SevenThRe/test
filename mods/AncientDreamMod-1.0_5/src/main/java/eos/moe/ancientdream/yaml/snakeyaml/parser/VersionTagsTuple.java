/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.parser;

import eos.moe.ancientdream.yaml.snakeyaml.DumperOptions;
import java.util.Map;

class VersionTagsTuple {
    private DumperOptions.Version version;
    private Map<String, String> tags;

    public VersionTagsTuple(DumperOptions.Version version, Map<String, String> tags) {
        this.version = version;
        this.tags = tags;
    }

    public DumperOptions.Version getVersion() {
        return this.version;
    }

    public Map<String, String> getTags() {
        return this.tags;
    }

    public String toString() {
        return String.format("VersionTagsTuple<%s, %s>", new Object[]{this.version, this.tags});
    }
}

