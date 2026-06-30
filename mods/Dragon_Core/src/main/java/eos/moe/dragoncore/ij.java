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
            GlStateManager.func_179135_a((boolean)false, (boolean)false, (boolean)false, (boolean)false);
            ij.ALLATORIxDEMO(a2, a3, a4);
            GlStateManager.func_179135_a((boolean)true, (boolean)true, (boolean)true, (boolean)true);
            GlStateManager.func_179132_a((boolean)false);
            GlStateManager.func_179143_c((int)516);
        }
        catch (Throwable a5) {
            a5.printStackTrace();
        }
    }

    public static void c() {
        try {
            GlStateManager.func_179097_i();
        }
        catch (Throwable a2) {
            a2.printStackTrace();
        }
    }

    public static void ALLATORIxDEMO() {
        try {
            GlStateManager.func_179132_a((boolean)true);
            GL11.glClear((int)256);
            GlStateManager.func_179126_j();
            GlStateManager.func_179143_c((int)515);
            GlStateManager.func_179141_d();
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GlStateManager.func_179082_a((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
        catch (Throwable a2) {
            a2.printStackTrace();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void ALLATORIxDEMO(String a2, double a3, double a4) {
        GlStateManager.func_179094_E();
        try {
            GlStateManager.func_179147_l();
            GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
            GlStateManager.func_179098_w();
            ww.ALLATORIxDEMO(a2);
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glTexParameteri((int)3553, (int)10241, (int)9729);
            GL11.glTexParameteri((int)3553, (int)10240, (int)9729);
            GL11.glTexParameteri((int)3553, (int)10242, (int)33071);
            GL11.glTexParameteri((int)3553, (int)10243, (int)33071);
            Tessellator a5 = Tessellator.func_178181_a();
            BufferBuilder a6 = a5.func_178180_c();
            a6.func_181668_a(7, DefaultVertexFormats.field_181707_g);
            a6.func_181662_b(0.0, a4, 1000.0).func_187315_a(0.0, 1.0).func_181675_d();
            a6.func_181662_b(a3, a4, 1000.0).func_187315_a(1.0, 1.0).func_181675_d();
            a6.func_181662_b(a3, 0.0, 1000.0).func_187315_a(1.0, 0.0).func_181675_d();
            a6.func_181662_b(0.0, 0.0, 1000.0).func_187315_a(0.0, 0.0).func_181675_d();
            a5.func_78381_a();
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
        finally {
            GlStateManager.func_179121_F();
        }
    }
}

