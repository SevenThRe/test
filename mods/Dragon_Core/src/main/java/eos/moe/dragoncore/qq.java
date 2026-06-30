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
            Framebuffer a4 = Minecraft.func_71410_x().func_147110_a();
            yp.ALLATORIxDEMO(a4.field_147621_c, a4.field_147618_d, a4.field_147617_g, a2.field_147617_g, a3);
            a2.func_147610_a(false);
            GlStateManager.func_179082_a((float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
            GlStateManager.func_179086_m((int)16384);
            GlStateManager.func_179132_a((boolean)false);
            GlStateManager.func_179097_i();
            GlStateManager.func_179135_a((boolean)true, (boolean)true, (boolean)true, (boolean)true);
            a4.func_147610_a(false);
            yp.y.func_147992_a("DiffuseSampler", (Object)a3.field_147617_g);
            yp.y.func_147995_c();
            GlStateManager.func_179147_l();
            GlStateManager.func_179120_a((int)GlStateManager.SourceFactor.SRC_ALPHA.field_187395_p, (int)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA.field_187345_o, (int)GlStateManager.SourceFactor.ONE.field_187395_p, (int)GlStateManager.DestFactor.ZERO.field_187345_o);
            Tessellator a5 = Tessellator.func_178181_a();
            BufferBuilder a6 = a5.func_178180_c();
            a6.func_181668_a(7, DefaultVertexFormats.field_181705_e);
            a6.func_181662_b(-1.0, 1.0, 500.0).func_181675_d();
            a6.func_181662_b(-1.0, -1.0, 500.0).func_181675_d();
            a6.func_181662_b(1.0, -1.0, 500.0).func_181675_d();
            a6.func_181662_b(1.0, 1.0, 500.0).func_181675_d();
            a5.func_78381_a();
            yp.y.func_147993_b();
            GlStateManager.func_179132_a((boolean)true);
            GlStateManager.func_179135_a((boolean)true, (boolean)true, (boolean)true, (boolean)true);
            GlStateManager.func_179126_j();
            ALLATORIxDEMO = false;
        }
    }
}

