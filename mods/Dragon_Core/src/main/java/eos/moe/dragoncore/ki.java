/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.s;
import eos.moe.dragoncore.tl;
import eos.moe.dragoncore.yk;
import java.util.Random;

public class ki
implements s {
    private tl k = new tl();
    private byte[] ALLATORIxDEMO = new byte[12];

    public ki(char[] a2, long a3) throws yk {
        ki a4;
        a4.ALLATORIxDEMO(a2, a3);
    }

    private /* synthetic */ void ALLATORIxDEMO(char[] a2, long a3) throws yk {
        ki a4;
        if (a2 == null || a2.length <= 0) {
            throw new yk("input password is null or empty, cannot initialize standard encrypter");
        }
        a4.k.ALLATORIxDEMO(a2);
        a4.ALLATORIxDEMO = a4.ALLATORIxDEMO(12);
        a4.k.ALLATORIxDEMO(a2);
        a4.ALLATORIxDEMO[11] = (byte)(a3 >>> 24);
        a4.ALLATORIxDEMO[10] = (byte)(a3 >>> 16);
        if (a4.ALLATORIxDEMO.length < 12) {
            throw new yk("invalid header bytes generated, cannot perform standard encryption");
        }
        a4.ALLATORIxDEMO(a4.ALLATORIxDEMO);
    }

    @Override
    public int ALLATORIxDEMO(byte[] a2) throws yk {
        ki a3;
        if (a2 == null) {
            throw new NullPointerException();
        }
        return a3.ALLATORIxDEMO(a2, 0, a2.length);
    }

    @Override
    public int ALLATORIxDEMO(byte[] a2, int a3, int a4) throws yk {
        if (a4 < 0) {
            throw new yk("invalid length specified to decrpyt data");
        }
        for (int a5 = a3; a5 < a3 + a4; ++a5) {
            ki a6;
            a2[a5] = a6.ALLATORIxDEMO(a2[a5]);
        }
        return a4;
    }

    public byte ALLATORIxDEMO(byte a2) {
        ki a3;
        byte a4 = (byte)(a2 ^ a3.k.ALLATORIxDEMO() & 0xFF);
        a3.k.ALLATORIxDEMO(a2);
        return a4;
    }

    public byte[] ALLATORIxDEMO(int a2) throws yk {
        if (a2 <= 0) {
            throw new yk("size is either 0 or less than 0, cannot generate header for standard encryptor");
        }
        byte[] a3 = new byte[a2];
        Random a4 = new Random();
        for (int a5 = 0; a5 < a3.length; ++a5) {
            ki a6;
            a3[a5] = a6.ALLATORIxDEMO((byte)a4.nextInt(256));
        }
        return a3;
    }

    public byte[] ALLATORIxDEMO() {
        ki a2;
        return a2.ALLATORIxDEMO;
    }
}

