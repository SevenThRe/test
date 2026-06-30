/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.math.functions.rounding;

import blockbuster.math.IValue;
import blockbuster.math.functions.NNFunction;

public class Floor
extends NNFunction {
    public Floor(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    @Override
    public int getRequiredArguments() {
        return 1;
    }

    @Override
    public double doubleValue() {
        return Math.floor(this.getArg(0).doubleValue());
    }
}

