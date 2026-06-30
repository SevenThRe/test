/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.client.shader.Framebuffer
 *  net.minecraft.client.shader.ShaderManager
 */
package eos.moe.dragoncore;

import java.io.IOException;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.ShaderManager;

public class yp {
    private static final Minecraft l = Minecraft.func_71410_x();
    private static int z;
    private static int s;
    private static Framebuffer g;
    private static Framebuffer t;
    private static Framebuffer r;
    private static Framebuffer x;
    private static Framebuffer v;
    private static Framebuffer m;
    private static Framebuffer c;
    private static Framebuffer q;
    private static final ShaderManager b;
    private static final ShaderManager o;
    public static final ShaderManager y;
    private static int k;
    private static int ALLATORIxDEMO;

    public yp() {
        yp a2;
    }

    private static /* synthetic */ ShaderManager ALLATORIxDEMO(String a2) {
        try {
            return new ShaderManager(Minecraft.func_71410_x().func_110442_L(), a2);
        }
        catch (IOException a3) {
            throw new RuntimeException(a3);
        }
    }

    public static Framebuffer c() {
        if (g == null) {
            g = yp.ALLATORIxDEMO(null, yp.l.func_147110_a().field_147621_c, yp.l.func_147110_a().field_147618_d, true);
            yp.ALLATORIxDEMO(g, yp.l.func_147110_a().field_147624_h);
        }
        return g;
    }

    public static Framebuffer ALLATORIxDEMO() {
        int a2 = yp.l.func_147110_a().field_147621_c;
        int a3 = yp.l.func_147110_a().field_147618_d;
        if (k != a2 || ALLATORIxDEMO != a3) {
            t = yp.ALLATORIxDEMO(t, a2, a3, false);
            k = a2;
            ALLATORIxDEMO = a3;
        }
        return t;
    }

    public static void ALLATORIxDEMO(Framebuffer a2, int a3) {
        a2.func_147610_a(false);
        OpenGlHelper.func_153176_h((int)OpenGlHelper.field_153199_f, (int)a3);
        if (!a2.isStencilEnabled()) {
            OpenGlHelper.func_153186_a((int)OpenGlHelper.field_153199_f, (int)33190, (int)a2.field_147622_a, (int)a2.field_147620_b);
            OpenGlHelper.func_153190_b((int)OpenGlHelper.field_153198_e, (int)OpenGlHelper.field_153201_h, (int)OpenGlHelper.field_153199_f, (int)a3);
        } else {
            OpenGlHelper.func_153186_a((int)OpenGlHelper.field_153199_f, (int)35056, (int)a2.field_147622_a, (int)a2.field_147620_b);
            OpenGlHelper.func_153190_b((int)OpenGlHelper.field_153198_e, (int)36096, (int)OpenGlHelper.field_153199_f, (int)a3);
            OpenGlHelper.func_153190_b((int)OpenGlHelper.field_153198_e, (int)36128, (int)OpenGlHelper.field_153199_f, (int)a3);
        }
    }

    public static void ALLATORIxDEMO(int a2, int a3) {
        if (z == a2 && s == a3) {
            return;
        }
        g = yp.ALLATORIxDEMO(null, a2, a3, true);
        yp.ALLATORIxDEMO(g, yp.l.func_147110_a().field_147624_h);
        t = yp.ALLATORIxDEMO(t, a2, a3, false);
        r = yp.ALLATORIxDEMO(r, a2 / 2, a3 / 2, false);
        x = yp.ALLATORIxDEMO(x, a2 / 4, a3 / 4, false);
        v = yp.ALLATORIxDEMO(v, a2 / 8, a3 / 8, false);
        m = yp.ALLATORIxDEMO(m, a2 / 2, a3 / 2, false);
        c = yp.ALLATORIxDEMO(c, a2 / 4, a3 / 4, false);
        q = yp.ALLATORIxDEMO(q, a2 / 8, a3 / 8, false);
        z = a2;
        s = a3;
    }

    private static /* synthetic */ Framebuffer ALLATORIxDEMO(@Nullable Framebuffer target, int width, int height, boolean useDepth) {
        if (target == null) {
            target = new Framebuffer(width, height, useDepth);
            target.func_147604_a(0.0f, 0.0f, 0.0f, 0.0f);
        }
        target.func_147613_a(width, height);
        target.func_147607_a(9729);
        return target;
    }

    public static void ALLATORIxDEMO(int a2, int a3, int a4, int a5, Framebuffer a6) {
        yp.ALLATORIxDEMO(a2, a3);
        GlStateManager.func_179135_a((boolean)true, (boolean)true, (boolean)true, (boolean)true);
        GlStateManager.func_179097_i();
        GlStateManager.func_179132_a((boolean)false);
        GlStateManager.func_179147_l();
        GlStateManager.func_179120_a((int)GlStateManager.SourceFactor.SRC_ALPHA.field_187395_p, (int)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA.field_187345_o, (int)GlStateManager.SourceFactor.ONE.field_187395_p, (int)GlStateManager.DestFactor.ZERO.field_187345_o);
        b.func_147992_a("DiffuseSampler", (Object)a5);
        b.func_147984_b("BlurDir").func_148087_a(1.0f, 0.0f);
        b.func_147984_b("Radius").func_148083_a(3, 0, 0, 0);
        b.func_147984_b("OutSize").func_148087_a((float)yp.r.field_147621_c, (float)yp.r.field_147618_d);
        yp.ALLATORIxDEMO(b, r);
        b.func_147992_a("DiffuseSampler", (Object)r);
        b.func_147984_b("BlurDir").func_148087_a(0.0f, 1.0f);
        b.func_147984_b("Radius").func_148083_a(3, 0, 0, 0);
        b.func_147984_b("OutSize").func_148087_a((float)yp.m.field_147621_c, (float)yp.m.field_147618_d);
        yp.ALLATORIxDEMO(b, m);
        b.func_147992_a("DiffuseSampler", (Object)m);
        b.func_147984_b("BlurDir").func_148087_a(1.0f, 0.0f);
        b.func_147984_b("Radius").func_148083_a(5, 0, 0, 0);
        b.func_147984_b("OutSize").func_148087_a((float)yp.x.field_147621_c, (float)yp.x.field_147618_d);
        yp.ALLATORIxDEMO(b, x);
        b.func_147992_a("DiffuseSampler", (Object)x);
        b.func_147984_b("BlurDir").func_148087_a(0.0f, 1.0f);
        b.func_147984_b("Radius").func_148083_a(5, 0, 0, 0);
        b.func_147984_b("OutSize").func_148087_a((float)yp.c.field_147621_c, (float)yp.c.field_147618_d);
        yp.ALLATORIxDEMO(b, c);
        b.func_147992_a("DiffuseSampler", (Object)c);
        b.func_147984_b("BlurDir").func_148087_a(1.0f, 0.0f);
        b.func_147984_b("Radius").func_148083_a(7, 0, 0, 0);
        b.func_147984_b("OutSize").func_148087_a((float)yp.v.field_147621_c, (float)yp.v.field_147618_d);
        yp.ALLATORIxDEMO(b, v);
        b.func_147992_a("DiffuseSampler", (Object)v);
        b.func_147984_b("BlurDir").func_148087_a(0.0f, 1.0f);
        b.func_147984_b("Radius").func_148083_a(7, 0, 0, 0);
        b.func_147984_b("OutSize").func_148087_a((float)yp.q.field_147621_c, (float)yp.q.field_147618_d);
        yp.ALLATORIxDEMO(b, q);
        o.func_147992_a("DiffuseSampler", (Object)a4);
        o.func_147992_a("HighLight", (Object)a5);
        o.func_147992_a("BlurTexture1", (Object)m);
        o.func_147992_a("BlurTexture2", (Object)c);
        o.func_147992_a("BlurTexture3", (Object)q);
        o.func_147984_b("BloomRadius").func_148090_a(1.0f);
        yp.ALLATORIxDEMO(o, a6);
        GlStateManager.func_179132_a((boolean)true);
        GlStateManager.func_179135_a((boolean)true, (boolean)true, (boolean)true, (boolean)true);
        GlStateManager.func_179126_j();
    }

    public static void ALLATORIxDEMO(ShaderManager a2, Framebuffer a3) {
        a3.func_147614_f();
        a3.func_147610_a(false);
        a2.func_147995_c();
        Tessellator a4 = Tessellator.func_178181_a();
        BufferBuilder a5 = a4.func_178180_c();
        a5.func_181668_a(7, DefaultVertexFormats.field_181705_e);
        a5.func_181662_b(-1.0, 1.0, 0.0).func_181675_d();
        a5.func_181662_b(-1.0, -1.0, 0.0).func_181675_d();
        a5.func_181662_b(1.0, -1.0, 0.0).func_181675_d();
        a5.func_181662_b(1.0, 1.0, 0.0).func_181675_d();
        a4.func_78381_a();
        a2.func_147993_b();
    }

    static {
        b = yp.ALLATORIxDEMO("dragoncore:separable_blur");
        o = yp.ALLATORIxDEMO("dragoncore:unreal_composite");
        y = yp.ALLATORIxDEMO("dragoncore:fast_blit");
    }
}

