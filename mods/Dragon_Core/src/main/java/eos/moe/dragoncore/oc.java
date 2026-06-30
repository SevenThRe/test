/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

public enum oc {
    o(1),
    y(2);

    private int k;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private /* synthetic */ oc(int n2) {
        void a2;
        oc a3;
        a3.k = a2;
    }

    public int ALLATORIxDEMO() {
        oc a2;
        return a2.k;
    }

    public static oc ALLATORIxDEMO(int a2) {
        for (oc a3 : oc.values()) {
            if (a3.k != a2) continue;
            return a3;
        }
        throw new IllegalArgumentException("Unsupported Aes version");
    }
}

