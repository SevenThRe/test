/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.math.functions.trig;

import blockbuster.math.IValue;
import blockbuster.math.functions.NNFunction;

public class Atan
extends NNFunction {
    public Atan(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    @Override
    public int getRequiredArguments() {
        return 1;
    }

    @Override
    public double doubleValue() {
        return Math.atan(this.getArg(0).doubleValue());
    }
}

