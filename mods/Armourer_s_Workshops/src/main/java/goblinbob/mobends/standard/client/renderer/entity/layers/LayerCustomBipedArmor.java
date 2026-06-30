/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.client.renderer.entity.layers.LayerArmorBase
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.client.ForgeHooksClient
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package goblinbob.mobends.standard.client.renderer.entity.layers;

import goblinbob.mobends.core.data.EntityDatabase;
import goblinbob.mobends.standard.client.model.armor.ArmorModelFactory;
import goblinbob.mobends.standard.client.model.armor.MalformedArmorModelException;
import goblinbob.mobends.standard.data.BipedEntityData;
import goblinbob.mobends.standard.main.ModConfig;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class LayerCustomBipedArmor
extends LayerArmorBase<ModelBiped> {
    public LayerCustomBipedArmor(RenderLivingBase<?> rendererIn) {
        super(rendererIn);
    }

    public void initArmor() {
        this.modelLeggings = new ModelBiped(0.5f);
        this.modelArmor = new ModelBiped(1.0f);
    }

    protected void setModelSlotVisible(ModelBiped model, EntityEquipmentSlot slotIn) {
        this.hideModelParts(model);
        switch (slotIn) {
            case HEAD: {
                model.bipedHead.showModel = true;
                model.bipedHeadwear.showModel = true;
                break;
            }
            case CHEST: {
                model.bipedBody.showModel = true;
                model.bipedRightArm.showModel = true;
                model.bipedLeftArm.showModel = true;
                break;
            }
            case LEGS: {
                model.bipedBody.showModel = true;
                model.bipedRightLeg.showModel = true;
                model.bipedLeftLeg.showModel = true;
                break;
            }
            case FEET: {
                model.bipedRightLeg.showModel = true;
                model.bipedLeftLeg.showModel = true;
                break;
            }
        }
    }

    protected void hideModelParts(ModelBiped model) {
        model.setVisible(false);
    }

    protected ModelBiped getArmorModelHook(EntityLivingBase entity, ItemStack itemStack, EntityEquipmentSlot slot, ModelBiped model) {
        Object entityData = EntityDatabase.instance.get(entity);
        ModelBiped suggestedModel = ForgeHooksClient.getArmorModel((EntityLivingBase)entity, (ItemStack)itemStack, (EntityEquipmentSlot)slot, (ModelBiped)model);
        boolean shouldBeMutated = !ModConfig.shouldKeepArmorAsVanilla(itemStack.getItem()) && entityData != null && entityData instanceof BipedEntityData;
        try {
            return ArmorModelFactory.getArmorModel(suggestedModel, shouldBeMutated);
        }
        catch (MalformedArmorModelException ex) {
            ex.printStackTrace();
            return suggestedModel;
        }
    }
}

