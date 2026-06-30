/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ec;
import eos.moe.dragoncore.ei;
import eos.moe.dragoncore.em;
import eos.moe.dragoncore.fa;
import eos.moe.dragoncore.gc;
import eos.moe.dragoncore.hc;
import eos.moe.dragoncore.ka;
import eos.moe.dragoncore.la;
import eos.moe.dragoncore.lc;
import eos.moe.dragoncore.nc;
import eos.moe.dragoncore.sc;
import eos.moe.dragoncore.ta;
import eos.moe.dragoncore.ua;
import eos.moe.dragoncore.xb;
import eos.moe.dragoncore.yk;
import eos.moe.dragoncore.zc;
import java.nio.charset.Charset;

public class ul {
    public ul() {
        ul a2;
    }

    public ec ALLATORIxDEMO(lc a2, boolean a3, int a4, Charset a5, ua a6) throws yk {
        ul a7;
        ec a8 = new ec();
        a8.ALLATORIxDEMO(ei.r);
        a8.k(fa.ALLATORIxDEMO(a2, a6));
        a8.f(fa.ALLATORIxDEMO(a2).ALLATORIxDEMO());
        if (a2.z() && a2.ALLATORIxDEMO() == zc.k) {
            a8.ALLATORIxDEMO(gc.y);
            a8.ALLATORIxDEMO(a7.ALLATORIxDEMO(a2));
            a8.ALLATORIxDEMO(a8.ALLATORIxDEMO() + 11);
        } else {
            a8.ALLATORIxDEMO(a2.ALLATORIxDEMO());
        }
        if (a2.z()) {
            if (a2.ALLATORIxDEMO() == null || a2.ALLATORIxDEMO() == zc.b) {
                throw new yk("Encryption method has to be set when encryptFiles flag is set in zip parameters");
            }
            a8.x(true);
            a8.ALLATORIxDEMO(a2.ALLATORIxDEMO());
        }
        String a9 = a7.ALLATORIxDEMO(a2.f());
        a8.ALLATORIxDEMO(a9);
        a8.c(a7.ALLATORIxDEMO(a9, a5));
        a8.x(a3 ? a4 : 0);
        if (a2.c() > 0L) {
            a8.x(ta.x(a2.c()));
        } else {
            a8.x(ta.x(System.currentTimeMillis()));
        }
        boolean a10 = ka.ALLATORIxDEMO(a9);
        a8.ALLATORIxDEMO(a10);
        a8.f(ka.ALLATORIxDEMO(a10));
        if (a2.f() && a2.ALLATORIxDEMO() == -1L) {
            a8.ALLATORIxDEMO(0L);
        } else {
            a8.ALLATORIxDEMO(a2.ALLATORIxDEMO());
        }
        if (a2.z() && a2.ALLATORIxDEMO() == zc.o) {
            a8.f(a2.f());
        }
        a8.c(a7.ALLATORIxDEMO(a8.x(), a2, a5));
        a8.f(a2.f());
        a8.c(a2.ALLATORIxDEMO());
        return a8;
    }

    public hc ALLATORIxDEMO(ec a2) {
        hc a3 = new hc();
        a3.ALLATORIxDEMO(ei.g);
        a3.f(a2.f());
        a3.ALLATORIxDEMO(a2.ALLATORIxDEMO());
        a3.x(a2.d());
        a3.ALLATORIxDEMO(a2.ALLATORIxDEMO());
        a3.c(a2.c());
        a3.ALLATORIxDEMO(a2.ALLATORIxDEMO());
        a3.x(a2.x());
        a3.ALLATORIxDEMO(a2.ALLATORIxDEMO());
        a3.ALLATORIxDEMO(a2.ALLATORIxDEMO());
        a3.f(a2.f());
        a3.c(a2.c());
        a3.c((byte[])a2.c().clone());
        a3.f(a2.f());
        a3.ALLATORIxDEMO(a2.ALLATORIxDEMO());
        return a3;
    }

    private /* synthetic */ byte[] ALLATORIxDEMO(boolean a2, lc a3, Charset a4) {
        ul a5;
        byte[] a6 = new byte[2];
        a6[0] = a5.ALLATORIxDEMO(a2, a3);
        if (a4.equals(la.k)) {
            a6[1] = xb.c(a6[1], 3);
        }
        return a6;
    }

    private /* synthetic */ byte ALLATORIxDEMO(boolean a2, lc a3) {
        byte a4 = 0;
        if (a2) {
            a4 = xb.c(a4, 0);
        }
        if (gc.o.equals((Object)a3.ALLATORIxDEMO())) {
            if (sc.b.equals((Object)a3.ALLATORIxDEMO())) {
                a4 = xb.ALLATORIxDEMO(a4, 1);
                a4 = xb.ALLATORIxDEMO(a4, 2);
            } else if (sc.o.equals((Object)a3.ALLATORIxDEMO())) {
                a4 = xb.c(a4, 1);
                a4 = xb.ALLATORIxDEMO(a4, 2);
            } else if (sc.q.equals((Object)a3.ALLATORIxDEMO())) {
                a4 = xb.ALLATORIxDEMO(a4, 1);
                a4 = xb.c(a4, 2);
            } else if (sc.c.equals((Object)a3.ALLATORIxDEMO()) || sc.y.equals((Object)a3.ALLATORIxDEMO())) {
                a4 = xb.c(a4, 1);
                a4 = xb.c(a4, 2);
            }
        }
        if (a3.f()) {
            a4 = xb.c(a4, 3);
        }
        return a4;
    }

    private /* synthetic */ String ALLATORIxDEMO(String a2) throws yk {
        if (!ta.ALLATORIxDEMO(a2)) {
            throw new yk("fileNameInZip is null or empty");
        }
        return a2;
    }

    private /* synthetic */ em ALLATORIxDEMO(lc a2) throws yk {
        em a3 = new em();
        if (a2.ALLATORIxDEMO() != null) {
            a3.ALLATORIxDEMO(a2.ALLATORIxDEMO());
        }
        if (a2.ALLATORIxDEMO() == nc.m) {
            a3.ALLATORIxDEMO(nc.m);
        } else if (a2.ALLATORIxDEMO() == nc.c) {
            a3.ALLATORIxDEMO(nc.c);
        } else if (a2.ALLATORIxDEMO() == nc.q) {
            a3.ALLATORIxDEMO(nc.q);
        } else {
            throw new yk("invalid AES key strength");
        }
        a3.ALLATORIxDEMO(a2.ALLATORIxDEMO());
        return a3;
    }

    private /* synthetic */ int ALLATORIxDEMO(String a2, Charset a3) {
        return a2.getBytes(a3).length;
    }
}

