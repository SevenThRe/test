/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.math;

import blockbuster.math.IValue;
import blockbuster.math.Operation;

public class Constant
implements IValue {
    private double doubleValue;
    private String stringValue;

    public Constant(double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public Constant(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public IValue get() {
        return this;
    }

    @Override
    public boolean isNumber() {
        return this.stringValue == null;
    }

    @Override
    public void set(double value) {
        this.doubleValue = value;
        this.stringValue = null;
    }

    @Override
    public void set(String value) {
        this.doubleValue = 0.0;
        this.stringValue = value;
    }

    @Override
    public double doubleValue() {
        return this.doubleValue;
    }

    @Override
    public boolean booleanValue() {
        if (this.isNumber()) {
            return Operation.isTrue(this.doubleValue);
        }
        return this.stringValue.equalsIgnoreCase("true");
    }

    @Override
    public String stringValue() {
        return this.stringValue;
    }

    public String toString() {
        return this.stringValue == null ? String.valueOf(this.doubleValue) : "\"" + this.stringValue + "\"";
    }
}

