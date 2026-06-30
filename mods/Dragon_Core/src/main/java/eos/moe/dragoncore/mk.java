/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.we;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class mk {
    public static Tessellator k = Tessellator.func_178181_a();
    public static BufferBuilder ALLATORIxDEMO = k.func_178180_c();

    public mk() {
        mk a2;
    }

    public static void ALLATORIxDEMO(float a2, float a3, float a4, float a5, float a6, float a7, float a8, float a9) {
        float a10 = 1.0f / a8;
        float a11 = 1.0f / a9;
        Tessellator a12 = Tessellator.func_178181_a();
        BufferBuilder a13 = a12.func_178180_c();
        a13.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        a13.func_181662_b((double)a2, (double)(a3 + a7), 0.0).func_187315_a((double)(a4 * a10), (double)((a5 + a7) * a11)).func_181675_d();
        a13.func_181662_b((double)(a2 + a6), (double)(a3 + a7), 0.0).func_187315_a((double)((a4 + a6) * a10), (double)((a5 + a7) * a11)).func_181675_d();
        a13.func_181662_b((double)(a2 + a6), (double)a3, 0.0).func_187315_a((double)((a4 + a6) * a10), (double)(a5 * a11)).func_181675_d();
        a13.func_181662_b((double)a2, (double)a3, 0.0).func_187315_a((double)(a4 * a10), (double)(a5 * a11)).func_181675_d();
        a12.func_78381_a();
    }

    public static void c(double a2, double a3, double a4, double a5, double a6, double a7, double a8, double a9) {
        mk.c(a2, a3, a4, a5, a6, a7, a8, a4, a5, a9);
    }

    public static void c(double a2, double a3, double a4, double a5, double a6, double a7, double a8, double a9, double a10, double a11) {
        double a12 = 1.0 / a11;
        mk.ALLATORIxDEMO(a2, a3, a4, a5, a6, a7, a8, a9, a10, a12);
    }

    public static void ALLATORIxDEMO(double a2, double a3, double a4, double a5, double a6, double a7, double a8, double a9, double a10, double a11) {
        ALLATORIxDEMO.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        ALLATORIxDEMO.func_181662_b(a2, a3, a6).func_187315_a(a7 * a11, a8 * a11).func_181675_d();
        ALLATORIxDEMO.func_181662_b(a2, a3 + a5, a6).func_187315_a(a7 * a11, (a8 + a10) * a11).func_181675_d();
        ALLATORIxDEMO.func_181662_b(a2 + a4, a3 + a5, a6).func_187315_a((a7 + a9) * a11, (a8 + a10) * a11).func_181675_d();
        ALLATORIxDEMO.func_181662_b(a2 + a4, a3, a6).func_187315_a((a7 + a9) * a11, a8 * a11).func_181675_d();
        k.func_78381_a();
    }

    public static void ALLATORIxDEMO(double a2, double a3, double a4, double a5, double a6, double a7, double a8, double a9) {
        mk.ALLATORIxDEMO(a2, a3, a4, a5, a6, a7, a8, a4, a5, a9);
    }

    public static void ALLATORIxDEMO(float a2, float a3, float a4, float a5, we a6, we a7, we a8, float a9) {
        float a10;
        float a11 = we.k(a6);
        float a12 = a11 + (a10 = we.k(a8));
        if (a4 <= a12) {
            mk.c(a2, a3, a11, we.d(a6), a5, we.x(a6), we.f(a6), we.c(a6), we.ALLATORIxDEMO(a6), a9);
            mk.c(a2 + a11, a3, a10, we.d(a8), a5, we.x(a8), we.f(a8), we.c(a8), we.ALLATORIxDEMO(a8), a9);
        } else {
            float a13 = a4 - a12;
            float a14 = we.k(a7);
            float a15 = a13 / a14;
            int a16 = (int)a15;
            float a17 = a15 - (float)((int)a15);
            mk.c(a2, a3, a11, we.d(a6), a5, we.x(a6), we.f(a6), we.c(a6), we.ALLATORIxDEMO(a6), a9);
            float a18 = a11;
            for (int a19 = 0; a19 < a16; ++a19) {
                mk.c(a2 + a18, a3, we.k(a7), we.d(a7), a5, we.x(a7), we.f(a7), we.c(a7), we.ALLATORIxDEMO(a7), a9);
                a18 += we.k(a7);
            }
            mk.c(a2 + a18, a3, a14 * a17, we.d(a7), a5, we.x(a7), we.f(a7), we.c(a7) * a17, we.ALLATORIxDEMO(a7), a9);
            mk.c(a2 + (a18 += a14 * a17), a3, a10, we.d(a8), a5, we.x(a8), we.f(a8), we.c(a8), we.ALLATORIxDEMO(a8), a9);
        }
    }

    public static void ALLATORIxDEMO(double a2, double a3, double a4, double a5, double a6) {
        ALLATORIxDEMO.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        ALLATORIxDEMO.func_181662_b(a2, a3, a6).func_187315_a(0.0, 0.0).func_181675_d();
        ALLATORIxDEMO.func_181662_b(a2, a3 + a5, a6).func_187315_a(0.0, 1.0).func_181675_d();
        ALLATORIxDEMO.func_181662_b(a2 + a4, a3 + a5, a6).func_187315_a(1.0, 1.0).func_181675_d();
        ALLATORIxDEMO.func_181662_b(a2 + a4, a3, a6).func_187315_a(1.0, 0.0).func_181675_d();
        k.func_78381_a();
    }

    public static void w(FontRenderer a2, String a3, float a4, float a5, int a6) {
        a2.func_175065_a(a3, a4, a5, a6, false);
    }

    public static void z(FontRenderer a2, String a3, float a4, float a5, int a6) {
        a2.func_175065_a(a3, a4, a5, a6, true);
    }

    public static void k(FontRenderer a2, String a3, float a4, float a5, int a6) {
        mk.w(a2, a3, a4 - (float)a2.func_78256_a(a3) / 2.0f, a5, a6);
    }

    public static void d(FontRenderer a2, String a3, float a4, float a5, int a6) {
        mk.z(a2, a3, a4 - (float)a2.func_78256_a(a3) / 2.0f, a5, a6);
    }

    public static void x(FontRenderer a2, String a3, float a4, float a5, int a6) {
        mk.w(a2, a3, a4, a5 - (float)a2.field_78288_b / 2.0f, a6);
    }

    public static void f(FontRenderer a2, String a3, float a4, float a5, int a6) {
        mk.z(a2, a3, a4, a5 - (float)a2.field_78288_b / 2.0f, a6);
    }

    public static void c(FontRenderer a2, String a3, float a4, float a5, int a6) {
        mk.w(a2, a3, a4 - (float)a2.func_78256_a(a3) / 2.0f, a5 - (float)a2.field_78288_b / 2.0f, a6);
    }

    public static void ALLATORIxDEMO(FontRenderer a2, String a3, float a4, float a5, int a6) {
        mk.z(a2, a3, a4 - (float)a2.func_78256_a(a3) / 2.0f, a5 - (float)a2.field_78288_b / 2.0f, a6);
    }
}

