/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.ShortPointer;
import org.bytedeco.javacpp.indexer.Bfloat16Indexer;
import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.Raw;

public class Bfloat16RawIndexer
extends Bfloat16Indexer {
    protected static final Raw RAW = Raw.getInstance();
    protected ShortPointer pointer;
    final long base;
    final long size;

    public Bfloat16RawIndexer(ShortPointer pointer) {
        this(pointer, Index.create(pointer.limit() - pointer.position()));
    }

    public Bfloat16RawIndexer(ShortPointer pointer, long ... sizes) {
        this(pointer, Index.create(sizes));
    }

    public Bfloat16RawIndexer(ShortPointer pointer, long[] sizes, long[] strides) {
        this(pointer, Index.create(sizes, strides));
    }

    public Bfloat16RawIndexer(ShortPointer pointer, Index index) {
        super(index);
        this.pointer = pointer;
        this.base = pointer.address() + pointer.position() * 2L;
        this.size = pointer.limit() - pointer.position();
    }

    @Override
    public Pointer pointer() {
        return this.pointer;
    }

    public Bfloat16Indexer reindex(Index index) {
        return new Bfloat16RawIndexer(this.pointer, index);
    }

    public float getRaw(long i2) {
        return Bfloat16RawIndexer.toFloat(RAW.getShort(this.base + Bfloat16RawIndexer.checkIndex(i2, this.size) * 2L));
    }

    @Override
    public float get(long i2) {
        return this.getRaw(this.index(i2));
    }

    @Override
    public Bfloat16Indexer get(long i2, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            h2[offset + n2] = this.getRaw(this.index(i2) + (long)n2);
        }
        return this;
    }

    @Override
    public float get(long i2, long j2) {
        return this.getRaw(this.index(i2, j2));
    }

    @Override
    public Bfloat16Indexer get(long i2, long j2, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            h2[offset + n2] = this.getRaw(this.index(i2, j2) + (long)n2);
        }
        return this;
    }

    @Override
    public float get(long i2, long j2, long k2) {
        return this.getRaw(this.index(i2, j2, k2));
    }

    @Override
    public float get(long ... indices) {
        return this.getRaw(this.index(indices));
    }

    @Override
    public Bfloat16Indexer get(long[] indices, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            h2[offset + n2] = this.getRaw(this.index(indices) + (long)n2);
        }
        return this;
    }

    public Bfloat16Indexer putRaw(long i2, float h2) {
        RAW.putShort(this.base + Bfloat16RawIndexer.checkIndex(i2, this.size) * 2L, (short)Bfloat16RawIndexer.fromFloat(h2));
        return this;
    }

    @Override
    public Bfloat16Indexer put(long i2, float h2) {
        return this.putRaw(this.index(i2), h2);
    }

    @Override
    public Bfloat16Indexer put(long i2, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(i2) + (long)n2, h2[offset + n2]);
        }
        return this;
    }

    @Override
    public Bfloat16Indexer put(long i2, long j2, float h2) {
        this.putRaw(this.index(i2, j2), h2);
        return this;
    }

    @Override
    public Bfloat16Indexer put(long i2, long j2, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(i2, j2) + (long)n2, h2[offset + n2]);
        }
        return this;
    }

    @Override
    public Bfloat16Indexer put(long i2, long j2, long k2, float h2) {
        this.putRaw(this.index(i2, j2, k2), h2);
        return this;
    }

    @Override
    public Bfloat16Indexer put(long[] indices, float h2) {
        this.putRaw(this.index(indices), h2);
        return this;
    }

    @Override
    public Bfloat16Indexer put(long[] indices, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(indices) + (long)n2, h2[offset + n2]);
        }
        return this;
    }

    @Override
    public void release() {
        this.pointer = null;
    }
}

