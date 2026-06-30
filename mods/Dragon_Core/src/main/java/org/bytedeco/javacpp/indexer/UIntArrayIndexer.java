/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.UIntIndexer;

public class UIntArrayIndexer
extends UIntIndexer {
    protected int[] array;

    public UIntArrayIndexer(int[] array) {
        this(array, Index.create((long)array.length));
    }

    public UIntArrayIndexer(int[] array, long ... sizes) {
        this(array, Index.create(sizes));
    }

    public UIntArrayIndexer(int[] array, long[] sizes, long[] strides) {
        this(array, Index.create(sizes, strides));
    }

    public UIntArrayIndexer(int[] array, Index index) {
        super(index);
        this.array = array;
    }

    public int[] array() {
        return this.array;
    }

    public UIntIndexer reindex(Index index) {
        return new UIntArrayIndexer(this.array, index);
    }

    @Override
    public long get(long i2) {
        return (long)this.array[(int)this.index(i2)] & 0xFFFFFFFFL;
    }

    @Override
    public UIntIndexer get(long i2, long[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            m2[offset + n2] = (long)this.array[(int)this.index(i2) + n2] & 0xFFFFFFFFL;
        }
        return this;
    }

    @Override
    public long get(long i2, long j2) {
        return (long)this.array[(int)this.index(i2, j2)] & 0xFFFFFFFFL;
    }

    @Override
    public UIntIndexer get(long i2, long j2, long[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            m2[offset + n2] = (long)this.array[(int)this.index(i2, j2) + n2] & 0xFFFFFFFFL;
        }
        return this;
    }

    @Override
    public long get(long i2, long j2, long k2) {
        return (long)this.array[(int)this.index(i2, j2, k2)] & 0xFFFFFFFFL;
    }

    @Override
    public long get(long ... indices) {
        return (long)this.array[(int)this.index(indices)] & 0xFFFFFFFFL;
    }

    @Override
    public UIntIndexer get(long[] indices, long[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            m2[offset + n2] = (long)this.array[(int)this.index(indices) + n2] & 0xFFFFFFFFL;
        }
        return this;
    }

    @Override
    public UIntIndexer put(long i2, long n2) {
        this.array[(int)this.index((long)i2)] = (int)n2;
        return this;
    }

    @Override
    public UIntIndexer put(long i2, long[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long)i2) + n2] = (int)m2[offset + n2];
        }
        return this;
    }

    @Override
    public UIntIndexer put(long i2, long j2, long n2) {
        this.array[(int)this.index((long)i2, (long)j2)] = (int)n2;
        return this;
    }

    @Override
    public UIntIndexer put(long i2, long j2, long[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long)i2, (long)j2) + n2] = (int)m2[offset + n2];
        }
        return this;
    }

    @Override
    public UIntIndexer put(long i2, long j2, long k2, long n2) {
        this.array[(int)this.index((long)i2, (long)j2, (long)k2)] = (int)n2;
        return this;
    }

    @Override
    public UIntIndexer put(long[] indices, long n2) {
        this.array[(int)this.index((long[])indices)] = (int)n2;
        return this;
    }

    @Override
    public UIntIndexer put(long[] indices, long[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long[])indices) + n2] = (int)m2[offset + n2];
        }
        return this;
    }

    @Override
    public void release() {
        this.array = null;
    }
}

