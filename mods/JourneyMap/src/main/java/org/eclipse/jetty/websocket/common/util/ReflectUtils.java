/*
 * Decompiled with CFR 0.152.
 */
package org.eclipse.jetty.websocket.common.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class ReflectUtils {
    private static StringBuilder appendTypeName(StringBuilder sb, Type type, boolean ellipses) {
        if (type instanceof Class) {
            Class<?> ctype = (Class<?>)type;
            if (ctype.isArray()) {
                try {
                    int dimensions = 0;
                    while (ctype.isArray()) {
                        ++dimensions;
                        ctype = ctype.getComponentType();
                    }
                    sb.append(ctype.getName());
                    for (int i = 0; i < dimensions; ++i) {
                        if (ellipses) {
                            sb.append("...");
                            continue;
                        }
                        sb.append("[]");
                    }
                    return sb;
                }
                catch (Throwable throwable) {
                    // empty catch block
                }
            }
            sb.append(ctype.getName());
        } else {
            sb.append(type.toString());
        }
        return sb;
    }

    public static Class<?> findGenericClassFor(Class<?> baseClass, Class<?> ifaceClass) {
        GenericRef ref = new GenericRef(baseClass, ifaceClass);
        if (ReflectUtils.resolveGenericRef(ref, baseClass)) {
            return ref.genericClass;
        }
        return null;
    }

    private static int findTypeParameterIndex(Class<?> clazz, TypeVariable<?> needVar) {
        TypeVariable<Class<?>>[] params = clazz.getTypeParameters();
        for (int i = 0; i < params.length; ++i) {
            if (!params[i].getName().equals(needVar.getName())) continue;
            return i;
        }
        return -1;
    }

    public static boolean isDefaultConstructable(Class<?> clazz) {
        int mods = clazz.getModifiers();
        if (Modifier.isAbstract(mods) || !Modifier.isPublic(mods)) {
            return false;
        }
        Class[] noargs = new Class[]{};
        try {
            Constructor<?> constructor = clazz.getConstructor(noargs);
            return Modifier.isPublic(constructor.getModifiers());
        }
        catch (NoSuchMethodException | SecurityException e) {
            return false;
        }
    }

    private static boolean resolveGenericRef(GenericRef ref, Class<?> clazz, Type type) {
        if (type instanceof Class) {
            if (type == ref.ifaceClass) {
                ref.setGenericFromType(type, 0);
                return true;
            }
            return ReflectUtils.resolveGenericRef(ref, type);
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType ptype = (ParameterizedType)type;
            Type rawType = ptype.getRawType();
            if (rawType == ref.ifaceClass) {
                ref.setGenericFromType(ptype.getActualTypeArguments()[0], 0);
                return true;
            }
            return ReflectUtils.resolveGenericRef(ref, rawType);
        }
        return false;
    }

    private static boolean resolveGenericRef(GenericRef ref, Type type) {
        ParameterizedType ptype;
        Class rawClass;
        if (type == null || type == Object.class) {
            return false;
        }
        if (type instanceof Class) {
            Type[] ifaces;
            Class clazz = (Class)type;
            if (clazz.getName().matches("^javax*\\..*")) {
                return false;
            }
            for (Type iface : ifaces = clazz.getGenericInterfaces()) {
                if (!ReflectUtils.resolveGenericRef(ref, clazz, iface)) continue;
                if (ref.needsUnwrap()) {
                    TypeVariable needVar = (TypeVariable)ref.genericType;
                    int typeParamIdx = ReflectUtils.findTypeParameterIndex(clazz, needVar);
                    if (typeParamIdx >= 0) {
                        TypeVariable<Class<T>>[] params = clazz.getTypeParameters();
                        if (params.length >= typeParamIdx) {
                            ref.setGenericFromType(params[typeParamIdx], typeParamIdx);
                        }
                    } else if (iface instanceof ParameterizedType) {
                        Type arg = ((ParameterizedType)iface).getActualTypeArguments()[ref.genericIndex];
                        ref.setGenericFromType(arg, ref.genericIndex);
                    }
                }
                return true;
            }
            type = clazz.getGenericSuperclass();
            return ReflectUtils.resolveGenericRef(ref, type);
        }
        if (type instanceof ParameterizedType && ReflectUtils.resolveGenericRef(ref, rawClass = (Class)(ptype = (ParameterizedType)type).getRawType()) && ref.needsUnwrap()) {
            TypeVariable needVar = (TypeVariable)ref.genericType;
            int typeParamIdx = ReflectUtils.findTypeParameterIndex(rawClass, needVar);
            Type arg = ptype.getActualTypeArguments()[typeParamIdx];
            ref.setGenericFromType(arg, typeParamIdx);
            return true;
        }
        return false;
    }

    public static String toShortName(Type type) {
        if (type == null) {
            return "<null>";
        }
        if (type instanceof Class) {
            String name = ((Class)type).getName();
            return ReflectUtils.trimClassName(name);
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType ptype = (ParameterizedType)type;
            StringBuilder str = new StringBuilder();
            str.append(ReflectUtils.trimClassName(((Class)ptype.getRawType()).getName()));
            str.append("<");
            Type[] args2 = ptype.getActualTypeArguments();
            for (int i = 0; i < args2.length; ++i) {
                if (i > 0) {
                    str.append(",");
                }
                str.append(args2[i]);
            }
            str.append(">");
            return str.toString();
        }
        return type.toString();
    }

    public static String toString(Class<?> pojo, Method method) {
        StringBuilder str = new StringBuilder();
        int mod = method.getModifiers() & Modifier.methodModifiers();
        if (mod != 0) {
            str.append(Modifier.toString(mod)).append(' ');
        }
        Type retType = method.getGenericReturnType();
        ReflectUtils.appendTypeName(str, retType, false).append(' ');
        str.append(pojo.getName());
        str.append("#");
        str.append(method.getName());
        str.append('(');
        Type[] params = method.getGenericParameterTypes();
        for (int j = 0; j < params.length; ++j) {
            boolean ellipses = method.isVarArgs() && j == params.length - 1;
            ReflectUtils.appendTypeName(str, params[j], ellipses);
            if (j >= params.length - 1) continue;
            str.append(", ");
        }
        str.append(')');
        return str.toString();
    }

    public static String trimClassName(String name) {
        int idx = name.lastIndexOf(46);
        if ((idx = (name = name.substring(idx + 1)).lastIndexOf(36)) >= 0) {
            name = name.substring(idx + 1);
        }
        return name;
    }

    private static class GenericRef {
        private final Class<?> baseClass;
        private final Class<?> ifaceClass;
        Class<?> genericClass;
        public Type genericType;
        private int genericIndex;

        public GenericRef(Class<?> baseClass, Class<?> ifaceClass) {
            this.baseClass = baseClass;
            this.ifaceClass = ifaceClass;
        }

        public boolean needsUnwrap() {
            return this.genericClass == null && this.genericType != null && this.genericType instanceof TypeVariable;
        }

        public void setGenericFromType(Type type, int index) {
            this.genericType = type;
            this.genericIndex = index;
            if (type instanceof Class) {
                this.genericClass = (Class)type;
            }
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("GenericRef [baseClass=");
            builder.append(this.baseClass);
            builder.append(", ifaceClass=");
            builder.append(this.ifaceClass);
            builder.append(", genericType=");
            builder.append(this.genericType);
            builder.append(", genericClass=");
            builder.append(this.genericClass);
            builder.append("]");
            return builder.toString();
        }
    }
}

