/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.yk;
import eos.moe.dragoncore.zk;

public enum gc {
    b(0),
    o(8),
    y(99);

    private int k;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private /* synthetic */ gc(int n2) {
        void a2;
        gc a3;
        a3.k = a2;
    }

    public int ALLATORIxDEMO() {
        gc a2;
        return a2.k;
    }

    public static gc ALLATORIxDEMO(int a2) throws yk {
        for (gc a3 : gc.values()) {
            if (a3.ALLATORIxDEMO() != a2) continue;
            return a3;
        }
        throw new yk("Unknown compression method", zk.o);
    }
}

