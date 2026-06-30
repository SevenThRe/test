/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 */
package goblinbob.mobends.standard.client.model.armor;

import goblinbob.mobends.core.client.model.IModelPart;
import goblinbob.mobends.standard.client.model.armor.ArmorWrapper;
import goblinbob.mobends.standard.client.model.armor.IPartWrapper;
import goblinbob.mobends.standard.client.model.armor.MalformedArmorModelException;
import goblinbob.mobends.standard.client.model.armor.PartContainer;
import goblinbob.mobends.standard.data.BipedEntityData;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public class HumanoidPartWrapper
implements IPartWrapper {
    protected IPartWrapper.DataPartSelector dataPartSelector;
    protected IPartWrapper.ModelPartSetter modelPartSetter;
    protected ModelRenderer vanillaPart;
    protected PartContainer partContainer;

    public HumanoidPartWrapper(ModelBiped vanillaModel, ModelRenderer vanillaPart, IPartWrapper.ModelPartSetter modelPartSetter, IPartWrapper.DataPartSelector dataPartSelector) {
        this.dataPartSelector = dataPartSelector;
        this.modelPartSetter = modelPartSetter;
        this.vanillaPart = vanillaPart;
        if (vanillaPart instanceof PartContainer) {
            throw new MalformedArmorModelException("Tried to mutate a previously mutated part. A ModelRenderer instance has to have been used between Model instances.");
        }
        this.partContainer = new PartContainer((ModelBase)vanillaModel, vanillaPart);
    }

    @Override
    public void syncUp(BipedEntityData<?> data) {
        this.partContainer.syncUp(this.dataPartSelector.selectPart(data));
    }

    @Override
    public void apply(ArmorWrapper armorWrapper) {
        this.modelPartSetter.replacePart(armorWrapper, this.partContainer);
        this.modelPartSetter.replacePart(armorWrapper.original, this.partContainer);
        this.partContainer.isHidden = this.vanillaPart.isHidden;
        this.partContainer.showModel = this.vanillaPart.showModel;
    }

    @Override
    public void deapply(ArmorWrapper armorWrapper) {
        this.modelPartSetter.replacePart(armorWrapper, this.vanillaPart);
        this.modelPartSetter.replacePart(armorWrapper.original, this.vanillaPart);
        this.vanillaPart.isHidden = this.partContainer.isHidden;
        this.vanillaPart.showModel = this.partContainer.showModel;
    }

    @Override
    public IPartWrapper setParent(IModelPart parent) {
        this.partContainer.setParent(parent);
        return this;
    }

    @Override
    public IPartWrapper offsetInner(float x2, float y2, float z2) {
        this.partContainer.setInnerOffset(x2, y2, z2);
        return this;
    }
}

