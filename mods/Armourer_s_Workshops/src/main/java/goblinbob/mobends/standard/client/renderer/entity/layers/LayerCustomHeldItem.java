/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.client.renderer.entity.layers.LayerRenderer
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumHandSide
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package goblinbob.mobends.standard.client.renderer.entity.layers;

import eos.moe.armourers.SkinRenderHelper;
import eos.moe.armourers.interfaces.IRenderPlayer;
import goblinbob.mobends.core.data.EntityDatabase;
import goblinbob.mobends.core.math.SmoothOrientation;
import goblinbob.mobends.core.util.GlHelper;
import goblinbob.mobends.standard.data.BipedEntityData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class LayerCustomHeldItem
implements LayerRenderer<EntityLivingBase> {
    protected final RenderLivingBase<?> livingEntityRenderer;

    public LayerCustomHeldItem(RenderLivingBase<?> livingEntityRendererIn) {
        this.livingEntityRenderer = livingEntityRendererIn;
    }

    public void func_177141_a(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        ItemStack itemstack1;
        boolean flag = entitylivingbaseIn.func_184591_cq() == EnumHandSide.RIGHT;
        ItemStack itemstack = flag ? entitylivingbaseIn.func_184592_cb() : entitylivingbaseIn.func_184614_ca();
        ItemStack itemStack = itemstack1 = flag ? entitylivingbaseIn.func_184614_ca() : entitylivingbaseIn.func_184592_cb();
        if (!itemstack.func_190926_b() || !itemstack1.func_190926_b()) {
            GlStateManager.func_179094_E();
            if (this.livingEntityRenderer.func_177087_b().field_78091_s) {
                GlStateManager.func_179109_b((float)0.0f, (float)0.75f, (float)0.0f);
                GlStateManager.func_179152_a((float)0.5f, (float)0.5f, (float)0.5f);
            }
            this.renderHeldItem(entitylivingbaseIn, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, EnumHandSide.RIGHT);
            this.renderHeldItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, EnumHandSide.LEFT);
            GlStateManager.func_179121_F();
        }
    }

    private void renderHeldItem(EntityLivingBase entity, ItemStack p_188358_2_, ItemCameraTransforms.TransformType p_188358_3_, EnumHandSide handSide) {
        if (!p_188358_2_.func_190926_b()) {
            GlStateManager.func_179094_E();
            if (entity.func_70093_af()) {
                GlStateManager.func_179109_b((float)0.0f, (float)0.2f, (float)0.0f);
            }
            this.translateToHand(handSide, entity);
            GlStateManager.func_179114_b((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GlStateManager.func_179114_b((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            boolean flag = handSide == EnumHandSide.LEFT;
            boolean isSmallArm = ((IRenderPlayer)this.livingEntityRenderer).isSmallArms();
            boolean isHidden = SkinRenderHelper.isArmHidden(entity.func_110124_au(), flag);
            if (isSmallArm && !isHidden) {
                GlStateManager.func_179109_b((float)((float)(flag ? -0.5 : 0.5) / 16.0f), (float)0.125f, (float)-0.625f);
            } else if (isSmallArm && isHidden) {
                GlStateManager.func_179109_b((float)((float)(flag ? -1 : 1) / 16.0f), (float)0.125f, (float)-0.625f);
            } else {
                GlStateManager.func_179109_b((float)0.0f, (float)0.125f, (float)-0.625f);
            }
            Minecraft.func_71410_x().func_175597_ag().func_187462_a(entity, p_188358_2_, p_188358_3_, flag);
            GlStateManager.func_179121_F();
        }
    }

    protected void translateToHand(EnumHandSide handSide, EntityLivingBase entity) {
        Object entityData = EntityDatabase.instance.get(entity);
        ((ModelBiped)this.livingEntityRenderer.func_177087_b()).func_187073_a(0.0625f, handSide);
        if (entityData instanceof BipedEntityData) {
            BipedEntityData bipedData = (BipedEntityData)entityData;
            SmoothOrientation itemRotation = handSide == EnumHandSide.RIGHT ? bipedData.renderRightItemRotation : bipedData.renderLeftItemRotation;
            GlStateManager.func_179109_b((float)0.0f, (float)0.5f, (float)0.0f);
            GlHelper.rotate(itemRotation.getSmooth());
            GlStateManager.func_179109_b((float)0.0f, (float)-0.5f, (float)0.0f);
        }
    }

    public boolean func_177142_b() {
        return false;
    }
}

