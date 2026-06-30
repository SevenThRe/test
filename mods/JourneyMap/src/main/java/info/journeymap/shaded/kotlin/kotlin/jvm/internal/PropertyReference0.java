/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin.jvm.internal;

import info.journeymap.shaded.kotlin.kotlin.SinceKotlin;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.PropertyReference;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Reflection;
import info.journeymap.shaded.kotlin.kotlin.reflect.KCallable;
import info.journeymap.shaded.kotlin.kotlin.reflect.KProperty0;

public abstract class PropertyReference0
extends PropertyReference
implements KProperty0 {
    public PropertyReference0() {
    }

    @SinceKotlin(version="1.1")
    public PropertyReference0(Object receiver) {
        super(receiver);
    }

    @Override
    protected KCallable computeReflected() {
        return Reflection.property0(this);
    }

    @Override
    public Object invoke() {
        return this.get();
    }

    public KProperty0.Getter getGetter() {
        return ((KProperty0)this.getReflected()).getGetter();
    }

    @Override
    @SinceKotlin(version="1.1")
    public Object getDelegate() {
        return ((KProperty0)this.getReflected()).getDelegate();
    }
}

