/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.ShortIndexer;

public class ShortArrayIndexer
extends ShortIndexer {
    protected short[] array;

    public ShortArrayIndexer(short[] array) {
        this(array, Index.create((long)array.length));
    }

    public ShortArrayIndexer(short[] array, long ... sizes) {
        this(array, Index.create(sizes));
    }

    public ShortArrayIndexer(short[] array, long[] sizes, long[] strides) {
        this(array, Index.create(sizes, strides));
    }

    public ShortArrayIndexer(short[] array, Index index) {
        super(index);
        this.array = array;
    }

    public short[] array() {
        return this.array;
    }

    public ShortIndexer reindex(Index index) {
        return new ShortArrayIndexer(this.array, index);
    }

    @Override
    public short get(long i2) {
        return this.array[(int)this.index(i2)];
    }

    @Override
    public ShortIndexer get(long i2, short[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            s2[offset + n2] = this.array[(int)this.index(i2) + n2];
        }
        return this;
    }

    @Override
    public short get(long i2, long j2) {
        return this.array[(int)this.index(i2, j2)];
    }

    @Override
    public ShortIndexer get(long i2, long j2, short[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            s2[offset + n2] = this.array[(int)this.index(i2, j2) + n2];
        }
        return this;
    }

    @Override
    public short get(long i2, long j2, long k2) {
        return this.array[(int)this.index(i2, j2, k2)];
    }

    @Override
    public short get(long ... indices) {
        return this.array[(int)this.index(indices)];
    }

    @Override
    public ShortIndexer get(long[] indices, short[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            s2[offset + n2] = this.array[(int)this.index(indices) + n2];
        }
        return this;
    }

    @Override
    public ShortIndexer put(long i2, short s2) {
        this.array[(int)this.index((long)i2)] = s2;
        return this;
    }

    @Override
    public ShortIndexer put(long i2, short[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long)i2) + n2] = s2[offset + n2];
        }
        return this;
    }

    @Override
    public ShortIndexer put(long i2, long j2, short s2) {
        this.array[(int)this.index((long)i2, (long)j2)] = s2;
        return this;
    }

    @Override
    public ShortIndexer put(long i2, long j2, short[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long)i2, (long)j2) + n2] = s2[offset + n2];
        }
        return this;
    }

    @Override
    public ShortIndexer put(long i2, long j2, long k2, short s2) {
        this.array[(int)this.index((long)i2, (long)j2, (long)k2)] = s2;
        return this;
    }

    @Override
    public ShortIndexer put(long[] indices, short s2) {
        this.array[(int)this.index((long[])indices)] = s2;
        return this;
    }

    @Override
    public ShortIndexer put(long[] indices, short[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long[])indices) + n2] = s2[offset + n2];
        }
        return this;
    }

    @Override
    public void release() {
        this.array = null;
    }
}

