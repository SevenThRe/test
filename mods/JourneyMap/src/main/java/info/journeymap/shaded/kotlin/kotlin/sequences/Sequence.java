/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin.sequences;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import java.util.Iterator;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010(\n\u0000\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u00a6\u0002\u00a8\u0006\u0005"}, d2={"Linfo/journeymap/shaded/kotlin/kotlin/sequences/Sequence;", "T", "", "iterator", "", "info.journeymap.shaded.kotlin.kotlin-stdlib"})
public interface Sequence<T> {
    @NotNull
    public Iterator<T> iterator();
}

