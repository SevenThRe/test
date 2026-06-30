/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.dc;
import eos.moe.dragoncore.ec;
import eos.moe.dragoncore.ei;
import eos.moe.dragoncore.em;
import eos.moe.dragoncore.hc;
import eos.moe.dragoncore.jc;
import eos.moe.dragoncore.ka;
import eos.moe.dragoncore.rc;
import eos.moe.dragoncore.re;
import eos.moe.dragoncore.ta;
import eos.moe.dragoncore.ti;
import eos.moe.dragoncore.ua;
import eos.moe.dragoncore.x;
import eos.moe.dragoncore.xc;
import eos.moe.dragoncore.yk;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;

public class mg {
    private static final short q = 16;
    private static final short b = 28;
    private static final short o = 11;
    private ua y = new ua();
    private byte[] k = new byte[8];
    private byte[] ALLATORIxDEMO = new byte[4];

    public mg() {
        mg a2;
    }

    public void ALLATORIxDEMO(dc a2, hc a3, OutputStream a4, Charset a5) throws IOException {
        try (ByteArrayOutputStream a6 = new ByteArrayOutputStream();){
            boolean a7;
            mg a8;
            a8.y.ALLATORIxDEMO((OutputStream)a6, (int)a3.ALLATORIxDEMO().ALLATORIxDEMO());
            a8.y.c(a6, a3.f());
            a6.write(a3.c());
            a8.y.c(a6, a3.ALLATORIxDEMO().ALLATORIxDEMO());
            a8.y.ALLATORIxDEMO(a8.k, 0, a3.d());
            a6.write(a8.k, 0, 4);
            a8.y.ALLATORIxDEMO(a8.k, 0, a3.f());
            a6.write(a8.k, 0, 4);
            boolean bl2 = a7 = a3.c() >= 0xFFFFFFFFL || a3.ALLATORIxDEMO() >= 0xFFFFFFFFL;
            if (a7) {
                a8.y.ALLATORIxDEMO(a8.k, 0, 0xFFFFFFFFL);
                a6.write(a8.k, 0, 4);
                a6.write(a8.k, 0, 4);
                a2.c(true);
                a3.d(true);
            } else {
                a8.y.ALLATORIxDEMO(a8.k, 0, a3.c());
                a6.write(a8.k, 0, 4);
                a8.y.ALLATORIxDEMO(a8.k, 0, a3.ALLATORIxDEMO());
                a6.write(a8.k, 0, 4);
                a3.d(false);
            }
            byte[] a9 = new byte[]{};
            if (ta.ALLATORIxDEMO(a3.ALLATORIxDEMO())) {
                a9 = a3.ALLATORIxDEMO().getBytes(a5);
            }
            a8.y.c(a6, a9.length);
            int a10 = 0;
            if (a7) {
                a10 += 20;
            }
            if (a3.ALLATORIxDEMO() != null) {
                a10 += 11;
            }
            a8.y.c(a6, a10);
            if (a9.length > 0) {
                a6.write(a9);
            }
            if (a7) {
                a8.y.c(a6, (int)ei.o.ALLATORIxDEMO());
                a8.y.c(a6, 16);
                a8.y.ALLATORIxDEMO((OutputStream)a6, a3.ALLATORIxDEMO());
                a8.y.ALLATORIxDEMO((OutputStream)a6, a3.c());
            }
            if (a3.ALLATORIxDEMO() != null) {
                em a11 = a3.ALLATORIxDEMO();
                a8.y.c(a6, (int)a11.ALLATORIxDEMO().ALLATORIxDEMO());
                a8.y.c(a6, a11.ALLATORIxDEMO());
                a8.y.c(a6, a11.ALLATORIxDEMO().ALLATORIxDEMO());
                a6.write(a11.ALLATORIxDEMO().getBytes());
                byte[] a12 = new byte[]{(byte)a11.ALLATORIxDEMO().x()};
                a6.write(a12);
                a8.y.c(a6, a11.ALLATORIxDEMO().ALLATORIxDEMO());
            }
            a4.write(a6.toByteArray());
        }
    }

    public void ALLATORIxDEMO(hc a2, OutputStream a3) throws IOException {
        if (a2 == null || a3 == null) {
            throw new yk("input parameters is null, cannot write extended local header");
        }
        try (ByteArrayOutputStream a4 = new ByteArrayOutputStream();){
            mg a5;
            a5.y.ALLATORIxDEMO((OutputStream)a4, (int)ei.t.ALLATORIxDEMO());
            a5.y.ALLATORIxDEMO(a5.k, 0, a2.f());
            a4.write(a5.k, 0, 4);
            if (a2.d()) {
                a5.y.ALLATORIxDEMO((OutputStream)a4, a2.c());
                a5.y.ALLATORIxDEMO((OutputStream)a4, a2.ALLATORIxDEMO());
            } else {
                a5.y.ALLATORIxDEMO(a5.k, 0, a2.c());
                a4.write(a5.k, 0, 4);
                a5.y.ALLATORIxDEMO(a5.k, 0, a2.ALLATORIxDEMO());
                a4.write(a5.k, 0, 4);
            }
            a3.write(a4.toByteArray());
        }
    }

    public void c(dc a2, OutputStream a3, Charset a4) throws IOException {
        if (a2 == null || a3 == null) {
            throw new yk("input parameters is null, cannot finalize zip file");
        }
        try (ByteArrayOutputStream a5 = new ByteArrayOutputStream();){
            mg a6;
            a6.ALLATORIxDEMO(a2, a3);
            long a7 = a6.ALLATORIxDEMO(a2);
            a6.ALLATORIxDEMO(a2, a5, a6.y, a4);
            int a8 = a5.size();
            if (a2.c() || a7 >= 0xFFFFFFFFL || a2.ALLATORIxDEMO().ALLATORIxDEMO().size() >= 65535) {
                if (a2.ALLATORIxDEMO() == null) {
                    a2.ALLATORIxDEMO(new rc());
                }
                if (a2.ALLATORIxDEMO() == null) {
                    a2.ALLATORIxDEMO(new jc());
                }
                a2.ALLATORIxDEMO().ALLATORIxDEMO(a7 + (long)a8);
                if (a6.ALLATORIxDEMO(a3)) {
                    int a9 = a6.ALLATORIxDEMO(a3);
                    a2.ALLATORIxDEMO().c(a9);
                    a2.ALLATORIxDEMO().ALLATORIxDEMO(a9 + 1);
                } else {
                    a2.ALLATORIxDEMO().c(0);
                    a2.ALLATORIxDEMO().ALLATORIxDEMO(1);
                }
                a6.ALLATORIxDEMO(a2, a8, a7, a5, a6.y);
                a6.ALLATORIxDEMO(a2, a5, a6.y);
            }
            a6.ALLATORIxDEMO(a2, a8, a7, a5, a6.y, a4);
            a6.ALLATORIxDEMO(a2, a3, a5.toByteArray(), a4);
        }
    }

    public void ALLATORIxDEMO(dc a2, OutputStream a3, Charset a4) throws IOException {
        if (a2 == null || a3 == null) {
            throw new yk("input parameters is null, cannot finalize zip file without validations");
        }
        try (ByteArrayOutputStream a5 = new ByteArrayOutputStream();){
            mg a6;
            long a7 = a2.ALLATORIxDEMO().c();
            a6.ALLATORIxDEMO(a2, a5, a6.y, a4);
            int a8 = a5.size();
            if (a2.c() || a7 >= 0xFFFFFFFFL || a2.ALLATORIxDEMO().ALLATORIxDEMO().size() >= 65535) {
                if (a2.ALLATORIxDEMO() == null) {
                    a2.ALLATORIxDEMO(new rc());
                }
                if (a2.ALLATORIxDEMO() == null) {
                    a2.ALLATORIxDEMO(new jc());
                }
                a2.ALLATORIxDEMO().ALLATORIxDEMO(a7 + (long)a8);
                a6.ALLATORIxDEMO(a2, a8, a7, a5, a6.y);
                a6.ALLATORIxDEMO(a2, a5, a6.y);
            }
            a6.ALLATORIxDEMO(a2, a8, a7, a5, a6.y, a4);
            a6.ALLATORIxDEMO(a2, a3, a5.toByteArray(), a4);
        }
    }

    public void ALLATORIxDEMO(ec a2, dc a3, re a4) throws IOException {
        mg a5;
        re a6;
        if (a2 == null || a3 == null) {
            throw new yk("invalid input parameters, cannot update local file header");
        }
        boolean a7 = false;
        if (a2.x() != a4.ALLATORIxDEMO()) {
            String a8 = a3.ALLATORIxDEMO().getParent();
            String a9 = ka.ALLATORIxDEMO(a3.ALLATORIxDEMO().getName());
            String a10 = a8 + System.getProperty("file.separator");
            a10 = a2.x() < 9 ? a10 + a9 + ".z0" + (a2.x() + 1) : a10 + a9 + ".z" + (a2.x() + 1);
            a6 = new re(new File(a10));
            a7 = true;
        } else {
            a6 = a4;
        }
        long a11 = a6.ALLATORIxDEMO();
        a6.ALLATORIxDEMO(a2.k() + 14L);
        a5.y.ALLATORIxDEMO(a5.k, 0, a2.f());
        a6.write(a5.k, 0, 4);
        a5.ALLATORIxDEMO(a6, a2);
        if (a7) {
            a6.close();
        } else {
            a4.ALLATORIxDEMO(a11);
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(re a2, ec a3) throws IOException {
        mg a4;
        if (a3.ALLATORIxDEMO() >= 0xFFFFFFFFL) {
            a4.y.ALLATORIxDEMO(a4.k, 0, 0xFFFFFFFFL);
            a2.write(a4.k, 0, 4);
            a2.write(a4.k, 0, 4);
            int a5 = 4 + a3.c() + 2 + 2;
            if (a2.ALLATORIxDEMO(a5) != a5) {
                throw new yk("Unable to skip " + a5 + " bytes to update LFH");
            }
            a4.y.ALLATORIxDEMO((OutputStream)a2, a3.ALLATORIxDEMO());
            a4.y.ALLATORIxDEMO((OutputStream)a2, a3.c());
        } else {
            a4.y.ALLATORIxDEMO(a4.k, 0, a3.c());
            a2.write(a4.k, 0, 4);
            a4.y.ALLATORIxDEMO(a4.k, 0, a3.ALLATORIxDEMO());
            a2.write(a4.k, 0, 4);
        }
    }

    private /* synthetic */ boolean ALLATORIxDEMO(OutputStream a2) {
        if (a2 instanceof re) {
            return ((re)a2).ALLATORIxDEMO();
        }
        if (a2 instanceof ti) {
            return ((ti)a2).ALLATORIxDEMO();
        }
        return false;
    }

    private /* synthetic */ int ALLATORIxDEMO(OutputStream a2) {
        if (a2 instanceof re) {
            return ((re)a2).ALLATORIxDEMO();
        }
        return ((ti)a2).ALLATORIxDEMO();
    }

    private /* synthetic */ void ALLATORIxDEMO(dc a2, OutputStream a3, byte[] a4, Charset a5) throws IOException {
        if (a4 == null) {
            throw new yk("invalid buff to write as zip headers");
        }
        if (a3 instanceof ti && ((ti)a3).ALLATORIxDEMO(a4.length)) {
            mg a6;
            a6.c(a2, a3, a5);
            return;
        }
        a3.write(a4);
    }

    private /* synthetic */ void ALLATORIxDEMO(dc a2, OutputStream a3) throws IOException {
        int a4 = 0;
        if (a3 instanceof x) {
            a2.ALLATORIxDEMO().c(((x)((Object)a3)).ALLATORIxDEMO());
            a4 = ((x)((Object)a3)).ALLATORIxDEMO();
        }
        if (a2.c()) {
            if (a2.ALLATORIxDEMO() == null) {
                a2.ALLATORIxDEMO(new rc());
            }
            if (a2.ALLATORIxDEMO() == null) {
                a2.ALLATORIxDEMO(new jc());
            }
            a2.ALLATORIxDEMO().ALLATORIxDEMO(a2.ALLATORIxDEMO().c());
            a2.ALLATORIxDEMO().c(a4);
            a2.ALLATORIxDEMO().ALLATORIxDEMO(a4 + 1);
        }
        a2.ALLATORIxDEMO().d(a4);
        a2.ALLATORIxDEMO().x(a4);
    }

    private /* synthetic */ void ALLATORIxDEMO(dc a2, ByteArrayOutputStream a3, ua a4, Charset a5) throws yk {
        if (a2.ALLATORIxDEMO() == null || a2.ALLATORIxDEMO().ALLATORIxDEMO() == null || a2.ALLATORIxDEMO().ALLATORIxDEMO().size() <= 0) {
            return;
        }
        for (ec a6 : a2.ALLATORIxDEMO().ALLATORIxDEMO()) {
            mg a7;
            a7.ALLATORIxDEMO(a2, a6, a3, a4, a5);
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(dc a2, ec a3, ByteArrayOutputStream a4, ua a5, Charset a6) throws yk {
        if (a3 == null) {
            throw new yk("input parameters is null, cannot write local file header");
        }
        try {
            mg a7;
            byte[] a8 = new byte[]{0, 0};
            boolean a9 = a7.ALLATORIxDEMO(a3);
            a5.ALLATORIxDEMO((OutputStream)a4, (int)a3.ALLATORIxDEMO().ALLATORIxDEMO());
            a5.c(a4, a3.k());
            a5.c(a4, a3.f());
            a4.write(a3.c());
            a5.c(a4, a3.ALLATORIxDEMO().ALLATORIxDEMO());
            a5.ALLATORIxDEMO(a7.k, 0, a3.d());
            a4.write(a7.k, 0, 4);
            a5.ALLATORIxDEMO(a7.k, 0, a3.f());
            a4.write(a7.k, 0, 4);
            if (a9) {
                a5.ALLATORIxDEMO(a7.k, 0, 0xFFFFFFFFL);
                a4.write(a7.k, 0, 4);
                a4.write(a7.k, 0, 4);
                a2.c(true);
            } else {
                a5.ALLATORIxDEMO(a7.k, 0, a3.c());
                a4.write(a7.k, 0, 4);
                a5.ALLATORIxDEMO(a7.k, 0, a3.ALLATORIxDEMO());
                a4.write(a7.k, 0, 4);
            }
            byte[] a10 = new byte[]{};
            if (ta.ALLATORIxDEMO(a3.ALLATORIxDEMO())) {
                a10 = a3.ALLATORIxDEMO().getBytes(a6);
            }
            a5.c(a4, a10.length);
            byte[] a11 = new byte[4];
            if (a9) {
                a5.ALLATORIxDEMO(a7.k, 0, 0xFFFFFFFFL);
                System.arraycopy(a7.k, 0, a11, 0, 4);
            } else {
                a5.ALLATORIxDEMO(a7.k, 0, a3.k());
                System.arraycopy(a7.k, 0, a11, 0, 4);
            }
            int a12 = a7.ALLATORIxDEMO(a3, a9);
            a5.c(a4, a12);
            String a13 = a3.c();
            byte[] a14 = new byte[]{};
            if (ta.ALLATORIxDEMO(a13)) {
                a14 = a13.getBytes(a6);
            }
            a5.c(a4, a14.length);
            if (a9) {
                a5.ALLATORIxDEMO(a7.ALLATORIxDEMO, 0, 65535);
                a4.write(a7.ALLATORIxDEMO, 0, 2);
            } else {
                a5.c(a4, a3.x());
            }
            a4.write(a8);
            a4.write(a3.f());
            a4.write(a11);
            if (a10.length > 0) {
                a4.write(a10);
            }
            if (a9) {
                a2.c(true);
                a5.c(a4, (int)ei.o.ALLATORIxDEMO());
                a5.c(a4, 28);
                a5.ALLATORIxDEMO((OutputStream)a4, a3.ALLATORIxDEMO());
                a5.ALLATORIxDEMO((OutputStream)a4, a3.c());
                a5.ALLATORIxDEMO((OutputStream)a4, a3.k());
                a5.ALLATORIxDEMO((OutputStream)a4, a3.x());
            }
            if (a3.ALLATORIxDEMO() != null) {
                em a15 = a3.ALLATORIxDEMO();
                a5.c(a4, (int)a15.ALLATORIxDEMO().ALLATORIxDEMO());
                a5.c(a4, a15.ALLATORIxDEMO());
                a5.c(a4, a15.ALLATORIxDEMO().ALLATORIxDEMO());
                a4.write(a15.ALLATORIxDEMO().getBytes());
                byte[] a16 = new byte[]{(byte)a15.ALLATORIxDEMO().x()};
                a4.write(a16);
                a5.c(a4, a15.ALLATORIxDEMO().ALLATORIxDEMO());
            }
            a7.ALLATORIxDEMO(a3, (OutputStream)a4);
            if (a14.length > 0) {
                a4.write(a14);
            }
        }
        catch (Exception a17) {
            throw new yk(a17);
        }
    }

    private /* synthetic */ int ALLATORIxDEMO(ec a2, boolean a3) throws IOException {
        int a4 = 0;
        if (a3) {
            a4 += 32;
        }
        if (a2.ALLATORIxDEMO() != null) {
            a4 += 11;
        }
        if (a2.ALLATORIxDEMO() != null) {
            for (xc a5 : a2.ALLATORIxDEMO()) {
                if (a5.ALLATORIxDEMO() == ei.y.ALLATORIxDEMO() || a5.ALLATORIxDEMO() == ei.o.ALLATORIxDEMO()) continue;
                a4 += 4 + a5.ALLATORIxDEMO();
            }
        }
        return a4;
    }

    private /* synthetic */ void ALLATORIxDEMO(ec a2, OutputStream a3) throws IOException {
        if (a2.ALLATORIxDEMO() == null || a2.ALLATORIxDEMO().size() == 0) {
            return;
        }
        for (xc a4 : a2.ALLATORIxDEMO()) {
            mg a5;
            if (a4.ALLATORIxDEMO() == ei.y.ALLATORIxDEMO() || a4.ALLATORIxDEMO() == ei.o.ALLATORIxDEMO()) continue;
            a5.y.c(a3, (int)a4.ALLATORIxDEMO());
            a5.y.c(a3, a4.ALLATORIxDEMO());
            if (a4.ALLATORIxDEMO() <= 0 || a4.ALLATORIxDEMO() == null) continue;
            a3.write(a4.ALLATORIxDEMO());
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(dc a2, int a3, long a4, ByteArrayOutputStream a5, ua a6) throws IOException {
        long a7;
        byte[] a8 = new byte[]{0, 0};
        a6.ALLATORIxDEMO((OutputStream)a5, (int)ei.b.ALLATORIxDEMO());
        a6.ALLATORIxDEMO((OutputStream)a5, 44L);
        if (a2.ALLATORIxDEMO() != null && a2.ALLATORIxDEMO().ALLATORIxDEMO() != null && a2.ALLATORIxDEMO().ALLATORIxDEMO().size() > 0) {
            a6.c(a5, a2.ALLATORIxDEMO().ALLATORIxDEMO().get(0).k());
            a6.c(a5, a2.ALLATORIxDEMO().ALLATORIxDEMO().get(0).f());
        } else {
            a5.write(a8);
            a5.write(a8);
        }
        a6.ALLATORIxDEMO((OutputStream)a5, a2.ALLATORIxDEMO().d());
        a6.ALLATORIxDEMO((OutputStream)a5, a2.ALLATORIxDEMO().x());
        long a9 = a7 = (long)a2.ALLATORIxDEMO().ALLATORIxDEMO().size();
        if (a2.f()) {
            mg a10;
            a9 = a10.ALLATORIxDEMO(a2.ALLATORIxDEMO().ALLATORIxDEMO(), a2.ALLATORIxDEMO().d());
        }
        a6.ALLATORIxDEMO((OutputStream)a5, a9);
        a6.ALLATORIxDEMO((OutputStream)a5, a7);
        a6.ALLATORIxDEMO((OutputStream)a5, (long)a3);
        a6.ALLATORIxDEMO((OutputStream)a5, a4);
    }

    private /* synthetic */ void ALLATORIxDEMO(dc a2, ByteArrayOutputStream a3, ua a4) throws IOException {
        a4.ALLATORIxDEMO((OutputStream)a3, (int)ei.q.ALLATORIxDEMO());
        a4.ALLATORIxDEMO((OutputStream)a3, a2.ALLATORIxDEMO().c());
        a4.ALLATORIxDEMO((OutputStream)a3, a2.ALLATORIxDEMO().ALLATORIxDEMO());
        a4.ALLATORIxDEMO((OutputStream)a3, a2.ALLATORIxDEMO().ALLATORIxDEMO());
    }

    private /* synthetic */ void ALLATORIxDEMO(dc a2, int a3, long a4, ByteArrayOutputStream a5, ua a6, Charset a7) throws IOException {
        long a8;
        byte[] a9 = new byte[8];
        a6.ALLATORIxDEMO((OutputStream)a5, (int)ei.x.ALLATORIxDEMO());
        a6.c(a5, a2.ALLATORIxDEMO().d());
        a6.c(a5, a2.ALLATORIxDEMO().x());
        long a10 = a8 = (long)a2.ALLATORIxDEMO().ALLATORIxDEMO().size();
        if (a2.f()) {
            mg a11;
            a10 = a11.ALLATORIxDEMO(a2.ALLATORIxDEMO().ALLATORIxDEMO(), a2.ALLATORIxDEMO().d());
        }
        if (a10 > 65535L) {
            a10 = 65535L;
        }
        a6.c(a5, (int)a10);
        if (a8 > 65535L) {
            a8 = 65535L;
        }
        a6.c(a5, (int)a8);
        a6.ALLATORIxDEMO((OutputStream)a5, a3);
        if (a4 > 0xFFFFFFFFL) {
            a6.ALLATORIxDEMO(a9, 0, 0xFFFFFFFFL);
            a5.write(a9, 0, 4);
        } else {
            a6.ALLATORIxDEMO(a9, 0, a4);
            a5.write(a9, 0, 4);
        }
        String a12 = a2.ALLATORIxDEMO().ALLATORIxDEMO();
        if (ta.ALLATORIxDEMO(a12)) {
            byte[] a13 = a12.getBytes(a7);
            a6.c(a5, a13.length);
            a5.write(a13);
        } else {
            a6.c(a5, 0);
        }
    }

    private /* synthetic */ long ALLATORIxDEMO(List<ec> a2, int a3) throws yk {
        if (a2 == null) {
            throw new yk("file headers are null, cannot calculate number of entries on this disk");
        }
        int a4 = 0;
        for (ec a5 : a2) {
            if (a5.x() != a3) continue;
            ++a4;
        }
        return a4;
    }

    private /* synthetic */ boolean ALLATORIxDEMO(ec a2) {
        return a2.c() >= 0xFFFFFFFFL || a2.ALLATORIxDEMO() >= 0xFFFFFFFFL || a2.k() >= 0xFFFFFFFFL || a2.x() >= 65535;
    }

    private /* synthetic */ long ALLATORIxDEMO(dc a2) {
        if (a2.c() && a2.ALLATORIxDEMO() != null && a2.ALLATORIxDEMO().ALLATORIxDEMO() != -1L) {
            return a2.ALLATORIxDEMO().ALLATORIxDEMO();
        }
        return a2.ALLATORIxDEMO().c();
    }
}

