/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class wa {
    public static byte y(byte a2, int a3) {
        return (byte)(a2 | 1 << a3);
    }

    public wa() {
        wa a2;
    }

    public static boolean r(byte a2, int a3) {
        return ((long)a2 & 1L << a3) != 0L;
    }

    public static byte r(byte a2, int a3) {
        return (byte)(a2 & ~(1 << a3));
    }
}

