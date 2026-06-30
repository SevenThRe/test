/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import java.nio.Buffer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.indexer.Index;
import org.bytedeco.javacpp.indexer.Indexable;
import org.bytedeco.javacpp.indexer.StrideIndex;

public abstract class Indexer
implements AutoCloseable {
    @Deprecated
    protected long[] sizes;
    @Deprecated
    protected long[] strides;
    protected final Index index;
    protected Indexable indexable;

    @Override
    public void close() {
        this.release();
    }

    protected Indexer(Index index) {
        this.index = index;
        this.sizes = index.sizes();
        if (index instanceof StrideIndex) {
            this.strides = ((StrideIndex)index).strides();
        }
    }

    protected Indexer(long[] sizes, long[] strides) {
        this(Index.create(sizes, strides));
    }

    public int rank() {
        return this.index.rank();
    }

    public long[] sizes() {
        return this.index.sizes();
    }

    @Deprecated
    public long[] strides() {
        return this.strides;
    }

    public long size(int i2) {
        return this.index.size(i2);
    }

    @Deprecated
    public long stride(int i2) {
        return this.strides != null ? this.strides[i2] : -1L;
    }

    @Deprecated
    public long rows() {
        return this.sizes.length > 0 && this.sizes.length < 4 ? this.sizes[0] : -1L;
    }

    @Deprecated
    public long cols() {
        return this.sizes.length > 1 && this.sizes.length < 4 ? this.sizes[1] : -1L;
    }

    @Deprecated
    public long width() {
        return this.sizes.length > 1 && this.sizes.length < 4 ? this.sizes[1] : -1L;
    }

    @Deprecated
    public long height() {
        return this.sizes.length > 0 && this.sizes.length < 4 ? this.sizes[0] : -1L;
    }

    @Deprecated
    public long channels() {
        return this.sizes.length > 2 && this.sizes.length < 4 ? this.sizes[2] : -1L;
    }

    protected static final long checkIndex(long i2, long size) {
        if (i2 < 0L || i2 >= size) {
            throw new IndexOutOfBoundsException(Long.toString(i2));
        }
        return i2;
    }

    @Deprecated
    public static long[] strides(long ... sizes) {
        return StrideIndex.defaultStrides(sizes);
    }

    public long index(long i2) {
        return this.index.index(i2);
    }

    public long index(long i2, long j2) {
        return this.index.index(i2, j2);
    }

    public long index(long i2, long j2, long k2) {
        return this.index.index(i2, j2, k2);
    }

    public long index(long ... indices) {
        return this.index.index(indices);
    }

    public Indexable indexable() {
        return this.indexable;
    }

    public Indexer indexable(Indexable indexable) {
        this.indexable = indexable;
        return this;
    }

    public Object array() {
        return null;
    }

    public Buffer buffer() {
        return null;
    }

    public Pointer pointer() {
        return null;
    }

    public abstract void release();

    public abstract double getDouble(long ... var1);

    public abstract Indexer putDouble(long[] var1, double var2);

    public abstract <I extends Indexer> I reindex(Index var1);

    public String toString() {
        long rows = this.sizes.length > 0 ? this.sizes[0] : 1L;
        long cols = this.sizes.length > 1 ? this.sizes[1] : 1L;
        long channels = this.sizes.length > 2 ? this.sizes[2] : 1L;
        StringBuilder s2 = new StringBuilder(rows > 1L ? "\n[ " : "[ ");
        int i2 = 0;
        while ((long)i2 < rows) {
            int j2 = 0;
            while ((long)j2 < cols) {
                if (channels > 1L) {
                    s2.append("(");
                }
                int k2 = 0;
                while ((long)k2 < channels) {
                    double v2 = this.getDouble(i2, j2, k2);
                    s2.append((float)v2);
                    if ((long)k2 < channels - 1L) {
                        s2.append(", ");
                    }
                    ++k2;
                }
                if (channels > 1L) {
                    s2.append(")");
                }
                if ((long)j2 < cols - 1L) {
                    s2.append(", ");
                }
                ++j2;
            }
            if ((long)i2 < rows - 1L) {
                s2.append("\n  ");
            }
            ++i2;
        }
        s2.append(" ]");
        return s2.toString();
    }
}

