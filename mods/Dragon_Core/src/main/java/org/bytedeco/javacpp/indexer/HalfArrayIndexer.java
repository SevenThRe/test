/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.indexer.HalfIndexer;
import org.bytedeco.javacpp.indexer.Index;

public class HalfArrayIndexer
extends HalfIndexer {
    protected short[] array;

    public HalfArrayIndexer(short[] array) {
        this(array, Index.create((long)array.length));
    }

    public HalfArrayIndexer(short[] array, long ... sizes) {
        this(array, Index.create(sizes));
    }

    public HalfArrayIndexer(short[] array, long[] sizes, long[] strides) {
        this(array, Index.create(sizes, strides));
    }

    public HalfArrayIndexer(short[] array, Index index) {
        super(index);
        this.array = array;
    }

    public short[] array() {
        return this.array;
    }

    public HalfIndexer reindex(Index index) {
        return new HalfArrayIndexer(this.array, index);
    }

    @Override
    public float get(long i2) {
        return HalfArrayIndexer.toFloat(this.array[(int)this.index(i2)]);
    }

    @Override
    public HalfIndexer get(long i2, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            h2[offset + n2] = HalfArrayIndexer.toFloat(this.array[(int)this.index(i2) + n2]);
        }
        return this;
    }

    @Override
    public float get(long i2, long j2) {
        return HalfArrayIndexer.toFloat(this.array[(int)this.index(i2, j2)]);
    }

    @Override
    public HalfIndexer get(long i2, long j2, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            h2[offset + n2] = HalfArrayIndexer.toFloat(this.array[(int)this.index(i2, j2) + n2]);
        }
        return this;
    }

    @Override
    public float get(long i2, long j2, long k2) {
        return HalfArrayIndexer.toFloat(this.array[(int)this.index(i2, j2, k2)]);
    }

    @Override
    public float get(long ... indices) {
        return HalfArrayIndexer.toFloat(this.array[(int)this.index(indices)]);
    }

    @Override
    public HalfIndexer get(long[] indices, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            h2[offset + n2] = HalfArrayIndexer.toFloat(this.array[(int)this.index(indices) + n2]);
        }
        return this;
    }

    @Override
    public HalfIndexer put(long i2, float h2) {
        this.array[(int)this.index((long)i2)] = (short)HalfArrayIndexer.fromFloat(h2);
        return this;
    }

    @Override
    public HalfIndexer put(long i2, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long)i2) + n2] = (short)HalfArrayIndexer.fromFloat(h2[offset + n2]);
        }
        return this;
    }

    @Override
    public HalfIndexer put(long i2, long j2, float h2) {
        this.array[(int)this.index((long)i2, (long)j2)] = (short)HalfArrayIndexer.fromFloat(h2);
        return this;
    }

    @Override
    public HalfIndexer put(long i2, long j2, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long)i2, (long)j2) + n2] = (short)HalfArrayIndexer.fromFloat(h2[offset + n2]);
        }
        return this;
    }

    @Override
    public HalfIndexer put(long i2, long j2, long k2, float h2) {
        this.array[(int)this.index((long)i2, (long)j2, (long)k2)] = (short)HalfArrayIndexer.fromFloat(h2);
        return this;
    }

    @Override
    public HalfIndexer put(long[] indices, float h2) {
        this.array[(int)this.index((long[])indices)] = (short)HalfArrayIndexer.fromFloat(h2);
        return this;
    }

    @Override
    public HalfIndexer put(long[] indices, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long[])indices) + n2] = (short)HalfArrayIndexer.fromFloat(h2[offset + n2]);
        }
        return this;
    }

    @Override
    public void release() {
        this.array = null;
    }
}

