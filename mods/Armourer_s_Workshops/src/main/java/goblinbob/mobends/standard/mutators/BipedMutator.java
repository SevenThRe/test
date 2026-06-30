/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.client.renderer.entity.layers.LayerBipedArmor
 *  net.minecraft.client.renderer.entity.layers.LayerCustomHead
 *  net.minecraft.client.renderer.entity.layers.LayerHeldItem
 *  net.minecraft.client.renderer.entity.layers.LayerRenderer
 *  net.minecraft.entity.EntityLivingBase
 */
package goblinbob.mobends.standard.mutators;

import goblinbob.mobends.core.client.model.BoxSide;
import goblinbob.mobends.core.client.model.IModelPart;
import goblinbob.mobends.core.client.model.ModelPart;
import goblinbob.mobends.core.client.model.ModelPartExtended;
import goblinbob.mobends.core.client.model.ModelPartPostOffset;
import goblinbob.mobends.core.data.IEntityDataFactory;
import goblinbob.mobends.core.mutators.Mutator;
import goblinbob.mobends.standard.client.renderer.entity.layers.LayerCustomBipedArmor;
import goblinbob.mobends.standard.client.renderer.entity.layers.LayerCustomHeldItem;
import goblinbob.mobends.standard.data.BipedEntityData;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;

public abstract class BipedMutator<D extends BipedEntityData<E>, E extends EntityLivingBase, M extends ModelBiped>
extends Mutator<D, E, M> {
    protected ModelPartPostOffset body;
    protected ModelPart head;
    protected ModelPart headwear;
    protected ModelPartExtended leftArm;
    protected ModelPartExtended rightArm;
    protected ModelPartPostOffset leftForeArm;
    protected ModelPartPostOffset rightForeArm;
    protected ModelPartExtended leftLeg;
    protected ModelPartExtended rightLeg;
    protected ModelPart leftForeLeg;
    protected ModelPart rightForeLeg;
    protected LayerCustomBipedArmor layerArmor;
    protected LayerBipedArmor layerArmorVanilla;
    protected LayerCustomHeldItem layerHeldItem;
    protected LayerHeldItem layerHeldItemVanilla;
    protected LayerCustomHead layerCustomHead;
    protected LayerCustomHead layerCustomHeadVanilla;

    public BipedMutator(IEntityDataFactory<E> dataFactory) {
        super(dataFactory);
    }

    @Override
    public void storeVanillaModel(M model) {
        ((ModelBiped)this.vanillaModel).bipedBody = ((ModelBiped)model).bipedBody;
        ((ModelBiped)this.vanillaModel).bipedHead = ((ModelBiped)model).bipedHead;
        ((ModelBiped)this.vanillaModel).bipedHeadwear = ((ModelBiped)model).bipedHeadwear;
        ((ModelBiped)this.vanillaModel).bipedLeftArm = ((ModelBiped)model).bipedLeftArm;
        ((ModelBiped)this.vanillaModel).bipedLeftLeg = ((ModelBiped)model).bipedLeftLeg;
        ((ModelBiped)this.vanillaModel).bipedRightArm = ((ModelBiped)model).bipedRightArm;
        ((ModelBiped)this.vanillaModel).bipedRightLeg = ((ModelBiped)model).bipedRightLeg;
    }

    @Override
    public void applyVanillaModel(M model) {
        ((ModelBiped)model).bipedBody = ((ModelBiped)this.vanillaModel).bipedBody;
        ((ModelBiped)model).bipedHead = ((ModelBiped)this.vanillaModel).bipedHead;
        ((ModelBiped)model).bipedHeadwear = ((ModelBiped)this.vanillaModel).bipedHeadwear;
        ((ModelBiped)model).bipedLeftArm = ((ModelBiped)this.vanillaModel).bipedLeftArm;
        ((ModelBiped)model).bipedLeftLeg = ((ModelBiped)this.vanillaModel).bipedLeftLeg;
        ((ModelBiped)model).bipedRightArm = ((ModelBiped)this.vanillaModel).bipedRightArm;
        ((ModelBiped)model).bipedRightLeg = ((ModelBiped)this.vanillaModel).bipedRightLeg;
    }

    @Override
    public void swapLayer(RenderLivingBase<? extends E> renderer, int index, boolean isModelVanilla) {
        LayerRenderer layer = (LayerRenderer)this.layerRenderers.get(index);
        if (layer instanceof LayerBipedArmor) {
            this.layerArmor = new LayerCustomBipedArmor(renderer);
            if (isModelVanilla) {
                this.layerArmorVanilla = (LayerBipedArmor)layer;
            }
            this.layerRenderers.set(index, this.layerArmor);
        } else if (layer instanceof LayerHeldItem) {
            this.layerHeldItem = new LayerCustomHeldItem(renderer);
            if (isModelVanilla) {
                this.layerHeldItemVanilla = (LayerHeldItem)layer;
            }
            this.layerRenderers.set(index, this.layerHeldItem);
        } else if (layer instanceof LayerCustomHead) {
            this.layerCustomHead = new LayerCustomHead((ModelRenderer)this.head);
            if (isModelVanilla) {
                this.layerCustomHeadVanilla = (LayerCustomHead)layer;
            }
            this.layerRenderers.set(index, this.layerCustomHead);
        }
    }

    @Override
    public void deswapLayer(RenderLivingBase<? extends E> renderer, int index) {
        LayerRenderer layer = (LayerRenderer)this.layerRenderers.get(index);
        if (layer instanceof LayerCustomBipedArmor) {
            this.layerRenderers.set(index, this.layerArmorVanilla);
        } else if (layer instanceof LayerCustomHeldItem) {
            this.layerRenderers.set(index, this.layerHeldItemVanilla);
        } else if (layer instanceof LayerCustomHead) {
            this.layerRenderers.set(index, this.layerCustomHeadVanilla);
        }
    }

    @Override
    public boolean createParts(M original, float scaleFactor) {
        this.body = (ModelPartPostOffset)new ModelPartPostOffset((ModelBase)original, 16, 16).setPostOffset(0.0f, -12.0f, 0.0f).setPosition(0.0f, 12.0f, 0.0f);
        ((ModelBiped)original).bipedBody = this.body;
        this.body.addBox(-4.0f, -12.0f, -2.0f, 8, 12, 4, scaleFactor);
        this.head = new ModelPart((ModelBase)original, 0, 0).setParent(this.body).setPosition(0.0f, -12.0f, 0.0f);
        ((ModelBiped)original).bipedHead = this.head;
        this.head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, scaleFactor);
        int armWidth = 4;
        float armY = -10.0f;
        this.leftArm = (ModelPartExtended)new ModelPartExtended((ModelBase)original, 40, 16).setParent(this.body).setPosition(5.0f, armY, 0.0f).setMirror(true);
        ((ModelBiped)original).bipedLeftArm = this.leftArm;
        this.leftArm.developBox(-1.0f, -2.0f, -2.0f, armWidth, 6, 4, scaleFactor).inflate(0.01f, 0.0f, 0.01f).hideFace(BoxSide.BOTTOM).create();
        this.rightArm = (ModelPartExtended)new ModelPartExtended((ModelBase)original, 40, 16).setParent(this.body).setPosition(-5.0f, armY, 0.0f);
        ((ModelBiped)original).bipedRightArm = this.rightArm;
        this.rightArm.developBox(-armWidth + 1, -2.0f, -2.0f, armWidth, 6, 4, scaleFactor).inflate(0.01f, 0.0f, 0.1f).hideFace(BoxSide.BOTTOM).create();
        this.leftForeArm = (ModelPartPostOffset)new ModelPartPostOffset((ModelBase)original, 40, 22).setPostOffset(0.0f, -4.0f, -2.0f).setParent(this.leftArm).setPosition(0.0f, 4.0f, 2.0f).setMirror(true);
        this.leftForeArm.developBox(-1.0f, 0.0f, -4.0f, armWidth, 6, 4, scaleFactor).hideFace(BoxSide.TOP).offsetTextureQuad(BoxSide.BOTTOM, 0.0f, -6.0f).create();
        this.leftArm.setExtension(this.leftForeArm);
        this.rightForeArm = (ModelPartPostOffset)new ModelPartPostOffset((ModelBase)original, 40, 22).setPostOffset(0.0f, -4.0f, -2.0f).setParent(this.rightArm).setPosition(0.0f, 4.0f, 2.0f);
        this.rightForeArm.developBox(-armWidth + 1, 0.0f, -4.0f, armWidth, 6, 4, scaleFactor).hideFace(BoxSide.TOP).offsetTextureQuad(BoxSide.BOTTOM, 0.0f, -6.0f).create();
        this.rightArm.setExtension(this.rightForeArm);
        this.rightLeg = (ModelPartExtended)new ModelPartExtended((ModelBase)original, 0, 16).setPosition(0.0f, 12.0f, 0.0f);
        ((ModelBiped)original).bipedRightLeg = this.rightLeg;
        this.rightLeg.addBox(-1.9f, 0.0f, -2.0f, 4, 6, 4, scaleFactor);
        this.leftLeg = (ModelPartExtended)new ModelPartExtended((ModelBase)original, 0, 16).setPosition(0.0f, 12.0f, 0.0f).setMirror(true);
        ((ModelBiped)original).bipedLeftLeg = this.leftLeg;
        this.leftLeg.addBox(-0.1f, 0.0f, -2.0f, 4, 6, 4, scaleFactor);
        this.leftForeLeg = new ModelPart((ModelBase)original, 0, 22).setParent(this.leftLeg).setPosition(0.0f, 6.0f, -2.0f).setMirror(true);
        this.leftForeLeg.developBox(-2.1f, 0.0f, 0.0f, 4, 6, 4, scaleFactor).inflate(0.01f, 0.0f, 0.01f).offsetTextureQuad(BoxSide.BOTTOM, 0.0f, -6.0f).create();
        this.leftLeg.setExtension(this.leftForeLeg);
        this.rightForeLeg = new ModelPart((ModelBase)original, 0, 22).setParent(this.rightLeg).setPosition(0.0f, 6.0f, -2.0f);
        this.rightForeLeg.developBox(-1.9f, 0.0f, 0.0f, 4, 6, 4, scaleFactor).inflate(0.01f, 0.0f, 0.01f).offsetTextureQuad(BoxSide.BOTTOM, 0.0f, -6.0f).create();
        this.rightLeg.setExtension(this.rightForeLeg);
        this.headwear = new ModelPart((ModelBase)original, 32, 0).setParent(this.head);
        ((ModelBiped)original).bipedHeadwear = this.headwear;
        this.headwear.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, scaleFactor + 0.5f);
        return true;
    }

    @Override
    public void syncUpWithData(D data) {
        this.head.syncUp(((BipedEntityData)data).head);
        this.body.syncUp(((BipedEntityData)data).body);
        this.leftArm.syncUp(((BipedEntityData)data).leftArm);
        this.rightArm.syncUp(((BipedEntityData)data).rightArm);
        this.leftLeg.syncUp(((BipedEntityData)data).leftLeg);
        this.rightLeg.syncUp(((BipedEntityData)data).rightLeg);
        this.leftForeArm.syncUp(((BipedEntityData)data).leftForeArm);
        this.rightForeArm.syncUp(((BipedEntityData)data).rightForeArm);
        this.leftForeLeg.syncUp(((BipedEntityData)data).leftForeLeg);
        this.rightForeLeg.syncUp(((BipedEntityData)data).rightForeLeg);
    }

    @Override
    public boolean isModelVanilla(M model) {
        return !(((ModelBiped)model).bipedBody instanceof IModelPart);
    }

    @Override
    public void updateModel(E entity, RenderLivingBase<? extends E> renderer, float partialTicks) {
        super.updateModel(entity, renderer, partialTicks);
        this.head.name = "\u5934";
        this.body.name = "\u8eab\u4f53";
        this.leftArm.name = "\u5de6\u80f3\u818a";
        this.rightArm.name = "\u53f3\u80f3\u818a";
        this.leftForeArm.name = "\u5de6\u624b\u81c2";
        this.rightForeArm.name = "\u53f3\u624b\u81c2";
        this.leftLeg.name = "\u5de6\u5927\u817f";
        this.rightLeg.name = "\u53f3\u5927\u817f";
        this.leftForeLeg.name = "\u5de6\u5c0f\u817f";
        this.rightForeLeg.name = "\u53f3\u5c0f\u817f";
        this.head.renderEntity = entity;
        this.body.renderEntity = entity;
        this.leftArm.renderEntity = entity;
        this.rightArm.renderEntity = entity;
        this.leftForeArm.renderEntity = entity;
        this.rightForeArm.renderEntity = entity;
        this.leftLeg.renderEntity = entity;
        this.rightLeg.renderEntity = entity;
        this.leftForeLeg.renderEntity = entity;
        this.rightForeLeg.renderEntity = entity;
    }
}

