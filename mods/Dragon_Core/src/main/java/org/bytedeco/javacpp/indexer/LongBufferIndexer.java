/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import java.nio.Buffer;
import java.nio.LongBuffer;
import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.LongIndexer;

public class LongBufferIndexer
extends LongIndexer {
    protected LongBuffer buffer;

    public LongBufferIndexer(LongBuffer buffer) {
        this(buffer, Index.create((long)buffer.limit()));
    }

    public LongBufferIndexer(LongBuffer buffer, long ... sizes) {
        this(buffer, Index.create(sizes));
    }

    public LongBufferIndexer(LongBuffer buffer, long[] sizes, long[] strides) {
        this(buffer, Index.create(sizes, strides));
    }

    public LongBufferIndexer(LongBuffer buffer, Index index) {
        super(index);
        this.buffer = buffer;
    }

    @Override
    public Buffer buffer() {
        return this.buffer;
    }

    public LongIndexer reindex(Index index) {
        return new LongBufferIndexer(this.buffer, index);
    }

    @Override
    public long get(long i2) {
        return this.buffer.get((int)this.index(i2));
    }

    @Override
    public LongIndexer get(long i2, long[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            l2[offset + n2] = this.buffer.get((int)this.index(i2) + n2);
        }
        return this;
    }

    @Override
    public long get(long i2, long j2) {
        return this.buffer.get((int)this.index(i2, j2));
    }

    @Override
    public LongIndexer get(long i2, long j2, long[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            l2[offset + n2] = this.buffer.get((int)this.index(i2, j2) + n2);
        }
        return this;
    }

    @Override
    public long get(long i2, long j2, long k2) {
        return this.buffer.get((int)this.index(i2, j2, k2));
    }

    @Override
    public long get(long ... indices) {
        return this.buffer.get((int)this.index(indices));
    }

    @Override
    public LongIndexer get(long[] indices, long[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            l2[offset + n2] = this.buffer.get((int)this.index(indices) + n2);
        }
        return this;
    }

    @Override
    public LongIndexer put(long i2, long l2) {
        this.buffer.put((int)this.index(i2), l2);
        return this;
    }

    @Override
    public LongIndexer put(long i2, long[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(i2) + n2, l2[offset + n2]);
        }
        return this;
    }

    @Override
    public LongIndexer put(long i2, long j2, long l2) {
        this.buffer.put((int)this.index(i2, j2), l2);
        return this;
    }

    @Override
    public LongIndexer put(long i2, long j2, long[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(i2, j2) + n2, l2[offset + n2]);
        }
        return this;
    }

    @Override
    public LongIndexer put(long i2, long j2, long k2, long l2) {
        this.buffer.put((int)this.index(i2, j2, k2), l2);
        return this;
    }

    @Override
    public LongIndexer put(long[] indices, long l2) {
        this.buffer.put((int)this.index(indices), l2);
        return this;
    }

    @Override
    public LongIndexer put(long[] indices, long[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(indices) + n2, l2[offset + n2]);
        }
        return this;
    }

    @Override
    public void release() {
        this.buffer = null;
    }
}

