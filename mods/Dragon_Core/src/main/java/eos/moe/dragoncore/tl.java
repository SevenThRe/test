/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

public class tl {
    private final int[] k = new int[3];
    private static final int[] ALLATORIxDEMO = new int[256];

    public tl() {
        tl a2;
    }

    public void ALLATORIxDEMO(char[] a2) {
        a4.k[0] = 305419896;
        a4.k[1] = 591751049;
        a4.k[2] = 878082192;
        for (int a3 = 0; a3 < a2.length; ++a3) {
            tl a4;
            a4.ALLATORIxDEMO((byte)(a2[a3] & 0xFF));
        }
    }

    public void ALLATORIxDEMO(byte a2) {
        tl a3;
        a3.k[0] = a3.ALLATORIxDEMO(a3.k[0], a2);
        a3.k[1] = a3.k[1] + (a3.k[0] & 0xFF);
        a3.k[1] = a3.k[1] * 134775813 + 1;
        a3.k[2] = a3.ALLATORIxDEMO(a3.k[2], (byte)(a3.k[1] >> 24));
    }

    private /* synthetic */ int ALLATORIxDEMO(int a2, byte a3) {
        return a2 >>> 8 ^ ALLATORIxDEMO[(a2 ^ a3) & 0xFF];
    }

    public byte ALLATORIxDEMO() {
        tl a2;
        int a3 = a2.k[2] | 2;
        return (byte)(a3 * (a3 ^ 1) >>> 8);
    }

    static {
        for (int a2 = 0; a2 < 256; ++a2) {
            int a3 = a2;
            for (int a4 = 0; a4 < 8; ++a4) {
                if ((a3 & 1) == 1) {
                    a3 = a3 >>> 1 ^ 0xEDB88320;
                    continue;
                }
                a3 >>>= 1;
            }
            tl.ALLATORIxDEMO[a2] = a3;
        }
    }
}

