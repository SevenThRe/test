/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ab;
import eos.moe.dragoncore.cf;
import eos.moe.dragoncore.dc;
import eos.moe.dragoncore.ec;
import eos.moe.dragoncore.jb;
import eos.moe.dragoncore.qa;
import eos.moe.dragoncore.ta;
import eos.moe.dragoncore.tf;
import eos.moe.dragoncore.uc;
import eos.moe.dragoncore.vb;
import eos.moe.dragoncore.wm;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

public class yb
extends ab<vb> {
    private char[] k;
    private wm ALLATORIxDEMO;

    public yb(dc a2, char[] a3, jb a4) {
        super(a2, a4);
        yb a5;
        a5.k = a3;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void ALLATORIxDEMO(vb a2, uc a3) throws IOException {
        yb a4;
        try (tf a5 = a4.ALLATORIxDEMO(vb.ALLATORIxDEMO(a2), (Charset)((Object)a2.ALLATORIxDEMO));){
            List<ec> a6 = a4.ALLATORIxDEMO(vb.ALLATORIxDEMO(a2));
            for (ec a7 : a6) {
                String a8 = a4.ALLATORIxDEMO(vb.c(a2), vb.ALLATORIxDEMO(a2), a7);
                a4.ALLATORIxDEMO(a5, a7, vb.ALLATORIxDEMO(a2), a8, a3);
            }
        }
        finally {
            if (a4.ALLATORIxDEMO != null) {
                a4.ALLATORIxDEMO.close();
            }
        }
    }

    @Override
    public long ALLATORIxDEMO(vb a2) {
        yb a3;
        List<ec> a4 = a3.ALLATORIxDEMO(vb.ALLATORIxDEMO(a2));
        return cf.ALLATORIxDEMO(a4);
    }

    private /* synthetic */ List<ec> ALLATORIxDEMO(ec a2) {
        yb a3;
        if (!a2.ALLATORIxDEMO()) {
            return Collections.singletonList(a2);
        }
        return cf.ALLATORIxDEMO(a3.ALLATORIxDEMO().ALLATORIxDEMO().ALLATORIxDEMO(), a2);
    }

    private /* synthetic */ tf ALLATORIxDEMO(ec a2, Charset a3) throws IOException {
        yb a4;
        a4.ALLATORIxDEMO = qa.ALLATORIxDEMO(a4.ALLATORIxDEMO());
        a4.ALLATORIxDEMO.ALLATORIxDEMO(a2);
        return new tf(a4.ALLATORIxDEMO, a4.k, a3);
    }

    private /* synthetic */ String ALLATORIxDEMO(String a2, ec a3, ec a4) {
        if (!ta.ALLATORIxDEMO(a2)) {
            return a2;
        }
        if (!a3.ALLATORIxDEMO()) {
            return a2;
        }
        String a5 = "/";
        if (a2.endsWith("/")) {
            a5 = "";
        }
        return a4.ALLATORIxDEMO().replaceFirst(a3.ALLATORIxDEMO(), a2 + a5);
    }
}

