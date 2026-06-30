/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

public final class ab
extends Enum<ab> {
    public static final /* enum */ ab r;
    private int l;
    public static final /* enum */ ab c;
    public static final /* enum */ ab v;
    public static final /* enum */ ab s;
    private static final /* synthetic */ ab[] m;
    public static final /* enum */ ab j;

    public static ab[] values() {
        return (ab[])m.clone();
    }

    public static ab valueOf(String a2) {
        return Enum.valueOf(ab.class, a2);
    }

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private /* synthetic */ ab(int n2) {
        void a2;
        void var2_-1;
        void var1_-1;
        ab a3;
        a3.l = a2;
    }

    static {
        v = new ab("FASTEST", 0, 1);
        r = new ab("FAST", 1, 3);
        j = new ab("NORMAL", 2, 5);
        s = new ab("MAXIMUM", 3, 7);
        c = new ab("ULTRA", 4, 9);
        ab[] abArray = new ab[5];
        abArray[0] = v;
        abArray[1] = r;
        abArray[2] = j;
        abArray[3] = s;
        abArray[4] = c;
        m = abArray;
    }

    public int r() {
        ab a2;
        return a2.l;
    }
}

