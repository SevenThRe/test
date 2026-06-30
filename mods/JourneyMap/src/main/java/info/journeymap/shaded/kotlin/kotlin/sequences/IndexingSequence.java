/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin.sequences;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.collections.CollectionsKt;
import info.journeymap.shaded.kotlin.kotlin.collections.IndexedValue;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.kotlin.kotlin.sequences.Sequence;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import java.util.Iterator;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010(\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u00a2\u0006\u0002\u0010\u0005J\u0015\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u0007H\u0096\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2={"Linfo/journeymap/shaded/kotlin/kotlin/sequences/IndexingSequence;", "T", "Linfo/journeymap/shaded/kotlin/kotlin/sequences/Sequence;", "Linfo/journeymap/shaded/kotlin/kotlin/collections/IndexedValue;", "sequence", "(Lkotlin/sequences/Sequence;)V", "iterator", "", "info.journeymap.shaded.kotlin.kotlin-stdlib"})
public final class IndexingSequence<T>
implements Sequence<IndexedValue<? extends T>> {
    private final Sequence<T> sequence;

    @Override
    @NotNull
    public Iterator<IndexedValue<T>> iterator() {
        return new Iterator<IndexedValue<? extends T>>(this){
            @NotNull
            private final Iterator<T> iterator;
            private int index;
            final /* synthetic */ IndexingSequence this$0;

            @NotNull
            public final Iterator<T> getIterator() {
                return this.iterator;
            }

            public final int getIndex() {
                return this.index;
            }

            public final void setIndex(int n) {
                this.index = n;
            }

            @NotNull
            public IndexedValue<T> next() {
                int n = this.index;
                this.index = n + 1;
                boolean bl = false;
                if (n < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                int n2 = n;
                T t = this.iterator.next();
                int n3 = n2;
                return new IndexedValue<T>(n3, t);
            }

            public boolean hasNext() {
                return this.iterator.hasNext();
            }
            {
                this.this$0 = $outer;
                this.iterator = IndexingSequence.access$getSequence$p($outer).iterator();
            }

            public void remove() {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }
        };
    }

    public IndexingSequence(@NotNull Sequence<? extends T> sequence) {
        Intrinsics.checkParameterIsNotNull(sequence, "sequence");
        this.sequence = sequence;
    }

    public static final /* synthetic */ Sequence access$getSequence$p(IndexingSequence $this) {
        return $this.sequence;
    }
}

