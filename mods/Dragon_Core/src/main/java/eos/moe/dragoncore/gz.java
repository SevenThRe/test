/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

public enum gz {
    l("IDLE"),
    z("WALK"),
    s("FLY"),
    g("SWIM"),
    t("IDLE_SWIM"),
    r("SPECIAL"),
    x("SPECIAL2"),
    v("SPECIAL3"),
    m(false, "BOUNCEOPEN"),
    c(false, "BOUNCECLOSED"),
    q(false, "BREAK"),
    b(false, "SHAKELEFT"),
    o(false, "SHAKERIGHT");

    public boolean y;
    public String k;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private /* synthetic */ gz(String string) {
        void a2;
        gz a3;
        a3.y = true;
        a3.k = a2;
    }

    /*
     * WARNING - void declaration
     */
    private /* synthetic */ gz(boolean bl22, String bl22) {
        void a2;
        void a3;
        gz a4;
        a4.y = true;
        a4.y = a3;
        a4.k = a2;
    }

    public String toString() {
        gz a2;
        return a2.name().toLowerCase().replace("_", "");
    }

    public static gz ALLATORIxDEMO(String a2) {
        for (gz a3 : gz.values()) {
            if (!a2.equalsIgnoreCase(a3.toString())) continue;
            return a3;
        }
        return l;
    }
}

