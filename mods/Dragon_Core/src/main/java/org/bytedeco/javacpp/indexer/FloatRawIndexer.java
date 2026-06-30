/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.indexer.FloatIndexer;
import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.Raw;

public class FloatRawIndexer
extends FloatIndexer {
    protected static final Raw RAW = Raw.getInstance();
    protected FloatPointer pointer;
    final long base;
    final long size;

    public FloatRawIndexer(FloatPointer pointer) {
        this(pointer, Index.create(pointer.limit() - pointer.position()));
    }

    public FloatRawIndexer(FloatPointer pointer, long ... sizes) {
        this(pointer, Index.create(sizes));
    }

    public FloatRawIndexer(FloatPointer pointer, long[] sizes, long[] strides) {
        this(pointer, Index.create(sizes, strides));
    }

    public FloatRawIndexer(FloatPointer pointer, Index index) {
        super(index);
        this.pointer = pointer;
        this.base = pointer.address() + pointer.position() * 4L;
        this.size = pointer.limit() - pointer.position();
    }

    @Override
    public Pointer pointer() {
        return this.pointer;
    }

    public FloatIndexer reindex(Index index) {
        return new FloatRawIndexer(this.pointer, index);
    }

    public float getRaw(long i2) {
        return RAW.getFloat(this.base + FloatRawIndexer.checkIndex(i2, this.size) * 4L);
    }

    @Override
    public float get(long i2) {
        return this.getRaw(this.index(i2));
    }

    @Override
    public FloatIndexer get(long i2, float[] f2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            f2[offset + n2] = this.getRaw(this.index(i2) + (long)n2);
        }
        return this;
    }

    @Override
    public float get(long i2, long j2) {
        return this.getRaw(this.index(i2, j2));
    }

    @Override
    public FloatIndexer get(long i2, long j2, float[] f2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            f2[offset + n2] = this.getRaw(this.index(i2, j2) + (long)n2);
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
    public FloatIndexer get(long[] indices, float[] f2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            f2[offset + n2] = this.getRaw(this.index(indices) + (long)n2);
        }
        return this;
    }

    public FloatIndexer putRaw(long i2, float f2) {
        RAW.putFloat(this.base + FloatRawIndexer.checkIndex(i2, this.size) * 4L, f2);
        return this;
    }

    @Override
    public FloatIndexer put(long i2, float f2) {
        return this.putRaw(this.index(i2), f2);
    }

    @Override
    public FloatIndexer put(long i2, float[] f2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(i2) + (long)n2, f2[offset + n2]);
        }
        return this;
    }

    @Override
    public FloatIndexer put(long i2, long j2, float f2) {
        this.putRaw(this.index(i2, j2), f2);
        return this;
    }

    @Override
    public FloatIndexer put(long i2, long j2, float[] f2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(i2, j2) + (long)n2, f2[offset + n2]);
        }
        return this;
    }

    @Override
    public FloatIndexer put(long i2, long j2, long k2, float f2) {
        this.putRaw(this.index(i2, j2, k2), f2);
        return this;
    }

    @Override
    public FloatIndexer put(long[] indices, float f2) {
        this.putRaw(this.index(indices), f2);
        return this;
    }

    @Override
    public FloatIndexer put(long[] indices, float[] f2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(indices) + (long)n2, f2[offset + n2]);
        }
        return this;
    }

    @Override
    public void release() {
        this.pointer = null;
    }
}

