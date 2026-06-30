/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.renderer.ActiveRenderInfo
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.EntityViewRenderEvent$CameraSetup
 *  net.minecraftforge.client.event.FOVUpdateEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.Event
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ala;
import eos.moe.dragoncore.ca;
import eos.moe.dragoncore.gfa;
import eos.moe.dragoncore.gka;
import eos.moe.dragoncore.laa;
import eos.moe.dragoncore.um;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid="dragoncore")
public class qha {
    public static float e;
    public static long n;
    public static float j;
    public static int i;
    public static int l;
    public static int z;
    public static long s;
    public static ala g;
    public static long t;
    public static long r;
    public static float x;
    public static float v;
    public static float m;
    public static int c;
    public static int q;
    public static int b;
    public static long o;
    public static float y;
    private static final List<gfa> k;
    private static final List<gfa> ALLATORIxDEMO;

    public qha() {
        qha a2;
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(FOVUpdateEvent a2) {
        if (System.currentTimeMillis() < n) {
            a2.setNewfov(a2.getFov() * e);
        }
    }

    public static boolean ALLATORIxDEMO() {
        if (System.currentTimeMillis() > r) {
            if (g != null) {
                ala.ALLATORIxDEMO(g);
                g = null;
            }
            return false;
        }
        return true;
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(EntityViewRenderEvent.CameraSetup a2) {
        float a3;
        EntityPlayerSP a4;
        Entity a5 = Minecraft.func_71410_x().func_175606_aa();
        if (a5 instanceof EntityLivingBase && System.currentTimeMillis() <= s + (long)(i + l) * (long)z) {
            long a6 = System.currentTimeMillis() - s;
            long a7 = a6 % (long)(i + l);
            EntityLivingBase a8 = (EntityLivingBase)a5;
            float a9 = 0.0f;
            if (a7 > (long)i) {
                long a10 = a7 - (long)i;
                a9 = j - j * ((float)a10 / (float)l);
            } else {
                a9 = j * ((float)a7 / (float)i);
            }
            float a11 = a8.field_70739_aP;
            GlStateManager.func_179114_b((float)(-a11), (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.func_179114_b((float)(-a9), (float)0.0f, (float)0.0f, (float)1.0f);
            GlStateManager.func_179114_b((float)a11, (float)0.0f, (float)1.0f, (float)0.0f);
        }
        if ((a4 = Minecraft.func_71410_x().field_71439_g) != null) {
            a3 = Minecraft.func_71410_x().func_184121_ak();
            float a12 = (float)a4.field_70173_aa + a3;
            float a13 = 0.0f;
            for (gfa a14 : k) {
                if (gfa.ALLATORIxDEMO((gfa)a14).field_72450_a == 0.0 && gfa.ALLATORIxDEMO((gfa)a14).field_72448_b == 0.0 && gfa.ALLATORIxDEMO((gfa)a14).field_72449_c == 0.0) {
                    a13 += a14.ALLATORIxDEMO((EntityPlayer)a4, a3);
                    continue;
                }
                if (!(gfa.ALLATORIxDEMO(a14).func_72438_d(a4.func_174791_d()) < (double)gfa.ALLATORIxDEMO(a14))) continue;
                a13 += a14.ALLATORIxDEMO((EntityPlayer)a4, a3);
            }
            if (a13 > 1.0f) {
                a13 = 1.0f;
            }
            a2.setPitch((float)((double)a2.getPitch() + (double)a13 * Math.cos(a12 * 3.0f + 2.0f) * 25.0));
            a2.setYaw((float)((double)a2.getYaw() + (double)a13 * Math.cos(a12 * 5.0f + 1.0f) * 25.0));
            a2.setRoll((float)((double)a2.getRoll() + (double)a13 * Math.cos(a12 * 4.0f) * 25.0));
        }
        if (a5 != null && System.currentTimeMillis() < o) {
            a3 = Minecraft.func_71410_x().func_184121_ak();
            float a15 = (float)a5.field_70173_aa + a3;
            a2.setPitch((float)((double)a2.getPitch() + (double)y * Math.cos(a15 * 3.0f + 2.0f) * 25.0));
            a2.setYaw((float)((double)a2.getYaw() + (double)y * Math.cos(a15 * 5.0f + 1.0f) * 25.0));
            a2.setRoll((float)((double)a2.getRoll() + (double)y * Math.cos(a15 * 4.0f) * 25.0));
        }
    }

    public static void ALLATORIxDEMO(float a2) throws RuntimeException {
        RayTraceResult a3;
        float a4;
        float a5;
        long a6;
        Minecraft a7 = Minecraft.func_71410_x();
        Entity a8 = a7.func_175606_aa();
        float a9 = -999.0f;
        if (g != ala.y) {
            ala.ALLATORIxDEMO(ala.b);
        }
        double a10 = 0.0;
        double a11 = m;
        if (g == ala.q) {
            a11 += (double)3.6f;
        }
        if ((a6 = System.currentTimeMillis() - t) < (long)c) {
            a10 = a11 * (double)a6 / (double)c;
            if (v != -999.0f) {
                a9 = um.x(x, v, (float)a6 / (float)c);
            }
        } else if (a6 < (long)(c + q)) {
            a10 = a11;
            if (v != -999.0f) {
                a9 = v;
            }
        } else {
            a6 = a6 - (long)c - (long)q;
            a10 = a11 - a11 * (double)a6 / (double)b;
            if (v != -999.0f) {
                float a12 = a8.field_70127_C + (a8.field_70125_A - a8.field_70127_C) * a2;
                a9 = um.x(v, a12, (float)a6 / (float)b);
            }
        }
        if (g == ala.q) {
            a10 += (double)0.4f;
        } else if (g == ala.b || g == ala.o) {
            a10 += 4.0;
        }
        EntityRenderer a13 = a7.field_71460_t;
        float a14 = a8.func_70047_e();
        double a15 = a8.field_70169_q + (a8.field_70165_t - a8.field_70169_q) * (double)a2;
        double a16 = a8.field_70167_r + (a8.field_70163_u - a8.field_70167_r) * (double)a2 + (double)a14;
        double a17 = a8.field_70166_s + (a8.field_70161_v - a8.field_70166_s) * (double)a2;
        float a18 = a8.field_70177_z;
        float a19 = a8.field_70125_A;
        if (a7.field_71474_y.field_74320_O == 2) {
            a19 += 180.0f;
        }
        double a20 = (double)(-MathHelper.func_76126_a((float)(a18 * ((float)Math.PI / 180))) * MathHelper.func_76134_b((float)(a19 * ((float)Math.PI / 180)))) * a10;
        double a21 = (double)(MathHelper.func_76134_b((float)(a18 * ((float)Math.PI / 180))) * MathHelper.func_76134_b((float)(a19 * ((float)Math.PI / 180)))) * a10;
        double a22 = (double)(-MathHelper.func_76126_a((float)(a19 * ((float)Math.PI / 180)))) * a10;
        for (int a23 = 0; a23 < 8; ++a23) {
            double a24;
            a5 = (a23 & 1) * 2 - 1;
            a4 = (a23 >> 1 & 1) * 2 - 1;
            float a25 = (a23 >> 2 & 1) * 2 - 1;
            if ((a3 = a7.field_71441_e.func_72933_a(new Vec3d(a15 + (double)(a5 *= 0.1f), a16 + (double)(a4 *= 0.1f), a17 + (double)(a25 *= 0.1f)), new Vec3d(a15 - a20 + (double)a5 + (double)a25, a16 - a22 + (double)a4, a17 - a21 + (double)a25))) == null || !((a24 = a3.field_72307_f.func_72438_d(new Vec3d(a15, a16, a17))) < a10)) continue;
            a10 = a24;
        }
        if (a7.field_71474_y.field_74320_O == 2) {
            GlStateManager.func_179114_b((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        }
        GlStateManager.func_179114_b((float)(a8.field_70125_A - a19), (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)(a8.field_70177_z - a18), (float)0.0f, (float)1.0f, (float)0.0f);
        if (ca.y) {
            gka.ALLATORIxDEMO(0.0f, 0.0f, (float)(-a10), a18, a19);
        } else if (ca.k) {
            laa.ALLATORIxDEMO(0.0f, 0.0f, (float)(-a10), a18, a19);
        } else {
            GlStateManager.func_179109_b((float)0.0f, (float)0.0f, (float)((float)(-a10)));
        }
        GlStateManager.func_179114_b((float)(a18 - a8.field_70177_z), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)(a19 - a8.field_70125_A), (float)1.0f, (float)0.0f, (float)0.0f);
        if (!a7.field_71474_y.field_74325_U) {
            float a26 = a8.field_70126_B + (a8.field_70177_z - a8.field_70126_B) * a2 + 180.0f;
            a5 = a8.field_70127_C + (a8.field_70125_A - a8.field_70127_C) * a2;
            a4 = 0.0f;
            if (a8 instanceof EntityAnimal) {
                EntityAnimal a27 = (EntityAnimal)a8;
                a26 = a27.field_70758_at + (a27.field_70759_as - a27.field_70758_at) * a2 + 180.0f;
            }
            IBlockState a28 = ActiveRenderInfo.func_186703_a((World)a7.field_71441_e, (Entity)a8, (float)a2);
            a3 = new EntityViewRenderEvent.CameraSetup(a13, a8, a28, (double)a2, a26, a5, a4);
            MinecraftForge.EVENT_BUS.post((Event)a3);
            GlStateManager.func_179114_b((float)a3.getRoll(), (float)0.0f, (float)0.0f, (float)1.0f);
            GlStateManager.func_179114_b((float)(a9 == -999.0f ? a3.getPitch() : a9), (float)1.0f, (float)0.0f, (float)0.0f);
            GlStateManager.func_179114_b((float)a3.getYaw(), (float)0.0f, (float)1.0f, (float)0.0f);
        }
        GlStateManager.func_179109_b((float)0.0f, (float)(-a14), (float)0.0f);
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(TickEvent.ClientTickEvent a2) {
        if (a2.phase == TickEvent.Phase.START) {
            if (!ALLATORIxDEMO.isEmpty()) {
                k.removeAll(ALLATORIxDEMO);
                ALLATORIxDEMO.clear();
            }
            for (gfa a3 : k) {
                a3.c();
            }
        }
    }

    public static void ALLATORIxDEMO(Vec3d a2, float a3, float a4, int a5, int a6) {
        gfa a7 = new gfa(a2, a3, a4, a5, a6);
        k.add(a7);
    }

    public static /* synthetic */ List ALLATORIxDEMO() {
        return ALLATORIxDEMO;
    }

    static {
        t = Long.MAX_VALUE;
        k = new ArrayList<gfa>();
        ALLATORIxDEMO = new ArrayList<gfa>();
    }
}

