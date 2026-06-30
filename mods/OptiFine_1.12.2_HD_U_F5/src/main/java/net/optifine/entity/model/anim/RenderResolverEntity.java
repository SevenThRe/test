/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model.anim;

import net.optifine.entity.model.anim.IRenderResolver;
import net.optifine.entity.model.anim.RenderEntityParameterBool;
import net.optifine.entity.model.anim.RenderEntityParameterFloat;
import net.optifine.expr.IExpression;

public class RenderResolverEntity
implements IRenderResolver {
    @Override
    public IExpression getParameter(String name) {
        RenderEntityParameterBool parBool = RenderEntityParameterBool.parse(name);
        if (parBool != null) {
            return parBool;
        }
        RenderEntityParameterFloat parFloat = RenderEntityParameterFloat.parse(name);
        if (parFloat != null) {
            return parFloat;
        }
        return null;
    }
}

