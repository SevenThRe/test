/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.b;
import eos.moe.dragoncore.br;
import eos.moe.dragoncore.pf;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xn;

public class cx
implements b {
    public b[] ALLATORIxDEMO;

    public cx(b[] a2) {
        cx a3;
        a3.ALLATORIxDEMO = a2;
    }

    @Override
    public v ALLATORIxDEMO(br a2, xn a3) {
        cx a4;
        for (b a5 : a4.ALLATORIxDEMO) {
            a5.ALLATORIxDEMO(a2, a3);
            if (a2.ALLATORIxDEMO() != null) {
                return a2.ALLATORIxDEMO();
            }
            if (a2.c() || a2.ALLATORIxDEMO()) break;
        }
        return pf.y;
    }
}

