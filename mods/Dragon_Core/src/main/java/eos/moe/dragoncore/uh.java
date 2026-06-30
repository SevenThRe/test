/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.cj;
import eos.moe.dragoncore.ki;
import eos.moe.dragoncore.lc;
import eos.moe.dragoncore.rk;
import eos.moe.dragoncore.ta;
import eos.moe.dragoncore.yk;
import java.io.IOException;
import java.io.OutputStream;

public class uh
extends rk<ki> {
    public uh(cj a2, lc a3, char[] a4) throws IOException, yk {
        super(a2, a3, a4);
        uh a5;
    }

    @Override
    public ki ALLATORIxDEMO(OutputStream a2, lc a3, char[] a4) throws IOException, yk {
        uh a5;
        long a6 = a5.ALLATORIxDEMO(a3);
        ki a7 = new ki(a4, a6);
        a5.ALLATORIxDEMO(a7.ALLATORIxDEMO());
        return a7;
    }

    @Override
    public void write(int a2) throws IOException {
        uh a3;
        a3.write(new byte[]{(byte)a2});
    }

    @Override
    public void write(byte[] a2) throws IOException {
        uh a3;
        a3.write(a2, 0, a2.length);
    }

    @Override
    public void write(byte[] a2, int a3, int a4) throws IOException {
        uh a5;
        super.write(a2, a3, a4);
    }

    private /* synthetic */ long ALLATORIxDEMO(lc a2) {
        if (a2.f()) {
            long a3 = ta.x(a2.c());
            return (a3 & 0xFFFFL) << 16;
        }
        return a2.f();
    }
}

