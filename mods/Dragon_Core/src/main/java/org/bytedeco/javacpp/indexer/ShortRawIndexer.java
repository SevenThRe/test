/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.ShortPointer;
import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.Raw;
import org.bytedeco.javacpp.indexer.ShortIndexer;

public class ShortRawIndexer
extends ShortIndexer {
    protected static final Raw RAW = Raw.getInstance();
    protected ShortPointer pointer;
    final long base;
    final long size;

    public ShortRawIndexer(ShortPointer pointer) {
        this(pointer, Index.create(pointer.limit() - pointer.position()));
    }

    public ShortRawIndexer(ShortPointer pointer, long ... sizes) {
        this(pointer, Index.create(sizes));
    }

    public ShortRawIndexer(ShortPointer pointer, long[] sizes, long[] strides) {
        this(pointer, Index.create(sizes, strides));
    }

    public ShortRawIndexer(ShortPointer pointer, Index index) {
        super(index);
        this.pointer = pointer;
        this.base = pointer.address() + pointer.position() * 2L;
        this.size = pointer.limit() - pointer.position();
    }

    @Override
    public Pointer pointer() {
        return this.pointer;
    }

    public ShortIndexer reindex(Index index) {
        return new ShortRawIndexer(this.pointer, index);
    }

    public short getRaw(long i2) {
        return RAW.getShort(this.base + ShortRawIndexer.checkIndex(i2, this.size) * 2L);
    }

    @Override
    public short get(long i2) {
        return this.getRaw(this.index(i2));
    }

    @Override
    public ShortIndexer get(long i2, short[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            s2[offset + n2] = this.getRaw(this.index(i2) + (long)n2);
        }
        return this;
    }

    @Override
    public short get(long i2, long j2) {
        return this.getRaw(this.index(i2, j2));
    }

    @Override
    public ShortIndexer get(long i2, long j2, short[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            s2[offset + n2] = this.getRaw(this.index(i2, j2) + (long)n2);
        }
        return this;
    }

    @Override
    public short get(long i2, long j2, long k2) {
        return this.getRaw(this.index(i2, j2, k2));
    }

    @Override
    public short get(long ... indices) {
        return this.getRaw(this.index(indices));
    }

    @Override
    public ShortIndexer get(long[] indices, short[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            s2[offset + n2] = this.getRaw(this.index(indices) + (long)n2);
        }
        return this;
    }

    public ShortIndexer putRaw(long i2, short s2) {
        RAW.putShort(this.base + ShortRawIndexer.checkIndex(i2, this.size) * 2L, s2);
        return this;
    }

    @Override
    public ShortIndexer put(long i2, short s2) {
        return this.putRaw(this.index(i2), s2);
    }

    @Override
    public ShortIndexer put(long i2, short[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(i2) + (long)n2, s2[offset + n2]);
        }
        return this;
    }

    @Override
    public ShortIndexer put(long i2, long j2, short s2) {
        this.putRaw(this.index(i2, j2), s2);
        return this;
    }

    @Override
    public ShortIndexer put(long i2, long j2, short[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(i2, j2) + (long)n2, s2[offset + n2]);
        }
        return this;
    }

    @Override
    public ShortIndexer put(long i2, long j2, long k2, short s2) {
        this.putRaw(this.index(i2, j2, k2), s2);
        return this;
    }

    @Override
    public ShortIndexer put(long[] indices, short s2) {
        this.putRaw(this.index(indices), s2);
        return this;
    }

    @Override
    public ShortIndexer put(long[] indices, short[] s2, int offset, int length) {
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

