/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import org.bytedeco.javacpp.indexer.FloatIndexer;
import org.bytedeco.javacpp.indexer.Index;

public class FloatBufferIndexer
extends FloatIndexer {
    protected FloatBuffer buffer;

    public FloatBufferIndexer(FloatBuffer buffer) {
        this(buffer, Index.create((long)buffer.limit()));
    }

    public FloatBufferIndexer(FloatBuffer buffer, long ... sizes) {
        this(buffer, Index.create(sizes));
    }

    public FloatBufferIndexer(FloatBuffer buffer, long[] sizes, long[] strides) {
        this(buffer, Index.create(sizes, strides));
    }

    public FloatBufferIndexer(FloatBuffer buffer, Index index) {
        super(index);
        this.buffer = buffer;
    }

    @Override
    public Buffer buffer() {
        return this.buffer;
    }

    public FloatIndexer reindex(Index index) {
        return new FloatBufferIndexer(this.buffer, index);
    }

    @Override
    public float get(long i2) {
        return this.buffer.get((int)this.index(i2));
    }

    @Override
    public FloatIndexer get(long i2, float[] f2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            f2[offset + n2] = this.buffer.get((int)this.index(i2) + n2);
        }
        return this;
    }

    @Override
    public float get(long i2, long j2) {
        return this.buffer.get((int)this.index(i2, j2));
    }

    @Override
    public FloatIndexer get(long i2, long j2, float[] f2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            f2[offset + n2] = this.buffer.get((int)this.index(i2, j2) + n2);
        }
        return this;
    }

    @Override
    public float get(long i2, long j2, long k2) {
        return this.buffer.get((int)this.index(i2, j2, k2));
    }

    @Override
    public float get(long ... indices) {
        return this.buffer.get((int)this.index(indices));
    }

    @Override
    public FloatIndexer get(long[] indices, float[] f2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            f2[offset + n2] = this.buffer.get((int)this.index(indices) + n2);
        }
        return this;
    }

    @Override
    public FloatIndexer put(long i2, float f2) {
        this.buffer.put((int)this.index(i2), f2);
        return this;
    }

    @Override
    public FloatIndexer put(long i2, float[] f2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(i2) + n2, f2[offset + n2]);
        }
        return this;
    }

    @Override
    public FloatIndexer put(long i2, long j2, float f2) {
        this.buffer.put((int)this.index(i2, j2), f2);
        return this;
    }

    @Override
    public FloatIndexer put(long i2, long j2, float[] f2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(i2, j2) + n2, f2[offset + n2]);
        }
        return this;
    }

    @Override
    public FloatIndexer put(long i2, long j2, long k2, float f2) {
        this.buffer.put((int)this.index(i2, j2, k2), f2);
        return this;
    }

    @Override
    public FloatIndexer put(long[] indices, float f2) {
        this.buffer.put((int)this.index(indices), f2);
        return this;
    }

    @Override
    public FloatIndexer put(long[] indices, float[] f2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(indices) + n2, f2[offset + n2]);
        }
        return this;
    }

    @Override
    public void release() {
        this.buffer = null;
    }
}

