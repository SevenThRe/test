/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.particle.Particle
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ww;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL11;

@Mod.EventBusSubscriber(modid="dragoncore")
public class hfa
extends Particle {
    private static final int[] c = new int[]{0, 21, 33, 54, 75, 96, 118, 139, 158, 180, 200};
    private final int q;
    private final int b;
    private int o;
    private float y;
    private float k;
    private final String ALLATORIxDEMO;

    public hfa(int a2, int a3, World a4, double a5, double a6, double a7, double a8, double a9, double a10, float a11) {
        super(a4, a5, a6, a7, 0.0, 0.0, 0.0);
        hfa a12;
        a12.b = a2 * 25;
        a12.q = 25;
        a12.field_187122_b = a4;
        a12.field_190017_n = false;
        a12.field_187129_i = -Math.random() * 0.2 + 0.1;
        a12.field_187130_j = 0.1f;
        a12.field_187131_k = -Math.random() * 0.2 + 0.1;
        a12.field_70545_g = 0.1f;
        a12.field_70544_f = 0.0f;
        a12.k = 0.0f;
        a12.field_70547_e = (int)(Math.random() * 30.0 + 30.0);
        a12.field_70547_e *= (int)a11;
        a12.ALLATORIxDEMO = String.valueOf(a3);
        for (int a13 = 0; a13 < a12.ALLATORIxDEMO.length(); ++a13) {
            a12.o += a12.ALLATORIxDEMO(a12.ALLATORIxDEMO.charAt(a13));
        }
        a12.func_189213_a();
    }

    public void func_189213_a() {
        hfa a2;
        a2.field_187123_c = a2.field_187126_f;
        a2.field_187124_d = a2.field_187127_g;
        a2.field_187125_e = a2.field_187128_h;
        if (a2.field_70546_d++ >= a2.field_70547_e) {
            a2.func_187112_i();
        }
        if (a2.field_187122_b == null) {
            return;
        }
        a2.k = a2.field_70544_f;
        if (a2.field_70546_d < a2.field_70547_e / 3) {
            a2.field_70544_f = (float)a2.field_70546_d / ((float)a2.field_70547_e / 3.0f) * 0.325f;
        }
        a2.field_187129_i *= 0.93;
        a2.field_187130_j -= 0.04 * (double)a2.field_70545_g;
        a2.field_187131_k *= 0.93;
        a2.func_187110_a(a2.field_187129_i, a2.field_187130_j, a2.field_187131_k);
    }

    private /* synthetic */ int ALLATORIxDEMO(char a2) {
        if (a2 >= '0' && a2 <= '9') {
            return c[a2 - 47] - c[a2 - 48];
        }
        return 0;
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
        hfa a10;
        if (a10.field_187122_b == null) {
            return;
        }
        float a11 = (float)(a10.field_187123_c + (a10.field_187126_f - a10.field_187123_c) * (double)a4 - Particle.field_70556_an);
        float a12 = (float)(a10.field_187124_d + (a10.field_187127_g - a10.field_187124_d) * (double)a4 - Particle.field_70554_ao);
        float a13 = (float)(a10.field_187125_e + (a10.field_187128_h - a10.field_187125_e) * (double)a4 - Particle.field_70555_ap);
        float a14 = a10.field_70544_f + (a10.field_70544_f - a10.k) * a4;
        int a15 = a10.func_189214_a(a4);
        int a16 = a15 >> 16 & 0xFFFF;
        int a17 = a15 & 0xFFFF;
        float a18 = -((float)a10.o / (float)a10.q * a14 * 2.0f + (float)(a10.ALLATORIxDEMO.length() - 1) * a14 * -0.4f) / 2.0f;
        boolean a19 = GL11.glIsEnabled((int)2896);
        GL11.glEnable((int)2896);
        ww.ALLATORIxDEMO("damagepic.png");
        for (int a20 = 0; a20 < a10.ALLATORIxDEMO.length(); ++a20) {
            float a21 = a20 % 2 == 0 ? a14 * 0.1f : -a14 * 0.1f;
            float a22 = a14;
            float a23 = a14;
            char a24 = a10.ALLATORIxDEMO.charAt(a20);
            int a25 = a10.ALLATORIxDEMO(a24);
            if (a25 < a10.q) {
                a22 = (float)((double)a22 * ((double)a25 / (double)a10.q));
            } else {
                a23 = (float)((double)a23 * ((double)a10.q / (double)a25));
            }
            float a26 = (float)c[a24 - 48] / 256.0f;
            float a27 = a26 + (float)a25 / 256.0f;
            float a28 = (float)a10.b / 256.0f;
            float a29 = a28 + (float)a10.q / 256.0f;
            Vec3d[] a30 = new Vec3d[]{new Vec3d((double)(-a5 * a22 - a8 * a23), (double)(-a6 * a23), (double)(-a7 * a22 - a9 * a23)), new Vec3d((double)(-a5 * a22 + a8 * a23), (double)(a6 * a23), (double)(-a7 * a22 + a9 * a23)), new Vec3d((double)(a5 * a22 + a8 * a23), (double)(a6 * a23), (double)(a7 * a22 + a9 * a23)), new Vec3d((double)(a5 * a22 - a8 * a23), (double)(-a6 * a23), (double)(a7 * a22 - a9 * a23))};
            Vec3d a31 = new Vec3d((double)(-a5 * (a18 + a22) + a8 * a21), (double)(a6 * a21), (double)(-a7 * (a18 + a22) + a9 * a21));
            a2.func_181662_b((double)a11 + a30[0].field_72450_a + a31.field_72450_a, (double)a12 + a30[0].field_72448_b + a31.field_72448_b, (double)a13 + a30[0].field_72449_c + a31.field_72449_c).func_187315_a((double)a27, (double)a29).func_181666_a(a10.field_70552_h, a10.field_70553_i, a10.field_70551_j, a10.field_82339_as).func_187314_a(a16, a17).func_181675_d();
            a2.func_181662_b((double)a11 + a30[1].field_72450_a + a31.field_72450_a, (double)a12 + a30[1].field_72448_b + a31.field_72448_b, (double)a13 + a30[1].field_72449_c + a31.field_72449_c).func_187315_a((double)a27, (double)a28).func_181666_a(a10.field_70552_h, a10.field_70553_i, a10.field_70551_j, a10.field_82339_as).func_187314_a(a16, a17).func_181675_d();
            a2.func_181662_b((double)a11 + a30[2].field_72450_a + a31.field_72450_a, (double)a12 + a30[2].field_72448_b + a31.field_72448_b, (double)a13 + a30[2].field_72449_c + a31.field_72449_c).func_187315_a((double)a26, (double)a28).func_181666_a(a10.field_70552_h, a10.field_70553_i, a10.field_70551_j, a10.field_82339_as).func_187314_a(a16, a17).func_181675_d();
            a2.func_181662_b((double)a11 + a30[3].field_72450_a + a31.field_72450_a, (double)a12 + a30[3].field_72448_b + a31.field_72448_b, (double)a13 + a30[3].field_72449_c + a31.field_72449_c).func_187315_a((double)a26, (double)a29).func_181666_a(a10.field_70552_h, a10.field_70553_i, a10.field_70551_j, a10.field_82339_as).func_187314_a(a16, a17).func_181675_d();
            a18 += 2.0f * a22 + a14 * -0.4f;
        }
        if (!a19) {
            GL11.glDisable((int)2896);
        }
    }

    public void func_70536_a(int a2) {
        hfa a3;
        if (a3.func_70537_b() > 3) {
            throw new RuntimeException("Invalid call to Particle.setMiscTex");
        }
        a3.field_94054_b = a2 % 16;
        a3.field_94055_c = a2 / 16;
    }
}

