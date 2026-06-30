/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.indexer.Bfloat16Indexer;
import org.bytedeco.javacpp.indexer.Index;

public class Bfloat16ArrayIndexer
extends Bfloat16Indexer {
    protected short[] array;

    public Bfloat16ArrayIndexer(short[] array) {
        this(array, Index.create((long)array.length));
    }

    public Bfloat16ArrayIndexer(short[] array, long ... sizes) {
        this(array, Index.create(sizes));
    }

    public Bfloat16ArrayIndexer(short[] array, long[] sizes, long[] strides) {
        this(array, Index.create(sizes, strides));
    }

    public Bfloat16ArrayIndexer(short[] array, Index index) {
        super(index);
        this.array = array;
    }

    public short[] array() {
        return this.array;
    }

    public Bfloat16Indexer reindex(Index index) {
        return new Bfloat16ArrayIndexer(this.array, index);
    }

    @Override
    public float get(long i2) {
        return Bfloat16ArrayIndexer.toFloat(this.array[(int)this.index(i2)]);
    }

    @Override
    public Bfloat16Indexer get(long i2, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            h2[offset + n2] = Bfloat16ArrayIndexer.toFloat(this.array[(int)this.index(i2) + n2]);
        }
        return this;
    }

    @Override
    public float get(long i2, long j2) {
        return Bfloat16ArrayIndexer.toFloat(this.array[(int)this.index(i2, j2)]);
    }

    @Override
    public Bfloat16Indexer get(long i2, long j2, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            h2[offset + n2] = Bfloat16ArrayIndexer.toFloat(this.array[(int)this.index(i2, j2) + n2]);
        }
        return this;
    }

    @Override
    public float get(long i2, long j2, long k2) {
        return Bfloat16ArrayIndexer.toFloat(this.array[(int)this.index(i2, j2, k2)]);
    }

    @Override
    public float get(long ... indices) {
        return Bfloat16ArrayIndexer.toFloat(this.array[(int)this.index(indices)]);
    }

    @Override
    public Bfloat16Indexer get(long[] indices, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            h2[offset + n2] = Bfloat16ArrayIndexer.toFloat(this.array[(int)this.index(indices) + n2]);
        }
        return this;
    }

    @Override
    public Bfloat16Indexer put(long i2, float h2) {
        this.array[(int)this.index((long)i2)] = (short)Bfloat16ArrayIndexer.fromFloat(h2);
        return this;
    }

    @Override
    public Bfloat16Indexer put(long i2, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long)i2) + n2] = (short)Bfloat16ArrayIndexer.fromFloat(h2[offset + n2]);
        }
        return this;
    }

    @Override
    public Bfloat16Indexer put(long i2, long j2, float h2) {
        this.array[(int)this.index((long)i2, (long)j2)] = (short)Bfloat16ArrayIndexer.fromFloat(h2);
        return this;
    }

    @Override
    public Bfloat16Indexer put(long i2, long j2, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long)i2, (long)j2) + n2] = (short)Bfloat16ArrayIndexer.fromFloat(h2[offset + n2]);
        }
        return this;
    }

    @Override
    public Bfloat16Indexer put(long i2, long j2, long k2, float h2) {
        this.array[(int)this.index((long)i2, (long)j2, (long)k2)] = (short)Bfloat16ArrayIndexer.fromFloat(h2);
        return this;
    }

    @Override
    public Bfloat16Indexer put(long[] indices, float h2) {
        this.array[(int)this.index((long[])indices)] = (short)Bfloat16ArrayIndexer.fromFloat(h2);
        return this;
    }

    @Override
    public Bfloat16Indexer put(long[] indices, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long[])indices) + n2] = (short)Bfloat16ArrayIndexer.fromFloat(h2[offset + n2]);
        }
        return this;
    }

    @Override
    public void release() {
        this.array = null;
    }
}

