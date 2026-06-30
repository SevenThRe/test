/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin.sequences;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.collections.CollectionsKt;
import info.journeymap.shaded.kotlin.kotlin.jvm.functions.Function2;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.kotlin.kotlin.sequences.Sequence;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import java.util.Iterator;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00020\u0003B-\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006\u00a2\u0006\u0002\u0010\bJ\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\nH\u0096\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2={"Linfo/journeymap/shaded/kotlin/kotlin/sequences/TransformingIndexedSequence;", "T", "R", "Linfo/journeymap/shaded/kotlin/kotlin/sequences/Sequence;", "sequence", "transformer", "Linfo/journeymap/shaded/kotlin/kotlin/Function2;", "", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function2;)V", "iterator", "", "info.journeymap.shaded.kotlin.kotlin-stdlib"})
public final class TransformingIndexedSequence<T, R>
implements Sequence<R> {
    private final Sequence<T> sequence;
    private final Function2<Integer, T, R> transformer;

    @Override
    @NotNull
    public Iterator<R> iterator() {
        return new Iterator<R>(this){
            @NotNull
            private final Iterator<T> iterator;
            private int index;
            final /* synthetic */ TransformingIndexedSequence this$0;

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

            public R next() {
                int n = this.index;
                this.index = n + 1;
                Function2 function2 = TransformingIndexedSequence.access$getTransformer$p(this.this$0);
                boolean bl = false;
                if (n < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                int n2 = n;
                return function2.invoke(n2, this.iterator.next());
            }

            public boolean hasNext() {
                return this.iterator.hasNext();
            }
            {
                this.this$0 = $outer;
                this.iterator = TransformingIndexedSequence.access$getSequence$p($outer).iterator();
            }

            public void remove() {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }
        };
    }

    public TransformingIndexedSequence(@NotNull Sequence<? extends T> sequence, @NotNull Function2<? super Integer, ? super T, ? extends R> transformer) {
        Intrinsics.checkParameterIsNotNull(sequence, "sequence");
        Intrinsics.checkParameterIsNotNull(transformer, "transformer");
        this.sequence = sequence;
        this.transformer = transformer;
    }

    public static final /* synthetic */ Function2 access$getTransformer$p(TransformingIndexedSequence $this) {
        return $this.transformer;
    }

    public static final /* synthetic */ Sequence access$getSequence$p(TransformingIndexedSequence $this) {
        return $this.sequence;
    }
}

