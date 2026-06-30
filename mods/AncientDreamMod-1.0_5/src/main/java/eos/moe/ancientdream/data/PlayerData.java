/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.ancientdream.data;

import eos.moe.ancientdream.data.PlayerSkillData;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.ConfigurationSection;
import eos.moe.ancientdream.yaml.snakeyaml.configuration.file.YamlConfiguration;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlayerData {
    public CopyOnWriteArrayList<PlayerSkillData> bindSkills = new CopyOnWriteArrayList();

    private void loadBindSkill(ConfigurationSection section1) {
        this.bindSkills.clear();
        for (int i = 0; i < 5; ++i) {
            ConfigurationSection section = section1.getConfigurationSection(String.valueOf(i));
            if (section == null) {
                section = new YamlConfiguration();
            }
            this.bindSkills.add(new PlayerSkillData(section));
        }
    }

    public void updateData(String type, YamlConfiguration section) {
        switch (type.toLowerCase(Locale.ROOT)) {
            case "bindskill": {
                this.loadBindSkill(section);
                return;
            }
        }
    }
}

