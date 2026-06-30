/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.jh;
import eos.moe.armourers.ph;
import eos.moe.armourers.v;
import java.util.Random;

public class sl
implements v {
    private jh m;
    private byte[] j;

    public byte[] r() {
        sl a2;
        return a2.j;
    }

    public byte r(byte a2) {
        sl a3;
        byte by = (byte)(a2 ^ a3.m.r() & 0xFF);
        a3.m.r(a2);
        return by;
    }

    public sl(char[] a2, long a3) throws ph {
        sl a4;
        sl sl2 = a4;
        a4.m = new jh();
        a4.j = new byte[12];
        a4.r(a2, a3);
    }

    @Override
    public int r(byte[] a2, int a3, int a4) throws ph {
        int n2;
        if (a4 < 0) {
            throw new ph("invalid length specified to decrpyt data");
        }
        int n3 = n2 = a3;
        while (n3 < a3 + a4) {
            sl a5;
            a2[++n2] = a5.r(a2[n2]);
            n3 = n2;
        }
        return a4;
    }

    public byte[] r(int a22) throws ph {
        int n2;
        if (a22 <= 0) {
            throw new ph("size is either 0 or less than 0, cannot generate header for standard encryptor");
        }
        byte[] a22 = new byte[a22];
        Random random = new Random();
        int n3 = n2 = 0;
        while (n3 < a22.length) {
            sl a3;
            a22[n2++] = a3.r((byte)random.nextInt(256));
            n3 = n2;
        }
        return a22;
    }

    @Override
    public int r(byte[] a2) throws ph {
        sl a3;
        if (a2 == null) {
            throw new NullPointerException();
        }
        return a3.r(a2, 0, a2.length);
    }

    private /* synthetic */ void r(char[] a2, long a3) throws ph {
        sl a4;
        if (a2 == null || a2.length <= 0) {
            throw new ph("input password is null or empty, cannot initialize standard encrypter");
        }
        sl sl2 = a4;
        a4.m.r(a2);
        a4.j = sl2.r(12);
        sl2.m.r(a2);
        sl2.j[11] = (byte)(a3 >>> 24);
        sl2.j[10] = (byte)(a3 >>> 16);
        if (sl2.j.length < 12) {
            throw new ph("invalid header bytes generated, cannot perform standard encryption");
        }
        sl sl3 = a4;
        sl3.r(sl3.j);
    }
}

