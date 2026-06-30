/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.Raw;
import org.bytedeco.javacpp.indexer.UByteIndexer;

public class UByteRawIndexer
extends UByteIndexer {
    protected static final Raw RAW = Raw.getInstance();
    protected BytePointer pointer;
    final long base;
    final long size;

    public UByteRawIndexer(BytePointer pointer) {
        this(pointer, Index.create(pointer.limit() - pointer.position()));
    }

    public UByteRawIndexer(BytePointer pointer, long ... sizes) {
        this(pointer, sizes, UByteRawIndexer.strides(sizes));
    }

    public UByteRawIndexer(BytePointer pointer, long[] sizes, long[] strides) {
        this(pointer, Index.create(sizes, strides));
    }

    public UByteRawIndexer(BytePointer pointer, Index index) {
        super(index);
        this.pointer = pointer;
        this.base = pointer.address() + pointer.position();
        this.size = pointer.limit() - pointer.position();
    }

    @Override
    public Pointer pointer() {
        return this.pointer;
    }

    public UByteIndexer reindex(Index index) {
        return new UByteRawIndexer(this.pointer, index);
    }

    public int getRaw(long i2) {
        return RAW.getByte(this.base + UByteRawIndexer.checkIndex(i2, this.size)) & 0xFF;
    }

    @Override
    public int get(long i2) {
        return this.getRaw(this.index(i2));
    }

    @Override
    public UByteIndexer get(long i2, int[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            b2[offset + n2] = this.getRaw(this.index(i2) + (long)n2) & 0xFF;
        }
        return this;
    }

    @Override
    public int get(long i2, long j2) {
        return this.getRaw(this.index(i2, j2)) & 0xFF;
    }

    @Override
    public UByteIndexer get(long i2, long j2, int[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            b2[offset + n2] = this.getRaw(this.index(i2, j2) + (long)n2) & 0xFF;
        }
        return this;
    }

    @Override
    public int get(long i2, long j2, long k2) {
        return this.getRaw(this.index(i2, j2, k2)) & 0xFF;
    }

    @Override
    public int get(long ... indices) {
        return this.getRaw(this.index(indices)) & 0xFF;
    }

    @Override
    public UByteIndexer get(long[] indices, int[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            b2[offset + n2] = this.getRaw(this.index(indices) + (long)n2) & 0xFF;
        }
        return this;
    }

    public UByteIndexer putRaw(long i2, int b2) {
        RAW.putByte(this.base + UByteRawIndexer.checkIndex(i2, this.size), (byte)b2);
        return this;
    }

    @Override
    public UByteIndexer put(long i2, int b2) {
        this.putRaw(this.index(i2), b2);
        return this;
    }

    @Override
    public UByteIndexer put(long i2, int[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(i2) + (long)n2, b2[offset + n2]);
        }
        return this;
    }

    @Override
    public UByteIndexer put(long i2, long j2, int b2) {
        this.putRaw(this.index(i2, j2), b2);
        return this;
    }

    @Override
    public UByteIndexer put(long i2, long j2, int[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(i2, j2) + (long)n2, b2[offset + n2]);
        }
        return this;
    }

    @Override
    public UByteIndexer put(long i2, long j2, long k2, int b2) {
        this.putRaw(this.index(i2, j2, k2), b2);
        return this;
    }

    @Override
    public UByteIndexer put(long[] indices, int b2) {
        this.putRaw(this.index(indices), b2);
        return this;
    }

    @Override
    public UByteIndexer put(long[] indices, int[] b2, int offset, int length) {
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

