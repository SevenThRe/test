/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  brs
 */
package net.optifine.entity.model.anim;

import net.optifine.entity.model.anim.ModelVariableFloat;
import net.optifine.expr.IExpressionResolver;

public interface IModelResolver
extends IExpressionResolver {
    public brs getModelRenderer(String var1);

    public ModelVariableFloat getModelVariable(String var1);
}

