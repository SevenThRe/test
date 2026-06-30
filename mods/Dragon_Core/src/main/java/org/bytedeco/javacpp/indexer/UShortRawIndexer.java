/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.ShortPointer;
import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.Raw;
import org.bytedeco.javacpp.indexer.UShortIndexer;

public class UShortRawIndexer
extends UShortIndexer {
    protected static final Raw RAW = Raw.getInstance();
    protected ShortPointer pointer;
    final long base;
    final long size;

    public UShortRawIndexer(ShortPointer pointer) {
        this(pointer, Index.create(pointer.limit() - pointer.position()));
    }

    public UShortRawIndexer(ShortPointer pointer, long ... sizes) {
        this(pointer, Index.create(sizes));
    }

    public UShortRawIndexer(ShortPointer pointer, long[] sizes, long[] strides) {
        this(pointer, Index.create(sizes, strides));
    }

    public UShortRawIndexer(ShortPointer pointer, Index index) {
        super(index);
        this.pointer = pointer;
        this.base = pointer.address() + pointer.position() * 2L;
        this.size = pointer.limit() - pointer.position();
    }

    @Override
    public Pointer pointer() {
        return this.pointer;
    }

    public UShortIndexer reindex(Index index) {
        return new UShortRawIndexer(this.pointer, index);
    }

    public int getRaw(long i2) {
        return RAW.getShort(this.base + UShortRawIndexer.checkIndex(i2, this.size) * 2L) & 0xFFFF;
    }

    @Override
    public int get(long i2) {
        return this.getRaw(this.index(i2));
    }

    @Override
    public UShortIndexer get(long i2, int[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            s2[offset + n2] = this.getRaw(this.index(i2) + (long)n2) & 0xFFFF;
        }
        return this;
    }

    @Override
    public int get(long i2, long j2) {
        return this.getRaw(this.index(i2, j2)) & 0xFFFF;
    }

    @Override
    public UShortIndexer get(long i2, long j2, int[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            s2[offset + n2] = this.getRaw(this.index(i2, j2) + (long)n2) & 0xFFFF;
        }
        return this;
    }

    @Override
    public int get(long i2, long j2, long k2) {
        return this.getRaw(this.index(i2, j2, k2)) & 0xFFFF;
    }

    @Override
    public int get(long ... indices) {
        return this.getRaw(this.index(indices)) & 0xFFFF;
    }

    @Override
    public UShortIndexer get(long[] indices, int[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            s2[offset + n2] = this.getRaw(this.index(indices) + (long)n2) & 0xFFFF;
        }
        return this;
    }

    public UShortIndexer putRaw(long i2, int s2) {
        RAW.putShort(this.base + UShortRawIndexer.checkIndex(i2, this.size) * 2L, (short)s2);
        return this;
    }

    @Override
    public UShortIndexer put(long i2, int s2) {
        return this.putRaw(this.index(i2), s2);
    }

    @Override
    public UShortIndexer put(long i2, int[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(i2) + (long)n2, s2[offset + n2]);
        }
        return this;
    }

    @Override
    public UShortIndexer put(long i2, long j2, int s2) {
        this.putRaw(this.index(i2, j2), s2);
        return this;
    }

    @Override
    public UShortIndexer put(long i2, long j2, int[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(i2, j2) + (long)n2, s2[offset + n2]);
        }
        return this;
    }

    @Override
    public UShortIndexer put(long i2, long j2, long k2, int s2) {
        this.putRaw(this.index(i2, j2, k2), s2);
        return this;
    }

    @Override
    public UShortIndexer put(long[] indices, int s2) {
        this.putRaw(this.index(indices), s2);
        return this;
    }

    @Override
    public UShortIndexer put(long[] indices, int[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(indices) + (long)n2, s2[offset + n2]);
        }
        return this;
    }

    @Override
    public void release() {
        this.pointer = null;
    }
}

