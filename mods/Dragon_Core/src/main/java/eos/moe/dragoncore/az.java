/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.b;
import eos.moe.dragoncore.ep;
import eos.moe.dragoncore.ez;
import eos.moe.dragoncore.js;
import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.us;
import eos.moe.dragoncore.vx;
import eos.moe.dragoncore.w;
import eos.moe.dragoncore.xy;

public class az
implements w {
    public az() {
        az a2;
    }

    @Override
    public b ALLATORIxDEMO(nh a2, vx a3, b a4) {
        az a5;
        b a6 = a2.c(a5.ALLATORIxDEMO());
        a2.ALLATORIxDEMO(ez.oa);
        if (a4 instanceof us) {
            us a7 = (us)a4;
            if (a7.c() instanceof xy) {
                a7.c(new js(a7.c(), a6));
            } else {
                a7.ALLATORIxDEMO(new js(a7.ALLATORIxDEMO(), a6));
            }
            return a7;
        }
        return new js(a4, a6);
    }

    @Override
    public ep ALLATORIxDEMO() {
        return ep.t;
    }
}

