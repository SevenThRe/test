/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.indexer.StrideIndex;

public class HyperslabIndex
extends StrideIndex {
    protected long[] selectionOffsets;
    protected long[] selectionStrides;
    protected long[] selectionCounts;
    protected long[] selectionBlocks;

    public HyperslabIndex(long[] sizes, long[] selectionOffsets, long[] selectionStrides, long[] selectionCounts, long[] selectionBlocks) {
        this(sizes, HyperslabIndex.defaultStrides(sizes), selectionOffsets, selectionStrides, selectionCounts, selectionBlocks);
    }

    public HyperslabIndex(long[] sizes, long[] strides, long[] selectionOffsets, long[] selectionStrides, long[] selectionCounts, long[] selectionBlocks) {
        super(sizes, strides);
        this.selectionOffsets = selectionOffsets;
        this.selectionStrides = selectionStrides;
        this.selectionCounts = selectionCounts;
        this.selectionBlocks = selectionBlocks;
        for (int i2 = 0; i2 < selectionCounts.length; ++i2) {
            this.sizes[i2] = selectionCounts[i2] * selectionBlocks[i2];
        }
    }

    @Override
    public long index(long i2) {
        return (this.selectionOffsets[0] + this.selectionStrides[0] * (i2 / this.selectionBlocks[0]) + i2 % this.selectionBlocks[0]) * this.strides[0];
    }

    @Override
    public long index(long i2, long j2) {
        return (this.selectionOffsets[0] + this.selectionStrides[0] * (i2 / this.selectionBlocks[0]) + i2 % this.selectionBlocks[0]) * this.strides[0] + (this.selectionOffsets[1] + this.selectionStrides[1] * (j2 / this.selectionBlocks[1]) + j2 % this.selectionBlocks[1]) * this.strides[1];
    }

    @Override
    public long index(long i2, long j2, long k2) {
        return (this.selectionOffsets[0] + this.selectionStrides[0] * (i2 / this.selectionBlocks[0]) + i2 % this.selectionBlocks[0]) * this.strides[0] + (this.selectionOffsets[1] + this.selectionStrides[1] * (j2 / this.selectionBlocks[1]) + j2 % this.selectionBlocks[1]) * this.strides[1] + (this.selectionOffsets[2] + this.selectionStrides[2] * (k2 / this.selectionBlocks[2]) + k2 % this.selectionBlocks[2]) * this.strides[2];
    }

    @Override
    public long index(long ... indices) {
        long index = 0L;
        for (int i2 = 0; i2 < indices.length; ++i2) {
            long coordinate = indices[i2];
            long mappedCoordinate = this.selectionOffsets[i2] + this.selectionStrides[i2] * (coordinate / this.selectionBlocks[i2]) + coordinate % this.selectionBlocks[i2];
            index += mappedCoordinate * this.strides[i2];
        }
        return index;
    }
}

