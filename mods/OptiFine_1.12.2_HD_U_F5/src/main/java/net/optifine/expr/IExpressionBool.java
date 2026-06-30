/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.expr;

import net.optifine.expr.ExpressionType;
import net.optifine.expr.IExpression;

public interface IExpressionBool
extends IExpression {
    public boolean eval();

    @Override
    default public ExpressionType getExpressionType() {
        return ExpressionType.BOOL;
    }
}

