/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin.jvm;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.annotation.AnnotationRetention;
import info.journeymap.shaded.kotlin.kotlin.annotation.AnnotationTarget;
import info.journeymap.shaded.kotlin.kotlin.annotation.MustBeDocumented;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@info.journeymap.shaded.kotlin.kotlin.annotation.Target(allowedTargets={AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.FILE})
@info.journeymap.shaded.kotlin.kotlin.annotation.Retention(value=AnnotationRetention.BINARY)
@MustBeDocumented
@Documented
@Retention(value=RetentionPolicy.CLASS)
@Target(value={ElementType.METHOD})
@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\u0002\u0018\u00002\u00020\u0001B\b\u0012\u0006\u0010\u0002\u001a\u00020\u0003R\u000f\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2={"Linfo/journeymap/shaded/kotlin/kotlin/jvm/JvmName;", "", "name", "", "()Ljava/lang/String;", "info.journeymap.shaded.kotlin.kotlin-stdlib"})
public @interface JvmName {
    public String name();
}

