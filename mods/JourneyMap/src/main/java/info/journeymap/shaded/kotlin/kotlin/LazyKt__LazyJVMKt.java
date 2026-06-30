/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin;

import info.journeymap.shaded.kotlin.kotlin.Lazy;
import info.journeymap.shaded.kotlin.kotlin.LazyKt$WhenMappings;
import info.journeymap.shaded.kotlin.kotlin.LazyThreadSafetyMode;
import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.NoWhenBranchMatchedException;
import info.journeymap.shaded.kotlin.kotlin.SafePublicationLazyImpl;
import info.journeymap.shaded.kotlin.kotlin.SynchronizedLazyImpl;
import info.journeymap.shaded.kotlin.kotlin.UnsafeLazyImpl;
import info.journeymap.shaded.kotlin.kotlin.jvm.functions.Function0;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import info.journeymap.shaded.org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=5, xi=1, d1={"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004\u001a*\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004\u001a(\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004\u00a8\u0006\t"}, d2={"lazy", "Linfo/journeymap/shaded/kotlin/kotlin/Lazy;", "T", "initializer", "Linfo/journeymap/shaded/kotlin/kotlin/Function0;", "lock", "", "mode", "Linfo/journeymap/shaded/kotlin/kotlin/LazyThreadSafetyMode;", "info.journeymap.shaded.kotlin.kotlin-stdlib"}, xs="info/journeymap/shaded/kotlin/kotlin/LazyKt")
class LazyKt__LazyJVMKt {
    @NotNull
    public static final <T> Lazy<T> lazy(@NotNull Function0<? extends T> initializer) {
        Intrinsics.checkParameterIsNotNull(initializer, "initializer");
        return new SynchronizedLazyImpl(initializer, null, 2, null);
    }

    @NotNull
    public static final <T> Lazy<T> lazy(@NotNull LazyThreadSafetyMode mode, @NotNull Function0<? extends T> initializer) {
        Lazy lazy;
        Intrinsics.checkParameterIsNotNull((Object)mode, "mode");
        Intrinsics.checkParameterIsNotNull(initializer, "initializer");
        switch (LazyKt$WhenMappings.$EnumSwitchMapping$0[mode.ordinal()]) {
            case 1: {
                lazy = new SynchronizedLazyImpl(initializer, null, 2, null);
                break;
            }
            case 2: {
                lazy = new SafePublicationLazyImpl<T>(initializer);
                break;
            }
            case 3: {
                lazy = new UnsafeLazyImpl<T>(initializer);
                break;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
        return lazy;
    }

    @NotNull
    public static final <T> Lazy<T> lazy(@Nullable Object lock, @NotNull Function0<? extends T> initializer) {
        Intrinsics.checkParameterIsNotNull(initializer, "initializer");
        return new SynchronizedLazyImpl<T>(initializer, lock);
    }
}

