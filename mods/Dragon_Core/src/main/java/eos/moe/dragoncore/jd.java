/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.u;

public class jd
implements u {
    public u y;
    public u k;
    public u ALLATORIxDEMO;

    public jd(u a2, u a3, u a4) {
        jd a5;
        a5.y = a2;
        a5.k = a3;
        a5.ALLATORIxDEMO = a4;
    }

    @Override
    public double ALLATORIxDEMO() {
        jd a2;
        return a2.y.ALLATORIxDEMO() != 0.0 ? a2.k.ALLATORIxDEMO() : a2.ALLATORIxDEMO.ALLATORIxDEMO();
    }

    public String toString() {
        jd a2;
        return a2.y.toString() + " ? " + a2.k.toString() + " : " + a2.ALLATORIxDEMO.toString();
    }
}

