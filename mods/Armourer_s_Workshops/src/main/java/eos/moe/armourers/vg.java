/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.un;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class vg {
    public int qa;
    public int la;
    public int ja;
    public byte[] ma;
    public int ta;
    public byte[] da;
    public static int ca;
    public BufferedImage fa;
    public boolean xa;
    public static int ga;
    public int ua;
    public int oa;
    public int za;
    public int ra;
    public int na;
    public int o;
    public int y;
    public int[] x;
    public ArrayList i;
    public int[] k;
    public boolean p;
    public int n;
    public byte[] q;
    public int[] f;
    public static int u;
    public int d;
    public Rectangle h;
    public int a;
    public int e;
    public BufferedInputStream b;
    public boolean g;
    public int z;
    public boolean t;
    public int w;
    public int r;
    public short[] l;
    public BufferedImage c;
    public int v;
    public static int s;
    public int m;
    public byte[] j;

    public void f() {
        vg a2;
        do {
            vg vg2 = a2;
            vg2.z();
            if (vg2.ma[0] != 1) continue;
            vg vg3 = a2;
            int n2 = vg3.ma[1] & 0xFF;
            vg3.n = (vg3.ma[2] & 0xFF) << 8 | n2;
        } while (a2.qa > 0 && !a2.r());
    }

    public void l() {
        int n2;
        int n3;
        int n4;
        block18: {
            int n5;
            vg a2;
            int n6 = -1;
            vg vg2 = a2;
            n4 = a2.ra * vg2.na;
            if (vg2.q == null || a2.q.length < n4) {
                a2.q = new byte[n4];
            }
            if (a2.l == null) {
                a2.l = new short[s];
            }
            if (a2.j == null) {
                a2.j = new byte[s];
            }
            if (a2.da == null) {
                a2.da = new byte[s + 1];
            }
            int n7 = a2.x();
            int n8 = 1 << n7;
            int n9 = n8 + 1;
            int n10 = n8 + 2;
            int n11 = n6;
            int n12 = n7 + 1;
            int n13 = (1 << n12) - 1;
            int n14 = n5 = 0;
            while (n14 < n8) {
                vg vg3 = a2;
                vg3.l[n5] = 0;
                int n15 = n5++;
                vg3.j[n15] = (byte)n15;
                n14 = n5;
            }
            int n16 = 0;
            int n17 = 0;
            int n18 = 0;
            int n19 = 0;
            int n20 = 0;
            int n21 = 0;
            int n22 = 0;
            int n23 = n3 = 0;
            while (n23 < n4) {
                if (n18 == 0) {
                    if (n21 < n12) {
                        if (n20 == 0) {
                            n20 = a2.z();
                            if (n20 <= 0) {
                                n2 = n17;
                                break block18;
                            }
                            n16 = 0;
                        }
                        int n24 = n21;
                        n21 += 8;
                        --n20;
                        n22 += (a2.ma[n16] & 0xFF) << n24;
                        ++n16;
                        n23 = n3;
                        continue;
                    }
                    n5 = n22 & n13;
                    n22 >>= n12;
                    n21 -= n12;
                    if (n5 > n10) break;
                    if (n5 == n9) {
                        n2 = n17;
                        break block18;
                    }
                    if (n5 == n8) {
                        n12 = n7 + 1;
                        n13 = (1 << n12) - 1;
                        n10 = n8 + 2;
                        n11 = n6;
                        n23 = n3;
                        continue;
                    }
                    if (n11 == n6) {
                        a2.da[n18++] = a2.j[n5];
                        n11 = n5;
                        n19 = n5;
                        n23 = n3;
                        continue;
                    }
                    int n25 = n5;
                    if (n5 == n10) {
                        a2.da[n18++] = (byte)n19;
                        n5 = n11;
                    }
                    int n26 = n5;
                    while (n26 > n8) {
                        vg vg4 = a2;
                        a2.da[++n18] = vg4.j[n5];
                        n26 = vg4.l[n5];
                    }
                    n19 = a2.j[n5] & 0xFF;
                    if (n10 >= s) {
                        a2.da[n18++] = (byte)n19;
                        n23 = n3;
                        continue;
                    }
                    vg vg5 = a2;
                    vg5.da[n18++] = (byte)n19;
                    vg5.l[n10] = (short)n11;
                    vg5.j[n10++] = (byte)n19;
                    if ((n10 & n13) == 0 && n10 < s) {
                        ++n12;
                        n13 += n10;
                    }
                    n11 = n25;
                }
                a2.q[n17++] = a2.da[--n18];
                n23 = ++n3;
            }
            n2 = n17;
        }
        int n27 = n3 = n2;
        while (n27 < n4) {
            a2.q[n3++] = 0;
            n27 = n3;
        }
    }

    public Dimension r() {
        vg a2;
        vg vg2 = a2;
        return new Dimension(vg2.a, vg2.m);
    }

    public int[] r(int a2) {
        int n2;
        int n3 = 3 * a2;
        int[] nArray = null;
        byte[] byArray = new byte[n3];
        int n4 = 0;
        try {
            vg a3;
            n2 = n4 = a3.b.read(byArray);
        }
        catch (IOException iOException) {
            n2 = n4;
        }
        if (n2 < n3) {
            a3.z = ca;
            return nArray;
        }
        nArray = new int[256];
        int n5 = 0;
        n3 = 0;
        int n6 = n5;
        while (n6 < a2) {
            int n7 = byArray[n3] & 0xFF;
            n4 = n7;
            int n8 = byArray[++n3] & 0xFF;
            int n9 = n8;
            int n10 = byArray[++n3] & 0xFF;
            ++n3;
            int n11 = n10;
            nArray[n5++] = 0xFF000000 | n4 << 16 | n9 << 8 | n11;
            n6 = n5;
        }
        return nArray;
    }

    public boolean r() {
        vg a2;
        return a2.z != u;
    }

    public void n() {
        vg a2;
        vg vg2 = a2;
        vg2.ta = vg2.r();
        vg2.ja = vg2.r();
        vg2.ra = vg2.r();
        vg2.na = vg2.r();
        int n2 = vg2.x();
        vg2.t = (n2 & 0x80) != 0;
        a2.g = (n2 & 0x40) != 0;
        a2.e = 2 << (n2 & 7);
        vg vg3 = a2;
        if (a2.t) {
            vg vg4 = a2;
            vg3.x = vg4.r(vg4.e);
            a2.k = a2.x;
        } else {
            vg3.k = a2.f;
            vg vg5 = a2;
            if (vg5.w == vg5.za) {
                a2.o = 0;
            }
        }
        n2 = 0;
        if (a2.xa) {
            vg vg6 = a2;
            n2 = a2.k[vg6.za];
            vg6.k[a2.za] = 0;
        }
        if (a2.k == null) {
            a2.z = ca;
        }
        if (a2.r()) {
            return;
        }
        vg vg7 = a2;
        vg7.l();
        vg7.v();
        if (vg7.r()) {
            return;
        }
        vg vg8 = a2;
        ++vg8.y;
        vg vg9 = a2;
        vg8.c = new BufferedImage(vg9.a, vg9.m, 3);
        a2.x();
        vg vg10 = a2;
        vg8.i.add(new un(vg10.c, vg10.v));
        if (vg8.xa) {
            vg vg11 = a2;
            vg11.k[vg11.za] = n2;
        }
        a2.r();
    }

    public void v() {
        vg a2;
        vg vg2;
        do {
            vg2 = a2;
            vg2.z();
        } while (vg2.qa > 0 && !a2.r());
    }

    /*
     * Unable to fully structure code
     */
    public int r(BufferedInputStream a) {
        block3: {
            block2: {
                a.w();
                if (a == null) break block2;
                v0 = a;
                v0.b = a;
                v0.z();
                if (v0.r()) break block3;
                v1 = a;
                v1.h();
                if (v1.y >= 0) break block3;
                v2 = a;
                a.z = vg.ca;
                ** GOTO lbl18
            }
            a.z = vg.ga;
        }
        try {
            v2 = a;
lbl18:
            // 2 sources

            v2.close();
            v3 = a;
        }
        catch (IOException a) {
            v3 = a;
        }
        return v3.z;
    }

    public int x() {
        int n2 = 0;
        try {
            vg a2;
            n2 = a2.b.read();
            return n2;
        }
        catch (IOException iOException) {
            a2.z = ca;
            return n2;
        }
    }

    public vg() {
        vg a2;
        vg vg2 = a2;
        vg vg3 = a2;
        vg vg4 = a2;
        a2.n = 1;
        vg4.ma = new byte[256];
        vg4.qa = 0;
        vg3.oa = 0;
        vg3.r = 0;
        vg2.xa = 0;
        vg2.v = 0;
    }

    public int h() {
        vg a2;
        return a2.y;
    }

    public void w() {
        vg a2;
        vg vg2 = a2;
        vg vg3 = a2;
        vg3.z = u;
        vg3.y = 0;
        vg vg4 = a2;
        vg3.i = new ArrayList();
        vg2.f = null;
        vg2.x = null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public int z() {
        int n2;
        vg a2;
        vg vg2 = a2;
        vg2.qa = vg2.x();
        if (vg2.qa > 0) {
            try {
                int n3 = 0;
                for (n2 = 0; n2 < a2.qa; n2 += n3) {
                    vg vg3 = a2;
                    int n4 = n2;
                    n3 = vg3.b.read(vg3.ma, n4, a2.qa - n4);
                    if (n3 != -1) {
                        continue;
                    }
                    break;
                }
            }
            catch (IOException iOException) {
                // empty catch block
            }
            if (n2 < a2.qa) {
                a2.z = ca;
            }
        }
        return n2;
    }

    public void s() {
        vg a2;
        vg vg2 = a2;
        vg2.a = vg2.r();
        vg2.m = vg2.r();
        int n2 = vg2.x();
        vg2.p = (n2 & 0x80) != 0;
        vg vg3 = a2;
        vg3.la = 2 << (n2 & 7);
        vg3.w = vg3.x();
        vg3.d = vg3.x();
    }

    static {
        u = 0;
        ca = 1;
        ga = 2;
        s = 4096;
    }

    public int y() {
        vg a2;
        return a2.n;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public int r(String a2) {
        vg vg2;
        vg a3;
        a3.z = u;
        try {
            vg vg3;
            a2 = a2.trim().toLowerCase();
            if (a2.contains("file:") || a2.indexOf(":/") > 0) {
                URL uRL = new URL(a2);
                vg3 = a3;
                a3.b = new BufferedInputStream(a3.r(uRL));
            } else {
                vg3 = a3;
                a3.b = new BufferedInputStream(new FileInputStream(a2));
            }
            vg vg4 = a3;
            vg3.z = vg4.r(vg4.b);
            vg2 = a3;
            return vg2.z;
        }
        catch (IOException iOException) {
            vg2 = a3;
            a3.z = ga;
        }
        return vg2.z;
    }

    /*
     * Unable to fully structure code
     */
    public void x() {
        block17: {
            var1_1 = ((DataBufferInt)a.c.getRaster().getDataBuffer()).getData();
            if (a.r <= 0) break block17;
            if (a.r != 3) ** GOTO lbl11
            var2_2 = a.y - 2;
            if (var2_2 > 0) {
                v0 = a;
                v1 = v0;
                v0.fa = v0.r(var2_2 - 1);
            } else {
                a.fa = null;
lbl11:
                // 2 sources

                v1 = a;
            }
            if (v1.fa != null) {
                var2_3 = ((DataBufferInt)a.fa.getRaster().getDataBuffer()).getData();
                v2 = a;
                System.arraycopy(var2_3, 0, var1_1, 0, v2.a * v2.m);
                if (a.r == 2) {
                    v3 = a;
                    var3_4 = v3.c.createGraphics();
                    var4_6 = null;
                    if (v3.xa) {
                        var4_6 = new Color(0, 0, 0, 0);
                        v4 = var3_4;
                    } else {
                        var4_6 = new Color(a.ua);
                        v4 = var3_4;
                    }
                    v4.setColor(var4_6);
                    v5 = var3_4;
                    v5.setComposite(AlphaComposite.Src);
                    v5.fill(a.h);
                    v5.dispose();
                }
            }
        }
        var2_2 = 1;
        var3_5 = 8;
        var4_7 = 0;
        v6 = var5_8 = 0;
        while (v6 < a.na) {
            block18: {
                var6_9 = var5_8;
                if (!a.g) break block18;
                if (var4_7 < a.na) ** GOTO lbl-1000
                switch (++var2_2) lbl-1000:
                // 2 sources

                {
                    case 2: {
                        if (false) ** GOTO lbl-1000
                        v7 = var4_7 = 4;
                        break;
                    }
                    case 3: {
                        var4_7 = 2;
                        var3_5 = 4;
                        v7 = var4_7;
                        break;
                    }
                    case 4: {
                        var4_7 = 1;
                        var3_5 = 2;
                    }
                    default: lbl-1000:
                    // 2 sources

                    {
                        v7 = var4_7;
                    }
                }
                var6_9 = v7;
                var4_7 += var3_5;
            }
            if ((var6_9 += a.ja) < a.m) {
                if ((var6_9 *= a.a) + a.a < (var8_11 = (var7_10 = var6_9 + a.ta) + a.ra)) {
                    var8_11 = var6_9 + a.a;
                }
                var6_9 = var5_8 * a.ra;
                v8 = var7_10;
                while (v8 < var8_11) {
                    v9 = a;
                    v10 = v9.q[var6_9] & 255;
                    ++var6_9;
                    var9_12 = v10;
                    if ((var9_12 = v9.k[var9_12]) != 0) {
                        var1_1[var7_10] = var9_12;
                    }
                    v8 = ++var7_10;
                }
            }
            v6 = ++var5_8;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void h() {
        boolean bl = false;
        block10: while (!bl) {
            vg a2;
            if (a2.r()) return;
            int n2 = a2.x();
            switch (n2) {
                case 44: {
                    a2.n();
                    continue block10;
                }
                case 33: {
                    n2 = a2.x();
                    switch (n2) {
                        case 249: {
                            a2.y();
                            continue block10;
                        }
                        case 255: {
                            a2.z();
                            String string = "";
                            int n3 = 0;
                            int n4 = n3;
                            while (n4 < 11) {
                                char c2 = (char)a2.ma[n3];
                                string = new StringBuilder().insert(0, string).append(c2).toString();
                                n4 = ++n3;
                            }
                            vg vg2 = a2;
                            if (string.equals("NETSCAPE2.0")) {
                                vg2.f();
                                continue block10;
                            }
                            vg2.v();
                            continue block10;
                        }
                    }
                    a2.v();
                    continue block10;
                }
                case 59: {
                    return;
                }
                case 0: {
                    continue block10;
                }
            }
            a2.z = ca;
        }
    }

    public void z() {
        vg a2;
        int n2;
        String string = "";
        int n3 = n2 = 0;
        while (n3 < 6) {
            string = new StringBuilder().insert(0, string).append((char)a2.x()).toString();
            n3 = ++n2;
        }
        if (!string.startsWith("GIF")) {
            a2.z = ca;
            return;
        }
        vg vg2 = a2;
        vg2.s();
        if (vg2.p && !a2.r()) {
            vg vg3 = a2;
            vg3.f = vg3.r(vg3.la);
            vg3.o = vg3.f[a2.w];
        }
    }

    public BufferedImage r() {
        vg a2;
        return a2.r(0);
    }

    public int r() {
        vg a2;
        return a2.x() | a2.x() << 8;
    }

    public void y() {
        vg a2;
        vg vg2 = a2;
        a2.x();
        int n2 = vg2.x();
        vg2.oa = (n2 & 0x1C) >> 2;
        if (vg2.oa == 0) {
            a2.oa = 1;
        }
        a2.xa = (n2 & 1) != 0;
        vg vg3 = a2;
        vg3.v = vg3.r() * 10;
        vg3.za = vg3.x();
        vg3.x();
    }

    private /* synthetic */ InputStream r(URL a2) throws IOException {
        int n2;
        a2 = ((URL)a2).openStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] byArray = new byte[1024];
        Object object = a2;
        while ((n2 = ((InputStream)object).read(byArray)) > -1) {
            object = a2;
            byteArrayOutputStream.write(byArray, 0, n2);
        }
        byteArrayOutputStream.flush();
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    public void r() {
        vg a2;
        vg vg2 = a2;
        vg2.r = vg2.oa;
        vg vg3 = a2;
        vg vg4 = a2;
        a2.h = new Rectangle(a2.ta, vg4.ja, vg4.ra, a2.na);
        vg2.fa = vg2.c;
        a2.ua = a2.o;
        boolean bl = false;
        bl = false;
        bl = false;
        a2.x = null;
    }

    public BufferedImage r(int a2) {
        vg a3;
        BufferedImage bufferedImage = null;
        if (a2 >= 0 && a2 < a3.y) {
            bufferedImage = ((un)a3.i.get((int)a2)).j;
        }
        return bufferedImage;
    }

    /*
     * Unable to fully structure code
     */
    public int r(InputStream a) {
        block4: {
            block3: {
                a.w();
                if (a == null) break block3;
                if (!(a instanceof BufferedInputStream)) {
                    a = new BufferedInputStream(a);
                }
                a.b = (BufferedInputStream)a;
                v0 = a;
                v0.z();
                if (v0.r()) break block4;
                v1 = a;
                v1.h();
                if (v1.y >= 0) break block4;
                v2 = a;
                a.z = vg.ca;
                ** GOTO lbl20
            }
            a.z = vg.ga;
        }
        try {
            v2 = a;
lbl20:
            // 2 sources

            v2.close();
            v3 = a;
        }
        catch (IOException a) {
            v3 = a;
        }
        return v3.z;
    }

    public int r(int a2) {
        vg a3;
        a3.v = -1;
        if (a2 >= 0 && a2 < a3.y) {
            a3.v = ((un)a3.i.get((int)a2)).m;
        }
        return a3.v;
    }
}

