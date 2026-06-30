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

    public void func_177177_a() {
        this.field_177189_c = new ModelBiped(0.5f);
        this.field_177186_d = new ModelBiped(1.0f);
    }

    protected void setModelSlotVisible(ModelBiped model, EntityEquipmentSlot slotIn) {
        this.hideModelParts(model);
        switch (slotIn) {
            case HEAD: {
                model.field_78116_c.field_78806_j = true;
                model.field_178720_f.field_78806_j = true;
                break;
            }
            case CHEST: {
                model.field_78115_e.field_78806_j = true;
                model.field_178723_h.field_78806_j = true;
                model.field_178724_i.field_78806_j = true;
                break;
            }
            case LEGS: {
                model.field_78115_e.field_78806_j = true;
                model.field_178721_j.field_78806_j = true;
                model.field_178722_k.field_78806_j = true;
                break;
            }
            case FEET: {
                model.field_178721_j.field_78806_j = true;
                model.field_178722_k.field_78806_j = true;
                break;
            }
        }
    }

    protected void hideModelParts(ModelBiped model) {
        model.func_178719_a(false);
    }

    protected ModelBiped getArmorModelHook(EntityLivingBase entity, ItemStack itemStack, EntityEquipmentSlot slot, ModelBiped model) {
        Object entityData = EntityDatabase.instance.get(entity);
        ModelBiped suggestedModel = ForgeHooksClient.getArmorModel((EntityLivingBase)entity, (ItemStack)itemStack, (EntityEquipmentSlot)slot, (ModelBiped)model);
        boolean shouldBeMutated = !ModConfig.shouldKeepArmorAsVanilla(itemStack.func_77973_b()) && entityData != null && entityData instanceof BipedEntityData;
        try {
            return ArmorModelFactory.getArmorModel(suggestedModel, shouldBeMutated);
        }
        catch (MalformedArmorModelException ex) {
            ex.printStackTrace();
            return suggestedModel;
        }
    }
}

