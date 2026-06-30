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

public class EntityRendererGetMouseOver
extends ShoulderTransformer {
    @Override
    protected InsnList searchList(Mappings mappings, boolean obf) {
        InsnList searchList = new InsnList();
        searchList.add((AbstractInsnNode)new MethodInsnNode(182, mappings.map("Vec3d", obf), mappings.map("Vec3d#addVector", obf), mappings.desc("Vec3d#addVector", obf), false));
        return searchList;
    }

    @Override
    public void transform(Mappings mappings, boolean obf, MethodNode method, int offset) {
        AbstractInsnNode node = method.instructions.get(offset);
        AbstractInsnNode start = method.instructions.get(offset - 14);
        AbstractInsnNode stop = node.getPrevious().getPrevious();
        while (!start.getNext().equals(stop)) {
            method.instructions.remove(start.getNext());
        }
        method.instructions.remove(node.getPrevious());
        method.instructions.set(node, (AbstractInsnNode)new MethodInsnNode(184, "com/teamderpy/shouldersurfing/asm/InjectionDelegation", "shoulderSurfingLook", "(D)Ljava/util/Map$Entry;", false));
    }

    @Override
    public String getClassId() {
        return "EntityRenderer";
    }

    @Override
    public String getMethodId() {
        return "EntityRenderer#getMouseOver";
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

