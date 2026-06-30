/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.a;
import eos.moe.dragoncore.tl;
import eos.moe.dragoncore.yk;
import eos.moe.dragoncore.zk;

public class hg
implements a {
    private char[] o;
    private byte[] y;
    private byte[] k = new byte[4];
    private tl ALLATORIxDEMO;

    public hg(char[] a2, byte[] a3, byte[] a4) throws yk {
        hg a5;
        a5.o = a2;
        a5.y = a3;
        a5.ALLATORIxDEMO = new tl();
        a5.ALLATORIxDEMO(a4);
    }

    @Override
    public int ALLATORIxDEMO(byte[] a2, int a3, int a4) throws yk {
        if (a3 < 0 || a4 < 0) {
            throw new yk("one of the input parameters were null in standard decrypt data");
        }
        for (int a5 = a3; a5 < a3 + a4; ++a5) {
            hg a6;
            int a7 = a2[a5] & 0xFF;
            a7 = (a7 ^ a6.ALLATORIxDEMO.ALLATORIxDEMO()) & 0xFF;
            a6.ALLATORIxDEMO.ALLATORIxDEMO((byte)a7);
            a2[a5] = (byte)a7;
        }
        return a4;
    }

    private /* synthetic */ void ALLATORIxDEMO(byte[] a2) throws yk {
        hg a3;
        a3.k[3] = (byte)(a3.y[3] & 0xFF);
        a3.k[2] = (byte)(a3.y[3] >> 8 & 0xFF);
        a3.k[1] = (byte)(a3.y[3] >> 16 & 0xFF);
        a3.k[0] = (byte)(a3.y[3] >> 24 & 0xFF);
        if (a3.k[2] > 0 || a3.k[1] > 0 || a3.k[0] > 0) {
            throw new IllegalStateException("Invalid CRC in File Header");
        }
        if (a3.o == null || a3.o.length <= 0) {
            throw new yk("Wrong password!", zk.c);
        }
        a3.ALLATORIxDEMO.ALLATORIxDEMO(a3.o);
        byte a4 = a2[0];
        for (int a5 = 0; a5 < 12; ++a5) {
            a3.ALLATORIxDEMO.ALLATORIxDEMO((byte)(a4 ^ a3.ALLATORIxDEMO.ALLATORIxDEMO()));
            if (a5 + 1 == 12) continue;
            a4 = a2[a5 + 1];
        }
    }
}

