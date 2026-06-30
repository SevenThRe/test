/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import java.nio.Buffer;
import java.nio.IntBuffer;
import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.IntIndexer;

public class IntBufferIndexer
extends IntIndexer {
    protected IntBuffer buffer;

    public IntBufferIndexer(IntBuffer buffer) {
        this(buffer, Index.create((long)buffer.limit()));
    }

    public IntBufferIndexer(IntBuffer buffer, long ... sizes) {
        this(buffer, Index.create(sizes));
    }

    public IntBufferIndexer(IntBuffer buffer, long[] sizes, long[] strides) {
        this(buffer, Index.create(sizes, strides));
    }

    public IntBufferIndexer(IntBuffer buffer, Index index) {
        super(index);
        this.buffer = buffer;
    }

    @Override
    public Buffer buffer() {
        return this.buffer;
    }

    public IntIndexer reindex(Index index) {
        return new IntBufferIndexer(this.buffer, index);
    }

    @Override
    public int get(long i2) {
        return this.buffer.get((int)this.index(i2));
    }

    @Override
    public IntIndexer get(long i2, int[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            m2[offset + n2] = this.buffer.get((int)this.index(i2) + n2);
        }
        return this;
    }

    @Override
    public int get(long i2, long j2) {
        return this.buffer.get((int)this.index(i2, j2));
    }

    @Override
    public IntIndexer get(long i2, long j2, int[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            m2[offset + n2] = this.buffer.get((int)this.index(i2, j2) + n2);
        }
        return this;
    }

    @Override
    public int get(long i2, long j2, long k2) {
        return this.buffer.get((int)this.index(i2, j2, k2));
    }

    @Override
    public int get(long ... indices) {
        return this.buffer.get((int)this.index(indices));
    }

    @Override
    public IntIndexer get(long[] indices, int[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            m2[offset + n2] = this.buffer.get((int)this.index(indices) + n2);
        }
        return this;
    }

    @Override
    public IntIndexer put(long i2, int n2) {
        this.buffer.put((int)this.index(i2), n2);
        return this;
    }

    @Override
    public IntIndexer put(long i2, int[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(i2) + n2, m2[offset + n2]);
        }
        return this;
    }

    @Override
    public IntIndexer put(long i2, long j2, int n2) {
        this.buffer.put((int)this.index(i2, j2), n2);
        return this;
    }

    @Override
    public IntIndexer put(long i2, long j2, int[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(i2, j2) + n2, m2[offset + n2]);
        }
        return this;
    }

    @Override
    public IntIndexer put(long i2, long j2, long k2, int n2) {
        this.buffer.put((int)this.index(i2, j2, k2), n2);
        return this;
    }

    @Override
    public IntIndexer put(long[] indices, int n2) {
        this.buffer.put((int)this.index(indices), n2);
        return this;
    }

    @Override
    public IntIndexer put(long[] indices, int[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(indices) + n2, m2[offset + n2]);
        }
        return this;
    }

    @Override
    public void release() {
        this.buffer = null;
    }
}

