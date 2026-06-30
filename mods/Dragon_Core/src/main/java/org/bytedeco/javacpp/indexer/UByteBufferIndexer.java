/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.UByteIndexer;

public class UByteBufferIndexer
extends UByteIndexer {
    protected ByteBuffer buffer;

    public UByteBufferIndexer(ByteBuffer buffer) {
        this(buffer, Index.create((long)buffer.limit()));
    }

    public UByteBufferIndexer(ByteBuffer buffer, long ... sizes) {
        this(buffer, Index.create(sizes));
    }

    public UByteBufferIndexer(ByteBuffer buffer, long[] sizes, long[] strides) {
        this(buffer, Index.create(sizes, strides));
    }

    public UByteBufferIndexer(ByteBuffer buffer, Index index) {
        super(index);
        this.buffer = buffer;
    }

    @Override
    public Buffer buffer() {
        return this.buffer;
    }

    public UByteIndexer reindex(Index index) {
        return new UByteBufferIndexer(this.buffer, index);
    }

    @Override
    public int get(long i2) {
        return this.buffer.get((int)this.index(i2)) & 0xFF;
    }

    @Override
    public UByteIndexer get(long i2, int[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            b2[offset + n2] = this.buffer.get((int)this.index(i2) + n2) & 0xFF;
        }
        return this;
    }

    @Override
    public int get(long i2, long j2) {
        return this.buffer.get((int)this.index(i2, j2)) & 0xFF;
    }

    @Override
    public UByteIndexer get(long i2, long j2, int[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            b2[offset + n2] = this.buffer.get((int)this.index(i2, j2) + n2) & 0xFF;
        }
        return this;
    }

    @Override
    public int get(long i2, long j2, long k2) {
        return this.buffer.get((int)this.index(i2, j2, k2)) & 0xFF;
    }

    @Override
    public int get(long ... indices) {
        return this.buffer.get((int)this.index(indices)) & 0xFF;
    }

    @Override
    public UByteIndexer get(long[] indices, int[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            b2[offset + n2] = this.buffer.get((int)this.index(indices) + n2) & 0xFF;
        }
        return this;
    }

    @Override
    public UByteIndexer put(long i2, int b2) {
        this.buffer.put((int)this.index(i2), (byte)b2);
        return this;
    }

    @Override
    public UByteIndexer put(long i2, int[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(i2) + n2, (byte)b2[offset + n2]);
        }
        return this;
    }

    @Override
    public UByteIndexer put(long i2, long j2, int b2) {
        this.buffer.put((int)this.index(i2, j2), (byte)b2);
        return this;
    }

    @Override
    public UByteIndexer put(long i2, long j2, int[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(i2, j2) + n2, (byte)b2[offset + n2]);
        }
        return this;
    }

    @Override
    public UByteIndexer put(long i2, long j2, long k2, int b2) {
        this.buffer.put((int)this.index(i2, j2, k2), (byte)b2);
        return this;
    }

    @Override
    public UByteIndexer put(long[] indices, int b2) {
        this.buffer.put((int)this.index(indices), (byte)b2);
        return this;
    }

    @Override
    public UByteIndexer put(long[] indices, int[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(indices) + n2, (byte)b2[offset + n2]);
        }
        return this;
    }

    @Override
    public void release() {
        this.buffer = null;
    }
}

