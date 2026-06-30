/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.indexer.DoubleIndexer;
import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.Raw;

public class DoubleRawIndexer
extends DoubleIndexer {
    protected static final Raw RAW = Raw.getInstance();
    protected DoublePointer pointer;
    final long base;
    final long size;

    public DoubleRawIndexer(DoublePointer pointer) {
        this(pointer, Index.create(pointer.limit() - pointer.position()));
    }

    public DoubleRawIndexer(DoublePointer pointer, long ... sizes) {
        this(pointer, Index.create(sizes));
    }

    public DoubleRawIndexer(DoublePointer pointer, long[] sizes, long[] strides) {
        this(pointer, Index.create(sizes, strides));
    }

    public DoubleRawIndexer(DoublePointer pointer, Index index) {
        super(index);
        this.pointer = pointer;
        this.base = pointer.address() + pointer.position() * 8L;
        this.size = pointer.limit() - pointer.position();
    }

    @Override
    public Pointer pointer() {
        return this.pointer;
    }

    public DoubleIndexer reindex(Index index) {
        return new DoubleRawIndexer(this.pointer, index);
    }

    public double getRaw(long i2) {
        return RAW.getDouble(this.base + DoubleRawIndexer.checkIndex(i2, this.size) * 8L);
    }

    @Override
    public double get(long i2) {
        return this.getRaw(this.index(i2));
    }

    @Override
    public DoubleIndexer get(long i2, double[] d2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            d2[offset + n2] = this.getRaw(this.index(i2) + (long)n2);
        }
        return this;
    }

    @Override
    public double get(long i2, long j2) {
        return this.getRaw(this.index(i2, j2));
    }

    @Override
    public DoubleIndexer get(long i2, long j2, double[] d2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            d2[offset + n2] = this.getRaw(this.index(i2, j2) + (long)n2);
        }
        return this;
    }

    @Override
    public double get(long i2, long j2, long k2) {
        return this.getRaw(this.index(i2, j2, k2));
    }

    @Override
    public double get(long ... indices) {
        return this.getRaw(this.index(indices));
    }

    @Override
    public DoubleIndexer get(long[] indices, double[] d2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            d2[offset + n2] = this.getRaw(this.index(indices) + (long)n2);
        }
        return this;
    }

    public DoubleIndexer putRaw(long i2, double d2) {
        RAW.putDouble(this.base + DoubleRawIndexer.checkIndex(i2, this.size) * 8L, d2);
        return this;
    }

    @Override
    public DoubleIndexer put(long i2, double d2) {
        return this.putRaw(this.index(i2), d2);
    }

    @Override
    public DoubleIndexer put(long i2, double[] d2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(i2) + (long)n2, d2[offset + n2]);
        }
        return this;
    }

    @Override
    public DoubleIndexer put(long i2, long j2, double d2) {
        this.putRaw(this.index(i2, j2), d2);
        return this;
    }

    @Override
    public DoubleIndexer put(long i2, long j2, double[] d2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(i2, j2) + (long)n2, d2[offset + n2]);
        }
        return this;
    }

    @Override
    public DoubleIndexer put(long i2, long j2, long k2, double d2) {
        this.putRaw(this.index(i2, j2, k2), d2);
        return this;
    }

    @Override
    public DoubleIndexer put(long[] indices, double d2) {
        this.putRaw(this.index(indices), d2);
        return this;
    }

    @Override
    public DoubleIndexer put(long[] indices, double[] d2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(indices) + (long)n2, d2[offset + n2]);
        }
        return this;
    }

    @Override
    public void release() {
        this.pointer = null;
    }
}

