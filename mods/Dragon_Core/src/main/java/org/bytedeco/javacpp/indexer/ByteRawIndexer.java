/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.indexer.ByteIndexer;
import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.Raw;

public class ByteRawIndexer
extends ByteIndexer {
    protected static final Raw RAW = Raw.getInstance();
    protected BytePointer pointer;
    final long base;
    final long size;

    public ByteRawIndexer(BytePointer pointer) {
        this(pointer, Index.create(pointer.limit() - pointer.position()));
    }

    public ByteRawIndexer(BytePointer pointer, long ... sizes) {
        this(pointer, sizes, ByteRawIndexer.strides(sizes));
    }

    public ByteRawIndexer(BytePointer pointer, long[] sizes, long[] strides) {
        this(pointer, Index.create(sizes, strides));
    }

    public ByteRawIndexer(BytePointer pointer, Index index) {
        super(index);
        this.pointer = pointer;
        this.base = pointer.address() + pointer.position();
        this.size = pointer.limit() - pointer.position();
    }

    @Override
    public Pointer pointer() {
        return this.pointer;
    }

    public ByteIndexer reindex(Index index) {
        return new ByteRawIndexer(this.pointer, index);
    }

    public byte getRaw(long i2) {
        return RAW.getByte(this.base + ByteRawIndexer.checkIndex(i2, this.size));
    }

    @Override
    public byte get(long i2) {
        return this.getRaw(this.index(i2));
    }

    @Override
    public ByteIndexer get(long i2, byte[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            b2[offset + n2] = this.getRaw(this.index(i2) + (long)n2);
        }
        return this;
    }

    @Override
    public byte get(long i2, long j2) {
        return this.getRaw(this.index(i2, j2));
    }

    @Override
    public ByteIndexer get(long i2, long j2, byte[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            b2[offset + n2] = this.getRaw(this.index(i2, j2) + (long)n2);
        }
        return this;
    }

    @Override
    public byte get(long i2, long j2, long k2) {
        return this.getRaw(this.index(i2, j2, k2));
    }

    @Override
    public byte get(long ... indices) {
        return this.getRaw(this.index(indices));
    }

    @Override
    public ByteIndexer get(long[] indices, byte[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            b2[offset + n2] = this.getRaw(this.index(indices) + (long)n2);
        }
        return this;
    }

    public ByteIndexer putRaw(long i2, byte b2) {
        RAW.putByte(this.base + ByteRawIndexer.checkIndex(i2, this.size), b2);
        return this;
    }

    @Override
    public ByteIndexer put(long i2, byte b2) {
        return this.putRaw(this.index(i2), b2);
    }

    @Override
    public ByteIndexer put(long i2, byte[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(i2) + (long)n2, b2[offset + n2]);
        }
        return this;
    }

    @Override
    public ByteIndexer put(long i2, long j2, byte b2) {
        this.putRaw(this.index(i2, j2), b2);
        return this;
    }

    @Override
    public ByteIndexer put(long i2, long j2, byte[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(i2, j2) + (long)n2, b2[offset + n2]);
        }
        return this;
    }

    @Override
    public ByteIndexer put(long i2, long j2, long k2, byte b2) {
        this.putRaw(this.index(i2, j2, k2), b2);
        return this;
    }

    @Override
    public ByteIndexer put(long[] indices, byte b2) {
        this.putRaw(this.index(indices), b2);
        return this;
    }

    @Override
    public ByteIndexer put(long[] indices, byte[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(indices) + (long)n2, b2[offset + n2]);
        }
        return this;
    }

    @Override
    public byte getByte(long i2) {
        return RAW.getByte(this.base + ByteRawIndexer.checkIndex(i2, this.size - 1L));
    }

    @Override
    public ByteIndexer putByte(long i2, byte b2) {
        RAW.putByte(this.base + ByteRawIndexer.checkIndex(i2, this.size - 1L), b2);
        return this;
    }

    @Override
    public short getShort(long i2) {
        return RAW.getShort(this.base + ByteRawIndexer.checkIndex(i2, this.size - 1L));
    }

    @Override
    public ByteIndexer putShort(long i2, short s2) {
        RAW.putShort(this.base + ByteRawIndexer.checkIndex(i2, this.size - 1L), s2);
        return this;
    }

    @Override
    public int getInt(long i2) {
        return RAW.getInt(this.base + ByteRawIndexer.checkIndex(i2, this.size - 3L));
    }

    @Override
    public ByteIndexer putInt(long i2, int j2) {
        RAW.putInt(this.base + ByteRawIndexer.checkIndex(i2, this.size - 3L), j2);
        return this;
    }

    @Override
    public long getLong(long i2) {
        return RAW.getLong(this.base + ByteRawIndexer.checkIndex(i2, this.size - 7L));
    }

    @Override
    public ByteIndexer putLong(long i2, long j2) {
        RAW.putLong(this.base + ByteRawIndexer.checkIndex(i2, this.size - 7L), j2);
        return this;
    }

    @Override
    public float getFloat(long i2) {
        return RAW.getFloat(this.base + ByteRawIndexer.checkIndex(i2, this.size - 3L));
    }

    @Override
    public ByteIndexer putFloat(long i2, float f2) {
        RAW.putFloat(this.base + ByteRawIndexer.checkIndex(i2, this.size - 3L), f2);
        return this;
    }

    @Override
    public double getDouble(long i2) {
        return RAW.getDouble(this.base + ByteRawIndexer.checkIndex(i2, this.size - 7L));
    }

    @Override
    public ByteIndexer putDouble(long i2, double d2) {
        RAW.putDouble(this.base + ByteRawIndexer.checkIndex(i2, this.size - 7L), d2);
        return this;
    }

    @Override
    public char getChar(long i2) {
        return RAW.getChar(this.base + ByteRawIndexer.checkIndex(i2, this.size - 1L));
    }

    @Override
    public ByteIndexer putChar(long i2, char c2) {
        RAW.putChar(this.base + ByteRawIndexer.checkIndex(i2, this.size - 1L), c2);
        return this;
    }

    @Override
    public void release() {
        this.pointer = null;
    }
}

