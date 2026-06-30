/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.u;
import eos.moe.dragoncore.zh;

public class ah
implements u {
    public zh y;
    public u k;
    public u ALLATORIxDEMO;

    public ah(zh a2, u a3, u a4) {
        ah a5;
        a5.y = a2;
        a5.k = a3;
        a5.ALLATORIxDEMO = a4;
    }

    @Override
    public double ALLATORIxDEMO() {
        ah a2;
        return a2.y.ALLATORIxDEMO(a2.k.ALLATORIxDEMO(), a2.ALLATORIxDEMO.ALLATORIxDEMO());
    }

    public String toString() {
        ah a2;
        return a2.k.toString() + " " + a2.y.y + " " + a2.ALLATORIxDEMO.toString();
    }
}

