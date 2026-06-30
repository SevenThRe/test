/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.cf;
import eos.moe.dragoncore.dc;
import eos.moe.dragoncore.ec;
import eos.moe.dragoncore.fb;
import eos.moe.dragoncore.gc;
import eos.moe.dragoncore.if;
import eos.moe.dragoncore.jb;
import eos.moe.dragoncore.lc;
import eos.moe.dragoncore.mg;
import eos.moe.dragoncore.qb;
import eos.moe.dragoncore.re;
import eos.moe.dragoncore.ta;
import eos.moe.dragoncore.uc;
import eos.moe.dragoncore.yk;
import java.io.IOException;
import java.nio.charset.Charset;

public class ib
extends fb<qb> {
    public ib(dc a2, char[] a3, mg a4, jb a5) {
        super(a2, a3, a4, a5);
        ib a6;
    }

    @Override
    public void ALLATORIxDEMO(qb a2, uc a3) throws IOException {
        ib a4;
        a4.ALLATORIxDEMO(qb.ALLATORIxDEMO(a2));
        if (!ta.ALLATORIxDEMO(qb.ALLATORIxDEMO(a2).f())) {
            throw new yk("fileNameInZip has to be set in zipParameters when adding stream");
        }
        a4.ALLATORIxDEMO(a4.ALLATORIxDEMO(), (Charset)((Object)a2.ALLATORIxDEMO), qb.ALLATORIxDEMO(a2).f(), a3);
        qb.ALLATORIxDEMO(a2).f(true);
        if (qb.ALLATORIxDEMO(a2).ALLATORIxDEMO().equals((Object)gc.b)) {
            qb.ALLATORIxDEMO(a2).ALLATORIxDEMO(0L);
        }
        try (re a5 = new re(a4.ALLATORIxDEMO().ALLATORIxDEMO(), a4.ALLATORIxDEMO().ALLATORIxDEMO());
             if a6 = a4.ALLATORIxDEMO(a5, (Charset)((Object)a2.ALLATORIxDEMO));){
            ec a7;
            byte[] a8 = new byte[4096];
            int a9 = -1;
            lc a10 = qb.ALLATORIxDEMO(a2);
            a6.f(a10);
            if (!a10.f().endsWith("/") && !a10.f().endsWith("\\")) {
                while ((a9 = qb.ALLATORIxDEMO(a2).read(a8)) != -1) {
                    a6.write(a8, 0, a9);
                }
            }
            if ((a7 = a6.ALLATORIxDEMO()).ALLATORIxDEMO().equals((Object)gc.b)) {
                a4.ALLATORIxDEMO(a7, a5);
            }
        }
    }

    @Override
    public long ALLATORIxDEMO(qb a2) {
        return 0L;
    }

    private /* synthetic */ void ALLATORIxDEMO(dc a2, Charset a3, String a4, uc a5) throws yk {
        ec a6 = cf.c(a2, a4);
        if (a6 != null) {
            ib a7;
            a7.ALLATORIxDEMO(a6, a5, a3);
        }
    }
}

