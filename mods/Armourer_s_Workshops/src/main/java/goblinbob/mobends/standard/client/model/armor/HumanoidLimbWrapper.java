/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 */
package goblinbob.mobends.standard.client.model.armor;

import goblinbob.mobends.core.client.model.BoxFactory;
import goblinbob.mobends.core.client.model.BoxMutator;
import goblinbob.mobends.core.client.model.IModelPart;
import goblinbob.mobends.core.client.model.ModelPart;
import goblinbob.mobends.standard.client.model.armor.ArmorWrapper;
import goblinbob.mobends.standard.client.model.armor.IPartWrapper;
import goblinbob.mobends.standard.client.model.armor.MalformedArmorModelException;
import goblinbob.mobends.standard.client.model.armor.PartContainer;
import goblinbob.mobends.standard.data.BipedEntityData;
import java.util.List;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class HumanoidLimbWrapper
implements IPartWrapper {
    private final ModelRenderer vanillaPart;
    protected IPartWrapper.DataPartSelector upperPartDataSelector;
    protected IPartWrapper.ModelPartSetter modelPartSetter;
    private final IPartWrapper.DataPartSelector lowerPartDataSelector;
    private final float inflation;
    private ModelPart upperPart;
    private ModelPart upperPartAnchor;
    private ModelPart lowerPart;
    private ModelPart lowerPartAnchor;

    public HumanoidLimbWrapper(ModelBiped vanillaModel, ModelRenderer vanillaPart, IPartWrapper.ModelPartSetter modelPartSetter, IPartWrapper.DataPartSelector upperPartDataSelector, IPartWrapper.DataPartSelector lowerPartDataSelector, float cutPlane, float inflation) {
        this.vanillaPart = vanillaPart;
        this.upperPartDataSelector = upperPartDataSelector;
        this.lowerPartDataSelector = lowerPartDataSelector;
        this.modelPartSetter = modelPartSetter;
        this.inflation = inflation;
        if (vanillaPart instanceof PartContainer) {
            throw new MalformedArmorModelException("Tried to mutate a previously mutated part. A ModelRenderer instance has to have been used between Model instances.");
        }
        this.upperPart = new ModelPart((ModelBase)vanillaModel, false);
        this.upperPartAnchor = new ModelPart((ModelBase)vanillaModel, false);
        this.lowerPart = new ModelPart((ModelBase)vanillaModel, false);
        this.lowerPartAnchor = new ModelPart((ModelBase)vanillaModel, false);
        this.upperPart.addChild(this.upperPartAnchor);
        this.upperPart.addChild(this.lowerPart);
        this.lowerPart.addChild(this.lowerPartAnchor);
        this.lowerPart.mirror = this.lowerPartAnchor.mirror = vanillaPart.mirror;
        this.upperPartAnchor.mirror = this.lowerPartAnchor.mirror;
        this.upperPart.mirror = this.lowerPartAnchor.mirror;
        this.sliceAppendage(vanillaModel, vanillaPart, cutPlane);
    }

    private void sliceAppendage(ModelBiped vanillaModel, ModelRenderer vanillaPart, float cutPlane) {
        List vanillaBoxes = vanillaPart.cubeList;
        for (ModelBox box : vanillaBoxes) {
            BoxMutator mutator = BoxMutator.createFrom((ModelBase)vanillaModel, vanillaPart, box);
            if (mutator == null) continue;
            if (mutator.getFactory().min.y < cutPlane) {
                BoxFactory lowerPartFactory = mutator.sliceFromBottom(cutPlane);
                this.upperPartAnchor.addBox(mutator.getFactory().inflate(this.inflation, 0.0f, this.inflation).create(this.upperPart));
                if (lowerPartFactory == null) continue;
                float lowerInflation = this.inflation + 0.001f;
                this.lowerPartAnchor.addBox(lowerPartFactory.inflate(lowerInflation, 0.0f, lowerInflation).create(this.upperPart));
                continue;
            }
            this.lowerPartAnchor.addVanillaBox(box);
        }
        List vanillaChildren = vanillaPart.childModels;
        if (vanillaChildren != null) {
            for (ModelRenderer child : vanillaChildren) {
                if (child == null) continue;
                if (child.rotationPointY < cutPlane) {
                    this.upperPartAnchor.addChild(child);
                    continue;
                }
                this.lowerPartAnchor.addChild(child);
            }
        }
    }

    @Override
    public void syncUp(BipedEntityData<?> data) {
        this.upperPart.syncUp(this.upperPartDataSelector.selectPart(data));
        if (this.lowerPart != null) {
            this.lowerPart.syncUp(this.lowerPartDataSelector.selectPart(data));
        }
    }

    @Override
    public void apply(ArmorWrapper armorWrapper) {
        this.modelPartSetter.replacePart(armorWrapper, this.upperPart);
        this.modelPartSetter.replacePart(armorWrapper.original, this.upperPart);
        this.upperPart.isHidden = this.vanillaPart.isHidden;
        this.upperPart.showModel = this.vanillaPart.showModel;
    }

    @Override
    public void deapply(ArmorWrapper armorWrapper) {
        this.modelPartSetter.replacePart(armorWrapper, this.vanillaPart);
        this.modelPartSetter.replacePart(armorWrapper.original, this.vanillaPart);
        this.vanillaPart.isHidden = this.upperPart.isHidden;
        this.vanillaPart.showModel = this.upperPart.showModel;
    }

    @Override
    public IPartWrapper setParent(IModelPart parent) {
        this.upperPart.setParent(parent);
        return this;
    }

    @Override
    public IPartWrapper offsetInner(float x2, float y2, float z2) {
        this.upperPartAnchor.setPosition(x2, y2, z2);
        return this;
    }

    public HumanoidLimbWrapper offsetLower(float x2, float y2, float z2) {
        this.lowerPartAnchor.setPosition(x2, y2, z2);
        return this;
    }
}

