/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.eu;
import eos.moe.dragoncore.hq;
import eos.moe.dragoncore.rr;
import eos.moe.dragoncore.wp;
import java.util.ArrayList;
import java.util.List;

public class rx {
    public String y;
    public List<eu> k;
    public int ALLATORIxDEMO = -1;

    public rx(String a2, List<eu> a4, hq a5) {
        rx a6;
        a6.y = a2;
        a6.k = new ArrayList<eu>();
        a4.forEach(a3 -> {
            rx a4;
            a4.k.add(new eu((eu)a3, a5));
        });
    }

    public eu c() {
        rx a2;
        ++a2.ALLATORIxDEMO;
        if (a2.ALLATORIxDEMO >= a2.k.size()) {
            a2.ALLATORIxDEMO = 0;
        }
        if (a2.k.isEmpty()) {
            return null;
        }
        return a2.k.get(a2.ALLATORIxDEMO);
    }

    public eu ALLATORIxDEMO() {
        rx a2;
        if (a2.ALLATORIxDEMO < 0) {
            a2.ALLATORIxDEMO = 0;
        }
        if (a2.k.isEmpty()) {
            return null;
        }
        return a2.k.get(a2.ALLATORIxDEMO);
    }

    public void ALLATORIxDEMO(wp a2) {
        rx a3;
        ArrayList<eu> a4 = new ArrayList<eu>();
        for (eu a5 : a3.k) {
            if (a4.contains(a5)) continue;
            a5.ALLATORIxDEMO(a2);
            a4.add(a5);
        }
    }

    public eu ALLATORIxDEMO(rr a2) {
        rx a3;
        eu a4 = a3.ALLATORIxDEMO();
        if (a3.k.size() > 1 && a2.y + a2.k >= (float)a4.o) {
            a2.y = 0.0f;
            return a3.c();
        }
        return a4;
    }

    public eu ALLATORIxDEMO(int a2) {
        rx a3;
        if (a2 > a3.ALLATORIxDEMO().o) {
            return a3.c();
        }
        return a3.ALLATORIxDEMO();
    }
}

