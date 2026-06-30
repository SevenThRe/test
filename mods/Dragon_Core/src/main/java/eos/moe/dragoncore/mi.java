/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ac;
import eos.moe.dragoncore.cc;
import eos.moe.dragoncore.cf;
import eos.moe.dragoncore.dc;
import eos.moe.dragoncore.ec;
import eos.moe.dragoncore.ei;
import eos.moe.dragoncore.em;
import eos.moe.dragoncore.fc;
import eos.moe.dragoncore.gc;
import eos.moe.dragoncore.hc;
import eos.moe.dragoncore.jc;
import eos.moe.dragoncore.kc;
import eos.moe.dragoncore.nc;
import eos.moe.dragoncore.oc;
import eos.moe.dragoncore.og;
import eos.moe.dragoncore.pc;
import eos.moe.dragoncore.rc;
import eos.moe.dragoncore.ta;
import eos.moe.dragoncore.tc;
import eos.moe.dragoncore.ua;
import eos.moe.dragoncore.wc;
import eos.moe.dragoncore.xb;
import eos.moe.dragoncore.xc;
import eos.moe.dragoncore.yk;
import eos.moe.dragoncore.zc;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class mi {
    private dc y;
    private ua k = new ua();
    private byte[] ALLATORIxDEMO = new byte[4];

    public mi() {
        mi a2;
    }

    public dc ALLATORIxDEMO(RandomAccessFile a2, Charset a3) throws IOException {
        mi a4;
        if (a2.length() < 22L) {
            throw new yk("Zip file size less than minimum expected zip file size. Probably not a zip file or a corrupted zip file");
        }
        a4.y = new dc();
        try {
            a4.y.ALLATORIxDEMO(a4.ALLATORIxDEMO(a2, a4.k, a3));
        }
        catch (yk a5) {
            throw a5;
        }
        catch (IOException a6) {
            throw new yk("Zip headers not found. Probably not a zip file or a corrupted zip file", a6);
        }
        if (a4.y.ALLATORIxDEMO().c() == 0) {
            return a4.y;
        }
        a4.y.ALLATORIxDEMO(a4.ALLATORIxDEMO(a2, a4.k, a4.y.ALLATORIxDEMO().ALLATORIxDEMO()));
        if (a4.y.c()) {
            a4.y.ALLATORIxDEMO(a4.ALLATORIxDEMO(a2, a4.k));
            if (a4.y.ALLATORIxDEMO() != null && a4.y.ALLATORIxDEMO().c() > 0) {
                a4.y.f(true);
            } else {
                a4.y.f(false);
            }
        }
        a4.y.ALLATORIxDEMO(a4.ALLATORIxDEMO(a2, a4.k, a3));
        return a4.y;
    }

    private /* synthetic */ pc ALLATORIxDEMO(RandomAccessFile a2, ua a3, Charset a4) throws IOException {
        mi a5;
        long a6 = a2.length() - 22L;
        a5.ALLATORIxDEMO(a2, a6);
        int a7 = a3.c(a2);
        if ((long)a7 != ei.x.ALLATORIxDEMO()) {
            a6 = a5.ALLATORIxDEMO(a2);
            a2.seek(a6 + 4L);
        }
        pc a8 = new pc();
        a8.ALLATORIxDEMO(ei.x);
        a8.d(a3.ALLATORIxDEMO(a2));
        a8.x(a3.ALLATORIxDEMO(a2));
        a8.f(a3.ALLATORIxDEMO(a2));
        a8.c(a3.ALLATORIxDEMO(a2));
        a8.ALLATORIxDEMO(a3.c(a2));
        a8.ALLATORIxDEMO(a6);
        a2.readFully(a5.ALLATORIxDEMO);
        a8.c(a3.ALLATORIxDEMO(a5.ALLATORIxDEMO, 0));
        int a9 = a3.ALLATORIxDEMO(a2);
        a8.ALLATORIxDEMO(a5.ALLATORIxDEMO(a2, a9, a4));
        a5.y.f(a8.d() > 0);
        return a8;
    }

    private /* synthetic */ cc ALLATORIxDEMO(RandomAccessFile a2, ua a3, Charset a4) throws IOException {
        Object a5;
        mi a6;
        cc a7 = new cc();
        ArrayList<ec> a8 = new ArrayList<ec>();
        long a9 = cf.ALLATORIxDEMO(a6.y);
        long a10 = a6.ALLATORIxDEMO(a6.y);
        a2.seek(a9);
        byte[] a11 = new byte[2];
        byte[] a12 = new byte[4];
        int a13 = 0;
        while ((long)a13 < a10) {
            byte[] a14;
            a5 = new ec();
            if ((long)a3.c(a2) != ei.r.ALLATORIxDEMO()) {
                throw new yk("Expected central directory entry not found (#" + (a13 + 1) + ")");
            }
            ((ac)a5).ALLATORIxDEMO(ei.r);
            ((ec)a5).k(a3.ALLATORIxDEMO(a2));
            ((fc)a5).f(a3.ALLATORIxDEMO(a2));
            byte[] a15 = new byte[2];
            a2.readFully(a15);
            ((fc)a5).x(xb.ALLATORIxDEMO(a15[0], 0));
            ((fc)a5).f(xb.ALLATORIxDEMO(a15[0], 3));
            ((fc)a5).c(xb.ALLATORIxDEMO(a15[1], 3));
            ((fc)a5).c((byte[])a15.clone());
            ((fc)a5).ALLATORIxDEMO(gc.ALLATORIxDEMO(a3.ALLATORIxDEMO(a2)));
            ((fc)a5).x(a3.c(a2));
            a2.readFully(a12);
            ((fc)a5).f(a3.ALLATORIxDEMO(a12, 0));
            ((fc)a5).ALLATORIxDEMO(a12);
            ((fc)a5).c(a3.ALLATORIxDEMO(a2, 4));
            ((fc)a5).ALLATORIxDEMO(a3.ALLATORIxDEMO(a2, 4));
            int a16 = a3.ALLATORIxDEMO(a2);
            ((fc)a5).c(a16);
            ((fc)a5).ALLATORIxDEMO(a3.ALLATORIxDEMO(a2));
            int a17 = a3.ALLATORIxDEMO(a2);
            ((ec)a5).d(a17);
            ((ec)a5).x(a3.ALLATORIxDEMO(a2));
            a2.readFully(a11);
            ((ec)a5).x((byte[])a11.clone());
            a2.readFully(a12);
            ((ec)a5).f((byte[])a12.clone());
            a2.readFully(a12);
            ((ec)a5).d(a3.ALLATORIxDEMO(a12, 0));
            if (a16 > 0) {
                a14 = new byte[a16];
                a2.readFully(a14);
                String a18 = cf.ALLATORIxDEMO(a14, ((fc)a5).c(), a4);
                if (a18.contains(":\\")) {
                    a18 = a18.substring(a18.indexOf(":\\") + 2);
                }
                ((fc)a5).ALLATORIxDEMO(a18);
                ((fc)a5).ALLATORIxDEMO(a18.endsWith("/") || a18.endsWith("\\"));
            } else {
                ((fc)a5).ALLATORIxDEMO((String)null);
            }
            a6.ALLATORIxDEMO(a2, (ec)a5);
            a6.c((ec)a5, a3);
            a6.ALLATORIxDEMO((ec)a5, a3);
            if (a17 > 0) {
                a14 = new byte[a17];
                a2.readFully(a14);
                ((ec)a5).c(cf.ALLATORIxDEMO(a14, ((fc)a5).c(), a4));
            }
            if (((fc)a5).x()) {
                if (((fc)a5).ALLATORIxDEMO() != null) {
                    ((fc)a5).ALLATORIxDEMO(zc.k);
                } else {
                    ((fc)a5).ALLATORIxDEMO(zc.o);
                }
            }
            a8.add((ec)a5);
            ++a13;
        }
        a7.ALLATORIxDEMO(a8);
        wc a22 = new wc();
        if ((long)a3.c(a2) == ei.v.ALLATORIxDEMO()) {
            a22.ALLATORIxDEMO(ei.v);
            a22.ALLATORIxDEMO(a3.ALLATORIxDEMO(a2));
            if (a22.ALLATORIxDEMO() > 0) {
                a5 = new byte[a22.ALLATORIxDEMO()];
                a2.readFully((byte[])a5);
                a22.ALLATORIxDEMO(new String((byte[])a5));
            }
        }
        return a7;
    }

    private /* synthetic */ void ALLATORIxDEMO(RandomAccessFile a2, ec a3) throws IOException {
        mi a4;
        int a5 = a3.ALLATORIxDEMO();
        if (a5 <= 0) {
            return;
        }
        a3.ALLATORIxDEMO(a4.ALLATORIxDEMO(a2, a5));
    }

    private /* synthetic */ void ALLATORIxDEMO(InputStream a2, hc a3) throws IOException {
        mi a4;
        int a5 = a3.ALLATORIxDEMO();
        if (a5 <= 0) {
            return;
        }
        a3.ALLATORIxDEMO(a4.ALLATORIxDEMO(a2, a5));
    }

    private /* synthetic */ List<xc> ALLATORIxDEMO(RandomAccessFile a2, int a3) throws IOException {
        if (a3 < 4) {
            if (a3 > 0) {
                a2.skipBytes(a3);
            }
            return null;
        }
        byte[] a4 = new byte[a3];
        a2.read(a4);
        try {
            mi a5;
            return a5.ALLATORIxDEMO(a4, a3);
        }
        catch (Exception a6) {
            return Collections.emptyList();
        }
    }

    private /* synthetic */ List<xc> ALLATORIxDEMO(InputStream a2, int a3) throws IOException {
        if (a3 < 4) {
            if (a3 > 0) {
                a2.skip(a3);
            }
            return null;
        }
        byte[] a4 = new byte[a3];
        ta.ALLATORIxDEMO(a2, a4);
        try {
            mi a5;
            return a5.ALLATORIxDEMO(a4, a3);
        }
        catch (Exception a6) {
            return Collections.emptyList();
        }
    }

    private /* synthetic */ List<xc> ALLATORIxDEMO(byte[] a2, int a3) {
        int a4;
        ArrayList<xc> a5 = new ArrayList<xc>();
        for (int a6 = 0; a6 < a3; a6 += a4) {
            mi a7;
            xc a8 = new xc();
            int a9 = a7.k.ALLATORIxDEMO(a2, a6);
            a8.ALLATORIxDEMO((long)a9);
            a4 = a7.k.ALLATORIxDEMO(a2, a6 += 2);
            a8.ALLATORIxDEMO(a4);
            a6 += 2;
            if (a4 > 0) {
                byte[] a10 = new byte[a4];
                System.arraycopy(a2, a6, a10, 0, a4);
                a8.ALLATORIxDEMO(a10);
            }
            a5.add(a8);
        }
        return a5.size() > 0 ? a5 : null;
    }

    private /* synthetic */ jc ALLATORIxDEMO(RandomAccessFile a2, ua a3, long a4) throws IOException {
        mi a5;
        jc a6 = new jc();
        a5.c(a2, a4);
        int a7 = a3.c(a2);
        if ((long)a7 != ei.q.ALLATORIxDEMO()) {
            a5.y.c(false);
            return null;
        }
        a5.y.c(true);
        a6.ALLATORIxDEMO(ei.q);
        a6.c(a3.c(a2));
        a6.ALLATORIxDEMO(a3.ALLATORIxDEMO(a2));
        a6.ALLATORIxDEMO(a3.c(a2));
        return a6;
    }

    private /* synthetic */ rc ALLATORIxDEMO(RandomAccessFile a2, ua a3) throws IOException {
        mi a4;
        if (a4.y.ALLATORIxDEMO() == null) {
            throw new yk("invalid zip64 end of central directory locator");
        }
        long a5 = a4.y.ALLATORIxDEMO().ALLATORIxDEMO();
        if (a5 < 0L) {
            throw new yk("invalid offset for start of end of central directory record");
        }
        a2.seek(a5);
        rc a6 = new rc();
        int a7 = a3.c(a2);
        if ((long)a7 != ei.b.ALLATORIxDEMO()) {
            throw new yk("invalid signature for zip64 end of central directory record");
        }
        a6.ALLATORIxDEMO(ei.b);
        a6.d(a3.ALLATORIxDEMO(a2));
        a6.x(a3.ALLATORIxDEMO(a2));
        a6.f(a3.ALLATORIxDEMO(a2));
        a6.c(a3.c(a2));
        a6.ALLATORIxDEMO(a3.c(a2));
        a6.x(a3.ALLATORIxDEMO(a2));
        a6.f(a3.ALLATORIxDEMO(a2));
        a6.c(a3.ALLATORIxDEMO(a2));
        a6.ALLATORIxDEMO(a3.ALLATORIxDEMO(a2));
        long a8 = a6.d() - 44L;
        if (a8 > 0L) {
            byte[] a9 = new byte[(int)a8];
            a2.readFully(a9);
            a6.ALLATORIxDEMO(a9);
        }
        return a6;
    }

    private /* synthetic */ void c(ec a2, ua a3) throws yk {
        mi a4;
        if (a2.ALLATORIxDEMO() == null || a2.ALLATORIxDEMO().size() <= 0) {
            return;
        }
        kc a5 = a4.ALLATORIxDEMO(a2.ALLATORIxDEMO(), a3, a2.ALLATORIxDEMO(), a2.c(), a2.k(), a2.x());
        if (a5 == null) {
            return;
        }
        a2.ALLATORIxDEMO(a5);
        if (a5.c() != -1L) {
            a2.ALLATORIxDEMO(a5.c());
        }
        if (a5.f() != -1L) {
            a2.c(a5.f());
        }
        if (a5.ALLATORIxDEMO() != -1L) {
            a2.d(a5.ALLATORIxDEMO());
        }
        if (a5.ALLATORIxDEMO() != -1) {
            a2.x(a5.ALLATORIxDEMO());
        }
    }

    private /* synthetic */ void c(hc a2, ua a3) throws yk {
        mi a4;
        if (a2 == null) {
            throw new yk("file header is null in reading Zip64 Extended Info");
        }
        if (a2.ALLATORIxDEMO() == null || a2.ALLATORIxDEMO().size() <= 0) {
            return;
        }
        kc a5 = a4.ALLATORIxDEMO(a2.ALLATORIxDEMO(), a3, a2.ALLATORIxDEMO(), a2.c(), 0L, 0);
        if (a5 == null) {
            return;
        }
        a2.ALLATORIxDEMO(a5);
        if (a5.c() != -1L) {
            a2.ALLATORIxDEMO(a5.c());
        }
        if (a5.f() != -1L) {
            a2.c(a5.f());
        }
    }

    private /* synthetic */ kc ALLATORIxDEMO(List<xc> a2, ua a3, long a4, long a5, long a6, int a7) {
        for (xc a8 : a2) {
            if (a8 == null || ei.o.ALLATORIxDEMO() != a8.ALLATORIxDEMO()) continue;
            kc a9 = new kc();
            byte[] a10 = a8.ALLATORIxDEMO();
            if (a8.ALLATORIxDEMO() <= 0) {
                return null;
            }
            int a11 = 0;
            if (a11 < a8.ALLATORIxDEMO() && a4 == 0xFFFFFFFFL) {
                a9.c(a3.ALLATORIxDEMO(a10, a11));
                a11 += 8;
            }
            if (a11 < a8.ALLATORIxDEMO() && a5 == 0xFFFFFFFFL) {
                a9.f(a3.ALLATORIxDEMO(a10, a11));
                a11 += 8;
            }
            if (a11 < a8.ALLATORIxDEMO() && a6 == 0xFFFFFFFFL) {
                a9.ALLATORIxDEMO(a3.ALLATORIxDEMO(a10, a11));
                a11 += 8;
            }
            if (a11 < a8.ALLATORIxDEMO() && a7 == 65535) {
                a9.ALLATORIxDEMO(a3.c(a10, a11));
            }
            return a9;
        }
        return null;
    }

    private /* synthetic */ void c(RandomAccessFile a2, long a3) throws IOException {
        mi a4;
        a4.ALLATORIxDEMO(a2, a3 - 4L - 8L - 4L - 4L);
    }

    public hc ALLATORIxDEMO(InputStream a2, Charset a3) throws IOException {
        mi a4;
        hc a5 = new hc();
        byte[] a6 = new byte[4];
        int a7 = a4.k.c(a2);
        if ((long)a7 != ei.g.ALLATORIxDEMO()) {
            return null;
        }
        a5.ALLATORIxDEMO(ei.g);
        a5.f(a4.k.ALLATORIxDEMO(a2));
        byte[] a8 = new byte[2];
        if (ta.ALLATORIxDEMO(a2, a8) != 2) {
            throw new yk("Could not read enough bytes for generalPurposeFlags");
        }
        a5.x(xb.ALLATORIxDEMO(a8[0], 0));
        a5.f(xb.ALLATORIxDEMO(a8[0], 3));
        a5.c(xb.ALLATORIxDEMO(a8[1], 3));
        a5.c((byte[])a8.clone());
        a5.ALLATORIxDEMO(gc.ALLATORIxDEMO(a4.k.ALLATORIxDEMO(a2)));
        a5.x(a4.k.c(a2));
        ta.ALLATORIxDEMO(a2, a6);
        a5.f(a4.k.ALLATORIxDEMO(a6, 0));
        a5.ALLATORIxDEMO((byte[])a6.clone());
        a5.c(a4.k.ALLATORIxDEMO(a2, 4));
        a5.ALLATORIxDEMO(a4.k.ALLATORIxDEMO(a2, 4));
        int a9 = a4.k.ALLATORIxDEMO(a2);
        a5.c(a9);
        a5.ALLATORIxDEMO(a4.k.ALLATORIxDEMO(a2));
        if (a9 > 0) {
            byte[] a10 = new byte[a9];
            ta.ALLATORIxDEMO(a2, a10);
            String a11 = cf.ALLATORIxDEMO(a10, a5.c(), a3);
            if (a11 == null) {
                throw new yk("file name is null, cannot assign file name to local file header");
            }
            if (a11.contains(":" + System.getProperty("file.separator"))) {
                a11 = a11.substring(a11.indexOf(":" + System.getProperty("file.separator")) + 2);
            }
            a5.ALLATORIxDEMO(a11);
            a5.ALLATORIxDEMO(a11.endsWith("/") || a11.endsWith("\\"));
        } else {
            a5.ALLATORIxDEMO((String)null);
        }
        a4.ALLATORIxDEMO(a2, a5);
        a4.c(a5, a4.k);
        a4.ALLATORIxDEMO(a5, a4.k);
        if (a5.x() && a5.ALLATORIxDEMO() != zc.k) {
            if (BigInteger.valueOf(a5.c()[0]).testBit(6)) {
                a5.ALLATORIxDEMO(zc.y);
            } else {
                a5.ALLATORIxDEMO(zc.o);
            }
        }
        return a5;
    }

    public tc ALLATORIxDEMO(InputStream a2, boolean a3) throws IOException {
        mi a4;
        tc a5 = new tc();
        byte[] a6 = new byte[4];
        ta.ALLATORIxDEMO(a2, a6);
        long a7 = a4.k.ALLATORIxDEMO(a6, 0);
        if (a7 == ei.t.ALLATORIxDEMO()) {
            a5.ALLATORIxDEMO(ei.t);
            ta.ALLATORIxDEMO(a2, a6);
            a5.f(a4.k.ALLATORIxDEMO(a6, 0));
        } else {
            a5.f(a7);
        }
        if (a3) {
            a5.c(a4.k.ALLATORIxDEMO(a2));
            a5.ALLATORIxDEMO(a4.k.ALLATORIxDEMO(a2));
        } else {
            a5.c(a4.k.c(a2));
            a5.ALLATORIxDEMO(a4.k.c(a2));
        }
        return a5;
    }

    private /* synthetic */ void ALLATORIxDEMO(ec a2, ua a3) throws yk {
        mi a4;
        if (a2.ALLATORIxDEMO() == null || a2.ALLATORIxDEMO().size() <= 0) {
            return;
        }
        em a5 = a4.ALLATORIxDEMO(a2.ALLATORIxDEMO(), a3);
        if (a5 != null) {
            a2.ALLATORIxDEMO(a5);
            a2.ALLATORIxDEMO(zc.k);
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(hc a2, ua a3) throws yk {
        mi a4;
        if (a2.ALLATORIxDEMO() == null || a2.ALLATORIxDEMO().size() <= 0) {
            return;
        }
        em a5 = a4.ALLATORIxDEMO(a2.ALLATORIxDEMO(), a3);
        if (a5 != null) {
            a2.ALLATORIxDEMO(a5);
            a2.ALLATORIxDEMO(zc.k);
        }
    }

    private /* synthetic */ em ALLATORIxDEMO(List<xc> a2, ua a3) throws yk {
        if (a2 == null) {
            return null;
        }
        for (xc a4 : a2) {
            if (a4 == null || a4.ALLATORIxDEMO() != ei.y.ALLATORIxDEMO()) continue;
            if (a4.ALLATORIxDEMO() == null) {
                throw new yk("corrupt AES extra data records");
            }
            em a5 = new em();
            a5.ALLATORIxDEMO(ei.y);
            a5.ALLATORIxDEMO(a4.ALLATORIxDEMO());
            byte[] a6 = a4.ALLATORIxDEMO();
            a5.ALLATORIxDEMO(oc.ALLATORIxDEMO(a3.ALLATORIxDEMO(a6, 0)));
            byte[] a7 = new byte[2];
            System.arraycopy(a6, 2, a7, 0, 2);
            a5.ALLATORIxDEMO(new String(a7));
            a5.ALLATORIxDEMO(nc.ALLATORIxDEMO(a6[4] & 0xFF));
            a5.ALLATORIxDEMO(gc.ALLATORIxDEMO(a3.ALLATORIxDEMO(a6, 5)));
            return a5;
        }
        return null;
    }

    private /* synthetic */ long ALLATORIxDEMO(dc a2) {
        if (a2.c()) {
            return a2.ALLATORIxDEMO().f();
        }
        return a2.ALLATORIxDEMO().c();
    }

    private /* synthetic */ long ALLATORIxDEMO(RandomAccessFile a2) throws IOException {
        byte[] a3 = new byte[4096];
        long a4 = a2.getFilePointer();
        do {
            mi a5;
            int a6;
            long a7;
            if ((a7 = a4 - (long)(a6 = a4 > 4096L ? 4096 : (int)a4) + 4L) == 4L) {
                a7 = 0L;
            }
            a5.ALLATORIxDEMO(a2, a7);
            a2.read(a3, 0, a6);
            a4 = a7;
            for (int a8 = 0; a8 < a6 - 3; ++a8) {
                if ((long)a5.k.c(a3, a8) != ei.x.ALLATORIxDEMO()) continue;
                return a4 + (long)a8;
            }
        } while (a4 > 0L);
        throw new yk("Zip headers not found. Probably not a zip file");
    }

    private /* synthetic */ void ALLATORIxDEMO(RandomAccessFile a2, long a3) throws IOException {
        if (a2 instanceof og) {
            ((og)a2).ALLATORIxDEMO(a3);
        } else {
            a2.seek(a3);
        }
    }

    private /* synthetic */ String ALLATORIxDEMO(RandomAccessFile a2, int a3, Charset a4) {
        if (a3 <= 0) {
            return null;
        }
        try {
            byte[] a5 = new byte[a3];
            a2.readFully(a5);
            return new String(a5, a4);
        }
        catch (IOException a6) {
            return null;
        }
    }
}

