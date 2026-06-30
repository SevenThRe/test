/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin.reflect;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.SinceKotlin;
import info.journeymap.shaded.kotlin.kotlin.reflect.KClassifier;
import info.journeymap.shaded.kotlin.kotlin.reflect.KType;
import info.journeymap.shaded.kotlin.kotlin.reflect.KVariance;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import java.util.List;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004R\u0012\u0010\u0005\u001a\u00020\u0006X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0012"}, d2={"Linfo/journeymap/shaded/kotlin/kotlin/reflect/KTypeParameter;", "Linfo/journeymap/shaded/kotlin/kotlin/reflect/KClassifier;", "isReified", "", "()Z", "name", "", "getName", "()Ljava/lang/String;", "upperBounds", "", "Linfo/journeymap/shaded/kotlin/kotlin/reflect/KType;", "getUpperBounds", "()Ljava/util/List;", "variance", "Linfo/journeymap/shaded/kotlin/kotlin/reflect/KVariance;", "getVariance", "()Lkotlin/reflect/KVariance;", "info.journeymap.shaded.kotlin.kotlin-stdlib"})
@SinceKotlin(version="1.1")
public interface KTypeParameter
extends KClassifier {
    @NotNull
    public String getName();

    @NotNull
    public List<KType> getUpperBounds();

    @NotNull
    public KVariance getVariance();

    public boolean isReified();
}

