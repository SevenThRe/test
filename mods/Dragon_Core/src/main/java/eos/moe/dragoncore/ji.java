/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.i;
import eos.moe.dragoncore.qg;
import eos.moe.dragoncore.ui;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xf;
import java.util.ArrayList;
import org.yaml.snakeyamla.configuration.file.YamlConfiguration;

public class ji {
    public ji() {
        ji a2;
    }

    @i(f={"\u53d6Yaml\u503c", "Yaml_Get"})
    public static v f(ui a2, String a3) {
        YamlConfiguration a4 = a2.getYaml();
        if (a4.isList(a3)) {
            return new qg(a4.getList(a3), false);
        }
        return xf.ALLATORIxDEMO(a4.getString(a3, ""));
    }

    @i(f={"\u53d6Yaml\u8282\u70b9", "Yaml_Get_Keys"})
    public static v c(ui a2, String a3) {
        YamlConfiguration a4 = a2.getYaml();
        if (a3.isEmpty()) {
            return new qg(a4.getKeys(false), false);
        }
        if (a4.isConfigurationSection(a3)) {
            return new qg(a4.getConfigurationSection(a3).getKeys(false), false);
        }
        return new qg(new ArrayList<v>());
    }

    @i(f={"\u53d6Yaml\u5168\u90e8\u8282\u70b9", "Yaml_Get_All_Keys"})
    public static v ALLATORIxDEMO(ui a2, String a3) {
        YamlConfiguration a4 = a2.getYaml();
        if (a3.isEmpty()) {
            return new qg(a4.getKeys(true), false);
        }
        if (a4.isConfigurationSection(a3)) {
            return new qg(a4.getConfigurationSection(a3).getKeys(true), false);
        }
        return new qg(new ArrayList<v>());
    }
}

