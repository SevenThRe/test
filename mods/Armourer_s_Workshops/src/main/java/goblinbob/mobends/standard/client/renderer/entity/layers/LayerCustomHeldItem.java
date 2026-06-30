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

    public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        ItemStack itemstack1;
        boolean flag = entitylivingbaseIn.getPrimaryHand() == EnumHandSide.RIGHT;
        ItemStack itemstack = flag ? entitylivingbaseIn.getHeldItemOffhand() : entitylivingbaseIn.getHeldItemMainhand();
        ItemStack itemStack = itemstack1 = flag ? entitylivingbaseIn.getHeldItemMainhand() : entitylivingbaseIn.getHeldItemOffhand();
        if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
            GlStateManager.pushMatrix();
            if (this.livingEntityRenderer.getMainModel().isChild) {
                GlStateManager.translate((float)0.0f, (float)0.75f, (float)0.0f);
                GlStateManager.scale((float)0.5f, (float)0.5f, (float)0.5f);
            }
            this.renderHeldItem(entitylivingbaseIn, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, EnumHandSide.RIGHT);
            this.renderHeldItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, EnumHandSide.LEFT);
            GlStateManager.popMatrix();
        }
    }

    private void renderHeldItem(EntityLivingBase entity, ItemStack p_188358_2_, ItemCameraTransforms.TransformType p_188358_3_, EnumHandSide handSide) {
        if (!p_188358_2_.isEmpty()) {
            GlStateManager.pushMatrix();
            if (entity.isSneaking()) {
                GlStateManager.translate((float)0.0f, (float)0.2f, (float)0.0f);
            }
            this.translateToHand(handSide, entity);
            GlStateManager.rotate((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GlStateManager.rotate((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            boolean flag = handSide == EnumHandSide.LEFT;
            boolean isSmallArm = ((IRenderPlayer)this.livingEntityRenderer).isSmallArms();
            boolean isHidden = SkinRenderHelper.isArmHidden(entity.getUniqueID(), flag);
            if (isSmallArm && !isHidden) {
                GlStateManager.translate((float)((float)(flag ? -0.5 : 0.5) / 16.0f), (float)0.125f, (float)-0.625f);
            } else if (isSmallArm && isHidden) {
                GlStateManager.translate((float)((float)(flag ? -1 : 1) / 16.0f), (float)0.125f, (float)-0.625f);
            } else {
                GlStateManager.translate((float)0.0f, (float)0.125f, (float)-0.625f);
            }
            Minecraft.getMinecraft().getItemRenderer().renderItemSide(entity, p_188358_2_, p_188358_3_, flag);
            GlStateManager.popMatrix();
        }
    }

    protected void translateToHand(EnumHandSide handSide, EntityLivingBase entity) {
        Object entityData = EntityDatabase.instance.get(entity);
        ((ModelBiped)this.livingEntityRenderer.getMainModel()).postRenderArm(0.0625f, handSide);
        if (entityData instanceof BipedEntityData) {
            BipedEntityData bipedData = (BipedEntityData)entityData;
            SmoothOrientation itemRotation = handSide == EnumHandSide.RIGHT ? bipedData.renderRightItemRotation : bipedData.renderLeftItemRotation;
            GlStateManager.translate((float)0.0f, (float)0.5f, (float)0.0f);
            GlHelper.rotate(itemRotation.getSmooth());
            GlStateManager.translate((float)0.0f, (float)-0.5f, (float)0.0f);
        }
    }

    public boolean shouldCombineTextures() {
        return false;
    }
}

