/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.tfa;
import org.yaml.snakeyamla.configuration.ConfigurationSection;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class uha {
    private String o;
    private String y;
    private String k;
    private tfa ALLATORIxDEMO;

    public uha(ConfigurationSection a2) {
        uha a3;
        a3.o = a2.getString("type");
        a3.y = String.valueOf(a2.get("x", "0"));
        a3.k = String.valueOf(a2.get("y", "0"));
    }

    public void ALLATORIxDEMO(tfa a2) {
        a.ALLATORIxDEMO = a2;
    }

    public abstract void ALLATORIxDEMO();

    public String ALLATORIxDEMO() {
        uha a2;
        return a2.o;
    }

    public double c() {
        uha a2;
        return a2.ALLATORIxDEMO.ALLATORIxDEMO(a2.y);
    }

    public double ALLATORIxDEMO() {
        uha a2;
        return a2.ALLATORIxDEMO.ALLATORIxDEMO(a2.k);
    }

    public tfa ALLATORIxDEMO() {
        uha a2;
        return a2.ALLATORIxDEMO;
    }
}

