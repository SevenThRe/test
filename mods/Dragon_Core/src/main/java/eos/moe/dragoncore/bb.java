/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.dc;
import eos.moe.dragoncore.jb;
import eos.moe.dragoncore.mg;
import eos.moe.dragoncore.ob;
import eos.moe.dragoncore.pc;
import eos.moe.dragoncore.re;
import eos.moe.dragoncore.tb;
import eos.moe.dragoncore.uc;
import eos.moe.dragoncore.vc;
import eos.moe.dragoncore.yk;
import java.io.IOException;
import java.nio.charset.Charset;

public class bb
extends ob<tb> {
    private dc ALLATORIxDEMO;

    public bb(dc a2, jb a3) {
        super(a3);
        bb a4;
        a4.ALLATORIxDEMO = a2;
    }

    @Override
    public void ALLATORIxDEMO(tb a2, uc a3) throws IOException {
        bb a4;
        if (tb.ALLATORIxDEMO(a2) == null) {
            throw new yk("comment is null, cannot update Zip file with comment");
        }
        pc a5 = a4.ALLATORIxDEMO.ALLATORIxDEMO();
        a5.ALLATORIxDEMO(tb.ALLATORIxDEMO(a2));
        try (re a6 = new re(a4.ALLATORIxDEMO.ALLATORIxDEMO());){
            if (a4.ALLATORIxDEMO.c()) {
                a6.ALLATORIxDEMO(a4.ALLATORIxDEMO.ALLATORIxDEMO().ALLATORIxDEMO());
            } else {
                a6.ALLATORIxDEMO(a5.c());
            }
            mg a7 = new mg();
            a7.ALLATORIxDEMO(a4.ALLATORIxDEMO, a6, (Charset)((Object)a2.ALLATORIxDEMO));
        }
    }

    @Override
    public long ALLATORIxDEMO(tb a2) {
        return 0L;
    }

    @Override
    public vc ALLATORIxDEMO() {
        return vc.y;
    }
}

