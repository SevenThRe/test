/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonPrimitive
 */
package blockbuster.math.molang.expressions;

import blockbuster.math.Constant;
import blockbuster.math.IValue;
import blockbuster.math.molang.MolangParser;
import blockbuster.math.molang.expressions.MolangExpression;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public class MolangValue
extends MolangExpression {
    public IValue value;
    public boolean returns;

    public MolangValue(MolangParser context, IValue value) {
        super(context);
        this.value = value;
    }

    public MolangExpression addReturn() {
        this.returns = true;
        return this;
    }

    @Override
    public double get() {
        return this.value.get().doubleValue();
    }

    public String toString() {
        return (this.returns ? "return " : "") + this.value.toString();
    }

    @Override
    public JsonElement toJson() {
        if (this.value instanceof Constant) {
            return new JsonPrimitive((Number)this.value.get().doubleValue());
        }
        return super.toJson();
    }
}

