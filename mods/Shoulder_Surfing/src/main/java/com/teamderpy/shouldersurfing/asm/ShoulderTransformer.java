/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.IClassTransformer
 *  net.minecraft.launchwrapper.Launch
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.objectweb.asm.ClassReader
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.ClassWriter
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.FieldInsnNode
 *  org.objectweb.asm.tree.IincInsnNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.InsnNode
 *  org.objectweb.asm.tree.IntInsnNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package com.teamderpy.shouldersurfing.asm;

import com.teamderpy.shouldersurfing.asm.Mappings;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.Launch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.IincInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public abstract class ShoulderTransformer
implements IClassTransformer {
    private static final Mappings MAPPINGS = Mappings.load("mappings.json");
    private static final Logger LOGGER = LogManager.getLogger((String)"Shoulder Surfing");
    private static final boolean OBFUSCATED = (Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment") == false;

    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (name.equals(this.getTransformedClassName(OBFUSCATED))) {
            ClassNode classNode = new ClassNode();
            ClassReader classReader = new ClassReader(bytes);
            classReader.accept((ClassVisitor)classNode, 0);
            if (this.hasMethodTransformer()) {
                LOGGER.info("Attempting to transform method for class " + name + " -> " + transformedName);
                this.transformMethod(MAPPINGS, OBFUSCATED, classNode);
            }
            ClassWriter writer = new ClassWriter(classReader, 3);
            classNode.accept((ClassVisitor)writer);
            if (this.hasClassTransformer()) {
                LOGGER.info("Attempting to transform class " + name + " -> " + transformedName);
                this.transform(MAPPINGS, OBFUSCATED, writer);
            }
            return writer.toByteArray();
        }
        return bytes;
    }

    private void transformMethod(Mappings mappings, boolean obf, ClassNode classNode) {
        String methodId = this.getMethodId();
        String methodName = mappings.map(methodId, obf);
        String methodDesc = mappings.desc(methodId, obf);
        for (Object m : classNode.methods) {
            MethodNode method = (MethodNode)m;
            if (!method.name.equals(methodName) || !method.desc.equals(methodDesc)) continue;
            String methodDeobf = mappings.map(methodId, false) + mappings.desc(methodId, false);
            String methodObf = method.name + method.desc;
            int offset = ShoulderTransformer.locateOffset(method.instructions, this.searchList(mappings, obf), this.ignoreLabels(), this.ignoreLineNumber());
            if (offset == -1) {
                LOGGER.info(this.getClass().getSimpleName() + ": Failed to locate offset for method " + methodDeobf + " -> " + methodObf);
                continue;
            }
            LOGGER.info(this.getClass().getSimpleName() + ": Found offset " + offset + " for method " + methodDeobf + " -> " + methodObf);
            this.transform(mappings, obf, method, offset);
        }
    }

    private String getTransformedClassName(boolean obf) {
        return MAPPINGS.map(this.getClassId(), obf).replace('/', '.');
    }

    protected void transform(Mappings mappings, boolean obf, MethodNode method, int offset) {
    }

    protected void transform(Mappings mappings, boolean obf, ClassWriter writer) {
    }

    protected boolean ignoreLabels() {
        return true;
    }

    protected boolean ignoreLineNumber() {
        return true;
    }

    protected String getMethodId() {
        return null;
    }

    protected InsnList searchList(Mappings mappings, boolean obf) {
        return null;
    }

    protected abstract String getClassId();

    protected abstract boolean hasMethodTransformer();

    protected abstract boolean hasClassTransformer();

    private static int locateOffset(InsnList instructions, InsnList search, boolean ignoreLabel, boolean ignoreLineNumber) {
        return ShoulderTransformer.locateOffset(instructions, search, 0, 0, instructions.size(), ignoreLabel, ignoreLineNumber);
    }

    private static int locateOffset(InsnList instructions, InsnList search, int searchNdx, int startAt, int limit, boolean ignoreLabel, boolean ignoreLineNumber) {
        int attempts = 0;
        for (int i = startAt; i < instructions.size() && attempts < limit; ++i) {
            AbstractInsnNode instruction = instructions.get(i);
            if (ignoreLabel && instruction.getType() == 8 || ignoreLineNumber && instruction.getType() == 15) continue;
            boolean match = false;
            AbstractInsnNode searchNode = search.get(searchNdx);
            if (instruction.getType() == searchNode.getType()) {
                if (instruction.getType() == 4) {
                    if (((FieldInsnNode)instruction).desc.equals(((FieldInsnNode)searchNode).desc) && ((FieldInsnNode)instruction).name.equals(((FieldInsnNode)searchNode).name) && ((FieldInsnNode)instruction).owner.equals(((FieldInsnNode)searchNode).owner)) {
                        match = true;
                    }
                } else if (instruction.getType() == 2) {
                    if (((VarInsnNode)instruction).var == ((VarInsnNode)searchNode).var && instruction.getOpcode() == searchNode.getOpcode()) {
                        match = true;
                    }
                } else if (instruction.getType() == 0) {
                    if (((InsnNode)instruction).getOpcode() == ((InsnNode)searchNode).getOpcode()) {
                        match = true;
                    }
                } else if (instruction.getType() == 5) {
                    if (((MethodInsnNode)instruction).desc.equals(((MethodInsnNode)searchNode).desc) && ((MethodInsnNode)instruction).name.equals(((MethodInsnNode)searchNode).name) && ((MethodInsnNode)instruction).owner.equals(((MethodInsnNode)searchNode).owner)) {
                        match = true;
                    }
                } else if (instruction.getType() == 1) {
                    if (((IntInsnNode)instruction).operand == ((IntInsnNode)searchNode).operand && instruction.getOpcode() == searchNode.getOpcode()) {
                        match = true;
                    }
                } else if (instruction.getType() == 10 && ((IincInsnNode)instruction).var == ((IincInsnNode)searchNode).var && ((IincInsnNode)instruction).incr == ((IincInsnNode)searchNode).incr && instruction.getOpcode() == searchNode.getOpcode()) {
                    match = true;
                }
                if (match) {
                    if (searchNdx >= search.size() - 1) {
                        return i;
                    }
                    int offset = ShoulderTransformer.locateOffset(instructions, search, searchNdx + 1, i + 1, 1, ignoreLabel, ignoreLineNumber);
                    if (offset != -1) {
                        return offset;
                    }
                }
            }
            if (match) continue;
            ++attempts;
        }
        return -1;
    }
}

