/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.b;

public final class tu {
    public tu() {
        tu a2;
    }

    public static b ALLATORIxDEMO(b a2, String a3) {
        Object a4 = a2.ALLATORIxDEMO().get(a3);
        if (a4 instanceof b) {
            return (b)a4;
        }
        return null;
    }

    public static b f(b a2) {
        return tu.ALLATORIxDEMO(a2, "parent");
    }

    public static b c(b a2) {
        return tu.ALLATORIxDEMO(a2, "next");
    }

    public static b ALLATORIxDEMO(b a2) {
        return tu.ALLATORIxDEMO(a2, "previous");
    }
}

