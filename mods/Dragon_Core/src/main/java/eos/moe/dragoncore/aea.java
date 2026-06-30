/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.Particle
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.RenderItem
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.block.model.IBakedModel
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.client.ForgeHooksClient
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.aka;
import eos.moe.dragoncore.cea;
import eos.moe.dragoncore.faa;
import eos.moe.dragoncore.oja;
import eos.moe.dragoncore.pa;
import eos.moe.dragoncore.pm;
import eos.moe.dragoncore.wka;
import eos.moe.dragoncore.ww;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class aea {
    public static aea b;
    private final Minecraft o = Minecraft.func_71410_x();
    private final RenderManager y = a2.o.func_175598_ae();
    private final RenderItem k = Minecraft.func_71410_x().func_175599_af();
    private final Random ALLATORIxDEMO = new Random();

    public aea() {
        aea a2;
        b = a2;
    }

    @SubscribeEvent
    public void ALLATORIxDEMO(RenderWorldLastEvent a2) {
        aea a3;
        if (a3.y.field_78734_h == null) {
            return;
        }
        GlStateManager.func_179094_E();
        for (oja a4 : aka.k.ALLATORIxDEMO().values()) {
            if (!"*".equalsIgnoreCase(a4.ALLATORIxDEMO()) && !wka.y.equals(a4.ALLATORIxDEMO())) continue;
            a3.ALLATORIxDEMO(a4, a2.getPartialTicks());
        }
        GlStateManager.func_179121_F();
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private /* synthetic */ void ALLATORIxDEMO(oja a2, float a3) {
        Vec3d a4;
        aea a5;
        Entity a6 = pm.ALLATORIxDEMO(a2.ALLATORIxDEMO());
        if (a2.ALLATORIxDEMO() != null && a6 == null) {
            return;
        }
        Vec3d a7 = a5.y.field_78734_h.func_174791_d();
        double a8 = a7.func_72438_d(a4 = a2.c());
        if (a8 > a2.ALLATORIxDEMO() && a6 == null) {
            return;
        }
        if (!a2.f() && Minecraft.func_71410_x().field_71474_y.field_74320_O == 0) {
            return;
        }
        GlStateManager.func_179094_E();
        GlStateManager.func_179140_f();
        GlStateManager.func_179129_p();
        if (a2.c()) {
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)240.0f, (float)240.0f);
        }
        if (a2.ALLATORIxDEMO() != null) {
            Vec3d a9 = a2.ALLATORIxDEMO();
            double a10 = a6.field_70142_S + (a6.field_70165_t - a6.field_70142_S) * (double)a3;
            double a11 = a6.field_70137_T + (a6.field_70163_u - a6.field_70137_T) * (double)a3;
            double a12 = a6.field_70136_U + (a6.field_70161_v - a6.field_70136_U) * (double)a3;
            float a13 = 0.0f;
            if (a6 instanceof EntityLivingBase) {
                EntityLivingBase a14 = (EntityLivingBase)a6;
                a13 = a5.ALLATORIxDEMO(a14.field_70760_ar, a14.field_70761_aq, a3);
            }
            double a15 = Math.toRadians(a13 + 90.0f);
            double a16 = Math.toRadians(90.0);
            double a17 = Math.sin(a16) * Math.cos(a15);
            double a18 = Math.sin(a16) * Math.sin(a15);
            a15 = a9.field_72450_a < 0.0 ? Math.toRadians(a13) : Math.toRadians(a13 + 180.0f);
            double a19 = Math.abs(a9.field_72450_a) * Math.sin(a16) * Math.cos(a15) + a9.field_72449_c * a17;
            double a20 = a9.field_72448_b;
            double a21 = Math.abs(a9.field_72450_a) * Math.sin(a16) * Math.sin(a15) + a9.field_72449_c * a18;
            a9 = new Vec3d(a10 - Particle.field_70556_an, a11 - Particle.field_70554_ao, a12 - Particle.field_70555_ap);
            GlStateManager.func_179137_b((double)a9.field_72450_a, (double)a9.field_72448_b, (double)a9.field_72449_c);
            GlStateManager.func_179137_b((double)a19, (double)a20, (double)a21);
            GlStateManager.func_179137_b((double)a2.c().field_72450_a, (double)a2.c().field_72448_b, (double)a2.c().field_72449_c);
        } else {
            double a22 = a4.field_72450_a - a5.y.field_78730_l;
            double a23 = a4.field_72448_b - a5.y.field_78731_m;
            double a24 = a4.field_72449_c - a5.y.field_78728_n;
            GlStateManager.func_179137_b((double)a22, (double)a23, (double)a24);
        }
        if (a2.x()) {
            GlStateManager.func_179114_b((float)(-a5.y.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.func_179114_b((float)a5.y.field_78732_j, (float)1.0f, (float)0.0f, (float)0.0f);
        } else if (a2.d()) {
            float a25 = 0.0f;
            if (a6 instanceof EntityLivingBase) {
                EntityLivingBase a26 = (EntityLivingBase)a6;
                a25 = a5.ALLATORIxDEMO(a26.field_70760_ar, a26.field_70761_aq, a3);
            }
            GlStateManager.func_179114_b((float)(-a25), (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.func_179114_b((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        }
        GlStateManager.func_179114_b((float)a2.f(), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)a2.x(), (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)a2.c(), (float)0.0f, (float)0.0f, (float)1.0f);
        GlStateManager.func_179114_b((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        long a27 = System.currentTimeMillis();
        cea.r = 1.0f;
        for (pa a28 : a2.ALLATORIxDEMO()) {
            a28.ALLATORIxDEMO(a27, a6, a3);
        }
        GlStateManager.func_179147_l();
        if (a2.ALLATORIxDEMO()) {
            GlStateManager.func_179097_i();
            GlStateManager.func_179132_a((boolean)false);
        }
        if (a2.ALLATORIxDEMO() != null) {
            GlStateManager.func_179109_b((float)0.0f, (float)-0.09375f, (float)0.0f);
            GlStateManager.func_179152_a((float)a2.d(), (float)a2.d(), (float)a2.d());
            GlStateManager.func_179109_b((float)0.0f, (float)0.09375f, (float)0.0f);
            GlStateManager.func_179114_b((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.func_179114_b((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)cea.r);
            a5.ALLATORIxDEMO(a2.ALLATORIxDEMO());
        } else if (a2.c().startsWith("[text]")) {
            Object a23 = a2.c().substring(6).replace("&", "\u00a7");
            a23 = faa.ALLATORIxDEMO((String)a23);
            float a29 = a2.d() / 9.0f;
            float a30 = a5.o.field_71466_p.func_78256_a((String)a23);
            GlStateManager.func_179109_b((float)(-(a30 *= a29) / 2.0f), (float)(-a2.d() / 2.0f), (float)0.0f);
            GlStateManager.func_179152_a((float)a29, (float)a29, (float)a29);
            int a31 = 0xFFFFFF | (int)(cea.r * 255.0f) << 24;
            a5.o.field_71466_p.func_175065_a((String)a23, 0.0f, 0.0f, a31, false);
        } else {
            ww.ALLATORIxDEMO(a2.c());
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)(a2.ALLATORIxDEMO() * cea.r));
            aea.ALLATORIxDEMO(0, 0, 0.0f, 0.0f, a2.k(), a2.d(), a2.k(), a2.d());
        }
        if (a2.ALLATORIxDEMO()) {
            GlStateManager.func_179132_a((boolean)true);
            GlStateManager.func_179126_j();
        }
        GlStateManager.func_179084_k();
        GlStateManager.func_179145_e();
        GlStateManager.func_179121_F();
    }

    public void ALLATORIxDEMO(ItemStack a2) {
        aea a3;
        int a4 = a2.func_190926_b() ? 187 : Item.func_150891_b((Item)a2.func_77973_b()) + a2.func_77960_j();
        a3.ALLATORIxDEMO.setSeed(a4);
        boolean a5 = false;
        if (a3.ALLATORIxDEMO()) {
            a3.y.field_78724_e.func_110581_b(a3.ALLATORIxDEMO()).func_174936_b(false, false);
            a5 = true;
        }
        GlStateManager.func_179091_B();
        GlStateManager.func_179092_a((int)516, (float)0.1f);
        GlStateManager.func_179147_l();
        RenderHelper.func_74519_b();
        GlStateManager.func_187428_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.func_179094_E();
        IBakedModel a6 = a3.k.func_184393_a(a2, (World)FMLClientHandler.instance().getWorldClient(), null);
        GlStateManager.func_179094_E();
        IBakedModel a7 = ForgeHooksClient.handleCameraTransforms((IBakedModel)a6, (ItemCameraTransforms.TransformType)ItemCameraTransforms.TransformType.GROUND, (boolean)false);
        a3.k.func_180454_a(a2, a7);
        GlStateManager.func_179121_F();
        GlStateManager.func_179121_F();
        GlStateManager.func_179101_C();
        GlStateManager.func_179084_k();
        a3.ALLATORIxDEMO();
        if (a5) {
            a3.y.field_78724_e.func_110581_b(a3.ALLATORIxDEMO()).func_174935_a();
        }
    }

    public ResourceLocation ALLATORIxDEMO() {
        return TextureMap.field_110575_b;
    }

    public boolean ALLATORIxDEMO() {
        aea a2;
        a2.y.field_78724_e.func_110577_a(TextureMap.field_110575_b);
        return true;
    }

    public float ALLATORIxDEMO(float a2, float a3, float a4) {
        float a5;
        for (a5 = a3 - a2; a5 < -180.0f; a5 += 360.0f) {
        }
        while (a5 >= 180.0f) {
            a5 -= 360.0f;
        }
        return a2 + a4 * a5;
    }

    public static void ALLATORIxDEMO(int a2, int a3, float a4, float a5, float a6, float a7, float a8, float a9) {
        a8 = 1.0f / a8;
        a9 = 1.0f / a9;
        Tessellator a10 = Tessellator.func_178181_a();
        BufferBuilder a11 = a10.func_178180_c();
        a11.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        a11.func_181662_b((double)((float)a2 - a6 / 2.0f), (double)((float)a3 + a7 - a7 / 2.0f), 0.0).func_187315_a((double)(a4 * a8), (double)((a5 + a7) * a9)).func_181675_d();
        a11.func_181662_b((double)((float)a2 + a6 - a6 / 2.0f), (double)((float)a3 + a7 - a7 / 2.0f), 0.0).func_187315_a((double)((a4 + a6) * a8), (double)((a5 + a7) * a9)).func_181675_d();
        a11.func_181662_b((double)((float)a2 + a6 - a6 / 2.0f), (double)((float)a3 - a7 / 2.0f), 0.0).func_187315_a((double)((a4 + a6) * a8), (double)(a5 * a9)).func_181675_d();
        a11.func_181662_b((double)((float)a2 - a6 / 2.0f), (double)((float)a3 - a7 / 2.0f), 0.0).func_187315_a((double)(a4 * a8), (double)(a5 * a9)).func_181675_d();
        a10.func_78381_a();
    }

    private static /* synthetic */ void ALLATORIxDEMO(int a2, int a3, float a4, float a5, float a6, float a7) {
        a6 = 1.0f / a6;
        a7 = 1.0f / a7;
        Tessellator a8 = Tessellator.func_178181_a();
        BufferBuilder a9 = a8.func_178180_c();
        a9.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        a9.func_181662_b((double)a2, (double)((float)a3 + a7), 0.0).func_187315_a((double)(a4 * a6), (double)((a5 + a7) * a7)).func_181675_d();
        a9.func_181662_b((double)((float)a2 + a6), (double)((float)a3 + a7), 0.0).func_187315_a((double)((a4 + a6) * a6), (double)((a5 + a7) * a7)).func_181675_d();
        a9.func_181662_b((double)((float)a2 + a6), (double)a3, 0.0).func_187315_a((double)((a4 + a6) * a6), (double)(a5 * a7)).func_181675_d();
        a9.func_181662_b((double)a2, (double)a3, 0.0).func_187315_a((double)(a4 * a6), (double)(a5 * a7)).func_181675_d();
        a8.func_78381_a();
    }
}

