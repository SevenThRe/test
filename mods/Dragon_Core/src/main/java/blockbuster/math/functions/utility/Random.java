/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.math.functions.utility;

import blockbuster.math.IValue;
import blockbuster.math.functions.NNFunction;

public class Random
extends NNFunction {
    public java.util.Random random = new java.util.Random();

    public Random(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    @Override
    public double doubleValue() {
        double random;
        if (this.args.length >= 3) {
            this.random.setSeed((long)this.getArg(2).doubleValue());
            random = this.random.nextDouble();
        } else {
            random = Math.random();
        }
        if (this.args.length >= 2) {
            double a2 = this.getArg(0).doubleValue();
            double b2 = this.getArg(1).doubleValue();
            double min = Math.min(a2, b2);
            double max = Math.max(a2, b2);
            random = random * (max - min) + min;
        } else if (this.args.length >= 1) {
            random *= this.getArg(0).doubleValue();
        }
        return random;
    }
}

