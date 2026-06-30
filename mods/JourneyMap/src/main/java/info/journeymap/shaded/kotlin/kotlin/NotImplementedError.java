/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.DefaultConstructorMarker;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005\u00a8\u0006\u0006"}, d2={"Linfo/journeymap/shaded/kotlin/kotlin/NotImplementedError;", "Ljava/lang/Error;", "Linfo/journeymap/shaded/kotlin/kotlin/Error;", "message", "", "(Ljava/lang/String;)V", "info.journeymap.shaded.kotlin.kotlin-stdlib"})
public final class NotImplementedError
extends Error {
    public NotImplementedError(@NotNull String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        super(message);
    }

    public /* synthetic */ NotImplementedError(String string, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 1) != 0) {
            string = "An operation is not implemented.";
        }
        this(string);
    }

    public NotImplementedError() {
        this(null, 1, null);
    }
}

