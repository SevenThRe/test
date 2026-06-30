/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.b;
import eos.moe.dragoncore.d;
import eos.moe.dragoncore.tv;
import java.util.function.Predicate;

public class bw
implements d {
    private final Predicate<b> k;
    private b ALLATORIxDEMO;

    public bw(Predicate<b> a2) {
        bw a3;
        a3.k = a2;
    }

    @Override
    public Object ALLATORIxDEMO(b a2) {
        bw a3;
        if (a3.k.test(a2)) {
            a3.ALLATORIxDEMO = a2;
            return tv.o;
        }
        return null;
    }

    public b ALLATORIxDEMO() {
        bw a2;
        return a2.ALLATORIxDEMO;
    }
}

