/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ga;
import eos.moe.dragoncore.xr;
import java.util.function.Predicate;

public class ux {
    public ux() {
        ux a2;
    }

    public static Predicate<xr> c() {
        return a2 -> true;
    }

    public static Predicate<xr> ALLATORIxDEMO() {
        return a2 -> a2.c() >= a2.ALLATORIxDEMO().ALLATORIxDEMO();
    }

    public static Predicate<xr> ALLATORIxDEMO(int a2) {
        return a3 -> a3.c() >= a2;
    }

    public static Predicate<xr> ALLATORIxDEMO(float a2) {
        ga.ALLATORIxDEMO(a2, 0.0f, 1.0f);
        return a3 -> {
            float a4 = a3.ALLATORIxDEMO().ALLATORIxDEMO();
            float a5 = a3.c();
            return a5 >= a4 * a2;
        };
    }
}

