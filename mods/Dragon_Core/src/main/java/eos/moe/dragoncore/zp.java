/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.b;
import eos.moe.dragoncore.c;
import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.vx;
import eos.moe.dragoncore.xv;
import java.util.List;

public class zp
implements c {
    public zp() {
        zp a2;
    }

    @Override
    public b ALLATORIxDEMO(nh a2, vx a3) {
        List<b> a4 = a2.c();
        if (a4.size() != 2) {
            throw new RuntimeException("Loop: Expected 2 argument, " + a4.size() + " argument given");
        }
        return new xv(a4.get(0), a4.get(1));
    }
}

