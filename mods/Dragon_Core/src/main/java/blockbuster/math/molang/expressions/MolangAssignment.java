/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.math.molang.expressions;

import blockbuster.math.IValue;
import blockbuster.math.Variable;
import blockbuster.math.molang.MolangParser;
import blockbuster.math.molang.expressions.MolangExpression;

public class MolangAssignment
extends MolangExpression {
    public Variable variable;
    public IValue expression;

    public MolangAssignment(MolangParser context, Variable variable, IValue expression) {
        super(context);
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public double get() {
        double value = this.expression.get().doubleValue();
        this.variable.set(value);
        return value;
    }

    public String toString() {
        return this.variable.getName() + " = " + this.expression.toString();
    }
}

