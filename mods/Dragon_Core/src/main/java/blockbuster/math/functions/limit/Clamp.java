/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.math.functions.limit;

import blockbuster.math.IValue;
import blockbuster.math.MathUtils;
import blockbuster.math.functions.NNFunction;

public class Clamp
extends NNFunction {
    public Clamp(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    @Override
    public int getRequiredArguments() {
        return 3;
    }

    @Override
    public double doubleValue() {
        return MathUtils.clamp(this.getArg(0).doubleValue(), this.getArg(1).doubleValue(), this.getArg(2).doubleValue());
    }
}

