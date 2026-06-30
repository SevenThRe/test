/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.indexer.Bfloat16Indexer;
import org.bytedeco.javacpp.indexer.ByteArrayIndexer;
import org.bytedeco.javacpp.indexer.ByteBufferIndexer;
import org.bytedeco.javacpp.indexer.ByteRawIndexer;
import org.bytedeco.javacpp.indexer.HalfIndexer;
import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.Indexer;
import org.bytedeco.javacpp.indexer.Raw;
import org.bytedeco.javacpp.indexer.ULongIndexer;

public abstract class ByteIndexer
extends Indexer {
    public static final int VALUE_BYTES = 1;

    protected ByteIndexer(Index index) {
        super(index);
    }

    protected ByteIndexer(long[] sizes, long[] strides) {
        super(sizes, strides);
    }

    public static ByteIndexer create(byte[] array) {
        return new ByteArrayIndexer(array);
    }

    public static ByteIndexer create(ByteBuffer buffer) {
        return new ByteBufferIndexer(buffer);
    }

    public static ByteIndexer create(BytePointer pointer) {
        return new ByteRawIndexer(pointer);
    }

    public static ByteIndexer create(byte[] array, Index index) {
        return new ByteArrayIndexer(array, index);
    }

    public static ByteIndexer create(ByteBuffer buffer, Index index) {
        return new ByteBufferIndexer(buffer, index);
    }

    public static ByteIndexer create(BytePointer pointer, Index index) {
        return new ByteRawIndexer(pointer, index);
    }

    public static ByteIndexer create(byte[] array, long ... sizes) {
        return new ByteArrayIndexer(array, sizes);
    }

    public static ByteIndexer create(ByteBuffer buffer, long ... sizes) {
        return new ByteBufferIndexer(buffer, sizes);
    }

    public static ByteIndexer create(BytePointer pointer, long ... sizes) {
        return new ByteRawIndexer(pointer, sizes);
    }

    public static ByteIndexer create(byte[] array, long[] sizes, long[] strides) {
        return new ByteArrayIndexer(array, sizes, strides);
    }

    public static ByteIndexer create(ByteBuffer buffer, long[] sizes, long[] strides) {
        return new ByteBufferIndexer(buffer, sizes, strides);
    }

    public static ByteIndexer create(BytePointer pointer, long[] sizes, long[] strides) {
        return new ByteRawIndexer(pointer, sizes, strides);
    }

    public static ByteIndexer create(BytePointer pointer, long[] sizes, long[] strides, boolean direct) {
        return ByteIndexer.create(pointer, Index.create(sizes, strides), direct);
    }

    public static ByteIndexer create(final BytePointer pointer, Index index, boolean direct) {
        if (direct) {
            return Raw.getInstance() != null ? new ByteRawIndexer(pointer, index) : new ByteBufferIndexer(pointer.asBuffer(), index);
        }
        final long position = pointer.position();
        byte[] array = new byte[(int)Math.min(pointer.limit() - position, Integer.MAX_VALUE)];
        pointer.get(array);
        return new ByteArrayIndexer(array, index){

            @Override
            public void release() {
                pointer.position(position).put(this.array);
                super.release();
            }
        };
    }

    public abstract byte get(long var1);

    public ByteIndexer get(long i2, byte[] b2) {
        return this.get(i2, b2, 0, b2.length);
    }

    public abstract ByteIndexer get(long var1, byte[] var3, int var4, int var5);

    public abstract byte get(long var1, long var3);

    public ByteIndexer get(long i2, long j2, byte[] b2) {
        return this.get(i2, j2, b2, 0, b2.length);
    }

    public abstract ByteIndexer get(long var1, long var3, byte[] var5, int var6, int var7);

    public abstract byte get(long var1, long var3, long var5);

    public abstract byte get(long ... var1);

    public ByteIndexer get(long[] indices, byte[] b2) {
        return this.get(indices, b2, 0, b2.length);
    }

    public abstract ByteIndexer get(long[] var1, byte[] var2, int var3, int var4);

    public abstract ByteIndexer put(long var1, byte var3);

    public ByteIndexer put(long i2, byte ... b2) {
        return this.put(i2, b2, 0, b2.length);
    }

    public abstract ByteIndexer put(long var1, byte[] var3, int var4, int var5);

    public abstract ByteIndexer put(long var1, long var3, byte var5);

    public ByteIndexer put(long i2, long j2, byte ... b2) {
        return this.put(i2, j2, b2, 0, b2.length);
    }

    public abstract ByteIndexer put(long var1, long var3, byte[] var5, int var6, int var7);

    public abstract ByteIndexer put(long var1, long var3, long var5, byte var7);

    public abstract ByteIndexer put(long[] var1, byte var2);

    public ByteIndexer put(long[] indices, byte ... b2) {
        return this.put(indices, b2, 0, b2.length);
    }

    public abstract ByteIndexer put(long[] var1, byte[] var2, int var3, int var4);

    public abstract byte getByte(long var1);

    public abstract ByteIndexer putByte(long var1, byte var3);

    public abstract short getShort(long var1);

    public abstract ByteIndexer putShort(long var1, short var3);

    public abstract int getInt(long var1);

    public abstract ByteIndexer putInt(long var1, int var3);

    public abstract long getLong(long var1);

    public abstract ByteIndexer putLong(long var1, long var3);

    public abstract float getFloat(long var1);

    public abstract ByteIndexer putFloat(long var1, float var3);

    public abstract double getDouble(long var1);

    public abstract ByteIndexer putDouble(long var1, double var3);

    public abstract char getChar(long var1);

    public abstract ByteIndexer putChar(long var1, char var3);

    public int getUByte(long i2) {
        return this.getByte(i2) & 0xFF;
    }

    public ByteIndexer putUByte(long i2, int b2) {
        return this.putByte(i2, (byte)b2);
    }

    public int getUShort(long i2) {
        return this.getShort(i2) & 0xFFFF;
    }

    public ByteIndexer putUShort(long i2, int s2) {
        return this.putShort(i2, (short)s2);
    }

    public long getUInt(long i2) {
        return (long)this.getInt(i2) & 0xFFFFFFFFL;
    }

    public ByteIndexer putUInt(long i2, long n2) {
        return this.putInt(i2, (int)n2);
    }

    public BigInteger getULong(long i2) {
        return ULongIndexer.toBigInteger(this.getLong(i2));
    }

    public ByteIndexer putULong(long i2, BigInteger l2) {
        return this.putLong(i2, ULongIndexer.fromBigInteger(l2));
    }

    public float getHalf(long i2) {
        return HalfIndexer.toFloat(this.getShort(i2));
    }

    public ByteIndexer putHalf(long i2, float h2) {
        return this.putShort(i2, (short)HalfIndexer.fromFloat(h2));
    }

    public float getBfloat16(long i2) {
        return Bfloat16Indexer.toFloat(this.getShort(i2));
    }

    public ByteIndexer putBfloat16(long i2, float h2) {
        return this.putShort(i2, (short)Bfloat16Indexer.fromFloat(h2));
    }

    public boolean getBoolean(long i2) {
        return this.get(i2) != 0;
    }

    public ByteIndexer putBoolean(long i2, boolean b2) {
        return this.put(i2, b2 ? (byte)1 : 0);
    }

    @Override
    public double getDouble(long ... indices) {
        return this.get(indices);
    }

    @Override
    public ByteIndexer putDouble(long[] indices, double b2) {
        return this.put(indices, (byte)b2);
    }
}

