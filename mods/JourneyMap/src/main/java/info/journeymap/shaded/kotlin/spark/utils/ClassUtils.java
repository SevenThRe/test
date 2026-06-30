/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.utils;

import info.journeymap.shaded.kotlin.spark.utils.Assert;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public abstract class ClassUtils {
    public static final String ARRAY_SUFFIX = "[]";
    private static final String INTERNAL_ARRAY_PREFIX = "[";
    private static final String NON_PRIMITIVE_ARRAY_PREFIX = "[L";
    private static final Map<Class<?>, Class<?>> primitiveWrapperTypeMap = new HashMap(8);
    private static final Map<Class<?>, Class<?>> primitiveTypeToWrapperMap = new HashMap(8);
    private static final Map<String, Class<?>> primitiveTypeNameMap = new HashMap(32);
    private static final Map<String, Class<?>> commonClassCache = new HashMap(32);

    private static void registerCommonClasses(Class<?> ... commonClasses) {
        for (Class<?> clazz : commonClasses) {
            commonClassCache.put(clazz.getName(), clazz);
        }
    }

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        if (cl == null) {
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }

    public static Class<?> forName(String name, ClassLoader classLoader) throws ClassNotFoundException, LinkageError {
        Assert.notNull(name, "Name must not be null");
        Class<?> clazz = ClassUtils.resolvePrimitiveClassName(name);
        if (clazz == null) {
            clazz = commonClassCache.get(name);
        }
        if (clazz != null) {
            return clazz;
        }
        if (name.endsWith(ARRAY_SUFFIX)) {
            String elementClassName = name.substring(0, name.length() - ARRAY_SUFFIX.length());
            Class<?> elementClass = ClassUtils.forName(elementClassName, classLoader);
            return Array.newInstance(elementClass, 0).getClass();
        }
        if (name.startsWith(NON_PRIMITIVE_ARRAY_PREFIX) && name.endsWith(";")) {
            String elementName = name.substring(NON_PRIMITIVE_ARRAY_PREFIX.length(), name.length() - 1);
            Class<?> elementClass = ClassUtils.forName(elementName, classLoader);
            return Array.newInstance(elementClass, 0).getClass();
        }
        if (name.startsWith(INTERNAL_ARRAY_PREFIX)) {
            String elementName = name.substring(INTERNAL_ARRAY_PREFIX.length());
            Class<?> elementClass = ClassUtils.forName(elementName, classLoader);
            return Array.newInstance(elementClass, 0).getClass();
        }
        ClassLoader classLoaderToUse = classLoader;
        if (classLoaderToUse == null) {
            classLoaderToUse = ClassUtils.getDefaultClassLoader();
        }
        try {
            return classLoaderToUse.loadClass(name);
        }
        catch (ClassNotFoundException ex) {
            int lastDotIndex = name.lastIndexOf(46);
            if (lastDotIndex != -1) {
                String innerClassName = name.substring(0, lastDotIndex) + '$' + name.substring(lastDotIndex + 1);
                try {
                    return classLoaderToUse.loadClass(innerClassName);
                }
                catch (ClassNotFoundException ex2) {
                    // empty catch block
                }
            }
            throw ex;
        }
    }

    public static Class<?> resolvePrimitiveClassName(String name) {
        Class<?> result = null;
        if (name != null && name.length() <= 8) {
            result = primitiveTypeNameMap.get(name);
        }
        return result;
    }

    public static String classPackageAsResourcePath(Class<?> clazz) {
        if (clazz == null) {
            return "";
        }
        String className = clazz.getName();
        int packageEndIndex = className.lastIndexOf(46);
        if (packageEndIndex == -1) {
            return "";
        }
        String packageName = className.substring(0, packageEndIndex);
        return packageName.replace('.', '/');
    }

    static {
        primitiveWrapperTypeMap.put(Boolean.class, Boolean.TYPE);
        primitiveWrapperTypeMap.put(Byte.class, Byte.TYPE);
        primitiveWrapperTypeMap.put(Character.class, Character.TYPE);
        primitiveWrapperTypeMap.put(Double.class, Double.TYPE);
        primitiveWrapperTypeMap.put(Float.class, Float.TYPE);
        primitiveWrapperTypeMap.put(Integer.class, Integer.TYPE);
        primitiveWrapperTypeMap.put(Long.class, Long.TYPE);
        primitiveWrapperTypeMap.put(Short.class, Short.TYPE);
        for (Map.Entry<Class<?>, Class<?>> entry : primitiveWrapperTypeMap.entrySet()) {
            primitiveTypeToWrapperMap.put(entry.getValue(), entry.getKey());
            ClassUtils.registerCommonClasses(entry.getKey());
        }
        HashSet<Class<Void>> primitiveTypes = new HashSet<Class<Void>>(32);
        primitiveTypes.addAll(primitiveWrapperTypeMap.values());
        primitiveTypes.addAll(Arrays.asList(boolean[].class, byte[].class, char[].class, double[].class, float[].class, int[].class, long[].class, short[].class));
        primitiveTypes.add(Void.TYPE);
        for (Class clazz : primitiveTypes) {
            primitiveTypeNameMap.put(clazz.getName(), clazz);
        }
        ClassUtils.registerCommonClasses(Boolean[].class, Byte[].class, Character[].class, Double[].class, Float[].class, Integer[].class, Long[].class, Short[].class);
        ClassUtils.registerCommonClasses(Number.class, Number[].class, String.class, String[].class, Object.class, Object[].class, Class.class, Class[].class);
        ClassUtils.registerCommonClasses(Throwable.class, Exception.class, RuntimeException.class, Error.class, StackTraceElement.class, StackTraceElement[].class);
    }
}

