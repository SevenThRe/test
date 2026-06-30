/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.Particle
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ww;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class fla
extends Particle {
    private static final int[] x = new int[]{0, 16, 32, 49, 66, 83, 100, 116, 133, 149, 165};
    private final int v;
    private final int m;
    private int c;
    private float q;
    private float b;
    private final String o;
    public float y = 1.0f;
    private float k;
    private float ALLATORIxDEMO = 1.0f;

    public fla(int a2, int a3, World a4, double a5, double a6, double a7, float a8, float a9, float a10) {
        super(a4, a5, a6, a7, 0.0, 0.0, 0.0);
        double a11;
        fla a12;
        a12.particleRed = a8;
        a12.particleGreen = a9;
        a12.particleBlue = a10;
        a12.m = 24 * a2;
        a12.v = 24;
        a12.world = a4;
        a12.canCollide = false;
        a12.particleGravity = 0.0f;
        a12.particleScale = 0.625f;
        a12.b = (float)(Math.random() * (double)0.1f - (double)0.05f + 1.0);
        a12.q = 0.0f;
        a12.o = String.valueOf(a3);
        for (int a13 = 0; a13 < a12.o.length(); ++a13) {
            a12.c += a12.ALLATORIxDEMO(a12.o.charAt(a13));
        }
        double a14 = 1.0;
        Entity a15 = Minecraft.getMinecraft().getRenderManager().renderViewEntity;
        if (a15 != null && (a11 = a15.getDistance(a5, a6, a7)) < 7.0) {
            a14 = Math.max(a11 / 7.0, 0.15);
        }
        a12.b = (float)((double)a12.b * a14);
        a11 = (Math.random() - 0.5) * 1.5 * a14;
        double a16 = (Math.random() - 0.5) * 1.5 * a14;
        double a17 = (Math.random() - 0.5) * 1.5 * a14;
        a12.motionX = (double)(a11 > 0.0 ? 1 : -1) * Math.random() * 0.3 * a14;
        a12.motionY = (double)(a16 > 0.0 ? 1 : -1) * Math.random() * 0.3 * a14;
        a12.motionZ = (double)(a17 > 0.0 ? 1 : -1) * Math.random() * 0.3 * a14;
        a12.posX = a5 + a11;
        a12.posY = a6 + a16;
        a12.posZ = a7 + a17;
        a12.setPosition(a5, a6, a7);
        a12.prevPosX = a5;
        a12.prevPosY = a6;
        a12.prevPosZ = a7;
        a12.particleAlpha = 0.6f;
        a12.onUpdate();
    }

    public void onUpdate() {
        fla a2;
        a2.prevPosX = a2.posX;
        a2.prevPosY = a2.posY;
        a2.prevPosZ = a2.posZ;
        a2.ALLATORIxDEMO = a2.particleAlpha;
        a2.q = a2.particleScale;
        a2.k += a2.y;
        if (a2.k >= 20.0f) {
            a2.setExpired();
        }
        if (a2.world == null) {
            return;
        }
        a2.particleAlpha = a2.k > 10.0f ? Math.max((25.0f - a2.k) / 15.0f, 0.0f) : (a2.k > 3.0f ? 1.0f : a2.k / 3.0f * 0.7f + 0.3f);
        if (a2.k <= 4.0f) {
            float a3 = a2.k / 4.0f;
            a2.particleScale = (2.5f - a3 * 1.4f) * 0.25f;
        } else {
            a2.particleScale = 0.275f;
        }
        if (a2.k < 3.0f) {
            a2.move(a2.motionX, a2.motionY, a2.motionZ);
        }
    }

    public static float ALLATORIxDEMO(double a2) {
        double a3 = 2.0943951023931953;
        if (a2 == 0.0) {
            return 0.0f;
        }
        if (a2 == 1.0) {
            return 1.0f;
        }
        return (float)(Math.pow(2.0, -10.0 * a2) * Math.sin((a2 * 10.0 - 0.75) * a3) + 1.0);
    }

    private /* synthetic */ int ALLATORIxDEMO(char a2) {
        if (a2 >= '0' && a2 <= '9') {
            return x[a2 - 47] - x[a2 - 48];
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
        return 1;
    }

    public void renderParticle(BufferBuilder a2, Entity a3, float a4, float a5, float a6, float a7, float a8, float a9) {
        fla a10;
        if (a10.world == null || a10.particleAlpha == 0.0f) {
            return;
        }
        float a11 = (float)(a10.prevPosX + (a10.posX - a10.prevPosX) * (double)a4 - Particle.interpPosX);
        float a12 = (float)(a10.prevPosY + (a10.posY - a10.prevPosY) * (double)a4 - Particle.interpPosY);
        float a13 = (float)(a10.prevPosZ + (a10.posZ - a10.prevPosZ) * (double)a4 - Particle.interpPosZ);
        float a14 = (a10.q + (a10.particleScale - a10.q) * a4) * a10.b;
        if (Minecraft.getMinecraft().gameSettings.thirdPersonView == 0) {
            a14 *= 0.7f;
        }
        float a15 = a10.ALLATORIxDEMO + (a10.particleAlpha - a10.ALLATORIxDEMO) * a4;
        int a16 = a10.getBrightnessForRender(a4);
        int a17 = a16 >> 16 & 0xFFFF;
        int a18 = a16 & 0xFFFF;
        float a19 = -((float)a10.c / (float)a10.v * a14 * 2.5f + (float)(a10.o.length() - 1) * a14 * -0.4f) / 2.0f;
        boolean a20 = GL11.glIsEnabled((int)2896);
        GL11.glEnable((int)2896);
        ww.ALLATORIxDEMO("damagepic11.png");
        GlStateManager.enableBlend();
        GlStateManager.blendFunc((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        for (int a21 = 0; a21 < a10.o.length(); ++a21) {
            float a22 = 0.0f;
            float a23 = a14;
            float a24 = a14;
            char a25 = a10.o.charAt(a21);
            int a26 = a10.ALLATORIxDEMO(a25);
            if (a26 < a10.v) {
                a23 = (float)((double)a23 * ((double)a26 / (double)a10.v));
            } else {
                a24 = (float)((double)a24 * ((double)a10.v / (double)a26));
            }
            float a27 = (float)x[a25 - 48] / 256.0f;
            float a28 = a27 + (float)a26 / 256.0f;
            float a29 = (float)a10.m / 256.0f;
            float a30 = a29 + (float)a10.v / 256.0f;
            Vec3d[] a31 = new Vec3d[]{new Vec3d((double)(-a5 * a23 - a8 * a24), (double)(-a6 * a24), (double)(-a7 * a23 - a9 * a24)), new Vec3d((double)(-a5 * a23 + a8 * a24), (double)(a6 * a24), (double)(-a7 * a23 + a9 * a24)), new Vec3d((double)(a5 * a23 + a8 * a24), (double)(a6 * a24), (double)(a7 * a23 + a9 * a24)), new Vec3d((double)(a5 * a23 - a8 * a24), (double)(-a6 * a24), (double)(a7 * a23 - a9 * a24))};
            Vec3d a32 = new Vec3d((double)(-a5 * (a19 + a23) + a8 * a22), (double)(a6 * a22), (double)(-a7 * (a19 + a23) + a9 * a22));
            a2.pos((double)a11 + a31[0].x + a32.x, (double)a12 + a31[0].y + a32.y, (double)a13 + a31[0].z + a32.z).tex((double)a28, (double)a30).color(a10.particleRed, a10.particleGreen, a10.particleBlue, a15).lightmap(a17, a18).endVertex();
            a2.pos((double)a11 + a31[1].x + a32.x, (double)a12 + a31[1].y + a32.y, (double)a13 + a31[1].z + a32.z).tex((double)a28, (double)a29).color(a10.particleRed, a10.particleGreen, a10.particleBlue, a15).lightmap(a17, a18).endVertex();
            a2.pos((double)a11 + a31[2].x + a32.x, (double)a12 + a31[2].y + a32.y, (double)a13 + a31[2].z + a32.z).tex((double)a27, (double)a29).color(a10.particleRed, a10.particleGreen, a10.particleBlue, a15).lightmap(a17, a18).endVertex();
            a2.pos((double)a11 + a31[3].x + a32.x, (double)a12 + a31[3].y + a32.y, (double)a13 + a31[3].z + a32.z).tex((double)a27, (double)a30).color(a10.particleRed, a10.particleGreen, a10.particleBlue, a15).lightmap(a17, a18).endVertex();
            a19 += 2.5f * a23 + a14 * -0.4f;
        }
        if (!a20) {
            GL11.glDisable((int)2896);
        }
    }

    public void setParticleTextureIndex(int a2) {
        fla a3;
        if (a3.getFXLayer() > 3) {
            throw new RuntimeException("Invalid call to Particle.setMiscTex");
        }
        a3.particleTextureIndexX = a2 % 16;
        a3.particleTextureIndexY = a2 / 16;
    }
}

