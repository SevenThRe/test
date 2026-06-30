/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.SinceKotlin;
import info.journeymap.shaded.kotlin.kotlin.annotation.AnnotationRetention;
import info.journeymap.shaded.kotlin.kotlin.annotation.AnnotationTarget;
import info.journeymap.shaded.kotlin.kotlin.annotation.MustBeDocumented;
import info.journeymap.shaded.kotlin.kotlin.annotation.Retention;
import info.journeymap.shaded.kotlin.kotlin.annotation.Target;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Target(allowedTargets={AnnotationTarget.CLASS, AnnotationTarget.CONSTRUCTOR, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY})
@Retention(value=AnnotationRetention.BINARY)
@MustBeDocumented
@Documented
@java.lang.annotation.Retention(value=RetentionPolicy.CLASS)
@java.lang.annotation.Target(value={ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000\u00a8\u0006\u0002"}, d2={"Linfo/journeymap/shaded/kotlin/kotlin/PublishedApi;", "", "info.journeymap.shaded.kotlin.kotlin-stdlib"})
@SinceKotlin(version="1.1")
public @interface PublishedApi {
}

