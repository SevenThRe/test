/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.nc;
import eos.moe.dragoncore.s;
import eos.moe.dragoncore.sl;
import eos.moe.dragoncore.wn;
import eos.moe.dragoncore.xh;
import eos.moe.dragoncore.yk;
import eos.moe.dragoncore.ym;
import eos.moe.dragoncore.zl;
import java.util.Random;

public class hd
implements s {
    private static final int t = 2;
    private char[] r;
    private nc x;
    private xh v;
    private sl m;
    private boolean c;
    private int q = 1;
    private int b = 0;
    private byte[] o;
    private byte[] y;
    private byte[] k;
    private byte[] ALLATORIxDEMO;

    public hd(char[] a2, nc a3) throws yk {
        hd a4;
        if (a2 == null || a2.length == 0) {
            throw new yk("input password is empty or null");
        }
        if (a3 != nc.m && a3 != nc.q) {
            throw new yk("Invalid AES key strength");
        }
        a4.r = a2;
        a4.x = a3;
        a4.c = false;
        a4.y = new byte[16];
        a4.o = new byte[16];
        a4.ALLATORIxDEMO();
    }

    private /* synthetic */ void ALLATORIxDEMO() throws yk {
        hd a2;
        int a3 = a2.x.ALLATORIxDEMO();
        int a4 = a2.x.c();
        int a5 = a2.x.f();
        a2.ALLATORIxDEMO = hd.ALLATORIxDEMO(a5);
        byte[] a6 = a2.ALLATORIxDEMO(a2.ALLATORIxDEMO, a2.r, a3, a4);
        if (a6 == null || a6.length != a3 + a4 + 2) {
            throw new yk("invalid key generated, cannot decrypt file");
        }
        byte[] a7 = new byte[a3];
        byte[] a8 = new byte[a4];
        a2.k = new byte[2];
        System.arraycopy(a6, 0, a7, 0, a3);
        System.arraycopy(a6, a3, a8, 0, a4);
        System.arraycopy(a6, a3 + a4, a2.k, 0, 2);
        a2.v = new xh(a7);
        a2.m = new sl("HmacSHA1");
        a2.m.ALLATORIxDEMO(a8);
    }

    private /* synthetic */ byte[] ALLATORIxDEMO(byte[] a2, char[] a3, int a4, int a5) throws yk {
        try {
            ym a6 = new ym("HmacSHA1", "ISO-8859-1", a2, 1000);
            zl a7 = new zl(a6);
            byte[] a8 = a7.ALLATORIxDEMO(a3, a4 + a5 + 2);
            return a8;
        }
        catch (Exception a9) {
            throw new yk(a9);
        }
    }

    @Override
    public int ALLATORIxDEMO(byte[] a2) throws yk {
        hd a3;
        if (a2 == null) {
            throw new yk("input bytes are null, cannot perform AES encrpytion");
        }
        return a3.ALLATORIxDEMO(a2, 0, a2.length);
    }

    @Override
    public int ALLATORIxDEMO(byte[] a2, int a3, int a4) throws yk {
        hd a5;
        if (a5.c) {
            throw new yk("AES Encrypter is in finished state (A non 16 byte block has already been passed to encrypter)");
        }
        if (a4 % 16 != 0) {
            a5.c = true;
        }
        for (int a6 = a3; a6 < a3 + a4; a6 += 16) {
            a5.b = a6 + 16 <= a3 + a4 ? 16 : a3 + a4 - a6;
            wn.ALLATORIxDEMO(a5.o, a5.q);
            a5.v.ALLATORIxDEMO(a5.o, a5.y);
            for (int a7 = 0; a7 < a5.b; ++a7) {
                a2[a6 + a7] = (byte)(a2[a6 + a7] ^ a5.y[a7]);
            }
            a5.m.ALLATORIxDEMO(a2, a6, a5.b);
            ++a5.q;
        }
        return a4;
    }

    private static /* synthetic */ byte[] ALLATORIxDEMO(int a2) throws yk {
        if (a2 != 8 && a2 != 16) {
            throw new yk("invalid salt size, cannot generate salt");
        }
        int a3 = 0;
        if (a2 == 8) {
            a3 = 2;
        }
        if (a2 == 16) {
            a3 = 4;
        }
        byte[] a4 = new byte[a2];
        for (int a5 = 0; a5 < a3; ++a5) {
            Random a6 = new Random();
            int a7 = a6.nextInt();
            a4[0 + a5 * 4] = (byte)(a7 >> 24);
            a4[1 + a5 * 4] = (byte)(a7 >> 16);
            a4[2 + a5 * 4] = (byte)(a7 >> 8);
            a4[3 + a5 * 4] = (byte)a7;
        }
        return a4;
    }

    public byte[] f() {
        hd a2;
        byte[] a3 = a2.m.ALLATORIxDEMO();
        byte[] a4 = new byte[10];
        System.arraycopy(a3, 0, a4, 0, 10);
        return a4;
    }

    public byte[] c() {
        hd a2;
        return a2.k;
    }

    public byte[] ALLATORIxDEMO() {
        hd a2;
        return a2.ALLATORIxDEMO;
    }
}

