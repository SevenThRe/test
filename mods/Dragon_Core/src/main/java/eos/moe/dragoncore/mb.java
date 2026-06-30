/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.cf;
import eos.moe.dragoncore.db;
import eos.moe.dragoncore.dc;
import eos.moe.dragoncore.ec;
import eos.moe.dragoncore.ic;
import eos.moe.dragoncore.jb;
import eos.moe.dragoncore.mg;
import eos.moe.dragoncore.pc;
import eos.moe.dragoncore.re;
import eos.moe.dragoncore.ub;
import eos.moe.dragoncore.uc;
import eos.moe.dragoncore.vc;
import eos.moe.dragoncore.yk;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class mb
extends ub<db> {
    private dc k;
    private mg ALLATORIxDEMO;

    public mb(dc a2, mg a3, jb a4) {
        super(a4);
        mb a5;
        a5.k = a2;
        a5.ALLATORIxDEMO = a3;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void ALLATORIxDEMO(db a2, uc a3) throws IOException {
        mb a4;
        if (a4.k.f()) {
            throw new yk("This is a split archive. Zip file format does not allow updating split/spanned files");
        }
        List<String> a5 = a4.c(db.ALLATORIxDEMO(a2));
        if (a5.isEmpty()) {
            return;
        }
        File a6 = a4.ALLATORIxDEMO(a4.k.ALLATORIxDEMO().getPath());
        boolean a7 = false;
        try (re a8 = new re(a6);
             RandomAccessFile a9 = new RandomAccessFile(a4.k.ALLATORIxDEMO(), ic.o.ALLATORIxDEMO());){
            long a10 = 0L;
            List<ec> a11 = a4.ALLATORIxDEMO(a4.k.ALLATORIxDEMO().ALLATORIxDEMO());
            for (ec a12 : a11) {
                long a13 = a4.ALLATORIxDEMO(a11, a12, a4.k) - a8.ALLATORIxDEMO();
                if (a4.ALLATORIxDEMO(a12, a5)) {
                    a4.ALLATORIxDEMO(a11, a12, a13);
                    if (!a4.k.ALLATORIxDEMO().ALLATORIxDEMO().remove(a12)) {
                        throw new yk("Could not remove entry from list of central directory headers");
                    }
                    a10 += a13;
                } else {
                    a10 += super.ALLATORIxDEMO(a9, a8, a10, a13, a3);
                }
                a4.ALLATORIxDEMO();
            }
            a4.ALLATORIxDEMO.c(a4.k, a8, (Charset)((Object)a2.ALLATORIxDEMO));
            a7 = true;
        }
        finally {
            a4.ALLATORIxDEMO(a7, a4.k.ALLATORIxDEMO(), a6);
        }
    }

    @Override
    public long ALLATORIxDEMO(db a2) {
        mb a3;
        return a3.k.ALLATORIxDEMO().length();
    }

    private /* synthetic */ List<String> c(List<String> a2) throws yk {
        ArrayList<String> a3 = new ArrayList<String>();
        for (String a4 : a2) {
            mb a5;
            if (cf.c(a5.k, a4) == null) continue;
            a3.add(a4);
        }
        return a3;
    }

    private /* synthetic */ boolean ALLATORIxDEMO(ec a2, List<String> a3) {
        for (String a4 : a3) {
            if (!a2.ALLATORIxDEMO().startsWith(a4)) continue;
            return true;
        }
        return false;
    }

    private /* synthetic */ void ALLATORIxDEMO(List<ec> a2, ec a3, long a4) throws yk {
        mb a5;
        a5.ALLATORIxDEMO(a2, a5.k, a3, a5.ALLATORIxDEMO(a4));
        pc a6 = a5.k.ALLATORIxDEMO();
        a6.c(a6.c() - a4);
        a6.c(a6.c() - 1);
        if (a6.f() > 0) {
            a6.f(a6.f() - 1);
        }
        if (a5.k.c()) {
            a5.k.ALLATORIxDEMO().ALLATORIxDEMO(a5.k.ALLATORIxDEMO().ALLATORIxDEMO() - a4);
            a5.k.ALLATORIxDEMO().x(a5.k.ALLATORIxDEMO().f() - 1L);
            a5.k.ALLATORIxDEMO().ALLATORIxDEMO(a5.k.ALLATORIxDEMO().ALLATORIxDEMO() - a4);
        }
    }

    @Override
    private /* synthetic */ long ALLATORIxDEMO(long a2) {
        if (a2 == Long.MIN_VALUE) {
            throw new ArithmeticException("long overflow");
        }
        return -a2;
    }

    @Override
    public vc ALLATORIxDEMO() {
        return vc.c;
    }
}

