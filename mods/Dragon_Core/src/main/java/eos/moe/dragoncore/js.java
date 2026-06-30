/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.b;
import eos.moe.dragoncore.br;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xn;
import eos.moe.dragoncore.xy;

public class js
implements b {
    public b k;
    public b ALLATORIxDEMO;

    public js(b a2, b a3) {
        js a4;
        a4.k = a2;
        a4.ALLATORIxDEMO = a3;
    }

    @Override
    public v ALLATORIxDEMO(br a2, xn a3) {
        js a4;
        String a5 = a4.k instanceof xy ? ((xy)a4.k).ALLATORIxDEMO() : a4.k.ALLATORIxDEMO(a2, a3).c();
        return a3.ALLATORIxDEMO(a5 + "." + (int)a4.ALLATORIxDEMO.ALLATORIxDEMO(a2, a3).ALLATORIxDEMO());
    }

    @Override
    public void ALLATORIxDEMO(br a2, xn a3, v a4) {
        js a5;
        String a6 = a5.k instanceof xy ? ((xy)a5.k).ALLATORIxDEMO() : a5.k.ALLATORIxDEMO(a2, a3).c();
        a3.ALLATORIxDEMO(a6 + "." + (int)a5.ALLATORIxDEMO.ALLATORIxDEMO(a2, a3).ALLATORIxDEMO(), a4);
    }

    public b c() {
        js a2;
        return a2.k;
    }

    public void c(b a2) {
        a.k = a2;
    }

    public b ALLATORIxDEMO() {
        js a2;
        return a2.ALLATORIxDEMO;
    }

    public void ALLATORIxDEMO(b a2) {
        a.ALLATORIxDEMO = a2;
    }
}

