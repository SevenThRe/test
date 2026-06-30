/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.math.functions.utility;

import blockbuster.math.IValue;
import blockbuster.math.functions.utility.DieRoll;

public class DieRollInteger
extends DieRoll {
    public DieRollInteger(IValue[] values, String name) throws Exception {
        super(values, name);
    }

    @Override
    public double doubleValue() {
        return (int)super.doubleValue();
    }
}

