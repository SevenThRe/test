/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin.ranges;

import info.journeymap.shaded.kotlin.kotlin.ExperimentalUnsignedTypes;
import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.SinceKotlin;
import info.journeymap.shaded.kotlin.kotlin.ULong;
import info.journeymap.shaded.kotlin.kotlin.UnsignedKt;
import info.journeymap.shaded.kotlin.kotlin.collections.ULongIterator;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.DefaultConstructorMarker;
import java.util.NoSuchElementException;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B \u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\t\u0010\n\u001a\u00020\u000bH\u0096\u0002J\u0010\u0010\r\u001a\u00020\u0003H\u0016\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eR\u0013\u0010\b\u001a\u00020\u0003X\u0082\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0013\u0010\f\u001a\u00020\u0003X\u0082\u000e\u00f8\u0001\u0000\u00a2\u0006\u0004\n\u0002\u0010\tR\u0013\u0010\u0005\u001a\u00020\u0003X\u0082\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\n\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2={"Linfo/journeymap/shaded/kotlin/kotlin/ranges/ULongProgressionIterator;", "Linfo/journeymap/shaded/kotlin/kotlin/collections/ULongIterator;", "first", "Linfo/journeymap/shaded/kotlin/kotlin/ULong;", "last", "step", "", "(JJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "finalElement", "J", "hasNext", "", "next", "nextULong", "()J", "info.journeymap.shaded.kotlin.kotlin-stdlib"})
@SinceKotlin(version="1.3")
@ExperimentalUnsignedTypes
final class ULongProgressionIterator
extends ULongIterator {
    private final long finalElement;
    private boolean hasNext;
    private final long step;
    private long next;

    @Override
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override
    public long nextULong() {
        long value = this.next;
        if (value == this.finalElement) {
            if (!this.hasNext) {
                throw (Throwable)new NoSuchElementException();
            }
            this.hasNext = false;
        } else {
            long l;
            ULongProgressionIterator uLongProgressionIterator = this;
            long l2 = uLongProgressionIterator.next;
            long l3 = this.step;
            ULongProgressionIterator uLongProgressionIterator2 = uLongProgressionIterator;
            boolean bl = false;
            uLongProgressionIterator2.next = l = ULong.constructor-impl(l2 + l3);
        }
        return value;
    }

    private ULongProgressionIterator(long first, long last, long step) {
        long l;
        boolean bl;
        ULongProgressionIterator uLongProgressionIterator;
        int n;
        boolean bl2;
        ULongProgressionIterator uLongProgressionIterator2;
        long l2;
        this.finalElement = last;
        ULongProgressionIterator uLongProgressionIterator3 = this;
        if (step > 0L) {
            l2 = first;
            uLongProgressionIterator2 = uLongProgressionIterator3;
            bl2 = false;
            n = UnsignedKt.ulongCompare(l2, last);
            uLongProgressionIterator = uLongProgressionIterator2;
            bl = n <= 0;
        } else {
            l2 = first;
            uLongProgressionIterator2 = uLongProgressionIterator3;
            bl2 = false;
            n = UnsignedKt.ulongCompare(l2, last);
            uLongProgressionIterator = uLongProgressionIterator2;
            bl = n >= 0;
        }
        uLongProgressionIterator.hasNext = bl;
        l2 = step;
        uLongProgressionIterator2 = this;
        bl2 = false;
        uLongProgressionIterator2.step = l = ULong.constructor-impl(l2);
        this.next = this.hasNext ? first : this.finalElement;
    }

    public /* synthetic */ ULongProgressionIterator(long first, long last, long step, DefaultConstructorMarker $constructor_marker) {
        this(first, last, step);
    }
}

