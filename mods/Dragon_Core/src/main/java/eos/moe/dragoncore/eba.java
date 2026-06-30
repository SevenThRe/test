/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.hca;
import eos.moe.dragoncore.ida;
import eos.moe.dragoncore.mha;
import eos.moe.dragoncore.tfa;
import eos.moe.dragoncore.uha;
import eos.moe.dragoncore.vda;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.lwjgl.input.Keyboard;
import org.yaml.snakeyamla.configuration.ConfigurationSection;
import org.yaml.snakeyamla.configuration.file.YamlConfiguration;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class eba {
    public int r;
    public String x;
    public int v;
    public String m;
    public String c;
    public String q;
    public List<uha> b;
    public List<String> o;
    public List<String> y;
    public Pattern k;
    private tfa ALLATORIxDEMO;

    public eba(YamlConfiguration a2) {
        eba a3;
        a3.r = a2.getInt("setting.shifttype");
        a3.x = a2.getString("setting.match", "unknow");
        a3.v = a2.getInt("setting.priority");
        a3.m = String.valueOf(a2.get("setting.width", "0"));
        a3.c = String.valueOf(a2.get("setting.move", "0"));
        a3.q = String.valueOf(a2.get("setting.height", "0"));
        if (a3.x.equals("*")) {
            a3.x = ".*";
        }
        a3.k = Pattern.compile(a3.x);
        a3.o = a2.getStringList("Lines");
        a3.y = a2.getStringList("Lores");
        a3.b = new ArrayList<uha>();
        for (ConfigurationSection a4 : a2.getSections()) {
            String a5 = a4.getString("type");
            if ("texture".equalsIgnoreCase(a5)) {
                a3.b.add(new vda(a4));
                continue;
            }
            if ("text".equalsIgnoreCase(a5)) {
                a3.b.add(new hca(a4));
                continue;
            }
            if ("model".equalsIgnoreCase(a5)) {
                a3.b.add(new mha(a4));
                continue;
            }
            if (!"color".equalsIgnoreCase(a5)) continue;
            a3.b.add(new ida(a4));
        }
    }

    public void ALLATORIxDEMO(tfa a2) {
        eba a3;
        a3.ALLATORIxDEMO = a2;
        for (uha a4 : a3.b) {
            a4.ALLATORIxDEMO(a2);
        }
    }

    public double f() {
        eba a2;
        return a2.ALLATORIxDEMO.ALLATORIxDEMO(a2.c);
    }

    public double c() {
        eba a2;
        return a2.ALLATORIxDEMO.ALLATORIxDEMO(a2.m);
    }

    public double ALLATORIxDEMO() {
        eba a2;
        return a2.ALLATORIxDEMO.ALLATORIxDEMO(a2.q);
    }

    public Point2D.Float c(int a2) {
        String[] a3;
        eba a4;
        if (a2 < a4.o.size() && (a3 = a4.o.get(a2).split(",")).length == 2) {
            float a5 = (float)a4.ALLATORIxDEMO.ALLATORIxDEMO(a3[0].replace("i", String.valueOf(a2)));
            float a6 = (float)a4.ALLATORIxDEMO.ALLATORIxDEMO(a3[1].replace("i", String.valueOf(a2)));
            return new Point2D.Float(a5, a6);
        }
        return new Point2D.Float(999.0f, 999.0f);
    }

    public Point2D.Float ALLATORIxDEMO(int a2) {
        String[] a3;
        eba a4;
        if (a2 < a4.y.size() && (a3 = a4.y.get(a2).split(",")).length == 2) {
            float a5 = (float)a4.ALLATORIxDEMO.ALLATORIxDEMO(a3[0].replace("i", String.valueOf(a2)));
            float a6 = (float)a4.ALLATORIxDEMO.ALLATORIxDEMO(a3[1].replace("i", String.valueOf(a2)));
            return new Point2D.Float(a5, a6);
        }
        return new Point2D.Float(999.0f, 999.0f);
    }

    public boolean ALLATORIxDEMO() {
        eba a2;
        boolean a3;
        boolean bl2 = a3 = Keyboard.isKeyDown((int)42) || Keyboard.isKeyDown((int)54);
        if (a2.r == 1 && a3) {
            return false;
        }
        return a2.r != 2 || a3;
    }

    public int ALLATORIxDEMO() {
        eba a2;
        return a2.v;
    }
}

