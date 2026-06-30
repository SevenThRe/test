/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin.sequences;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.jvm.functions.Function1;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.DefaultConstructorMarker;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.kotlin.kotlin.sequences.Sequence;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import info.journeymap.shaded.org.jetbrains.annotations.Nullable;
import java.util.Iterator;
import java.util.NoSuchElementException;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B1\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\u0007\u00a2\u0006\u0002\u0010\bJ\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0096\u0002R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2={"Linfo/journeymap/shaded/kotlin/kotlin/sequences/FilteringSequence;", "T", "Linfo/journeymap/shaded/kotlin/kotlin/sequences/Sequence;", "sequence", "sendWhen", "", "predicate", "Linfo/journeymap/shaded/kotlin/kotlin/Function1;", "(Lkotlin/sequences/Sequence;ZLkotlin/jvm/functions/Function1;)V", "iterator", "", "info.journeymap.shaded.kotlin.kotlin-stdlib"})
public final class FilteringSequence<T>
implements Sequence<T> {
    private final Sequence<T> sequence;
    private final boolean sendWhen;
    private final Function1<T, Boolean> predicate;

    @Override
    @NotNull
    public Iterator<T> iterator() {
        return new Iterator<T>(this){
            @NotNull
            private final Iterator<T> iterator;
            private int nextState;
            @Nullable
            private T nextItem;
            final /* synthetic */ FilteringSequence this$0;

            @NotNull
            public final Iterator<T> getIterator() {
                return this.iterator;
            }

            public final int getNextState() {
                return this.nextState;
            }

            public final void setNextState(int n) {
                this.nextState = n;
            }

            @Nullable
            public final T getNextItem() {
                return this.nextItem;
            }

            public final void setNextItem(@Nullable T t) {
                this.nextItem = t;
            }

            private final void calcNext() {
                while (this.iterator.hasNext()) {
                    T item = this.iterator.next();
                    if ((Boolean)FilteringSequence.access$getPredicate$p(this.this$0).invoke(item) != FilteringSequence.access$getSendWhen$p(this.this$0)) continue;
                    this.nextItem = item;
                    this.nextState = 1;
                    return;
                }
                this.nextState = 0;
            }

            public T next() {
                if (this.nextState == -1) {
                    this.calcNext();
                }
                if (this.nextState == 0) {
                    throw (Throwable)new NoSuchElementException();
                }
                T result = this.nextItem;
                this.nextItem = null;
                this.nextState = -1;
                return result;
            }

            public boolean hasNext() {
                if (this.nextState == -1) {
                    this.calcNext();
                }
                return this.nextState == 1;
            }
            {
                this.this$0 = $outer;
                this.iterator = FilteringSequence.access$getSequence$p($outer).iterator();
                this.nextState = -1;
            }

            public void remove() {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }
        };
    }

    public FilteringSequence(@NotNull Sequence<? extends T> sequence, boolean sendWhen, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(sequence, "sequence");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        this.sequence = sequence;
        this.sendWhen = sendWhen;
        this.predicate = predicate;
    }

    public /* synthetic */ FilteringSequence(Sequence sequence, boolean bl, Function1 function1, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 2) != 0) {
            bl = true;
        }
        this(sequence, bl, function1);
    }

    public static final /* synthetic */ boolean access$getSendWhen$p(FilteringSequence $this) {
        return $this.sendWhen;
    }

    public static final /* synthetic */ Function1 access$getPredicate$p(FilteringSequence $this) {
        return $this.predicate;
    }

    public static final /* synthetic */ Sequence access$getSequence$p(FilteringSequence $this) {
        return $this.sequence;
    }
}

