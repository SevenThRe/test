/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 */
package eos.moe.dragoncore;

import com.google.common.base.Preconditions;

public class ga {
    public ga() {
        ga a2;
    }

    public static void c(int a2, int a3, int a4) {
        if (a2 < a3 || a2 > a4) {
            throw new IllegalArgumentException("Number should be in range [" + a3 + ", " + a4 + "] (inclusive). Provided: " + a2);
        }
    }

    public static void ALLATORIxDEMO(float a2, float a3, float a4) {
        if (a2 < a3 || a2 > a4) {
            throw new IllegalArgumentException("Number should be in range [" + a3 + ", " + a4 + "] (inclusive). Provided: " + a2);
        }
    }

    public static void ALLATORIxDEMO(int a2, int a3, int a4) {
        if (a2 <= a3 || a2 >= a4) {
            throw new IllegalArgumentException("Number should be in range [" + a3 + ", " + a4 + "] (exclusive). Provided: " + a2);
        }
    }

    public static void x(int a2, int a3) {
        if (a2 < a3) {
            throw new IllegalArgumentException("Provided number should be greater or equal " + a3 + ". Provided: " + a2);
        }
    }

    public static void f(int a2, int a3) {
        if (a2 <= a3) {
            throw new IllegalArgumentException("Provided number should be greater than " + a3 + ". Provided: " + a2);
        }
    }

    public static void ALLATORIxDEMO(float a2, float a3) {
        if (a2 <= a3) {
            throw new IllegalArgumentException("Provided number should be greater than " + a3 + ". Provided: " + a2);
        }
    }

    public static void c(int a2, int a3) {
        if (a2 >= a3) {
            throw new IllegalArgumentException("Provided number should be less than " + a3 + ". Provided: " + a2);
        }
    }

    public static void ALLATORIxDEMO(int a2, int a3) {
        if (a2 > a3) {
            throw new IllegalArgumentException("Provided number should be less or equals " + a3 + ". Provided: " + a2);
        }
    }

    public static <T> void ALLATORIxDEMO(T[] a2, int a3) {
        Preconditions.checkNotNull(a2);
        if (a2.length != a3) {
            throw new IllegalArgumentException("Provided array should have length " + a3 + ". Provided: " + a2.length);
        }
    }

    public static void ALLATORIxDEMO(Object a2, Class<?> a3) {
        if (!a3.isInstance(a2)) {
            throw new IllegalArgumentException("Provided object should be an instance of " + a3 + ". Provided: " + a2.getClass());
        }
    }
}

