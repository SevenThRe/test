/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.indexer.HyperslabIndex;
import org.bytedeco.javacpp.indexer.OneIndex;
import org.bytedeco.javacpp.indexer.StrideIndex;

public abstract class Index {
    protected final long[] sizes;

    public static Index create(long size) {
        return new OneIndex(size);
    }

    public static Index create(long ... sizes) {
        return new StrideIndex(sizes);
    }

    public static Index create(long[] sizes, long[] strides) {
        return new StrideIndex(sizes, strides);
    }

    public static Index create(long[] sizes, long[] selectionOffsets, long[] selectionStrides, long[] selectionCounts, long[] selectionBlocks) {
        return new HyperslabIndex(sizes, selectionOffsets, selectionStrides, selectionCounts, selectionBlocks);
    }

    public static Index create(long[] sizes, long[] strides, long[] selectionOffsets, long[] selectionStrides, long[] selectionCounts, long[] selectionBlocks) {
        return new HyperslabIndex(sizes, strides, selectionOffsets, selectionStrides, selectionCounts, selectionBlocks);
    }

    public Index(long ... sizes) {
        this.sizes = sizes;
    }

    public int rank() {
        return this.sizes.length;
    }

    public long[] sizes() {
        return this.sizes;
    }

    public long size(int i2) {
        return this.sizes[i2];
    }

    public long index(long i2) {
        return this.index(new long[]{i2});
    }

    public long index(long i2, long j2) {
        return this.index(new long[]{i2, j2});
    }

    public long index(long i2, long j2, long k2) {
        return this.index(new long[]{i2, j2, k2});
    }

    public abstract long index(long ... var1);
}

