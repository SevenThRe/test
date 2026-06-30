/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.j;
import eos.moe.dragoncore.sl;
import eos.moe.dragoncore.ta;
import eos.moe.dragoncore.ym;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class zl {
    private ym k;
    private j ALLATORIxDEMO;

    public zl(ym a2) {
        a3(a2, null);
        zl a3;
    }

    public zl(ym a2, j a3) {
        zl a4;
        a4.k = a2;
        a4.ALLATORIxDEMO = a3;
    }

    public byte[] ALLATORIxDEMO(char[] a2) {
        zl a3;
        return a3.ALLATORIxDEMO(a2, 0);
    }

    public byte[] ALLATORIxDEMO(char[] a2, int a3) {
        zl a4;
        if (a2 == null) {
            throw new NullPointerException();
        }
        byte[] a5 = ta.ALLATORIxDEMO(a2);
        a4.ALLATORIxDEMO(a5);
        if (a3 == 0) {
            a3 = a4.ALLATORIxDEMO.ALLATORIxDEMO();
        }
        return a4.ALLATORIxDEMO(a4.ALLATORIxDEMO, a4.k.c(), a4.k.ALLATORIxDEMO(), a3);
    }

    public boolean ALLATORIxDEMO(char[] a2) {
        zl a3;
        byte[] a4 = a3.ALLATORIxDEMO().ALLATORIxDEMO();
        if (a4 == null || a4.length == 0) {
            return false;
        }
        byte[] a5 = a3.ALLATORIxDEMO(a2, a4.length);
        if (a5 == null || a5.length != a4.length) {
            return false;
        }
        for (int a6 = 0; a6 < a5.length; ++a6) {
            if (a5[a6] == a4[a6]) continue;
            return false;
        }
        return true;
    }

    private /* synthetic */ void ALLATORIxDEMO(byte[] a2) {
        zl a3;
        if (a3.ALLATORIxDEMO == null) {
            a3.ALLATORIxDEMO = new sl(a3.k.c());
        }
        a3.ALLATORIxDEMO.ALLATORIxDEMO(a2);
    }

    public j ALLATORIxDEMO() {
        zl a2;
        return a2.ALLATORIxDEMO;
    }

    private /* synthetic */ byte[] ALLATORIxDEMO(j a2, byte[] a3, int a4, int a5) {
        zl a6;
        if (a3 == null) {
            a3 = new byte[]{};
        }
        int a7 = a2.ALLATORIxDEMO();
        int a8 = a6.ALLATORIxDEMO(a5, a7);
        int a9 = a5 - (a8 - 1) * a7;
        byte[] a10 = new byte[a8 * a7];
        int a11 = 0;
        for (int a12 = 1; a12 <= a8; ++a12) {
            a6.ALLATORIxDEMO(a10, a11, a2, a3, a4, a12);
            a11 += a7;
        }
        if (a9 < a7) {
            byte[] a13 = new byte[a5];
            System.arraycopy(a10, 0, a13, 0, a5);
            return a13;
        }
        return a10;
    }

    private /* synthetic */ int ALLATORIxDEMO(int a2, int a3) {
        int a4 = 0;
        if (a2 % a3 > 0) {
            a4 = 1;
        }
        return a2 / a3 + a4;
    }

    private /* synthetic */ void ALLATORIxDEMO(byte[] a2, int a3, j a4, byte[] a5, int a6, int a7) {
        zl a8;
        int a9 = a4.ALLATORIxDEMO();
        byte[] a10 = new byte[a9];
        byte[] a11 = new byte[a5.length + 4];
        System.arraycopy(a5, 0, a11, 0, a5.length);
        a8.ALLATORIxDEMO(a11, a5.length, a7);
        for (int a12 = 0; a12 < a6; ++a12) {
            a11 = a4.ALLATORIxDEMO(a11);
            a8.ALLATORIxDEMO(a10, a11);
        }
        System.arraycopy(a10, 0, a2, a3, a9);
    }

    private /* synthetic */ void ALLATORIxDEMO(byte[] a2, byte[] a3) {
        for (int a4 = 0; a4 < a2.length; ++a4) {
            int n2 = a4;
            a2[n2] = (byte)(a2[n2] ^ a3[a4]);
        }
    }

    public void ALLATORIxDEMO(byte[] a2, int a3, int a4) {
        a2[a3] = (byte)(a4 / 0x1000000);
        a2[a3 + 1] = (byte)(a4 / 65536);
        a2[a3 + 2] = (byte)(a4 / 256);
        a2[a3 + 3] = (byte)a4;
    }

    public ym ALLATORIxDEMO() {
        zl a2;
        return a2.k;
    }

    public void ALLATORIxDEMO(ym a2) {
        a.k = a2;
    }

    public void ALLATORIxDEMO(j a2) {
        a.ALLATORIxDEMO = a2;
    }
}

