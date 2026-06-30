/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.da;
import eos.moe.dragoncore.dq;
import eos.moe.dragoncore.kq;

public class st {
    private final dq ALLATORIxDEMO;

    public st(kq a2) {
        st a3;
        a3.ALLATORIxDEMO = new dq(a2, null);
    }

    public st(kq a2, int a3) {
        st a4;
        a4.ALLATORIxDEMO = new dq(a2, null);
        a4.ALLATORIxDEMO(a3);
    }

    private /* synthetic */ st(dq a2) {
        st a3;
        a3.ALLATORIxDEMO = a2.c();
    }

    public static st ALLATORIxDEMO(dq a2) {
        return new st(a2);
    }

    public st ALLATORIxDEMO(boolean a2) {
        st a3;
        dq.ALLATORIxDEMO(a3.ALLATORIxDEMO, a2);
        return a3;
    }

    public st ALLATORIxDEMO(int a2) {
        st a3;
        dq.ALLATORIxDEMO(a3.ALLATORIxDEMO, Math.max(a2, 0));
        return a3;
    }

    public st ALLATORIxDEMO(float a2) {
        st a3;
        dq.ALLATORIxDEMO(a3.ALLATORIxDEMO, Math.max(a2, 1.0E-4f));
        return a3;
    }

    public st ALLATORIxDEMO(st a2) {
        st a3;
        dq.ALLATORIxDEMO(a3.ALLATORIxDEMO, a2.ALLATORIxDEMO());
        return a3;
    }

    public void ALLATORIxDEMO(da a2, String a3) {
        st a4;
        a2.ALLATORIxDEMO(a4, a3);
    }

    public dq ALLATORIxDEMO() {
        st a2;
        return a2.ALLATORIxDEMO;
    }
}

