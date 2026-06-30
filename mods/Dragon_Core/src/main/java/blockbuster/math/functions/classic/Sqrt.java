/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.math.functions.classic;

import blockbuster.math.IValue;
import blockbuster.math.functions.NNFunction;

public class Sqrt
extends NNFunction {
    public Sqrt(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    @Override
    public int getRequiredArguments() {
        return 1;
    }

    @Override
    public double doubleValue() {
        return Math.sqrt(this.getArg(0).doubleValue());
    }
}

