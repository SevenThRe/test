/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.math.functions.trig;

import blockbuster.math.IValue;
import blockbuster.math.functions.NNFunction;

public class Cos
extends NNFunction {
    public Cos(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    @Override
    public int getRequiredArguments() {
        return 1;
    }

    @Override
    public double doubleValue() {
        return Math.cos(this.getArg(0).doubleValue());
    }
}

