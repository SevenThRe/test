/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ab;
import eos.moe.dragoncore.cf;
import eos.moe.dragoncore.dc;
import eos.moe.dragoncore.eb;
import eos.moe.dragoncore.ec;
import eos.moe.dragoncore.jb;
import eos.moe.dragoncore.qa;
import eos.moe.dragoncore.tf;
import eos.moe.dragoncore.uc;
import eos.moe.dragoncore.wm;
import java.io.IOException;
import java.nio.charset.Charset;

public class pb
extends ab<eb> {
    private char[] k;
    private wm ALLATORIxDEMO;

    public pb(dc a2, char[] a3, jb a4) {
        super(a2, a4);
        pb a5;
        a5.k = a3;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void ALLATORIxDEMO(eb a2, uc a3) throws IOException {
        pb a4;
        try (tf a5 = a4.ALLATORIxDEMO((Charset)((Object)a2.ALLATORIxDEMO));){
            for (ec a6 : a4.ALLATORIxDEMO().ALLATORIxDEMO().ALLATORIxDEMO()) {
                if (a6.ALLATORIxDEMO().startsWith("__MACOSX")) {
                    a3.c(a6.ALLATORIxDEMO());
                    continue;
                }
                a4.ALLATORIxDEMO.ALLATORIxDEMO(a6);
                a4.ALLATORIxDEMO(a5, a6, eb.ALLATORIxDEMO(a2), null, a3);
                a4.ALLATORIxDEMO();
            }
        }
        finally {
            if (a4.ALLATORIxDEMO != null) {
                a4.ALLATORIxDEMO.close();
            }
        }
    }

    @Override
    public long ALLATORIxDEMO(eb a2) {
        pb a3;
        return cf.ALLATORIxDEMO(a3.ALLATORIxDEMO().ALLATORIxDEMO().ALLATORIxDEMO());
    }

    private /* synthetic */ tf ALLATORIxDEMO(Charset a2) throws IOException {
        pb a3;
        a3.ALLATORIxDEMO = qa.ALLATORIxDEMO(a3.ALLATORIxDEMO());
        ec a4 = a3.ALLATORIxDEMO(a3.ALLATORIxDEMO());
        if (a4 != null) {
            a3.ALLATORIxDEMO.ALLATORIxDEMO(a4);
        }
        return new tf(a3.ALLATORIxDEMO, a3.k, a2);
    }

    private /* synthetic */ ec ALLATORIxDEMO(dc a2) {
        if (a2.ALLATORIxDEMO() == null || a2.ALLATORIxDEMO().ALLATORIxDEMO() == null || a2.ALLATORIxDEMO().ALLATORIxDEMO().size() == 0) {
            return null;
        }
        return a2.ALLATORIxDEMO().ALLATORIxDEMO().get(0);
    }
}

