/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.ab;
import eos.moe.armourers.c;
import eos.moe.armourers.cb;
import eos.moe.armourers.ga;
import eos.moe.armourers.nb;
import eos.moe.armourers.pb;
import eos.moe.armourers.ph;
import eos.moe.armourers.ra;
import eos.moe.armourers.u;
import eos.moe.armourers.vb;
import eos.moe.armourers.wa;
import eos.moe.armourers.wc;
import eos.moe.armourers.wm;
import eos.moe.armourers.ya;
import eos.moe.armourers.yb;
import eos.moe.armourers.zb;
import java.nio.charset.Charset;

public class ue {
    private /* synthetic */ wc r(zb a2) throws ph {
        wc wc2;
        wc wc3 = new wc();
        if (a2.r() != null) {
            wc3.r(a2.r());
        }
        if (a2.r() == vb.r) {
            wc wc4 = wc3;
            wc2 = wc4;
            wc4.r(vb.r);
        } else if (a2.r() == vb.v) {
            wc wc5 = wc3;
            wc2 = wc5;
            wc5.r(vb.v);
        } else if (a2.r() == vb.w) {
            wc wc6 = wc3;
            wc2 = wc6;
            wc6.r(vb.w);
        } else {
            throw new ph("invalid AES key strength");
        }
        wc2.r(a2.r());
        return wc3;
    }

    private /* synthetic */ int r(String a2, Charset a3) {
        return a2.getBytes(a3).length;
    }

    /*
     * Unable to fully structure code
     */
    private /* synthetic */ byte r(boolean a, zb a) {
        var3_3 = 0;
        if (a) {
            var3_3 = wa.y(var3_3, 0);
        }
        if (!cb.j.equals((Object)a.r())) ** GOTO lbl23
        if (ab.j.equals((Object)a.r())) {
            var3_3 = wa.r(var3_3, 1);
            var3_3 = wa.r(var3_3, 2);
            v0 = a;
        } else if (ab.s.equals((Object)a.r())) {
            var3_3 = wa.y(var3_3, 1);
            var3_3 = wa.r(var3_3, 2);
            v0 = a;
        } else if (ab.r.equals((Object)a.r())) {
            var3_3 = wa.r(var3_3, 1);
            var3_3 = wa.y(var3_3, 2);
            v0 = a;
        } else {
            if (ab.v.equals((Object)a.r()) || ab.c.equals((Object)a.r())) {
                var3_3 = wa.y(var3_3, 1);
                var3_3 = wa.y(var3_3, 2);
            }
lbl23:
            // 4 sources

            v0 = a;
        }
        if (v0.y()) {
            var3_3 = wa.y(var3_3, 3);
        }
        return var3_3;
    }

    public nb r(pb a2) {
        nb nb2;
        nb nb3 = nb2 = new nb();
        pb pb2 = a2;
        nb nb4 = nb2;
        pb pb3 = a2;
        nb nb5 = nb2;
        pb pb4 = a2;
        nb nb6 = nb2;
        pb pb5 = a2;
        nb2.r(wm.c);
        nb2.z(pb5.y());
        nb6.r(pb5.r());
        nb6.z(a2.y());
        nb2.h(pb4.r());
        nb5.r(pb4.r());
        nb5.r(a2.r());
        nb2.y(pb3.r());
        nb4.r(pb3.r());
        nb4.r(a2.r());
        nb2.y(pb2.h());
        nb3.r(pb2.z());
        nb3.r((byte[])a2.y().clone());
        nb nb7 = nb2;
        nb7.r(a2.h());
        nb7.y(a2.z());
        return nb7;
    }

    private /* synthetic */ String r(String a2) throws ph {
        if (!c.r(a2)) {
            throw new ph("fileNameInZip is null or empty");
        }
        return a2;
    }

    public pb r(zb a2, boolean a3, int a4, Charset a5, ra a6) throws ph {
        zb zb2;
        Object object;
        ue a7;
        zb zb3;
        pb pb2 = new pb();
        zb zb4 = a2;
        pb pb3 = pb2;
        pb3.r(wm.z);
        pb3.h(u.r(a2, (ra)a6));
        pb2.z(u.r(zb4).r());
        if (zb4.s() && a2.r() == yb.j) {
            zb3 = a2;
            pb pb4 = pb2;
            pb pb5 = pb2;
            pb5.r(cb.m);
            pb5.r(a7.r(a2));
            pb4.y(pb4.z() + 11);
        } else {
            pb2.r(a2.r());
            zb3 = a2;
        }
        if (zb3.s()) {
            if (a2.r() == null || a2.r() == yb.v) {
                throw new ph("Encryption method has to be set when encryptFiles flag is set in zip parameters");
            }
            pb2.y(true);
            pb2.r(a2.r());
        }
        a6 = a7.r(a2.r());
        pb pb6 = pb2;
        Object object2 = a6;
        pb2.r((String)object2);
        pb6.r(a7.r((String)object2, a5));
        pb6.s(a3 ? a4 : 0);
        pb pb7 = pb2;
        if (a2.z() > 0L) {
            pb7.z(c.y(a2.z()));
            object = a6;
        } else {
            pb7.z(c.y(System.currentTimeMillis()));
            object = a6;
        }
        a3 = ya.r((String)object);
        pb pb8 = pb2;
        pb8.h(a3);
        pb8.h(ya.r(a3));
        if (a2.y() && a2.r() == -1L) {
            zb2 = a2;
            pb2.h(0L);
        } else {
            pb2.h(a2.r());
            zb2 = a2;
        }
        if (zb2.s() && a2.r() == yb.m) {
            pb2.y(a2.y());
        }
        pb pb9 = pb2;
        pb pb10 = pb2;
        pb10.r(a7.r(pb10.r(), a2, a5));
        pb9.r(a2.y());
        pb9.y(a2.y());
        return pb2;
    }

    public ue() {
        ue a2;
    }

    private /* synthetic */ byte[] r(boolean a2, zb a3, Charset a4) {
        ue a5;
        byte[] byArray = new byte[2];
        byArray[0] = a5.r(a2, a3);
        if (a4.equals(ga.d)) {
            byArray[1] = wa.y(byArray[1], 3);
        }
        return byArray;
    }
}

