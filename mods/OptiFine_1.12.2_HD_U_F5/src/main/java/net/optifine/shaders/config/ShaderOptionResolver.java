/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.shaders.config;

import java.util.HashMap;
import java.util.Map;
import net.optifine.expr.IExpression;
import net.optifine.expr.IExpressionResolver;
import net.optifine.shaders.config.ExpressionShaderOptionSwitch;
import net.optifine.shaders.config.ShaderOption;
import net.optifine.shaders.config.ShaderOptionSwitch;

public class ShaderOptionResolver
implements IExpressionResolver {
    private Map<String, ExpressionShaderOptionSwitch> mapOptions = new HashMap<String, ExpressionShaderOptionSwitch>();

    public ShaderOptionResolver(ShaderOption[] options) {
        for (int i = 0; i < options.length; ++i) {
            ShaderOption so = options[i];
            if (!(so instanceof ShaderOptionSwitch)) continue;
            ShaderOptionSwitch sos = (ShaderOptionSwitch)so;
            ExpressionShaderOptionSwitch esos = new ExpressionShaderOptionSwitch(sos);
            this.mapOptions.put(so.getName(), esos);
        }
    }

    @Override
    public IExpression getExpression(String name) {
        ExpressionShaderOptionSwitch esos = this.mapOptions.get(name);
        return esos;
    }
}

