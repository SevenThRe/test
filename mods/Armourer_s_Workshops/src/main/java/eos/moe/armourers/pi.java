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
        a3.r((EntityLivingBase)Minecraft.func_71410_x().field_71439_g);
    }

    public static void r() {
        v = new pi();
    }

    private /* synthetic */ void r(EntityLivingBase a2) {
        RenderManager renderManager;
        pi a3;
        GlStateManager.func_179142_g();
        GlStateManager.func_179094_E();
        pi pi2 = a3;
        double d2 = pi2.j.l - pi.m.field_78730_l;
        double d3 = pi2.j.m - pi.m.field_78731_m;
        double d4 = pi2.j.t - pi.m.field_78728_n;
        GlStateManager.func_179137_b((double)d2, (double)d3, (double)d4);
        float f2 = pi2.j.j;
        GlStateManager.func_179152_a((float)f2, (float)f2, (float)f2);
        EntityLivingBase entityLivingBase = a2;
        EntityLivingBase entityLivingBase2 = a2;
        AxisAlignedBB axisAlignedBB = entityLivingBase2.func_184177_bl();
        d2 = 0.8f;
        d2 = 1.9;
        d2 = 0.8f;
        AxisAlignedBB axisAlignedBB2 = axisAlignedBB;
        d2 = Math.floor((axisAlignedBB2.field_72336_d - axisAlignedBB.field_72340_a) * 1000.0) / 1000.0;
        AxisAlignedBB axisAlignedBB3 = axisAlignedBB;
        d3 = Math.floor((axisAlignedBB2.field_72337_e - axisAlignedBB3.field_72338_b) * 1000.0) / 1000.0;
        d4 = Math.floor((axisAlignedBB3.field_72334_f - axisAlignedBB.field_72339_c) * 1000.0) / 1000.0;
        double d5 = 1.0;
        d5 = Math.max(1.0, d2 / (double)0.8f);
        d5 = Math.max(d5, d3 / 1.9);
        d5 = Math.max(d5, d4 / (double)0.8f);
        d5 = 1.0 / d5;
        GlStateManager.func_179139_a((double)d5, (double)d5, (double)d5);
        float f3 = entityLivingBase2.field_70761_aq;
        float f4 = entityLivingBase2.field_70177_z;
        float f5 = entityLivingBase2.field_70125_A;
        float f6 = entityLivingBase2.field_70758_at;
        float f7 = entityLivingBase2.field_70759_as;
        GlStateManager.func_179114_b((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.func_74519_b();
        GlStateManager.func_179114_b((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        entityLivingBase2.field_70761_aq = a3.j.w;
        entityLivingBase2.field_70177_z = a3.j.r;
        entityLivingBase2.field_70125_A = a3.j.s;
        entityLivingBase2.field_70759_as = a3.j.r;
        entityLivingBase2.field_70758_at = a3.j.r;
        GlStateManager.func_179109_b((float)0.0f, (float)0.0f, (float)0.0f);
        RenderManager renderManager2 = renderManager = Minecraft.func_71410_x().func_175598_ae();
        RenderManager renderManager3 = renderManager;
        renderManager3.func_178631_a(180.0f);
        renderManager3.func_178633_a(false);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        renderManager2.func_188391_a((Entity)a2, 0.0, 0.0, 0.0, 0.0f, 1.0f, false);
        renderManager2.func_178633_a(true);
        entityLivingBase2.field_70761_aq = f3;
        entityLivingBase.field_70177_z = f4;
        entityLivingBase.field_70125_A = f5;
        entityLivingBase.field_70758_at = f6;
        entityLivingBase.field_70759_as = f7;
        GlStateManager.func_179121_F();
        RenderHelper.func_74518_a();
        GlStateManager.func_179101_C();
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77476_b);
        GlStateManager.func_179090_x();
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77478_a);
    }

    public pi() {
        pi a2;
        MinecraftForge.EVENT_BUS.register((Object)a2);
    }

    static {
        s = Minecraft.func_71410_x();
        m = s.func_175598_ae();
    }
}

