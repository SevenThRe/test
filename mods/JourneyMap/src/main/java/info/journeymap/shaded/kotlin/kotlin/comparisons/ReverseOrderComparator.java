/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin.comparisons;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.comparisons.NaturalOrderComparator;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import java.util.Comparator;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u00c2\u0002\u0018\u00002\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002`\u0004B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0005J$\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\"\u0010\n\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002`\u0004\u00a8\u0006\u000b"}, d2={"Linfo/journeymap/shaded/kotlin/kotlin/comparisons/ReverseOrderComparator;", "Ljava/util/Comparator;", "", "", "Linfo/journeymap/shaded/kotlin/kotlin/Comparator;", "()V", "compare", "", "a", "b", "reversed", "info.journeymap.shaded.kotlin.kotlin-stdlib"})
final class ReverseOrderComparator
implements Comparator<Comparable<? super Object>> {
    public static final ReverseOrderComparator INSTANCE;

    @Override
    public int compare(@NotNull Comparable<Object> a, @NotNull Comparable<Object> b) {
        Intrinsics.checkParameterIsNotNull(a, "a");
        Intrinsics.checkParameterIsNotNull(b, "b");
        return b.compareTo(a);
    }

    @Override
    @NotNull
    public final Comparator<Comparable<Object>> reversed() {
        return NaturalOrderComparator.INSTANCE;
    }

    private ReverseOrderComparator() {
    }

    static {
        ReverseOrderComparator reverseOrderComparator;
        INSTANCE = reverseOrderComparator = new ReverseOrderComparator();
    }
}

