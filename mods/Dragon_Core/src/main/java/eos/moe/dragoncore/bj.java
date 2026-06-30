/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.je;
import eos.moe.dragoncore.u;

public class bj
extends je {
    public bj(u[] a2, String a3) throws Exception {
        super(a2, a3);
        bj a4;
    }

    @Override
    public int c() {
        return 1;
    }

    @Override
    public double ALLATORIxDEMO() {
        bj a2;
        double a3 = a2.ALLATORIxDEMO(0);
        return a3 < 0.0 ? Math.ceil(a3) : Math.floor(a3);
    }
}

