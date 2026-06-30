/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.TypeInsnNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package com.teamderpy.shouldersurfing.asm.transformers;

import com.teamderpy.shouldersurfing.asm.Mappings;
import com.teamderpy.shouldersurfing.asm.ShoulderTransformer;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class EntityRendererGetMouseOver2
extends ShoulderTransformer {
    @Override
    protected InsnList searchList(Mappings mappings, boolean obf) {
        InsnList searchList = new InsnList();
        searchList.add((AbstractInsnNode)new MethodInsnNode(182, mappings.map("AxisAlignedBB", obf), mappings.map("AxisAlignedBB#calculateIntercept", obf), mappings.desc("AxisAlignedBB#calculateIntercept", obf), false));
        return searchList;
    }

    @Override
    public void transform(Mappings mappings, boolean obf, MethodNode method, int offset) {
        AbstractInsnNode vec3d2 = method.instructions.get(offset - 1);
        if (vec3d2 instanceof VarInsnNode) {
            method.instructions.set(vec3d2.getPrevious(), (AbstractInsnNode)new VarInsnNode(vec3d2.getOpcode(), ((VarInsnNode)vec3d2).var));
            method.instructions.insertBefore(vec3d2, (AbstractInsnNode)new MethodInsnNode(185, "java/util/Map$Entry", "getKey", "()Ljava/lang/Object;", true));
            method.instructions.insertBefore(vec3d2, (AbstractInsnNode)new TypeInsnNode(192, mappings.map("Vec3d", obf)));
            method.instructions.insert(vec3d2, (AbstractInsnNode)new TypeInsnNode(192, mappings.map("Vec3d", obf)));
            method.instructions.insert(vec3d2, (AbstractInsnNode)new MethodInsnNode(185, "java/util/Map$Entry", "getValue", "()Ljava/lang/Object;", true));
        }
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

