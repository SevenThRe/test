/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.BooleanPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.indexer.BooleanIndexer;
import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.Raw;

public class BooleanRawIndexer
extends BooleanIndexer {
    protected static final Raw RAW = Raw.getInstance();
    protected BooleanPointer pointer;
    final long base;
    final long size;

    public BooleanRawIndexer(BooleanPointer pointer) {
        this(pointer, Index.create(pointer.limit() - pointer.position()));
    }

    public BooleanRawIndexer(BooleanPointer pointer, long ... sizes) {
        this(pointer, sizes, BooleanRawIndexer.strides(sizes));
    }

    public BooleanRawIndexer(BooleanPointer pointer, long[] sizes, long[] strides) {
        this(pointer, Index.create(sizes, strides));
    }

    public BooleanRawIndexer(BooleanPointer pointer, Index index) {
        super(index);
        this.pointer = pointer;
        this.base = pointer.address() + pointer.position() * 1L;
        this.size = pointer.limit() - pointer.position();
    }

    @Override
    public Pointer pointer() {
        return this.pointer;
    }

    public BooleanIndexer reindex(Index index) {
        return new BooleanRawIndexer(this.pointer, index);
    }

    public boolean getRaw(long i2) {
        return RAW.getBoolean(this.base + BooleanRawIndexer.checkIndex(i2, this.size) * 1L);
    }

    @Override
    public boolean get(long i2) {
        return this.getRaw(this.index(i2));
    }

    @Override
    public BooleanIndexer get(long i2, boolean[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            b2[offset + n2] = this.getRaw(this.index(i2) + (long)n2);
        }
        return this;
    }

    @Override
    public boolean get(long i2, long j2) {
        return this.getRaw(this.index(i2, j2));
    }

    @Override
    public BooleanIndexer get(long i2, long j2, boolean[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            b2[offset + n2] = this.getRaw(this.index(i2, j2) + (long)n2);
        }
        return this;
    }

    @Override
    public boolean get(long i2, long j2, long k2) {
        return this.getRaw(this.index(i2, j2, k2));
    }

    @Override
    public boolean get(long ... indices) {
        return this.getRaw(this.index(indices));
    }

    @Override
    public BooleanIndexer get(long[] indices, boolean[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            b2[offset + n2] = this.getRaw(this.index(indices) + (long)n2);
        }
        return this;
    }

    public BooleanIndexer putRaw(long i2, boolean b2) {
        RAW.putBoolean(this.base + BooleanRawIndexer.checkIndex(i2, this.size) * 1L, b2);
        return this;
    }

    @Override
    public BooleanIndexer put(long i2, boolean b2) {
        return this.putRaw(this.index(i2), b2);
    }

    @Override
    public BooleanIndexer put(long i2, boolean[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(i2) + (long)n2, b2[offset + n2]);
        }
        return this;
    }

    @Override
    public BooleanIndexer put(long i2, long j2, boolean b2) {
        this.putRaw(this.index(i2, j2), b2);
        return this;
    }

    @Override
    public BooleanIndexer put(long i2, long j2, boolean[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(i2, j2) + (long)n2, b2[offset + n2]);
        }
        return this;
    }

    @Override
    public BooleanIndexer put(long i2, long j2, long k2, boolean b2) {
        this.putRaw(this.index(i2, j2, k2), b2);
        return this;
    }

    @Override
    public BooleanIndexer put(long[] indices, boolean b2) {
        this.putRaw(this.index(indices), b2);
        return this;
    }

    @Override
    public BooleanIndexer put(long[] indices, boolean[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(indices) + (long)n2, b2[offset + n2]);
        }
        return this;
    }

    @Override
    public void release() {
        this.pointer = null;
    }
}

