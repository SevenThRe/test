/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 */
package goblinbob.mobends.core.mutators;

import goblinbob.mobends.core.data.IEntityDataFactory;
import goblinbob.mobends.core.data.LivingEntityData;
import goblinbob.mobends.core.mutators.Mutator;
import net.minecraft.entity.EntityLivingBase;

@FunctionalInterface
public interface IMutatorFactory<E extends EntityLivingBase> {
    public Mutator<? extends LivingEntityData<E>, ? extends E, ?> createMutator(IEntityDataFactory<E> var1);
}

