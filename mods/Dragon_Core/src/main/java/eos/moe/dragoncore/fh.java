/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.k;
import eos.moe.dragoncore.ve;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class fh
implements k {
    private List<ve> b = new CopyOnWriteArrayList<ve>();
    private List<ve> o = new CopyOnWriteArrayList<ve>();
    private List<ve> y = new CopyOnWriteArrayList<ve>();
    private List<ve> k = new CopyOnWriteArrayList<ve>();
    private double ALLATORIxDEMO;

    public fh() {
        fh a2;
    }

    @Override
    public void ALLATORIxDEMO() {
        fh a3;
        double a4 = System.currentTimeMillis();
        double a5 = a4 - a3.ALLATORIxDEMO;
        ArrayList<ve> a6 = new ArrayList<ve>(a3.b);
        for (ve ve2 : a6) {
            ve2.f();
            a3.b.remove(ve2);
            a3.o.add(ve2);
        }
        ArrayList<ve> a7 = new ArrayList<ve>(a3.o);
        for (ve a8 : a7) {
            if (a3.k.contains(a8)) {
                a3.k.remove(a8);
                a3.o.remove(a8);
                a3.y.add(a8);
                continue;
            }
            if (!a8.ALLATORIxDEMO(a5)) continue;
            a3.o.remove(a8);
            a3.y.add(a8);
        }
        ArrayList<ve> arrayList = new ArrayList<ve>(a3.y);
        for (ve a9 : arrayList) {
            a9.c();
            a3.y.remove(a9);
        }
        a3.ALLATORIxDEMO = a4;
    }

    @Override
    public void c(ve a2) {
        fh a3;
        a3.b.add(a2);
    }

    @Override
    public void ALLATORIxDEMO(ve a2) {
        fh a3;
        a3.k.add(a2);
    }
}

