/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.particle.Particle
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ww;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class fja
extends Particle {
    public fja(World a2, double a3, double a4, double a5) {
        super(a2, a3, a4, a5, 0.0, 0.0, 0.0);
        fja a6;
        a6.field_187122_b = a2;
        a6.field_190017_n = false;
        a6.field_187129_i = 0.0;
        a6.field_187130_j = 0.0;
        a6.field_187131_k = 0.0;
        a6.field_70545_g = 0.0f;
        a6.field_70544_f = 1.0f;
        a6.field_70547_e = 100;
        a6.func_189213_a();
    }

    public void func_189213_a() {
        fja a2;
        if (a2.field_70546_d++ >= a2.field_70547_e) {
            a2.func_187112_i();
        }
    }

    public int func_189214_a(float a2) {
        return 0xF00000;
    }

    public boolean func_187111_c() {
        return true;
    }

    public int func_70537_b() {
        return 2;
    }

    public void func_180434_a(BufferBuilder a2, Entity a3, float a4, float a5, float a6, float a7, float a8, float a9) {
        fja a10;
        int a11 = a10.field_70546_d * 3 / a10.field_70547_e + 1;
        ww.ALLATORIxDEMO("cut" + a11 + ".png");
        GlStateManager.func_179112_b((int)770, (int)771);
        GlStateManager.func_179147_l();
        GlStateManager.func_179084_k();
        GlStateManager.func_179132_a((boolean)false);
        float a12 = 0.0f;
        float a13 = 1.0f;
        float a14 = 0.0f;
        float a15 = 1.0f;
        float a16 = 1.0f;
        float a17 = (float)(a10.field_187123_c + (a10.field_187126_f - a10.field_187123_c) * (double)a4 - field_70556_an);
        float a18 = (float)(a10.field_187124_d + (a10.field_187127_g - a10.field_187124_d) * (double)a4 - field_70554_ao);
        float a19 = (float)(a10.field_187125_e + (a10.field_187128_h - a10.field_187125_e) * (double)a4 - field_70555_ap);
        int a20 = a10.func_189214_a(a4);
        int a21 = a20 >> 16 & 0xFFFF;
        int a22 = a20 & 0xFFFF;
        Vec3d[] a23 = new Vec3d[]{new Vec3d((double)(-a5 * a16 - a8 * a16), (double)(-a6 * a16), (double)(-a7 * a16 - a9 * a16)), new Vec3d((double)(-a5 * a16 + a8 * a16), (double)(a6 * a16), (double)(-a7 * a16 + a9 * a16)), new Vec3d((double)(a5 * a16 + a8 * a16), (double)(a6 * a16), (double)(a7 * a16 + a9 * a16)), new Vec3d((double)(a5 * a16 - a8 * a16), (double)(-a6 * a16), (double)(a7 * a16 - a9 * a16))};
        a2.func_181662_b((double)a17 + a23[0].field_72450_a, (double)a18 + a23[0].field_72448_b, (double)a19 + a23[0].field_72449_c).func_187315_a((double)a13, (double)a15).func_181666_a(a10.field_70552_h, a10.field_70553_i, a10.field_70551_j, a10.field_82339_as).func_187314_a(a21, a22).func_181675_d();
        a2.func_181662_b((double)a17 + a23[1].field_72450_a, (double)a18 + a23[1].field_72448_b, (double)a19 + a23[1].field_72449_c).func_187315_a((double)a13, (double)a14).func_181666_a(a10.field_70552_h, a10.field_70553_i, a10.field_70551_j, a10.field_82339_as).func_187314_a(a21, a22).func_181675_d();
        a2.func_181662_b((double)a17 + a23[2].field_72450_a, (double)a18 + a23[2].field_72448_b, (double)a19 + a23[2].field_72449_c).func_187315_a((double)a12, (double)a14).func_181666_a(a10.field_70552_h, a10.field_70553_i, a10.field_70551_j, a10.field_82339_as).func_187314_a(a21, a22).func_181675_d();
        a2.func_181662_b((double)a17 + a23[3].field_72450_a, (double)a18 + a23[3].field_72448_b, (double)a19 + a23[3].field_72449_c).func_187315_a((double)a12, (double)a15).func_181666_a(a10.field_70552_h, a10.field_70553_i, a10.field_70551_j, a10.field_82339_as).func_187314_a(a21, a22).func_181675_d();
        GlStateManager.func_179132_a((boolean)true);
        GlStateManager.func_179126_j();
    }

    public void func_70536_a(int a2) {
        fja a3;
        if (a3.func_70537_b() > 3) {
            throw new RuntimeException("Invalid call to Particle.setMiscTex");
        }
        a3.field_94054_b = a2 % 16;
        a3.field_94055_c = a2 / 16;
    }
}

