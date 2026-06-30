/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.math;

import blockbuster.math.IValue;
import blockbuster.math.Operation;
import blockbuster.math.Wrapper;

public class Negative
extends Wrapper {
    public Negative(IValue value) {
        super(value);
    }

    @Override
    protected void process() {
        this.result.set(this.doubleValue());
    }

    @Override
    public double doubleValue() {
        return -this.value.doubleValue();
    }

    @Override
    public boolean booleanValue() {
        return Operation.isTrue(this.doubleValue());
    }

    public String toString() {
        return "-" + this.value.toString();
    }
}

