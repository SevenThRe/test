/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import java.math.BigInteger;
import java.nio.Buffer;
import java.nio.LongBuffer;
import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.ULongIndexer;

public class ULongBufferIndexer
extends ULongIndexer {
    protected LongBuffer buffer;

    public ULongBufferIndexer(LongBuffer buffer) {
        this(buffer, Index.create((long)buffer.limit()));
    }

    public ULongBufferIndexer(LongBuffer buffer, long ... sizes) {
        this(buffer, Index.create(sizes));
    }

    public ULongBufferIndexer(LongBuffer buffer, long[] sizes, long[] strides) {
        this(buffer, Index.create(sizes, strides));
    }

    public ULongBufferIndexer(LongBuffer buffer, Index index) {
        super(index);
        this.buffer = buffer;
    }

    @Override
    public Buffer buffer() {
        return this.buffer;
    }

    public ULongIndexer reindex(Index index) {
        return new ULongBufferIndexer(this.buffer, index);
    }

    @Override
    public BigInteger get(long i2) {
        return ULongBufferIndexer.toBigInteger(this.buffer.get((int)this.index(i2)));
    }

    @Override
    public ULongIndexer get(long i2, BigInteger[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            l2[offset + n2] = ULongBufferIndexer.toBigInteger(this.buffer.get((int)this.index(i2) + n2));
        }
        return this;
    }

    @Override
    public BigInteger get(long i2, long j2) {
        return ULongBufferIndexer.toBigInteger(this.buffer.get((int)this.index(i2, j2)));
    }

    @Override
    public ULongIndexer get(long i2, long j2, BigInteger[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            l2[offset + n2] = ULongBufferIndexer.toBigInteger(this.buffer.get((int)this.index(i2, j2) + n2));
        }
        return this;
    }

    @Override
    public BigInteger get(long i2, long j2, long k2) {
        return ULongBufferIndexer.toBigInteger(this.buffer.get((int)this.index(i2, j2, k2)));
    }

    @Override
    public BigInteger get(long ... indices) {
        return ULongBufferIndexer.toBigInteger(this.buffer.get((int)this.index(indices)));
    }

    @Override
    public ULongIndexer get(long[] indices, BigInteger[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            l2[offset + n2] = ULongBufferIndexer.toBigInteger(this.buffer.get((int)this.index(indices) + n2));
        }
        return this;
    }

    @Override
    public ULongIndexer put(long i2, BigInteger l2) {
        this.buffer.put((int)this.index(i2), ULongBufferIndexer.fromBigInteger(l2));
        return this;
    }

    @Override
    public ULongIndexer put(long i2, BigInteger[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(i2) + n2, ULongBufferIndexer.fromBigInteger(l2[offset + n2]));
        }
        return this;
    }

    @Override
    public ULongIndexer put(long i2, long j2, BigInteger l2) {
        this.buffer.put((int)this.index(i2, j2), ULongBufferIndexer.fromBigInteger(l2));
        return this;
    }

    @Override
    public ULongIndexer put(long i2, long j2, BigInteger[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(i2, j2) + n2, ULongBufferIndexer.fromBigInteger(l2[offset + n2]));
        }
        return this;
    }

    @Override
    public ULongIndexer put(long i2, long j2, long k2, BigInteger l2) {
        this.buffer.put((int)this.index(i2, j2, k2), ULongBufferIndexer.fromBigInteger(l2));
        return this;
    }

    @Override
    public ULongIndexer put(long[] indices, BigInteger l2) {
        this.buffer.put((int)this.index(indices), ULongBufferIndexer.fromBigInteger(l2));
        return this;
    }

    @Override
    public ULongIndexer put(long[] indices, BigInteger[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(indices) + n2, ULongBufferIndexer.fromBigInteger(l2[offset + n2]));
        }
        return this;
    }

    @Override
    public void release() {
        this.buffer = null;
    }
}

