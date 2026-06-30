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
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.color((float)red, (float)green, (float)blue, (float)alpha);
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION);
        vertexbuffer.pos((double)left, (double)top + (double)height, 0.0).endVertex();
        vertexbuffer.pos((double)left + (double)width, (double)top + (double)height, 0.0).endVertex();
        vertexbuffer.pos((double)left + (double)width, (double)top, 0.0).endVertex();
        vertexbuffer.pos((double)left, (double)top, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void rectangle(float left, float top, float width, float height) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION);
        vertexbuffer.pos((double)left, (double)top + (double)height, 0.0).endVertex();
        vertexbuffer.pos((double)left + (double)width, (double)top + (double)height, 0.0).endVertex();
        vertexbuffer.pos((double)left + (double)width, (double)top, 0.0).endVertex();
        vertexbuffer.pos((double)left, (double)top, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void rectangle(float x2, float y2, float w2, float h2, int color) {
        float a2 = (float)(color >> 24 & 0xFF) / 255.0f;
        float r2 = (float)(color >> 16 & 0xFF) / 255.0f;
        float g2 = (float)(color >> 8 & 0xFF) / 255.0f;
        float b2 = (float)(color & 0xFF) / 255.0f;
        GlStateManager.color((float)r2, (float)g2, (float)b2, (float)a2);
        Draw.rectangle(x2, y2, w2, h2);
    }

    public static void rectangleHorizontalGradient(float x2, float y2, float w2, float h2, IColorRead color0, IColorRead color1) {
        GlStateManager.enableBlend();
        GlStateManager.blendFunc((int)770, (int)771);
        GL11.glHint((int)3152, (int)4354);
        GlStateManager.shadeModel((int)7425);
        GlStateManager.disableTexture2D();
        GlStateManager.glBegin((int)7);
        GlStateManager.color((float)color0.getR(), (float)color0.getG(), (float)color0.getB(), (float)color0.getA());
        GlStateManager.glTexCoord2f((float)0.0f, (float)0.0f);
        GlStateManager.glVertex3f((float)(x2 + 0.0f), (float)(y2 + 0.0f), (float)0.0f);
        GlStateManager.glTexCoord2f((float)0.0f, (float)1.0f);
        GlStateManager.glVertex3f((float)(x2 + 0.0f), (float)(y2 + h2), (float)0.0f);
        GlStateManager.color((float)color1.getR(), (float)color1.getG(), (float)color1.getB(), (float)color1.getA());
        GlStateManager.glTexCoord2f((float)1.0f, (float)1.0f);
        GlStateManager.glVertex3f((float)(x2 + w2), (float)(y2 + h2), (float)0.0f);
        GlStateManager.glTexCoord2f((float)1.0f, (float)0.0f);
        GlStateManager.glVertex3f((float)(x2 + w2), (float)(y2 + 0.0f), (float)0.0f);
        GlStateManager.glEnd();
        GlStateManager.enableTexture2D();
    }

    public static void rectangleVerticalGradient(float x2, float y2, float w2, float h2, IColorRead color0, IColorRead color1) {
        GlStateManager.enableBlend();
        GlStateManager.blendFunc((int)770, (int)771);
        GL11.glHint((int)3152, (int)4354);
        GlStateManager.shadeModel((int)7425);
        GlStateManager.disableTexture2D();
        GlStateManager.glBegin((int)7);
        GlStateManager.color((float)color0.getR(), (float)color0.getG(), (float)color0.getB(), (float)color0.getA());
        GlStateManager.glTexCoord2f((float)1.0f, (float)0.0f);
        GlStateManager.glVertex3f((float)(x2 + w2), (float)(y2 + 0.0f), (float)0.0f);
        GlStateManager.glTexCoord2f((float)0.0f, (float)0.0f);
        GlStateManager.glVertex3f((float)(x2 + 0.0f), (float)(y2 + 0.0f), (float)0.0f);
        GlStateManager.color((float)color1.getR(), (float)color1.getG(), (float)color1.getB(), (float)color1.getA());
        GlStateManager.glTexCoord2f((float)0.0f, (float)1.0f);
        GlStateManager.glVertex3f((float)(x2 + 0.0f), (float)(y2 + h2), (float)0.0f);
        GlStateManager.glTexCoord2f((float)1.0f, (float)1.0f);
        GlStateManager.glVertex3f((float)(x2 + w2), (float)(y2 + h2), (float)0.0f);
        GlStateManager.glEnd();
        GlStateManager.enableTexture2D();
    }

    public static void rectangleHorizontalGradient(float x2, float y2, float width, float height, int color0, int color1) {
        GL11.glHint((int)3152, (int)4354);
        GlStateManager.shadeModel((int)7425);
        float a0 = (float)(color0 >> 24 & 0xFF) / 255.0f;
        float r0 = (float)(color0 >> 16 & 0xFF) / 255.0f;
        float g0 = (float)(color0 >> 8 & 0xFF) / 255.0f;
        float b0 = (float)(color0 & 0xFF) / 255.0f;
        float a1 = (float)(color1 >> 24 & 0xFF) / 255.0f;
        float r1 = (float)(color1 >> 16 & 0xFF) / 255.0f;
        float g1 = (float)(color1 >> 8 & 0xFF) / 255.0f;
        float b1 = (float)(color1 & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vertexbuffer.pos((double)x2, (double)y2 + (double)height, 0.0).color(r0, g0, b0, a0).endVertex();
        vertexbuffer.pos((double)x2 + (double)width, (double)y2 + (double)height, 0.0).color(r1, g1, b1, a1).endVertex();
        vertexbuffer.pos((double)x2 + (double)width, (double)y2, 0.0).color(r1, g1, b1, a1).endVertex();
        vertexbuffer.pos((double)x2, (double)y2, 0.0).color(r0, g0, b0, a0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void rectangleVerticalGradient(float x2, float y2, float w2, float h2, int color0, int color1) {
        GlStateManager.enableBlend();
        GL11.glHint((int)3152, (int)4354);
        GlStateManager.shadeModel((int)7425);
        float a0 = (float)(color0 >> 24 & 0xFF) / 255.0f;
        float r0 = (float)(color0 >> 16 & 0xFF) / 255.0f;
        float g0 = (float)(color0 >> 8 & 0xFF) / 255.0f;
        float b0 = (float)(color0 & 0xFF) / 255.0f;
        float a1 = (float)(color1 >> 24 & 0xFF) / 255.0f;
        float r1 = (float)(color1 >> 16 & 0xFF) / 255.0f;
        float g1 = (float)(color1 >> 8 & 0xFF) / 255.0f;
        float b1 = (float)(color1 & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vertexbuffer.pos((double)x2, (double)y2 + (double)h2, 0.0).color(r1, g1, b1, a1).endVertex();
        vertexbuffer.pos((double)x2 + (double)w2, (double)y2 + (double)h2, 0.0).color(r1, g1, b1, a1).endVertex();
        vertexbuffer.pos((double)x2 + (double)w2, (double)y2, 0.0).color(r0, g0, b0, a0).endVertex();
        vertexbuffer.pos((double)x2, (double)y2, 0.0).color(r0, g0, b0, a0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void circle(double x2, double y2, double radius, int vertices) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        vertexbuffer.begin(2, DefaultVertexFormats.POSITION);
        for (int i2 = 0; i2 < vertices; ++i2) {
            double angle = (double)i2 / (double)vertices * Math.PI * 2.0;
            vertexbuffer.pos(x2 + Math.cos(angle) * radius, y2 + Math.sin(angle) * radius, 0.0).endVertex();
        }
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void circle(double x2, double y2, double radius) {
        Draw.circle(x2, y2, radius, 100);
    }

    public static void cube(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, IColorRead color) {
        float r2 = color.getR();
        float g2 = color.getG();
        float b2 = color.getB();
        float a2 = color.getA();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
        vertexbuffer.pos(minX, maxY, minZ).tex(0.0, 0.0).color(r2, g2, b2, a2).normal(-1.0f, 0.0f, 0.0f).endVertex();
        vertexbuffer.pos(minX, minY, minZ).tex(0.0, 0.0).color(r2, g2, b2, a2).normal(-1.0f, 0.0f, 0.0f).endVertex();
        vertexbuffer.pos(minX, minY, maxZ).tex(0.0, 0.0).color(r2, g2, b2, a2).normal(-1.0f, 0.0f, 0.0f).endVertex();
        vertexbuffer.pos(minX, maxY, maxZ).tex(0.0, 0.0).color(r2, g2, b2, a2).normal(-1.0f, 0.0f, 0.0f).endVertex();
        vertexbuffer.pos(maxX, maxY, maxZ).tex(0.0, 0.0).color(r2, g2, b2, a2).normal(1.0f, 0.0f, 0.0f).endVertex();
        vertexbuffer.pos(maxX, minY, maxZ).tex(0.0, 0.0).color(r2, g2, b2, a2).normal(1.0f, 0.0f, 0.0f).endVertex();
        vertexbuffer.pos(maxX, minY, minZ).tex(0.0, 0.0).color(r2, g2, b2, a2).normal(1.0f, 0.0f, 0.0f).endVertex();
        vertexbuffer.pos(maxX, maxY, minZ).tex(0.0, 0.0).color(r2, g2, b2, a2).normal(1.0f, 0.0f, 0.0f).endVertex();
        vertexbuffer.pos(maxX, maxY, minZ).tex(0.0, 0.0).color(r2, g2, b2, a2).normal(0.0f, 0.0f, -1.0f).endVertex();
        vertexbuffer.pos(maxX, minY, minZ).tex(0.0, 0.0).color(r2, g2, b2, a2).normal(0.0f, 0.0f, -1.0f).endVertex();
        vertexbuffer.pos(minX, minY, minZ).tex(0.0, 0.0).color(r2, g2, b2, a2).normal(0.0f, 0.0f, -1.0f).endVertex();
        vertexbuffer.pos(minX, maxY, minZ).tex(0.0, 0.0).color(r2, g2, b2, a2).normal(0.0f, 0.0f, -1.0f).endVertex();
        vertexbuffer.pos(minX, maxY, maxZ).tex(0.0, 0.0).color(r2, g2, b2, a2).normal(0.0f, 0.0f, 1.0f).endVertex();
        vertexbuffer.pos(minX, minY, maxZ).tex(0.0, 0.0).color(r2, g2, b2, a2).normal(0.0f, 0.0f, 1.0f).endVertex();
        vertexbuffer.pos(maxX, minY, maxZ).tex(0.0, 0.0).color(r2, g2, b2, a2).normal(0.0f, 0.0f, 1.0f).endVertex();
        vertexbuffer.pos(maxX, maxY, maxZ).tex(0.0, 0.0).color(r2, g2, b2, a2).normal(0.0f, 0.0f, 1.0f).endVertex();
        vertexbuffer.pos(minX, minY, maxZ).tex(0.0, 0.0).color(r2, g2, b2, a2).normal(0.0f, -1.0f, 0.0f).endVertex();
        vertexbuffer.pos(minX, minY, minZ).tex(0.0, 0.0).color(r2, g2, b2, a2).normal(0.0f, -1.0f, 0.0f).endVertex();
        vertexbuffer.pos(maxX, minY, minZ).tex(0.0, 0.0).color(r2, g2, b2, a2).normal(0.0f, -1.0f, 0.0f).endVertex();
        vertexbuffer.pos(maxX, minY, maxZ).tex(0.0, 0.0).color(r2, g2, b2, a2).normal(0.0f, -1.0f, 0.0f).endVertex();
        vertexbuffer.pos(maxX, maxY, maxZ).tex(0.0, 0.0).color(r2, g2, b2, a2).normal(0.0f, 1.0f, 0.0f).endVertex();
        vertexbuffer.pos(maxX, maxY, minZ).tex(0.0, 0.0).color(r2, g2, b2, a2).normal(0.0f, 1.0f, 0.0f).endVertex();
        vertexbuffer.pos(minX, maxY, minZ).tex(0.0, 0.0).color(r2, g2, b2, a2).normal(0.0f, 1.0f, 0.0f).endVertex();
        vertexbuffer.pos(minX, maxY, maxZ).tex(0.0, 0.0).color(r2, g2, b2, a2).normal(0.0f, 1.0f, 0.0f).endVertex();
        tessellator.draw();
    }

    public static void texturedModalRect(int x2, int y2, int textureX, int textureY, int width, int height) {
        float f2 = 0.00390625f;
        float f1 = 0.00390625f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos((double)(x2 + 0), (double)(y2 + height), 0.0).tex((double)((float)(textureX + 0) * 0.00390625f), (double)((float)(textureY + height) * 0.00390625f)).endVertex();
        vertexbuffer.pos((double)(x2 + width), (double)(y2 + height), 0.0).tex((double)((float)(textureX + width) * 0.00390625f), (double)((float)(textureY + height) * 0.00390625f)).endVertex();
        vertexbuffer.pos((double)(x2 + width), (double)(y2 + 0), 0.0).tex((double)((float)(textureX + width) * 0.00390625f), (double)((float)(textureY + 0) * 0.00390625f)).endVertex();
        vertexbuffer.pos((double)(x2 + 0), (double)(y2 + 0), 0.0).tex((double)((float)(textureX + 0) * 0.00390625f), (double)((float)(textureY + 0) * 0.00390625f)).endVertex();
        tessellator.draw();
    }

    public static void texturedModalRect(int x2, int y2, int width, int height, int textureX, int textureY, int textureWidth, int textureHeight) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos((double)(x2 + 0), (double)(y2 + height), 0.0).tex((double)((float)(textureX + 0) * 0.00390625f), (double)((float)(textureY + textureHeight) * 0.00390625f)).endVertex();
        vertexbuffer.pos((double)(x2 + width), (double)(y2 + height), 0.0).tex((double)((float)(textureX + textureWidth) * 0.00390625f), (double)((float)(textureY + textureHeight) * 0.00390625f)).endVertex();
        vertexbuffer.pos((double)(x2 + width), (double)(y2 + 0), 0.0).tex((double)((float)(textureX + textureWidth) * 0.00390625f), (double)((float)(textureY + 0) * 0.00390625f)).endVertex();
        vertexbuffer.pos((double)(x2 + 0), (double)(y2 + 0), 0.0).tex((double)((float)(textureX + 0) * 0.00390625f), (double)((float)(textureY + 0) * 0.00390625f)).endVertex();
        tessellator.draw();
    }

    public static void texturedRectangle(int x2, int y2, int width, int height, float textureX, float textureY, float textureWidth, float textureHeight) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos((double)(x2 + 0), (double)(y2 + height), 0.0).tex((double)textureX, (double)(textureY + textureHeight)).endVertex();
        vertexbuffer.pos((double)(x2 + width), (double)(y2 + height), 0.0).tex((double)(textureX + textureWidth), (double)(textureY + textureHeight)).endVertex();
        vertexbuffer.pos((double)(x2 + width), (double)(y2 + 0), 0.0).tex((double)(textureX + textureWidth), (double)textureY).endVertex();
        vertexbuffer.pos((double)(x2 + 0), (double)(y2 + 0), 0.0).tex((double)textureX, (double)textureY).endVertex();
        tessellator.draw();
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
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(1, DefaultVertexFormats.POSITION_COLOR);
        vertexbuffer.pos(minX, minY, minZ).color(color.getR(), color.getG(), color.getB(), color.getA()).endVertex();
        vertexbuffer.pos(maxX, maxY, maxZ).color(color.getR(), color.getG(), color.getB(), color.getA()).endVertex();
        tessellator.draw();
    }
}

