/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.indexer.CharIndexer;
import org.bytedeco.javacpp.indexer.Index;

public class CharArrayIndexer
extends CharIndexer {
    protected char[] array;

    public CharArrayIndexer(char[] array) {
        this(array, Index.create((long)array.length));
    }

    public CharArrayIndexer(char[] array, long ... sizes) {
        this(array, Index.create(sizes));
    }

    public CharArrayIndexer(char[] array, long[] sizes, long[] strides) {
        this(array, Index.create(sizes, strides));
    }

    public CharArrayIndexer(char[] array, Index index) {
        super(index);
        this.array = array;
    }

    public char[] array() {
        return this.array;
    }

    public CharIndexer reindex(Index index) {
        return new CharArrayIndexer(this.array, index);
    }

    @Override
    public char get(long i2) {
        return this.array[(int)this.index(i2)];
    }

    @Override
    public CharIndexer get(long i2, char[] c2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            c2[offset + n2] = this.array[(int)this.index(i2) + n2];
        }
        return this;
    }

    @Override
    public char get(long i2, long j2) {
        return this.array[(int)this.index(i2, j2)];
    }

    @Override
    public CharIndexer get(long i2, long j2, char[] c2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            c2[offset + n2] = this.array[(int)this.index(i2, j2) + n2];
        }
        return this;
    }

    @Override
    public char get(long i2, long j2, long k2) {
        return this.array[(int)this.index(i2, j2, k2)];
    }

    @Override
    public char get(long ... indices) {
        return this.array[(int)this.index(indices)];
    }

    @Override
    public CharIndexer get(long[] indices, char[] c2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            c2[offset + n2] = this.array[(int)this.index(indices) + n2];
        }
        return this;
    }

    @Override
    public CharIndexer put(long i2, char c2) {
        this.array[(int)this.index((long)i2)] = c2;
        return this;
    }

    @Override
    public CharIndexer put(long i2, char[] c2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long)i2) + n2] = c2[offset + n2];
        }
        return this;
    }

    @Override
    public CharIndexer put(long i2, long j2, char c2) {
        this.array[(int)this.index((long)i2, (long)j2)] = c2;
        return this;
    }

    @Override
    public CharIndexer put(long i2, long j2, char[] c2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long)i2, (long)j2) + n2] = c2[offset + n2];
        }
        return this;
    }

    @Override
    public CharIndexer put(long i2, long j2, long k2, char c2) {
        this.array[(int)this.index((long)i2, (long)j2, (long)k2)] = c2;
        return this;
    }

    @Override
    public CharIndexer put(long[] indices, char c2) {
        this.array[(int)this.index((long[])indices)] = c2;
        return this;
    }

    @Override
    public CharIndexer put(long[] indices, char[] c2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.array[(int)this.index((long[])indices) + n2] = c2[offset + n2];
        }
        return this;
    }

    @Override
    public void release() {
        this.array = null;
    }
}

