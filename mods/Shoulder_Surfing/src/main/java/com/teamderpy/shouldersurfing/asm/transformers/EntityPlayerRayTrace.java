/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.ClassWriter
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.commons.GeneratorAdapter
 *  org.objectweb.asm.commons.Method
 */
package com.teamderpy.shouldersurfing.asm.transformers;

import com.teamderpy.shouldersurfing.asm.Mappings;
import com.teamderpy.shouldersurfing.asm.ShoulderTransformer;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

public class EntityPlayerRayTrace
extends ShoulderTransformer {
    @Override
    public void transform(Mappings mappings, boolean obf, ClassWriter writer) {
        String rayTraceResult = mappings.map("RayTraceResult", obf);
        String entity = mappings.map("Entity", obf);
        String rayTrace = mappings.map("Entity#rayTrace", obf);
        Method method = Method.getMethod((String)(rayTraceResult + " " + rayTrace + " (double, float)"), (boolean)true);
        GeneratorAdapter adapter = new GeneratorAdapter(1, method, null, null, (ClassVisitor)writer);
        adapter.loadThis();
        adapter.loadArg(0);
        adapter.loadArg(1);
        adapter.invokeStatic(Type.getType((String)"com/teamderpy/shouldersurfing/asm/InjectionDelegation"), Method.getMethod((String)(rayTraceResult + " rayTrace (" + entity + ", double, float)"), (boolean)true));
        adapter.returnValue();
        adapter.endMethod();
    }

    @Override
    public String getClassId() {
        return "EntityPlayer";
    }

    @Override
    public String getMethodId() {
        return "EntityPlayer#rayTrace";
    }

    @Override
    protected boolean hasMethodTransformer() {
        return false;
    }

    @Override
    protected boolean hasClassTransformer() {
        return true;
    }
}

