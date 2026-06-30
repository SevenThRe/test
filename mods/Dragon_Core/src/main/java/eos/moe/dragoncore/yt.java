/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.b;
import eos.moe.dragoncore.br;
import eos.moe.dragoncore.pf;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xn;
import java.util.List;

public class yt
implements b {
    public List<b> ALLATORIxDEMO;

    public yt(List<b> a2) {
        yt a3;
        a3.ALLATORIxDEMO = a2;
    }

    @Override
    public v ALLATORIxDEMO(br a2, xn a3) {
        yt a4;
        for (int a5 = 0; a5 < a4.ALLATORIxDEMO.size(); a5 += 2) {
            b a6 = a4.ALLATORIxDEMO.get(a5);
            v a7 = a6.ALLATORIxDEMO(a2, a3);
            if (a5 + 1 >= a4.ALLATORIxDEMO.size()) {
                return a7;
            }
            if (a7.ALLATORIxDEMO() != 1.0) continue;
            b a8 = a4.ALLATORIxDEMO.get(a5 + 1);
            return a8.ALLATORIxDEMO(a2, a3);
        }
        return pf.y;
    }
}

