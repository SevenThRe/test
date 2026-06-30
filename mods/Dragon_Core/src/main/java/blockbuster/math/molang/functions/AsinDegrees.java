/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.math.molang.functions;

import blockbuster.math.IValue;
import blockbuster.math.functions.trig.Asin;

public class AsinDegrees
extends Asin {
    public AsinDegrees(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    @Override
    public double doubleValue() {
        return super.doubleValue() / Math.PI * 180.0;
    }
}

