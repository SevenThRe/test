/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.b;
import eos.moe.dragoncore.br;
import eos.moe.dragoncore.bt;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xn;
import eos.moe.dragoncore.xy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class dw
implements b {
    public b k;
    public b[] ALLATORIxDEMO;

    public dw(b a2, b[] a3) {
        dw a4;
        a4.k = a2;
        a4.ALLATORIxDEMO = a3;
    }

    @Override
    public v ALLATORIxDEMO(br a2, xn a3) {
        dw a4;
        String a5 = a4.k instanceof xy ? ((xy)a4.k).ALLATORIxDEMO() : a4.k.ALLATORIxDEMO(a2, a3).c();
        List<b> a6 = Arrays.asList(a4.ALLATORIxDEMO);
        ArrayList<v> a7 = new ArrayList<v>();
        for (b a8 : a6) {
            v a9 = a8.ALLATORIxDEMO(a2, a3);
            a7.add(a9);
        }
        return a3.ALLATORIxDEMO(a5, new bt(a7));
    }
}

