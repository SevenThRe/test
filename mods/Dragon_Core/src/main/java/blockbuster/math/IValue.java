/*
 * Decompiled with CFR 0.152.
 */
package blockbuster.math;

public interface IValue {
    public IValue get();

    public boolean isNumber();

    public void set(double var1);

    public void set(String var1);

    public double doubleValue();

    public boolean booleanValue();

    public String stringValue();
}

