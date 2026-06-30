/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.eo;
import eos.moe.armourers.lo;
import eos.moe.armourers.ym;
import java.util.ArrayList;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class te {
    public static ym i;
    public static ym k;
    public static ym p;
    public static ym n;
    public static ym q;
    public static ym f;
    public static ym u;
    private static int d;
    private static ym[] h;
    public static ym a;
    public static ym e;
    public static ym b;
    private static ArrayList<ym> g;
    public static ym z;
    public static ym t;
    public static ym w;
    public static ym r;
    public static ym l;
    public static ym c;
    public static ym v;
    public static ym s;
    public static ym m;
    public static ym j;

    static {
        f = new ym(0, 9, "none");
        u = new ym(1, 1, true, "dye_1");
        n = new ym(2, 2, true, "dye_2");
        b = new ym(3, 3, true, "dye_3");
        a = new ym(4, 4, true, "dye_4");
        w = new ym(5, 5, true, "dye_5");
        m = new ym(6, 6, true, "dye_6");
        j = new ym(7, 7, true, "dye_7");
        l = new ym(8, 8, true, "dye_8");
        s = new ym(104, 14, true, "rainbow").r(1.0f, 0.0f);
        p = new ym(105, 15, "pulse_1").r(2.0f, 0.0f);
        v = new ym(106, 16, "pulse_2").r(3.0f, 0.0f);
        e = new ym(107, 17, "texture");
        q = new ym(108, 18, "flicker_1").r(4.0f, 0.0f);
        t = new ym(109, 19, "flicker_2").r(5.0f, 0.0f);
        r = new ym(253, 10, true, "skin").r(eo.s);
        k = new ym(254, 11, true, "hair").r(eo.v);
        i = new ym(251, 12, true, "eye").r(eo.j);
        z = new ym(252, 13, true, "misc").r(eo.c);
        c = new ym(255, 0, "normal");
        h = new ym[256];
        g = new ArrayList();
        d = 0;
    }

    private static /* synthetic */ void r(ym a2) {
        te.h[a2.z()] = a2;
        g.add(a2);
        if (a2.r()) {
            a2.r(d);
            ++d;
        }
    }

    public static ym r(String a2) {
        int n2;
        ym[] ymArray = h;
        int n3 = h.length;
        int n4 = n2 = 0;
        while (n4 < n3) {
            ym ym2 = ymArray[n2];
            if (ym2.y().equals(a2)) {
                return ym2;
            }
            n4 = ++n2;
        }
        return c;
    }

    public static ym y(int a2) {
        return te.r(0xFF & a2 >> 24);
    }

    public static ym r(int a2) {
        return h[a2];
    }

    public te() {
        te a2;
    }

    public static int r() {
        return d;
    }

    public static ym r(byte a2) {
        return te.r(a2 & 0xFF);
    }

    public static ArrayList<ym> r() {
        return g;
    }

    public static int r(ym a2, int a3) {
        return lo.r(a3, 0, a2.z());
    }

    public static void r() {
        int n2;
        int n3 = n2 = 0;
        while (n3 < h.length) {
            te.h[n2++] = c;
            n3 = n2;
        }
        te.r(c);
        te.r(u);
        te.r(n);
        te.r(b);
        te.r(a);
        te.r(w);
        te.r(m);
        te.r(j);
        te.r(l);
        te.r(e);
        te.r(s);
        te.r(p);
        te.r(v);
        te.r(q);
        te.r(t);
        te.r(r);
        te.r(k);
        te.r(i);
        te.r(z);
        te.r(f);
    }
}

