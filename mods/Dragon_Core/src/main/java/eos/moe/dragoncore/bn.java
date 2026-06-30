/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ml;
import eos.moe.dragoncore.n;
import eos.moe.dragoncore.pf;
import eos.moe.dragoncore.r;
import eos.moe.dragoncore.um;
import eos.moe.dragoncore.ve;
import java.lang.ref.WeakReference;

public class bn
extends ve {
    private WeakReference<r> v;
    private String m;
    private ml c;
    private long q;
    private long b;
    private boolean o;
    private long y;
    private long k;
    private n ALLATORIxDEMO;

    public bn(String a2, r a3, ml a4, long a5, boolean a6, long a7, long a8, n a9) {
        bn a10;
        a10.v = new WeakReference<r>(a3);
        a10.m = a2;
        a10.c = a4;
        a10.b = a5;
        a10.o = a6;
        a10.y = a7;
        a10.k = a8;
        a10.ALLATORIxDEMO = a9;
    }

    @Override
    public boolean ALLATORIxDEMO(double a2) {
        bn a3;
        a3.q = (long)((double)a3.q + a2);
        r a4 = (r)a3.v.get();
        if (a4 == null || a4.isClosed()) {
            return true;
        }
        long a5 = a3.q;
        if (a3.q > a3.y + a3.k + a3.b) {
            if (a3.o) {
                a4.setAnimationValue(a3.m, pf.ALLATORIxDEMO(a3.c.ALLATORIxDEMO()));
            } else {
                a4.setAnimationValue(a3.m, null);
            }
            return true;
        }
        if (a5 >= a3.y) {
            if (a5 < a3.y + a3.k) {
                double a6 = (float)(a5 - a3.y) / (float)a3.k;
                double a7 = a3.ALLATORIxDEMO.d().apply(a6);
                double a8 = um.f(a3.c.c(), a3.c.ALLATORIxDEMO(), a7);
                a4.setAnimationValue(a3.m, pf.ALLATORIxDEMO(a8));
            } else {
                a4.setAnimationValue(a3.m, pf.ALLATORIxDEMO(a3.c.ALLATORIxDEMO()));
            }
        }
        return false;
    }
}

