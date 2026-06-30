/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class xb {
    public xb() {
        xb a2;
    }

    public static boolean ALLATORIxDEMO(byte a2, int a3) {
        return ((long)a2 & 1L << a3) != 0L;
    }

    public static byte c(byte a2, int a3) {
        return (byte)(a2 | 1 << a3);
    }

    public static byte ALLATORIxDEMO(byte a2, int a3) {
        return (byte)(a2 & ~(1 << a3));
    }
}

