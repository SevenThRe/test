/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import java.nio.Buffer;
import java.nio.IntBuffer;
import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.UIntIndexer;

public class UIntBufferIndexer
extends UIntIndexer {
    protected IntBuffer buffer;

    public UIntBufferIndexer(IntBuffer buffer) {
        this(buffer, Index.create((long)buffer.limit()));
    }

    public UIntBufferIndexer(IntBuffer buffer, long ... sizes) {
        this(buffer, Index.create(sizes));
    }

    public UIntBufferIndexer(IntBuffer buffer, long[] sizes, long[] strides) {
        this(buffer, Index.create(sizes, strides));
    }

    public UIntBufferIndexer(IntBuffer buffer, Index index) {
        super(index);
        this.buffer = buffer;
    }

    @Override
    public Buffer buffer() {
        return this.buffer;
    }

    public UIntIndexer reindex(Index index) {
        return new UIntBufferIndexer(this.buffer, index);
    }

    @Override
    public long get(long i2) {
        return (long)this.buffer.get((int)this.index(i2)) & 0xFFFFFFFFL;
    }

    @Override
    public UIntIndexer get(long i2, long[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            m2[offset + n2] = (long)this.buffer.get((int)this.index(i2) + n2) & 0xFFFFFFFFL;
        }
        return this;
    }

    @Override
    public long get(long i2, long j2) {
        return (long)this.buffer.get((int)this.index(i2, j2)) & 0xFFFFFFFFL;
    }

    @Override
    public UIntIndexer get(long i2, long j2, long[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            m2[offset + n2] = (long)this.buffer.get((int)this.index(i2, j2) + n2) & 0xFFFFFFFFL;
        }
        return this;
    }

    @Override
    public long get(long i2, long j2, long k2) {
        return (long)this.buffer.get((int)this.index(i2, j2, k2)) & 0xFFFFFFFFL;
    }

    @Override
    public long get(long ... indices) {
        return (long)this.buffer.get((int)this.index(indices)) & 0xFFFFFFFFL;
    }

    @Override
    public UIntIndexer get(long[] indices, long[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            m2[offset + n2] = (long)this.buffer.get((int)this.index(indices) + n2) & 0xFFFFFFFFL;
        }
        return this;
    }

    @Override
    public UIntIndexer put(long i2, long n2) {
        this.buffer.put((int)this.index(i2), (int)n2);
        return this;
    }

    @Override
    public UIntIndexer put(long i2, long[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(i2) + n2, (int)m2[offset + n2]);
        }
        return this;
    }

    @Override
    public UIntIndexer put(long i2, long j2, long n2) {
        this.buffer.put((int)this.index(i2, j2), (int)n2);
        return this;
    }

    @Override
    public UIntIndexer put(long i2, long j2, long[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(i2, j2) + n2, (int)m2[offset + n2]);
        }
        return this;
    }

    @Override
    public UIntIndexer put(long i2, long j2, long k2, long n2) {
        this.buffer.put((int)this.index(i2, j2, k2), (int)n2);
        return this;
    }

    @Override
    public UIntIndexer put(long[] indices, long n2) {
        this.buffer.put((int)this.index(indices), (int)n2);
        return this;
    }

    @Override
    public UIntIndexer put(long[] indices, long[] m2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(indices) + n2, (int)m2[offset + n2]);
        }
        return this;
    }

    @Override
    public void release() {
        this.buffer = null;
    }
}

