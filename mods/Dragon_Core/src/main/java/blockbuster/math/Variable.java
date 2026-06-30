/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.math;

import blockbuster.math.Constant;

public class Variable
extends Constant {
    private String name;

    public Variable(String name, double value) {
        super(value);
        this.name = name;
    }

    public Variable(String name, String value) {
        super(value);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

