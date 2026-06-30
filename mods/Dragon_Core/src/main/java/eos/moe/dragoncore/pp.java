/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.b;
import eos.moe.dragoncore.c;
import eos.moe.dragoncore.ez;
import eos.moe.dragoncore.lw;
import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.vx;
import eos.moe.dragoncore.ys;

public class pp
implements c {
    public pp() {
        pp a2;
    }

    @Override
    public b ALLATORIxDEMO(nh a2, vx a3) {
        if (a2.ALLATORIxDEMO(ez.w, false) || a2.ALLATORIxDEMO(ez.ua, false)) {
            return new lw(new ys(0.0));
        }
        return new lw(a2.ALLATORIxDEMO());
    }
}

