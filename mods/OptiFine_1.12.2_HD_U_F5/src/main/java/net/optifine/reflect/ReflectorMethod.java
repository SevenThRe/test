/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.reflect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorClass;

public class ReflectorMethod {
    private ReflectorClass reflectorClass = null;
    private String targetMethodName = null;
    private Class[] targetMethodParameterTypes = null;
    private boolean checked = false;
    private Method targetMethod = null;

    public ReflectorMethod(ReflectorClass reflectorClass, String targetMethodName) {
        this(reflectorClass, targetMethodName, null, false);
    }

    public ReflectorMethod(ReflectorClass reflectorClass, String targetMethodName, Class[] targetMethodParameterTypes) {
        this(reflectorClass, targetMethodName, targetMethodParameterTypes, false);
    }

    public ReflectorMethod(ReflectorClass reflectorClass, String targetMethodName, Class[] targetMethodParameterTypes, boolean lazyResolve) {
        this.reflectorClass = reflectorClass;
        this.targetMethodName = targetMethodName;
        this.targetMethodParameterTypes = targetMethodParameterTypes;
        if (!lazyResolve) {
            Method method = this.getTargetMethod();
        }
    }

    public Method getTargetMethod() {
        if (this.checked) {
            return this.targetMethod;
        }
        this.checked = true;
        Class cls = this.reflectorClass.getTargetClass();
        if (cls == null) {
            return null;
        }
        try {
            if (this.targetMethodParameterTypes == null) {
                Method[] ms = ReflectorMethod.getMethods(cls, this.targetMethodName);
                if (ms.length <= 0) {
                    Config.log("(Reflector) Method not present: " + cls.getName() + "." + this.targetMethodName);
                    return null;
                }
                if (ms.length > 1) {
                    Config.warn("(Reflector) More than one method found: " + cls.getName() + "." + this.targetMethodName);
                    for (int i = 0; i < ms.length; ++i) {
                        Method m2 = ms[i];
                        Config.warn("(Reflector)  - " + m2);
                    }
                    return null;
                }
                this.targetMethod = ms[0];
            } else {
                this.targetMethod = ReflectorMethod.getMethod(cls, this.targetMethodName, this.targetMethodParameterTypes);
            }
            if (this.targetMethod == null) {
                Config.log("(Reflector) Method not present: " + cls.getName() + "." + this.targetMethodName);
                return null;
            }
            this.targetMethod.setAccessible(true);
            return this.targetMethod;
        }
        catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean exists() {
        if (this.checked) {
            return this.targetMethod != null;
        }
        return this.getTargetMethod() != null;
    }

    public Class getReturnType() {
        Method tm = this.getTargetMethod();
        if (tm == null) {
            return null;
        }
        return tm.getReturnType();
    }

    public void deactivate() {
        this.checked = true;
        this.targetMethod = null;
    }

    public static Method getMethod(Class cls, String methodName, Class[] paramTypes) {
        Method[] ms = cls.getDeclaredMethods();
        for (int i = 0; i < ms.length; ++i) {
            Class[] types;
            Method m2 = ms[i];
            if (!m2.getName().equals(methodName) || !Reflector.matchesTypes(paramTypes, types = m2.getParameterTypes())) continue;
            return m2;
        }
        return null;
    }

    public static Method[] getMethods(Class cls, String methodName) {
        ArrayList<Method> listMethods = new ArrayList<Method>();
        Method[] ms = cls.getDeclaredMethods();
        for (int i = 0; i < ms.length; ++i) {
            Method m2 = ms[i];
            if (!m2.getName().equals(methodName)) continue;
            listMethods.add(m2);
        }
        Method[] methods = listMethods.toArray(new Method[listMethods.size()]);
        return methods;
    }
}

