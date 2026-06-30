/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.c;
import eos.moe.armourers.gf;
import eos.moe.armourers.kg;
import eos.moe.armourers.m;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class pf {
    private m m;
    private kg j;

    private /* synthetic */ void r(byte[] a2) {
        pf a3;
        if (a3.m == null) {
            pf pf2 = a3;
            a3.m = new gf(a3.j.r());
        }
        a3.m.r(a2);
    }

    private /* synthetic */ void r(byte[] a2, int a3, m a4, byte[] a5, int a6, int a7) {
        pf a8;
        int n2 = a4.r();
        byte[] byArray = new byte[n2];
        byte[] byArray2 = new byte[a5.length + 4];
        System.arraycopy(a5, 0, byArray2, 0, a5.length);
        a8.r(byArray2, a5.length, a7);
        int n3 = a5 = 0;
        while (n3 < a6) {
            byArray2 = a4.r(byArray2);
            a8.r(byArray, byArray2);
            n3 = ++a5;
        }
        System.arraycopy(byArray, 0, a2, a3, n2);
    }

    public void r(m a2) {
        a.m = a2;
    }

    public void r(byte[] a2, int a3, int a4) {
        int n2 = a3;
        a2[a3] = (byte)(a4 / 0x1000000);
        a2[n2 + 1] = (byte)(a4 / 65536);
        a2[n2 + 2] = (byte)(a4 / 256);
        a2[a3 + 3] = (byte)a4;
    }

    public pf(kg a2) {
        a3(a2, null);
        pf a3;
    }

    private /* synthetic */ byte[] r(m a2, byte[] a3, int a4, int a5) {
        int n2;
        pf a6;
        if (a3 == null) {
            a3 = new byte[]{};
        }
        int n3 = a2.r();
        int n4 = a6.r(a5, n3);
        int n5 = a5 - (n4 - 1) * n3;
        byte[] byArray = new byte[n4 * n3];
        int n6 = 0;
        int n7 = n2 = 1;
        while (n7 <= n4) {
            int n8 = n6;
            a6.r(byArray, n8, a2, a3, a4, n2);
            n6 = n8 + n3;
            n7 = ++n2;
        }
        if (n5 < n3) {
            byte[] byArray2 = new byte[a5];
            System.arraycopy(byArray, 0, byArray2, 0, a5);
            return byArray2;
        }
        return byArray;
    }

    public byte[] r(char[] a2) {
        pf a3;
        return a3.r(a2, 0);
    }

    public void r(kg a2) {
        a.j = a2;
    }

    public m r() {
        pf a2;
        return a2.m;
    }

    public boolean r(char[] a2) {
        int n2;
        pf a3;
        byte[] byArray = a3.r().y();
        if (byArray == null || byArray.length == 0) {
            return false;
        }
        byte[] byArray2 = a3.r((char[])a2, byArray.length);
        a2 = byArray2;
        if (byArray2 == null || a2.length != byArray.length) {
            return false;
        }
        int n3 = n2 = 0;
        while (n3 < a2.length) {
            if (a2[n2] != byArray[n2]) {
                return false;
            }
            n3 = ++n2;
        }
        return true;
    }

    public pf(kg a2, m a3) {
        pf a4;
        pf pf2 = a4;
        pf2.j = a2;
        pf2.m = a3;
    }

    private /* synthetic */ void r(byte[] a2, byte[] a3) {
        int n2;
        int n3 = n2 = 0;
        while (n3 < a2.length) {
            int n4 = n2;
            byte by = (byte)(a2[n4] ^ a3[n2]);
            a2[n4] = by;
            n3 = ++n2;
        }
    }

    public byte[] r(char[] a2, int a3) {
        pf a4;
        if (a2 == null) {
            throw new NullPointerException();
        }
        a2 = c.r(a2);
        a4.r((byte[])a2);
        if (a3 == 0) {
            a3 = a4.m.r();
        }
        pf pf2 = a4;
        return pf2.r(pf2.m, pf2.j.r(), a4.j.r(), a3);
    }

    private /* synthetic */ int r(int a2, int a3) {
        int n2 = 0;
        if (a2 % a3 > 0) {
            n2 = 1;
        }
        return a2 / a3 + n2;
    }

    public kg r() {
        pf a2;
        return a2.j;
    }
}

