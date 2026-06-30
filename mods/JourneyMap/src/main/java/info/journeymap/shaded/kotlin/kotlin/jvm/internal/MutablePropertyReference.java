/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.kotlin.jvm.internal;

import info.journeymap.shaded.kotlin.kotlin.SinceKotlin;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.PropertyReference;
import info.journeymap.shaded.kotlin.kotlin.reflect.KMutableProperty;

public abstract class MutablePropertyReference
extends PropertyReference
implements KMutableProperty {
    public MutablePropertyReference() {
    }

    @SinceKotlin(version="1.1")
    public MutablePropertyReference(Object receiver) {
        super(receiver);
    }
}

