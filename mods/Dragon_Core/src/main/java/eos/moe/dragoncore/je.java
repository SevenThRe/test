/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.u;

public abstract class je
implements u {
    public u[] k;
    public String ALLATORIxDEMO;

    public je(u[] a2, String a3) throws Exception {
        je a4;
        if (a2.length < a4.c()) {
            String a5 = String.format("Function '%s' requires at least %s arguments. %s are given!", a4.ALLATORIxDEMO(), a4.c(), a2.length);
            throw new Exception(a5);
        }
        a4.k = a2;
        a4.ALLATORIxDEMO = a3;
    }

    public double ALLATORIxDEMO(int a2) {
        je a3;
        if (a2 < 0 || a2 >= a3.k.length) {
            return 0.0;
        }
        return a3.k[a2].ALLATORIxDEMO();
    }

    public String toString() {
        je a2;
        String a3 = "";
        for (int a4 = 0; a4 < a2.k.length; ++a4) {
            a3 = a3 + a2.k[a4].toString();
            if (a4 >= a2.k.length - 1) continue;
            a3 = a3 + ", ";
        }
        return a2.ALLATORIxDEMO() + "(" + a3 + ")";
    }

    public String ALLATORIxDEMO() {
        je a2;
        return a2.ALLATORIxDEMO;
    }

    public int c() {
        return 0;
    }
}

