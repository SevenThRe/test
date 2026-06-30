/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  eos.moe.dragoncore.api.model.AnimationModelRenderer
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelPlayer
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.client.renderer.entity.layers.LayerCape
 *  net.minecraft.client.renderer.entity.layers.LayerElytra
 *  net.minecraft.client.renderer.entity.layers.LayerRenderer
 */
package goblinbob.mobends.standard.mutators;

import eos.moe.armourers.interfaces.IRenderPlayer;
import eos.moe.dragoncore.api.model.AnimationModelRenderer;
import goblinbob.mobends.core.client.model.BoxSide;
import goblinbob.mobends.core.client.model.IModelPart;
import goblinbob.mobends.core.client.model.ModelPart;
import goblinbob.mobends.core.client.model.ModelPartExtended;
import goblinbob.mobends.core.client.model.ModelPartPostOffset;
import goblinbob.mobends.core.data.IEntityDataFactory;
import goblinbob.mobends.standard.client.renderer.entity.layers.LayerCustomCape;
import goblinbob.mobends.standard.client.renderer.entity.layers.LayerCustomElytra;
import goblinbob.mobends.standard.data.PlayerData;
import goblinbob.mobends.standard.mutators.BipedMutator;
import goblinbob.mobends.standard.previewer.PlayerPreviewer;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerCape;
import net.minecraft.client.renderer.entity.layers.LayerElytra;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;

public class PlayerMutator
extends BipedMutator<PlayerData, AbstractClientPlayer, ModelPlayer> {
    protected ModelPart bodywear;
    protected ModelPart leftArmwear;
    protected ModelPart rightArmwear;
    protected ModelPart leftForeArmwear;
    protected ModelPart rightForeArmwear;
    protected ModelPart leftLegwear;
    protected ModelPart rightLegwear;
    protected ModelPart leftForeLegwear;
    protected ModelPart rightForeLegwear;
    protected boolean smallArms;
    protected LayerCustomCape layerCape;
    protected LayerCape layerCapeVanilla;
    protected LayerCustomElytra layerElytra;
    protected LayerElytra layerElytraVanilla;

    public PlayerMutator(IEntityDataFactory<AbstractClientPlayer> dataFactory) {
        super(dataFactory);
    }

    public boolean hasSmallArms() {
        return this.smallArms;
    }

    @Override
    public boolean mutate(RenderLivingBase<? extends AbstractClientPlayer> renderer) {
        return super.mutate(renderer);
    }

    @Override
    public void demutate(RenderLivingBase<? extends AbstractClientPlayer> renderer) {
        super.demutate(renderer);
    }

    @Override
    public void fetchFields(RenderLivingBase<? extends AbstractClientPlayer> renderer) {
        super.fetchFields(renderer);
        this.smallArms = ((IRenderPlayer)renderer).isSmallArms();
    }

    @Override
    public void storeVanillaModel(ModelPlayer model) {
        ModelPlayer vanillaModel = new ModelPlayer(0.0f, this.smallArms);
        this.vanillaModel = vanillaModel;
        super.storeVanillaModel(model);
        vanillaModel.field_178730_v = model.field_178730_v;
        vanillaModel.field_178734_a = model.field_178734_a;
        vanillaModel.field_178733_c = model.field_178733_c;
        vanillaModel.field_178732_b = model.field_178732_b;
        vanillaModel.field_178731_d = model.field_178731_d;
    }

    @Override
    public void applyVanillaModel(ModelPlayer model) {
        super.applyVanillaModel(model);
        model.field_178730_v = ((ModelPlayer)this.vanillaModel).field_178730_v;
        model.field_178734_a = ((ModelPlayer)this.vanillaModel).field_178734_a;
        model.field_178733_c = ((ModelPlayer)this.vanillaModel).field_178733_c;
        model.field_178732_b = ((ModelPlayer)this.vanillaModel).field_178732_b;
        model.field_178731_d = ((ModelPlayer)this.vanillaModel).field_178731_d;
    }

    @Override
    public void swapLayer(RenderLivingBase<? extends AbstractClientPlayer> renderer, int index, boolean isModelVanilla) {
        super.swapLayer(renderer, index, isModelVanilla);
        LayerRenderer layer = (LayerRenderer)this.layerRenderers.get(index);
        if (layer instanceof LayerCape) {
            this.layerCape = new LayerCustomCape((RenderPlayer)renderer);
            if (isModelVanilla) {
                this.layerCapeVanilla = (LayerCape)layer;
            }
            this.layerRenderers.set(index, this.layerCape);
        }
        if (layer instanceof LayerElytra) {
            this.layerElytra = new LayerCustomElytra((RenderPlayer)renderer);
            if (isModelVanilla) {
                this.layerElytraVanilla = (LayerElytra)layer;
            }
            this.layerRenderers.set(index, this.layerElytra);
        }
    }

    @Override
    public void deswapLayer(RenderLivingBase<? extends AbstractClientPlayer> renderer, int index) {
        super.deswapLayer(renderer, index);
        LayerRenderer layer = (LayerRenderer)this.layerRenderers.get(index);
        if (layer instanceof LayerCustomCape) {
            this.layerRenderers.set(index, this.layerCapeVanilla);
        }
        if (layer instanceof LayerCustomElytra) {
            this.layerRenderers.set(index, this.layerElytraVanilla);
        }
    }

    @Override
    public boolean createParts(ModelPlayer original, float scaleFactor) {
        super.createParts(original, scaleFactor);
        int armWidth = this.smallArms ? 3 : 4;
        float armY = this.smallArms ? -9.5f : -10.0f;
        this.leftArm = new ModelPartExtended((ModelBase)original, 32, 48);
        original.field_178724_i = this.leftArm;
        this.leftArm.setParent(this.body).setPosition(5.0f, armY, 0.0f).developBox(-2.0f + (float)(this.smallArms ? 1 : 0), -2.0f, -2.0f, armWidth, 6, 4, scaleFactor).inflate(0.01f, 0.0f, 0.01f).hideFace(BoxSide.BOTTOM).create();
        this.rightArm = new ModelPartExtended((ModelBase)original, 40, 16);
        original.field_178723_h = this.rightArm;
        this.rightArm.setParent(this.body).setPosition(-5.0f, armY, 0.0f).developBox(-armWidth + 2 + (this.smallArms ? -1 : 0), -2.0f, -2.0f, armWidth, 6, 4, scaleFactor).inflate(0.01f, 0.0f, 0.01f).hideFace(BoxSide.BOTTOM).create();
        this.leftForeArm = new ModelPartPostOffset((ModelBase)original, 32, 54).setPostOffset(0.0f, -4.0f, -2.0f);
        this.leftForeArm.setPosition(0.0f, 4.0f, 2.0f).setParent(this.leftArm).developBox(-2.0f + (float)(this.smallArms ? 1 : 0), 0.0f, -4.0f, armWidth, 6, 4, scaleFactor).hideFace(BoxSide.TOP).offsetTextureQuad(BoxSide.BOTTOM, 0.0f, -6.0f).create();
        this.leftArm.setExtension(this.leftForeArm);
        this.rightForeArm = new ModelPartPostOffset((ModelBase)original, 40, 22).setPostOffset(0.0f, -4.0f, -2.0f);
        this.rightForeArm.setPosition(0.0f, 4.0f, 2.0f).setParent(this.rightArm).developBox(-armWidth + 2 + (this.smallArms ? -1 : 0), 0.0f, -4.0f, armWidth, 6, 4, scaleFactor).hideFace(BoxSide.TOP).offsetTextureQuad(BoxSide.BOTTOM, 0.0f, -6.0f).create();
        this.rightArm.setExtension(this.rightForeArm);
        this.leftLeg = (ModelPartExtended)new ModelPartExtended((ModelBase)original, 16, 48).setPosition(0.0f, 12.0f, 0.0f);
        original.field_178722_k = this.leftLeg;
        this.leftLeg.func_78790_a(-2.1f, 0.0f, -2.0f, 4, 6, 4, scaleFactor);
        this.leftLeg.setExtension(this.leftForeLeg);
        this.bodywear = new ModelPart((ModelBase)original, 16, 32);
        original.field_178730_v = this.bodywear;
        this.bodywear.setParent(this.body);
        this.bodywear.func_78790_a(-4.0f, -12.0f, -2.0f, 8, 12, 4, scaleFactor + 0.25f);
        float limbWearHeight = 6.0f + 2.0f * scaleFactor + 0.5f - 0.25f;
        if (this.smallArms) {
            this.leftArmwear = new ModelPart((ModelBase)original, 48, 48);
            original.field_178734_a = this.leftArmwear;
            this.leftArmwear.setParent(this.leftArm).developBox(-1.0f, -2.0f, -2.0f, 3, 6, 4, scaleFactor + 0.25f).setHeight(limbWearHeight).inflate(0.0025f, 0.0f, 0.0025f).hideFace(BoxSide.BOTTOM).create();
            this.rightArmwear = new ModelPart((ModelBase)original, 40, 32);
            original.field_178732_b = this.rightArmwear;
            this.rightArmwear.setParent(this.rightArm).developBox(-armWidth + 1, -2.0f, -2.0f, 3, 6, 4, scaleFactor + 0.25f).setHeight(limbWearHeight).inflate(0.0025f, 0.0f, 0.0025f).hideFace(BoxSide.BOTTOM).create();
            this.leftForeArmwear = new ModelPart((ModelBase)original, 48, 54);
            this.leftForeArmwear.developBox(-1.0f, 0.0f, -4.0f, 3, 6, 4, scaleFactor + 0.25f).setHeight(limbWearHeight).inflate(0.005f, 0.0f, 0.005f).offset(0.0f, 0.25f, 0.0f).hideFace(BoxSide.TOP).offsetTextureQuad(BoxSide.BOTTOM, 0.0f, -6.0f).create();
            this.leftForeArm.func_78792_a(this.leftForeArmwear);
            this.rightForeArmwear = new ModelPart((ModelBase)original, 40, 38);
            this.rightForeArmwear.developBox(-armWidth + 1, 0.0f, -4.0f, 3, 6, 4, scaleFactor + 0.25f).setHeight(limbWearHeight).inflate(0.005f, 0.0f, 0.005f).offset(0.0f, 0.25f, 0.0f).hideFace(BoxSide.TOP).offsetTextureQuad(BoxSide.BOTTOM, 0.0f, -6.0f).create();
            this.rightForeArm.func_78792_a(this.rightForeArmwear);
        } else {
            this.leftArmwear = new ModelPart((ModelBase)original, 48, 48);
            original.field_178734_a = this.leftArmwear;
            this.leftArmwear.setParent(this.leftArm).developBox(-2.0f, -2.0f, -2.0f, 4, 6, 4, scaleFactor + 0.25f).setHeight(limbWearHeight).inflate(0.0025f, 0.0f, 0.0025f).hideFace(BoxSide.BOTTOM).create();
            this.rightArmwear = new ModelPart((ModelBase)original, 40, 32);
            original.field_178732_b = this.rightArmwear;
            this.rightArmwear.setParent(this.rightArm).developBox(-2.0f, -2.0f, -2.0f, 4, 6, 4, scaleFactor + 0.25f).setHeight(limbWearHeight).inflate(0.0025f, 0.0f, 0.0025f).hideFace(BoxSide.BOTTOM).create();
            this.leftForeArmwear = new ModelPart((ModelBase)original, 48, 54);
            this.leftForeArmwear.developBox(-2.0f, 0.0f, -4.0f, 4, 6, 4, scaleFactor + 0.25f).setHeight(limbWearHeight).inflate(0.005f, 0.0f, 0.005f).offset(0.0f, 0.25f, 0.0f).hideFace(BoxSide.TOP).offsetTextureQuad(BoxSide.BOTTOM, 0.0f, -6.0f).create();
            this.leftForeArm.func_78792_a(this.leftForeArmwear);
            this.rightForeArmwear = new ModelPart((ModelBase)original, 40, 38);
            this.rightForeArmwear.developBox(-2.0f, 0.0f, -4.0f, 4, 6, 4, scaleFactor + 0.25f).setHeight(limbWearHeight).inflate(0.005f, 0.0f, 0.005f).offset(0.0f, 0.25f, 0.0f).hideFace(BoxSide.TOP).offsetTextureQuad(BoxSide.BOTTOM, 0.0f, -6.0f).create();
            this.rightForeArm.func_78792_a(this.rightForeArmwear);
        }
        this.leftLegwear = new ModelPart((ModelBase)original, 0, 48);
        original.field_178733_c = this.leftLegwear;
        this.leftLegwear.setParent(this.leftLeg).developBox(-2.1f, 0.0f, -2.0f, 4, 6, 4, scaleFactor + 0.25f).setHeight(limbWearHeight).hideFace(BoxSide.BOTTOM).create();
        this.rightLegwear = new ModelPart((ModelBase)original, 0, 32);
        original.field_178731_d = this.rightLegwear;
        this.rightLegwear.setParent(this.rightLeg).developBox(-1.9f, 0.0f, -2.0f, 4, 6, 4, scaleFactor + 0.25f).setHeight(limbWearHeight).hideFace(BoxSide.BOTTOM).create();
        this.leftForeLegwear = new ModelPart((ModelBase)original, 0, 54);
        this.leftForeLegwear.developBox(-2.1f, 0.0f, 0.0f, 4, 6, 4, scaleFactor + 0.25f).setHeight(limbWearHeight).inflate(0.005f, 0.0f, 0.005f).offset(0.0f, 0.25f, 0.0f).hideFace(BoxSide.TOP).offsetTextureQuad(BoxSide.BOTTOM, 0.0f, -6.0f).create();
        this.leftForeLeg.func_78792_a(this.leftForeLegwear);
        this.rightForeLegwear = new ModelPart((ModelBase)original, 0, 38);
        this.rightForeLegwear.developBox(-1.9f, 0.0f, 0.0f, 4, 6, 4, scaleFactor + 0.25f).setHeight(limbWearHeight).inflate(0.005f, 0.0f, 0.005f).offset(0.0f, 0.25f, 0.0f).hideFace(BoxSide.TOP).offsetTextureQuad(BoxSide.BOTTOM, 0.0f, -6.0f).create();
        this.rightForeLeg.func_78792_a(this.rightForeLegwear);
        return true;
    }

    @Override
    public void resetArmWear() {
        this.leftForeArmwear.setVisible(this.leftArmwear.isShowing());
        this.rightForeArmwear.setVisible(this.rightArmwear.isShowing());
        this.leftForeLegwear.setVisible(this.leftLegwear.isShowing());
        this.rightForeLegwear.setVisible(this.rightLegwear.isShowing());
    }

    @Override
    public void performAnimations(PlayerData data, String animatedEntityKey, RenderLivingBase<? extends AbstractClientPlayer> renderer, float partialTicks) {
        this.resetArmWear();
        super.performAnimations(data, animatedEntityKey, renderer, partialTicks);
    }

    @Override
    public void postRefresh() {
        if (this.layerArmor != null) {
            this.layerArmor.func_177177_a();
        }
    }

    public void poseForFirstPersonView() {
        IModelPart[] iModelParts;
        for (IModelPart iModelPart : iModelParts = new IModelPart[]{this.body, this.rightArm, this.rightForeArm, this.leftArm, this.leftForeArm}) {
            iModelPart.getRotation().identity();
            if (!(iModelPart instanceof AnimationModelRenderer)) continue;
            AnimationModelRenderer modelPart = (AnimationModelRenderer)iModelPart;
            modelPart.clear();
        }
    }

    @Override
    public boolean isModelVanilla(ModelPlayer model) {
        return !(model.field_78115_e instanceof IModelPart);
    }

    @Override
    public boolean shouldModelBeSkipped(ModelBase model) {
        return !(model instanceof ModelPlayer);
    }

    @Override
    public PlayerData getData(AbstractClientPlayer entity) {
        return PlayerPreviewer.isPreviewInProgress() ? PlayerPreviewer.getPreviewData() : (PlayerData)super.getData(entity);
    }

    @Override
    public PlayerData getOrMakeData(AbstractClientPlayer entity) {
        return PlayerPreviewer.isPreviewInProgress() ? PlayerPreviewer.getPreviewData() : (PlayerData)super.getOrMakeData(entity);
    }
}

