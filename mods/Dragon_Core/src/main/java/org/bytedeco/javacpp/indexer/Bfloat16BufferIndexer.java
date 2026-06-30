/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import java.nio.Buffer;
import java.nio.ShortBuffer;
import org.bytedeco.javacpp.indexer.Bfloat16Indexer;
import org.bytedeco.javacpp.indexer.Index;

public class Bfloat16BufferIndexer
extends Bfloat16Indexer {
    protected ShortBuffer buffer;

    public Bfloat16BufferIndexer(ShortBuffer buffer) {
        this(buffer, Index.create((long)buffer.limit()));
    }

    public Bfloat16BufferIndexer(ShortBuffer buffer, long ... sizes) {
        this(buffer, Index.create(sizes));
    }

    public Bfloat16BufferIndexer(ShortBuffer buffer, long[] sizes, long[] strides) {
        this(buffer, Index.create(sizes, strides));
    }

    public Bfloat16BufferIndexer(ShortBuffer buffer, Index index) {
        super(index);
        this.buffer = buffer;
    }

    @Override
    public Buffer buffer() {
        return this.buffer;
    }

    public Bfloat16Indexer reindex(Index index) {
        return new Bfloat16BufferIndexer(this.buffer, index);
    }

    @Override
    public float get(long i2) {
        return Bfloat16BufferIndexer.toFloat(this.buffer.get((int)this.index(i2)));
    }

    @Override
    public Bfloat16Indexer get(long i2, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            h2[offset + n2] = Bfloat16BufferIndexer.toFloat(this.buffer.get((int)this.index(i2) + n2));
        }
        return this;
    }

    @Override
    public float get(long i2, long j2) {
        return Bfloat16BufferIndexer.toFloat(this.buffer.get((int)this.index(i2, j2)));
    }

    @Override
    public Bfloat16Indexer get(long i2, long j2, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            h2[offset + n2] = Bfloat16BufferIndexer.toFloat(this.buffer.get((int)this.index(i2, j2) + n2));
        }
        return this;
    }

    @Override
    public float get(long i2, long j2, long k2) {
        return Bfloat16BufferIndexer.toFloat(this.buffer.get((int)this.index(i2, j2, k2)));
    }

    @Override
    public float get(long ... indices) {
        return Bfloat16BufferIndexer.toFloat(this.buffer.get((int)this.index(indices)));
    }

    @Override
    public Bfloat16Indexer get(long[] indices, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            h2[offset + n2] = Bfloat16BufferIndexer.toFloat(this.buffer.get((int)this.index(indices) + n2));
        }
        return this;
    }

    @Override
    public Bfloat16Indexer put(long i2, float h2) {
        this.buffer.put((int)this.index(i2), (short)Bfloat16BufferIndexer.fromFloat(h2));
        return this;
    }

    @Override
    public Bfloat16Indexer put(long i2, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(i2) + n2, (short)Bfloat16BufferIndexer.fromFloat(h2[offset + n2]));
        }
        return this;
    }

    @Override
    public Bfloat16Indexer put(long i2, long j2, float h2) {
        this.buffer.put((int)this.index(i2, j2), (short)Bfloat16BufferIndexer.fromFloat(h2));
        return this;
    }

    @Override
    public Bfloat16Indexer put(long i2, long j2, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(i2, j2) + n2, (short)Bfloat16BufferIndexer.fromFloat(h2[offset + n2]));
        }
        return this;
    }

    @Override
    public Bfloat16Indexer put(long i2, long j2, long k2, float h2) {
        this.buffer.put((int)this.index(i2, j2, k2), (short)Bfloat16BufferIndexer.fromFloat(h2));
        return this;
    }

    @Override
    public Bfloat16Indexer put(long[] indices, float h2) {
        this.buffer.put((int)this.index(indices), (short)Bfloat16BufferIndexer.fromFloat(h2));
        return this;
    }

    @Override
    public Bfloat16Indexer put(long[] indices, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(indices) + n2, (short)Bfloat16BufferIndexer.fromFloat(h2[offset + n2]));
        }
        return this;
    }

    @Override
    public void release() {
        this.buffer = null;
    }
}

