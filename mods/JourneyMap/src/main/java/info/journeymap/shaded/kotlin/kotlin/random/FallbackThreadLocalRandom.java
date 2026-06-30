/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin.random;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.kotlin.kotlin.random.AbstractPlatformRandom;
import info.journeymap.shaded.kotlin.kotlin.random.FallbackThreadLocalRandom;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import java.util.Random;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u001b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003*\u0001\b\b\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\t\u00a8\u0006\n"}, d2={"Linfo/journeymap/shaded/kotlin/kotlin/random/FallbackThreadLocalRandom;", "Linfo/journeymap/shaded/kotlin/kotlin/random/AbstractPlatformRandom;", "()V", "impl", "Ljava/util/Random;", "getImpl", "()Ljava/util/Random;", "implStorage", "info/journeymap/shaded/kotlin/kotlin/random/FallbackThreadLocalRandom$implStorage$1", "Linfo/journeymap/shaded/kotlin/kotlin/random/FallbackThreadLocalRandom$implStorage$1;", "info.journeymap.shaded.kotlin.kotlin-stdlib"})
public final class FallbackThreadLocalRandom
extends AbstractPlatformRandom {
    private final implStorage.1 implStorage = new ThreadLocal<Random>(){

        @NotNull
        protected Random initialValue() {
            return new Random();
        }
    };

    @Override
    @NotNull
    public Random getImpl() {
        Object t = this.implStorage.get();
        Intrinsics.checkExpressionValueIsNotNull(t, "implStorage.get()");
        return (Random)t;
    }
}

