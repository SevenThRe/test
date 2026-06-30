/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.math.functions.classic;

import blockbuster.math.IValue;
import blockbuster.math.functions.NNFunction;

public class Ln
extends NNFunction {
    public Ln(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    @Override
    public int getRequiredArguments() {
        return 1;
    }

    @Override
    public double doubleValue() {
        return Math.log(this.getArg(0).doubleValue());
    }
}

