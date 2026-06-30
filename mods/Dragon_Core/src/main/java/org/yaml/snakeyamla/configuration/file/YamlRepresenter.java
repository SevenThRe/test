/*
 * Decompiled with CFR 0.152.
 */
package org.yaml.snakeyamla.configuration.file;

import java.util.LinkedHashMap;
import org.yaml.snakeyamla.configuration.ConfigurationSection;
import org.yaml.snakeyamla.configuration.serialization.ConfigurationSerializable;
import org.yaml.snakeyamla.configuration.serialization.ConfigurationSerialization;
import org.yaml.snakeyamla.nodes.Node;
import org.yaml.snakeyamla.representer.Representer;
import org.yaml.snakeyamla.representer.SafeRepresenter;

public class YamlRepresenter
extends Representer {
    public YamlRepresenter() {
        this.multiRepresenters.put(ConfigurationSection.class, new RepresentConfigurationSection());
        this.multiRepresenters.put(ConfigurationSerializable.class, new RepresentConfigurationSerializable());
    }

    private class RepresentConfigurationSerializable
    extends SafeRepresenter.RepresentMap {
        private RepresentConfigurationSerializable() {
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
        }

        @Override
        public Node representData(Object data) {
            return super.representData(((ConfigurationSection)data).getValues(false));
        }
    }
}

