/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.b;
import eos.moe.dragoncore.d;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class uz
implements d {
    private final Predicate<b> k;
    private final List<b> ALLATORIxDEMO = new ArrayList<b>();

    public uz(Predicate<b> a2) {
        uz a3;
        a3.k = a2;
    }

    @Override
    public Object ALLATORIxDEMO(b a2) {
        uz a3;
        if (a3.k.test(a2)) {
            a3.ALLATORIxDEMO.add(a2);
        }
        return null;
    }

    public List<b> ALLATORIxDEMO() {
        uz a2;
        return a2.ALLATORIxDEMO;
    }
}

