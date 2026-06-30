/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.u;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class sm
implements u {
    private u k;
    private double ALLATORIxDEMO;

    public sm(u a2, double a3) {
        sm a4;
        a4.k = a2;
        a4.ALLATORIxDEMO = a3;
    }

    @Override
    public double ALLATORIxDEMO() {
        sm a2;
        return a2.k.ALLATORIxDEMO() + a2.ALLATORIxDEMO;
    }

    public u ALLATORIxDEMO() {
        sm a2;
        return a2.k;
    }

    public double c() {
        sm a2;
        return a2.ALLATORIxDEMO;
    }
}

