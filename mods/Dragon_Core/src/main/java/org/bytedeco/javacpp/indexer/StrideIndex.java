/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.indexer.Index;

public class StrideIndex
extends Index {
    protected final long[] strides;

    public static long[] defaultStrides(long ... sizes) {
        long[] strides = new long[sizes.length];
        strides[sizes.length - 1] = 1L;
        for (int i2 = sizes.length - 2; i2 >= 0; --i2) {
            strides[i2] = strides[i2 + 1] * sizes[i2 + 1];
        }
        return strides;
    }

    public StrideIndex(long ... sizes) {
        this(sizes, StrideIndex.defaultStrides(sizes));
    }

    public StrideIndex(long[] sizes, long[] strides) {
        super(sizes);
        this.strides = strides;
    }

    public long[] strides() {
        return this.strides;
    }

    @Override
    public long index(long i2) {
        return i2 * this.strides[0];
    }

    @Override
    public long index(long i2, long j2) {
        return i2 * this.strides[0] + j2 * this.strides[1];
    }

    @Override
    public long index(long i2, long j2, long k2) {
        return i2 * this.strides[0] + j2 * this.strides[1] + k2 * this.strides[2];
    }

    @Override
    public long index(long ... indices) {
        long index = 0L;
        for (int i2 = 0; i2 < indices.length && i2 < this.strides.length; ++i2) {
            index += indices[i2] * this.strides[i2];
        }
        return index;
    }
}

