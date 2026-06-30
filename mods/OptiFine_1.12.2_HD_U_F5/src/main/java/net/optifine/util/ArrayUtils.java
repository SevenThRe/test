/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.util;

public class ArrayUtils {
    public static boolean contains(Object[] arr2, Object val) {
        if (arr2 == null) {
            return false;
        }
        for (int i = 0; i < arr2.length; ++i) {
            Object obj = arr2[i];
            if (obj != val) continue;
            return true;
        }
        return false;
    }
}

