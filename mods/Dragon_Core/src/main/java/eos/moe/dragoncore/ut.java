/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.hp;
import eos.moe.dragoncore.sr;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.yaml.snakeyamla.configuration.ConfigurationSection;
import org.yaml.snakeyamla.configuration.file.YamlConfiguration;

public class ut {
    private List<hp> y;
    private Map<String, String> k;
    private sr ALLATORIxDEMO;

    public ut(List<hp> a2, Map<String, String> a3, sr a4) {
        ut a5;
        a5.y = a2;
        a5.k = a3;
        a5.ALLATORIxDEMO = a4;
    }

    public List<hp> ALLATORIxDEMO() {
        ut a2;
        return a2.y;
    }

    public Map<String, String> ALLATORIxDEMO() {
        ut a2;
        return a2.k;
    }

    public sr ALLATORIxDEMO() {
        ut a2;
        return a2.ALLATORIxDEMO;
    }

    public static ut ALLATORIxDEMO(YamlConfiguration a2) {
        ArrayList<hp> a4 = new ArrayList<hp>();
        ConfigurationSection a5 = a2.getConfigurationSection("Layer");
        if (a5 != null) {
            for (String string : a5.getKeys(false)) {
                ConfigurationSection a6 = a5.getConfigurationSection(string);
                if (a6 == null) continue;
                a4.add(new hp(a6));
            }
        }
        LinkedHashMap<String, String> a7 = new LinkedHashMap<String, String>();
        ConfigurationSection configurationSection = a2.getConfigurationSection("Trigger");
        if (configurationSection != null) {
            for (String a8 : configurationSection.getKeys(false)) {
                String a9 = configurationSection.getString(a8);
                if (a9 == null) continue;
                a7.put(a8, a9);
            }
        }
        return new ut(a4, a7, null);
    }
}

