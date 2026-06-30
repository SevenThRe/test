/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.Gui
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.md;
import eos.moe.dragoncore.mh;
import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.p;
import eos.moe.dragoncore.se;
import eos.moe.dragoncore.ui;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import net.minecraft.client.gui.Gui;
import org.yaml.snakeyamla.configuration.ConfigurationSection;
import org.yaml.snakeyamla.configuration.file.YamlConfiguration;

public abstract class rd
extends Gui
implements p {
    public ui y;
    public ConfigurationSection k;
    public ConfigurationSection ALLATORIxDEMO;

    public rd(ui a3, ConfigurationSection a4) {
        rd a5;
        a5.y = a3;
        a5.k = a4;
        a5.ALLATORIxDEMO = new YamlConfiguration();
        Optional.ofNullable(a4.getString("extends")).ifPresent(a2 -> {
            rd a3;
            if (a3.y.getYaml().isConfigurationSection((String)a2)) {
                a3.ALLATORIxDEMO = a3.y.getYaml().getConfigurationSection((String)a2);
            }
        });
    }

    @Override
    public boolean isClosed() {
        rd a2;
        return a2.y.w;
    }

    @Override
    public ui getManager() {
        rd a2;
        return a2.y;
    }

    @Override
    public ConfigurationSection getSection() {
        rd a2;
        return a2.k;
    }

    public mh createMoLangParserString(String a2, String a3) {
        rd a4;
        return a4.toMolangParser(a2, a4.readYmlAsString(a2, a3));
    }

    public mh createMoLangParser(String a2, Object a3) {
        rd a4;
        return a4.toMolangParser(a2, a4.readYml(a2, a3));
    }

    public mh toMolangParser(String a2, Object a3) {
        rd a4;
        return new se(a2, a4.toMolangParser(a3), a4);
    }

    public mh toMolangParser(String a2, String a3) {
        rd a4;
        return new se(a2, a4.toMolangParser(a3), a4);
    }

    public md createMoLangParserList(String a2) {
        rd a4;
        ConfigurationSection a5 = a4.k.contains(a2) ? a4.k : a4.ALLATORIxDEMO;
        ArrayList<String> a6 = new ArrayList<String>();
        if (a5.isList(a2)) {
            a6.addAll(a5.getStringList(a2));
        } else if (a5.isString(a2)) {
            a6.add(a5.getString(a2));
        }
        return new md(a6.stream().map(a3 -> {
            rd a4;
            return a4.toMolangParser(a2, (String)a3);
        }).collect(Collectors.toList()));
    }

    public nh toMolangParser(Object a2) {
        rd a3;
        return a3.y.parseExpression(a2);
    }

    public nh toMolangParser(String a2) {
        rd a3;
        return a3.y.parseExpression(a2);
    }

    public Object readYml(String a2, Object a3) {
        rd a4;
        return a4.k.get(a2, a4.ALLATORIxDEMO.get(a2, a3));
    }

    public String readYmlAsString(String a2, String a3) {
        rd a4;
        return a4.k.getString(a2, a4.ALLATORIxDEMO.getString(a2, a3));
    }
}

