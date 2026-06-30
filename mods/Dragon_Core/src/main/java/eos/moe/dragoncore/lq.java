/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.b;
import eos.moe.dragoncore.c;
import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.nq;
import eos.moe.dragoncore.vx;
import java.util.List;

public class lq
implements c {
    public lq() {
        lq a2;
    }

    @Override
    public b ALLATORIxDEMO(nh a2, vx a3) {
        List<b> a4 = a2.c();
        if (a4.size() != 1) {
            throw new RuntimeException("IF: Expected 1 argument, " + a4.size() + " argument given");
        }
        return new nq(a4.get(0));
    }
}

