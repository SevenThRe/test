/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.math;

import blockbuster.math.IValue;

public class Group
implements IValue {
    private IValue value;

    public Group(IValue value) {
        this.value = value;
    }

    @Override
    public IValue get() {
        return this.value.get();
    }

    @Override
    public boolean isNumber() {
        return this.value.isNumber();
    }

    @Override
    public void set(double value) {
        this.value.set(value);
    }

    @Override
    public void set(String value) {
        this.value.set(value);
    }

    @Override
    public double doubleValue() {
        return this.value.doubleValue();
    }

    @Override
    public boolean booleanValue() {
        return this.value.booleanValue();
    }

    @Override
    public String stringValue() {
        return this.value.stringValue();
    }

    public String toString() {
        return "(" + this.value.toString() + ")";
    }
}

