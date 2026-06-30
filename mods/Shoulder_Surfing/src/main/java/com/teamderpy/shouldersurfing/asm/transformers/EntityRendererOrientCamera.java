/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.InsnNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package com.teamderpy.shouldersurfing.asm.transformers;

import com.teamderpy.shouldersurfing.asm.Mappings;
import com.teamderpy.shouldersurfing.asm.ShoulderTransformer;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class EntityRendererOrientCamera
extends ShoulderTransformer {
    @Override
    protected InsnList searchList(Mappings mappings, boolean obf) {
        InsnList searchList = new InsnList();
        searchList.add((AbstractInsnNode)new MethodInsnNode(184, mappings.map("GlStateManager", obf), mappings.map("GlStateManager#rotate", obf), mappings.desc("GlStateManager#rotate", obf), false));
        searchList.add((AbstractInsnNode)new InsnNode(11));
        searchList.add((AbstractInsnNode)new InsnNode(11));
        searchList.add((AbstractInsnNode)new VarInsnNode(24, 10));
        searchList.add((AbstractInsnNode)new InsnNode(119));
        searchList.add((AbstractInsnNode)new InsnNode(144));
        searchList.add((AbstractInsnNode)new MethodInsnNode(184, mappings.map("GlStateManager", obf), mappings.map("GlStateManager#translate", obf), mappings.desc("GlStateManager#translate", obf), false));
        return searchList;
    }

    @Override
    protected void transform(Mappings mappings, boolean obf, MethodNode method, int offset) {
        AbstractInsnNode instruction = method.instructions.get(offset);
        method.instructions.insertBefore(instruction, (AbstractInsnNode)new VarInsnNode(23, 12));
        method.instructions.insertBefore(instruction, (AbstractInsnNode)new VarInsnNode(23, 13));
        method.instructions.set(instruction, (AbstractInsnNode)new MethodInsnNode(184, "com/teamderpy/shouldersurfing/asm/InjectionDelegation", "cameraSetup", "(FFFFF)V", false));
    }

    @Override
    public String getClassId() {
        return "EntityRenderer";
    }

    @Override
    public String getMethodId() {
        return "EntityRenderer#orientCamera";
    }

    @Override
    protected boolean hasMethodTransformer() {
        return true;
    }

    @Override
    protected boolean hasClassTransformer() {
        return false;
    }
}

