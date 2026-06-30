/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.math.matrix;

import goblinbob.mobends.core.math.matrix.IMat3x3d;

public class Mat3x3d
implements IMat3x3d {
    public static final Mat3x3d ONE = new Mat3x3d(1);
    public static final Mat3x3d IDENTITY = new Mat3x3d(new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0});
    private final double[] fields = new double[9];

    public Mat3x3d() {
    }

    public Mat3x3d(int fillValue) {
        this();
        for (int i2 = 0; i2 < 9; ++i2) {
            this.fields[i2] = fillValue;
        }
    }

    public Mat3x3d(double[] fields) {
        this();
        for (int i2 = 0; i2 < this.fields.length; ++i2) {
            this.fields[i2] = fields[i2];
        }
    }

    public Mat3x3d(IMat3x3d other) {
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
        return this.fields[r2 + c2 * 3];
    }

    @Override
    public void set(int c2, int r2, double value) {
        this.fields[r2 + c2 * 3] = value;
    }

    @Override
    public void setFields(double ... values) {
        int len = Math.min(values.length, 9);
        for (int i2 = 0; i2 < len; ++i2) {
            this.fields[i2] = values[i2];
        }
    }

    @Override
    public void copyFrom(IMat3x3d src) {
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

