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
        a6.world = a2;
        a6.canCollide = false;
        a6.motionX = 0.0;
        a6.motionY = 0.0;
        a6.motionZ = 0.0;
        a6.particleGravity = 0.0f;
        a6.particleScale = 1.0f;
        a6.particleMaxAge = 100;
        a6.onUpdate();
    }

    public void onUpdate() {
        fja a2;
        if (a2.particleAge++ >= a2.particleMaxAge) {
            a2.setExpired();
        }
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
        fja a10;
        int a11 = a10.particleAge * 3 / a10.particleMaxAge + 1;
        ww.ALLATORIxDEMO("cut" + a11 + ".png");
        GlStateManager.blendFunc((int)770, (int)771);
        GlStateManager.enableBlend();
        GlStateManager.disableBlend();
        GlStateManager.depthMask((boolean)false);
        float a12 = 0.0f;
        float a13 = 1.0f;
        float a14 = 0.0f;
        float a15 = 1.0f;
        float a16 = 1.0f;
        float a17 = (float)(a10.prevPosX + (a10.posX - a10.prevPosX) * (double)a4 - interpPosX);
        float a18 = (float)(a10.prevPosY + (a10.posY - a10.prevPosY) * (double)a4 - interpPosY);
        float a19 = (float)(a10.prevPosZ + (a10.posZ - a10.prevPosZ) * (double)a4 - interpPosZ);
        int a20 = a10.getBrightnessForRender(a4);
        int a21 = a20 >> 16 & 0xFFFF;
        int a22 = a20 & 0xFFFF;
        Vec3d[] a23 = new Vec3d[]{new Vec3d((double)(-a5 * a16 - a8 * a16), (double)(-a6 * a16), (double)(-a7 * a16 - a9 * a16)), new Vec3d((double)(-a5 * a16 + a8 * a16), (double)(a6 * a16), (double)(-a7 * a16 + a9 * a16)), new Vec3d((double)(a5 * a16 + a8 * a16), (double)(a6 * a16), (double)(a7 * a16 + a9 * a16)), new Vec3d((double)(a5 * a16 - a8 * a16), (double)(-a6 * a16), (double)(a7 * a16 - a9 * a16))};
        a2.pos((double)a17 + a23[0].x, (double)a18 + a23[0].y, (double)a19 + a23[0].z).tex((double)a13, (double)a15).color(a10.particleRed, a10.particleGreen, a10.particleBlue, a10.particleAlpha).lightmap(a21, a22).endVertex();
        a2.pos((double)a17 + a23[1].x, (double)a18 + a23[1].y, (double)a19 + a23[1].z).tex((double)a13, (double)a14).color(a10.particleRed, a10.particleGreen, a10.particleBlue, a10.particleAlpha).lightmap(a21, a22).endVertex();
        a2.pos((double)a17 + a23[2].x, (double)a18 + a23[2].y, (double)a19 + a23[2].z).tex((double)a12, (double)a14).color(a10.particleRed, a10.particleGreen, a10.particleBlue, a10.particleAlpha).lightmap(a21, a22).endVertex();
        a2.pos((double)a17 + a23[3].x, (double)a18 + a23[3].y, (double)a19 + a23[3].z).tex((double)a12, (double)a15).color(a10.particleRed, a10.particleGreen, a10.particleBlue, a10.particleAlpha).lightmap(a21, a22).endVertex();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
    }

    public void setParticleTextureIndex(int a2) {
        fja a3;
        if (a3.getFXLayer() > 3) {
            throw new RuntimeException("Invalid call to Particle.setMiscTex");
        }
        a3.particleTextureIndexX = a2 % 16;
        a3.particleTextureIndexY = a2 / 16;
    }
}

