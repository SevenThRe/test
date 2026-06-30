/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.b;
import eos.moe.dragoncore.c;
import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.ps;
import eos.moe.dragoncore.vx;
import java.util.List;

public class es
implements c {
    public es() {
        es a2;
    }

    @Override
    public b ALLATORIxDEMO(nh a2, vx a3) {
        List<b> a4 = a2.c();
        if (a4.size() == 4) {
            return new ps(a4.get(0), a4.get(1), a4.get(2), a4.get(3));
        }
        if (a4.size() == 3) {
            return new ps(a4.get(0), a4.get(1), a4.get(2));
        }
        throw new RuntimeException("ForEach: Expected 3 argument, " + a4.size() + " argument given");
    }
}

