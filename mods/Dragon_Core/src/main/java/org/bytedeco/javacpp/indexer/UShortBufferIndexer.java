/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import java.nio.Buffer;
import java.nio.ShortBuffer;
import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.UShortIndexer;

public class UShortBufferIndexer
extends UShortIndexer {
    protected ShortBuffer buffer;

    public UShortBufferIndexer(ShortBuffer buffer) {
        this(buffer, Index.create((long)buffer.limit()));
    }

    public UShortBufferIndexer(ShortBuffer buffer, long ... sizes) {
        this(buffer, Index.create(sizes));
    }

    public UShortBufferIndexer(ShortBuffer buffer, long[] sizes, long[] strides) {
        this(buffer, Index.create(sizes, strides));
    }

    public UShortBufferIndexer(ShortBuffer buffer, Index index) {
        super(index);
        this.buffer = buffer;
    }

    @Override
    public Buffer buffer() {
        return this.buffer;
    }

    public UShortIndexer reindex(Index index) {
        return new UShortBufferIndexer(this.buffer, index);
    }

    @Override
    public int get(long i2) {
        return this.buffer.get((int)this.index(i2)) & 0xFFFF;
    }

    @Override
    public UShortIndexer get(long i2, int[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            s2[offset + n2] = this.buffer.get((int)this.index(i2) + n2) & 0xFFFF;
        }
        return this;
    }

    @Override
    public int get(long i2, long j2) {
        return this.buffer.get((int)this.index(i2, j2)) & 0xFFFF;
    }

    @Override
    public UShortIndexer get(long i2, long j2, int[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            s2[offset + n2] = this.buffer.get((int)this.index(i2, j2) + n2) & 0xFFFF;
        }
        return this;
    }

    @Override
    public int get(long i2, long j2, long k2) {
        return this.buffer.get((int)this.index(i2, j2, k2)) & 0xFFFF;
    }

    @Override
    public int get(long ... indices) {
        return this.buffer.get((int)this.index(indices)) & 0xFFFF;
    }

    @Override
    public UShortIndexer get(long[] indices, int[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            s2[offset + n2] = this.buffer.get((int)this.index(indices) + n2) & 0xFFFF;
        }
        return this;
    }

    @Override
    public UShortIndexer put(long i2, int s2) {
        this.buffer.put((int)this.index(i2), (short)s2);
        return this;
    }

    @Override
    public UShortIndexer put(long i2, int[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(i2) + n2, (short)s2[offset + n2]);
        }
        return this;
    }

    @Override
    public UShortIndexer put(long i2, long j2, int s2) {
        this.buffer.put((int)this.index(i2, j2), (short)s2);
        return this;
    }

    @Override
    public UShortIndexer put(long i2, long j2, int[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(i2, j2) + n2, (short)s2[offset + n2]);
        }
        return this;
    }

    @Override
    public UShortIndexer put(long i2, long j2, long k2, int s2) {
        this.buffer.put((int)this.index(i2, j2, k2), (short)s2);
        return this;
    }

    @Override
    public UShortIndexer put(long[] indices, int s2) {
        this.buffer.put((int)this.index(indices), (short)s2);
        return this;
    }

    @Override
    public UShortIndexer put(long[] indices, int[] s2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(indices) + n2, (short)s2[offset + n2]);
        }
        return this;
    }

    @Override
    public void release() {
        this.buffer = null;
    }
}

