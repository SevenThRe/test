/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.gf;
import eos.moe.armourers.ho;
import eos.moe.armourers.kg;
import eos.moe.armourers.l;
import eos.moe.armourers.pf;
import eos.moe.armourers.ph;
import eos.moe.armourers.uh;
import eos.moe.armourers.vb;
import eos.moe.armourers.wc;
import eos.moe.armourers.xi;
import java.util.Arrays;

public class tl
implements l {
    private byte[] w;
    private wc r;
    public static final int l = 2;
    private gf c;
    private char[] v;
    private byte[] s;
    private int m;
    private xi j;

    @Override
    public int r(byte[] a2, int a3, int a4) throws ph {
        int n2;
        int n3 = n2 = a3;
        while (n3 < a3 + a4) {
            tl a5;
            int n4 = n2 + 16 <= a3 + a4 ? 16 : a3 + a4 - n2;
            tl tl2 = a5;
            tl2.c.r(a2, n2, n4);
            tl tl3 = a5;
            ho.r(tl2.s, tl3.m);
            tl tl4 = a5;
            tl3.j.r(tl4.s, tl4.w);
            int n5 = 0;
            int n6 = n5;
            while (n6 < n4) {
                a2[n2 + ++n5] = (byte)(a2[n2 + n5] ^ a5.w[n5]);
                n6 = n5;
            }
            ++a5.m;
            n3 = n2 += 16;
        }
        return a4;
    }

    private /* synthetic */ byte[] r(byte[] a2, char[] a3, int a4, int a5) {
        a2 = new kg("HmacSHA1", "ISO-8859-1", (byte[])a2, 1000);
        return new pf((kg)a2).r(a3, a4 + a5 + 2);
    }

    public byte[] r() {
        tl a2;
        return a2.c.r();
    }

    private /* synthetic */ void r(byte[] a2, byte[] a3) throws ph {
        tl a4;
        tl tl2 = a4;
        vb vb2 = tl2.r.r();
        if (tl2.v == null || a4.v.length <= 0) {
            throw new ph("empty or null password provided for AES Decryptor");
        }
        if ((a2 = a4.r(a2, a4.v, vb2.y(), vb2.h())) == null || a2.length != vb2.y() + vb2.h() + 2) {
            throw new ph("invalid derived key");
        }
        vb vb3 = vb2;
        byte[] byArray = new byte[vb3.y()];
        byte[] byArray2 = new byte[vb3.h()];
        byte[] byArray3 = new byte[2];
        vb vb4 = vb2;
        System.arraycopy(a2, 0, byArray, 0, vb2.y());
        System.arraycopy(a2, vb4.y(), byArray2, 0, vb2.h());
        System.arraycopy(a2, vb4.y() + vb2.h(), byArray3, 0, 2);
        if (!Arrays.equals(a3, byArray3)) {
            throw new ph("Wrong Password", uh.m);
        }
        a4.j = new xi(byArray);
        tl tl3 = a4;
        tl3.c = new gf("HmacSHA1");
        a4.c.r(byArray2);
    }

    public tl(wc a2, char[] a3, byte[] a4, byte[] a5) throws ph {
        tl a6;
        tl tl2 = a6;
        tl tl3 = a6;
        tl3.m = 1;
        tl3.r = a2;
        tl2.v = a3;
        tl2.s = new byte[16];
        a6.w = new byte[16];
        a6.r(a4, a5);
    }
}

