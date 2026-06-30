/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin.sequences;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.collections.CollectionsKt;
import info.journeymap.shaded.kotlin.kotlin.internal.InlineOnly;
import info.journeymap.shaded.kotlin.kotlin.sequences.Sequence;
import info.journeymap.shaded.kotlin.kotlin.sequences.SequencesKt;
import info.journeymap.shaded.kotlin.kotlin.sequences.SequencesKt__SequenceBuilderKt;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import java.util.Enumeration;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0087\b\u00a8\u0006\u0004"}, d2={"asSequence", "Linfo/journeymap/shaded/kotlin/kotlin/sequences/Sequence;", "T", "Ljava/util/Enumeration;", "info.journeymap.shaded.kotlin.kotlin-stdlib"}, xs="info/journeymap/shaded/kotlin/kotlin/sequences/SequencesKt")
class SequencesKt__SequencesJVMKt
extends SequencesKt__SequenceBuilderKt {
    @InlineOnly
    private static final <T> Sequence<T> asSequence(@NotNull Enumeration<T> $this$asSequence) {
        int $i$f$asSequence = 0;
        return SequencesKt.asSequence(CollectionsKt.iterator($this$asSequence));
    }
}

