/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin.jvm.internal;

import info.journeymap.shaded.kotlin.kotlin.SinceKotlin;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.ClassReference;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.FunctionBase;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.FunctionReference;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Lambda;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.MutablePropertyReference0;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.MutablePropertyReference1;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.MutablePropertyReference2;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.PackageReference;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.PropertyReference0;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.PropertyReference1;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.PropertyReference2;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.TypeReference;
import info.journeymap.shaded.kotlin.kotlin.reflect.KClass;
import info.journeymap.shaded.kotlin.kotlin.reflect.KClassifier;
import info.journeymap.shaded.kotlin.kotlin.reflect.KDeclarationContainer;
import info.journeymap.shaded.kotlin.kotlin.reflect.KFunction;
import info.journeymap.shaded.kotlin.kotlin.reflect.KMutableProperty0;
import info.journeymap.shaded.kotlin.kotlin.reflect.KMutableProperty1;
import info.journeymap.shaded.kotlin.kotlin.reflect.KMutableProperty2;
import info.journeymap.shaded.kotlin.kotlin.reflect.KProperty0;
import info.journeymap.shaded.kotlin.kotlin.reflect.KProperty1;
import info.journeymap.shaded.kotlin.kotlin.reflect.KProperty2;
import info.journeymap.shaded.kotlin.kotlin.reflect.KType;
import info.journeymap.shaded.kotlin.kotlin.reflect.KTypeProjection;
import java.util.List;

public class ReflectionFactory {
    private static final String KOTLIN_JVM_FUNCTIONS = "info.journeymap.shaded.kotlin.kotlin.jvm.functions.";

    public KClass createKotlinClass(Class javaClass) {
        return new ClassReference(javaClass);
    }

    public KClass createKotlinClass(Class javaClass, String internalName) {
        return new ClassReference(javaClass);
    }

    public KDeclarationContainer getOrCreateKotlinPackage(Class javaClass, String moduleName) {
        return new PackageReference(javaClass, moduleName);
    }

    public KClass getOrCreateKotlinClass(Class javaClass) {
        return new ClassReference(javaClass);
    }

    public KClass getOrCreateKotlinClass(Class javaClass, String internalName) {
        return new ClassReference(javaClass);
    }

    @SinceKotlin(version="1.1")
    public String renderLambdaToString(Lambda lambda2) {
        return this.renderLambdaToString((FunctionBase)lambda2);
    }

    @SinceKotlin(version="1.3")
    public String renderLambdaToString(FunctionBase lambda2) {
        String result = lambda2.getClass().getGenericInterfaces()[0].toString();
        return result.startsWith(KOTLIN_JVM_FUNCTIONS) ? result.substring(KOTLIN_JVM_FUNCTIONS.length()) : result;
    }

    public KFunction function(FunctionReference f) {
        return f;
    }

    public KProperty0 property0(PropertyReference0 p) {
        return p;
    }

    public KMutableProperty0 mutableProperty0(MutablePropertyReference0 p) {
        return p;
    }

    public KProperty1 property1(PropertyReference1 p) {
        return p;
    }

    public KMutableProperty1 mutableProperty1(MutablePropertyReference1 p) {
        return p;
    }

    public KProperty2 property2(PropertyReference2 p) {
        return p;
    }

    public KMutableProperty2 mutableProperty2(MutablePropertyReference2 p) {
        return p;
    }

    @SinceKotlin(version="1.4")
    public KType typeOf(KClassifier klass, List<KTypeProjection> arguments, boolean isMarkedNullable) {
        return new TypeReference(klass, arguments, isMarkedNullable);
    }
}

