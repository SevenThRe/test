/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.math.functions.utility;

import blockbuster.math.IValue;
import blockbuster.math.functions.NNFunction;

public class HermiteBlend
extends NNFunction {
    public HermiteBlend(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    @Override
    public int getRequiredArguments() {
        return 1;
    }

    @Override
    public double doubleValue() {
        double x2 = this.getArg(0).doubleValue();
        return 3.0 * x2 * x2 - 2.0 * x2 * x2 * x2;
    }
}

