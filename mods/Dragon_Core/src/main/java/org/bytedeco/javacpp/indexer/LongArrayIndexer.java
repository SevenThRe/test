/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.LongIndexer;

public class LongArrayIndexer
extends LongIndexer {
    protected long[] array;

    public LongArrayIndexer(long[] array) {
        this(array, Index.create((long)array.length));
    }

    public LongArrayIndexer(long[] array, long ... sizes) {
        this(array, Index.create(sizes));
    }

    public LongArrayIndexer(long[] array, long[] sizes, long[] strides) {
        this(array, Index.create(sizes, strides));
    }

    public LongArrayIndexer(long[] array, Index index) {
        super(index);
        this.array = array;
    }

    public long[] array() {
        return this.array;
    }

    public LongIndexer reindex(Index index) {
        return new LongArrayIndexer(this.array, index);
    }

    @Override
    public long get(long i2) {
        return this.array[(int)this.index(i2)];
    }

    @Override
    public LongIndexer get(long i2, long[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            l2[offset + n2] = this.array[(int)this.index(i2) + n2];
        }
        return this;
    }

    @Override
    public long get(long i2, long j2) {
        return this.array[(int)this.index(i2, j2)];
    }

    @Override
    public LongIndexer get(long i2, long j2, long[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            l2[offset + n2] = this.array[(int)this.index(i2, j2) + n2];
        }
        return this;
    }

    @Override
    public long get(long i2, long j2, long k2) {
        return this.array[(int)this.index(i2, j2, k2)];
    }

    @Override
    public long get(long ... indices) {
        return this.array[(int)this.index(indices)];
    }

    @Override
    public LongIndexer get(long[] indices, long[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            l2[offset + n2] = this.array[(int)this.index(indices) + n2];
        }
        return this;
    }

    @Override
    public LongIndexer put(long i2, long l2) {
        this.array[(int)this.index((long)i2)] = l2;
        return this;
    }

    @Override
    public LongIndexer put(long i2, long[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long)i2) + n2] = l2[offset + n2];
        }
        return this;
    }

    @Override
    public LongIndexer put(long i2, long j2, long l2) {
        this.array[(int)this.index((long)i2, (long)j2)] = l2;
        return this;
    }

    @Override
    public LongIndexer put(long i2, long j2, long[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long)i2, (long)j2) + n2] = l2[offset + n2];
        }
        return this;
    }

    @Override
    public LongIndexer put(long i2, long j2, long k2, long l2) {
        this.array[(int)this.index((long)i2, (long)j2, (long)k2)] = l2;
        return this;
    }

    @Override
    public LongIndexer put(long[] indices, long l2) {
        this.array[(int)this.index((long[])indices)] = l2;
        return this;
    }

    @Override
    public LongIndexer put(long[] indices, long[] l2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long[])indices) + n2] = l2[offset + n2];
        }
        return this;
    }

    @Override
    public void release() {
        this.array = null;
    }
}

