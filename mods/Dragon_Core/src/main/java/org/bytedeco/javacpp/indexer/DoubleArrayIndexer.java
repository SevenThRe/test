/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.indexer.DoubleIndexer;
import org.bytedeco.javacpp.indexer.Index;

public class DoubleArrayIndexer
extends DoubleIndexer {
    protected double[] array;

    public DoubleArrayIndexer(double[] array) {
        this(array, Index.create((long)array.length));
    }

    public DoubleArrayIndexer(double[] array, long ... sizes) {
        this(array, Index.create(sizes));
    }

    public DoubleArrayIndexer(double[] array, long[] sizes, long[] strides) {
        this(array, Index.create(sizes, strides));
    }

    public DoubleArrayIndexer(double[] array, Index index) {
        super(index);
        this.array = array;
    }

    public double[] array() {
        return this.array;
    }

    public DoubleIndexer reindex(Index index) {
        return new DoubleArrayIndexer(this.array, index);
    }

    @Override
    public double get(long i2) {
        return this.array[(int)this.index(i2)];
    }

    @Override
    public DoubleIndexer get(long i2, double[] d2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            d2[offset + n2] = this.array[(int)this.index(i2) + n2];
        }
        return this;
    }

    @Override
    public double get(long i2, long j2) {
        return this.array[(int)this.index(i2, j2)];
    }

    @Override
    public DoubleIndexer get(long i2, long j2, double[] d2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            d2[offset + n2] = this.array[(int)this.index(i2, j2) + n2];
        }
        return this;
    }

    @Override
    public double get(long i2, long j2, long k2) {
        return this.array[(int)this.index(i2, j2, k2)];
    }

    @Override
    public double get(long ... indices) {
        return this.array[(int)this.index(indices)];
    }

    @Override
    public DoubleIndexer get(long[] indices, double[] d2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            d2[offset + n2] = this.array[(int)this.index(indices) + n2];
        }
        return this;
    }

    @Override
    public DoubleIndexer put(long i2, double d2) {
        this.array[(int)this.index((long)i2)] = d2;
        return this;
    }

    @Override
    public DoubleIndexer put(long i2, double[] d2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long)i2) + n2] = d2[offset + n2];
        }
        return this;
    }

    @Override
    public DoubleIndexer put(long i2, long j2, double d2) {
        this.array[(int)this.index((long)i2, (long)j2)] = d2;
        return this;
    }

    @Override
    public DoubleIndexer put(long i2, long j2, double[] d2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long)i2, (long)j2) + n2] = d2[offset + n2];
        }
        return this;
    }

    @Override
    public DoubleIndexer put(long i2, long j2, long k2, double d2) {
        this.array[(int)this.index((long)i2, (long)j2, (long)k2)] = d2;
        return this;
    }

    @Override
    public DoubleIndexer put(long[] indices, double d2) {
        this.array[(int)this.index((long[])indices)] = d2;
        return this;
    }

    @Override
    public DoubleIndexer put(long[] indices, double[] d2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long[])indices) + n2] = d2[offset + n2];
        }
        return this;
    }

    @Override
    public void release() {
        this.array = null;
    }
}

