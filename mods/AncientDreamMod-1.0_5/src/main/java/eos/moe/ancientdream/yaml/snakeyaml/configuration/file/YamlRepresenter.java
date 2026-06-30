/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.yaml.snakeyaml.configuration.file;

import eos.moe.ancientdream.yaml.snakeyaml.configuration.ConfigurationSection;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.serialization.ConfigurationSerializable;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.serialization.ConfigurationSerialization;
import eos.moe.ancientdream.yaml.snakeyaml.nodes.Node;
import eos.moe.ancientdream.yaml.snakeyaml.representer.Representer;
import eos.moe.ancientdream.yaml.snakeyaml.representer.SafeRepresenter;
import java.util.LinkedHashMap;

public class YamlRepresenter
extends Representer {
    public YamlRepresenter() {
        this.multiRepresenters.put(ConfigurationSection.class, new RepresentConfigurationSection());
        this.multiRepresenters.put(ConfigurationSerializable.class, new RepresentConfigurationSerializable());
    }

    private class RepresentConfigurationSerializable
    extends SafeRepresenter.RepresentMap {
        private RepresentConfigurationSerializable() {
            super(YamlRepresenter.this);
        }

        @Override
        public Node representData(Object data) {
            ConfigurationSerializable serializable = (ConfigurationSerializable)data;
            LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();
            values.put("==", ConfigurationSerialization.getAlias(serializable.getClass()));
            values.putAll(serializable.serialize());
            return super.representData(values);
        }
    }

    private class RepresentConfigurationSection
    extends SafeRepresenter.RepresentMap {
        private RepresentConfigurationSection() {
            super(YamlRepresenter.this);
        }

        @Override
        public Node representData(Object data) {
            return super.representData(((ConfigurationSection)data).getValues(false));
        }
    }
}

