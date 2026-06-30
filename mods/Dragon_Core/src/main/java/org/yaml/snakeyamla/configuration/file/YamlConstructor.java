/*
 * Decompiled with CFR 0.152.
 */
package org.yaml.snakeyamla.configuration.file;

import java.util.LinkedHashMap;
import java.util.Map;
import org.yaml.snakeyamla.configuration.serialization.ConfigurationSerialization;
import org.yaml.snakeyamla.constructor.SafeConstructor;
import org.yaml.snakeyamla.error.YAMLException;
import org.yaml.snakeyamla.nodes.Node;
import org.yaml.snakeyamla.nodes.Tag;

public class YamlConstructor
extends SafeConstructor {
    public YamlConstructor() {
        this.yamlConstructors.put(Tag.MAP, new ConstructCustomObject());
    }

    private class ConstructCustomObject
    extends SafeConstructor.ConstructYamlMap {
        private ConstructCustomObject() {
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
                catch (IllegalArgumentException ex2) {
                    throw new YAMLException("Could not deserialize object", ex2);
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

