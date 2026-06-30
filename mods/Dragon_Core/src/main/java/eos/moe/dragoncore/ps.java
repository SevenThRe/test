/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.b;
import eos.moe.dragoncore.br;
import eos.moe.dragoncore.hl;
import eos.moe.dragoncore.pf;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xn;
import java.util.ArrayList;

public class ps
implements b {
    public b o;
    public b y;
    public b k;
    public b ALLATORIxDEMO;

    public ps(b a2, b a3, b a4) {
        a5(a2, null, a3, a4);
        ps a5;
    }

    public ps(b a2, b a3, b a4, b a5) {
        ps a6;
        a6.o = a2;
        a6.y = a4;
        a6.k = a5;
        a6.ALLATORIxDEMO = a3;
    }

    @Override
    public v ALLATORIxDEMO(br a2, xn a3) {
        ps a4;
        v a5 = a4.y.ALLATORIxDEMO(a2, a3);
        if (a5 instanceof hl) {
            hl a6 = (hl)a5;
            br a7 = new br();
            ArrayList<v> a8 = new ArrayList<v>(a6.ALLATORIxDEMO());
            for (int a9 = 0; a9 < a8.size(); ++a9) {
                v a10 = a8.get(a9);
                if (a4.ALLATORIxDEMO != null) {
                    a4.ALLATORIxDEMO.ALLATORIxDEMO(a7, a3, pf.ALLATORIxDEMO(a9));
                }
                a4.o.ALLATORIxDEMO(a7, a3, a10);
                a4.k.ALLATORIxDEMO(a7, a3);
                if (a7.ALLATORIxDEMO() != null) {
                    return a7.ALLATORIxDEMO();
                }
                if (a7.c()) break;
            }
        }
        return pf.y;
    }
}

