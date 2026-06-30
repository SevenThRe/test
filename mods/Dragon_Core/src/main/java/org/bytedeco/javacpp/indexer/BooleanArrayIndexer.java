/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.indexer.BooleanIndexer;
import org.bytedeco.javacpp.indexer.Index;

public class BooleanArrayIndexer
extends BooleanIndexer {
    protected boolean[] array;

    public BooleanArrayIndexer(boolean[] array) {
        this(array, Index.create((long)array.length));
    }

    public BooleanArrayIndexer(boolean[] array, long ... sizes) {
        this(array, Index.create(sizes));
    }

    public BooleanArrayIndexer(boolean[] array, long[] sizes, long[] strides) {
        this(array, Index.create(sizes, strides));
    }

    public BooleanArrayIndexer(boolean[] array, Index index) {
        super(index);
        this.array = array;
    }

    public boolean[] array() {
        return this.array;
    }

    public BooleanIndexer reindex(Index index) {
        return new BooleanArrayIndexer(this.array, index);
    }

    @Override
    public boolean get(long i2) {
        return this.array[(int)this.index(i2)];
    }

    @Override
    public BooleanIndexer get(long i2, boolean[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            b2[offset + n2] = this.array[(int)this.index(i2) + n2];
        }
        return this;
    }

    @Override
    public boolean get(long i2, long j2) {
        return this.array[(int)this.index(i2, j2)];
    }

    @Override
    public BooleanIndexer get(long i2, long j2, boolean[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            b2[offset + n2] = this.array[(int)this.index(i2, j2) + n2];
        }
        return this;
    }

    @Override
    public boolean get(long i2, long j2, long k2) {
        return this.array[(int)this.index(i2, j2, k2)];
    }

    @Override
    public boolean get(long ... indices) {
        return this.array[(int)this.index(indices)];
    }

    @Override
    public BooleanIndexer get(long[] indices, boolean[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            b2[offset + n2] = this.array[(int)this.index(indices) + n2];
        }
        return this;
    }

    @Override
    public BooleanIndexer put(long i2, boolean b2) {
        this.array[(int)this.index((long)i2)] = b2;
        return this;
    }

    @Override
    public BooleanIndexer put(long i2, boolean[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long)i2) + n2] = b2[offset + n2];
        }
        return this;
    }

    @Override
    public BooleanIndexer put(long i2, long j2, boolean b2) {
        this.array[(int)this.index((long)i2, (long)j2)] = b2;
        return this;
    }

    @Override
    public BooleanIndexer put(long i2, long j2, boolean[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long)i2, (long)j2) + n2] = b2[offset + n2];
        }
        return this;
    }

    @Override
    public BooleanIndexer put(long i2, long j2, long k2, boolean b2) {
        this.array[(int)this.index((long)i2, (long)j2, (long)k2)] = b2;
        return this;
    }

    @Override
    public BooleanIndexer put(long[] indices, boolean b2) {
        this.array[(int)this.index((long[])indices)] = b2;
        return this;
    }

    @Override
    public BooleanIndexer put(long[] indices, boolean[] b2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long[])indices) + n2] = b2[offset + n2];
        }
        return this;
    }

    @Override
    public void release() {
        this.array = null;
    }
}

