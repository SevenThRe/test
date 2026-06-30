/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  org.lwjgl.opengl.GL11
 */
package goblinbob.mobends.core.util;

import goblinbob.mobends.core.util.IColorRead;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class Draw {
    public static void rectangle(int left, int top, int width, int height, int color) {
        float alpha = (float)(color >> 24 & 0xFF) / 255.0f;
        float red = (float)(color >> 16 & 0xFF) / 255.0f;
        float green = (float)(color >> 8 & 0xFF) / 255.0f;
        float blue = (float)(color & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder vertexbuffer = tessellator.func_178180_c();
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_187428_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.func_179131_c((float)red, (float)green, (float)blue, (float)alpha);
        vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
        vertexbuffer.func_181662_b((double)left, (double)top + (double)height, 0.0).func_181675_d();
        vertexbuffer.func_181662_b((double)left + (double)width, (double)top + (double)height, 0.0).func_181675_d();
        vertexbuffer.func_181662_b((double)left + (double)width, (double)top, 0.0).func_181675_d();
        vertexbuffer.func_181662_b((double)left, (double)top, 0.0).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }

    public static void rectangle(float left, float top, float width, float height) {
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder vertexbuffer = tessellator.func_178180_c();
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_187428_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
        vertexbuffer.func_181662_b((double)left, (double)top + (double)height, 0.0).func_181675_d();
        vertexbuffer.func_181662_b((double)left + (double)width, (double)top + (double)height, 0.0).func_181675_d();
        vertexbuffer.func_181662_b((double)left + (double)width, (double)top, 0.0).func_181675_d();
        vertexbuffer.func_181662_b((double)left, (double)top, 0.0).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }

    public static void rectangle(float x2, float y2, float w2, float h2, int color) {
        float a2 = (float)(color >> 24 & 0xFF) / 255.0f;
        float r2 = (float)(color >> 16 & 0xFF) / 255.0f;
        float g2 = (float)(color >> 8 & 0xFF) / 255.0f;
        float b2 = (float)(color & 0xFF) / 255.0f;
        GlStateManager.func_179131_c((float)r2, (float)g2, (float)b2, (float)a2);
        Draw.rectangle(x2, y2, w2, h2);
    }

    public static void rectangleHorizontalGradient(float x2, float y2, float w2, float h2, IColorRead color0, IColorRead color1) {
        GlStateManager.func_179147_l();
        GlStateManager.func_179112_b((int)770, (int)771);
        GL11.glHint((int)3152, (int)4354);
        GlStateManager.func_179103_j((int)7425);
        GlStateManager.func_179090_x();
        GlStateManager.func_187447_r((int)7);
        GlStateManager.func_179131_c((float)color0.getR(), (float)color0.getG(), (float)color0.getB(), (float)color0.getA());
        GlStateManager.func_187426_b((float)0.0f, (float)0.0f);
        GlStateManager.func_187435_e((float)(x2 + 0.0f), (float)(y2 + 0.0f), (float)0.0f);
        GlStateManager.func_187426_b((float)0.0f, (float)1.0f);
        GlStateManager.func_187435_e((float)(x2 + 0.0f), (float)(y2 + h2), (float)0.0f);
        GlStateManager.func_179131_c((float)color1.getR(), (float)color1.getG(), (float)color1.getB(), (float)color1.getA());
        GlStateManager.func_187426_b((float)1.0f, (float)1.0f);
        GlStateManager.func_187435_e((float)(x2 + w2), (float)(y2 + h2), (float)0.0f);
        GlStateManager.func_187426_b((float)1.0f, (float)0.0f);
        GlStateManager.func_187435_e((float)(x2 + w2), (float)(y2 + 0.0f), (float)0.0f);
        GlStateManager.func_187437_J();
        GlStateManager.func_179098_w();
    }

    public static void rectangleVerticalGradient(float x2, float y2, float w2, float h2, IColorRead color0, IColorRead color1) {
        GlStateManager.func_179147_l();
        GlStateManager.func_179112_b((int)770, (int)771);
        GL11.glHint((int)3152, (int)4354);
        GlStateManager.func_179103_j((int)7425);
        GlStateManager.func_179090_x();
        GlStateManager.func_187447_r((int)7);
        GlStateManager.func_179131_c((float)color0.getR(), (float)color0.getG(), (float)color0.getB(), (float)color0.getA());
        GlStateManager.func_187426_b((float)1.0f, (float)0.0f);
        GlStateManager.func_187435_e((float)(x2 + w2), (float)(y2 + 0.0f), (float)0.0f);
        GlStateManager.func_187426_b((float)0.0f, (float)0.0f);
        GlStateManager.func_187435_e((float)(x2 + 0.0f), (float)(y2 + 0.0f), (float)0.0f);
        GlStateManager.func_179131_c((float)color1.getR(), (float)color1.getG(), (float)color1.getB(), (float)color1.getA());
        GlStateManager.func_187426_b((float)0.0f, (float)1.0f);
        GlStateManager.func_187435_e((float)(x2 + 0.0f), (float)(y2 + h2), (float)0.0f);
        GlStateManager.func_187426_b((float)1.0f, (float)1.0f);
        GlStateManager.func_187435_e((float)(x2 + w2), (float)(y2 + h2), (float)0.0f);
        GlStateManager.func_187437_J();
        GlStateManager.func_179098_w();
    }

    public static void rectangleHorizontalGradient(float x2, float y2, float width, float height, int color0, int color1) {
        GL11.glHint((int)3152, (int)4354);
        GlStateManager.func_179103_j((int)7425);
        float a0 = (float)(color0 >> 24 & 0xFF) / 255.0f;
        float r0 = (float)(color0 >> 16 & 0xFF) / 255.0f;
        float g0 = (float)(color0 >> 8 & 0xFF) / 255.0f;
        float b0 = (float)(color0 & 0xFF) / 255.0f;
        float a1 = (float)(color1 >> 24 & 0xFF) / 255.0f;
        float r1 = (float)(color1 >> 16 & 0xFF) / 255.0f;
        float g1 = (float)(color1 >> 8 & 0xFF) / 255.0f;
        float b1 = (float)(color1 & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder vertexbuffer = tessellator.func_178180_c();
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_187428_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181706_f);
        vertexbuffer.func_181662_b((double)x2, (double)y2 + (double)height, 0.0).func_181666_a(r0, g0, b0, a0).func_181675_d();
        vertexbuffer.func_181662_b((double)x2 + (double)width, (double)y2 + (double)height, 0.0).func_181666_a(r1, g1, b1, a1).func_181675_d();
        vertexbuffer.func_181662_b((double)x2 + (double)width, (double)y2, 0.0).func_181666_a(r1, g1, b1, a1).func_181675_d();
        vertexbuffer.func_181662_b((double)x2, (double)y2, 0.0).func_181666_a(r0, g0, b0, a0).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }

    public static void rectangleVerticalGradient(float x2, float y2, float w2, float h2, int color0, int color1) {
        GlStateManager.func_179147_l();
        GL11.glHint((int)3152, (int)4354);
        GlStateManager.func_179103_j((int)7425);
        float a0 = (float)(color0 >> 24 & 0xFF) / 255.0f;
        float r0 = (float)(color0 >> 16 & 0xFF) / 255.0f;
        float g0 = (float)(color0 >> 8 & 0xFF) / 255.0f;
        float b0 = (float)(color0 & 0xFF) / 255.0f;
        float a1 = (float)(color1 >> 24 & 0xFF) / 255.0f;
        float r1 = (float)(color1 >> 16 & 0xFF) / 255.0f;
        float g1 = (float)(color1 >> 8 & 0xFF) / 255.0f;
        float b1 = (float)(color1 & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder vertexbuffer = tessellator.func_178180_c();
        GlStateManager.func_187428_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181706_f);
        vertexbuffer.func_181662_b((double)x2, (double)y2 + (double)h2, 0.0).func_181666_a(r1, g1, b1, a1).func_181675_d();
        vertexbuffer.func_181662_b((double)x2 + (double)w2, (double)y2 + (double)h2, 0.0).func_181666_a(r1, g1, b1, a1).func_181675_d();
        vertexbuffer.func_181662_b((double)x2 + (double)w2, (double)y2, 0.0).func_181666_a(r0, g0, b0, a0).func_181675_d();
        vertexbuffer.func_181662_b((double)x2, (double)y2, 0.0).func_181666_a(r0, g0, b0, a0).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }

    public static void circle(double x2, double y2, double radius, int vertices) {
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder vertexbuffer = tessellator.func_178180_c();
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_187428_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        vertexbuffer.func_181668_a(2, DefaultVertexFormats.field_181705_e);
        for (int i2 = 0; i2 < vertices; ++i2) {
            double angle = (double)i2 / (double)vertices * Math.PI * 2.0;
            vertexbuffer.func_181662_b(x2 + Math.cos(angle) * radius, y2 + Math.sin(angle) * radius, 0.0).func_181675_d();
        }
        tessellator.func_78381_a();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }

    public static void circle(double x2, double y2, double radius) {
        Draw.circle(x2, y2, radius, 100);
    }

    public static void cube(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, IColorRead color) {
        float r2 = color.getR();
        float g2 = color.getG();
        float b2 = color.getB();
        float a2 = color.getA();
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder vertexbuffer = tessellator.func_178180_c();
        vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181712_l);
        vertexbuffer.func_181662_b(minX, maxY, minZ).func_187315_a(0.0, 0.0).func_181666_a(r2, g2, b2, a2).func_181663_c(-1.0f, 0.0f, 0.0f).func_181675_d();
        vertexbuffer.func_181662_b(minX, minY, minZ).func_187315_a(0.0, 0.0).func_181666_a(r2, g2, b2, a2).func_181663_c(-1.0f, 0.0f, 0.0f).func_181675_d();
        vertexbuffer.func_181662_b(minX, minY, maxZ).func_187315_a(0.0, 0.0).func_181666_a(r2, g2, b2, a2).func_181663_c(-1.0f, 0.0f, 0.0f).func_181675_d();
        vertexbuffer.func_181662_b(minX, maxY, maxZ).func_187315_a(0.0, 0.0).func_181666_a(r2, g2, b2, a2).func_181663_c(-1.0f, 0.0f, 0.0f).func_181675_d();
        vertexbuffer.func_181662_b(maxX, maxY, maxZ).func_187315_a(0.0, 0.0).func_181666_a(r2, g2, b2, a2).func_181663_c(1.0f, 0.0f, 0.0f).func_181675_d();
        vertexbuffer.func_181662_b(maxX, minY, maxZ).func_187315_a(0.0, 0.0).func_181666_a(r2, g2, b2, a2).func_181663_c(1.0f, 0.0f, 0.0f).func_181675_d();
        vertexbuffer.func_181662_b(maxX, minY, minZ).func_187315_a(0.0, 0.0).func_181666_a(r2, g2, b2, a2).func_181663_c(1.0f, 0.0f, 0.0f).func_181675_d();
        vertexbuffer.func_181662_b(maxX, maxY, minZ).func_187315_a(0.0, 0.0).func_181666_a(r2, g2, b2, a2).func_181663_c(1.0f, 0.0f, 0.0f).func_181675_d();
        vertexbuffer.func_181662_b(maxX, maxY, minZ).func_187315_a(0.0, 0.0).func_181666_a(r2, g2, b2, a2).func_181663_c(0.0f, 0.0f, -1.0f).func_181675_d();
        vertexbuffer.func_181662_b(maxX, minY, minZ).func_187315_a(0.0, 0.0).func_181666_a(r2, g2, b2, a2).func_181663_c(0.0f, 0.0f, -1.0f).func_181675_d();
        vertexbuffer.func_181662_b(minX, minY, minZ).func_187315_a(0.0, 0.0).func_181666_a(r2, g2, b2, a2).func_181663_c(0.0f, 0.0f, -1.0f).func_181675_d();
        vertexbuffer.func_181662_b(minX, maxY, minZ).func_187315_a(0.0, 0.0).func_181666_a(r2, g2, b2, a2).func_181663_c(0.0f, 0.0f, -1.0f).func_181675_d();
        vertexbuffer.func_181662_b(minX, maxY, maxZ).func_187315_a(0.0, 0.0).func_181666_a(r2, g2, b2, a2).func_181663_c(0.0f, 0.0f, 1.0f).func_181675_d();
        vertexbuffer.func_181662_b(minX, minY, maxZ).func_187315_a(0.0, 0.0).func_181666_a(r2, g2, b2, a2).func_181663_c(0.0f, 0.0f, 1.0f).func_181675_d();
        vertexbuffer.func_181662_b(maxX, minY, maxZ).func_187315_a(0.0, 0.0).func_181666_a(r2, g2, b2, a2).func_181663_c(0.0f, 0.0f, 1.0f).func_181675_d();
        vertexbuffer.func_181662_b(maxX, maxY, maxZ).func_187315_a(0.0, 0.0).func_181666_a(r2, g2, b2, a2).func_181663_c(0.0f, 0.0f, 1.0f).func_181675_d();
        vertexbuffer.func_181662_b(minX, minY, maxZ).func_187315_a(0.0, 0.0).func_181666_a(r2, g2, b2, a2).func_181663_c(0.0f, -1.0f, 0.0f).func_181675_d();
        vertexbuffer.func_181662_b(minX, minY, minZ).func_187315_a(0.0, 0.0).func_181666_a(r2, g2, b2, a2).func_181663_c(0.0f, -1.0f, 0.0f).func_181675_d();
        vertexbuffer.func_181662_b(maxX, minY, minZ).func_187315_a(0.0, 0.0).func_181666_a(r2, g2, b2, a2).func_181663_c(0.0f, -1.0f, 0.0f).func_181675_d();
        vertexbuffer.func_181662_b(maxX, minY, maxZ).func_187315_a(0.0, 0.0).func_181666_a(r2, g2, b2, a2).func_181663_c(0.0f, -1.0f, 0.0f).func_181675_d();
        vertexbuffer.func_181662_b(maxX, maxY, maxZ).func_187315_a(0.0, 0.0).func_181666_a(r2, g2, b2, a2).func_181663_c(0.0f, 1.0f, 0.0f).func_181675_d();
        vertexbuffer.func_181662_b(maxX, maxY, minZ).func_187315_a(0.0, 0.0).func_181666_a(r2, g2, b2, a2).func_181663_c(0.0f, 1.0f, 0.0f).func_181675_d();
        vertexbuffer.func_181662_b(minX, maxY, minZ).func_187315_a(0.0, 0.0).func_181666_a(r2, g2, b2, a2).func_181663_c(0.0f, 1.0f, 0.0f).func_181675_d();
        vertexbuffer.func_181662_b(minX, maxY, maxZ).func_187315_a(0.0, 0.0).func_181666_a(r2, g2, b2, a2).func_181663_c(0.0f, 1.0f, 0.0f).func_181675_d();
        tessellator.func_78381_a();
    }

    public static void texturedModalRect(int x2, int y2, int textureX, int textureY, int width, int height) {
        float f2 = 0.00390625f;
        float f1 = 0.00390625f;
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder vertexbuffer = tessellator.func_178180_c();
        vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        vertexbuffer.func_181662_b((double)(x2 + 0), (double)(y2 + height), 0.0).func_187315_a((double)((float)(textureX + 0) * 0.00390625f), (double)((float)(textureY + height) * 0.00390625f)).func_181675_d();
        vertexbuffer.func_181662_b((double)(x2 + width), (double)(y2 + height), 0.0).func_187315_a((double)((float)(textureX + width) * 0.00390625f), (double)((float)(textureY + height) * 0.00390625f)).func_181675_d();
        vertexbuffer.func_181662_b((double)(x2 + width), (double)(y2 + 0), 0.0).func_187315_a((double)((float)(textureX + width) * 0.00390625f), (double)((float)(textureY + 0) * 0.00390625f)).func_181675_d();
        vertexbuffer.func_181662_b((double)(x2 + 0), (double)(y2 + 0), 0.0).func_187315_a((double)((float)(textureX + 0) * 0.00390625f), (double)((float)(textureY + 0) * 0.00390625f)).func_181675_d();
        tessellator.func_78381_a();
    }

    public static void texturedModalRect(int x2, int y2, int width, int height, int textureX, int textureY, int textureWidth, int textureHeight) {
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder vertexbuffer = tessellator.func_178180_c();
        vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        vertexbuffer.func_181662_b((double)(x2 + 0), (double)(y2 + height), 0.0).func_187315_a((double)((float)(textureX + 0) * 0.00390625f), (double)((float)(textureY + textureHeight) * 0.00390625f)).func_181675_d();
        vertexbuffer.func_181662_b((double)(x2 + width), (double)(y2 + height), 0.0).func_187315_a((double)((float)(textureX + textureWidth) * 0.00390625f), (double)((float)(textureY + textureHeight) * 0.00390625f)).func_181675_d();
        vertexbuffer.func_181662_b((double)(x2 + width), (double)(y2 + 0), 0.0).func_187315_a((double)((float)(textureX + textureWidth) * 0.00390625f), (double)((float)(textureY + 0) * 0.00390625f)).func_181675_d();
        vertexbuffer.func_181662_b((double)(x2 + 0), (double)(y2 + 0), 0.0).func_187315_a((double)((float)(textureX + 0) * 0.00390625f), (double)((float)(textureY + 0) * 0.00390625f)).func_181675_d();
        tessellator.func_78381_a();
    }

    public static void texturedRectangle(int x2, int y2, int width, int height, float textureX, float textureY, float textureWidth, float textureHeight) {
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder vertexbuffer = tessellator.func_178180_c();
        vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        vertexbuffer.func_181662_b((double)(x2 + 0), (double)(y2 + height), 0.0).func_187315_a((double)textureX, (double)(textureY + textureHeight)).func_181675_d();
        vertexbuffer.func_181662_b((double)(x2 + width), (double)(y2 + height), 0.0).func_187315_a((double)(textureX + textureWidth), (double)(textureY + textureHeight)).func_181675_d();
        vertexbuffer.func_181662_b((double)(x2 + width), (double)(y2 + 0), 0.0).func_187315_a((double)(textureX + textureWidth), (double)textureY).func_181675_d();
        vertexbuffer.func_181662_b((double)(x2 + 0), (double)(y2 + 0), 0.0).func_187315_a((double)textureX, (double)textureY).func_181675_d();
        tessellator.func_78381_a();
    }

    public static void thsPuzzle(int x2, int y2, int left, int middle, int right, int height, int textureX, int textureY) {
        Draw.texturedModalRect(x2, y2, textureX, textureY, left, height);
        Draw.texturedModalRect(x2 + left, y2, middle, height, textureX + left, textureY, 1, height);
        Draw.texturedModalRect(x2 + left + middle, y2, textureX + left + 1, textureY, right, height);
    }

    public static void borderBox(int x2, int y2, int width, int height, int border, int textureX, int textureY) {
        Draw.texturedModalRect(x2 - border, y2 - border, textureX, textureY, border, border);
        Draw.texturedModalRect(x2, y2 - border, width, border, textureX + border, textureY, 1, border);
        Draw.texturedModalRect(x2 + width, y2 - border, textureX + border + 1, textureY, border, border);
        Draw.texturedModalRect(x2 + width, y2, border, height, textureX + border + 1, textureY + border, border, 1);
        Draw.texturedModalRect(x2 + width, y2 + height, textureX + border + 1, textureY + border + 1, border, border);
        Draw.texturedModalRect(x2, y2 + height, width, border, textureX + border, textureY + border + 1, 1, border);
        Draw.texturedModalRect(x2 - border, y2 + height, textureX, textureY + border + 1, border, border);
        Draw.texturedModalRect(x2 - border, y2, border, height, textureX, textureY + border, border, 1);
        Draw.texturedModalRect(x2, y2, width, height, textureX + border, textureY + border, 1, 1);
    }

    public static void line(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, IColorRead color) {
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder vertexbuffer = tessellator.func_178180_c();
        vertexbuffer.func_181668_a(1, DefaultVertexFormats.field_181706_f);
        vertexbuffer.func_181662_b(minX, minY, minZ).func_181666_a(color.getR(), color.getG(), color.getB(), color.getA()).func_181675_d();
        vertexbuffer.func_181662_b(maxX, maxY, maxZ).func_181666_a(color.getR(), color.getG(), color.getB(), color.getA()).func_181675_d();
        tessellator.func_78381_a();
    }
}

