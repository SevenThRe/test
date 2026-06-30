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
        GlStateManager.func_179123_a();
        RenderHelper.func_74520_c();
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.func_179091_B();
        GlStateManager.func_179142_g();
        GlStateManager.func_179108_z();
        xd.z();
        GlStateManager.func_179126_j();
        fk fk2 = new fk(a6);
        if (fk2.r() != null) {
            GlStateManager.func_179094_E();
            GL11.glTranslatef((float)a2, (float)a3, (float)500.0f);
            GL11.glScalef((float)10.0f, (float)10.0f, (float)-10.0f);
            GL11.glRotatef((float)30.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            float f2 = (float)((double)System.currentTimeMillis() / 10.0 % 360.0);
            GL11.glRotatef((float)f2, (float)0.0f, (float)1.0f, (float)0.0f);
            ih.r(new fk(a6), true, false, a4, a5);
            GlStateManager.func_179121_F();
        } else {
            ModelData modelData = fk2.r();
            Object object = a6 = modelData == null || modelData.getTransformBakedModel() == null ? null : modelData.getTransformBakedModel().handlePerspective(ItemCameraTransforms.TransformType.GUI);
            if (a6 != null && a6.getRight() != null) {
                GlStateManager.func_179094_E();
                GlStateManager.func_179109_b((float)a2, (float)a3, (float)500.0f);
                GlStateManager.func_179152_a((float)1.0f, (float)-1.0f, (float)1.0f);
                GlStateManager.func_179139_a((double)((double)a4 * 0.8), (double)((double)a4 * 0.8), (double)((double)a4 * 0.8));
                ForgeHooksClient.multiplyCurrentGlMatrix((Matrix4f)((Matrix4f)a6.getRight()));
                GlStateManager.func_179152_a((float)-1.0f, (float)-1.0f, (float)1.0f);
                float f3 = (float)((double)System.currentTimeMillis() / 10.0 % 360.0);
                GL11.glRotatef((float)f3, (float)0.0f, (float)1.0f, (float)0.0f);
                tg.r(modelData, 0.0625f);
                GlStateManager.func_179121_F();
            } else if (modelData != null) {
                GlStateManager.func_179094_E();
                GL11.glTranslatef((float)a2, (float)a3, (float)500.0f);
                GL11.glScalef((float)10.0f, (float)10.0f, (float)-10.0f);
                GL11.glRotatef((float)30.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                float f4 = (float)((double)System.currentTimeMillis() / 10.0 % 360.0);
                GL11.glRotatef((float)f4, (float)0.0f, (float)1.0f, (float)0.0f);
                a3 = a4 / 32.0f;
                GlStateManager.func_179109_b((float)0.0f, (float)(a5 / 32.0f), (float)0.0f);
                float f5 = a3;
                GlStateManager.func_179152_a((float)f5, (float)f5, (float)f5);
                tg.r(modelData, 0.0625f);
                GlStateManager.func_179121_F();
            }
        }
        GlStateManager.func_179097_i();
        xd.r();
        GlStateManager.func_179133_A();
        GlStateManager.func_179119_h();
        GlStateManager.func_179101_C();
        RenderHelper.func_74518_a();
        GlStateManager.func_179099_b();
    }

    public static void r(int a2, int a32, int a42, EntityLivingBase a5) {
        RenderManager renderManager;
        GlStateManager.func_179142_g();
        GlStateManager.func_179094_E();
        GlStateManager.func_179126_j();
        GlStateManager.func_179109_b((float)a2, (float)a32, (float)50.0f);
        GlStateManager.func_179152_a((float)(-a42), (float)a42, (float)a42);
        GlStateManager.func_179114_b((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        EntityLivingBase entityLivingBase = a5;
        float a32 = entityLivingBase.field_70761_aq;
        float a42 = entityLivingBase.field_70177_z;
        float f2 = entityLivingBase.field_70125_A;
        float f3 = entityLivingBase.field_70758_at;
        float f4 = entityLivingBase.field_70759_as;
        GlStateManager.func_179114_b((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.func_74519_b();
        GlStateManager.func_179114_b((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        entityLivingBase.field_70761_aq = -1.0f;
        entityLivingBase.field_70177_z = -2.0f;
        entityLivingBase.field_70125_A = -2.5f;
        entityLivingBase.field_70759_as = entityLivingBase.field_70177_z;
        entityLivingBase.field_70758_at = entityLivingBase.field_70177_z;
        GlStateManager.func_179109_b((float)0.0f, (float)0.0f, (float)0.0f);
        RenderManager renderManager2 = renderManager = Minecraft.func_71410_x().func_175598_ae();
        RenderManager renderManager3 = renderManager;
        renderManager3.func_178631_a(180.0f);
        renderManager3.func_178633_a(false);
        renderManager2.func_188391_a((Entity)a5, 0.0, 0.0, 0.0, 0.0f, 1.0f, false);
        renderManager2.func_178633_a(true);
        entityLivingBase.field_70761_aq = a32;
        entityLivingBase.field_70177_z = a42;
        entityLivingBase.field_70125_A = f2;
        entityLivingBase.field_70758_at = f3;
        entityLivingBase.field_70759_as = f4;
        GlStateManager.func_179121_F();
        RenderHelper.func_74518_a();
        GlStateManager.func_179101_C();
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77476_b);
        GlStateManager.func_179090_x();
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77478_a);
    }

    public th() {
        th a2;
    }
}

