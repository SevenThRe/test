/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin.experimental;

import info.journeymap.shaded.kotlin.kotlin.Experimental;
import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.SinceKotlin;
import info.journeymap.shaded.kotlin.kotlin.annotation.AnnotationRetention;
import info.journeymap.shaded.kotlin.kotlin.annotation.AnnotationTarget;
import info.journeymap.shaded.kotlin.kotlin.annotation.MustBeDocumented;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@MustBeDocumented
@info.journeymap.shaded.kotlin.kotlin.annotation.Retention(value=AnnotationRetention.BINARY)
@info.journeymap.shaded.kotlin.kotlin.annotation.Target(allowedTargets={AnnotationTarget.ANNOTATION_CLASS})
@Documented
@Retention(value=RetentionPolicy.CLASS)
@Target(value={ElementType.ANNOTATION_TYPE})
@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000\u00a8\u0006\u0002"}, d2={"Linfo/journeymap/shaded/kotlin/kotlin/experimental/ExperimentalTypeInference;", "", "info.journeymap.shaded.kotlin.kotlin-stdlib"})
@Experimental(level=Experimental.Level.ERROR)
@SinceKotlin(version="1.3")
public @interface ExperimentalTypeInference {
}

