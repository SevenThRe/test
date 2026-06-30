/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

public class wn {
    public wn() {
        wn a2;
    }

    public static void ALLATORIxDEMO(byte[] a2, int a3) {
        a2[0] = (byte)a3;
        a2[1] = (byte)(a3 >> 8);
        a2[2] = (byte)(a3 >> 16);
        a2[3] = (byte)(a3 >> 24);
        for (int a4 = 4; a4 <= 15; ++a4) {
            a2[a4] = 0;
        }
    }
}

