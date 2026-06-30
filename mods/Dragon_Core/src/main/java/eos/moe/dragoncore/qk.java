/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.pf;
import eos.moe.dragoncore.r;
import eos.moe.dragoncore.v;

public class qk
implements r {
    private final String k;
    private final r ALLATORIxDEMO;

    public qk(String a2, r a3, double a4) {
        qk a5;
        a5.k = a2;
        a5.ALLATORIxDEMO = a3;
        a5.ALLATORIxDEMO(a4);
    }

    public double ALLATORIxDEMO() {
        qk a2;
        return a2.getAnimationValue(a2.k).ALLATORIxDEMO();
    }

    public void ALLATORIxDEMO(double a2) {
        qk a3;
        a3.setAnimationValue(a3.k, pf.ALLATORIxDEMO(a2));
    }

    public qk ALLATORIxDEMO(r a2) {
        qk a3;
        return new qk(a3.k, a2, a3.ALLATORIxDEMO());
    }

    @Override
    public void setAnimationValue(String a2, v a3) {
        qk a4;
        a4.ALLATORIxDEMO.setAnimationValue(a2, a3);
    }

    @Override
    public v getAnimationValue(String a2) {
        qk a3;
        return a3.ALLATORIxDEMO.getAnimationValue(a2);
    }

    @Override
    public boolean isClosed() {
        qk a2;
        return a2.ALLATORIxDEMO.isClosed();
    }
}

