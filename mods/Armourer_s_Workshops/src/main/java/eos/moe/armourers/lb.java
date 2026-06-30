/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

public final class lb
extends Enum<lb> {
    public static final /* enum */ lb v = new lb("ONE", 0, 1);
    public static final /* enum */ lb s = new lb("TWO", 1, 2);
    private static final /* synthetic */ lb[] m;
    private int j;

    public static lb r(int a2) {
        int n2;
        lb[] lbArray = lb.values();
        int n3 = lbArray.length;
        int n4 = n2 = 0;
        while (n4 < n3) {
            lb lb2 = lbArray[n2];
            if (lb2.j == a2) {
                return lb2;
            }
            n4 = ++n2;
        }
        throw new IllegalArgumentException("Unsupported Aes version");
    }

    public int r() {
        lb a2;
        return a2.j;
    }

    public static lb[] values() {
        return (lb[])m.clone();
    }

    public static lb valueOf(String a2) {
        return Enum.valueOf(lb.class, a2);
    }

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private /* synthetic */ lb(int n2) {
        void a2;
        void var2_-1;
        void var1_-1;
        lb a3;
        a3.j = a2;
    }

    static {
        lb[] lbArray = new lb[2];
        lbArray[0] = v;
        lbArray[1] = s;
        m = lbArray;
    }
}

