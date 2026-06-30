/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.a;
import eos.moe.dragoncore.em;
import eos.moe.dragoncore.nc;
import eos.moe.dragoncore.sl;
import eos.moe.dragoncore.wn;
import eos.moe.dragoncore.xh;
import eos.moe.dragoncore.yk;
import eos.moe.dragoncore.ym;
import eos.moe.dragoncore.zk;
import eos.moe.dragoncore.zl;
import java.util.Arrays;

public class cn
implements a {
    public static final int m = 2;
    private em c;
    private char[] q;
    private xh b;
    private sl o;
    private int y = 1;
    private byte[] k;
    private byte[] ALLATORIxDEMO;

    public cn(em a2, char[] a3, byte[] a4, byte[] a5) throws yk {
        cn a6;
        a6.c = a2;
        a6.q = a3;
        a6.k = new byte[16];
        a6.ALLATORIxDEMO = new byte[16];
        a6.ALLATORIxDEMO(a4, a5);
    }

    private /* synthetic */ void ALLATORIxDEMO(byte[] a2, byte[] a3) throws yk {
        cn a4;
        nc a5 = a4.c.ALLATORIxDEMO();
        if (a4.q == null || a4.q.length <= 0) {
            throw new yk("empty or null password provided for AES Decryptor");
        }
        byte[] a6 = a4.ALLATORIxDEMO(a2, a4.q, a5.ALLATORIxDEMO(), a5.c());
        if (a6 == null || a6.length != a5.ALLATORIxDEMO() + a5.c() + 2) {
            throw new yk("invalid derived key");
        }
        byte[] a7 = new byte[a5.ALLATORIxDEMO()];
        byte[] a8 = new byte[a5.c()];
        byte[] a9 = new byte[2];
        System.arraycopy(a6, 0, a7, 0, a5.ALLATORIxDEMO());
        System.arraycopy(a6, a5.ALLATORIxDEMO(), a8, 0, a5.c());
        System.arraycopy(a6, a5.ALLATORIxDEMO() + a5.c(), a9, 0, 2);
        if (!Arrays.equals(a3, a9)) {
            throw new yk("wrong password", zk.c);
        }
        a4.b = new xh(a7);
        a4.o = new sl("HmacSHA1");
        a4.o.ALLATORIxDEMO(a8);
    }

    @Override
    public int ALLATORIxDEMO(byte[] a2, int a3, int a4) throws yk {
        for (int a5 = a3; a5 < a3 + a4; a5 += 16) {
            cn a6;
            int a7 = a5 + 16 <= a3 + a4 ? 16 : a3 + a4 - a5;
            a6.o.ALLATORIxDEMO(a2, a5, a7);
            wn.ALLATORIxDEMO(a6.k, a6.y);
            a6.b.ALLATORIxDEMO(a6.k, a6.ALLATORIxDEMO);
            for (int a8 = 0; a8 < a7; ++a8) {
                a2[a5 + a8] = (byte)(a2[a5 + a8] ^ a6.ALLATORIxDEMO[a8]);
            }
            ++a6.y;
        }
        return a4;
    }

    private /* synthetic */ byte[] ALLATORIxDEMO(byte[] a2, char[] a3, int a4, int a5) {
        ym a6 = new ym("HmacSHA1", "ISO-8859-1", a2, 1000);
        zl a7 = new zl(a6);
        return a7.ALLATORIxDEMO(a3, a4 + a5 + 2);
    }

    public byte[] ALLATORIxDEMO() {
        cn a2;
        return a2.o.ALLATORIxDEMO();
    }
}

