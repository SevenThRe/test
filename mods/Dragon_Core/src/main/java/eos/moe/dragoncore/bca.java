/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.client.shader.ShaderManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.Vec2f
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  org.apache.commons.lang3.math.NumberUtils
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.dfa;
import eos.moe.dragoncore.hba;
import eos.moe.dragoncore.i;
import eos.moe.dragoncore.mea;
import eos.moe.dragoncore.sda;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.ShaderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.math.NumberUtils;

public class bca {
    public static bca c;
    private static final Minecraft q;
    private static final RenderManager b;
    public static boolean o;
    public static Map<String, hba> y;
    private static ShaderManager k;
    private static ShaderManager ALLATORIxDEMO;

    public bca() {
        bca a2;
        c = a2;
    }

    public static void ALLATORIxDEMO() {
        if (o) {
            return;
        }
        try {
            k = new ShaderManager(Minecraft.func_71410_x().func_110442_L(), "dragoncore:indicator/circle");
            ALLATORIxDEMO = new ShaderManager(Minecraft.func_71410_x().func_110442_L(), "dragoncore:indicator/rectangle");
            o = true;
        }
        catch (Exception a2) {
            throw new RuntimeException("\u65e0\u6cd5\u8f7d\u5165shader\u6587\u4ef6", a2);
        }
    }

    public static boolean ALLATORIxDEMO(String a2) {
        return a2.split("-").length >= 5;
    }

    @i(f={"indicator_circle"}, c=true)
    public static void c(String ... a4) {
        sda a5;
        double a6;
        String[] a7;
        if (a4.length == 1) {
            y.remove(a4[0]);
            return;
        }
        String a8 = UUID.randomUUID().toString();
        if (a4.length == 11) {
            a8 = a4[0];
            a4 = Arrays.copyOfRange(a4, 1, a4.length);
        }
        dfa a9 = new dfa();
        if (bca.ALLATORIxDEMO(a4[0])) {
            a7 = a4[0].split(",");
            a6 = a7.length == 2 ? Double.parseDouble(a7[1]) : 0.0;
            a5 = new sda(a6, (a2, a3) -> a2.field_70142_S + (a2.field_70165_t - a2.field_70142_S) * (double)Minecraft.func_71410_x().func_184121_ak());
            sda.c(a5, UUID.fromString(a7[0]));
            a9.x(a5);
        } else {
            a9.x(new sda(Double.parseDouble(a4[0]), null));
        }
        if (bca.ALLATORIxDEMO(a4[1])) {
            a7 = a4[1].split(",");
            a6 = a7.length == 2 ? Double.parseDouble(a7[1]) : 0.0;
            a5 = new sda(a6, (a2, a3) -> a2.field_70137_T + (a2.field_70163_u - a2.field_70137_T) * (double)Minecraft.func_71410_x().func_184121_ak());
            sda.c(a5, UUID.fromString(a7[0]));
            a9.f(a5);
        } else {
            a9.f(new sda(Double.parseDouble(a4[1]), null));
        }
        if (bca.ALLATORIxDEMO(a4[2])) {
            a7 = a4[2].split(",");
            a6 = a7.length == 2 ? Double.parseDouble(a7[1]) : 0.0;
            a5 = new sda(a6, (a2, a3) -> a2.field_70136_U + (a2.field_70161_v - a2.field_70136_U) * (double)Minecraft.func_71410_x().func_184121_ak());
            sda.c(a5, UUID.fromString(a7[0]));
            a9.c(a5);
        } else {
            a9.c(new sda(Double.parseDouble(a4[2]), null));
        }
        if (bca.ALLATORIxDEMO(a4[3])) {
            a7 = a4[3].split(",");
            a6 = a7.length == 2 ? Double.parseDouble(a7[1]) : 0.0;
            a5 = new sda(a6, (a2, a3) -> (double)a2.field_70126_B + (double)(a2.field_70177_z - a2.field_70126_B) * (double)Minecraft.func_71410_x().func_184121_ak());
            sda.c(a5, UUID.fromString(a7[0]));
            a9.ALLATORIxDEMO(a5);
        } else {
            a9.ALLATORIxDEMO(new sda(Double.parseDouble(a4[3]), null));
        }
        a9.ALLATORIxDEMO((int)Double.parseDouble(a4[4]));
        a9.x((int)Double.parseDouble(a4[5]));
        a9.f((int)Double.parseDouble(a4[6]));
        a9.c((int)Double.parseDouble(a4[7]));
        a9.d((int)Double.parseDouble(a4[8]));
        a9.ALLATORIxDEMO(Double.parseDouble(a4[9]));
        y.put(a8, a9);
    }

    @i(f={"indicator_rectangle"}, c=true)
    public static void ALLATORIxDEMO(String ... a4) {
        sda a5;
        double a6;
        String[] a7;
        if (a4.length == 1) {
            y.remove(a4[0]);
            return;
        }
        String a8 = UUID.randomUUID().toString();
        if (a4.length == 11) {
            a8 = a4[0];
            a4 = Arrays.copyOfRange(a4, 1, a4.length);
        }
        mea a9 = new mea();
        if (bca.ALLATORIxDEMO(a4[0])) {
            a7 = a4[0].split(",");
            a6 = a7.length == 2 ? Double.parseDouble(a7[1]) : 0.0;
            a5 = new sda(a6, (a2, a3) -> a2.field_70142_S + (a2.field_70165_t - a2.field_70142_S) * (double)Minecraft.func_71410_x().func_184121_ak());
            sda.c(a5, UUID.fromString(a7[0]));
            a9.x(a5);
        } else {
            a9.x(new sda(Double.parseDouble(a4[0]), null));
        }
        if (bca.ALLATORIxDEMO(a4[1])) {
            a7 = a4[1].split(",");
            a6 = a7.length == 2 ? Double.parseDouble(a7[1]) : 0.0;
            a5 = new sda(a6, (a2, a3) -> a2.field_70137_T + (a2.field_70163_u - a2.field_70137_T) * (double)Minecraft.func_71410_x().func_184121_ak());
            sda.c(a5, UUID.fromString(a7[0]));
            a9.f(a5);
        } else {
            a9.f(new sda(Double.parseDouble(a4[1]), null));
        }
        if (bca.ALLATORIxDEMO(a4[2])) {
            a7 = a4[2].split(",");
            a6 = a7.length == 2 ? Double.parseDouble(a7[1]) : 0.0;
            a5 = new sda(a6, (a2, a3) -> a2.field_70136_U + (a2.field_70161_v - a2.field_70136_U) * (double)Minecraft.func_71410_x().func_184121_ak());
            sda.c(a5, UUID.fromString(a7[0]));
            a9.c(a5);
        } else {
            a9.c(new sda(Double.parseDouble(a4[2]), null));
        }
        if (bca.ALLATORIxDEMO(a4[3])) {
            a7 = a4[3].split(",");
            a6 = NumberUtils.toDouble((String)a7[a7.length - 1]);
            a5 = null;
            if (bca.ALLATORIxDEMO(a7[1])) {
                a5 = new sda(a6, (a2, a3) -> {
                    Vec3d a4 = new Vec3d(a2.field_70142_S + (a2.field_70165_t - a2.field_70142_S) * (double)Minecraft.func_71410_x().func_184121_ak(), 0.0, a2.field_70136_U + (a2.field_70161_v - a2.field_70136_U) * (double)Minecraft.func_71410_x().func_184121_ak());
                    Vec3d a5 = new Vec3d(a3.field_70142_S + (a3.field_70165_t - a3.field_70142_S) * (double)Minecraft.func_71410_x().func_184121_ak(), 0.0, a3.field_70136_U + (a3.field_70161_v - a3.field_70136_U) * (double)Minecraft.func_71410_x().func_184121_ak());
                    Vec3d a6 = a5.func_178788_d(a4).func_72432_b();
                    Vec2f a7 = bca.ALLATORIxDEMO(a6);
                    return a7.field_189983_j;
                });
                sda.c(a5, UUID.fromString(a7[0]));
                sda.ALLATORIxDEMO(a5, UUID.fromString(a7[1]));
            } else if (bca.ALLATORIxDEMO(a7[0])) {
                a5 = new sda(a6, (a2, a3) -> (double)a2.field_70126_B + (double)(a2.field_70177_z - a2.field_70126_B) * (double)Minecraft.func_71410_x().func_184121_ak());
                sda.c(a5, UUID.fromString(a7[0]));
            }
            a9.ALLATORIxDEMO(a5);
        } else {
            a9.ALLATORIxDEMO(new sda(Double.parseDouble(a4[3]), null));
        }
        a9.ALLATORIxDEMO((int)Double.parseDouble(a4[4]));
        a9.x((int)Double.parseDouble(a4[5]));
        a9.f((int)Double.parseDouble(a4[6]));
        a9.c((int)Double.parseDouble(a4[7]));
        a9.ALLATORIxDEMO((double)((int)Double.parseDouble(a4[8])));
        if (bca.ALLATORIxDEMO(a4[9])) {
            a7 = a4[9].split(",");
            a6 = NumberUtils.toDouble((String)a7[a7.length - 1]);
            a5 = new sda(a6, (a2, a3) -> {
                Vec3d a4 = new Vec3d(a2.field_70142_S + (a2.field_70165_t - a2.field_70142_S) * (double)Minecraft.func_71410_x().func_184121_ak(), 0.0, a2.field_70136_U + (a2.field_70161_v - a2.field_70136_U) * (double)Minecraft.func_71410_x().func_184121_ak());
                Vec3d a5 = new Vec3d(a3.field_70142_S + (a3.field_70165_t - a3.field_70142_S) * (double)Minecraft.func_71410_x().func_184121_ak(), 0.0, a3.field_70136_U + (a3.field_70161_v - a3.field_70136_U) * (double)Minecraft.func_71410_x().func_184121_ak());
                return a4.func_72438_d(a5) + 1.0;
            });
            sda.c(a5, UUID.fromString(a7[0]));
            sda.ALLATORIxDEMO(a5, UUID.fromString(a7[1]));
            a9.d(a5);
        } else {
            a9.d(new sda(Double.parseDouble(a4[9]), null));
        }
        y.put(a8, a9);
    }

    public static Vec2f ALLATORIxDEMO(Vec3d a2) {
        double a3 = a2.field_72450_a;
        double a4 = a2.field_72448_b;
        double a5 = a2.field_72449_c;
        if (a3 == 0.0 && a5 == 0.0) {
            float a6 = a4 > 0.0 ? -90 : 90;
            return new Vec2f(a6, 0.0f);
        }
        double a7 = Math.atan2(-a3, a5);
        float a8 = (float)Math.toDegrees((a7 + Math.PI * 2) % (Math.PI * 2));
        double a9 = a3 * a3;
        double a10 = a5 * a5;
        double a11 = Math.sqrt(a9 + a10);
        float a12 = (float)Math.toDegrees(Math.atan(-a4 / a11));
        return new Vec2f(a12, a8);
    }

    @SubscribeEvent
    public void ALLATORIxDEMO(RenderWorldLastEvent a2) {
        if (bca.b.field_78734_h == null) {
            return;
        }
        bca.ALLATORIxDEMO();
        Iterator<Map.Entry<String, hba>> a3 = y.entrySet().iterator();
        long a4 = System.currentTimeMillis();
        while (a3.hasNext()) {
            bca a5;
            Map.Entry<String, hba> a6 = a3.next();
            hba a7 = a6.getValue();
            if (a4 > a7.v + (long)a7.b) {
                a3.remove();
                continue;
            }
            GlStateManager.func_179094_E();
            a5.ALLATORIxDEMO(a7);
            GlStateManager.func_179121_F();
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(hba a2) {
        long a3 = System.currentTimeMillis() - a2.v;
        double a4 = 0.0;
        double a5 = 0.0;
        double a6 = 0.0;
        if (a3 < 490L) {
            a6 = a4 = (double)((float)a3 / 700.0f + 0.3f);
        } else if (a3 < (long)(a2.b - 300)) {
            a4 = 1.0;
            a5 = (float)(a3 - 490L) % 1000.0f / 1000.0f;
            a6 = 1.0;
        } else {
            a6 = 1.0f - (float)(a3 - (long)a2.b + 300L) / 300.0f;
            a4 = 1.0;
            a5 = 0.0;
        }
        a4 = bca.ALLATORIxDEMO(a4);
        Minecraft.func_71410_x().func_110434_K().func_110577_a(new ResourceLocation("textures/misc/shadow.png"));
        GlStateManager.func_179147_l();
        GlStateManager.func_179129_p();
        GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.func_179132_a((boolean)false);
        a2.ALLATORIxDEMO();
        a2.c((float)a6);
        a2.ALLATORIxDEMO((float)a4);
        if (a5 > 0.0) {
            a2.c((float)a6);
            a2.ALLATORIxDEMO((float)a5);
        }
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.func_179084_k();
        GlStateManager.func_179132_a((boolean)true);
    }

    public static double ALLATORIxDEMO(double a2) {
        return -(Math.cos(Math.PI * a2) - 1.0) / 2.0;
    }

    public static void ALLATORIxDEMO(float a2) {
        Tessellator a3 = Tessellator.func_178181_a();
        BufferBuilder a4 = a3.func_178180_c();
        a4.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        a4.func_181662_b((double)(-a2), 0.0, (double)a2).func_187315_a(0.0, 1.0).func_181675_d();
        a4.func_181662_b((double)a2, 0.0, (double)a2).func_187315_a(1.0, 1.0).func_181675_d();
        a4.func_181662_b((double)a2, 0.0, (double)(-a2)).func_187315_a(1.0, 0.0).func_181675_d();
        a4.func_181662_b((double)(-a2), 0.0, (double)(-a2)).func_187315_a(0.0, 0.0).func_181675_d();
        a3.func_78381_a();
    }

    public static /* synthetic */ RenderManager ALLATORIxDEMO() {
        return b;
    }

    public static /* synthetic */ ShaderManager c() {
        return k;
    }

    public static /* synthetic */ ShaderManager ALLATORIxDEMO() {
        return ALLATORIxDEMO;
    }

    static {
        q = Minecraft.func_71410_x();
        b = q.func_175598_ae();
        y = new HashMap<String, hba>();
    }
}

