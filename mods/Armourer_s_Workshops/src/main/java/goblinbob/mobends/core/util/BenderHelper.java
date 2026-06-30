/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.entity.EntityLivingBase
 */
package goblinbob.mobends.core.util;

import goblinbob.mobends.core.bender.EntityBender;
import goblinbob.mobends.core.bender.EntityBenderRegistry;
import goblinbob.mobends.core.data.LivingEntityData;
import goblinbob.mobends.core.mutators.Mutator;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.EntityLivingBase;

public class BenderHelper {
    public static boolean isEntityAnimated(EntityLivingBase entity) {
        EntityBender<EntityLivingBase> bender = EntityBenderRegistry.instance.getForEntity(entity);
        return bender != null && bender.isAnimated();
    }

    public static <T extends EntityLivingBase> Mutator<?, ?, ?> getMutatorForRenderer(Class<T> entityClass, RenderLivingBase<T> renderer) {
        EntityBender<T> bender = EntityBenderRegistry.instance.getForEntityClass(entityClass);
        return bender != null ? bender.getMutator(renderer) : null;
    }

    public static <D extends LivingEntityData<E>, E extends EntityLivingBase> D getData(E entity, RenderLivingBase<? extends EntityLivingBase> renderer) {
        EntityBender<E> entityBender = EntityBenderRegistry.instance.getForEntity(entity);
        if (entityBender == null) {
            return null;
        }
        Mutator<?, ?, ?> mutator = entityBender.getMutator(renderer);
        if (mutator == null) {
            return null;
        }
        return (D)mutator.getOrMakeData(entity);
    }
}

