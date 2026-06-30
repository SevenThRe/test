/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonPrimitive
 */
package blockbuster.math.molang.expressions;

import blockbuster.math.Constant;
import blockbuster.math.Operation;
import blockbuster.math.molang.MolangParser;
import blockbuster.math.molang.expressions.MolangValue;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public abstract class MolangExpression {
    public MolangParser context;

    public static boolean isZero(MolangExpression expression) {
        return MolangExpression.isConstant(expression, 0.0);
    }

    public static boolean isOne(MolangExpression expression) {
        return MolangExpression.isConstant(expression, 1.0);
    }

    public static boolean isConstant(MolangExpression expression, double x2) {
        if (expression instanceof MolangValue) {
            MolangValue value = (MolangValue)expression;
            return value.value instanceof Constant && Operation.equals(value.value.get().doubleValue(), x2);
        }
        return false;
    }

    public static boolean isExpressionConstant(MolangExpression expression) {
        if (expression instanceof MolangValue) {
            MolangValue value = (MolangValue)expression;
            return value.value instanceof Constant;
        }
        return false;
    }

    public MolangExpression(MolangParser context) {
        this.context = context;
    }

    public abstract double get();

    public JsonElement toJson() {
        return new JsonPrimitive(this.toString());
    }
}

