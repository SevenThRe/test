/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.EntityLivingBase
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ai;
import eos.moe.dragoncore.bg;
import eos.moe.dragoncore.bt;
import eos.moe.dragoncore.ck;
import eos.moe.dragoncore.cw;
import eos.moe.dragoncore.cz;
import eos.moe.dragoncore.fk;
import eos.moe.dragoncore.fs;
import eos.moe.dragoncore.fz;
import eos.moe.dragoncore.gl;
import eos.moe.dragoncore.hw;
import eos.moe.dragoncore.jk;
import eos.moe.dragoncore.jy;
import eos.moe.dragoncore.kk;
import eos.moe.dragoncore.lr;
import eos.moe.dragoncore.mj;
import eos.moe.dragoncore.ne;
import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.nj;
import eos.moe.dragoncore.nz;
import eos.moe.dragoncore.of;
import eos.moe.dragoncore.om;
import eos.moe.dragoncore.pg;
import eos.moe.dragoncore.ql;
import eos.moe.dragoncore.qv;
import eos.moe.dragoncore.sp;
import eos.moe.dragoncore.sy;
import eos.moe.dragoncore.tm;
import eos.moe.dragoncore.wv;
import eos.moe.dragoncore.xs;
import eos.moe.dragoncore.yl;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import org.yaml.snakeyamla.configuration.ConfigurationSection;
import org.yaml.snakeyamla.configuration.file.YamlConfiguration;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class kp {
    private final qv g;
    public boolean t;
    public boolean r;
    public String x;
    public List<Future> v;
    private Map<String, cz> m;
    public Map<String, nh> c;
    public Map<String, String> q;
    public nh b;
    private YamlConfiguration o;
    public EntityLivingBase y;
    private static final Pattern k = Pattern.compile("[']([^']+)[']");
    private static final Pattern ALLATORIxDEMO = Pattern.compile("[%]([^%]+)[%]");

    public qv ALLATORIxDEMO() {
        kp a2;
        return a2.g;
    }

    public kp(EntityLivingBase a2, YamlConfiguration a3) {
        kp a4;
        a4.o = a3;
        a4.y = a2;
        a4.x = a3.getString("display", "aim").toLowerCase(Locale.ROOT);
        a4.r = a3.getBoolean("throuth");
        a4.v = new CopyOnWriteArrayList<Future>();
        a4.c = new HashMap<String, nh>();
        a4.q = new HashMap<String, String>();
        a4.m = new jk<cz>();
        a4.g = hw.ALLATORIxDEMO(a4);
        a4.g.ALLATORIxDEMO("\u65b9\u6cd5", "func", a4.ALLATORIxDEMO());
        a4.b = a4.ALLATORIxDEMO(a3.get("offsetY", "\u65b9\u6cd5.\u53d6\u5b9e\u4f53\u9ad8\u5ea6+0.25"));
        for (String a5 : a4.o.getKeys(false)) {
            ConfigurationSection configurationSection = a3.getConfigurationSection(a5);
            cz a7 = a4.ALLATORIxDEMO(configurationSection);
            if (a7 == null) continue;
            a4.m.put(configurationSection.getName(), a7);
        }
        ConfigurationSection a8 = a4.o.getConfigurationSection("Functions");
        if (a8 != null) {
            for (String string : a8.getKeys(false)) {
                a4.c.put(string.toLowerCase(Locale.ROOT), a4.ALLATORIxDEMO(a8.get(string)));
            }
        }
    }

    public cz ALLATORIxDEMO(ConfigurationSection a2) {
        if (a2 == null || !a2.contains("type")) {
            return null;
        }
        String a3 = a2.getString("type");
        if (a3 == null) {
            return null;
        }
        switch (a3.toLowerCase(Locale.ROOT)) {
            case "l": 
            case "label": 
            case "\u6587\u672c": {
                kp a4;
                return new fz(a4, a2);
            }
            case "t": 
            case "texture": 
            case "\u56fe\u7247": {
                kp a4;
                return new cw(a4, a2);
            }
        }
        return null;
    }

    public lr ALLATORIxDEMO() {
        kp a2;
        lr a3 = new lr(new HashMap<String, Function<bt, Object>>());
        a3.ALLATORIxDEMO().putAll(sy.ALLATORIxDEMO.ALLATORIxDEMO());
        wv.ALLATORIxDEMO(sp.class, a2, a3);
        wv.ALLATORIxDEMO(fk.class, a2, a3);
        wv.ALLATORIxDEMO(xs.class, a2, a3);
        wv.ALLATORIxDEMO(kk.class, a2, a3);
        wv.ALLATORIxDEMO(tm.class, a2, a3);
        wv.ALLATORIxDEMO(bg.class, a2, a3);
        wv.ALLATORIxDEMO(pg.class, a2, a3);
        wv.ALLATORIxDEMO(om.class, a2, a3);
        wv.ALLATORIxDEMO(gl.class, a2, a3);
        wv.ALLATORIxDEMO(mj.class, a2, a3);
        wv.ALLATORIxDEMO(ck.class, a2, a3);
        wv.ALLATORIxDEMO(of.class, a2, a3);
        wv.ALLATORIxDEMO(ne.class, a2, a3);
        wv.ALLATORIxDEMO(ql.class, a2, a3);
        wv.ALLATORIxDEMO(yl.class, a2, a3);
        wv.ALLATORIxDEMO(fs.class, a2, a3);
        wv.ALLATORIxDEMO(ai.class, a2, a3);
        wv.ALLATORIxDEMO(jy.class, a2, a3);
        wv.ALLATORIxDEMO(nz.class, a2, a3);
        return a3;
    }

    public void ALLATORIxDEMO(EntityLivingBase a2, float a3, boolean a4) {
        kp a5;
        for (cz a6 : a5.m.values()) {
            a6.c(a2, a3, a4);
            GlStateManager.translate((double)0.0, (double)0.0, (double)-0.01);
        }
    }

    public boolean ALLATORIxDEMO(nj a2) {
        kp a3;
        return a3.c(a2.ALLATORIxDEMO());
    }

    public boolean c(String a2) {
        kp a3;
        nh a4 = a3.c.get(a2);
        if (a4 != null) {
            return a4.c();
        }
        return false;
    }

    public Map<String, cz> ALLATORIxDEMO() {
        kp a2;
        return a2.m;
    }

    public nh ALLATORIxDEMO(Object a2) {
        kp a3;
        String a4 = String.valueOf(a2);
        if (a4.startsWith("co|") || a4.startsWith("mo|")) {
            a4 = a4.substring(3);
        }
        a4 = a3.ALLATORIxDEMO(a4);
        return hw.ALLATORIxDEMO(a3.g, a4);
    }

    public nh ALLATORIxDEMO(String a2) {
        kp a3;
        if (!a2.startsWith("co|") && !a2.startsWith("mo|")) {
            if (a3.ALLATORIxDEMO(a2)) {
                a2 = a3.ALLATORIxDEMO(a2);
                return hw.ALLATORIxDEMO(a3.g, a2);
            }
            if (!a2.startsWith("'")) {
                a2 = "'" + a2 + "'";
            }
        } else {
            a2 = a2.substring(3);
        }
        a2 = a3.ALLATORIxDEMO(a2);
        return hw.ALLATORIxDEMO(a3.g, a2);
    }

    private /* synthetic */ String ALLATORIxDEMO(String a2) {
        Matcher a3 = k.matcher(a2);
        while (a3.find()) {
            String a4 = a3.group();
            Matcher a5 = ALLATORIxDEMO.matcher(a4);
            boolean a6 = false;
            while (a5.find() && a5.group().contains("_")) {
                a6 = true;
                String a7 = a5.group();
                a4 = a4.replace(a7, "',\u65b9\u6cd5.\u53d6\u53d8\u91cf('" + a5.group(1) + "'),'");
            }
            if (!a6) continue;
            a4 = "\u65b9\u6cd5.\u5408\u5e76\u6587\u672c(" + a4 + ")";
            a2 = a2.replace(a3.group(), a4);
        }
        return a2;
    }

    private /* synthetic */ boolean ALLATORIxDEMO(String a2) {
        kp a3;
        for (String a4 : a3.g.ALLATORIxDEMO().ALLATORIxDEMO().keySet()) {
            if (!a2.contains(a4 + ".")) continue;
            return true;
        }
        return false;
    }

    public YamlConfiguration ALLATORIxDEMO() {
        kp a2;
        return a2.o;
    }
}

