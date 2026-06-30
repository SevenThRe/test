/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.InsnNode
 *  org.objectweb.asm.tree.JumpInsnNode
 *  org.objectweb.asm.tree.LabelNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package eos.moe.dragoncore.tweaker.transform;

import eos.moe.dragoncore.kca;
import eos.moe.dragoncore.oga;
import eos.moe.dragoncore.tweaker.TransformerManager;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class FontRendererTransform {
    public FontRendererTransform() {
        FontRendererTransform a2;
    }

    public static String getOwner() {
        return FontRendererTransform.class.getName().replace(".", "/");
    }

    public static float getWidth(char a2) {
        kca a3 = oga.k.ALLATORIxDEMO(a2);
        return a3 == null ? -1.0f : a3.s();
    }

    public static class Transformer_renderItem
    implements TransformerManager.IMethodTransformer {
        public Transformer_renderItem() {
            Transformer_renderItem a2;
        }

        @Override
        public void transform(ClassNode a2, MethodNode a3) {
            LabelNode a4 = (LabelNode)a3.instructions.get(0);
            a3.instructions.insert((AbstractInsnNode)new InsnNode(174));
            a3.instructions.insert((AbstractInsnNode)new MethodInsnNode(184, FontRendererTransform.getOwner(), "getWidth", "(C)F", false));
            a3.instructions.insert((AbstractInsnNode)new VarInsnNode(21, 1));
            a3.instructions.insert((AbstractInsnNode)new JumpInsnNode(155, a4));
            a3.instructions.insert((AbstractInsnNode)new InsnNode(149));
            a3.instructions.insert((AbstractInsnNode)new InsnNode(11));
            a3.instructions.insert((AbstractInsnNode)new MethodInsnNode(184, FontRendererTransform.getOwner(), "getWidth", "(C)F", false));
            a3.instructions.insert((AbstractInsnNode)new VarInsnNode(21, 1));
        }
    }
}

