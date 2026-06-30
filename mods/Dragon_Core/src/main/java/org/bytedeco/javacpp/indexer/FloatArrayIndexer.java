/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.indexer.FloatIndexer;
import org.bytedeco.javacpp.indexer.Index;

public class FloatArrayIndexer
extends FloatIndexer {
    protected float[] array;

    public FloatArrayIndexer(float[] array) {
        this(array, Index.create((long)array.length));
    }

    public FloatArrayIndexer(float[] array, long ... sizes) {
        this(array, Index.create(sizes));
    }

    public FloatArrayIndexer(float[] array, long[] sizes, long[] strides) {
        this(array, Index.create(sizes, strides));
    }

    public FloatArrayIndexer(float[] array, Index index) {
        super(index);
        this.array = array;
    }

    public float[] array() {
        return this.array;
    }

    public FloatIndexer reindex(Index index) {
        return new FloatArrayIndexer(this.array, index);
    }

    @Override
    public float get(long i2) {
        return this.array[(int)this.index(i2)];
    }

    @Override
    public FloatIndexer get(long i2, float[] f2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            f2[offset + n2] = this.array[(int)this.index(i2) + n2];
        }
        return this;
    }

    @Override
    public float get(long i2, long j2) {
        return this.array[(int)this.index(i2, j2)];
    }

    @Override
    public FloatIndexer get(long i2, long j2, float[] f2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            f2[offset + n2] = this.array[(int)this.index(i2, j2) + n2];
        }
        return this;
    }

    @Override
    public float get(long i2, long j2, long k2) {
        return this.array[(int)this.index(i2, j2, k2)];
    }

    @Override
    public float get(long ... indices) {
        return this.array[(int)this.index(indices)];
    }

    @Override
    public FloatIndexer get(long[] indices, float[] f2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            f2[offset + n2] = this.array[(int)this.index(indices) + n2];
        }
        return this;
    }

    @Override
    public FloatIndexer put(long i2, float f2) {
        this.array[(int)this.index((long)i2)] = f2;
        return this;
    }

    @Override
    public FloatIndexer put(long i2, float[] f2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long)i2) + n2] = f2[offset + n2];
        }
        return this;
    }

    @Override
    public FloatIndexer put(long i2, long j2, float f2) {
        this.array[(int)this.index((long)i2, (long)j2)] = f2;
        return this;
    }

    @Override
    public FloatIndexer put(long i2, long j2, float[] f2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long)i2, (long)j2) + n2] = f2[offset + n2];
        }
        return this;
    }

    @Override
    public FloatIndexer put(long i2, long j2, long k2, float f2) {
        this.array[(int)this.index((long)i2, (long)j2, (long)k2)] = f2;
        return this;
    }

    @Override
    public FloatIndexer put(long[] indices, float f2) {
        this.array[(int)this.index((long[])indices)] = f2;
        return this;
    }

    @Override
    public FloatIndexer put(long[] indices, float[] f2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long[])indices) + n2] = f2[offset + n2];
        }
        return this;
    }

    @Override
    public void release() {
        this.array = null;
    }
}

