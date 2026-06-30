/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.math.functions;

import blockbuster.math.Constant;
import blockbuster.math.IValue;

public abstract class Function
implements IValue {
    protected IValue[] args;
    protected String name;
    protected IValue result = new Constant(0.0);

    public Function(IValue[] values, String name) throws Exception {
        if (values.length < this.getRequiredArguments()) {
            String message = String.format("Function '%s' requires at least %s arguments. %s are given!", this.getName(), this.getRequiredArguments(), values.length);
            throw new Exception(message);
        }
        for (int i2 = 0; i2 < values.length; ++i2) {
            this.verifyArgument(i2, values[i2]);
        }
        this.args = values;
        this.name = name;
    }

    protected void verifyArgument(int index, IValue value) {
    }

    @Override
    public void set(double value) {
    }

    @Override
    public void set(String value) {
    }

    public IValue getArg(int index) {
        if (index < 0 || index >= this.args.length) {
            throw new IllegalStateException("Index should be within the argument's length range! Given " + index + ", arguments length: " + this.args.length);
        }
        return this.args[index].get();
    }

    public String toString() {
        String args = "";
        for (int i2 = 0; i2 < this.args.length; ++i2) {
            args = args + this.args[i2].toString();
            if (i2 >= this.args.length - 1) continue;
            args = args + ", ";
        }
        return this.getName() + "(" + args + ")";
    }

    public String getName() {
        return this.name;
    }

    public int getRequiredArguments() {
        return 0;
    }
}

