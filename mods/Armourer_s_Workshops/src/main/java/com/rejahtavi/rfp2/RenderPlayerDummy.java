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
        EntityPlayerSP player = Minecraft.func_71410_x().field_71439_g;
        if (player == null) {
            return;
        }
        ItemStack itemMainHand = player.field_71071_by.func_70448_g();
        ItemStack itemOffHand = (ItemStack)player.field_71071_by.field_184439_c.get(0);
        ItemStack itemHelmetSlot0 = (ItemStack)player.field_71071_by.field_70460_b.get(0);
        ItemStack itemHelmetSlot1 = (ItemStack)player.field_71071_by.field_70460_b.get(1);
        ItemStack itemHelmetSlot2 = (ItemStack)player.field_71071_by.field_70460_b.get(2);
        ItemStack itemHelmetSlot3 = (ItemStack)player.field_71071_by.field_70460_b.get(3);
        if (RFP2Config.compatibility.disableWhenSneaking && player.func_70093_af()) {
            return;
        }
        Render render = this.field_76990_c.func_78713_a((Entity)player);
        RenderPlayer playerRenderer = (RenderPlayer)render;
        if (playerRenderer == null) {
            return;
        }
        ModelPlayer playerModel = playerRenderer.func_177087_b();
        if (playerModel == null) {
            return;
        }
        boolean[] modelState = new boolean[]{playerModel.field_78116_c.field_78807_k, playerModel.field_178720_f.field_78807_k, playerModel.field_78115_e.field_78807_k, playerModel.field_178730_v.field_78807_k, playerModel.field_178724_i.field_78807_k, playerModel.field_178734_a.field_78807_k, playerModel.field_178723_h.field_78807_k, playerModel.field_178732_b.field_78807_k, playerModel.field_178722_k.field_78807_k, playerModel.field_178733_c.field_78807_k, playerModel.field_178721_j.field_78807_k, playerModel.field_178731_d.field_78807_k};
        try {
            if (Minecraft.func_71410_x().field_71474_y.field_74320_O != 0) {
                return;
            }
            if (player.func_184613_cA()) {
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
            player.field_71071_by.field_70460_b.set(0, (Object)ItemStack.field_190927_a);
            player.field_71071_by.field_70460_b.set(1, (Object)ItemStack.field_190927_a);
            player.field_71071_by.field_70460_b.set(2, (Object)ItemStack.field_190927_a);
            player.field_71071_by.field_70460_b.set(3, (Object)ItemStack.field_190927_a);
            playerModel.field_78116_c.field_78807_k = true;
            playerModel.field_178720_f.field_78807_k = true;
            playerModel.field_78115_e.field_78807_k = true;
            playerModel.field_178730_v.field_78807_k = true;
            playerModel.field_178722_k.field_78807_k = true;
            playerModel.field_178733_c.field_78807_k = true;
            playerModel.field_178721_j.field_78807_k = true;
            playerModel.field_178731_d.field_78807_k = true;
            playerModel.field_178724_i.field_78807_k = true;
            playerModel.field_178734_a.field_78807_k = true;
            playerModel.field_178723_h.field_78807_k = true;
            playerModel.field_178732_b.field_78807_k = true;
            PlayerController playerController = CPCManager.get(player.func_110124_au());
            if (!isRealArmsEnabled || playerController != null && playerController.isPlaying()) {
                player.field_71071_by.func_70304_b(player.field_71071_by.field_70461_c);
                player.field_71071_by.field_184439_c.set(0, (Object)ItemStack.field_190927_a);
            } else if (playerController != null && playerController.isCustomPlaying() && (player.func_184592_cb().func_77978_p() == null || !player.func_184592_cb().func_77978_p().func_74764_b("offhand"))) {
                player.field_71071_by.field_184439_c.set(0, (Object)ItemStack.field_190927_a);
            } else if (player.func_184587_cr() && player.func_184600_cs() == EnumHand.MAIN_HAND) {
                player.field_71071_by.field_184439_c.set(0, (Object)ItemStack.field_190927_a);
            } else if (player.func_184614_ca().func_77973_b() instanceof ItemBow || player.func_184614_ca().func_77973_b() instanceof ItemPickaxe && !(player.func_184592_cb().func_77973_b() instanceof ItemPickaxe)) {
                player.field_71071_by.field_184439_c.set(0, (Object)ItemStack.field_190927_a);
            }
            playerRenderPosX = player.field_70165_t - renderEntity.field_70165_t + renderPosX;
            playerRenderPosY = player.field_70163_u - renderEntity.field_70163_u + renderPosY;
            playerRenderPosZ = player.field_70161_v - renderEntity.field_70161_v + renderPosZ;
            if (!player.func_70608_bn()) {
                if (!isHeadRotationEnabled) {
                    player.field_70761_aq = player.field_70177_z;
                    player.field_70760_ar = player.field_70126_B;
                }
                playerRenderAngle = this.linearInterpolate(player.field_70760_ar, player.field_70761_aq, partialTicks);
                playerRenderPosX += (double)playerModelOffset * Math.sin(Math.toRadians(playerRenderAngle));
                playerRenderPosZ -= (double)playerModelOffset * Math.cos(Math.toRadians(playerRenderAngle));
            }
            ModelPart.cancelRenderSkin = true;
            renderFirstPerson = true;
            int i2 = player.func_70070_b();
            if (player.func_70027_ad()) {
                i2 = 0xF000F0;
            }
            int j2 = i2 % 65536;
            int k2 = i2 / 65536;
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)j2, (float)k2);
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.field_76990_c.func_178633_a(false);
            this.field_76990_c.func_188391_a((Entity)player, playerRenderPosX, playerRenderPosY, playerRenderPosZ, playerRenderAngle, partialTicks, true);
            this.field_76990_c.func_178633_a(true);
            renderFirstPerson = false;
            ModelPart.cancelRenderSkin = false;
        }
        catch (Exception e2) {
            RFP2.errorDisableMod(((Object)((Object)this)).getClass().getName() + ".doRender()", e2);
        }
        finally {
            player.field_71071_by.field_70460_b.set(0, (Object)itemHelmetSlot0);
            player.field_71071_by.field_70460_b.set(1, (Object)itemHelmetSlot1);
            player.field_71071_by.field_70460_b.set(2, (Object)itemHelmetSlot2);
            player.field_71071_by.field_70460_b.set(3, (Object)itemHelmetSlot3);
            player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, itemMainHand);
            player.field_71071_by.field_184439_c.set(0, (Object)itemOffHand);
            playerModel.field_78116_c.field_78807_k = modelState[0];
            playerModel.field_178720_f.field_78807_k = modelState[1];
            playerModel.field_78115_e.field_78807_k = modelState[2];
            playerModel.field_178730_v.field_78807_k = modelState[3];
            playerModel.field_178724_i.field_78807_k = modelState[4];
            playerModel.field_178734_a.field_78807_k = modelState[5];
            playerModel.field_178723_h.field_78807_k = modelState[6];
            playerModel.field_178732_b.field_78807_k = modelState[7];
            playerModel.field_178722_k.field_78807_k = modelState[8];
            playerModel.field_178733_c.field_78807_k = modelState[9];
            playerModel.field_178721_j.field_78807_k = modelState[10];
            playerModel.field_178731_d.field_78807_k = modelState[11];
        }
    }
}

