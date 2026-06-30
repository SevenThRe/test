/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.util;

import java.lang.reflect.Array;
import java.util.ArrayDeque;

public class ArrayCache {
    private Class elementClass = null;
    private int maxCacheSize = 0;
    private ArrayDeque cache = new ArrayDeque();

    public ArrayCache(Class elementClass, int maxCacheSize) {
        this.elementClass = elementClass;
        this.maxCacheSize = maxCacheSize;
    }

    public synchronized Object allocate(int size) {
        Object arr2 = this.cache.pollLast();
        if (arr2 == null || Array.getLength(arr2) < size) {
            arr2 = Array.newInstance(this.elementClass, size);
        }
        return arr2;
    }

    public synchronized void free(Object arr2) {
        if (arr2 == null) {
            return;
        }
        Class<?> cls = arr2.getClass();
        if (cls.getComponentType() != this.elementClass) {
            throw new IllegalArgumentException("Wrong component type");
        }
        if (this.cache.size() >= this.maxCacheSize) {
            return;
        }
        this.cache.add(arr2);
    }
}

