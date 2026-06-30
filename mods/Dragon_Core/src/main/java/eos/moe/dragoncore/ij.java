/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ww;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class ij {
    public ij() {
        ij a2;
    }

    public static void c(String a2, double a3, double a4) {
        try {
            ij.ALLATORIxDEMO();
            GlStateManager.colorMask((boolean)false, (boolean)false, (boolean)false, (boolean)false);
            ij.ALLATORIxDEMO(a2, a3, a4);
            GlStateManager.colorMask((boolean)true, (boolean)true, (boolean)true, (boolean)true);
            GlStateManager.depthMask((boolean)false);
            GlStateManager.depthFunc((int)516);
        }
        catch (Throwable a5) {
            a5.printStackTrace();
        }
    }

    public static void c() {
        try {
            GlStateManager.disableDepth();
        }
        catch (Throwable a2) {
            a2.printStackTrace();
        }
    }

    public static void ALLATORIxDEMO() {
        try {
            GlStateManager.depthMask((boolean)true);
            GL11.glClear((int)256);
            GlStateManager.enableDepth();
            GlStateManager.depthFunc((int)515);
            GlStateManager.enableAlpha();
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GlStateManager.clearColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
        catch (Throwable a2) {
            a2.printStackTrace();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void ALLATORIxDEMO(String a2, double a3, double a4) {
        GlStateManager.pushMatrix();
        try {
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
            GlStateManager.enableTexture2D();
            ww.ALLATORIxDEMO(a2);
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glTexParameteri((int)3553, (int)10241, (int)9729);
            GL11.glTexParameteri((int)3553, (int)10240, (int)9729);
            GL11.glTexParameteri((int)3553, (int)10242, (int)33071);
            GL11.glTexParameteri((int)3553, (int)10243, (int)33071);
            Tessellator a5 = Tessellator.getInstance();
            BufferBuilder a6 = a5.getBuffer();
            a6.begin(7, DefaultVertexFormats.POSITION_TEX);
            a6.pos(0.0, a4, 1000.0).tex(0.0, 1.0).endVertex();
            a6.pos(a3, a4, 1000.0).tex(1.0, 1.0).endVertex();
            a6.pos(a3, 0.0, 1000.0).tex(1.0, 0.0).endVertex();
            a6.pos(0.0, 0.0, 1000.0).tex(0.0, 0.0).endVertex();
            a5.draw();
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
        finally {
            GlStateManager.popMatrix();
        }
    }
}

