/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import java.nio.ShortBuffer;
import org.bytedeco.javacpp.ShortPointer;
import org.bytedeco.javacpp.indexer.Bfloat16ArrayIndexer;
import org.bytedeco.javacpp.indexer.Bfloat16BufferIndexer;
import org.bytedeco.javacpp.indexer.Bfloat16RawIndexer;
import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.Indexer;
import org.bytedeco.javacpp.indexer.Raw;

public abstract class Bfloat16Indexer
extends Indexer {
    public static final int VALUE_BYTES = 2;

    protected Bfloat16Indexer(Index index) {
        super(index);
    }

    protected Bfloat16Indexer(long[] sizes, long[] strides) {
        super(sizes, strides);
    }

    public static Bfloat16Indexer create(short[] array) {
        return new Bfloat16ArrayIndexer(array);
    }

    public static Bfloat16Indexer create(ShortBuffer buffer) {
        return new Bfloat16BufferIndexer(buffer);
    }

    public static Bfloat16Indexer create(ShortPointer pointer) {
        return new Bfloat16RawIndexer(pointer);
    }

    public static Bfloat16Indexer create(short[] array, Index index) {
        return new Bfloat16ArrayIndexer(array, index);
    }

    public static Bfloat16Indexer create(ShortBuffer buffer, Index index) {
        return new Bfloat16BufferIndexer(buffer, index);
    }

    public static Bfloat16Indexer create(ShortPointer pointer, Index index) {
        return new Bfloat16RawIndexer(pointer, index);
    }

    public static Bfloat16Indexer create(short[] array, long ... sizes) {
        return new Bfloat16ArrayIndexer(array, sizes);
    }

    public static Bfloat16Indexer create(ShortBuffer buffer, long ... sizes) {
        return new Bfloat16BufferIndexer(buffer, sizes);
    }

    public static Bfloat16Indexer create(ShortPointer pointer, long ... sizes) {
        return new Bfloat16RawIndexer(pointer, sizes);
    }

    public static Bfloat16Indexer create(short[] array, long[] sizes, long[] strides) {
        return new Bfloat16ArrayIndexer(array, sizes, strides);
    }

    public static Bfloat16Indexer create(ShortBuffer buffer, long[] sizes, long[] strides) {
        return new Bfloat16BufferIndexer(buffer, sizes, strides);
    }

    public static Bfloat16Indexer create(ShortPointer pointer, long[] sizes, long[] strides) {
        return new Bfloat16RawIndexer(pointer, sizes, strides);
    }

    public static Bfloat16Indexer create(ShortPointer pointer, long[] sizes, long[] strides, boolean direct) {
        return Bfloat16Indexer.create(pointer, Index.create(sizes, strides), direct);
    }

    public static Bfloat16Indexer create(final ShortPointer pointer, Index index, boolean direct) {
        if (direct) {
            return Raw.getInstance() != null ? new Bfloat16RawIndexer(pointer, index) : new Bfloat16BufferIndexer(pointer.asBuffer(), index);
        }
        final long position = pointer.position();
        short[] array = new short[(int)Math.min(pointer.limit() - position, Integer.MAX_VALUE)];
        pointer.get(array);
        return new Bfloat16ArrayIndexer(array, index){

            @Override
            public void release() {
                pointer.position(position).put(this.array);
                super.release();
            }
        };
    }

    public static float toFloat(int h2) {
        return Float.intBitsToFloat(h2 << 16);
    }

    public static int fromFloat(float h2) {
        return Float.floatToIntBits(h2) >>> 16;
    }

    public abstract float get(long var1);

    public Bfloat16Indexer get(long i2, float[] h2) {
        return this.get(i2, h2, 0, h2.length);
    }

    public abstract Bfloat16Indexer get(long var1, float[] var3, int var4, int var5);

    public abstract float get(long var1, long var3);

    public Bfloat16Indexer get(long i2, long j2, float[] h2) {
        return this.get(i2, j2, h2, 0, h2.length);
    }

    public abstract Bfloat16Indexer get(long var1, long var3, float[] var5, int var6, int var7);

    public abstract float get(long var1, long var3, long var5);

    public abstract float get(long ... var1);

    public Bfloat16Indexer get(long[] indices, float[] h2) {
        return this.get(indices, h2, 0, h2.length);
    }

    public abstract Bfloat16Indexer get(long[] var1, float[] var2, int var3, int var4);

    public abstract Bfloat16Indexer put(long var1, float var3);

    public Bfloat16Indexer put(long i2, float ... h2) {
        return this.put(i2, h2, 0, h2.length);
    }

    public abstract Bfloat16Indexer put(long var1, float[] var3, int var4, int var5);

    public abstract Bfloat16Indexer put(long var1, long var3, float var5);

    public Bfloat16Indexer put(long i2, long j2, float ... h2) {
        return this.put(i2, j2, h2, 0, h2.length);
    }

    public abstract Bfloat16Indexer put(long var1, long var3, float[] var5, int var6, int var7);

    public abstract Bfloat16Indexer put(long var1, long var3, long var5, float var7);

    public abstract Bfloat16Indexer put(long[] var1, float var2);

    public Bfloat16Indexer put(long[] indices, float ... h2) {
        return this.put(indices, h2, 0, h2.length);
    }

    public abstract Bfloat16Indexer put(long[] var1, float[] var2, int var3, int var4);

    @Override
    public double getDouble(long ... indices) {
        return this.get(indices);
    }

    @Override
    public Bfloat16Indexer putDouble(long[] indices, double h2) {
        return this.put(indices, (float)h2);
    }
}

