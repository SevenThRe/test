/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import java.math.BigInteger;
import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.ULongIndexer;

public class ULongArrayIndexer
extends ULongIndexer {
    protected long[] array;

    public ULongArrayIndexer(long[] array) {
        this(array, Index.create((long)array.length));
    }

    public ULongArrayIndexer(long[] array, long ... sizes) {
        this(array, Index.create(sizes));
    }

    public ULongArrayIndexer(long[] array, long[] sizes, long[] strides) {
        this(array, Index.create(sizes, strides));
    }

    public ULongArrayIndexer(long[] array, Index index) {
        super(index);
        this.array = array;
    }

    public long[] array() {
        return this.array;
    }

    public ULongIndexer reindex(Index index) {
        return new ULongArrayIndexer(this.array, index);
    }

    @Override
    public BigInteger get(long i2) {
        return ULongArrayIndexer.toBigInteger(this.array[(int)this.index(i2)]);
    }

    @Override
    public ULongIndexer get(long i2, BigInteger[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            l2[offset + n2] = ULongArrayIndexer.toBigInteger(this.array[(int)this.index(i2) + n2]);
        }
        return this;
    }

    @Override
    public BigInteger get(long i2, long j2) {
        return ULongArrayIndexer.toBigInteger(this.array[(int)this.index(i2, j2)]);
    }

    @Override
    public ULongIndexer get(long i2, long j2, BigInteger[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            l2[offset + n2] = ULongArrayIndexer.toBigInteger(this.array[(int)this.index(i2, j2) + n2]);
        }
        return this;
    }

    @Override
    public BigInteger get(long i2, long j2, long k2) {
        return ULongArrayIndexer.toBigInteger(this.array[(int)this.index(i2, j2, k2)]);
    }

    @Override
    public BigInteger get(long ... indices) {
        return ULongArrayIndexer.toBigInteger(this.array[(int)this.index(indices)]);
    }

    @Override
    public ULongIndexer get(long[] indices, BigInteger[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            l2[offset + n2] = ULongArrayIndexer.toBigInteger(this.array[(int)this.index(indices) + n2]);
        }
        return this;
    }

    @Override
    public ULongIndexer put(long i2, BigInteger l2) {
        this.array[(int)this.index((long)i2)] = ULongArrayIndexer.fromBigInteger(l2);
        return this;
    }

    @Override
    public ULongIndexer put(long i2, BigInteger[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long)i2) + n2] = ULongArrayIndexer.fromBigInteger(l2[offset + n2]);
        }
        return this;
    }

    @Override
    public ULongIndexer put(long i2, long j2, BigInteger l2) {
        this.array[(int)this.index((long)i2, (long)j2)] = ULongArrayIndexer.fromBigInteger(l2);
        return this;
    }

    @Override
    public ULongIndexer put(long i2, long j2, BigInteger[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long)i2, (long)j2) + n2] = ULongArrayIndexer.fromBigInteger(l2[offset + n2]);
        }
        return this;
    }

    @Override
    public ULongIndexer put(long i2, long j2, long k2, BigInteger l2) {
        this.array[(int)this.index((long)i2, (long)j2, (long)k2)] = ULongArrayIndexer.fromBigInteger(l2);
        return this;
    }

    @Override
    public ULongIndexer put(long[] indices, BigInteger l2) {
        this.array[(int)this.index((long[])indices)] = ULongArrayIndexer.fromBigInteger(l2);
        return this;
    }

    @Override
    public ULongIndexer put(long[] indices, BigInteger[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long[])indices) + n2] = ULongArrayIndexer.fromBigInteger(l2[offset + n2]);
        }
        return this;
    }

    @Override
    public void release() {
        this.array = null;
    }
}

