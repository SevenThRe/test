/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.dc;
import eos.moe.dragoncore.ec;
import eos.moe.dragoncore.ei;
import eos.moe.dragoncore.ic;
import eos.moe.dragoncore.jb;
import eos.moe.dragoncore.jc;
import eos.moe.dragoncore.ka;
import eos.moe.dragoncore.mg;
import eos.moe.dragoncore.ob;
import eos.moe.dragoncore.pc;
import eos.moe.dragoncore.rc;
import eos.moe.dragoncore.sb;
import eos.moe.dragoncore.ua;
import eos.moe.dragoncore.uc;
import eos.moe.dragoncore.vc;
import eos.moe.dragoncore.yk;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.List;

public class zb
extends ob<sb> {
    private dc k;
    private ua ALLATORIxDEMO = new ua();

    public zb(dc a2, jb a3) {
        super(a3);
        zb a4;
        a4.k = a2;
    }

    @Override
    public void ALLATORIxDEMO(sb a2, uc a3) throws IOException {
        zb a4;
        if (!a4.k.f()) {
            yk a5 = new yk("archive not a split zip file");
            a3.c(a5);
            throw a5;
        }
        try (FileOutputStream a6 = new FileOutputStream(sb.ALLATORIxDEMO(a2));){
            long a7 = 0L;
            int a8 = a4.k.ALLATORIxDEMO().d();
            if (a8 <= 0) {
                throw new yk("zip archive not a split zip file");
            }
            int a9 = 0;
            for (int a10 = 0; a10 <= a8; ++a10) {
                try (RandomAccessFile a11 = a4.ALLATORIxDEMO(a4.k, a10);){
                    int a12 = 0;
                    long a13 = a11.length();
                    if (a10 == 0) {
                        if ((long)a4.ALLATORIxDEMO.c(a11) == ei.c.ALLATORIxDEMO()) {
                            a9 = 4;
                            a12 = 4;
                        } else {
                            a11.seek(0L);
                        }
                    }
                    if (a10 == a8) {
                        a13 = a4.k.ALLATORIxDEMO().c();
                    }
                    ka.ALLATORIxDEMO(a11, a6, a12, a13, a3);
                    a4.ALLATORIxDEMO(a4.k.ALLATORIxDEMO().ALLATORIxDEMO(), a10 == 0 ? 0L : (a7 += a13 - (long)a12), a10, a9);
                    a4.ALLATORIxDEMO();
                    continue;
                }
            }
            a4.ALLATORIxDEMO(a4.k, a7, a6, (Charset)((Object)a2.ALLATORIxDEMO));
            a3.f();
        }
        catch (CloneNotSupportedException a14) {
            throw new yk(a14);
        }
    }

    @Override
    public long ALLATORIxDEMO(sb a2) {
        zb a3;
        if (!a3.k.f()) {
            return 0L;
        }
        long a4 = 0L;
        for (int a5 = 0; a5 <= a3.k.ALLATORIxDEMO().d(); ++a5) {
            a4 += a3.ALLATORIxDEMO(a3.k, a5).length();
        }
        return a4;
    }

    private /* synthetic */ void ALLATORIxDEMO(List<ec> a2, long a3, int a4, int a5) {
        for (ec a6 : a2) {
            if (a6.x() != a4) continue;
            a6.d(a6.k() + a3 - (long)a5);
            a6.x(0);
        }
    }

    private /* synthetic */ File ALLATORIxDEMO(dc a2, int a3) {
        if (a3 == a2.ALLATORIxDEMO().d()) {
            return a2.ALLATORIxDEMO();
        }
        String a4 = ".z0";
        if (a3 >= 9) {
            a4 = ".z";
        }
        String a5 = a2.ALLATORIxDEMO().getPath();
        String a6 = a2.ALLATORIxDEMO().getPath().substring(0, a5.lastIndexOf(".")) + a4 + (a3 + 1);
        return new File(a6);
    }

    private /* synthetic */ RandomAccessFile ALLATORIxDEMO(dc a2, int a3) throws FileNotFoundException {
        zb a4;
        File a5 = a4.ALLATORIxDEMO(a2, a3);
        return new RandomAccessFile(a5, ic.o.ALLATORIxDEMO());
    }

    private /* synthetic */ void ALLATORIxDEMO(dc a2, long a3, OutputStream a4, Charset a5) throws IOException, CloneNotSupportedException {
        zb a6;
        dc a7 = (dc)a2.clone();
        a7.ALLATORIxDEMO().c(a3);
        a6.f(a7, a3);
        mg a8 = new mg();
        a8.ALLATORIxDEMO(a7, a4, a5);
    }

    private /* synthetic */ void f(dc a2, long a3) {
        zb a4;
        a2.f(false);
        a4.ALLATORIxDEMO(a2);
        if (a2.c()) {
            a4.c(a2, a3);
            a4.ALLATORIxDEMO(a2, a3);
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(dc a2) {
        int a3 = a2.ALLATORIxDEMO().ALLATORIxDEMO().size();
        pc a4 = a2.ALLATORIxDEMO();
        a4.d(0);
        a4.x(0);
        a4.c(a3);
        a4.f(a3);
    }

    private /* synthetic */ void c(dc a2, long a3) {
        if (a2.ALLATORIxDEMO() == null) {
            return;
        }
        jc a4 = a2.ALLATORIxDEMO();
        a4.c(0);
        a4.ALLATORIxDEMO(a4.ALLATORIxDEMO() + a3);
        a4.ALLATORIxDEMO(1);
    }

    private /* synthetic */ void ALLATORIxDEMO(dc a2, long a3) {
        if (a2.ALLATORIxDEMO() == null) {
            return;
        }
        rc a4 = a2.ALLATORIxDEMO();
        a4.c(0);
        a4.ALLATORIxDEMO(0);
        a4.x((long)a2.ALLATORIxDEMO().c());
        a4.ALLATORIxDEMO(a4.ALLATORIxDEMO() + a3);
    }

    @Override
    public vc ALLATORIxDEMO() {
        return vc.o;
    }
}

