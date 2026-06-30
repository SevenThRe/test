/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.cf;
import eos.moe.dragoncore.db;
import eos.moe.dragoncore.dc;
import eos.moe.dragoncore.ec;
import eos.moe.dragoncore.gc;
import eos.moe.dragoncore.if;
import eos.moe.dragoncore.jb;
import eos.moe.dragoncore.ka;
import eos.moe.dragoncore.lc;
import eos.moe.dragoncore.mb;
import eos.moe.dragoncore.mg;
import eos.moe.dragoncore.ob;
import eos.moe.dragoncore.qc;
import eos.moe.dragoncore.rb;
import eos.moe.dragoncore.re;
import eos.moe.dragoncore.ta;
import eos.moe.dragoncore.uc;
import eos.moe.dragoncore.vc;
import eos.moe.dragoncore.xb;
import eos.moe.dragoncore.yk;
import eos.moe.dragoncore.zc;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class fb<T>
extends ob<T> {
    private dc b;
    private char[] o;
    private mg y;
    private byte[] k = new byte[4096];
    private int ALLATORIxDEMO = -1;

    public fb(dc a2, char[] a3, mg a4, jb a5) {
        super(a5);
        fb a6;
        a6.b = a2;
        a6.o = a3;
        a6.y = a4;
    }

    public void ALLATORIxDEMO(List<File> a2, uc a3, lc a4, Charset a5) throws IOException {
        fb a6;
        ka.ALLATORIxDEMO(a2, a4.ALLATORIxDEMO());
        List<File> a7 = a6.ALLATORIxDEMO(a2, a4, a3, a5);
        try (re a8 = new re(a6.b.ALLATORIxDEMO(), a6.b.ALLATORIxDEMO());
             if a9 = a6.ALLATORIxDEMO(a8, a5);){
            for (File a10 : a7) {
                a6.ALLATORIxDEMO();
                lc a11 = a6.ALLATORIxDEMO(a4, a10, a3);
                a3.ALLATORIxDEMO(a10.getAbsolutePath());
                if (ka.ALLATORIxDEMO(a10) && a6.ALLATORIxDEMO(a11)) {
                    a6.ALLATORIxDEMO(a10, a9, a11, a8);
                    if (qc.o.equals((Object)a11.ALLATORIxDEMO())) continue;
                }
                a6.ALLATORIxDEMO(a10, a9, a11, a8, a3);
            }
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(File a2, if a3, lc a4, re a5) throws IOException {
        fb a6;
        lc a7 = new lc(a4);
        a7.f(a6.ALLATORIxDEMO(a4.f(), a2.getName()));
        a7.z(false);
        a7.ALLATORIxDEMO(gc.b);
        a3.f(a7);
        String a8 = ka.ALLATORIxDEMO(a2);
        a3.write(a8.getBytes());
        a6.ALLATORIxDEMO(a3, a5, a2, true);
    }

    private /* synthetic */ void ALLATORIxDEMO(File a2, if a3, lc a4, re a5, uc a6) throws IOException {
        fb a7;
        a3.f(a4);
        if (!a2.isDirectory()) {
            try (FileInputStream a8 = new FileInputStream(a2);){
                while ((a7.ALLATORIxDEMO = ((InputStream)a8).read(a7.k)) != -1) {
                    a3.write(a7.k, 0, a7.ALLATORIxDEMO);
                    a6.c(a7.ALLATORIxDEMO);
                    a7.ALLATORIxDEMO();
                }
            }
        }
        a7.ALLATORIxDEMO(a3, a5, a2, false);
    }

    private /* synthetic */ void ALLATORIxDEMO(if a2, re a3, File a4, boolean a5) throws IOException {
        fb a6;
        ec a7 = a2.ALLATORIxDEMO();
        byte[] a8 = ka.ALLATORIxDEMO(a4);
        if (!a5) {
            a8[3] = xb.ALLATORIxDEMO(a8[3], 5);
        }
        a7.f(a8);
        a6.ALLATORIxDEMO(a7, a3);
    }

    public long ALLATORIxDEMO(List<File> a2, lc a3) throws yk {
        long a4 = 0L;
        for (File a5 : a2) {
            fb a6;
            if (!a5.exists()) continue;
            a4 = a3.z() && a3.ALLATORIxDEMO() == zc.o ? (a4 += a5.length() * 2L) : (a4 += a5.length());
            String a7 = ka.ALLATORIxDEMO(a5, a3);
            ec a8 = cf.c(a6.ALLATORIxDEMO(), a7);
            if (a8 == null) continue;
            a4 += a6.ALLATORIxDEMO().ALLATORIxDEMO().length() - a8.c();
        }
        return a4;
    }

    public if ALLATORIxDEMO(re a2, Charset a3) throws IOException {
        fb a4;
        if (a4.b.ALLATORIxDEMO().exists()) {
            a2.ALLATORIxDEMO(cf.ALLATORIxDEMO(a4.b));
        }
        return new if(a2, a4.o, a3, a4.b);
    }

    public void ALLATORIxDEMO(lc a2) throws yk {
        if (a2 == null) {
            throw new yk("cannot validate zip parameters");
        }
        if (a2.ALLATORIxDEMO() != gc.b && a2.ALLATORIxDEMO() != gc.o) {
            throw new yk("unsupported compression type");
        }
        if (a2.z()) {
            fb a3;
            if (a2.ALLATORIxDEMO() == zc.b) {
                throw new yk("Encryption method has to be set, when encrypt files flag is set");
            }
            if (a3.o == null || a3.o.length <= 0) {
                throw new yk("input password is empty or null");
            }
        } else {
            a2.ALLATORIxDEMO(zc.b);
        }
    }

    public void ALLATORIxDEMO(ec a2, re a3) throws IOException {
        fb a4;
        a4.y.ALLATORIxDEMO(a2, a4.ALLATORIxDEMO(), a3);
    }

    private /* synthetic */ lc ALLATORIxDEMO(lc a2, File a3, uc a4) throws IOException {
        lc a5 = new lc(a2);
        a5.c(ta.x(a3.lastModified()));
        if (a3.isDirectory()) {
            a5.ALLATORIxDEMO(0L);
        } else {
            a5.ALLATORIxDEMO(a3.length());
        }
        a5.f(false);
        a5.c(a3.lastModified());
        if (!ta.ALLATORIxDEMO(a2.f())) {
            String a6 = ka.ALLATORIxDEMO(a3, a2);
            a5.f(a6);
        }
        if (a3.isDirectory()) {
            a5.ALLATORIxDEMO(gc.b);
            a5.ALLATORIxDEMO(zc.b);
            a5.z(false);
        } else {
            if (a5.z() && a5.ALLATORIxDEMO() == zc.o) {
                a4.ALLATORIxDEMO(vc.q);
                a5.f(rb.ALLATORIxDEMO(a3, a4));
                a4.ALLATORIxDEMO(vc.m);
            }
            if (a3.length() == 0L) {
                a5.ALLATORIxDEMO(gc.b);
            }
        }
        return a5;
    }

    private /* synthetic */ List<File> ALLATORIxDEMO(List<File> a2, lc a3, uc a4, Charset a5) throws yk {
        fb a6;
        ArrayList<File> a7 = new ArrayList<File>(a2);
        if (!a6.b.ALLATORIxDEMO().exists()) {
            return a7;
        }
        for (File a8 : a2) {
            String a9 = ka.ALLATORIxDEMO(a8, a3);
            ec a10 = cf.c(a6.b, a9);
            if (a10 == null) continue;
            if (a3.c()) {
                a4.ALLATORIxDEMO(vc.c);
                a6.ALLATORIxDEMO(a10, a4, a5);
                a6.ALLATORIxDEMO();
                a4.ALLATORIxDEMO(vc.m);
                continue;
            }
            a7.remove(a8);
        }
        return a7;
    }

    public void ALLATORIxDEMO(ec a2, uc a3, Charset a4) throws yk {
        fb a5;
        jb a6 = new jb(null, false, a3);
        mb a7 = new mb(a5.b, a5.y, a6);
        a7.c(new db(Collections.singletonList(a2.ALLATORIxDEMO()), a4));
    }

    private /* synthetic */ String ALLATORIxDEMO(String a2, String a3) {
        if (a2.contains("/")) {
            return a2.substring(0, a2.lastIndexOf("/") + 1) + a3;
        }
        return a3;
    }

    private /* synthetic */ boolean ALLATORIxDEMO(lc a2) {
        return qc.o.equals((Object)a2.ALLATORIxDEMO()) || qc.k.equals((Object)a2.ALLATORIxDEMO());
    }

    @Override
    public vc ALLATORIxDEMO() {
        return vc.m;
    }

    public dc ALLATORIxDEMO() {
        fb a2;
        return a2.b;
    }
}

