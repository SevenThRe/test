/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.bk;
import eos.moe.dragoncore.el;
import eos.moe.dragoncore.gc;
import eos.moe.dragoncore.ka;
import eos.moe.dragoncore.lc;
import eos.moe.dragoncore.ua;
import eos.moe.dragoncore.zc;

public class fa {
    public fa() {
        fa a2;
    }

    public static int ALLATORIxDEMO(lc a2, ua a3) {
        byte[] a4 = new byte[]{el.b.ALLATORIxDEMO(), el.y.ALLATORIxDEMO()};
        if (ka.f() && !a2.ALLATORIxDEMO()) {
            a4[1] = el.o.ALLATORIxDEMO();
        }
        return a3.ALLATORIxDEMO(a4, 0);
    }

    public static bk ALLATORIxDEMO(lc a2) {
        bk a3 = bk.q;
        if (a2.ALLATORIxDEMO() == gc.o) {
            a3 = bk.b;
        }
        if (a2.ALLATORIxDEMO() > 0xFFFFFFFFL) {
            a3 = bk.o;
        }
        if (a2.z() && a2.ALLATORIxDEMO().equals((Object)zc.k)) {
            a3 = bk.y;
        }
        return a3;
    }
}

