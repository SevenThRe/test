/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.IntIndexer;
import org.bytedeco.javacpp.indexer.Raw;

public class IntRawIndexer
extends IntIndexer {
    protected static final Raw RAW = Raw.getInstance();
    protected IntPointer pointer;
    final long base;
    final long size;

    public IntRawIndexer(IntPointer pointer) {
        this(pointer, Index.create(pointer.limit() - pointer.position()));
    }

    public IntRawIndexer(IntPointer pointer, long ... sizes) {
        this(pointer, Index.create(sizes));
    }

    public IntRawIndexer(IntPointer pointer, long[] sizes, long[] strides) {
        this(pointer, Index.create(sizes, strides));
    }

    public IntRawIndexer(IntPointer pointer, Index index) {
        super(index);
        this.pointer = pointer;
        this.base = pointer.address() + pointer.position() * 4L;
        this.size = pointer.limit() - pointer.position();
    }

    @Override
    public Pointer pointer() {
        return this.pointer;
    }

    public IntIndexer reindex(Index index) {
        return new IntRawIndexer(this.pointer, index);
    }

    public int getRaw(long i2) {
        return RAW.getInt(this.base + IntRawIndexer.checkIndex(i2, this.size) * 4L);
    }

    @Override
    public int get(long i2) {
        return this.getRaw(this.index(i2));
    }

    @Override
    public IntIndexer get(long i2, int[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            m2[offset + n2] = this.getRaw(this.index(i2) + (long)n2);
        }
        return this;
    }

    @Override
    public int get(long i2, long j2) {
        return this.getRaw(this.index(i2, j2));
    }

    @Override
    public IntIndexer get(long i2, long j2, int[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            m2[offset + n2] = this.getRaw(this.index(i2, j2) + (long)n2);
        }
        return this;
    }

    @Override
    public int get(long i2, long j2, long k2) {
        return this.getRaw(this.index(i2, j2, k2));
    }

    @Override
    public int get(long ... indices) {
        return this.getRaw(this.index(indices));
    }

    @Override
    public IntIndexer get(long[] indices, int[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            m2[offset + n2] = this.getRaw(this.index(indices) + (long)n2);
        }
        return this;
    }

    public IntIndexer putRaw(long i2, int n2) {
        RAW.putInt(this.base + IntRawIndexer.checkIndex(i2, this.size) * 4L, n2);
        return this;
    }

    @Override
    public IntIndexer put(long i2, int n2) {
        return this.putRaw(this.index(i2), n2);
    }

    @Override
    public IntIndexer put(long i2, int[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(i2) + (long)n2, m2[offset + n2]);
        }
        return this;
    }

    @Override
    public IntIndexer put(long i2, long j2, int n2) {
        this.putRaw(this.index(i2, j2), n2);
        return this;
    }

    @Override
    public IntIndexer put(long i2, long j2, int[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(i2, j2) + (long)n2, m2[offset + n2]);
        }
        return this;
    }

    @Override
    public IntIndexer put(long i2, long j2, long k2, int n2) {
        this.putRaw(this.index(i2, j2, k2), n2);
        return this;
    }

    @Override
    public IntIndexer put(long[] indices, int n2) {
        this.putRaw(this.index(indices), n2);
        return this;
    }

    @Override
    public IntIndexer put(long[] indices, int[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(indices) + (long)n2, m2[offset + n2]);
        }
        return this;
    }

    @Override
    public void release() {
        this.pointer = null;
    }
}

