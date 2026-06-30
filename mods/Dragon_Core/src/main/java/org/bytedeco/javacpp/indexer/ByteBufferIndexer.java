/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import org.bytedeco.javacpp.indexer.ByteIndexer;
import org.bytedeco.javacpp.indexer.Index;

public class ByteBufferIndexer
extends ByteIndexer {
    protected ByteBuffer buffer;

    public ByteBufferIndexer(ByteBuffer buffer) {
        this(buffer, Index.create((long)buffer.limit()));
    }

    public ByteBufferIndexer(ByteBuffer buffer, long ... sizes) {
        this(buffer, Index.create(sizes));
    }

    public ByteBufferIndexer(ByteBuffer buffer, long[] sizes, long[] strides) {
        this(buffer, Index.create(sizes, strides));
    }

    public ByteBufferIndexer(ByteBuffer buffer, Index index) {
        super(index);
        this.buffer = buffer;
    }

    @Override
    public Buffer buffer() {
        return this.buffer;
    }

    public ByteIndexer reindex(Index index) {
        return new ByteBufferIndexer(this.buffer, index);
    }

    @Override
    public byte get(long i2) {
        return this.buffer.get((int)this.index(i2));
    }

    @Override
    public ByteIndexer get(long i2, byte[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            b2[offset + n2] = this.buffer.get((int)this.index(i2) + n2);
        }
        return this;
    }

    @Override
    public byte get(long i2, long j2) {
        return this.buffer.get((int)this.index(i2, j2));
    }

    @Override
    public ByteIndexer get(long i2, long j2, byte[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            b2[offset + n2] = this.buffer.get((int)this.index(i2, j2) + n2);
        }
        return this;
    }

    @Override
    public byte get(long i2, long j2, long k2) {
        return this.buffer.get((int)this.index(i2, j2, k2));
    }

    @Override
    public byte get(long ... indices) {
        return this.buffer.get((int)this.index(indices));
    }

    @Override
    public ByteIndexer get(long[] indices, byte[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            b2[offset + n2] = this.buffer.get((int)this.index(indices) + n2);
        }
        return this;
    }

    @Override
    public ByteIndexer put(long i2, byte b2) {
        this.buffer.put((int)this.index(i2), b2);
        return this;
    }

    @Override
    public ByteIndexer put(long i2, byte[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(i2) + n2, b2[offset + n2]);
        }
        return this;
    }

    @Override
    public ByteIndexer put(long i2, long j2, byte b2) {
        this.buffer.put((int)this.index(i2, j2), b2);
        return this;
    }

    @Override
    public ByteIndexer put(long i2, long j2, byte[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(i2, j2) + n2, b2[offset + n2]);
        }
        return this;
    }

    @Override
    public ByteIndexer put(long i2, long j2, long k2, byte b2) {
        this.buffer.put((int)this.index(i2, j2, k2), b2);
        return this;
    }

    @Override
    public ByteIndexer put(long[] indices, byte b2) {
        this.buffer.put((int)this.index(indices), b2);
        return this;
    }

    @Override
    public ByteIndexer put(long[] indices, byte[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(indices) + n2, b2[offset + n2]);
        }
        return this;
    }

    @Override
    public byte getByte(long i2) {
        return this.buffer.get((int)i2);
    }

    @Override
    public ByteIndexer putByte(long i2, byte b2) {
        this.buffer.put((int)i2, b2);
        return this;
    }

    @Override
    public short getShort(long i2) {
        return this.buffer.getShort((int)i2);
    }

    @Override
    public ByteIndexer putShort(long i2, short s2) {
        this.buffer.putShort((int)i2, s2);
        return this;
    }

    @Override
    public int getInt(long i2) {
        return this.buffer.getInt((int)i2);
    }

    @Override
    public ByteIndexer putInt(long i2, int j2) {
        this.buffer.putInt((int)i2, j2);
        return this;
    }

    @Override
    public long getLong(long i2) {
        return this.buffer.getLong((int)i2);
    }

    @Override
    public ByteIndexer putLong(long i2, long j2) {
        this.buffer.putLong((int)i2, j2);
        return this;
    }

    @Override
    public float getFloat(long i2) {
        return this.buffer.getFloat((int)i2);
    }

    @Override
    public ByteIndexer putFloat(long i2, float f2) {
        this.buffer.putFloat((int)i2, f2);
        return this;
    }

    @Override
    public double getDouble(long i2) {
        return this.buffer.getDouble((int)i2);
    }

    @Override
    public ByteIndexer putDouble(long i2, double d2) {
        this.buffer.putDouble((int)i2, d2);
        return this;
    }

    @Override
    public char getChar(long i2) {
        return this.buffer.getChar((int)i2);
    }

    @Override
    public ByteIndexer putChar(long i2, char c2) {
        this.buffer.putChar((int)i2, c2);
        return this;
    }

    @Override
    public void release() {
        this.buffer = null;
    }
}

