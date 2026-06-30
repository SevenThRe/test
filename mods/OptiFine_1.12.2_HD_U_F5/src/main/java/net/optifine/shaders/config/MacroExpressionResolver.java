/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.shaders.config;

import java.util.Map;
import net.optifine.expr.ConstantFloat;
import net.optifine.expr.FunctionBool;
import net.optifine.expr.FunctionType;
import net.optifine.expr.IExpression;
import net.optifine.expr.IExpressionResolver;

public class MacroExpressionResolver
implements IExpressionResolver {
    private Map<String, String> mapMacroValues = null;

    public MacroExpressionResolver(Map<String, String> mapMacroValues) {
        this.mapMacroValues = mapMacroValues;
    }

    @Override
    public IExpression getExpression(String name) {
        String nameNext;
        String PREFIX_DEFINED = "defined_";
        if (name.startsWith(PREFIX_DEFINED)) {
            String macro = name.substring(PREFIX_DEFINED.length());
            if (this.mapMacroValues.containsKey(macro)) {
                return new FunctionBool(FunctionType.TRUE, null);
            }
            return new FunctionBool(FunctionType.FALSE, null);
        }
        while (this.mapMacroValues.containsKey(name) && (nameNext = this.mapMacroValues.get(name)) != null && !nameNext.equals(name)) {
            name = nameNext;
        }
        int valInt = Config.parseInt(name, Integer.MIN_VALUE);
        if (valInt == Integer.MIN_VALUE) {
            Config.warn("Unknown macro value: " + name);
            return new ConstantFloat(0.0f);
        }
        return new ConstantFloat(valInt);
    }
}

