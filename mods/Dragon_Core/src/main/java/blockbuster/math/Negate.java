/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.math;

import blockbuster.math.IValue;
import blockbuster.math.Wrapper;

public class Negate
extends Wrapper {
    public Negate(IValue value) {
        super(value);
    }

    @Override
    protected void process() {
        this.result.set(this.doubleValue());
    }

    @Override
    public double doubleValue() {
        return this.booleanValue() ? 1.0 : 0.0;
    }

    @Override
    public boolean booleanValue() {
        return !this.value.booleanValue();
    }

    public String toString() {
        return "!" + this.value.toString();
    }
}

