/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 */
package com.teamderpy.shouldersurfing.asm.transformers;

import com.teamderpy.shouldersurfing.asm.Mappings;
import com.teamderpy.shouldersurfing.asm.ShoulderTransformer;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class EntityRendererRayTrace
extends ShoulderTransformer {
    @Override
    protected InsnList searchList(Mappings mappings, boolean obf) {
        InsnList searchList = new InsnList();
        searchList.add((AbstractInsnNode)new MethodInsnNode(182, mappings.map("WorldClient", obf), mappings.map("WorldClient#rayTraceBlocks", obf), mappings.desc("WorldClient#rayTraceBlocks", obf), false));
        return searchList;
    }

    @Override
    public void transform(Mappings mappings, boolean obf, MethodNode method, int offset) {
        MethodInsnNode instruction = new MethodInsnNode(184, "com/teamderpy/shouldersurfing/asm/InjectionDelegation", "getRayTraceResult", mappings.desc("InjectionDelegation#getRayTraceResult", obf), false);
        method.instructions.set(method.instructions.get(offset), (AbstractInsnNode)instruction);
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

