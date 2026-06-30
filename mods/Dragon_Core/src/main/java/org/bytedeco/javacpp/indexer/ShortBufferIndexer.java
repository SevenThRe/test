/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import java.nio.Buffer;
import java.nio.ShortBuffer;
import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.ShortIndexer;

public class ShortBufferIndexer
extends ShortIndexer {
    protected ShortBuffer buffer;

    public ShortBufferIndexer(ShortBuffer buffer) {
        this(buffer, Index.create((long)buffer.limit()));
    }

    public ShortBufferIndexer(ShortBuffer buffer, long ... sizes) {
        this(buffer, Index.create(sizes));
    }

    public ShortBufferIndexer(ShortBuffer buffer, long[] sizes, long[] strides) {
        this(buffer, Index.create(sizes, strides));
    }

    public ShortBufferIndexer(ShortBuffer buffer, Index index) {
        super(index);
        this.buffer = buffer;
    }

    @Override
    public Buffer buffer() {
        return this.buffer;
    }

    public ShortIndexer reindex(Index index) {
        return new ShortBufferIndexer(this.buffer, index);
    }

    @Override
    public short get(long i2) {
        return this.buffer.get((int)this.index(i2));
    }

    @Override
    public ShortIndexer get(long i2, short[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            s2[offset + n2] = this.buffer.get((int)this.index(i2) + n2);
        }
        return this;
    }

    @Override
    public short get(long i2, long j2) {
        return this.buffer.get((int)this.index(i2, j2));
    }

    @Override
    public ShortIndexer get(long i2, long j2, short[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            s2[offset + n2] = this.buffer.get((int)this.index(i2, j2) + n2);
        }
        return this;
    }

    @Override
    public short get(long i2, long j2, long k2) {
        return this.buffer.get((int)this.index(i2, j2, k2));
    }

    @Override
    public short get(long ... indices) {
        return this.buffer.get((int)this.index(indices));
    }

    @Override
    public ShortIndexer get(long[] indices, short[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            s2[offset + n2] = this.buffer.get((int)this.index(indices) + n2);
        }
        return this;
    }

    @Override
    public ShortIndexer put(long i2, short s2) {
        this.buffer.put((int)this.index(i2), s2);
        return this;
    }

    @Override
    public ShortIndexer put(long i2, short[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(i2) + n2, s2[offset + n2]);
        }
        return this;
    }

    @Override
    public ShortIndexer put(long i2, long j2, short s2) {
        this.buffer.put((int)this.index(i2, j2), s2);
        return this;
    }

    @Override
    public ShortIndexer put(long i2, long j2, short[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(i2, j2) + n2, s2[offset + n2]);
        }
        return this;
    }

    @Override
    public ShortIndexer put(long i2, long j2, long k2, short s2) {
        this.buffer.put((int)this.index(i2, j2, k2), s2);
        return this;
    }

    @Override
    public ShortIndexer put(long[] indices, short s2) {
        this.buffer.put((int)this.index(indices), s2);
        return this;
    }

    @Override
    public ShortIndexer put(long[] indices, short[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(indices) + n2, s2[offset + n2]);
        }
        return this;
    }

    @Override
    public void release() {
        this.buffer = null;
    }
}

