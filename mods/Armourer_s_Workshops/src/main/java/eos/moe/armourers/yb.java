/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

public final class yb
extends Enum<yb> {
    public static final /* enum */ yb c;
    public static final /* enum */ yb v;
    private static final /* synthetic */ yb[] s;
    public static final /* enum */ yb m;
    public static final /* enum */ yb j;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private /* synthetic */ yb() {
        void var2_-1;
        void var1_-1;
        yb a2;
    }

    static {
        v = new yb("NONE", 0);
        m = new yb("ZIP_STANDARD", 1);
        c = new yb("ZIP_STANDARD_VARIANT_STRONG", 2);
        j = new yb("AES", 3);
        yb[] ybArray = new yb[4];
        ybArray[0] = v;
        ybArray[1] = m;
        ybArray[2] = c;
        ybArray[3] = j;
        s = ybArray;
    }

    public static yb valueOf(String a2) {
        return Enum.valueOf(yb.class, a2);
    }

    public static yb[] values() {
        return (yb[])s.clone();
    }
}

