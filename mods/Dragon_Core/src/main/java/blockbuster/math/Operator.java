/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.math;

import blockbuster.math.Constant;
import blockbuster.math.IValue;
import blockbuster.math.Operation;

public class Operator
implements IValue {
    public static boolean DEBUG = false;
    public Operation operation;
    public IValue a;
    public IValue b;
    private IValue result = new Constant(0.0);

    public Operator(Operation op2, IValue a2, IValue b2) {
        this.operation = op2;
        this.a = a2;
        this.b = b2;
    }

    @Override
    public IValue get() {
        if (!this.isNumber() && this.operation == Operation.ADD) {
            this.result.set(this.stringValue());
        } else {
            this.result.set(this.doubleValue());
        }
        return this.result;
    }

    @Override
    public boolean isNumber() {
        return this.a.isNumber() || this.b.isNumber();
    }

    @Override
    public void set(double value) {
    }

    @Override
    public void set(String value) {
    }

    @Override
    public double doubleValue() {
        if (!this.isNumber() && this.operation == Operation.EQUALS) {
            return this.a.stringValue().equals(this.b.stringValue()) ? 1.0 : 0.0;
        }
        return this.operation.calculate(this.a.doubleValue(), this.b.doubleValue());
    }

    @Override
    public boolean booleanValue() {
        return Operation.isTrue(this.doubleValue());
    }

    @Override
    public String stringValue() {
        if (this.operation == Operation.ADD) {
            return this.a.stringValue() + this.b.stringValue();
        }
        return this.a.stringValue();
    }

    public String toString() {
        if (DEBUG) {
            return "(" + this.a.toString() + " " + this.operation.sign + " " + this.b.toString() + ")";
        }
        return this.a.toString() + " " + this.operation.sign + " " + this.b.toString();
    }
}

