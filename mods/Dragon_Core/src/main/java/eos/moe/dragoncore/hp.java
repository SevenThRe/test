/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import java.util.List;
import org.yaml.snakeyamla.configuration.ConfigurationSection;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class hp {
    private String c;
    private String q;
    private float b;
    private List<String> o;
    private String y;
    private int k;
    private boolean ALLATORIxDEMO;

    public hp(ConfigurationSection a2) {
        hp a3;
        a3.c = a2.getName();
        a3.q = a2.getString("blendType", "OVERRIDE");
        a3.b = (float)a2.getDouble("weight", 1.0);
        a3.o = a2.getStringList("animations");
        a3.y = a2.getString("next");
        a3.k = a2.getInt("nextTransitionTime");
        a3.ALLATORIxDEMO = a2.getBoolean("lockBodyAngle");
    }

    public String f() {
        hp a2;
        return a2.c;
    }

    public String c() {
        hp a2;
        return a2.q;
    }

    public float ALLATORIxDEMO() {
        hp a2;
        return a2.b;
    }

    public List<String> ALLATORIxDEMO() {
        hp a2;
        return a2.o;
    }

    public String ALLATORIxDEMO() {
        hp a2;
        return a2.y;
    }

    public boolean ALLATORIxDEMO() {
        hp a2;
        return a2.ALLATORIxDEMO;
    }

    public int ALLATORIxDEMO() {
        hp a2;
        return a2.k;
    }
}

