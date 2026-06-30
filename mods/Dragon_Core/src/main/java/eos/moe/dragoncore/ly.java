/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.b;
import eos.moe.dragoncore.c;
import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.ur;
import eos.moe.dragoncore.vx;
import eos.moe.dragoncore.ys;
import java.util.List;

public class ly
implements c {
    public ly() {
        ly a2;
    }

    @Override
    public b ALLATORIxDEMO(nh a2, vx a3) {
        List<b> a4 = a2.c();
        if (a4.size() == 2) {
            return new ur(a4.get(0), a4.get(1), new ys(1.0));
        }
        if (a4.size() == 3) {
            return new ur(a4.get(0), a4.get(1), a4.get(2));
        }
        throw new RuntimeException("Delay: Expected 2|3 argument, " + a4.size() + " argument given");
    }
}

