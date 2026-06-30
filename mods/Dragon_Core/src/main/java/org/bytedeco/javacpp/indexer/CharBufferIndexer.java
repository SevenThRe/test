/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import java.nio.Buffer;
import java.nio.CharBuffer;
import org.bytedeco.javacpp.indexer.CharIndexer;
import org.bytedeco.javacpp.indexer.Index;

public class CharBufferIndexer
extends CharIndexer {
    protected CharBuffer buffer;

    public CharBufferIndexer(CharBuffer buffer) {
        this(buffer, Index.create((long)buffer.limit()));
    }

    public CharBufferIndexer(CharBuffer buffer, long ... sizes) {
        this(buffer, Index.create(sizes));
    }

    public CharBufferIndexer(CharBuffer buffer, long[] sizes, long[] strides) {
        this(buffer, Index.create(sizes, strides));
    }

    public CharBufferIndexer(CharBuffer buffer, Index index) {
        super(index);
        this.buffer = buffer;
    }

    @Override
    public Buffer buffer() {
        return this.buffer;
    }

    public CharIndexer reindex(Index index) {
        return new CharBufferIndexer(this.buffer, index);
    }

    @Override
    public char get(long i2) {
        return this.buffer.get((int)this.index(i2));
    }

    @Override
    public CharIndexer get(long i2, char[] c2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            c2[offset + n2] = this.buffer.get((int)this.index(i2) + n2);
        }
        return this;
    }

    @Override
    public char get(long i2, long j2) {
        return this.buffer.get((int)this.index(i2, j2));
    }

    @Override
    public CharIndexer get(long i2, long j2, char[] c2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            c2[offset + n2] = this.buffer.get((int)this.index(i2, j2) + n2);
        }
        return this;
    }

    @Override
    public char get(long i2, long j2, long k2) {
        return this.buffer.get((int)this.index(i2, j2, k2));
    }

    @Override
    public char get(long ... indices) {
        return this.buffer.get((int)this.index(indices));
    }

    @Override
    public CharIndexer get(long[] indices, char[] c2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            c2[offset + n2] = this.buffer.get((int)this.index(indices) + n2);
        }
        return this;
    }

    @Override
    public CharIndexer put(long i2, char c2) {
        this.buffer.put((int)this.index(i2), c2);
        return this;
    }

    @Override
    public CharIndexer put(long i2, char[] c2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(i2) + n2, c2[offset + n2]);
        }
        return this;
    }

    @Override
    public CharIndexer put(long i2, long j2, char c2) {
        this.buffer.put((int)this.index(i2, j2), c2);
        return this;
    }

    @Override
    public CharIndexer put(long i2, long j2, char[] c2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(i2, j2) + n2, c2[offset + n2]);
        }
        return this;
    }

    @Override
    public CharIndexer put(long i2, long j2, long k2, char c2) {
        this.buffer.put((int)this.index(i2, j2, k2), c2);
        return this;
    }

    @Override
    public CharIndexer put(long[] indices, char c2) {
        this.buffer.put((int)this.index(indices), c2);
        return this;
    }

    @Override
    public CharIndexer put(long[] indices, char[] c2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(indices) + n2, c2[offset + n2]);
        }
        return this;
    }

    @Override
    public void release() {
        this.buffer = null;
    }
}

