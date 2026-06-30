/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import java.nio.Buffer;
import java.nio.ShortBuffer;
import org.bytedeco.javacpp.indexer.HalfIndexer;
import org.bytedeco.javacpp.indexer.Index;

public class HalfBufferIndexer
extends HalfIndexer {
    protected ShortBuffer buffer;

    public HalfBufferIndexer(ShortBuffer buffer) {
        this(buffer, Index.create((long)buffer.limit()));
    }

    public HalfBufferIndexer(ShortBuffer buffer, long ... sizes) {
        this(buffer, Index.create(sizes));
    }

    public HalfBufferIndexer(ShortBuffer buffer, long[] sizes, long[] strides) {
        this(buffer, Index.create(sizes, strides));
    }

    public HalfBufferIndexer(ShortBuffer buffer, Index index) {
        super(index);
        this.buffer = buffer;
    }

    @Override
    public Buffer buffer() {
        return this.buffer;
    }

    public HalfIndexer reindex(Index index) {
        return new HalfBufferIndexer(this.buffer, index);
    }

    @Override
    public float get(long i2) {
        return HalfBufferIndexer.toFloat(this.buffer.get((int)this.index(i2)));
    }

    @Override
    public HalfIndexer get(long i2, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            h2[offset + n2] = HalfBufferIndexer.toFloat(this.buffer.get((int)this.index(i2) + n2));
        }
        return this;
    }

    @Override
    public float get(long i2, long j2) {
        return HalfBufferIndexer.toFloat(this.buffer.get((int)this.index(i2, j2)));
    }

    @Override
    public HalfIndexer get(long i2, long j2, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            h2[offset + n2] = HalfBufferIndexer.toFloat(this.buffer.get((int)this.index(i2, j2) + n2));
        }
        return this;
    }

    @Override
    public float get(long i2, long j2, long k2) {
        return HalfBufferIndexer.toFloat(this.buffer.get((int)this.index(i2, j2, k2)));
    }

    @Override
    public float get(long ... indices) {
        return HalfBufferIndexer.toFloat(this.buffer.get((int)this.index(indices)));
    }

    @Override
    public HalfIndexer get(long[] indices, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            h2[offset + n2] = HalfBufferIndexer.toFloat(this.buffer.get((int)this.index(indices) + n2));
        }
        return this;
    }

    @Override
    public HalfIndexer put(long i2, float h2) {
        this.buffer.put((int)this.index(i2), (short)HalfBufferIndexer.fromFloat(h2));
        return this;
    }

    @Override
    public HalfIndexer put(long i2, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(i2) + n2, (short)HalfBufferIndexer.fromFloat(h2[offset + n2]));
        }
        return this;
    }

    @Override
    public HalfIndexer put(long i2, long j2, float h2) {
        this.buffer.put((int)this.index(i2, j2), (short)HalfBufferIndexer.fromFloat(h2));
        return this;
    }

    @Override
    public HalfIndexer put(long i2, long j2, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(i2, j2) + n2, (short)HalfBufferIndexer.fromFloat(h2[offset + n2]));
        }
        return this;
    }

    @Override
    public HalfIndexer put(long i2, long j2, long k2, float h2) {
        this.buffer.put((int)this.index(i2, j2, k2), (short)HalfBufferIndexer.fromFloat(h2));
        return this;
    }

    @Override
    public HalfIndexer put(long[] indices, float h2) {
        this.buffer.put((int)this.index(indices), (short)HalfBufferIndexer.fromFloat(h2));
        return this;
    }

    @Override
    public HalfIndexer put(long[] indices, float[] h2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(indices) + n2, (short)HalfBufferIndexer.fromFloat(h2[offset + n2]));
        }
        return this;
    }

    @Override
    public void release() {
        this.buffer = null;
    }
}

