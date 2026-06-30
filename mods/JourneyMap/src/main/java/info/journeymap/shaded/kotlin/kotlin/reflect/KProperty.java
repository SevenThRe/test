/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin.reflect;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.SinceKotlin;
import info.journeymap.shaded.kotlin.kotlin.reflect.KCallable;
import info.journeymap.shaded.kotlin.kotlin.reflect.KFunction;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0002\u000e\u000fR\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\b8&X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\u000bR\u001a\u0010\f\u001a\u00020\b8&X\u00a7\u0004\u00a2\u0006\f\u0012\u0004\b\r\u0010\n\u001a\u0004\b\f\u0010\u000b\u00a8\u0006\u0010"}, d2={"Linfo/journeymap/shaded/kotlin/kotlin/reflect/KProperty;", "R", "Linfo/journeymap/shaded/kotlin/kotlin/reflect/KCallable;", "getter", "Linfo/journeymap/shaded/kotlin/kotlin/reflect/KProperty$Getter;", "getGetter", "()Lkotlin/reflect/KProperty$Getter;", "isConst", "", "isConst$annotations", "()V", "()Z", "isLateinit", "isLateinit$annotations", "Accessor", "Getter", "info.journeymap.shaded.kotlin.kotlin-stdlib"})
public interface KProperty<R>
extends KCallable<R> {
    public boolean isLateinit();

    public boolean isConst();

    @NotNull
    public Getter<R> getGetter();

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\u00020\u0002R\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2={"Linfo/journeymap/shaded/kotlin/kotlin/reflect/KProperty$Accessor;", "R", "", "property", "Linfo/journeymap/shaded/kotlin/kotlin/reflect/KProperty;", "getProperty", "()Lkotlin/reflect/KProperty;", "info.journeymap.shaded.kotlin.kotlin-stdlib"})
    public static interface Accessor<R> {
        @NotNull
        public KProperty<R> getProperty();
    }

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003\u00a8\u0006\u0004"}, d2={"Linfo/journeymap/shaded/kotlin/kotlin/reflect/KProperty$Getter;", "R", "Linfo/journeymap/shaded/kotlin/kotlin/reflect/KProperty$Accessor;", "Linfo/journeymap/shaded/kotlin/kotlin/reflect/KFunction;", "info.journeymap.shaded.kotlin.kotlin-stdlib"})
    public static interface Getter<R>
    extends Accessor<R>,
    KFunction<R> {
    }

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=3)
    public static final class DefaultImpls {
        @SinceKotlin(version="1.1")
        public static /* synthetic */ void isLateinit$annotations() {
        }

        @SinceKotlin(version="1.1")
        public static /* synthetic */ void isConst$annotations() {
        }
    }
}

