/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin.jvm.internal;

import info.journeymap.shaded.kotlin.kotlin.SinceKotlin;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.PropertyReference;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Reflection;
import info.journeymap.shaded.kotlin.kotlin.reflect.KCallable;
import info.journeymap.shaded.kotlin.kotlin.reflect.KProperty1;

public abstract class PropertyReference1
extends PropertyReference
implements KProperty1 {
    public PropertyReference1() {
    }

    @SinceKotlin(version="1.1")
    public PropertyReference1(Object receiver) {
        super(receiver);
    }

    @Override
    protected KCallable computeReflected() {
        return Reflection.property1(this);
    }

    @Override
    public Object invoke(Object receiver) {
        return this.get(receiver);
    }

    public KProperty1.Getter getGetter() {
        return ((KProperty1)this.getReflected()).getGetter();
    }

    @SinceKotlin(version="1.1")
    public Object getDelegate(Object receiver) {
        return ((KProperty1)this.getReflected()).getDelegate(receiver);
    }
}

