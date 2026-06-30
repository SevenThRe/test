/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

public class ho {
    public ho() {
        ho a2;
    }

    public static void r(byte[] a2, int a3) {
        a2[0] = (byte)a3;
        a2[1] = (byte)(a3 >> 8);
        a2[2] = (byte)(a3 >> 16);
        a2[3] = (byte)(a3 >> 24);
        int n2 = a3 = 4;
        while (n2 <= 15) {
            a2[a3++] = 0;
            n2 = a3;
        }
    }
}

