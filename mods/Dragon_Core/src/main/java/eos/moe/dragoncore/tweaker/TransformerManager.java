/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.IClassTransformer
 *  net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper
 *  net.minecraftforge.fml.relauncher.FMLRelaunchLog
 *  org.objectweb.asm.ClassReader
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.ClassWriter
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.MethodNode
 */
package eos.moe.dragoncore.tweaker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import net.minecraftforge.fml.relauncher.FMLRelaunchLog;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class TransformerManager
implements IClassTransformer {
    private Map<String, Map<String, IMethodTransformer>> map = new HashMap<String, Map<String, IMethodTransformer>>();

    public TransformerManager() {
        TransformerManager a2;
    }

    private /* synthetic */ void addMethodTransformer(String a2, String a3, String[] a4, IMethodTransformer a5) {
        TransformerManager a6;
        if (!a6.map.containsKey(a2)) {
            a6.map.put(a2, new HashMap());
        }
        for (String a7 : a4) {
            a6.map.get(a2).put(a7 + a3, a5);
            FMLRelaunchLog.info((String)"[CSL DEBUG] REGISTERING METHOD %s(%s)", (Object[])new Object[]{a2, a7 + a3});
        }
    }

    public byte[] transform(String a2, String a3, byte[] a4) {
        TransformerManager a5;
        if (!a5.map.containsKey(a3)) {
            return a4;
        }
        FMLRelaunchLog.info((String)"[CSL DEBUG] CLASS %s will be transformed", (Object[])new Object[]{a3});
        Map<String, IMethodTransformer> a6 = a5.map.get(a3);
        ClassReader a7 = new ClassReader(a4);
        ClassNode a8 = new ClassNode();
        a7.accept((ClassVisitor)a8, 0);
        ArrayList a9 = new ArrayList();
        a9.addAll(a8.methods);
        for (MethodNode a10 : a9) {
            String a11 = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(a2, a10.name, a10.desc);
            String a12 = FMLDeobfuscatingRemapper.INSTANCE.mapMethodDesc(a10.desc);
            if (!a6.containsKey(a11 + a12)) continue;
            try {
                FMLRelaunchLog.info((String)"[CSL DEBUG] Transforming method %s in class %s(%s)", (Object[])new Object[]{a11 + a12, a2, a3});
                a6.get(a11 + a12).transform(a8, a10);
                FMLRelaunchLog.info((String)"[CSL DEBUG] Successfully transformed method %s in class %s(%s)", (Object[])new Object[]{a11 + a12, a2, a3});
            }
            catch (Exception a13) {
                FMLRelaunchLog.warning((String)"[CSL DEBUG] An error happened when transforming method %s in class %s(%s). The whole class was not modified.", (Object[])new Object[]{a11 + a12, a2, a3});
                a13.printStackTrace();
                return a4;
            }
        }
        ClassWriter a14 = new ClassWriter(3);
        a8.accept((ClassVisitor)a14);
        a4 = a14.toByteArray();
        return a4;
    }

    public static interface IMethodTransformer {
        public void transform(ClassNode var1, MethodNode var2);
    }
}

