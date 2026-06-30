/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin.sequences;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.jvm.functions.Function1;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.kotlin.kotlin.sequences.FlatteningSequence;
import info.journeymap.shaded.kotlin.kotlin.sequences.Sequence;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import java.util.Iterator;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00020\u0003B'\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006\u00a2\u0006\u0002\u0010\u0007J3\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\t0\u0003\"\u0004\b\u0002\u0010\t2\u0018\u0010\n\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u000b0\u0006H\u0000\u00a2\u0006\u0002\b\fJ\u000f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\u000bH\u0096\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2={"Linfo/journeymap/shaded/kotlin/kotlin/sequences/TransformingSequence;", "T", "R", "Linfo/journeymap/shaded/kotlin/kotlin/sequences/Sequence;", "sequence", "transformer", "Linfo/journeymap/shaded/kotlin/kotlin/Function1;", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)V", "flatten", "E", "iterator", "", "flatten$kotlin_stdlib", "info.journeymap.shaded.kotlin.kotlin-stdlib"})
public final class TransformingSequence<T, R>
implements Sequence<R> {
    private final Sequence<T> sequence;
    private final Function1<T, R> transformer;

    @Override
    @NotNull
    public Iterator<R> iterator() {
        return new Iterator<R>(this){
            @NotNull
            private final Iterator<T> iterator;
            final /* synthetic */ TransformingSequence this$0;

            @NotNull
            public final Iterator<T> getIterator() {
                return this.iterator;
            }

            public R next() {
                return TransformingSequence.access$getTransformer$p(this.this$0).invoke(this.iterator.next());
            }

            public boolean hasNext() {
                return this.iterator.hasNext();
            }
            {
                this.this$0 = $outer;
                this.iterator = TransformingSequence.access$getSequence$p($outer).iterator();
            }

            public void remove() {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }
        };
    }

    @NotNull
    public final <E> Sequence<E> flatten$kotlin_stdlib(@NotNull Function1<? super R, ? extends Iterator<? extends E>> iterator2) {
        Intrinsics.checkParameterIsNotNull(iterator2, "iterator");
        return new FlatteningSequence(this.sequence, this.transformer, iterator2);
    }

    public TransformingSequence(@NotNull Sequence<? extends T> sequence, @NotNull Function1<? super T, ? extends R> transformer) {
        Intrinsics.checkParameterIsNotNull(sequence, "sequence");
        Intrinsics.checkParameterIsNotNull(transformer, "transformer");
        this.sequence = sequence;
        this.transformer = transformer;
    }

    public static final /* synthetic */ Function1 access$getTransformer$p(TransformingSequence $this) {
        return $this.transformer;
    }

    public static final /* synthetic */ Sequence access$getSequence$p(TransformingSequence $this) {
        return $this.sequence;
    }
}

