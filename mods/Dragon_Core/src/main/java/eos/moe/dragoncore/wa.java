/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

public class wa {
    public wa() {
        wa a2;
    }

    public static <T> boolean ALLATORIxDEMO(T[] a2, T a3) {
        for (T a4 : a2) {
            if (!a4.equals(a3)) continue;
            return true;
        }
        return false;
    }

    public static <T> boolean ALLATORIxDEMO(Iterable<T> a2, T a3) {
        for (T a4 : a2) {
            if (!a4.equals(a3)) continue;
            return true;
        }
        return false;
    }
}

