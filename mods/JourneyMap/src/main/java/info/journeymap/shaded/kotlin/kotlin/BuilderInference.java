/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.SinceKotlin;
import info.journeymap.shaded.kotlin.kotlin.annotation.AnnotationRetention;
import info.journeymap.shaded.kotlin.kotlin.annotation.AnnotationTarget;
import info.journeymap.shaded.kotlin.kotlin.annotation.Retention;
import info.journeymap.shaded.kotlin.kotlin.annotation.Target;
import info.journeymap.shaded.kotlin.kotlin.experimental.ExperimentalTypeInference;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Target(allowedTargets={AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY})
@Retention(value=AnnotationRetention.BINARY)
@java.lang.annotation.Retention(value=RetentionPolicy.CLASS)
@java.lang.annotation.Target(value={ElementType.METHOD, ElementType.PARAMETER})
@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000\u00a8\u0006\u0002"}, d2={"Linfo/journeymap/shaded/kotlin/kotlin/BuilderInference;", "", "info.journeymap.shaded.kotlin.kotlin-stdlib"})
@SinceKotlin(version="1.3")
@ExperimentalTypeInference
public @interface BuilderInference {
}

