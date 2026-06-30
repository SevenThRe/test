/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

public enum nc {
    m(1, 8, 16, 16),
    c(2, 12, 24, 24),
    q(3, 16, 32, 32);

    private int b;
    private int o;
    private int y;
    private int k;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private /* synthetic */ nc(int n22, int n22, int a2, int a3) {
        void a4;
        void a5;
        nc a6;
        a6.b = a2;
        a6.o = a3;
        a6.y = a5;
        a6.k = a4;
    }

    public int x() {
        nc a2;
        return a2.b;
    }

    public int f() {
        nc a2;
        return a2.o;
    }

    public int c() {
        nc a2;
        return a2.y;
    }

    public int ALLATORIxDEMO() {
        nc a2;
        return a2.k;
    }

    public static nc ALLATORIxDEMO(int a2) {
        for (nc a3 : nc.values()) {
            if (a3.x() != a2) continue;
            return a3;
        }
        return null;
    }
}

