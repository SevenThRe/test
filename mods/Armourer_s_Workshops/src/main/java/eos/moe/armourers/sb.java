/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

public final class sb
extends Enum<sb> {
    private String v;
    public static final /* enum */ sb s = new sb("READ", 0, "r");
    private static final /* synthetic */ sb[] m;
    public static final /* enum */ sb j = new sb("WRITE", 1, "rw");

    public static sb[] values() {
        return (sb[])m.clone();
    }

    public String r() {
        sb a2;
        return a2.v;
    }

    public static sb valueOf(String a2) {
        return Enum.valueOf(sb.class, a2);
    }

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private /* synthetic */ sb(String string) {
        void a2;
        void var2_-1;
        void var1_-1;
        sb a3;
        a3.v = a2;
    }

    static {
        sb[] sbArray = new sb[2];
        sbArray[0] = s;
        sbArray[1] = j;
        m = sbArray;
    }
}

