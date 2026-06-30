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
    private static final Minecraft l = Minecraft.getMinecraft();
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
            return new ShaderManager(Minecraft.getMinecraft().getResourceManager(), a2);
        }
        catch (IOException a3) {
            throw new RuntimeException(a3);
        }
    }

    public static Framebuffer c() {
        if (g == null) {
            g = yp.ALLATORIxDEMO(null, yp.l.getFramebuffer().framebufferWidth, yp.l.getFramebuffer().framebufferHeight, true);
            yp.ALLATORIxDEMO(g, yp.l.getFramebuffer().depthBuffer);
        }
        return g;
    }

    public static Framebuffer ALLATORIxDEMO() {
        int a2 = yp.l.getFramebuffer().framebufferWidth;
        int a3 = yp.l.getFramebuffer().framebufferHeight;
        if (k != a2 || ALLATORIxDEMO != a3) {
            t = yp.ALLATORIxDEMO(t, a2, a3, false);
            k = a2;
            ALLATORIxDEMO = a3;
        }
        return t;
    }

    public static void ALLATORIxDEMO(Framebuffer a2, int a3) {
        a2.bindFramebuffer(false);
        OpenGlHelper.glBindRenderbuffer((int)OpenGlHelper.GL_RENDERBUFFER, (int)a3);
        if (!a2.isStencilEnabled()) {
            OpenGlHelper.glRenderbufferStorage((int)OpenGlHelper.GL_RENDERBUFFER, (int)33190, (int)a2.framebufferTextureWidth, (int)a2.framebufferTextureHeight);
            OpenGlHelper.glFramebufferRenderbuffer((int)OpenGlHelper.GL_FRAMEBUFFER, (int)OpenGlHelper.GL_DEPTH_ATTACHMENT, (int)OpenGlHelper.GL_RENDERBUFFER, (int)a3);
        } else {
            OpenGlHelper.glRenderbufferStorage((int)OpenGlHelper.GL_RENDERBUFFER, (int)35056, (int)a2.framebufferTextureWidth, (int)a2.framebufferTextureHeight);
            OpenGlHelper.glFramebufferRenderbuffer((int)OpenGlHelper.GL_FRAMEBUFFER, (int)36096, (int)OpenGlHelper.GL_RENDERBUFFER, (int)a3);
            OpenGlHelper.glFramebufferRenderbuffer((int)OpenGlHelper.GL_FRAMEBUFFER, (int)36128, (int)OpenGlHelper.GL_RENDERBUFFER, (int)a3);
        }
    }

    public static void ALLATORIxDEMO(int a2, int a3) {
        if (z == a2 && s == a3) {
            return;
        }
        g = yp.ALLATORIxDEMO(null, a2, a3, true);
        yp.ALLATORIxDEMO(g, yp.l.getFramebuffer().depthBuffer);
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
            target.setFramebufferColor(0.0f, 0.0f, 0.0f, 0.0f);
        }
        target.createBindFramebuffer(width, height);
        target.setFramebufferFilter(9729);
        return target;
    }

    public static void ALLATORIxDEMO(int a2, int a3, int a4, int a5, Framebuffer a6) {
        yp.ALLATORIxDEMO(a2, a3);
        GlStateManager.colorMask((boolean)true, (boolean)true, (boolean)true, (boolean)true);
        GlStateManager.disableDepth();
        GlStateManager.depthMask((boolean)false);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate((int)GlStateManager.SourceFactor.SRC_ALPHA.factor, (int)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA.factor, (int)GlStateManager.SourceFactor.ONE.factor, (int)GlStateManager.DestFactor.ZERO.factor);
        b.addSamplerTexture("DiffuseSampler", (Object)a5);
        b.getShaderUniformOrDefault("BlurDir").set(1.0f, 0.0f);
        b.getShaderUniformOrDefault("Radius").set(3, 0, 0, 0);
        b.getShaderUniformOrDefault("OutSize").set((float)yp.r.framebufferWidth, (float)yp.r.framebufferHeight);
        yp.ALLATORIxDEMO(b, r);
        b.addSamplerTexture("DiffuseSampler", (Object)r);
        b.getShaderUniformOrDefault("BlurDir").set(0.0f, 1.0f);
        b.getShaderUniformOrDefault("Radius").set(3, 0, 0, 0);
        b.getShaderUniformOrDefault("OutSize").set((float)yp.m.framebufferWidth, (float)yp.m.framebufferHeight);
        yp.ALLATORIxDEMO(b, m);
        b.addSamplerTexture("DiffuseSampler", (Object)m);
        b.getShaderUniformOrDefault("BlurDir").set(1.0f, 0.0f);
        b.getShaderUniformOrDefault("Radius").set(5, 0, 0, 0);
        b.getShaderUniformOrDefault("OutSize").set((float)yp.x.framebufferWidth, (float)yp.x.framebufferHeight);
        yp.ALLATORIxDEMO(b, x);
        b.addSamplerTexture("DiffuseSampler", (Object)x);
        b.getShaderUniformOrDefault("BlurDir").set(0.0f, 1.0f);
        b.getShaderUniformOrDefault("Radius").set(5, 0, 0, 0);
        b.getShaderUniformOrDefault("OutSize").set((float)yp.c.framebufferWidth, (float)yp.c.framebufferHeight);
        yp.ALLATORIxDEMO(b, c);
        b.addSamplerTexture("DiffuseSampler", (Object)c);
        b.getShaderUniformOrDefault("BlurDir").set(1.0f, 0.0f);
        b.getShaderUniformOrDefault("Radius").set(7, 0, 0, 0);
        b.getShaderUniformOrDefault("OutSize").set((float)yp.v.framebufferWidth, (float)yp.v.framebufferHeight);
        yp.ALLATORIxDEMO(b, v);
        b.addSamplerTexture("DiffuseSampler", (Object)v);
        b.getShaderUniformOrDefault("BlurDir").set(0.0f, 1.0f);
        b.getShaderUniformOrDefault("Radius").set(7, 0, 0, 0);
        b.getShaderUniformOrDefault("OutSize").set((float)yp.q.framebufferWidth, (float)yp.q.framebufferHeight);
        yp.ALLATORIxDEMO(b, q);
        o.addSamplerTexture("DiffuseSampler", (Object)a4);
        o.addSamplerTexture("HighLight", (Object)a5);
        o.addSamplerTexture("BlurTexture1", (Object)m);
        o.addSamplerTexture("BlurTexture2", (Object)c);
        o.addSamplerTexture("BlurTexture3", (Object)q);
        o.getShaderUniformOrDefault("BloomRadius").set(1.0f);
        yp.ALLATORIxDEMO(o, a6);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.colorMask((boolean)true, (boolean)true, (boolean)true, (boolean)true);
        GlStateManager.enableDepth();
    }

    public static void ALLATORIxDEMO(ShaderManager a2, Framebuffer a3) {
        a3.framebufferClear();
        a3.bindFramebuffer(false);
        a2.useShader();
        Tessellator a4 = Tessellator.getInstance();
        BufferBuilder a5 = a4.getBuffer();
        a5.begin(7, DefaultVertexFormats.POSITION);
        a5.pos(-1.0, 1.0, 0.0).endVertex();
        a5.pos(-1.0, -1.0, 0.0).endVertex();
        a5.pos(1.0, -1.0, 0.0).endVertex();
        a5.pos(1.0, 1.0, 0.0).endVertex();
        a4.draw();
        a2.endShader();
    }

    static {
        b = yp.ALLATORIxDEMO("dragoncore:separable_blur");
        o = yp.ALLATORIxDEMO("dragoncore:unreal_composite");
        y = yp.ALLATORIxDEMO("dragoncore:fast_blit");
    }
}

