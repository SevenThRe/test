/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

public class jh {
    private final int[] m = new int[3];
    private static final int[] j;

    static {
        int n2;
        j = new int[256];
        int n3 = n2 = 0;
        while (n3 < 256) {
            int n4;
            int n5 = n2;
            int n6 = n4 = 0;
            while (n6 < 8) {
                n5 = (n5 & 1) == 1 ? n5 >>> 1 ^ 0xEDB88320 : (n5 >>>= 1);
                n6 = ++n4;
            }
            jh.j[n2++] = n5;
            n3 = n2;
        }
    }

    public byte r() {
        jh a2;
        int n2 = a2.m[2] | 2;
        return (byte)(n2 * (n2 ^ 1) >>> 8);
    }

    private /* synthetic */ int r(int a2, byte a3) {
        return a2 >>> 8 ^ j[(a2 ^ a3) & 0xFF];
    }

    public void r(char[] a2) {
        int n2;
        jh a3;
        jh jh2 = a3;
        jh2.m[0] = 305419896;
        jh2.m[1] = 591751049;
        jh2.m[2] = 878082192;
        int n3 = n2 = 0;
        while (n3 < a2.length) {
            char c2 = a2[n2];
            a3.r((byte)(c2 & 0xFF));
            n3 = ++n2;
        }
    }

    public jh() {
        jh a2;
    }

    public void r(byte a2) {
        jh a3;
        jh jh2 = a3;
        jh jh3 = a3;
        jh2.m[0] = jh3.r(a3.m[0], a2);
        jh3.m[1] = jh3.m[1] + (a3.m[0] & 0xFF);
        jh2.m[1] = a3.m[1] * 134775813 + 1;
        jh jh4 = a3;
        jh2.m[2] = jh4.r(a3.m[2], (byte)(jh4.m[1] >> 24));
    }
}

