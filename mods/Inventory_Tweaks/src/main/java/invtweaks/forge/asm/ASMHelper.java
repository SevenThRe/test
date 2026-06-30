/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.InsnNode
 *  org.objectweb.asm.tree.IntInsnNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package invtweaks.forge.asm;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class ASMHelper {
    public static void generateBooleanMethodConst(@NotNull ClassNode clazz, String name, boolean retval) {
        MethodNode method = new MethodNode(262144, 4097, name, "()Z", null, null);
        InsnList code = method.instructions;
        code.add((AbstractInsnNode)new InsnNode(retval ? 4 : 3));
        code.add((AbstractInsnNode)new InsnNode(172));
        clazz.methods.add(method);
    }

    public static void generateIntegerMethodConst(@NotNull ClassNode clazz, String name, short retval) {
        MethodNode method = new MethodNode(262144, 4097, name, "()I", null, null);
        InsnList code = method.instructions;
        if (retval >= -128 && retval <= 127) {
            code.add((AbstractInsnNode)new IntInsnNode(16, (int)retval));
        } else {
            code.add((AbstractInsnNode)new IntInsnNode(17, (int)retval));
        }
        code.add((AbstractInsnNode)new InsnNode(172));
        clazz.methods.add(method);
    }

    public static void generateSelfForwardingMethod(@NotNull ClassNode clazz, String name, String forwardname, @NotNull Type rettype) {
        MethodNode method = new MethodNode(262144, 4097, name, "()" + rettype.getDescriptor(), null, null);
        ASMHelper.populateSelfForwardingMethod(method, forwardname, rettype, Type.getObjectType((String)clazz.name));
        clazz.methods.add(method);
    }

    public static void generateForwardingToStaticMethod(@NotNull ClassNode clazz, String name, String forwardname, @NotNull Type rettype, @NotNull Type fowardtype) {
        MethodNode method = new MethodNode(262144, 4097, name, "()" + rettype.getDescriptor(), null, null);
        ASMHelper.populateForwardingToStaticMethod(method, forwardname, rettype, Type.getObjectType((String)clazz.name), fowardtype);
        clazz.methods.add(method);
    }

    public static void generateForwardingToStaticMethod(@NotNull ClassNode clazz, String name, String forwardname, @NotNull Type rettype, @NotNull Type fowardtype, @NotNull Type thistype) {
        MethodNode method = new MethodNode(262144, 4097, name, "()" + rettype.getDescriptor(), null, null);
        ASMHelper.populateForwardingToStaticMethod(method, forwardname, rettype, thistype, fowardtype);
        clazz.methods.add(method);
    }

    public static void replaceSelfForwardingMethod(@NotNull MethodNode method, String forwardname, @NotNull Type thistype) {
        Type methodType = Type.getMethodType((String)method.desc);
        method.instructions.clear();
        ASMHelper.populateSelfForwardingMethod(method, forwardname, methodType.getReturnType(), thistype);
    }

    public static void populateForwardingToStaticMethod(@NotNull MethodNode method, String forwardname, @NotNull Type rettype, @NotNull Type thistype, @NotNull Type forwardtype) {
        InsnList code = method.instructions;
        code.add((AbstractInsnNode)new VarInsnNode(thistype.getOpcode(21), 0));
        code.add((AbstractInsnNode)new MethodInsnNode(184, forwardtype.getInternalName(), forwardname, Type.getMethodDescriptor((Type)rettype, (Type[])new Type[]{thistype}), false));
        code.add((AbstractInsnNode)new InsnNode(rettype.getOpcode(172)));
    }

    public static void populateSelfForwardingMethod(@NotNull MethodNode method, String forwardname, @NotNull Type rettype, @NotNull Type thistype) {
        InsnList code = method.instructions;
        code.add((AbstractInsnNode)new VarInsnNode(thistype.getOpcode(21), 0));
        code.add((AbstractInsnNode)new MethodInsnNode(182, thistype.getInternalName(), forwardname, "()" + rettype.getDescriptor(), false));
        code.add((AbstractInsnNode)new InsnNode(rettype.getOpcode(172)));
    }
}

