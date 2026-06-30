/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.math.molang.functions;

import blockbuster.math.IValue;
import blockbuster.math.functions.trig.Atan2;

public class Atan2Degrees
extends Atan2 {
    public Atan2Degrees(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    @Override
    public double doubleValue() {
        return super.doubleValue() / Math.PI * 180.0;
    }
}

