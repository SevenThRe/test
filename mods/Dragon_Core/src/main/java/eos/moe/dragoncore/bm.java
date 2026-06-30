/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.gm;
import eos.moe.dragoncore.nd;
import eos.moe.dragoncore.u;
import eos.moe.dragoncore.zi;

public class bm
extends gm {
    public zi k;
    public u ALLATORIxDEMO;

    public bm(nd a2, zi a3, u a4) {
        super(a2);
        bm a5;
        a5.k = a3;
        a5.ALLATORIxDEMO = a4;
    }

    @Override
    public double ALLATORIxDEMO() {
        bm a2;
        double a3 = a2.ALLATORIxDEMO.ALLATORIxDEMO();
        a2.k.ALLATORIxDEMO(a3);
        return a3;
    }

    public String toString() {
        bm a2;
        return a2.k.ALLATORIxDEMO() + " = " + a2.ALLATORIxDEMO.toString();
    }
}

