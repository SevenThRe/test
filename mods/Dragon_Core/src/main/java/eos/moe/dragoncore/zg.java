/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.jj;
import eos.moe.dragoncore.mh;
import eos.moe.dragoncore.od;
import eos.moe.dragoncore.ui;
import eos.moe.dragoncore.wi;
import java.io.StringReader;
import net.minecraft.client.Minecraft;
import org.yaml.snakeyamla.configuration.ConfigurationSection;
import org.yaml.snakeyamla.configuration.file.YamlConfiguration;

public class zg
extends jj {
    public mh y;
    public ui k;
    private int ALLATORIxDEMO = -1;

    public zg(ui a2, ConfigurationSection a3) {
        super(a2, a3);
        zg a4;
        a4.y = a4.createMoLangParserString("src", "");
        a4.p = a4.toMolangParser("width", 0);
        a4.u = a4.toMolangParser("height", 0);
        a4.openGuiOnMainThread();
    }

    @Override
    public void draw(int a2, int a3, boolean a4) {
        zg a5;
        a5.runAction("preRender");
        if (a5.checkUpdate()) {
            a5.openGuiOnMainThread();
        }
        if (a5.k != null && !a5.k.w && a5.isVisible()) {
            a5.k.func_73863_a(a2, a3, 0.0f);
        }
        a5.runAction("postRender");
    }

    @Override
    public void render(int a2, int a3) {
    }

    public boolean checkUpdate() {
        zg a2;
        String a3 = a2.y.c();
        YamlConfiguration a4 = wi.b.ALLATORIxDEMO(a3);
        int a5 = a4 != null ? a4.getLoadFromString().hashCode() : a3.hashCode();
        return a5 != a2.ALLATORIxDEMO;
    }

    public boolean isVisible() {
        zg a2;
        return a2.ta.c();
    }

    public boolean isEnable() {
        zg a2;
        return a2.xa.c();
    }

    public void openGuiOnMainThread() {
        zg a2;
        Minecraft.func_71410_x().func_152344_a(a2::openGui);
    }

    public void openGui() {
        zg a2;
        String a3 = a2.y.c();
        YamlConfiguration a4 = wi.b.ALLATORIxDEMO(a3);
        if (a4 != null) {
            a2.ALLATORIxDEMO = a4.getLoadFromString().hashCode();
        } else if (a3.contains("\n")) {
            a4 = YamlConfiguration.loadConfiguration(new StringReader(a3));
            a4.setFileName(a2.k.getName());
            a2.ALLATORIxDEMO = a3.hashCode();
        }
        if (a2.k != null && !a2.k.w) {
            a2.k.onGuiClosed1();
        }
        if (a4 != null) {
            a2.k = new ui(a3, a4, wi.b.ALLATORIxDEMO(), Minecraft.func_71410_x().field_71439_g.field_71069_bz, od.q);
            a2.k.initGui_();
            a2.k.open();
            a2.runAction("loaded");
        }
    }

    @Override
    public void onClose() {
        zg a2;
        if (a2.k != null && !a2.k.w) {
            a2.k.onGuiClosed1();
        }
    }

    @Override
    public void onRemove() {
        zg a2;
        a2.onClose();
    }

    @Override
    public Object getValue(String a2) {
        zg a3;
        if ("loaded".equals(a2)) {
            return a3.k != null && !a3.k.w ? "1" : "0";
        }
        return super.getValue(a2);
    }

    @Override
    public void setValue(String a2, Object a3) {
        zg a4;
        if ("src".equals(a2)) {
            a4.y.ALLATORIxDEMO(a4.toMolangParser((String)a3));
            return;
        }
        super.setValue(a2, a3);
    }
}

