/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.model.ModelPlayer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemPickaxe
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.ResourceLocation
 */
package com.rejahtavi.rfp2;

import baka.animation.controller.CPCManager;
import com.rejahtavi.rfp2.EntityPlayerDummy;
import com.rejahtavi.rfp2.RFP2;
import com.rejahtavi.rfp2.RFP2Config;
import goblinbob.mobends.core.client.model.ModelPart;
import goblinbob.mobends.standard.animation.controller.PlayerController;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;

public class RenderPlayerDummy
extends Render<EntityPlayerDummy> {
    public static boolean renderFirstPerson;

    public RenderPlayerDummy(RenderManager renderManager) {
        super(renderManager);
    }

    protected ResourceLocation getEntityTexture(EntityPlayerDummy entity) {
        return null;
    }

    private float linearInterpolate(float current, float target, float partialTicks) {
        return (1.0f - partialTicks) * current + partialTicks * target;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void doRender(EntityPlayerDummy renderEntity, double renderPosX, double renderPosY, double renderPosZ, float renderYaw, float partialTicks) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if (player == null) {
            return;
        }
        ItemStack itemMainHand = player.inventory.getCurrentItem();
        ItemStack itemOffHand = (ItemStack)player.inventory.offHandInventory.get(0);
        ItemStack itemHelmetSlot0 = (ItemStack)player.inventory.armorInventory.get(0);
        ItemStack itemHelmetSlot1 = (ItemStack)player.inventory.armorInventory.get(1);
        ItemStack itemHelmetSlot2 = (ItemStack)player.inventory.armorInventory.get(2);
        ItemStack itemHelmetSlot3 = (ItemStack)player.inventory.armorInventory.get(3);
        if (RFP2Config.compatibility.disableWhenSneaking && player.isSneaking()) {
            return;
        }
        Render render = this.renderManager.getEntityRenderObject((Entity)player);
        RenderPlayer playerRenderer = (RenderPlayer)render;
        if (playerRenderer == null) {
            return;
        }
        ModelPlayer playerModel = playerRenderer.getMainModel();
        if (playerModel == null) {
            return;
        }
        boolean[] modelState = new boolean[]{playerModel.bipedHead.isHidden, playerModel.bipedHeadwear.isHidden, playerModel.bipedBody.isHidden, playerModel.bipedBodyWear.isHidden, playerModel.bipedLeftArm.isHidden, playerModel.bipedLeftArmwear.isHidden, playerModel.bipedRightArm.isHidden, playerModel.bipedRightArmwear.isHidden, playerModel.bipedLeftLeg.isHidden, playerModel.bipedLeftLegwear.isHidden, playerModel.bipedRightLeg.isHidden, playerModel.bipedRightLegwear.isHidden};
        try {
            if (Minecraft.getMinecraft().gameSettings.thirdPersonView != 0) {
                return;
            }
            if (player.isElytraFlying()) {
                return;
            }
            if (!RFP2.state.isModEnabled((EntityPlayer)player)) {
                return;
            }
            double playerRenderPosX = 0.0;
            double playerRenderPosZ = 0.0;
            double playerRenderPosY = 0.0;
            float playerRenderAngle = 0.0f;
            float playerModelOffset = (float)RFP2Config.preferences.playerModelOffset;
            boolean isRealArmsEnabled = RFP2.state.isRealArmsEnabled((EntityPlayer)player);
            boolean isHeadRotationEnabled = RFP2.state.isHeadRotationEnabled((EntityPlayer)player);
            player.inventory.armorInventory.set(0, (Object)ItemStack.EMPTY);
            player.inventory.armorInventory.set(1, (Object)ItemStack.EMPTY);
            player.inventory.armorInventory.set(2, (Object)ItemStack.EMPTY);
            player.inventory.armorInventory.set(3, (Object)ItemStack.EMPTY);
            playerModel.bipedHead.isHidden = true;
            playerModel.bipedHeadwear.isHidden = true;
            playerModel.bipedBody.isHidden = true;
            playerModel.bipedBodyWear.isHidden = true;
            playerModel.bipedLeftLeg.isHidden = true;
            playerModel.bipedLeftLegwear.isHidden = true;
            playerModel.bipedRightLeg.isHidden = true;
            playerModel.bipedRightLegwear.isHidden = true;
            playerModel.bipedLeftArm.isHidden = true;
            playerModel.bipedLeftArmwear.isHidden = true;
            playerModel.bipedRightArm.isHidden = true;
            playerModel.bipedRightArmwear.isHidden = true;
            PlayerController playerController = CPCManager.get(player.getUniqueID());
            if (!isRealArmsEnabled || playerController != null && playerController.isPlaying()) {
                player.inventory.removeStackFromSlot(player.inventory.currentItem);
                player.inventory.offHandInventory.set(0, (Object)ItemStack.EMPTY);
            } else if (playerController != null && playerController.isCustomPlaying() && (player.getHeldItemOffhand().getTagCompound() == null || !player.getHeldItemOffhand().getTagCompound().hasKey("offhand"))) {
                player.inventory.offHandInventory.set(0, (Object)ItemStack.EMPTY);
            } else if (player.isHandActive() && player.getActiveHand() == EnumHand.MAIN_HAND) {
                player.inventory.offHandInventory.set(0, (Object)ItemStack.EMPTY);
            } else if (player.getHeldItemMainhand().getItem() instanceof ItemBow || player.getHeldItemMainhand().getItem() instanceof ItemPickaxe && !(player.getHeldItemOffhand().getItem() instanceof ItemPickaxe)) {
                player.inventory.offHandInventory.set(0, (Object)ItemStack.EMPTY);
            }
            playerRenderPosX = player.posX - renderEntity.posX + renderPosX;
            playerRenderPosY = player.posY - renderEntity.posY + renderPosY;
            playerRenderPosZ = player.posZ - renderEntity.posZ + renderPosZ;
            if (!player.isPlayerSleeping()) {
                if (!isHeadRotationEnabled) {
                    player.renderYawOffset = player.rotationYaw;
                    player.prevRenderYawOffset = player.prevRotationYaw;
                }
                playerRenderAngle = this.linearInterpolate(player.prevRenderYawOffset, player.renderYawOffset, partialTicks);
                playerRenderPosX += (double)playerModelOffset * Math.sin(Math.toRadians(playerRenderAngle));
                playerRenderPosZ -= (double)playerModelOffset * Math.cos(Math.toRadians(playerRenderAngle));
            }
            ModelPart.cancelRenderSkin = true;
            renderFirstPerson = true;
            int i2 = player.getBrightnessForRender();
            if (player.isBurning()) {
                i2 = 0xF000F0;
            }
            int j2 = i2 % 65536;
            int k2 = i2 / 65536;
            OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)j2, (float)k2);
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.renderManager.setRenderShadow(false);
            this.renderManager.renderEntity((Entity)player, playerRenderPosX, playerRenderPosY, playerRenderPosZ, playerRenderAngle, partialTicks, true);
            this.renderManager.setRenderShadow(true);
            renderFirstPerson = false;
            ModelPart.cancelRenderSkin = false;
        }
        catch (Exception e2) {
            RFP2.errorDisableMod(((Object)((Object)this)).getClass().getName() + ".doRender()", e2);
        }
        finally {
            player.inventory.armorInventory.set(0, (Object)itemHelmetSlot0);
            player.inventory.armorInventory.set(1, (Object)itemHelmetSlot1);
            player.inventory.armorInventory.set(2, (Object)itemHelmetSlot2);
            player.inventory.armorInventory.set(3, (Object)itemHelmetSlot3);
            player.inventory.setInventorySlotContents(player.inventory.currentItem, itemMainHand);
            player.inventory.offHandInventory.set(0, (Object)itemOffHand);
            playerModel.bipedHead.isHidden = modelState[0];
            playerModel.bipedHeadwear.isHidden = modelState[1];
            playerModel.bipedBody.isHidden = modelState[2];
            playerModel.bipedBodyWear.isHidden = modelState[3];
            playerModel.bipedLeftArm.isHidden = modelState[4];
            playerModel.bipedLeftArmwear.isHidden = modelState[5];
            playerModel.bipedRightArm.isHidden = modelState[6];
            playerModel.bipedRightArmwear.isHidden = modelState[7];
            playerModel.bipedLeftLeg.isHidden = modelState[8];
            playerModel.bipedLeftLegwear.isHidden = modelState[9];
            playerModel.bipedRightLeg.isHidden = modelState[10];
            playerModel.bipedRightLegwear.isHidden = modelState[11];
        }
    }
}

