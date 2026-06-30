/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.indexer.Index;

public class OneIndex
extends Index {
    public OneIndex(long size) {
        super(size);
    }

    @Override
    public long index(long i2) {
        return i2;
    }

    @Override
    public long index(long i2, long j2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long index(long i2, long j2, long k2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long index(long ... indices) {
        if (indices.length == 1) {
            return indices[0];
        }
        throw new UnsupportedOperationException();
    }
}

