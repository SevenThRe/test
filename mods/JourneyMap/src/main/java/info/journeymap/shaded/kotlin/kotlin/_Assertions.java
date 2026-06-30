/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.PublishedApi;
import info.journeymap.shaded.kotlin.kotlin.jvm.JvmField;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u00c1\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0000X\u0081\u0004\u00a2\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u00a8\u0006\u0006"}, d2={"Linfo/journeymap/shaded/kotlin/kotlin/_Assertions;", "", "()V", "ENABLED", "", "ENABLED$annotations", "info.journeymap.shaded.kotlin.kotlin-stdlib"})
@PublishedApi
public final class _Assertions {
    @JvmField
    public static final boolean ENABLED;
    public static final _Assertions INSTANCE;

    @PublishedApi
    public static /* synthetic */ void ENABLED$annotations() {
    }

    private _Assertions() {
    }

    static {
        _Assertions _Assertions2;
        INSTANCE = _Assertions2 = new _Assertions();
        ENABLED = _Assertions2.getClass().desiredAssertionStatus();
    }
}

