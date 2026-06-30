/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.b;
import eos.moe.dragoncore.br;
import eos.moe.dragoncore.pf;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xn;

public class yw
implements b {
    public b y;
    public b k;
    public b ALLATORIxDEMO;

    public yw(b a2) {
        yw a3;
        a3.y = a2;
    }

    public yw(b a2, b a3, b a4) {
        yw a5;
        a5.y = a2;
        a5.k = a3;
        a5.ALLATORIxDEMO = a4;
    }

    @Override
    public v ALLATORIxDEMO(br a2, xn a3) {
        yw a4;
        if (a4.y.ALLATORIxDEMO(a2, a3).ALLATORIxDEMO() == 1.0) {
            return a4.k == null ? a4.y.ALLATORIxDEMO(a2, a3) : a4.k.ALLATORIxDEMO(a2, a3);
        }
        if (a4.ALLATORIxDEMO != null) {
            return a4.ALLATORIxDEMO.ALLATORIxDEMO(a2, a3);
        }
        return pf.y;
    }
}

