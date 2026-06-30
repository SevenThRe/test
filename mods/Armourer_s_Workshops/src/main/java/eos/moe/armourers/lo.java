/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

public class lo {
    public static int r(int a2, int a3, int a4) {
        int[] nArray = new int[]{a2 >>> 24 & 0xFF, a2 >>> 16 & 0xFF, a2 >>> 8 & 0xFF, a2 & 0xFF};
        nArray[a3] = a4;
        return (nArray[0] << 24) + (nArray[1] << 16) + (nArray[2] << 8) + nArray[3];
    }

    public lo() {
        lo a2;
    }

    public static int r(int a2, int a3) {
        return a2 >>> (3 - a3) * 8 & 0xFF;
    }
}

