/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.bytedeco.javacpp.indexer.ByteIndexer;
import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.Raw;

public class ByteArrayIndexer
extends ByteIndexer {
    protected static final Raw RAW = Raw.getInstance();
    protected ByteBuffer buffer;
    protected byte[] array;

    public ByteArrayIndexer(byte[] array) {
        this(array, Index.create((long)array.length));
    }

    public ByteArrayIndexer(byte[] array, long ... sizes) {
        this(array, Index.create(sizes));
    }

    public ByteArrayIndexer(byte[] array, long[] sizes, long[] strides) {
        this(array, Index.create(sizes, strides));
    }

    public ByteArrayIndexer(byte[] array, Index index) {
        super(index);
        this.array = array;
    }

    public byte[] array() {
        return this.array;
    }

    public ByteIndexer reindex(Index index) {
        return new ByteArrayIndexer(this.array, index);
    }

    @Override
    public byte get(long i2) {
        return this.array[(int)this.index(i2)];
    }

    @Override
    public ByteIndexer get(long i2, byte[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            b2[offset + n2] = this.array[(int)this.index(i2) + n2];
        }
        return this;
    }

    @Override
    public byte get(long i2, long j2) {
        return this.array[(int)this.index(i2, j2)];
    }

    @Override
    public ByteIndexer get(long i2, long j2, byte[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            b2[offset + n2] = this.array[(int)this.index(i2, j2) + n2];
        }
        return this;
    }

    @Override
    public byte get(long i2, long j2, long k2) {
        return this.array[(int)this.index(i2, j2, k2)];
    }

    @Override
    public byte get(long ... indices) {
        return this.array[(int)this.index(indices)];
    }

    @Override
    public ByteIndexer get(long[] indices, byte[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            b2[offset + n2] = this.array[(int)this.index(indices) + n2];
        }
        return this;
    }

    @Override
    public ByteIndexer put(long i2, byte b2) {
        this.array[(int)this.index((long)i2)] = b2;
        return this;
    }

    @Override
    public ByteIndexer put(long i2, byte[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long)i2) + n2] = b2[offset + n2];
        }
        return this;
    }

    @Override
    public ByteIndexer put(long i2, long j2, byte b2) {
        this.array[(int)this.index((long)i2, (long)j2)] = b2;
        return this;
    }

    @Override
    public ByteIndexer put(long i2, long j2, byte[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long)i2, (long)j2) + n2] = b2[offset + n2];
        }
        return this;
    }

    @Override
    public ByteIndexer put(long i2, long j2, long k2, byte b2) {
        this.array[(int)this.index((long)i2, (long)j2, (long)k2)] = b2;
        return this;
    }

    @Override
    public ByteIndexer put(long[] indices, byte b2) {
        this.array[(int)this.index((long[])indices)] = b2;
        return this;
    }

    @Override
    public ByteIndexer put(long[] indices, byte[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long[])indices) + n2] = b2[offset + n2];
        }
        return this;
    }

    ByteBuffer getBuffer() {
        if (this.buffer == null) {
            this.buffer = ByteBuffer.wrap(this.array).order(ByteOrder.nativeOrder());
        }
        return this.buffer;
    }

    @Override
    public byte getByte(long i2) {
        return this.array[(int)i2];
    }

    @Override
    public ByteIndexer putByte(long i2, byte b2) {
        this.array[(int)i2] = b2;
        return this;
    }

    @Override
    public short getShort(long i2) {
        if (RAW != null) {
            return RAW.getShort(this.array, ByteArrayIndexer.checkIndex(i2, this.array.length - 1));
        }
        return this.getBuffer().getShort((int)i2);
    }

    @Override
    public ByteIndexer putShort(long i2, short s2) {
        if (RAW != null) {
            RAW.putShort(this.array, ByteArrayIndexer.checkIndex(i2, this.array.length - 1), s2);
        } else {
            this.getBuffer().putShort((int)i2, s2);
        }
        return this;
    }

    @Override
    public int getInt(long i2) {
        if (RAW != null) {
            return RAW.getInt(this.array, ByteArrayIndexer.checkIndex(i2, this.array.length - 3));
        }
        return this.getBuffer().getInt((int)i2);
    }

    @Override
    public ByteIndexer putInt(long i2, int j2) {
        if (RAW != null) {
            RAW.putInt(this.array, ByteArrayIndexer.checkIndex(i2, this.array.length - 3), j2);
        } else {
            this.getBuffer().putInt((int)i2, j2);
        }
        return this;
    }

    @Override
    public long getLong(long i2) {
        if (RAW != null) {
            return RAW.getLong(this.array, ByteArrayIndexer.checkIndex(i2, this.array.length - 7));
        }
        return this.getBuffer().getLong((int)i2);
    }

    @Override
    public ByteIndexer putLong(long i2, long j2) {
        if (RAW != null) {
            RAW.putLong(this.array, ByteArrayIndexer.checkIndex(i2, this.array.length - 7), j2);
        } else {
            this.getBuffer().putLong((int)i2, j2);
        }
        return this;
    }

    @Override
    public float getFloat(long i2) {
        if (RAW != null) {
            return RAW.getFloat(this.array, ByteArrayIndexer.checkIndex(i2, this.array.length - 3));
        }
        return this.getBuffer().getFloat((int)i2);
    }

    @Override
    public ByteIndexer putFloat(long i2, float f2) {
        if (RAW != null) {
            RAW.putFloat(this.array, ByteArrayIndexer.checkIndex(i2, this.array.length - 3), f2);
        } else {
            this.getBuffer().putFloat((int)i2, f2);
        }
        return this;
    }

    @Override
    public double getDouble(long i2) {
        if (RAW != null) {
            return RAW.getDouble(this.array, ByteArrayIndexer.checkIndex(i2, this.array.length - 7));
        }
        return this.getBuffer().getDouble((int)i2);
    }

    @Override
    public ByteIndexer putDouble(long i2, double d2) {
        if (RAW != null) {
            RAW.putDouble(this.array, ByteArrayIndexer.checkIndex(i2, this.array.length - 7), d2);
        } else {
            this.getBuffer().putDouble((int)i2, d2);
        }
        return this;
    }

    @Override
    public char getChar(long i2) {
        if (RAW != null) {
            return RAW.getChar(this.array, ByteArrayIndexer.checkIndex(i2, this.array.length - 1));
        }
        return this.getBuffer().getChar((int)i2);
    }

    @Override
    public ByteIndexer putChar(long i2, char c2) {
        if (RAW != null) {
            RAW.putChar(this.array, ByteArrayIndexer.checkIndex(i2, this.array.length - 1), c2);
        } else {
            this.getBuffer().putChar((int)i2, c2);
        }
        return this;
    }

    @Override
    public void release() {
        this.array = null;
    }
}

