/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.function.LongSupplier;

public class NativeMemory {
    private static LongSupplier bufferAllocatedSupplier = NativeMemory.makeLongSupplier(new String[][]{{"sun.misc.SharedSecrets", "getJavaNioAccess", "getDirectBufferPool", "getMemoryUsed"}, {"jdk.internal.misc.SharedSecrets", "getJavaNioAccess", "getDirectBufferPool", "getMemoryUsed"}});
    private static LongSupplier bufferMaximumSupplier = NativeMemory.makeLongSupplier(new String[][]{{"sun.misc.VM", "maxDirectMemory"}, {"jdk.internal.misc.VM", "maxDirectMemory"}});

    public static long getBufferAllocated() {
        if (bufferAllocatedSupplier == null) {
            return -1L;
        }
        return bufferAllocatedSupplier.getAsLong();
    }

    public static long getBufferMaximum() {
        if (bufferMaximumSupplier == null) {
            return -1L;
        }
        return bufferMaximumSupplier.getAsLong();
    }

    private static LongSupplier makeLongSupplier(String[][] paths) {
        ArrayList<Throwable> exceptions = new ArrayList<Throwable>();
        for (int i = 0; i < paths.length; ++i) {
            String[] path = paths[i];
            try {
                LongSupplier supplier = NativeMemory.makeLongSupplier(path);
                return supplier;
            }
            catch (Throwable e) {
                exceptions.add(e);
                continue;
            }
        }
        for (Throwable t : exceptions) {
            Config.warn("" + t.getClass().getName() + ": " + t.getMessage());
        }
        return null;
    }

    private static LongSupplier makeLongSupplier(String[] path) throws Exception {
        if (path.length < 2) {
            return null;
        }
        Class<?> cls = Class.forName(path[0]);
        Method method = cls.getMethod(path[1], new Class[0]);
        method.setAccessible(true);
        Object object = null;
        for (int i = 2; i < path.length; ++i) {
            String name = path[i];
            object = method.invoke(object, new Object[0]);
            method = object.getClass().getMethod(name, new Class[0]);
            method.setAccessible(true);
        }
        final Object objectF = object;
        final Method methodF = method;
        LongSupplier ls = new LongSupplier(){
            private boolean disabled = false;

            @Override
            public long getAsLong() {
                if (this.disabled) {
                    return -1L;
                }
                try {
                    return (Long)methodF.invoke(objectF, new Object[0]);
                }
                catch (Throwable e) {
                    Config.warn("" + e.getClass().getName() + ": " + e.getMessage());
                    this.disabled = true;
                    return -1L;
                }
            }
        };
        return ls;
    }
}

