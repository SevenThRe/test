/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin.jvm.internal;

import info.journeymap.shaded.kotlin.kotlin.SinceKotlin;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.MutablePropertyReference;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Reflection;
import info.journeymap.shaded.kotlin.kotlin.reflect.KCallable;
import info.journeymap.shaded.kotlin.kotlin.reflect.KMutableProperty0;
import info.journeymap.shaded.kotlin.kotlin.reflect.KProperty0;

public abstract class MutablePropertyReference0
extends MutablePropertyReference
implements KMutableProperty0 {
    public MutablePropertyReference0() {
    }

    @SinceKotlin(version="1.1")
    public MutablePropertyReference0(Object receiver) {
        super(receiver);
    }

    @Override
    protected KCallable computeReflected() {
        return Reflection.mutableProperty0(this);
    }

    @Override
    public Object invoke() {
        return this.get();
    }

    @Override
    public KProperty0.Getter getGetter() {
        return ((KMutableProperty0)this.getReflected()).getGetter();
    }

    public KMutableProperty0.Setter getSetter() {
        return ((KMutableProperty0)this.getReflected()).getSetter();
    }

    @Override
    @SinceKotlin(version="1.1")
    public Object getDelegate() {
        return ((KMutableProperty0)this.getReflected()).getDelegate();
    }
}

