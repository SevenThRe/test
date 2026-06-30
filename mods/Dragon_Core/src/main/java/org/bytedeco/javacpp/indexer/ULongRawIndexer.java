/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import java.math.BigInteger;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.Raw;
import org.bytedeco.javacpp.indexer.ULongIndexer;

public class ULongRawIndexer
extends ULongIndexer {
    protected static final Raw RAW = Raw.getInstance();
    protected LongPointer pointer;
    final long base;
    final long size;

    public ULongRawIndexer(LongPointer pointer) {
        this(pointer, Index.create(pointer.limit() - pointer.position()));
    }

    public ULongRawIndexer(LongPointer pointer, long ... sizes) {
        this(pointer, Index.create(sizes));
    }

    public ULongRawIndexer(LongPointer pointer, long[] sizes, long[] strides) {
        this(pointer, Index.create(sizes, strides));
    }

    public ULongRawIndexer(LongPointer pointer, Index index) {
        super(index);
        this.pointer = pointer;
        this.base = pointer.address() + pointer.position() * 8L;
        this.size = pointer.limit() - pointer.position();
    }

    @Override
    public Pointer pointer() {
        return this.pointer;
    }

    public ULongIndexer reindex(Index index) {
        return new ULongRawIndexer(this.pointer, index);
    }

    public BigInteger getRaw(long i2) {
        return ULongRawIndexer.toBigInteger(RAW.getLong(this.base + ULongRawIndexer.checkIndex(i2, this.size) * 8L));
    }

    @Override
    public BigInteger get(long i2) {
        return this.getRaw(this.index(i2));
    }

    @Override
    public ULongIndexer get(long i2, BigInteger[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            l2[offset + n2] = this.getRaw(this.index(i2) + (long)n2);
        }
        return this;
    }

    @Override
    public BigInteger get(long i2, long j2) {
        return this.getRaw(this.index(i2, j2));
    }

    @Override
    public ULongIndexer get(long i2, long j2, BigInteger[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            l2[offset + n2] = this.getRaw(this.index(i2, j2) + (long)n2);
        }
        return this;
    }

    @Override
    public BigInteger get(long i2, long j2, long k2) {
        return this.getRaw(this.index(i2, j2, k2));
    }

    @Override
    public BigInteger get(long ... indices) {
        return this.getRaw(this.index(indices));
    }

    @Override
    public ULongIndexer get(long[] indices, BigInteger[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            l2[offset + n2] = this.getRaw(this.index(indices) + (long)n2);
        }
        return this;
    }

    public ULongIndexer putRaw(long i2, BigInteger l2) {
        RAW.putLong(this.base + ULongRawIndexer.checkIndex(i2, this.size) * 8L, ULongRawIndexer.fromBigInteger(l2));
        return this;
    }

    @Override
    public ULongIndexer put(long i2, BigInteger l2) {
        return this.putRaw(this.index(i2), l2);
    }

    @Override
    public ULongIndexer put(long i2, BigInteger[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(i2) + (long)n2, l2[offset + n2]);
        }
        return this;
    }

    @Override
    public ULongIndexer put(long i2, long j2, BigInteger l2) {
        this.putRaw(this.index(i2, j2), l2);
        return this;
    }

    @Override
    public ULongIndexer put(long i2, long j2, BigInteger[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(i2, j2) + (long)n2, l2[offset + n2]);
        }
        return this;
    }

    @Override
    public ULongIndexer put(long i2, long j2, long k2, BigInteger l2) {
        this.putRaw(this.index(i2, j2, k2), l2);
        return this;
    }

    @Override
    public ULongIndexer put(long[] indices, BigInteger l2) {
        this.putRaw(this.index(indices), l2);
        return this;
    }

    @Override
    public ULongIndexer put(long[] indices, BigInteger[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(indices) + (long)n2, l2[offset + n2]);
        }
        return this;
    }

    @Override
    public void release() {
        this.pointer = null;
    }
}

