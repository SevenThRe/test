/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin.collections;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.collections.IndexedValue;
import info.journeymap.shaded.kotlin.kotlin.collections.IndexingIterator;
import info.journeymap.shaded.kotlin.kotlin.jvm.functions.Function0;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.markers.KMappedMarker;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import java.util.Iterator;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\u0015\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u0006H\u0096\u0002R\u001a\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2={"Linfo/journeymap/shaded/kotlin/kotlin/collections/IndexingIterable;", "T", "", "Linfo/journeymap/shaded/kotlin/kotlin/collections/IndexedValue;", "iteratorFactory", "Linfo/journeymap/shaded/kotlin/kotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)V", "iterator", "info.journeymap.shaded.kotlin.kotlin-stdlib"})
public final class IndexingIterable<T>
implements Iterable<IndexedValue<? extends T>>,
KMappedMarker {
    private final Function0<Iterator<T>> iteratorFactory;

    @Override
    @NotNull
    public Iterator<IndexedValue<T>> iterator() {
        return new IndexingIterator<T>(this.iteratorFactory.invoke());
    }

    public IndexingIterable(@NotNull Function0<? extends Iterator<? extends T>> iteratorFactory) {
        Intrinsics.checkParameterIsNotNull(iteratorFactory, "iteratorFactory");
        this.iteratorFactory = iteratorFactory;
    }
}

