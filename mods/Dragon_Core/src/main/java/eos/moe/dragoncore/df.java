/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.je;
import eos.moe.dragoncore.u;
import java.util.Random;

public class df
extends je {
    public Random ALLATORIxDEMO = new Random();

    public df(u[] a2, String a3) throws Exception {
        super(a2, a3);
        df a4;
    }

    @Override
    public double ALLATORIxDEMO() {
        df a2;
        double a3 = 0.0;
        if (a2.k.length >= 3) {
            a2.ALLATORIxDEMO.setSeed((long)a2.ALLATORIxDEMO(2));
            a3 = a2.ALLATORIxDEMO.nextDouble();
        } else {
            a3 = Math.random();
        }
        if (a2.k.length >= 2) {
            double a4 = a2.ALLATORIxDEMO(0);
            double a5 = a2.ALLATORIxDEMO(1);
            double a6 = Math.min(a4, a5);
            double a7 = Math.max(a4, a5);
            a3 = a3 * (a7 - a6) + a6;
        } else if (a2.k.length >= 1) {
            a3 *= a2.ALLATORIxDEMO(0);
        }
        return a3;
    }
}

