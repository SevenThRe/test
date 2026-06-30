/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 */
package goblinbob.mobends.standard.client.model.armor;

import goblinbob.mobends.core.bender.EntityBender;
import goblinbob.mobends.core.bender.EntityBenderRegistry;
import goblinbob.mobends.core.client.model.ModelPartTransform;
import goblinbob.mobends.core.data.EntityDatabase;
import goblinbob.mobends.standard.client.model.armor.HumanoidLimbWrapper;
import goblinbob.mobends.standard.client.model.armor.HumanoidPartWrapper;
import goblinbob.mobends.standard.client.model.armor.IPartWrapper;
import goblinbob.mobends.standard.client.model.armor.MalformedArmorModelException;
import goblinbob.mobends.standard.data.BipedEntityData;
import goblinbob.mobends.standard.data.PlayerData;
import goblinbob.mobends.standard.previewer.PlayerPreviewer;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ArmorWrapper
extends ModelBiped {
    protected ModelBiped original;
    protected boolean mutated = true;
    protected boolean applied = false;
    protected List<IPartWrapper> partWrappers;
    protected IPartWrapper bodyParts;
    protected IPartWrapper headParts;
    protected IPartWrapper headwearParts;
    protected IPartWrapper leftArmParts;
    protected IPartWrapper rightArmParts;
    protected IPartWrapper leftLegParts;
    protected IPartWrapper rightLegParts;
    protected ModelPartTransform bodyTransform;
    private static final IPartWrapper.ModelPartSetter bodySetter = (model, part) -> {
        model.bipedBody = part;
    };
    private static final IPartWrapper.ModelPartSetter headSetter = (model, part) -> {
        model.bipedHead = part;
    };
    private static final IPartWrapper.ModelPartSetter headwearSetter = (model, part) -> {
        model.bipedHeadwear = part;
    };
    private static final IPartWrapper.ModelPartSetter leftArmSetter = (model, part) -> {
        model.bipedLeftArm = part;
    };
    private static final IPartWrapper.ModelPartSetter rightArmSetter = (model, part) -> {
        model.bipedRightArm = part;
    };
    private static final IPartWrapper.ModelPartSetter leftLegSetter = (model, part) -> {
        model.bipedLeftLeg = part;
    };
    private static final IPartWrapper.ModelPartSetter rightLegSetter = (model, part) -> {
        model.bipedRightLeg = part;
    };

    private ArmorWrapper(ModelBiped original) {
        this.original = original;
        this.bipedBody = original.bipedBody;
        this.bipedHead = original.bipedHead;
        this.bipedLeftArm = original.bipedLeftArm;
        this.bipedRightArm = original.bipedRightArm;
        this.bipedLeftLeg = original.bipedLeftLeg;
        this.bipedRightLeg = original.bipedRightLeg;
        this.bipedHeadwear = original.bipedHeadwear;
        this.bodyTransform = new ModelPartTransform();
        this.partWrappers = new ArrayList<IPartWrapper>();
        this.bodyParts = this.registerWrapper(original, this.bipedBody, bodySetter, data -> data.body).offsetInner(0.0f, -12.0f, 0.0f);
        this.headParts = this.registerWrapper(original, this.bipedHead, headSetter, data -> data.head).setParent(this.bodyTransform);
        this.headwearParts = this.registerWrapper(original, this.bipedHeadwear, headwearSetter, data -> data.head).setParent(this.bodyTransform);
        this.leftArmParts = this.registerWrapper(original, this.bipedLeftArm, leftArmSetter, data -> data.leftArm, data -> data.leftForeArm, 4.0f, 0.001f).offsetLower(0.0f, -4.0f, -2.0f).setParent(this.bodyTransform);
        this.rightArmParts = this.registerWrapper(original, this.bipedRightArm, rightArmSetter, data -> data.rightArm, data -> data.rightForeArm, 4.0f, 0.001f).offsetLower(0.0f, -4.0f, -2.0f).setParent(this.bodyTransform);
        this.leftLegParts = this.registerWrapper(original, this.bipedLeftLeg, leftLegSetter, data -> data.leftLeg, data -> data.leftForeLeg, 6.0f, 0.0f).offsetLower(-0.100000024f, -6.0f, 2.0f).offsetInner(-0.100000024f, 0.0f, 0.0f);
        this.rightLegParts = this.registerWrapper(original, this.bipedRightLeg, rightLegSetter, data -> data.rightLeg, data -> data.rightForeLeg, 6.0f, 0.0f).offsetLower(0.100000024f, -6.0f, 2.0f).offsetInner(0.100000024f, 0.0f, 0.0f);
    }

    private HumanoidPartWrapper registerWrapper(ModelBiped vanillaModel, ModelRenderer vanillaPart, IPartWrapper.ModelPartSetter setter, IPartWrapper.DataPartSelector dataSelector) {
        HumanoidPartWrapper wrapper = new HumanoidPartWrapper(vanillaModel, vanillaPart, setter, dataSelector);
        this.partWrappers.add(wrapper);
        return wrapper;
    }

    private HumanoidLimbWrapper registerWrapper(ModelBiped vanillaModel, ModelRenderer vanillaPart, IPartWrapper.ModelPartSetter setter, IPartWrapper.DataPartSelector data, IPartWrapper.DataPartSelector lowerData, float cutPlane, float inflation) {
        HumanoidLimbWrapper wrapper = new HumanoidLimbWrapper(vanillaModel, vanillaPart, setter, data, lowerData, cutPlane, inflation);
        this.partWrappers.add(wrapper);
        return wrapper;
    }

    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (!this.mutated) {
            throw new MalformedArmorModelException("Operating on a demutated armor wrapper.");
        }
        if (!(entityIn instanceof EntityLivingBase)) {
            return;
        }
        EntityLivingBase entityLiving = (EntityLivingBase)entityIn;
        EntityBender<EntityLivingBase> entityBender = EntityBenderRegistry.instance.getForEntity(entityLiving);
        if (entityBender == null) {
            return;
        }
        Object entityData = EntityDatabase.instance.get(entityLiving);
        if (!(entityData instanceof BipedEntityData)) {
            return;
        }
        if (entityData instanceof PlayerData && PlayerPreviewer.isPreviewInProgress()) {
            entityData = PlayerPreviewer.getPreviewData();
        }
        BipedEntityData dataBiped = (BipedEntityData)entityData;
        this.bodyTransform.syncUp(dataBiped.body);
        this.partWrappers.forEach(group -> group.syncUp(dataBiped));
        this.apply();
        this.original.setModelAttributes((ModelBase)this);
        this.original.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.deapply();
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
    }

    public void demutate() {
        this.deapply();
        this.partWrappers.clear();
        this.mutated = false;
    }

    public void apply() {
        if (this.applied) {
            return;
        }
        for (IPartWrapper wrapper : this.partWrappers) {
            wrapper.apply(this);
        }
        this.applied = true;
    }

    public void deapply() {
        if (!this.applied) {
            return;
        }
        for (IPartWrapper wrapper : this.partWrappers) {
            wrapper.deapply(this);
        }
        this.applied = false;
    }

    public static ArmorWrapper createFor(ModelBiped src) {
        return new ArmorWrapper(src);
    }
}

