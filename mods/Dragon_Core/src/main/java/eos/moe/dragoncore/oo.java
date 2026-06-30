/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.b;
import eos.moe.dragoncore.c;
import eos.moe.dragoncore.dw;
import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.vx;
import eos.moe.dragoncore.xy;
import java.util.List;

public class oo
implements c {
    public oo() {
        oo a2;
    }

    @Override
    public b ALLATORIxDEMO(nh a2, vx a3) {
        List<b> a4 = a2.c();
        String a5 = a2.c(a3.ALLATORIxDEMO());
        xy a6 = new xy(a5);
        if (a4.size() > 0 || a2.ALLATORIxDEMO(a5).equals("func") || a2.ALLATORIxDEMO(a5).equals("\u65b9\u6cd5")) {
            return new dw(a6, a4.toArray(new b[0]));
        }
        return a6;
    }
}

