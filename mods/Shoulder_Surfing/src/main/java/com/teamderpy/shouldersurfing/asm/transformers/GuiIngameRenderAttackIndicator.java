/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.FieldInsnNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.MethodNode
 */
package com.teamderpy.shouldersurfing.asm.transformers;

import com.teamderpy.shouldersurfing.asm.Mappings;
import com.teamderpy.shouldersurfing.asm.ShoulderTransformer;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;

public class GuiIngameRenderAttackIndicator
extends ShoulderTransformer {
    @Override
    protected InsnList searchList(Mappings mappings, boolean obf) {
        InsnList searchList = new InsnList();
        searchList.add((AbstractInsnNode)new FieldInsnNode(180, mappings.map("GameSettings", obf), mappings.map("GameSettings#thirdPersonView", obf), mappings.desc("GameSettings#thirdPersonView", obf)));
        return searchList;
    }

    @Override
    public void transform(Mappings mappings, boolean obf, MethodNode method, int offset) {
    }

    @Override
    public String getClassId() {
        return "GuiIngame";
    }

    @Override
    public String getMethodId() {
        return "GuiIngame#renderAttackIndicator";
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

