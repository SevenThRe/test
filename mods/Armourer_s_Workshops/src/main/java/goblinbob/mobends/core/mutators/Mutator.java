/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.client.renderer.entity.layers.LayerRenderer
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.math.MathHelper
 */
package goblinbob.mobends.core.mutators;

import goblinbob.mobends.core.animation.controller.IAnimationController;
import goblinbob.mobends.core.data.EntityData;
import goblinbob.mobends.core.data.EntityDatabase;
import goblinbob.mobends.core.data.IEntityDataFactory;
import goblinbob.mobends.core.data.LivingEntityData;
import goblinbob.mobends.core.kumo.variable.KumoVariableRegistry;
import goblinbob.mobends.core.math.vector.SmoothVector3f;
import goblinbob.mobends.core.network.NetworkConfiguration;
import goblinbob.mobends.core.pack.BendsPackPerformer;
import goblinbob.mobends.core.util.GUtil;
import java.util.Collection;
import java.util.List;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public abstract class Mutator<D extends LivingEntityData<E>, E extends EntityLivingBase, M extends ModelBase> {
    protected M vanillaModel;
    protected float headYaw;
    protected float headPitch;
    protected float limbSwing;
    protected float limbSwingAmount;
    protected float swingProgress;
    private IEntityDataFactory<E> dataFactory;
    protected List<LayerRenderer<?>> layerRenderers;

    public Mutator(IEntityDataFactory<E> dataFactory) {
        this.dataFactory = dataFactory;
    }

    public void fetchFields(RenderLivingBase<? extends E> renderer) {
        this.layerRenderers = renderer.layerRenderers;
    }

    public abstract void storeVanillaModel(M var1);

    public abstract void applyVanillaModel(M var1);

    public abstract void swapLayer(RenderLivingBase<? extends E> var1, int var2, boolean var3);

    public abstract void deswapLayer(RenderLivingBase<? extends E> var1, int var2);

    public abstract boolean createParts(M var1, float var2);

    public boolean mutate(RenderLivingBase<? extends E> renderer) {
        if (renderer.getMainModel() == null || this.shouldModelBeSkipped(renderer.getMainModel())) {
            return false;
        }
        this.fetchFields(renderer);
        ModelBase model = renderer.getMainModel();
        float scaleFactor = 0.0f;
        boolean isModelVanilla = this.isModelVanilla(model);
        if (isModelVanilla) {
            this.storeVanillaModel(model);
        }
        this.createParts(model, scaleFactor);
        if (this.layerRenderers != null) {
            for (int i2 = 0; i2 < this.layerRenderers.size(); ++i2) {
                this.swapLayer(renderer, i2, isModelVanilla);
            }
        }
        return true;
    }

    public void demutate(RenderLivingBase<? extends E> renderer) {
        if (this.shouldModelBeSkipped(renderer.getMainModel())) {
            return;
        }
        ModelBase model = renderer.getMainModel();
        this.applyVanillaModel(model);
        if (this.layerRenderers != null) {
            for (int i2 = 0; i2 < this.layerRenderers.size(); ++i2) {
                this.deswapLayer(renderer, i2);
            }
        }
    }

    public void updateModel(E entity, RenderLivingBase<? extends E> renderer, float partialTicks) {
        boolean shouldSit = entity.isRiding() && entity.getRidingEntity() != null && entity.getRidingEntity().shouldRiderSit();
        float f2 = GUtil.interpolateRotation(((EntityLivingBase)entity).prevRenderYawOffset, ((EntityLivingBase)entity).renderYawOffset, partialTicks);
        float f1 = GUtil.interpolateRotation(((EntityLivingBase)entity).prevRotationYawHead, ((EntityLivingBase)entity).rotationYawHead, partialTicks);
        float yaw = f1 - f2;
        if (shouldSit && entity.getRidingEntity() instanceof EntityLivingBase) {
            EntityLivingBase entitylivingbase = (EntityLivingBase)entity.getRidingEntity();
            f2 = GUtil.interpolateRotation(entitylivingbase.prevRenderYawOffset, entitylivingbase.renderYawOffset, partialTicks);
            yaw = f1 - f2;
            float f3 = MathHelper.wrapDegrees((float)yaw);
            if (f3 < -85.0f) {
                f3 = -85.0f;
            }
            if (f3 >= 85.0f) {
                f3 = 85.0f;
            }
            f2 = f1 - f3;
            if (f3 * f3 > 2500.0f) {
                f2 += f3 * 0.2f;
            }
            yaw = f1 - f2;
        }
        float pitch = ((EntityLivingBase)entity).prevRotationPitch + (((EntityLivingBase)entity).rotationPitch - ((EntityLivingBase)entity).prevRotationPitch) * partialTicks;
        float f5 = 0.0f;
        float f6 = 0.0f;
        if (!entity.isRiding()) {
            f5 = ((EntityLivingBase)entity).prevLimbSwingAmount + (((EntityLivingBase)entity).limbSwingAmount - ((EntityLivingBase)entity).prevLimbSwingAmount) * partialTicks;
            f6 = ((EntityLivingBase)entity).limbSwing - ((EntityLivingBase)entity).limbSwingAmount * (1.0f - partialTicks);
            if (entity.isChild()) {
                f6 *= 3.0f;
            }
            if (f5 > 1.0f) {
                f5 = 1.0f;
            }
            yaw = f1 - f2;
        }
        this.headYaw = yaw;
        this.headPitch = pitch;
        this.limbSwing = f6;
        this.limbSwingAmount = f5;
        this.swingProgress = entity.getSwingProgress(partialTicks);
    }

    public void performAnimations(D data, String animatedEntityKey, RenderLivingBase<? extends E> renderer, float partialTicks) {
        ((LivingEntityData)data).headYaw.set(Float.valueOf(MathHelper.wrapDegrees((float)this.headYaw)));
        ((LivingEntityData)data).headPitch.set(Float.valueOf(MathHelper.wrapDegrees((float)this.headPitch)));
        ((LivingEntityData)data).limbSwing.set(Float.valueOf(this.limbSwing));
        ((LivingEntityData)data).limbSwingAmount.set(Float.valueOf(this.limbSwingAmount));
        ((LivingEntityData)data).swingProgress.set(Float.valueOf(this.swingProgress));
        KumoVariableRegistry.instance.provideTemporaryData((EntityData<?>)data);
        IAnimationController<?> controller = ((EntityData)data).getController();
        Collection<String> actions = controller.perform(data);
        SmoothVector3f lastGlobalOffset = new SmoothVector3f(((LivingEntityData)data).globalOffset);
        SmoothVector3f lastLocalOffset = new SmoothVector3f(((LivingEntityData)data).localOffset);
        if (NetworkConfiguration.instance.areBendsPacksAllowed()) {
            BendsPackPerformer.INSTANCE.performCurrentPack((EntityData<?>)data, animatedEntityKey, actions);
            if (NetworkConfiguration.instance.isMovementLimited()) {
                ((LivingEntityData)data).globalOffset.limitDistanceTo(lastGlobalOffset, 10.0f);
                ((LivingEntityData)data).localOffset.limitDistanceTo(lastLocalOffset, 10.0f);
            }
        }
    }

    public abstract void syncUpWithData(D var1);

    public D getData(E entity) {
        return (D)EntityDatabase.instance.get(entity);
    }

    public D getOrMakeData(E entity) {
        return (D)EntityDatabase.instance.getOrMake(this.dataFactory, entity);
    }

    public abstract boolean isModelVanilla(M var1);

    public abstract boolean shouldModelBeSkipped(ModelBase var1);

    public void postRefresh() {
    }

    public void resetArmWear() {
    }
}

