/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.jj;
import eos.moe.dragoncore.mh;
import eos.moe.dragoncore.ui;
import org.yaml.snakeyamla.configuration.ConfigurationSection;
import org.yaml.snakeyamla.configuration.file.YamlConfiguration;

public class pk
extends jj {
    public mh k;
    public String ALLATORIxDEMO;

    public pk(ui a2, ConfigurationSection a3) {
        super(a2, a3);
        pk a4;
        a4.k = a4.createMoLangParser("data", 1);
        Object a5 = a4.readYml("src", "");
        if (a5 instanceof ConfigurationSection) {
            YamlConfiguration a6 = new YamlConfiguration();
            ConfigurationSection a7 = (ConfigurationSection)a5;
            for (String a8 : ((ConfigurationSection)a5).getKeys(false)) {
                a6.set(a8, a7.get(a8));
            }
            a4.ALLATORIxDEMO = a6.saveToString();
        } else {
            a4.ALLATORIxDEMO = a5.toString();
        }
    }

    @Override
    public void render(int a2, int a3) {
    }

    public void createComponent() {
    }
}

