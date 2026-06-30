/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.math.functions.classic;

import blockbuster.math.IValue;
import blockbuster.math.functions.NNFunction;

public class Pow
extends NNFunction {
    public Pow(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    @Override
    public int getRequiredArguments() {
        return 2;
    }

    @Override
    public double doubleValue() {
        return Math.pow(this.getArg(0).doubleValue(), this.getArg(1).doubleValue());
    }
}

