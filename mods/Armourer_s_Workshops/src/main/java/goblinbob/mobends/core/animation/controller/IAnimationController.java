/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package goblinbob.mobends.core.animation.controller;

import goblinbob.mobends.core.data.EntityData;
import java.util.Collection;
import javax.annotation.Nullable;

public interface IAnimationController<T extends EntityData<?>> {
    @Nullable
    public Collection<String> perform(T var1);
}

