/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

public enum rz {
    y,
    k;


    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private /* synthetic */ rz() {
        rz a2;
    }

    public static rz ALLATORIxDEMO(String a2) {
        if (a2.equalsIgnoreCase("OVERRIDE")) {
            return y;
        }
        if (a2.equalsIgnoreCase("ADDING")) {
            return k;
        }
        return y;
    }
}

