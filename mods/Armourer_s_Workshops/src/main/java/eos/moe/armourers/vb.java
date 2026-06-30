/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

public final class vb
extends Enum<vb> {
    public static final /* enum */ vb w;
    public static final /* enum */ vb r;
    private int l;
    private int c;
    public static final /* enum */ vb v;
    private static final /* synthetic */ vb[] s;
    private int m;
    private int j;

    public static vb r(int a2) {
        int n2;
        vb[] vbArray = vb.values();
        int n3 = vbArray.length;
        int n4 = n2 = 0;
        while (n4 < n3) {
            vb vb2 = vbArray[n2];
            if (vb2.z() == a2) {
                return vb2;
            }
            n4 = ++n2;
        }
        return null;
    }

    public int h() {
        vb a2;
        return a2.l;
    }

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private /* synthetic */ vb(int n22, int n22, int a2, int a3) {
        void a4;
        void a5;
        void var2_-1;
        void var1_-1;
        vb a6;
        vb vb2 = a6;
        vb vb3 = a6;
        vb3.j = a2;
        vb3.m = a3;
        vb2.l = a5;
        vb2.c = a4;
    }

    public static vb valueOf(String a2) {
        return Enum.valueOf(vb.class, a2);
    }

    public static vb[] values() {
        return (vb[])s.clone();
    }

    public int z() {
        vb a2;
        return a2.j;
    }

    public int y() {
        vb a2;
        return a2.c;
    }

    public int r() {
        vb a2;
        return a2.m;
    }

    static {
        r = new vb("KEY_STRENGTH_128", 0, 1, 8, 16, 16);
        v = new vb("KEY_STRENGTH_192", 1, 2, 12, 24, 24);
        w = new vb("KEY_STRENGTH_256", 2, 3, 16, 32, 32);
        vb[] vbArray = new vb[3];
        vbArray[0] = r;
        vbArray[1] = v;
        vbArray[2] = w;
        s = vbArray;
    }
}

