/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.math.matrix;

public interface IMatd {
    public double[] getFields();

    public double get(int var1, int var2);

    public int getCols();

    public int getRows();

    public void set(int var1, int var2, double var3);

    public void setFields(double ... var1);

    public void scale(double var1);
}

