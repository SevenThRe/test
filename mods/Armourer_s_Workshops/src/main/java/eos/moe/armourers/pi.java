/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package eos.moe.armourers;

import eos.moe.armourers.de;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class pi {
    public static pi v;
    private static Minecraft s;
    private static RenderManager m;
    public de j;

    @SubscribeEvent
    public void r(RenderWorldLastEvent a2) {
        pi a3;
        if (a3.j == null) {
            return;
        }
        a3.r((EntityLivingBase)Minecraft.getMinecraft().player);
    }

    public static void r() {
        v = new pi();
    }

    private /* synthetic */ void r(EntityLivingBase a2) {
        RenderManager renderManager;
        pi a3;
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        pi pi2 = a3;
        double d2 = pi2.j.l - pi.m.viewerPosX;
        double d3 = pi2.j.m - pi.m.viewerPosY;
        double d4 = pi2.j.t - pi.m.viewerPosZ;
        GlStateManager.translate((double)d2, (double)d3, (double)d4);
        float f2 = pi2.j.j;
        GlStateManager.scale((float)f2, (float)f2, (float)f2);
        EntityLivingBase entityLivingBase = a2;
        EntityLivingBase entityLivingBase2 = a2;
        AxisAlignedBB axisAlignedBB = entityLivingBase2.getRenderBoundingBox();
        d2 = 0.8f;
        d2 = 1.9;
        d2 = 0.8f;
        AxisAlignedBB axisAlignedBB2 = axisAlignedBB;
        d2 = Math.floor((axisAlignedBB2.maxX - axisAlignedBB.minX) * 1000.0) / 1000.0;
        AxisAlignedBB axisAlignedBB3 = axisAlignedBB;
        d3 = Math.floor((axisAlignedBB2.maxY - axisAlignedBB3.minY) * 1000.0) / 1000.0;
        d4 = Math.floor((axisAlignedBB3.maxZ - axisAlignedBB.minZ) * 1000.0) / 1000.0;
        double d5 = 1.0;
        d5 = Math.max(1.0, d2 / (double)0.8f);
        d5 = Math.max(d5, d3 / 1.9);
        d5 = Math.max(d5, d4 / (double)0.8f);
        d5 = 1.0 / d5;
        GlStateManager.scale((double)d5, (double)d5, (double)d5);
        float f3 = entityLivingBase2.renderYawOffset;
        float f4 = entityLivingBase2.rotationYaw;
        float f5 = entityLivingBase2.rotationPitch;
        float f6 = entityLivingBase2.prevRotationYawHead;
        float f7 = entityLivingBase2.rotationYawHead;
        GlStateManager.rotate((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        entityLivingBase2.renderYawOffset = a3.j.w;
        entityLivingBase2.rotationYaw = a3.j.r;
        entityLivingBase2.rotationPitch = a3.j.s;
        entityLivingBase2.rotationYawHead = a3.j.r;
        entityLivingBase2.prevRotationYawHead = a3.j.r;
        GlStateManager.translate((float)0.0f, (float)0.0f, (float)0.0f);
        RenderManager renderManager2 = renderManager = Minecraft.getMinecraft().getRenderManager();
        RenderManager renderManager3 = renderManager;
        renderManager3.setPlayerViewY(180.0f);
        renderManager3.setRenderShadow(false);
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        renderManager2.renderEntity((Entity)a2, 0.0, 0.0, 0.0, 0.0f, 1.0f, false);
        renderManager2.setRenderShadow(true);
        entityLivingBase2.renderYawOffset = f3;
        entityLivingBase.rotationYaw = f4;
        entityLivingBase.rotationPitch = f5;
        entityLivingBase.prevRotationYawHead = f6;
        entityLivingBase.rotationYawHead = f7;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture((int)OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture((int)OpenGlHelper.defaultTexUnit);
    }

    public pi() {
        pi a2;
        MinecraftForge.EVENT_BUS.register((Object)a2);
    }

    static {
        s = Minecraft.getMinecraft();
        m = s.getRenderManager();
    }
}

