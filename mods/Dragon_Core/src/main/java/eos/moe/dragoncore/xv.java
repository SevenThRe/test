/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.b;
import eos.moe.dragoncore.br;
import eos.moe.dragoncore.pf;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xn;

public class xv
implements b {
    public b k;
    public b ALLATORIxDEMO;

    public xv(b a2, b a3) {
        xv a4;
        a4.k = a2;
        a4.ALLATORIxDEMO = a3;
    }

    @Override
    public v ALLATORIxDEMO(br a2, xn a3) {
        br a4 = new br();
        for (int a5 = (int)a6.k.ALLATORIxDEMO(a2, a3).ALLATORIxDEMO(); a5 > 0; --a5) {
            xv a6;
            a6.ALLATORIxDEMO.ALLATORIxDEMO(a4, a3);
            if (a4.ALLATORIxDEMO() == null) continue;
            return a4.ALLATORIxDEMO();
        }
        return pf.y;
    }
}

