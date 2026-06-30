/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.hc;
import eos.moe.dragoncore.hg;
import eos.moe.dragoncore.il;
import eos.moe.dragoncore.vm;
import eos.moe.dragoncore.yk;
import java.io.IOException;

public class jm
extends vm<hg> {
    public jm(il a2, hc a3, char[] a4) throws IOException, yk {
        super(a2, a3, a4);
        jm a5;
    }

    @Override
    public hg ALLATORIxDEMO(hc a2, char[] a3) throws yk, IOException {
        jm a4;
        return new hg(a3, a2.ALLATORIxDEMO(), a4.c());
    }

    private /* synthetic */ byte[] c() throws IOException {
        jm a2;
        byte[] a3 = new byte[12];
        a2.ALLATORIxDEMO(a3);
        return a3;
    }
}

