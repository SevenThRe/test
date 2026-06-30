/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.UShortIndexer;

public class UShortArrayIndexer
extends UShortIndexer {
    protected short[] array;

    public UShortArrayIndexer(short[] array) {
        this(array, Index.create((long)array.length));
    }

    public UShortArrayIndexer(short[] array, long ... sizes) {
        this(array, Index.create(sizes));
    }

    public UShortArrayIndexer(short[] array, long[] sizes, long[] strides) {
        this(array, Index.create(sizes, strides));
    }

    public UShortArrayIndexer(short[] array, Index index) {
        super(index);
        this.array = array;
    }

    public short[] array() {
        return this.array;
    }

    public UShortIndexer reindex(Index index) {
        return new UShortArrayIndexer(this.array, index);
    }

    @Override
    public int get(long i2) {
        return this.array[(int)this.index(i2)] & 0xFFFF;
    }

    @Override
    public UShortIndexer get(long i2, int[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            s2[offset + n2] = this.array[(int)this.index(i2) + n2] & 0xFFFF;
        }
        return this;
    }

    @Override
    public int get(long i2, long j2) {
        return this.array[(int)this.index(i2, j2)] & 0xFFFF;
    }

    @Override
    public UShortIndexer get(long i2, long j2, int[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            s2[offset + n2] = this.array[(int)this.index(i2, j2) + n2] & 0xFFFF;
        }
        return this;
    }

    @Override
    public int get(long i2, long j2, long k2) {
        return this.array[(int)this.index(i2, j2, k2)] & 0xFFFF;
    }

    @Override
    public int get(long ... indices) {
        return this.array[(int)this.index(indices)] & 0xFFFF;
    }

    @Override
    public UShortIndexer get(long[] indices, int[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            s2[offset + n2] = this.array[(int)this.index(indices) + n2] & 0xFFFF;
        }
        return this;
    }

    @Override
    public UShortIndexer put(long i2, int s2) {
        this.array[(int)this.index((long)i2)] = (short)s2;
        return this;
    }

    @Override
    public UShortIndexer put(long i2, int[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long)i2) + n2] = (short)s2[offset + n2];
        }
        return this;
    }

    @Override
    public UShortIndexer put(long i2, long j2, int s2) {
        this.array[(int)this.index((long)i2, (long)j2)] = (short)s2;
        return this;
    }

    @Override
    public UShortIndexer put(long i2, long j2, int[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long)i2, (long)j2) + n2] = (short)s2[offset + n2];
        }
        return this;
    }

    @Override
    public UShortIndexer put(long i2, long j2, long k2, int s2) {
        this.array[(int)this.index((long)i2, (long)j2, (long)k2)] = (short)s2;
        return this;
    }

    @Override
    public UShortIndexer put(long[] indices, int s2) {
        this.array[(int)this.index((long[])indices)] = (short)s2;
        return this;
    }

    @Override
    public UShortIndexer put(long[] indices, int[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long[])indices) + n2] = (short)s2[offset + n2];
        }
        return this;
    }

    @Override
    public void release() {
        this.array = null;
    }
}

