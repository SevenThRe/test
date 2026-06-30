/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.cb;
import eos.moe.armourers.ce;
import eos.moe.armourers.ra;
import eos.moe.armourers.ya;
import eos.moe.armourers.yb;
import eos.moe.armourers.yj;
import eos.moe.armourers.zb;

public class u {
    public u() {
        u a2;
    }

    public static int r(zb a2, ra a3) {
        byte[] byArray = new byte[]{yj.c.r(), yj.m.r()};
        if (ya.r() && !a2.h()) {
            byArray[1] = yj.j.r();
        }
        return a3.r(byArray, 0);
    }

    public static ce r(zb a2) {
        ce ce2 = ce.c;
        if (a2.r() == cb.j) {
            ce2 = ce.m;
        }
        if (a2.r() > 0xFFFFFFFFL) {
            ce2 = ce.j;
        }
        if (a2.s() && a2.r().equals((Object)yb.j)) {
            ce2 = ce.s;
        }
        return ce2;
    }
}

