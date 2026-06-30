/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package goblinbob.mobends.core.data;

import goblinbob.mobends.core.data.EntityData;
import net.minecraft.entity.Entity;

@FunctionalInterface
public interface IEntityDataFactory<E extends Entity> {
    public EntityData<E> createEntityData(E var1);
}

