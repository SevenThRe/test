/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.b;
import eos.moe.dragoncore.br;
import eos.moe.dragoncore.pf;
import eos.moe.dragoncore.us;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xn;

public class wu
extends us {
    public wu(b a2, b a3) {
        super(a2, a3);
        wu a4;
    }

    @Override
    public String ALLATORIxDEMO() {
        return "/";
    }

    @Override
    public v ALLATORIxDEMO(br a2, xn a3) {
        wu a4;
        v a5 = a4.k.ALLATORIxDEMO(a2, a3);
        double a6 = a5.ALLATORIxDEMO();
        if (a6 == 0.0) {
            return pf.y;
        }
        return new pf(a6 / a4.ALLATORIxDEMO.ALLATORIxDEMO(a2, a3).ALLATORIxDEMO());
    }
}

