/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.entity.model.anim;

import net.optifine.entity.model.anim.IModelResolver;
import net.optifine.entity.model.anim.ModelVariableFloat;
import net.optifine.expr.ExpressionParser;
import net.optifine.expr.IExpressionFloat;
import net.optifine.expr.ParseException;

public class ModelVariableUpdater {
    private String modelVariableName;
    private String expressionText;
    private ModelVariableFloat modelVariable;
    private IExpressionFloat expression;

    public boolean initialize(IModelResolver mr) {
        this.modelVariable = mr.getModelVariable(this.modelVariableName);
        if (this.modelVariable == null) {
            Config.warn("Model variable not found: " + this.modelVariableName);
            return false;
        }
        try {
            ExpressionParser ep = new ExpressionParser(mr);
            this.expression = ep.parseFloat(this.expressionText);
            return true;
        }
        catch (ParseException e) {
            Config.warn("Error parsing expression: " + this.expressionText);
            Config.warn(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public ModelVariableUpdater(String modelVariableName, String expressionText) {
        this.modelVariableName = modelVariableName;
        this.expressionText = expressionText;
    }

    public void update() {
        float val = this.expression.eval();
        this.modelVariable.setValue(val);
    }
}

