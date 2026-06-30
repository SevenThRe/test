/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ng;
import eos.moe.dragoncore.ym;

public class yf {
    public yf() {
        yf a2;
    }

    public boolean ALLATORIxDEMO(ym a2, String a3) {
        if (a2 == null || a3 == null) {
            return true;
        }
        String[] a4 = a3.split(":");
        if (a4.length != 3) {
            return true;
        }
        byte[] a5 = ng.ALLATORIxDEMO(a4[0]);
        int a6 = Integer.parseInt(a4[1]);
        byte[] a7 = ng.ALLATORIxDEMO(a4[2]);
        a2.c(a5);
        a2.ALLATORIxDEMO(a6);
        a2.ALLATORIxDEMO(a7);
        return false;
    }

    public String ALLATORIxDEMO(ym a2) {
        String a3 = ng.ALLATORIxDEMO(a2.c()) + ":" + String.valueOf(a2.ALLATORIxDEMO()) + ":" + ng.ALLATORIxDEMO(a2.ALLATORIxDEMO());
        return a3;
    }
}

