/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.shaders.uniform;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import net.optifine.expr.IExpression;
import net.optifine.expr.IExpressionCached;
import net.optifine.shaders.uniform.CustomUniform;

public class CustomUniforms {
    private CustomUniform[] uniforms;
    private IExpressionCached[] expressionsCached;

    public CustomUniforms(CustomUniform[] uniforms, Map<String, IExpression> mapExpressions) {
        this.uniforms = uniforms;
        ArrayList<IExpressionCached> list = new ArrayList<IExpressionCached>();
        Set<String> keys = mapExpressions.keySet();
        for (String key : keys) {
            IExpression expr = mapExpressions.get(key);
            if (!(expr instanceof IExpressionCached)) continue;
            IExpressionCached exprCached = (IExpressionCached)((Object)expr);
            list.add(exprCached);
        }
        this.expressionsCached = list.toArray(new IExpressionCached[list.size()]);
    }

    public void setProgram(int program) {
        for (int i = 0; i < this.uniforms.length; ++i) {
            CustomUniform uniform = this.uniforms[i];
            uniform.setProgram(program);
        }
    }

    public void update() {
        this.resetCache();
        for (int i = 0; i < this.uniforms.length; ++i) {
            CustomUniform uniform = this.uniforms[i];
            uniform.update();
        }
    }

    private void resetCache() {
        for (int i = 0; i < this.expressionsCached.length; ++i) {
            IExpressionCached exprCached = this.expressionsCached[i];
            exprCached.reset();
        }
    }

    public void reset() {
        for (int i = 0; i < this.uniforms.length; ++i) {
            CustomUniform cu = this.uniforms[i];
            cu.reset();
        }
    }
}

