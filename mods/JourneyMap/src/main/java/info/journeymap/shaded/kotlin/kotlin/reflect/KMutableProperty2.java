/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin.reflect;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.Unit;
import info.journeymap.shaded.kotlin.kotlin.jvm.functions.Function3;
import info.journeymap.shaded.kotlin.kotlin.reflect.KMutableProperty;
import info.journeymap.shaded.kotlin.kotlin.reflect.KProperty2;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\bf\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\b\u0012\u0004\u0012\u0002H\u00030\u0005:\u0001\u0010J%\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00028\u00002\u0006\u0010\r\u001a\u00028\u00012\u0006\u0010\u000e\u001a\u00028\u0002H&\u00a2\u0006\u0002\u0010\u000fR$\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0011"}, d2={"Linfo/journeymap/shaded/kotlin/kotlin/reflect/KMutableProperty2;", "D", "E", "R", "Linfo/journeymap/shaded/kotlin/kotlin/reflect/KProperty2;", "Linfo/journeymap/shaded/kotlin/kotlin/reflect/KMutableProperty;", "setter", "Linfo/journeymap/shaded/kotlin/kotlin/reflect/KMutableProperty2$Setter;", "getSetter", "()Lkotlin/reflect/KMutableProperty2$Setter;", "set", "", "receiver1", "receiver2", "value", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", "Setter", "info.journeymap.shaded.kotlin.kotlin-stdlib"})
public interface KMutableProperty2<D, E, R>
extends KProperty2<D, E, R>,
KMutableProperty<R> {
    public void set(D var1, E var2, R var3);

    @NotNull
    public Setter<D, E, R> getSetter();

    @Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\bf\u0018\u0000*\u0004\b\u0003\u0010\u0001*\u0004\b\u0004\u0010\u0002*\u0004\b\u0005\u0010\u00032\b\u0012\u0004\u0012\u0002H\u00030\u00042\u001a\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u00060\u0005\u00a8\u0006\u0007"}, d2={"Linfo/journeymap/shaded/kotlin/kotlin/reflect/KMutableProperty2$Setter;", "D", "E", "R", "Linfo/journeymap/shaded/kotlin/kotlin/reflect/KMutableProperty$Setter;", "Linfo/journeymap/shaded/kotlin/kotlin/Function3;", "", "info.journeymap.shaded.kotlin.kotlin-stdlib"})
    public static interface Setter<D, E, R>
    extends KMutableProperty.Setter<R>,
    Function3<D, E, R, Unit> {
    }
}

