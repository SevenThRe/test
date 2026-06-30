/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.IntIndexer;

public class IntArrayIndexer
extends IntIndexer {
    protected int[] array;

    public IntArrayIndexer(int[] array) {
        this(array, Index.create((long)array.length));
    }

    public IntArrayIndexer(int[] array, long ... sizes) {
        this(array, Index.create(sizes));
    }

    public IntArrayIndexer(int[] array, long[] sizes, long[] strides) {
        this(array, Index.create(sizes, strides));
    }

    public IntArrayIndexer(int[] array, Index index) {
        super(index);
        this.array = array;
    }

    public int[] array() {
        return this.array;
    }

    public IntIndexer reindex(Index index) {
        return new IntArrayIndexer(this.array, index);
    }

    @Override
    public int get(long i2) {
        return this.array[(int)this.index(i2)];
    }

    @Override
    public IntIndexer get(long i2, int[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            m2[offset + n2] = this.array[(int)this.index(i2) + n2];
        }
        return this;
    }

    @Override
    public int get(long i2, long j2) {
        return this.array[(int)this.index(i2, j2)];
    }

    @Override
    public IntIndexer get(long i2, long j2, int[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            m2[offset + n2] = this.array[(int)this.index(i2, j2) + n2];
        }
        return this;
    }

    @Override
    public int get(long i2, long j2, long k2) {
        return this.array[(int)this.index(i2, j2, k2)];
    }

    @Override
    public int get(long ... indices) {
        return this.array[(int)this.index(indices)];
    }

    @Override
    public IntIndexer get(long[] indices, int[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            m2[offset + n2] = this.array[(int)this.index(indices) + n2];
        }
        return this;
    }

    @Override
    public IntIndexer put(long i2, int n2) {
        this.array[(int)this.index((long)i2)] = n2;
        return this;
    }

    @Override
    public IntIndexer put(long i2, int[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long)i2) + n2] = m2[offset + n2];
        }
        return this;
    }

    @Override
    public IntIndexer put(long i2, long j2, int n2) {
        this.array[(int)this.index((long)i2, (long)j2)] = n2;
        return this;
    }

    @Override
    public IntIndexer put(long i2, long j2, int[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long)i2, (long)j2) + n2] = m2[offset + n2];
        }
        return this;
    }

    @Override
    public IntIndexer put(long i2, long j2, long k2, int n2) {
        this.array[(int)this.index((long)i2, (long)j2, (long)k2)] = n2;
        return this;
    }

    @Override
    public IntIndexer put(long[] indices, int n2) {
        this.array[(int)this.index((long[])indices)] = n2;
        return this;
    }

    @Override
    public IntIndexer put(long[] indices, int[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long[])indices) + n2] = m2[offset + n2];
        }
        return this;
    }

    @Override
    public void release() {
        this.array = null;
    }
}

