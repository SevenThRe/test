/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.b;
import eos.moe.dragoncore.br;
import eos.moe.dragoncore.us;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xf;
import eos.moe.dragoncore.xn;

public class ar
extends us {
    public ar(b a2, b a3) {
        super(a2, a3);
        ar a4;
    }

    @Override
    public String ALLATORIxDEMO() {
        return "&";
    }

    @Override
    public v ALLATORIxDEMO(br a2, xn a3) {
        ar a4;
        v a5 = a4.k.ALLATORIxDEMO(a2, a3);
        v a6 = a4.ALLATORIxDEMO.ALLATORIxDEMO(a2, a3);
        return new xf(a5.c() + a6.c());
    }
}

