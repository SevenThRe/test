/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.b;
import eos.moe.dragoncore.ep;
import eos.moe.dragoncore.ez;
import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.vx;
import eos.moe.dragoncore.w;
import eos.moe.dragoncore.yw;

public class op
implements w {
    public op() {
        op a2;
    }

    @Override
    public b ALLATORIxDEMO(nh a2, vx a3, b a4) {
        op a5;
        if (a2.ALLATORIxDEMO(ez.u) || a2.ALLATORIxDEMO(ez.e)) {
            return new yw(a4, null, a2.ALLATORIxDEMO(a5.ALLATORIxDEMO()));
        }
        b a6 = a2.ALLATORIxDEMO(a5.ALLATORIxDEMO());
        if (a2.ALLATORIxDEMO(ez.n, false)) {
            b a7 = a2.ALLATORIxDEMO(ep.l);
            return new yw(a4, a6, a7);
        }
        if (!a2.ALLATORIxDEMO(ez.u) && !a2.ALLATORIxDEMO(ez.e)) {
            return new yw(a4, a6, null);
        }
        b a8 = a2.ALLATORIxDEMO(a5.ALLATORIxDEMO());
        return new yw(a4, a6, a8);
    }

    @Override
    public ep ALLATORIxDEMO() {
        return ep.g;
    }
}

