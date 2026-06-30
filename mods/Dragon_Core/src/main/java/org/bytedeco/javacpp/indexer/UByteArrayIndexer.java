/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.UByteIndexer;

public class UByteArrayIndexer
extends UByteIndexer {
    protected byte[] array;

    public UByteArrayIndexer(byte[] array) {
        this(array, Index.create((long)array.length));
    }

    public UByteArrayIndexer(byte[] array, long ... sizes) {
        this(array, Index.create(sizes));
    }

    public UByteArrayIndexer(byte[] array, long[] sizes, long[] strides) {
        this(array, Index.create(sizes, strides));
    }

    public UByteArrayIndexer(byte[] array, Index index) {
        super(index);
        this.array = array;
    }

    public byte[] array() {
        return this.array;
    }

    public UByteIndexer reindex(Index index) {
        return new UByteArrayIndexer(this.array, index);
    }

    @Override
    public int get(long i2) {
        return this.array[(int)this.index(i2)] & 0xFF;
    }

    @Override
    public UByteIndexer get(long i2, int[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            b2[offset + n2] = this.array[(int)this.index(i2) + n2] & 0xFF;
        }
        return this;
    }

    @Override
    public int get(long i2, long j2) {
        return this.array[(int)this.index(i2, j2)] & 0xFF;
    }

    @Override
    public UByteIndexer get(long i2, long j2, int[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            b2[offset + n2] = this.array[(int)this.index(i2, j2) + n2] & 0xFF;
        }
        return this;
    }

    @Override
    public int get(long i2, long j2, long k2) {
        return this.array[(int)this.index(i2, j2, k2)] & 0xFF;
    }

    @Override
    public int get(long ... indices) {
        return this.array[(int)this.index(indices)] & 0xFF;
    }

    @Override
    public UByteIndexer get(long[] indices, int[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            b2[offset + n2] = this.array[(int)this.index(indices) + n2] & 0xFF;
        }
        return this;
    }

    @Override
    public UByteIndexer put(long i2, int b2) {
        this.array[(int)this.index((long)i2)] = (byte)b2;
        return this;
    }

    @Override
    public UByteIndexer put(long i2, int[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long)i2) + n2] = (byte)b2[offset + n2];
        }
        return this;
    }

    @Override
    public UByteIndexer put(long i2, long j2, int b2) {
        this.array[(int)this.index((long)i2, (long)j2)] = (byte)b2;
        return this;
    }

    @Override
    public UByteIndexer put(long i2, long j2, int[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long)i2, (long)j2) + n2] = (byte)b2[offset + n2];
        }
        return this;
    }

    @Override
    public UByteIndexer put(long i2, long j2, long k2, int b2) {
        this.array[(int)this.index((long)i2, (long)j2, (long)k2)] = (byte)b2;
        return this;
    }

    @Override
    public UByteIndexer put(long[] indices, int b2) {
        this.array[(int)this.index((long[])indices)] = (byte)b2;
        return this;
    }

    @Override
    public UByteIndexer put(long[] indices, int[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long[])indices) + n2] = (byte)b2[offset + n2];
        }
        return this;
    }

    @Override
    public void release() {
        this.array = null;
    }
}

