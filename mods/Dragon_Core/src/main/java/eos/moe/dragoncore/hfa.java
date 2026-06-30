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
        a12.world = a4;
        a12.canCollide = false;
        a12.motionX = -Math.random() * 0.2 + 0.1;
        a12.motionY = 0.1f;
        a12.motionZ = -Math.random() * 0.2 + 0.1;
        a12.particleGravity = 0.1f;
        a12.particleScale = 0.0f;
        a12.k = 0.0f;
        a12.particleMaxAge = (int)(Math.random() * 30.0 + 30.0);
        a12.particleMaxAge *= (int)a11;
        a12.ALLATORIxDEMO = String.valueOf(a3);
        for (int a13 = 0; a13 < a12.ALLATORIxDEMO.length(); ++a13) {
            a12.o += a12.ALLATORIxDEMO(a12.ALLATORIxDEMO.charAt(a13));
        }
        a12.onUpdate();
    }

    public void onUpdate() {
        hfa a2;
        a2.prevPosX = a2.posX;
        a2.prevPosY = a2.posY;
        a2.prevPosZ = a2.posZ;
        if (a2.particleAge++ >= a2.particleMaxAge) {
            a2.setExpired();
        }
        if (a2.world == null) {
            return;
        }
        a2.k = a2.particleScale;
        if (a2.particleAge < a2.particleMaxAge / 3) {
            a2.particleScale = (float)a2.particleAge / ((float)a2.particleMaxAge / 3.0f) * 0.325f;
        }
        a2.motionX *= 0.93;
        a2.motionY -= 0.04 * (double)a2.particleGravity;
        a2.motionZ *= 0.93;
        a2.move(a2.motionX, a2.motionY, a2.motionZ);
    }

    private /* synthetic */ int ALLATORIxDEMO(char a2) {
        if (a2 >= '0' && a2 <= '9') {
            return c[a2 - 47] - c[a2 - 48];
        }
        return 0;
    }

    public int getBrightnessForRender(float a2) {
        return 0xF00000;
    }

    public boolean shouldDisableDepth() {
        return true;
    }

    public int getFXLayer() {
        return 2;
    }

    public void renderParticle(BufferBuilder a2, Entity a3, float a4, float a5, float a6, float a7, float a8, float a9) {
        hfa a10;
        if (a10.world == null) {
            return;
        }
        float a11 = (float)(a10.prevPosX + (a10.posX - a10.prevPosX) * (double)a4 - Particle.interpPosX);
        float a12 = (float)(a10.prevPosY + (a10.posY - a10.prevPosY) * (double)a4 - Particle.interpPosY);
        float a13 = (float)(a10.prevPosZ + (a10.posZ - a10.prevPosZ) * (double)a4 - Particle.interpPosZ);
        float a14 = a10.particleScale + (a10.particleScale - a10.k) * a4;
        int a15 = a10.getBrightnessForRender(a4);
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
            a2.pos((double)a11 + a30[0].x + a31.x, (double)a12 + a30[0].y + a31.y, (double)a13 + a30[0].z + a31.z).tex((double)a27, (double)a29).color(a10.particleRed, a10.particleGreen, a10.particleBlue, a10.particleAlpha).lightmap(a16, a17).endVertex();
            a2.pos((double)a11 + a30[1].x + a31.x, (double)a12 + a30[1].y + a31.y, (double)a13 + a30[1].z + a31.z).tex((double)a27, (double)a28).color(a10.particleRed, a10.particleGreen, a10.particleBlue, a10.particleAlpha).lightmap(a16, a17).endVertex();
            a2.pos((double)a11 + a30[2].x + a31.x, (double)a12 + a30[2].y + a31.y, (double)a13 + a30[2].z + a31.z).tex((double)a26, (double)a28).color(a10.particleRed, a10.particleGreen, a10.particleBlue, a10.particleAlpha).lightmap(a16, a17).endVertex();
            a2.pos((double)a11 + a30[3].x + a31.x, (double)a12 + a30[3].y + a31.y, (double)a13 + a30[3].z + a31.z).tex((double)a26, (double)a29).color(a10.particleRed, a10.particleGreen, a10.particleBlue, a10.particleAlpha).lightmap(a16, a17).endVertex();
            a18 += 2.0f * a22 + a14 * -0.4f;
        }
        if (!a19) {
            GL11.glDisable((int)2896);
        }
    }

    public void setParticleTextureIndex(int a2) {
        hfa a3;
        if (a3.getFXLayer() > 3) {
            throw new RuntimeException("Invalid call to Particle.setMiscTex");
        }
        a3.particleTextureIndexX = a2 % 16;
        a3.particleTextureIndexY = a2 / 16;
    }
}

