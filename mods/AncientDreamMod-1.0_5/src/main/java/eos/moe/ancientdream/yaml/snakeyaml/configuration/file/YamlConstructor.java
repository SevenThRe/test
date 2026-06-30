/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.configuration.file;

import eos.moe.ancientdream.yaml.snakeyaml.configuration.serialization.ConfigurationSerialization;
import eos.moe.ancientdream.yaml.snakeyaml.constructor.SafeConstructor;
import eos.moe.ancientdream.yaml.snakeyaml.error.YAMLException;
import eos.moe.ancientdream.yaml.snakeyaml.nodes.Node;
import eos.moe.ancientdream.yaml.snakeyaml.nodes.Tag;
import java.util.LinkedHashMap;
import java.util.Map;

public class YamlConstructor
extends SafeConstructor {
    public YamlConstructor() {
        this.yamlConstructors.put(Tag.MAP, new ConstructCustomObject());
    }

    private class ConstructCustomObject
    extends SafeConstructor.ConstructYamlMap {
        private ConstructCustomObject() {
            super(YamlConstructor.this);
        }

        @Override
        public Object construct(Node node) {
            if (node.isTwoStepsConstruction()) {
                throw new YAMLException("Unexpected referential mapping structure. Node: " + node);
            }
            Map raw = (Map)super.construct(node);
            if (raw.containsKey("==")) {
                LinkedHashMap typed = new LinkedHashMap(raw.size());
                for (Map.Entry entry : raw.entrySet()) {
                    typed.put(entry.getKey().toString(), entry.getValue());
                }
                try {
                    return ConfigurationSerialization.deserializeObject(typed);
                }
                catch (IllegalArgumentException ex) {
                    throw new YAMLException("Could not deserialize object", ex);
                }
            }
            return raw;
        }

        @Override
        public void construct2ndStep(Node node, Object object) {
            throw new YAMLException("Unexpected referential mapping structure. Node: " + node);
        }
    }
}

