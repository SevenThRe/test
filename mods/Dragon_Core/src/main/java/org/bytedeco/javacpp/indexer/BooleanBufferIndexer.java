/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import org.bytedeco.javacpp.indexer.BooleanIndexer;
import org.bytedeco.javacpp.indexer.Index;

public class BooleanBufferIndexer
extends BooleanIndexer {
    protected ByteBuffer buffer;

    public BooleanBufferIndexer(ByteBuffer buffer) {
        this(buffer, Index.create((long)buffer.limit()));
    }

    public BooleanBufferIndexer(ByteBuffer buffer, long ... sizes) {
        this(buffer, Index.create(sizes));
    }

    public BooleanBufferIndexer(ByteBuffer buffer, long[] sizes, long[] strides) {
        this(buffer, Index.create(sizes, strides));
    }

    public BooleanBufferIndexer(ByteBuffer buffer, Index index) {
        super(index);
        this.buffer = buffer;
    }

    @Override
    public Buffer buffer() {
        return this.buffer;
    }

    public BooleanIndexer reindex(Index index) {
        return new BooleanBufferIndexer(this.buffer, index);
    }

    @Override
    public boolean get(long i2) {
        return this.buffer.get((int)this.index(i2)) != 0;
    }

    @Override
    public BooleanIndexer get(long i2, boolean[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            b2[offset + n2] = this.buffer.get((int)this.index(i2) + n2) != 0;
        }
        return this;
    }

    @Override
    public boolean get(long i2, long j2) {
        return this.buffer.get((int)this.index(i2, j2)) != 0;
    }

    @Override
    public BooleanIndexer get(long i2, long j2, boolean[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            b2[offset + n2] = this.buffer.get((int)this.index(i2, j2) + n2) != 0;
        }
        return this;
    }

    @Override
    public boolean get(long i2, long j2, long k2) {
        return this.buffer.get((int)this.index(i2, j2, k2)) != 0;
    }

    @Override
    public boolean get(long ... indices) {
        return this.buffer.get((int)this.index(indices)) != 0;
    }

    @Override
    public BooleanIndexer get(long[] indices, boolean[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            b2[offset + n2] = this.buffer.get((int)this.index(indices) + n2) != 0;
        }
        return this;
    }

    @Override
    public BooleanIndexer put(long i2, boolean b2) {
        this.buffer.put((int)this.index(i2), b2 ? (byte)1 : 0);
        return this;
    }

    @Override
    public BooleanIndexer put(long i2, boolean[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(i2) + n2, b2[offset + n2] ? (byte)1 : 0);
        }
        return this;
    }

    @Override
    public BooleanIndexer put(long i2, long j2, boolean b2) {
        this.buffer.put((int)this.index(i2, j2), b2 ? (byte)1 : 0);
        return this;
    }

    @Override
    public BooleanIndexer put(long i2, long j2, boolean[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(i2, j2) + n2, b2[offset + n2] ? (byte)1 : 0);
        }
        return this;
    }

    @Override
    public BooleanIndexer put(long i2, long j2, long k2, boolean b2) {
        this.buffer.put((int)this.index(i2, j2, k2), b2 ? (byte)1 : 0);
        return this;
    }

    @Override
    public BooleanIndexer put(long[] indices, boolean b2) {
        this.buffer.put((int)this.index(indices), b2 ? (byte)1 : 0);
        return this;
    }

    @Override
    public BooleanIndexer put(long[] indices, boolean[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(indices) + n2, b2[offset + n2] ? (byte)1 : 0);
        }
        return this;
    }

    @Override
    public void release() {
        this.buffer = null;
    }
}

