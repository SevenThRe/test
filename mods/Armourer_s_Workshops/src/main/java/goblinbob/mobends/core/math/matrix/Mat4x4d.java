/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.math.matrix;

import goblinbob.mobends.core.math.matrix.IMat4x4d;

public class Mat4x4d
implements IMat4x4d {
    public static final Mat4x4d ONE = new Mat4x4d(1);
    public static final Mat4x4d IDENTITY = new Mat4x4d(new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0});
    private final double[] fields = new double[16];

    public Mat4x4d() {
    }

    public Mat4x4d(int fillValue) {
        this();
        for (int i2 = 0; i2 < 16; ++i2) {
            this.fields[i2] = fillValue;
        }
    }

    public Mat4x4d(double[] fields) {
        this();
        for (int i2 = 0; i2 < this.fields.length; ++i2) {
            this.fields[i2] = fields[i2];
        }
    }

    public Mat4x4d(IMat4x4d other) {
        this();
        double[] otherFields = other.getFields();
        for (int i2 = 0; i2 < this.fields.length; ++i2) {
            this.fields[i2] = otherFields[i2];
        }
    }

    @Override
    public double[] getFields() {
        return this.fields;
    }

    @Override
    public double get(int c2, int r2) {
        return this.fields[r2 + c2 * 4];
    }

    @Override
    public void set(int c2, int r2, double value) {
        this.fields[r2 + c2 * 4] = value;
    }

    @Override
    public void setFields(double ... values) {
        int len = Math.min(values.length, 16);
        for (int i2 = 0; i2 < len; ++i2) {
            this.fields[i2] = values[i2];
        }
    }

    @Override
    public void copyFrom(IMat4x4d src) {
        double[] srcFields = src.getFields();
        for (int i2 = 0; i2 < this.fields.length; ++i2) {
            this.fields[i2] = srcFields[i2];
        }
    }

    @Override
    public void scale(double scalar) {
        int i2 = 0;
        while (i2 < this.fields.length) {
            int n2 = i2++;
            this.fields[n2] = this.fields[n2] * scalar;
        }
    }
}

