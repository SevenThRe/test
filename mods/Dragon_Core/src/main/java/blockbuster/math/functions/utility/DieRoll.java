/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.math.functions.utility;

import blockbuster.math.IValue;
import blockbuster.math.functions.NNFunction;

public class DieRoll
extends NNFunction {
    public static double rollDie(int num, double min, double max) {
        double m2 = Math.max(max, min);
        double n2 = Math.min(max, min);
        double sum = 0.0;
        for (int i2 = 0; i2 < num; ++i2) {
            sum += Math.random() * (m2 - n2) + n2;
        }
        return sum;
    }

    public DieRoll(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    @Override
    public int getRequiredArguments() {
        return 3;
    }

    @Override
    public double doubleValue() {
        return DieRoll.rollDie((int)this.getArg(0).doubleValue(), this.getArg(1).doubleValue(), this.getArg(2).doubleValue());
    }
}

