/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.teamderpy.shouldersurfing.config.Config
 *  com.teamderpy.shouldersurfing.util.ShoulderState
 *  com.teamderpy.shouldersurfing.util.ShoulderSurfingHelper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.Particle
 *  net.minecraft.client.renderer.ActiveRenderInfo
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 */
package eos.moe.dragoncore;

import com.teamderpy.shouldersurfing.config.Config;
import com.teamderpy.shouldersurfing.util.ShoulderState;
import com.teamderpy.shouldersurfing.util.ShoulderSurfingHelper;
import eos.moe.dragoncore.bca;
import eos.moe.dragoncore.wka;
import eos.moe.dragoncore.ww;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid="dragoncore")
public class nfa {
    public static List<Particle> ALLATORIxDEMO = new ArrayList<Particle>();

    public nfa() {
        nfa a2;
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(RenderWorldLastEvent a2) {
        if (Minecraft.func_71410_x().func_175606_aa() == null) {
            return;
        }
        if (!wka.k) {
            return;
        }
        if (ALLATORIxDEMO.isEmpty()) {
            return;
        }
        Entity a3 = Minecraft.func_71410_x().func_175606_aa();
        float a4 = a2.getPartialTicks();
        float a5 = ActiveRenderInfo.func_178808_b();
        float a6 = ActiveRenderInfo.func_178803_d();
        float a7 = ActiveRenderInfo.func_178805_e();
        float a8 = ActiveRenderInfo.func_178807_f();
        float a9 = ActiveRenderInfo.func_178809_c();
        Particle.field_70556_an = a3.field_70142_S + (a3.field_70165_t - a3.field_70142_S) * (double)a4;
        Particle.field_70554_ao = a3.field_70137_T + (a3.field_70163_u - a3.field_70137_T) * (double)a4;
        Particle.field_70555_ap = a3.field_70136_U + (a3.field_70161_v - a3.field_70136_U) * (double)a4;
        Particle.field_190016_K = a3.func_70676_i(a4);
        GlStateManager.func_179147_l();
        GlStateManager.func_179097_i();
        GlStateManager.func_179132_a((boolean)false);
        GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.func_179092_a((int)516, (float)0.003921569f);
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)240.0f, (float)240.0f);
        ww.ALLATORIxDEMO("damagepic11.png");
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Tessellator a10 = Tessellator.func_178181_a();
        BufferBuilder a11 = a10.func_178180_c();
        a11.func_181668_a(7, DefaultVertexFormats.field_181704_d);
        for (Particle a12 : ALLATORIxDEMO) {
            a12.func_180434_a(a11, a3, a4, a5, a9, a6, a7, a8);
        }
        a10.func_78381_a();
        GlStateManager.func_179132_a((boolean)true);
        GlStateManager.func_179126_j();
        GlStateManager.func_179084_k();
        GlStateManager.func_179092_a((int)516, (float)0.1f);
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(TickEvent.ClientTickEvent a2) {
        if (a2.phase == TickEvent.Phase.START) {
            if (ALLATORIxDEMO.isEmpty()) {
                return;
            }
            Iterator<Particle> a3 = ALLATORIxDEMO.iterator();
            while (a3.hasNext()) {
                Particle a4 = a3.next();
                a4.func_189213_a();
                if (a4.func_187113_k()) continue;
                a3.remove();
            }
        }
    }

    public static Vec3d ALLATORIxDEMO(float a2, float a3) {
        float a4 = MathHelper.func_76134_b((float)(-a3 * ((float)Math.PI / 180) - (float)Math.PI));
        float a5 = MathHelper.func_76126_a((float)(-a3 * ((float)Math.PI / 180) - (float)Math.PI));
        float a6 = -MathHelper.func_76134_b((float)(-a2 * ((float)Math.PI / 180)));
        float a7 = MathHelper.func_76126_a((float)(-a2 * ((float)Math.PI / 180)));
        return new Vec3d((double)(a5 * a6), (double)a7, (double)(a4 * a6));
    }

    public static Vec3d c(Entity a2) {
        Entity a3 = Minecraft.func_71410_x().func_175598_ae().field_78734_h;
        double a4 = a3.func_70032_d(a2) + 5.0f;
        Vec3d a5 = a3.func_174824_e(1.0f);
        Vec3d a6 = nfa.ALLATORIxDEMO(a3.field_70125_A, a3.field_70177_z);
        Map.Entry<Vec3d, Vec3d> a7 = nfa.ALLATORIxDEMO(a6, a4);
        AxisAlignedBB a8 = a2.func_174813_aQ().func_186662_g((double)a2.func_70111_Y());
        RayTraceResult a9 = a8.func_72327_a(a7.getKey(), a7.getValue());
        if (a8.func_72318_a(a5)) {
            return a9 == null ? a5 : a9.field_72307_f;
        }
        if (a9 != null) {
            return a9.field_72307_f;
        }
        return null;
    }

    public static Vec3d ALLATORIxDEMO(Entity a2) {
        Entity a3 = Minecraft.func_71410_x().func_175598_ae().field_78734_h;
        Vec3d a4 = new Vec3d(a2.field_70165_t - a3.field_70165_t, 0.0, a2.field_70161_v - a3.field_70161_v);
        float a5 = bca.ALLATORIxDEMO((Vec3d)a4.func_72432_b()).field_189983_j;
        double a6 = a3.func_70032_d(a2) + 5.0f;
        Vec3d a7 = a3.func_174824_e(1.0f);
        Vec3d a8 = nfa.ALLATORIxDEMO(a3.field_70125_A, a5);
        Map.Entry<Vec3d, Vec3d> a9 = nfa.ALLATORIxDEMO(a8, a6);
        AxisAlignedBB a10 = a2.func_174813_aQ().func_186662_g((double)a2.func_70111_Y());
        RayTraceResult a11 = a10.func_72327_a(a9.getKey(), a9.getValue());
        if (a10.func_72318_a(a7)) {
            return a11 == null ? a7 : a11.field_72307_f;
        }
        if (a11 != null) {
            return a11.field_72307_f;
        }
        return null;
    }

    public static Map.Entry<Vec3d, Vec3d> ALLATORIxDEMO(Vec3d a2, double a3) {
        if (ShoulderState.doShoulderSurfing() && !Config.CLIENT.getCrosshairType().isHoldingSpecialItem()) {
            return ShoulderSurfingHelper.shoulderSurfingLook((Entity)Minecraft.func_71410_x().func_175606_aa(), (float)Minecraft.func_71410_x().func_184121_ak(), (double)(a3 * a3));
        }
        Entity a4 = Minecraft.func_71410_x().func_175606_aa();
        Vec3d a5 = a4.func_174824_e(Minecraft.func_71410_x().func_184121_ak());
        Vec3d a6 = a5.func_72441_c(a2.field_72450_a * a3, a2.field_72448_b * a3, a2.field_72449_c * a3);
        return new AbstractMap.SimpleEntry<Vec3d, Vec3d>(a5, a6);
    }
}

