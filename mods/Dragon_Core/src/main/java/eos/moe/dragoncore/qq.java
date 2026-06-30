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
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.client.shader.Framebuffer
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.yp;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.Framebuffer;

public class qq {
    public static boolean ALLATORIxDEMO = false;

    public qq() {
        qq a2;
    }

    public static void ALLATORIxDEMO() {
        if (ALLATORIxDEMO) {
            Framebuffer a2 = yp.c();
            Framebuffer a3 = yp.ALLATORIxDEMO();
            Framebuffer a4 = Minecraft.getMinecraft().getFramebuffer();
            yp.ALLATORIxDEMO(a4.framebufferWidth, a4.framebufferHeight, a4.framebufferTexture, a2.framebufferTexture, a3);
            a2.bindFramebuffer(false);
            GlStateManager.clearColor((float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
            GlStateManager.clear((int)16384);
            GlStateManager.depthMask((boolean)false);
            GlStateManager.disableDepth();
            GlStateManager.colorMask((boolean)true, (boolean)true, (boolean)true, (boolean)true);
            a4.bindFramebuffer(false);
            yp.y.addSamplerTexture("DiffuseSampler", (Object)a3.framebufferTexture);
            yp.y.useShader();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate((int)GlStateManager.SourceFactor.SRC_ALPHA.factor, (int)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA.factor, (int)GlStateManager.SourceFactor.ONE.factor, (int)GlStateManager.DestFactor.ZERO.factor);
            Tessellator a5 = Tessellator.getInstance();
            BufferBuilder a6 = a5.getBuffer();
            a6.begin(7, DefaultVertexFormats.POSITION);
            a6.pos(-1.0, 1.0, 500.0).endVertex();
            a6.pos(-1.0, -1.0, 500.0).endVertex();
            a6.pos(1.0, -1.0, 500.0).endVertex();
            a6.pos(1.0, 1.0, 500.0).endVertex();
            a5.draw();
            yp.y.endShader();
            GlStateManager.depthMask((boolean)true);
            GlStateManager.colorMask((boolean)true, (boolean)true, (boolean)true, (boolean)true);
            GlStateManager.enableDepth();
            ALLATORIxDEMO = false;
        }
    }
}

