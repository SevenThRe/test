/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin.comparisons;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.SinceKotlin;
import info.journeymap.shaded.kotlin.kotlin.comparisons.ComparisonsKt;
import info.journeymap.shaded.kotlin.kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import java.util.Comparator;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000\u0010\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aG\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u0002H\u00012\u0006\u0010\u0003\u001a\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u00012\u001a\u0010\u0005\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0006j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`\u0007H\u0007\u00a2\u0006\u0002\u0010\b\u001a?\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u0002H\u00012\u0006\u0010\u0003\u001a\u0002H\u00012\u001a\u0010\u0005\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0006j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`\u0007H\u0007\u00a2\u0006\u0002\u0010\t\u001aG\u0010\n\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u0002H\u00012\u0006\u0010\u0003\u001a\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u00012\u001a\u0010\u0005\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0006j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`\u0007H\u0007\u00a2\u0006\u0002\u0010\b\u001a?\u0010\n\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u0002H\u00012\u0006\u0010\u0003\u001a\u0002H\u00012\u001a\u0010\u0005\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0006j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`\u0007H\u0007\u00a2\u0006\u0002\u0010\t\u00a8\u0006\u000b"}, d2={"maxOf", "T", "a", "b", "c", "comparator", "Ljava/util/Comparator;", "Linfo/journeymap/shaded/kotlin/kotlin/Comparator;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Comparator;)Ljava/lang/Object;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Comparator;)Ljava/lang/Object;", "minOf", "info.journeymap.shaded.kotlin.kotlin-stdlib"}, xs="info/journeymap/shaded/kotlin/kotlin/comparisons/ComparisonsKt")
class ComparisonsKt___ComparisonsKt
extends ComparisonsKt___ComparisonsJvmKt {
    @SinceKotlin(version="1.1")
    public static final <T> T maxOf(T a, T b, T c, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        return ComparisonsKt.maxOf(a, ComparisonsKt.maxOf(b, c, comparator), comparator);
    }

    @SinceKotlin(version="1.1")
    public static final <T> T maxOf(T a, T b, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        return comparator.compare(a, b) >= 0 ? a : b;
    }

    @SinceKotlin(version="1.1")
    public static final <T> T minOf(T a, T b, T c, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        return ComparisonsKt.minOf(a, ComparisonsKt.minOf(b, c, comparator), comparator);
    }

    @SinceKotlin(version="1.1")
    public static final <T> T minOf(T a, T b, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        return comparator.compare(a, b) <= 0 ? a : b;
    }
}

