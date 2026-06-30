/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.math.functions.utility;

import blockbuster.math.IValue;
import blockbuster.math.functions.NNFunction;
import blockbuster.utils.Interpolations;

public class LerpRotate
extends NNFunction {
    public LerpRotate(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    @Override
    public int getRequiredArguments() {
        return 3;
    }

    @Override
    public double doubleValue() {
        return Interpolations.lerpYaw(this.getArg(0).doubleValue(), this.getArg(1).doubleValue(), this.getArg(2).doubleValue());
    }
}

