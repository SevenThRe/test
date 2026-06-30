/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.cj;
import eos.moe.dragoncore.hd;
import eos.moe.dragoncore.lc;
import eos.moe.dragoncore.rk;
import eos.moe.dragoncore.yk;
import java.io.IOException;
import java.io.OutputStream;

public class uk
extends rk<hd> {
    private byte[] k = new byte[16];
    private int ALLATORIxDEMO = 0;

    public uk(cj a2, lc a3, char[] a4) throws IOException, yk {
        super(a2, a3, a4);
        uk a5;
    }

    @Override
    public hd ALLATORIxDEMO(OutputStream a2, lc a3, char[] a4) throws IOException, yk {
        uk a5;
        hd a6 = new hd(a4, a3.ALLATORIxDEMO());
        a5.ALLATORIxDEMO(a6);
        return a6;
    }

    private /* synthetic */ void ALLATORIxDEMO(hd a2) throws IOException {
        uk a3;
        a3.ALLATORIxDEMO(a2.ALLATORIxDEMO());
        a3.ALLATORIxDEMO(a2.c());
    }

    @Override
    public void write(int a2) throws IOException {
        uk a3;
        a3.write(new byte[]{(byte)a2});
    }

    @Override
    public void write(byte[] a2) throws IOException {
        uk a3;
        a3.write(a2, 0, a2.length);
    }

    @Override
    public void write(byte[] a2, int a3, int a4) throws IOException {
        uk a5;
        if (a4 < 16 - a5.ALLATORIxDEMO) {
            System.arraycopy(a2, a3, a5.k, a5.ALLATORIxDEMO, a4);
            a5.ALLATORIxDEMO += a4;
            return;
        }
        System.arraycopy(a2, a3, a5.k, a5.ALLATORIxDEMO, 16 - a5.ALLATORIxDEMO);
        super.write(a5.k, 0, a5.k.length);
        a3 = 16 - a5.ALLATORIxDEMO;
        a5.ALLATORIxDEMO = 0;
        if ((a4 -= a3) != 0 && a4 % 16 != 0) {
            System.arraycopy(a2, a4 + a3 - a4 % 16, a5.k, 0, a4 % 16);
            a5.ALLATORIxDEMO = a4 % 16;
            a4 -= a5.ALLATORIxDEMO;
        }
        super.write(a2, a3, a4);
    }

    @Override
    public void ALLATORIxDEMO() throws IOException {
        uk a2;
        if (a2.ALLATORIxDEMO != 0) {
            super.write(a2.k, 0, a2.ALLATORIxDEMO);
            a2.ALLATORIxDEMO = 0;
        }
        a2.ALLATORIxDEMO(((hd)a2.ALLATORIxDEMO()).f());
        super.ALLATORIxDEMO();
    }
}

