/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.Type
 */
package invtweaks.forge.asm.compatibility;

import org.objectweb.asm.Type;

public class MethodInfo {
    public Type methodType;
    public Type methodClass;
    public String methodName;
    public boolean isStatic = false;

    public MethodInfo(Type mType, Type mClass, String name) {
        this.methodType = mType;
        this.methodClass = mClass;
        this.methodName = name;
    }

    public MethodInfo(Type mType, Type mClass, String name, boolean stat) {
        this.methodType = mType;
        this.methodClass = mClass;
        this.methodName = name;
        this.isStatic = stat;
    }
}

