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
        model.field_78115_e = part;
    };
    private static final IPartWrapper.ModelPartSetter headSetter = (model, part) -> {
        model.field_78116_c = part;
    };
    private static final IPartWrapper.ModelPartSetter headwearSetter = (model, part) -> {
        model.field_178720_f = part;
    };
    private static final IPartWrapper.ModelPartSetter leftArmSetter = (model, part) -> {
        model.field_178724_i = part;
    };
    private static final IPartWrapper.ModelPartSetter rightArmSetter = (model, part) -> {
        model.field_178723_h = part;
    };
    private static final IPartWrapper.ModelPartSetter leftLegSetter = (model, part) -> {
        model.field_178722_k = part;
    };
    private static final IPartWrapper.ModelPartSetter rightLegSetter = (model, part) -> {
        model.field_178721_j = part;
    };

    private ArmorWrapper(ModelBiped original) {
        this.original = original;
        this.field_78115_e = original.field_78115_e;
        this.field_78116_c = original.field_78116_c;
        this.field_178724_i = original.field_178724_i;
        this.field_178723_h = original.field_178723_h;
        this.field_178722_k = original.field_178722_k;
        this.field_178721_j = original.field_178721_j;
        this.field_178720_f = original.field_178720_f;
        this.bodyTransform = new ModelPartTransform();
        this.partWrappers = new ArrayList<IPartWrapper>();
        this.bodyParts = this.registerWrapper(original, this.field_78115_e, bodySetter, data -> data.body).offsetInner(0.0f, -12.0f, 0.0f);
        this.headParts = this.registerWrapper(original, this.field_78116_c, headSetter, data -> data.head).setParent(this.bodyTransform);
        this.headwearParts = this.registerWrapper(original, this.field_178720_f, headwearSetter, data -> data.head).setParent(this.bodyTransform);
        this.leftArmParts = this.registerWrapper(original, this.field_178724_i, leftArmSetter, data -> data.leftArm, data -> data.leftForeArm, 4.0f, 0.001f).offsetLower(0.0f, -4.0f, -2.0f).setParent(this.bodyTransform);
        this.rightArmParts = this.registerWrapper(original, this.field_178723_h, rightArmSetter, data -> data.rightArm, data -> data.rightForeArm, 4.0f, 0.001f).offsetLower(0.0f, -4.0f, -2.0f).setParent(this.bodyTransform);
        this.leftLegParts = this.registerWrapper(original, this.field_178722_k, leftLegSetter, data -> data.leftLeg, data -> data.leftForeLeg, 6.0f, 0.0f).offsetLower(-0.100000024f, -6.0f, 2.0f).offsetInner(-0.100000024f, 0.0f, 0.0f);
        this.rightLegParts = this.registerWrapper(original, this.field_178721_j, rightLegSetter, data -> data.rightLeg, data -> data.rightForeLeg, 6.0f, 0.0f).offsetLower(0.100000024f, -6.0f, 2.0f).offsetInner(0.100000024f, 0.0f, 0.0f);
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

    public void func_78088_a(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
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
        this.original.func_178686_a((ModelBase)this);
        this.original.func_78088_a(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.deapply();
    }

    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
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

