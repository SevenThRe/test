/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin.jvm.internal;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.collections.ByteIterator;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import java.util.NoSuchElementException;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0005\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0096\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2={"Linfo/journeymap/shaded/kotlin/kotlin/jvm/internal/ArrayByteIterator;", "Linfo/journeymap/shaded/kotlin/kotlin/collections/ByteIterator;", "array", "", "([B)V", "index", "", "hasNext", "", "nextByte", "", "info.journeymap.shaded.kotlin.kotlin-stdlib"})
final class ArrayByteIterator
extends ByteIterator {
    private int index;
    private final byte[] array;

    @Override
    public boolean hasNext() {
        return this.index < this.array.length;
    }

    @Override
    public byte nextByte() {
        byte by;
        try {
            int by2 = this.index;
            this.index = by2 + 1;
            by = this.array[by2];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            --this.index;
            throw (Throwable)new NoSuchElementException(e.getMessage());
        }
        return by;
    }

    public ArrayByteIterator(@NotNull byte[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        this.array = array;
    }
}

