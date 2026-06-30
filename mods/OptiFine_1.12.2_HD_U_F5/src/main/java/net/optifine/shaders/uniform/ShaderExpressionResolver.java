/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  anh
 */
package net.optifine.shaders.uniform;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import net.optifine.expr.ConstantFloat;
import net.optifine.expr.IExpression;
import net.optifine.expr.IExpressionResolver;
import net.optifine.shaders.SMCLog;
import net.optifine.shaders.uniform.ShaderParameterBool;
import net.optifine.shaders.uniform.ShaderParameterFloat;
import net.optifine.shaders.uniform.ShaderParameterIndexed;

public class ShaderExpressionResolver
implements IExpressionResolver {
    private Map<String, IExpression> mapExpressions = new HashMap<String, IExpression>();

    public ShaderExpressionResolver(Map<String, IExpression> map) {
        this.registerExpressions();
        Set<String> keys = map.keySet();
        for (String name : keys) {
            IExpression expr = map.get(name);
            this.registerExpression(name, expr);
        }
    }

    private void registerExpressions() {
        ShaderParameterFloat[] spfs = ShaderParameterFloat.values();
        for (int i = 0; i < spfs.length; ++i) {
            ShaderParameterFloat spf = spfs[i];
            this.addParameterFloat(this.mapExpressions, spf);
        }
        ShaderParameterBool[] spbs = ShaderParameterBool.values();
        for (int i = 0; i < spbs.length; ++i) {
            ShaderParameterBool spb = spbs[i];
            this.mapExpressions.put(spb.getName(), spb);
        }
        for (anh biome : anh.p) {
            String name = biome.l().trim();
            name = "BIOME_" + name.toUpperCase().replace(' ', '_');
            int id = anh.a((anh)biome);
            ConstantFloat expr = new ConstantFloat(id);
            this.registerExpression(name, expr);
        }
    }

    private void addParameterFloat(Map<String, IExpression> map, ShaderParameterFloat spf) {
        String[] indexNames1 = spf.getIndexNames1();
        if (indexNames1 == null) {
            map.put(spf.getName(), new ShaderParameterIndexed(spf));
            return;
        }
        for (int i1 = 0; i1 < indexNames1.length; ++i1) {
            String indexName1 = indexNames1[i1];
            String[] indexNames2 = spf.getIndexNames2();
            if (indexNames2 == null) {
                map.put(spf.getName() + "." + indexName1, new ShaderParameterIndexed(spf, i1));
                continue;
            }
            for (int i2 = 0; i2 < indexNames2.length; ++i2) {
                String indexName2 = indexNames2[i2];
                map.put(spf.getName() + "." + indexName1 + "." + indexName2, new ShaderParameterIndexed(spf, i1, i2));
            }
        }
    }

    public boolean registerExpression(String name, IExpression expr) {
        if (this.mapExpressions.containsKey(name)) {
            SMCLog.warning("Expression already defined: " + name);
            return false;
        }
        this.mapExpressions.put(name, expr);
        return true;
    }

    @Override
    public IExpression getExpression(String name) {
        return this.mapExpressions.get(name);
    }

    public boolean hasExpression(String name) {
        return this.mapExpressions.containsKey(name);
    }
}

