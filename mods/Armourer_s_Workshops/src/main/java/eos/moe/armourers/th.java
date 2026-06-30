/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Matrix4f
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraftforge.client.ForgeHooksClient
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.armourers;

import eos.moe.armourers.ModelData;
import eos.moe.armourers.fk;
import eos.moe.armourers.ih;
import eos.moe.armourers.tg;
import eos.moe.armourers.xd;
import javax.vecmath.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.GL11;

public class th {
    public static void r(float a2, float a3, float a4, float a5, String a6) {
        if (a6.startsWith("DIR-")) {
            return;
        }
        GlStateManager.pushAttrib();
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableColorMaterial();
        GlStateManager.enableNormalize();
        xd.z();
        GlStateManager.enableDepth();
        fk fk2 = new fk(a6);
        if (fk2.r() != null) {
            GlStateManager.pushMatrix();
            GL11.glTranslatef((float)a2, (float)a3, (float)500.0f);
            GL11.glScalef((float)10.0f, (float)10.0f, (float)-10.0f);
            GL11.glRotatef((float)30.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            float f2 = (float)((double)System.currentTimeMillis() / 10.0 % 360.0);
            GL11.glRotatef((float)f2, (float)0.0f, (float)1.0f, (float)0.0f);
            ih.r(new fk(a6), true, false, a4, a5);
            GlStateManager.popMatrix();
        } else {
            ModelData modelData = fk2.r();
            Object object = a6 = modelData == null || modelData.getTransformBakedModel() == null ? null : modelData.getTransformBakedModel().handlePerspective(ItemCameraTransforms.TransformType.GUI);
            if (a6 != null && a6.getRight() != null) {
                GlStateManager.pushMatrix();
                GlStateManager.translate((float)a2, (float)a3, (float)500.0f);
                GlStateManager.scale((float)1.0f, (float)-1.0f, (float)1.0f);
                GlStateManager.scale((double)((double)a4 * 0.8), (double)((double)a4 * 0.8), (double)((double)a4 * 0.8));
                ForgeHooksClient.multiplyCurrentGlMatrix((Matrix4f)((Matrix4f)a6.getRight()));
                GlStateManager.scale((float)-1.0f, (float)-1.0f, (float)1.0f);
                float f3 = (float)((double)System.currentTimeMillis() / 10.0 % 360.0);
                GL11.glRotatef((float)f3, (float)0.0f, (float)1.0f, (float)0.0f);
                tg.r(modelData, 0.0625f);
                GlStateManager.popMatrix();
            } else if (modelData != null) {
                GlStateManager.pushMatrix();
                GL11.glTranslatef((float)a2, (float)a3, (float)500.0f);
                GL11.glScalef((float)10.0f, (float)10.0f, (float)-10.0f);
                GL11.glRotatef((float)30.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                float f4 = (float)((double)System.currentTimeMillis() / 10.0 % 360.0);
                GL11.glRotatef((float)f4, (float)0.0f, (float)1.0f, (float)0.0f);
                a3 = a4 / 32.0f;
                GlStateManager.translate((float)0.0f, (float)(a5 / 32.0f), (float)0.0f);
                float f5 = a3;
                GlStateManager.scale((float)f5, (float)f5, (float)f5);
                tg.r(modelData, 0.0625f);
                GlStateManager.popMatrix();
            }
        }
        GlStateManager.disableDepth();
        xd.r();
        GlStateManager.disableNormalize();
        GlStateManager.disableColorMaterial();
        GlStateManager.disableRescaleNormal();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.popAttrib();
    }

    public static void r(int a2, int a32, int a42, EntityLivingBase a5) {
        RenderManager renderManager;
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.enableDepth();
        GlStateManager.translate((float)a2, (float)a32, (float)50.0f);
        GlStateManager.scale((float)(-a42), (float)a42, (float)a42);
        GlStateManager.rotate((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        EntityLivingBase entityLivingBase = a5;
        float a32 = entityLivingBase.renderYawOffset;
        float a42 = entityLivingBase.rotationYaw;
        float f2 = entityLivingBase.rotationPitch;
        float f3 = entityLivingBase.prevRotationYawHead;
        float f4 = entityLivingBase.rotationYawHead;
        GlStateManager.rotate((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        entityLivingBase.renderYawOffset = -1.0f;
        entityLivingBase.rotationYaw = -2.0f;
        entityLivingBase.rotationPitch = -2.5f;
        entityLivingBase.rotationYawHead = entityLivingBase.rotationYaw;
        entityLivingBase.prevRotationYawHead = entityLivingBase.rotationYaw;
        GlStateManager.translate((float)0.0f, (float)0.0f, (float)0.0f);
        RenderManager renderManager2 = renderManager = Minecraft.getMinecraft().getRenderManager();
        RenderManager renderManager3 = renderManager;
        renderManager3.setPlayerViewY(180.0f);
        renderManager3.setRenderShadow(false);
        renderManager2.renderEntity((Entity)a5, 0.0, 0.0, 0.0, 0.0f, 1.0f, false);
        renderManager2.setRenderShadow(true);
        entityLivingBase.renderYawOffset = a32;
        entityLivingBase.rotationYaw = a42;
        entityLivingBase.rotationPitch = f2;
        entityLivingBase.prevRotationYawHead = f3;
        entityLivingBase.rotationYawHead = f4;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture((int)OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture((int)OpenGlHelper.defaultTexUnit);
    }

    public th() {
        th a2;
    }
}

