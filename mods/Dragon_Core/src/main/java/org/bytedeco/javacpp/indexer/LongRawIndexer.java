/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.LongIndexer;
import org.bytedeco.javacpp.indexer.Raw;

public class LongRawIndexer
extends LongIndexer {
    protected static final Raw RAW = Raw.getInstance();
    protected LongPointer pointer;
    final long base;
    final long size;

    public LongRawIndexer(LongPointer pointer) {
        this(pointer, Index.create(pointer.limit() - pointer.position()));
    }

    public LongRawIndexer(LongPointer pointer, long ... sizes) {
        this(pointer, Index.create(sizes));
    }

    public LongRawIndexer(LongPointer pointer, long[] sizes, long[] strides) {
        this(pointer, Index.create(sizes, strides));
    }

    public LongRawIndexer(LongPointer pointer, Index index) {
        super(index);
        this.pointer = pointer;
        this.base = pointer.address() + pointer.position() * 8L;
        this.size = pointer.limit() - pointer.position();
    }

    @Override
    public Pointer pointer() {
        return this.pointer;
    }

    public LongIndexer reindex(Index index) {
        return new LongRawIndexer(this.pointer, index);
    }

    public long getRaw(long i2) {
        return RAW.getLong(this.base + LongRawIndexer.checkIndex(i2, this.size) * 8L);
    }

    @Override
    public long get(long i2) {
        return this.getRaw(this.index(i2));
    }

    @Override
    public LongIndexer get(long i2, long[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            l2[offset + n2] = this.getRaw(this.index(i2) + (long)n2);
        }
        return this;
    }

    @Override
    public long get(long i2, long j2) {
        return this.getRaw(this.index(i2, j2));
    }

    @Override
    public LongIndexer get(long i2, long j2, long[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            l2[offset + n2] = this.getRaw(this.index(i2, j2) + (long)n2);
        }
        return this;
    }

    @Override
    public long get(long i2, long j2, long k2) {
        return this.getRaw(this.index(i2, j2, k2));
    }

    @Override
    public long get(long ... indices) {
        return this.getRaw(this.index(indices));
    }

    @Override
    public LongIndexer get(long[] indices, long[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            l2[offset + n2] = this.getRaw(this.index(indices) + (long)n2);
        }
        return this;
    }

    public LongIndexer putRaw(long i2, long l2) {
        RAW.putLong(this.base + LongRawIndexer.checkIndex(i2, this.size) * 8L, l2);
        return this;
    }

    @Override
    public LongIndexer put(long i2, long l2) {
        return this.putRaw(this.index(i2), l2);
    }

    @Override
    public LongIndexer put(long i2, long[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(i2) + (long)n2, l2[offset + n2]);
        }
        return this;
    }

    @Override
    public LongIndexer put(long i2, long j2, long l2) {
        this.putRaw(this.index(i2, j2), l2);
        return this;
    }

    @Override
    public LongIndexer put(long i2, long j2, long[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(i2, j2) + (long)n2, l2[offset + n2]);
        }
        return this;
    }

    @Override
    public LongIndexer put(long i2, long j2, long k2, long l2) {
        this.putRaw(this.index(i2, j2, k2), l2);
        return this;
    }

    @Override
    public LongIndexer put(long[] indices, long l2) {
        this.putRaw(this.index(indices), l2);
        return this;
    }

    @Override
    public LongIndexer put(long[] indices, long[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(indices) + (long)n2, l2[offset + n2]);
        }
        return this;
    }

    @Override
    public void release() {
        this.pointer = null;
    }
}

