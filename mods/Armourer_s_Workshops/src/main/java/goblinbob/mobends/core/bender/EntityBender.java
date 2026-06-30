/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package goblinbob.mobends.core.bender;

import goblinbob.mobends.core.bender.BoneMetadata;
import goblinbob.mobends.core.bender.IPreviewer;
import goblinbob.mobends.core.bender.PreviewHelper;
import goblinbob.mobends.core.client.MutatedRenderer;
import goblinbob.mobends.core.data.EntityData;
import goblinbob.mobends.core.data.IEntityDataFactory;
import goblinbob.mobends.core.data.LivingEntityData;
import goblinbob.mobends.core.math.TransformUtils;
import goblinbob.mobends.core.math.matrix.IMat4x4d;
import goblinbob.mobends.core.mutators.IMutatorFactory;
import goblinbob.mobends.core.mutators.Mutator;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public abstract class EntityBender<T extends EntityLivingBase> {
    protected final String key;
    protected final String unlocalizedName;
    private final MutatedRenderer<T> renderer;
    public final Class<T> entityClass;
    private final Map<RenderLivingBase<? extends T>, Mutator<LivingEntityData<T>, T, ?>> mutatorMap = new HashMap();
    private boolean animate;
    protected Map<String, BoneMetadata> boneMetadataMap;

    public EntityBender(String modId, @Nullable String key, String unlocalizedName, Class<T> entityClass, MutatedRenderer<T> renderer) {
        if (renderer == null) {
            throw new NullPointerException("The mutated renderer cannot be null.");
        }
        if (entityClass == null) {
            throw new NullPointerException("The entity class cannot be null.");
        }
        if (modId == null) {
            throw new NullPointerException("The Mod ID cannot be null.");
        }
        if (key == null) {
            ResourceLocation resourceLocation = EntityList.getKey(entityClass);
            if (resourceLocation == null) {
                throw new RuntimeException("Unable to find a key for " + entityClass.getName());
            }
            key = resourceLocation.toString();
            unlocalizedName = "entity." + EntityList.getTranslationName((ResourceLocation)resourceLocation) + ".name";
        }
        this.key = modId + "-" + key;
        this.unlocalizedName = unlocalizedName;
        this.entityClass = entityClass;
        this.renderer = renderer;
    }

    public abstract String[] getAlterableParts();

    public abstract IEntityDataFactory<T> getDataFactory();

    public abstract IMutatorFactory<T> getMutatorFactory();

    public abstract IPreviewer<?> getPreviewer();

    public abstract LivingEntityData<?> getDataForPreview();

    public String getKey() {
        return this.key;
    }

    public String getUnlocalizedName() {
        return this.unlocalizedName;
    }

    public String getLocalizedName() {
        return I18n.format((String)this.unlocalizedName, (Object[])new Object[0]);
    }

    public boolean isAnimated() {
        return this.animate;
    }

    public void setAnimate(boolean animate) {
        this.animate = animate;
    }

    public void beforeRender(EntityData<T> data, T entity, float partialTicks) {
        this.renderer.beforeRender(data, entity, partialTicks);
    }

    public void backtrackBeforeRender(EntityData<T> data, EntityPlayer entity, float partialTicks) {
        this.renderer.backtrackBeforeRender(data, entity, partialTicks);
    }

    public void afterRender(T entity, float partialTicks) {
        this.renderer.afterRender(entity, partialTicks);
    }

    public void resetArmWear(RenderLivingBase<? extends T> renderer) {
        Mutator<LivingEntityData<T>, T, ?> mutator = this.mutatorMap.get(renderer);
        if (mutator != null) {
            mutator.resetArmWear();
        }
    }

    public boolean applyMutation(RenderLivingBase<? extends T> renderer, T entity, float partialTicks) {
        Mutator<LivingEntityData<T>, T, ?> mutator = this.mutatorMap.get(renderer);
        if (mutator == null) {
            mutator = this.getMutatorFactory().createMutator(this.getDataFactory());
            if (!mutator.mutate(renderer)) {
                return false;
            }
            this.mutatorMap.put(renderer, mutator);
        }
        mutator.updateModel(entity, renderer, partialTicks);
        LivingEntityData<T> data = mutator.getOrMakeData(entity);
        mutator.performAnimations(data, this.key, renderer, partialTicks);
        mutator.syncUpWithData(data);
        return true;
    }

    public void deapplyMutation(RenderLivingBase<? extends T> renderer, EntityLivingBase entity) {
        if (this.mutatorMap.containsKey(renderer)) {
            Mutator<LivingEntityData<T>, T, ?> mutator = this.mutatorMap.get(renderer);
            mutator.demutate(renderer);
            this.mutatorMap.remove(renderer);
        }
    }

    public void refreshMutation() {
        for (Map.Entry<RenderLivingBase<T>, Mutator<LivingEntityData<T>, T, ?>> entry : this.mutatorMap.entrySet()) {
            Mutator<LivingEntityData<T>, T, ?> mutator = entry.getValue();
            mutator.demutate(entry.getKey());
            mutator.mutate(entry.getKey());
            mutator.postRefresh();
        }
    }

    protected T createPreviewEntity() {
        try {
            EntityLiving entity = (EntityLiving)this.entityClass.getConstructor(World.class).newInstance(Minecraft.getMinecraft().world);
            entity.world = Minecraft.getMinecraft().world;
            entity.setLocationAndAngles(0.0, 0.0, 0.0, 0.0f, 0.0f);
            entity.onInitialSpawn(entity.world.getDifficultyForLocation(entity.getPosition()), null);
            PreviewHelper.registerPreviewEntity((Entity)entity);
            return (T)entity;
        }
        catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void transformModelToCharacterSpace(IMat4x4d matrixOut) {
        TransformUtils.scale(matrixOut, -1.0, -1.0, 1.0);
        TransformUtils.translate(matrixOut, 0.0, -1.501f, 0.0);
    }

    public Mutator<?, ?, ?> getMutator(RenderLivingBase<? extends T> renderer) {
        Mutator<LivingEntityData<T>, T, ?> mutator = this.mutatorMap.get(renderer);
        if (mutator == null) {
            mutator = this.getMutatorFactory().createMutator(this.getDataFactory());
            if (!mutator.mutate(renderer)) {
                return null;
            }
            this.mutatorMap.put(renderer, mutator);
        }
        return mutator;
    }
}

