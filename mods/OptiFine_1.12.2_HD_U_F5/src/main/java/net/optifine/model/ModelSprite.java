/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  brs
 *  buk
 *  bus
 *  bve
 *  cdy
 *  org.lwjgl.opengl.GL11
 *  rk
 */
package net.optifine.model;

import org.lwjgl.opengl.GL11;

public class ModelSprite {
    private brs modelRenderer = null;
    private int textureOffsetX = 0;
    private int textureOffsetY = 0;
    private float posX = 0.0f;
    private float posY = 0.0f;
    private float posZ = 0.0f;
    private int sizeX = 0;
    private int sizeY = 0;
    private int sizeZ = 0;
    private float sizeAdd = 0.0f;
    private float minU = 0.0f;
    private float minV = 0.0f;
    private float maxU = 0.0f;
    private float maxV = 0.0f;

    public ModelSprite(brs modelRenderer, int textureOffsetX, int textureOffsetY, float posX, float posY, float posZ, int sizeX, int sizeY, int sizeZ, float sizeAdd) {
        this.modelRenderer = modelRenderer;
        this.textureOffsetX = textureOffsetX;
        this.textureOffsetY = textureOffsetY;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;
        this.sizeAdd = sizeAdd;
        this.minU = (float)textureOffsetX / modelRenderer.a;
        this.minV = (float)textureOffsetY / modelRenderer.b;
        this.maxU = (float)(textureOffsetX + sizeX) / modelRenderer.a;
        this.maxV = (float)(textureOffsetY + sizeY) / modelRenderer.b;
    }

    public void render(bve tessellator, float scale) {
        bus.c((float)(this.posX * scale), (float)(this.posY * scale), (float)(this.posZ * scale));
        float rMinU = this.minU;
        float rMaxU = this.maxU;
        float rMinV = this.minV;
        float rMaxV = this.maxV;
        if (this.modelRenderer.i) {
            rMinU = this.maxU;
            rMaxU = this.minU;
        }
        if (this.modelRenderer.mirrorV) {
            rMinV = this.maxV;
            rMaxV = this.minV;
        }
        ModelSprite.renderItemIn2D(tessellator, rMinU, rMinV, rMaxU, rMaxV, this.sizeX, this.sizeY, scale * (float)this.sizeZ, this.modelRenderer.a, this.modelRenderer.b);
        bus.c((float)(-this.posX * scale), (float)(-this.posY * scale), (float)(-this.posZ * scale));
    }

    public static void renderItemIn2D(bve tess, float minU, float minV, float maxU, float maxV, int sizeX, int sizeY, float width, float texWidth, float texHeight) {
        float var13;
        float var12;
        float var11;
        int var10;
        if (width < 6.25E-4f) {
            width = 6.25E-4f;
        }
        float dU = maxU - minU;
        float dV = maxV - minV;
        double dimX = rk.e((float)dU) * (texWidth / 16.0f);
        double dimY = rk.e((float)dV) * (texHeight / 16.0f);
        buk tessellator = tess.c();
        GL11.glNormal3f((float)0.0f, (float)0.0f, (float)-1.0f);
        tessellator.a(7, cdy.g);
        tessellator.b(0.0, dimY, 0.0).a((double)minU, (double)maxV).d();
        tessellator.b(dimX, dimY, 0.0).a((double)maxU, (double)maxV).d();
        tessellator.b(dimX, 0.0, 0.0).a((double)maxU, (double)minV).d();
        tessellator.b(0.0, 0.0, 0.0).a((double)minU, (double)minV).d();
        tess.b();
        GL11.glNormal3f((float)0.0f, (float)0.0f, (float)1.0f);
        tessellator.a(7, cdy.g);
        tessellator.b(0.0, 0.0, (double)width).a((double)minU, (double)minV).d();
        tessellator.b(dimX, 0.0, (double)width).a((double)maxU, (double)minV).d();
        tessellator.b(dimX, dimY, (double)width).a((double)maxU, (double)maxV).d();
        tessellator.b(0.0, dimY, (double)width).a((double)minU, (double)maxV).d();
        tess.b();
        float var8 = 0.5f * dU / (float)sizeX;
        float var9 = 0.5f * dV / (float)sizeY;
        GL11.glNormal3f((float)-1.0f, (float)0.0f, (float)0.0f);
        tessellator.a(7, cdy.g);
        for (var10 = 0; var10 < sizeX; ++var10) {
            var11 = (float)var10 / (float)sizeX;
            var12 = minU + dU * var11 + var8;
            tessellator.b((double)var11 * dimX, dimY, (double)width).a((double)var12, (double)maxV).d();
            tessellator.b((double)var11 * dimX, dimY, 0.0).a((double)var12, (double)maxV).d();
            tessellator.b((double)var11 * dimX, 0.0, 0.0).a((double)var12, (double)minV).d();
            tessellator.b((double)var11 * dimX, 0.0, (double)width).a((double)var12, (double)minV).d();
        }
        tess.b();
        GL11.glNormal3f((float)1.0f, (float)0.0f, (float)0.0f);
        tessellator.a(7, cdy.g);
        for (var10 = 0; var10 < sizeX; ++var10) {
            var11 = (float)var10 / (float)sizeX;
            var12 = minU + dU * var11 + var8;
            var13 = var11 + 1.0f / (float)sizeX;
            tessellator.b((double)var13 * dimX, 0.0, (double)width).a((double)var12, (double)minV).d();
            tessellator.b((double)var13 * dimX, 0.0, 0.0).a((double)var12, (double)minV).d();
            tessellator.b((double)var13 * dimX, dimY, 0.0).a((double)var12, (double)maxV).d();
            tessellator.b((double)var13 * dimX, dimY, (double)width).a((double)var12, (double)maxV).d();
        }
        tess.b();
        GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
        tessellator.a(7, cdy.g);
        for (var10 = 0; var10 < sizeY; ++var10) {
            var11 = (float)var10 / (float)sizeY;
            var12 = minV + dV * var11 + var9;
            var13 = var11 + 1.0f / (float)sizeY;
            tessellator.b(0.0, (double)var13 * dimY, (double)width).a((double)minU, (double)var12).d();
            tessellator.b(dimX, (double)var13 * dimY, (double)width).a((double)maxU, (double)var12).d();
            tessellator.b(dimX, (double)var13 * dimY, 0.0).a((double)maxU, (double)var12).d();
            tessellator.b(0.0, (double)var13 * dimY, 0.0).a((double)minU, (double)var12).d();
        }
        tess.b();
        GL11.glNormal3f((float)0.0f, (float)-1.0f, (float)0.0f);
        tessellator.a(7, cdy.g);
        for (var10 = 0; var10 < sizeY; ++var10) {
            var11 = (float)var10 / (float)sizeY;
            var12 = minV + dV * var11 + var9;
            tessellator.b(dimX, (double)var11 * dimY, (double)width).a((double)maxU, (double)var12).d();
            tessellator.b(0.0, (double)var11 * dimY, (double)width).a((double)minU, (double)var12).d();
            tessellator.b(0.0, (double)var11 * dimY, 0.0).a((double)minU, (double)var12).d();
            tessellator.b(dimX, (double)var11 * dimY, 0.0).a((double)maxU, (double)var12).d();
        }
        tess.b();
    }
}

