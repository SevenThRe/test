/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.gf;
import eos.moe.armourers.ho;
import eos.moe.armourers.kg;
import eos.moe.armourers.pf;
import eos.moe.armourers.ph;
import eos.moe.armourers.v;
import eos.moe.armourers.vb;
import eos.moe.armourers.xi;
import java.util.Random;

public class an
implements v {
    private vb b;
    private int g;
    private static final int z = 2;
    private byte[] t;
    private xi w;
    private byte[] r;
    private char[] l;
    private int c;
    private byte[] v;
    private boolean s;
    private byte[] m;
    private gf j;

    public byte[] z() {
        an a2;
        return a2.v;
    }

    private static /* synthetic */ byte[] r(int a2) throws ph {
        int n2;
        if (a2 != 8 && a2 != 16) {
            throw new ph("invalid salt size, cannot generate salt");
        }
        int n3 = 0;
        if (a2 == 8) {
            n3 = 2;
        }
        if (a2 == 16) {
            n3 = 4;
        }
        byte[] byArray = new byte[a2];
        int n4 = n2 = 0;
        while (n4 < n3) {
            int n5 = new Random().nextInt();
            byArray[0 + n2 * 4] = (byte)(n5 >> 24);
            byArray[1 + n2 * 4] = (byte)(n5 >> 16);
            byArray[2 + n2 * 4] = (byte)(n5 >> 8);
            int n6 = 3 + n2 * 4;
            byArray[n6] = (byte)n5;
            n4 = ++n2;
        }
        return byArray;
    }

    public byte[] y() {
        an a2;
        return a2.r;
    }

    @Override
    public int r(byte[] a2) throws ph {
        an a3;
        if (a2 == null) {
            throw new ph("input bytes are null, cannot perform AES encrpytion");
        }
        return a3.r(a2, 0, a2.length);
    }

    private /* synthetic */ byte[] r(byte[] a22, char[] a3, int a4, int a5) throws ph {
        try {
            a22 = new kg("HmacSHA1", "ISO-8859-1", (byte[])a22, 1000);
            a3 = new pf((kg)a22).r((char[])a3, a4 + a5 + 2);
            return a3;
        }
        catch (Exception a22) {
            throw new ph(a22);
        }
    }

    private /* synthetic */ void r() throws ph {
        an a2;
        an an2 = a2;
        int n2 = an2.b.y();
        int n3 = an2.b.h();
        an2.v = an.r(an2.b.r());
        byte[] byArray = an2.r(an2.v, a2.l, n2, n3);
        if (byArray == null || byArray.length != n2 + n3 + 2) {
            throw new ph("invalid key generated, cannot decrypt file");
        }
        byte[] byArray2 = new byte[n2];
        byte[] byArray3 = new byte[n3];
        a2.r = new byte[2];
        byte[] byArray4 = byArray;
        System.arraycopy(byArray4, 0, byArray2, 0, n2);
        System.arraycopy(byArray, n2, byArray3, 0, n3);
        System.arraycopy(byArray4, n2 + n3, a2.r, 0, 2);
        an an3 = a2;
        a2.w = new xi(byArray2);
        an3.j = new gf("HmacSHA1");
        a2.j.r(byArray3);
    }

    public an(char[] a2, vb a3) throws ph {
        an a4;
        an an2 = a4;
        an2.c = 1;
        an2.g = 0;
        if (a2 == null || a2.length == 0) {
            throw new ph("input password is empty or null");
        }
        if (a3 != vb.r && a3 != vb.w) {
            throw new ph("Invalid AES key strength");
        }
        an an3 = a4;
        an an4 = a4;
        a4.l = a2;
        an4.b = a3;
        an4.s = false;
        an3.t = new byte[16];
        an3.m = new byte[16];
        a4.r();
    }

    @Override
    public int r(byte[] a2, int a3, int a4) throws ph {
        int n2;
        an a5;
        if (a5.s) {
            throw new ph("AES Encrypter is in finished state (A non 16 byte block has already been passed to encrypter)");
        }
        if (a4 % 16 != 0) {
            a5.s = true;
        }
        int n3 = n2 = a3;
        while (n3 < a3 + a4) {
            a5.g = n2 + 16 <= a3 + a4 ? 16 : a3 + a4 - n2;
            an an2 = a5;
            ho.r(a5.m, an2.c);
            an an3 = a5;
            an2.w.r(an3.m, an3.t);
            int n4 = 0;
            int n5 = n4;
            while (n5 < a5.g) {
                a2[n2 + ++n4] = (byte)(a2[n2 + n4] ^ a5.t[n4]);
                n5 = n4;
            }
            an an4 = a5;
            an4.j.r(a2, n2, a5.g);
            ++an4.c;
            n3 = n2 += 16;
        }
        return a4;
    }

    public byte[] r() {
        an a2;
        byte[] byArray = a2.j.r();
        byte[] byArray2 = new byte[10];
        System.arraycopy(byArray, 0, byArray2, 0, 10);
        return byArray2;
    }
}

