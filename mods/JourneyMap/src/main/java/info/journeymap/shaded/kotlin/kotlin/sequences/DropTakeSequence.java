/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin.sequences;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.sequences.Sequence;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b`\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0007"}, d2={"Linfo/journeymap/shaded/kotlin/kotlin/sequences/DropTakeSequence;", "T", "Linfo/journeymap/shaded/kotlin/kotlin/sequences/Sequence;", "drop", "n", "", "take", "info.journeymap.shaded.kotlin.kotlin-stdlib"})
public interface DropTakeSequence<T>
extends Sequence<T> {
    @NotNull
    public Sequence<T> drop(int var1);

    @NotNull
    public Sequence<T> take(int var1);
}

