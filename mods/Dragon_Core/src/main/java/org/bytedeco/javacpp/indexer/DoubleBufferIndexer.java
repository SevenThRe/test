/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import java.nio.Buffer;
import java.nio.DoubleBuffer;
import org.bytedeco.javacpp.indexer.DoubleIndexer;
import org.bytedeco.javacpp.indexer.Index;

public class DoubleBufferIndexer
extends DoubleIndexer {
    protected DoubleBuffer buffer;

    public DoubleBufferIndexer(DoubleBuffer buffer) {
        this(buffer, Index.create((long)buffer.limit()));
    }

    public DoubleBufferIndexer(DoubleBuffer buffer, long ... sizes) {
        this(buffer, Index.create(sizes));
    }

    public DoubleBufferIndexer(DoubleBuffer buffer, long[] sizes, long[] strides) {
        this(buffer, Index.create(sizes, strides));
    }

    public DoubleBufferIndexer(DoubleBuffer buffer, Index index) {
        super(index);
        this.buffer = buffer;
    }

    @Override
    public Buffer buffer() {
        return this.buffer;
    }

    public DoubleIndexer reindex(Index index) {
        return new DoubleBufferIndexer(this.buffer, index);
    }

    @Override
    public double get(long i2) {
        return this.buffer.get((int)this.index(i2));
    }

    @Override
    public DoubleIndexer get(long i2, double[] d2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            d2[offset + n2] = this.buffer.get((int)this.index(i2) + n2);
        }
        return this;
    }

    @Override
    public double get(long i2, long j2) {
        return this.buffer.get((int)this.index(i2, j2));
    }

    @Override
    public DoubleIndexer get(long i2, long j2, double[] d2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            d2[offset + n2] = this.buffer.get((int)this.index(i2, j2) + n2);
        }
        return this;
    }

    @Override
    public double get(long i2, long j2, long k2) {
        return this.buffer.get((int)this.index(i2, j2, k2));
    }

    @Override
    public double get(long ... indices) {
        return this.buffer.get((int)this.index(indices));
    }

    @Override
    public DoubleIndexer get(long[] indices, double[] d2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            d2[offset + n2] = this.buffer.get((int)this.index(indices) + n2);
        }
        return this;
    }

    @Override
    public DoubleIndexer put(long i2, double d2) {
        this.buffer.put((int)this.index(i2), d2);
        return this;
    }

    @Override
    public DoubleIndexer put(long i2, double[] d2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(i2) + n2, d2[offset + n2]);
        }
        return this;
    }

    @Override
    public DoubleIndexer put(long i2, long j2, double d2) {
        this.buffer.put((int)this.index(i2, j2), d2);
        return this;
    }

    @Override
    public DoubleIndexer put(long i2, long j2, double[] d2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(i2, j2) + n2, d2[offset + n2]);
        }
        return this;
    }

    @Override
    public DoubleIndexer put(long i2, long j2, long k2, double d2) {
        this.buffer.put((int)this.index(i2, j2, k2), d2);
        return this;
    }

    @Override
    public DoubleIndexer put(long[] indices, double d2) {
        this.buffer.put((int)this.index(indices), d2);
        return this;
    }

    @Override
    public DoubleIndexer put(long[] indices, double[] d2, int offset, int length) {
        for (int n2 = 0; n2 < length; ++n2) {
            this.buffer.put((int)this.index(indices) + n2, d2[offset + n2]);
        }
        return this;
    }

    @Override
    public void release() {
        this.buffer = null;
    }
}

