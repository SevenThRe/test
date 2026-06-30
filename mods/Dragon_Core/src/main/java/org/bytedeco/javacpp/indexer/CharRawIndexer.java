/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.CharPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.indexer.CharIndexer;
import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.Raw;

public class CharRawIndexer
extends CharIndexer {
    protected static final Raw RAW = Raw.getInstance();
    protected CharPointer pointer;
    final long base;
    final long size;

    public CharRawIndexer(CharPointer pointer) {
        this(pointer, Index.create(pointer.limit() - pointer.position()));
    }

    public CharRawIndexer(CharPointer pointer, long ... sizes) {
        this(pointer, Index.create(sizes));
    }

    public CharRawIndexer(CharPointer pointer, long[] sizes, long[] strides) {
        this(pointer, Index.create(sizes, strides));
    }

    public CharRawIndexer(CharPointer pointer, Index index) {
        super(index);
        this.pointer = pointer;
        this.base = pointer.address() + pointer.position() * 2L;
        this.size = pointer.limit() - pointer.position();
    }

    @Override
    public Pointer pointer() {
        return this.pointer;
    }

    public CharIndexer reindex(Index index) {
        return new CharRawIndexer(this.pointer, index);
    }

    public char getRaw(long i2) {
        return RAW.getChar(this.base + CharRawIndexer.checkIndex(i2, this.size) * 2L);
    }

    @Override
    public char get(long i2) {
        return this.getRaw(this.index(i2));
    }

    @Override
    public CharIndexer get(long i2, char[] c2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            c2[offset + n2] = this.getRaw(this.index(i2) + (long)n2);
        }
        return this;
    }

    @Override
    public char get(long i2, long j2) {
        return this.getRaw(this.index(i2, j2));
    }

    @Override
    public CharIndexer get(long i2, long j2, char[] c2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            c2[offset + n2] = this.getRaw(this.index(i2, j2) + (long)n2);
        }
        return this;
    }

    @Override
    public char get(long i2, long j2, long k2) {
        return this.getRaw(this.index(i2, j2, k2));
    }

    @Override
    public char get(long ... indices) {
        return this.getRaw(this.index(indices));
    }

    @Override
    public CharIndexer get(long[] indices, char[] c2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            c2[offset + n2] = this.getRaw(this.index(indices) + (long)n2);
        }
        return this;
    }

    public CharIndexer putRaw(long i2, char c2) {
        RAW.putChar(this.base + CharRawIndexer.checkIndex(i2, this.size) * 2L, c2);
        return this;
    }

    @Override
    public CharIndexer put(long i2, char c2) {
        return this.putRaw(this.index(i2), c2);
    }

    @Override
    public CharIndexer put(long i2, char[] c2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(i2) + (long)n2, c2[offset + n2]);
        }
        return this;
    }

    @Override
    public CharIndexer put(long i2, long j2, char c2) {
        this.putRaw(this.index(i2, j2), c2);
        return this;
    }

    @Override
    public CharIndexer put(long i2, long j2, char[] c2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(i2, j2) + (long)n2, c2[offset + n2]);
        }
        return this;
    }

    @Override
    public CharIndexer put(long i2, long j2, long k2, char c2) {
        this.putRaw(this.index(i2, j2, k2), c2);
        return this;
    }

    @Override
    public CharIndexer put(long[] indices, char c2) {
        this.putRaw(this.index(indices), c2);
        return this;
    }

    @Override
    public CharIndexer put(long[] indices, char[] c2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.putRaw(this.index(indices) + (long)n2, c2[offset + n2]);
        }
        return this;
    }

    @Override
    public void release() {
        this.pointer = null;
    }
}

