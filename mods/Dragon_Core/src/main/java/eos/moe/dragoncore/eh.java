/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.gj;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
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
public class eh {
    public static int za = 0;
    public static int pa = 1;
    public static int da = 2;
    public BufferedInputStream qa;
    public int wa;
    public int fa;
    public int oa;
    public boolean ka;
    public int ua;
    public int xa = 1;
    public int[] ta;
    public int[] aa;
    public int[] ba;
    public int ma;
    public int h;
    public int f;
    public int d;
    public boolean p;
    public boolean u;
    public int w;
    public int a;
    public int e;
    public int n;
    public int j;
    public Rectangle i;
    public BufferedImage l;
    public BufferedImage z;
    public byte[] s = new byte[256];
    public int g = 0;
    public int t = 0;
    public int r = 0;
    public boolean x = false;
    public int v = 0;
    public int m;
    public static int c = 4096;
    public short[] q;
    public byte[] b;
    public byte[] o;
    public byte[] y;
    public ArrayList k;
    public int ALLATORIxDEMO;

    public eh() {
        eh a2;
    }

    public int ALLATORIxDEMO(int a2) {
        eh a3;
        a3.v = -1;
        if (a2 >= 0 && a2 < a3.ALLATORIxDEMO) {
            a3.v = ((gj)a3.k.get((int)a2)).ALLATORIxDEMO;
        }
        return a3.v;
    }

    public int d() {
        eh a2;
        return a2.ALLATORIxDEMO;
    }

    public BufferedImage ALLATORIxDEMO() {
        eh a2;
        return a2.ALLATORIxDEMO(0);
    }

    public int x() {
        eh a2;
        return a2.xa;
    }

    public void y() {
        int a2;
        eh a3;
        int[] a4 = ((DataBufferInt)a3.l.getRaster().getDataBuffer()).getData();
        if (a3.r > 0) {
            if (a3.r == 3) {
                a2 = a3.ALLATORIxDEMO - 2;
                a3.z = a2 > 0 ? a3.ALLATORIxDEMO(a2 - 1) : null;
            }
            if (a3.z != null) {
                int[] a5 = ((DataBufferInt)a3.z.getRaster().getDataBuffer()).getData();
                System.arraycopy(a5, 0, a4, 0, a3.fa * a3.oa);
                if (a3.r == 2) {
                    Graphics2D a6 = a3.l.createGraphics();
                    Color a7 = null;
                    a7 = a3.x ? new Color(0, 0, 0, 0) : new Color(a3.f);
                    a6.setColor(a7);
                    a6.setComposite(AlphaComposite.Src);
                    a6.fill(a3.i);
                    a6.dispose();
                }
            }
        }
        a2 = 1;
        int a8 = 8;
        int a9 = 0;
        for (int a10 = 0; a10 < a3.j; ++a10) {
            int a11 = a10;
            if (a3.u) {
                if (a9 >= a3.j) {
                    switch (++a2) {
                        case 2: {
                            a9 = 4;
                            break;
                        }
                        case 3: {
                            a9 = 2;
                            a8 = 4;
                            break;
                        }
                        case 4: {
                            a9 = 1;
                            a8 = 2;
                        }
                    }
                }
                a11 = a9;
                a9 += a8;
            }
            if ((a11 += a3.e) >= a3.oa) continue;
            int a12 = a11 * a3.fa;
            int a13 = a12 + a3.a;
            int a14 = a13 + a3.n;
            if (a12 + a3.fa < a14) {
                a14 = a12 + a3.fa;
            }
            int a15 = a10 * a3.n;
            while (a13 < a14) {
                int a16;
                int a17;
                if ((a17 = a3.ba[a16 = a3.y[a15++] & 0xFF]) != 0) {
                    a4[a13] = a17;
                }
                ++a13;
            }
        }
    }

    public BufferedImage ALLATORIxDEMO(int a2) {
        eh a3;
        BufferedImage a4 = null;
        if (a2 >= 0 && a2 < a3.ALLATORIxDEMO) {
            a4 = ((gj)a3.k.get((int)a2)).k;
        }
        return a4;
    }

    public Dimension ALLATORIxDEMO() {
        eh a2;
        return new Dimension(a2.fa, a2.oa);
    }

    public int ALLATORIxDEMO(BufferedInputStream a2) {
        eh a3;
        a3.s();
        if (a2 != null) {
            a3.qa = a2;
            a3.k();
            if (!a3.ALLATORIxDEMO()) {
                a3.w();
                if (a3.ALLATORIxDEMO < 0) {
                    a3.wa = pa;
                }
            }
        } else {
            a3.wa = da;
        }
        try {
            a2.close();
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return a3.wa;
    }

    public int ALLATORIxDEMO(InputStream a2) {
        eh a3;
        a3.s();
        if (a2 != null) {
            if (!(a2 instanceof BufferedInputStream)) {
                a2 = new BufferedInputStream(a2);
            }
            a3.qa = (BufferedInputStream)a2;
            a3.k();
            if (!a3.ALLATORIxDEMO()) {
                a3.w();
                if (a3.ALLATORIxDEMO < 0) {
                    a3.wa = pa;
                }
            }
        } else {
            a3.wa = da;
        }
        try {
            a2.close();
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return a3.wa;
    }

    public int ALLATORIxDEMO(String a2) {
        eh a3;
        a3.wa = za;
        try {
            a2 = a2.trim().toLowerCase();
            if (a2.contains("file:") || a2.indexOf(":/") > 0) {
                URL a4 = new URL(a2);
                a3.qa = new BufferedInputStream(a3.ALLATORIxDEMO(a4));
            } else {
                a3.qa = new BufferedInputStream(new FileInputStream(a2));
            }
            a3.wa = a3.ALLATORIxDEMO(a3.qa);
        }
        catch (IOException a5) {
            a3.wa = da;
        }
        return a3.wa;
    }

    private /* synthetic */ InputStream ALLATORIxDEMO(URL a2) throws IOException {
        int a3;
        InputStream a4 = a2.openStream();
        ByteArrayOutputStream a5 = new ByteArrayOutputStream();
        byte[] a6 = new byte[1024];
        while ((a3 = a4.read(a6)) > -1) {
            a5.write(a6, 0, a3);
        }
        a5.flush();
        return new ByteArrayInputStream(a5.toByteArray());
    }

    public void h() {
        int a2;
        eh a3;
        int a4 = -1;
        int a5 = a3.n * a3.j;
        if (a3.y == null || a3.y.length < a5) {
            a3.y = new byte[a5];
        }
        if (a3.q == null) {
            a3.q = new short[c];
        }
        if (a3.b == null) {
            a3.b = new byte[c];
        }
        if (a3.o == null) {
            a3.o = new byte[c + 1];
        }
        int a6 = a3.f();
        int a7 = 1 << a6;
        int a8 = a7 + 1;
        int a9 = a7 + 2;
        int a10 = a4;
        int a11 = a6 + 1;
        int a12 = (1 << a11) - 1;
        for (a2 = 0; a2 < a7; ++a2) {
            a3.q[a2] = 0;
            a3.b[a2] = (byte)a2;
        }
        int a13 = 0;
        int a14 = 0;
        int a15 = 0;
        int a16 = 0;
        int a17 = 0;
        int a18 = 0;
        int a19 = 0;
        int a20 = 0;
        while (a20 < a5) {
            if (a15 == 0) {
                if (a18 < a11) {
                    if (a17 == 0) {
                        a17 = a3.c();
                        if (a17 <= 0) break;
                        a13 = 0;
                    }
                    a19 += (a3.s[a13] & 0xFF) << a18;
                    a18 += 8;
                    ++a13;
                    --a17;
                    continue;
                }
                a2 = a19 & a12;
                a19 >>= a11;
                a18 -= a11;
                if (a2 > a9 || a2 == a8) break;
                if (a2 == a7) {
                    a11 = a6 + 1;
                    a12 = (1 << a11) - 1;
                    a9 = a7 + 2;
                    a10 = a4;
                    continue;
                }
                if (a10 == a4) {
                    a3.o[a15++] = a3.b[a2];
                    a10 = a2;
                    a16 = a2;
                    continue;
                }
                int a21 = a2;
                if (a2 == a9) {
                    a3.o[a15++] = (byte)a16;
                    a2 = a10;
                }
                while (a2 > a7) {
                    a3.o[a15++] = a3.b[a2];
                    a2 = a3.q[a2];
                }
                a16 = a3.b[a2] & 0xFF;
                if (a9 >= c) {
                    a3.o[a15++] = (byte)a16;
                    continue;
                }
                a3.o[a15++] = (byte)a16;
                a3.q[a9] = (short)a10;
                a3.b[a9] = (byte)a16;
                if ((++a9 & a12) == 0 && a9 < c) {
                    ++a11;
                    a12 += a9;
                }
                a10 = a21;
            }
            a3.y[a14++] = a3.o[--a15];
            ++a20;
        }
        for (a20 = a14; a20 < a5; ++a20) {
            a3.y[a20] = 0;
        }
    }

    public boolean ALLATORIxDEMO() {
        eh a2;
        return a2.wa != za;
    }

    public void s() {
        a.wa = za;
        a.ALLATORIxDEMO = 0;
        a.k = new ArrayList();
        a.ta = null;
        a.aa = null;
    }

    public int f() {
        int a2 = 0;
        try {
            eh a3;
            a2 = a3.qa.read();
        }
        catch (IOException a4) {
            a3.wa = pa;
        }
        return a2;
    }

    public int c() {
        int a2;
        eh a3;
        a3.g = a3.f();
        if (a3.g > 0) {
            try {
                int a4 = 0;
                for (a2 = 0; a2 < a3.g && (a4 = a3.qa.read(a3.s, a2, a3.g - a2)) != -1; a2 += a4) {
                }
            }
            catch (IOException iOException) {
                // empty catch block
            }
            if (a2 < a3.g) {
                a3.wa = pa;
            }
        }
        return a2;
    }

    public int[] ALLATORIxDEMO(int a2) {
        int a3 = 3 * a2;
        int[] a4 = null;
        byte[] a5 = new byte[a3];
        int a6 = 0;
        try {
            eh a7;
            a6 = a7.qa.read(a5);
        }
        catch (IOException iOException) {
            // empty catch block
        }
        if (a6 < a3) {
            a7.wa = pa;
        } else {
            a4 = new int[256];
            int a8 = 0;
            int a9 = 0;
            while (a8 < a2) {
                int a10 = a5[a9++] & 0xFF;
                int a11 = a5[a9++] & 0xFF;
                int a12 = a5[a9++] & 0xFF;
                a4[a8++] = 0xFF000000 | a10 << 16 | a11 << 8 | a12;
            }
        }
        return a4;
    }

    public void w() {
        eh a2;
        boolean a3 = false;
        block10: while (!a3 && !a2.ALLATORIxDEMO()) {
            int a4 = a2.f();
            switch (a4) {
                case 44: {
                    a2.d();
                    continue block10;
                }
                case 33: {
                    a4 = a2.f();
                    switch (a4) {
                        case 249: {
                            a2.z();
                            continue block10;
                        }
                        case 255: {
                            a2.c();
                            String a5 = "";
                            for (int a6 = 0; a6 < 11; ++a6) {
                                a5 = a5 + (char)a2.s[a6];
                            }
                            if (a5.equals("NETSCAPE2.0")) {
                                a2.f();
                                continue block10;
                            }
                            a2.ALLATORIxDEMO();
                            continue block10;
                        }
                    }
                    a2.ALLATORIxDEMO();
                    continue block10;
                }
                case 59: {
                    a3 = true;
                    continue block10;
                }
                case 0: {
                    continue block10;
                }
            }
            a2.wa = pa;
        }
    }

    public void z() {
        eh a2;
        a2.f();
        int a3 = a2.f();
        a2.t = (a3 & 0x1C) >> 2;
        if (a2.t == 0) {
            a2.t = 1;
        }
        a2.x = (a3 & 1) != 0;
        a2.v = a2.ALLATORIxDEMO() * 10;
        a2.m = a2.f();
        a2.f();
    }

    public void k() {
        eh a2;
        String a3 = "";
        for (int a4 = 0; a4 < 6; ++a4) {
            a3 = a3 + (char)a2.f();
        }
        if (!a3.startsWith("GIF")) {
            a2.wa = pa;
            return;
        }
        a2.x();
        if (a2.ka && !a2.ALLATORIxDEMO()) {
            a2.ta = a2.ALLATORIxDEMO(a2.ua);
            a2.h = a2.ta[a2.ma];
        }
    }

    public void d() {
        eh a2;
        a2.a = a2.ALLATORIxDEMO();
        a2.e = a2.ALLATORIxDEMO();
        a2.n = a2.ALLATORIxDEMO();
        a2.j = a2.ALLATORIxDEMO();
        int a3 = a2.f();
        a2.p = (a3 & 0x80) != 0;
        a2.u = (a3 & 0x40) != 0;
        a2.w = 2 << (a3 & 7);
        if (a2.p) {
            a2.aa = a2.ALLATORIxDEMO(a2.w);
            a2.ba = a2.aa;
        } else {
            a2.ba = a2.ta;
            if (a2.ma == a2.m) {
                a2.h = 0;
            }
        }
        int a4 = 0;
        if (a2.x) {
            a4 = a2.ba[a2.m];
            a2.ba[a2.m] = 0;
        }
        if (a2.ba == null) {
            a2.wa = pa;
        }
        if (a2.ALLATORIxDEMO()) {
            return;
        }
        a2.h();
        a2.ALLATORIxDEMO();
        if (a2.ALLATORIxDEMO()) {
            return;
        }
        ++a2.ALLATORIxDEMO;
        a2.l = new BufferedImage(a2.fa, a2.oa, 3);
        a2.y();
        a2.k.add(new gj(a2.l, a2.v));
        if (a2.x) {
            a2.ba[a2.m] = a4;
        }
        a2.c();
    }

    public void x() {
        eh a2;
        a2.fa = a2.ALLATORIxDEMO();
        a2.oa = a2.ALLATORIxDEMO();
        int a3 = a2.f();
        a2.ka = (a3 & 0x80) != 0;
        a2.ua = 2 << (a3 & 7);
        a2.ma = a2.f();
        a2.d = a2.f();
    }

    public void f() {
        eh a2;
        do {
            a2.c();
            if (a2.s[0] != 1) continue;
            int a3 = a2.s[1] & 0xFF;
            int a4 = a2.s[2] & 0xFF;
            a2.xa = a4 << 8 | a3;
        } while (a2.g > 0 && !a2.ALLATORIxDEMO());
    }

    public int ALLATORIxDEMO() {
        eh a2;
        return a2.f() | a2.f() << 8;
    }

    public void c() {
        eh a2;
        a2.r = a2.t;
        a2.i = new Rectangle(a2.a, a2.e, a2.n, a2.j);
        a2.z = a2.l;
        a2.f = a2.h;
        boolean a3 = false;
        boolean a4 = false;
        boolean a5 = false;
        a2.aa = null;
    }

    public void ALLATORIxDEMO() {
        eh a2;
        do {
            a2.c();
        } while (a2.g > 0 && !a2.ALLATORIxDEMO());
    }
}

