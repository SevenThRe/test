/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.ancientdream.client.utils;

import java.awt.Color;
import java.awt.Rectangle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderUtils {
    public static float alpha = 1.0f;

    public static void drawTexture(double x, double y, double width, double height, float mouseX, float mouseY, ResourceLocation def, ResourceLocation hov) {
        if ((double)mouseX >= x && (double)mouseX <= x + width && (double)mouseY >= y && (double)mouseY <= y + height) {
            RenderUtils.bindTexture(hov);
        } else {
            RenderUtils.bindTexture(def);
        }
        RenderUtils.drawTexture(x, y, 0.0, 0.0, width, height, width, height, new ResourceLocation[0]);
    }

    public static void drawTexture(double x, double y, double width, double height, ResourceLocation def) {
        RenderUtils.bindTexture(def);
        RenderUtils.drawTexture(x, y, 0.0, 0.0, width, height, width, height, new ResourceLocation[0]);
    }

    public static void drawTexture(double x, double y, double width, double height) {
        RenderUtils.drawTexture(x, y, 0.0, 0.0, width, height, width, height, new ResourceLocation[0]);
    }

    public static void drawTexture(double x, double y, double u, double v, double u1, double v1, double width, double height, double textureWidth, double textureHeight, float mouseX, float mouseY, ResourceLocation def) {
        if ((double)mouseX >= x && (double)mouseX <= x + width && (double)mouseY >= y && (double)mouseY <= y + height) {
            RenderUtils.drawTexture(x, y, u1, v1, width, height, textureWidth, textureHeight, def);
        } else {
            RenderUtils.drawTexture(x, y, u, v, width, height, textureWidth, textureHeight, def);
        }
    }

    public static void drawTexture(double x, double y, double u, double v, double width, double height, double textureWidth, double textureHeight, float mouseX, float mouseY, ResourceLocation def, ResourceLocation hov) {
        if ((double)mouseX >= x && (double)mouseX <= x + width && (double)mouseY >= y && (double)mouseY <= y + height) {
            RenderUtils.bindTexture(hov);
        } else {
            RenderUtils.bindTexture(def);
        }
        RenderUtils.drawTexture(x, y, u, v, width, height, textureWidth, textureHeight, new ResourceLocation[0]);
    }

    public static void drawTexture(double x, double y, double u, double v, double width, double height, double textureWidth, double textureHeight, ResourceLocation ... res) {
        if (res.length > 0) {
            RenderUtils.bindTexture(res[0]);
        }
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)Math.min(1.0f, Math.max(0.0f, alpha)));
        float f = (float)(1.0 / textureWidth);
        float f1 = (float)(1.0 / textureHeight);
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferbuilder = tessellator.func_178180_c();
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        bufferbuilder.func_181662_b(x, y + height, 0.0).func_187315_a(u * (double)f, (v + height) * (double)f1).func_181675_d();
        bufferbuilder.func_181662_b(x + width, y + height, 0.0).func_187315_a((u + width) * (double)f, (v + height) * (double)f1).func_181675_d();
        bufferbuilder.func_181662_b(x + width, y, 0.0).func_187315_a((u + width) * (double)f, v * (double)f1).func_181675_d();
        bufferbuilder.func_181662_b(x, y, 0.0).func_187315_a(u * (double)f, v * (double)f1).func_181675_d();
        tessellator.func_78381_a();
    }

    public static void drawText(String text, double x, double y, boolean center, boolean shadow) {
        text = "\u00a7f" + text.replace("&", "\u00a7");
        FontRenderer fontRenderer = Minecraft.func_71410_x().field_71466_p;
        fontRenderer.func_175065_a(text, (float)(x - (double)(center ? RenderUtils.getTextWidth(null, text) / 2 : 0)), (float)y, -1, shadow);
    }

    public static int getTextWidth(FontRenderer fontRenderer, String text) {
        if (fontRenderer == null) {
            fontRenderer = Minecraft.func_71410_x().field_71466_p;
        }
        return fontRenderer.func_78256_a(text.replace("&", "\u00a7"));
    }

    public static void drawColor(double x, double y, double width, double height, Color c) {
        int color = RenderUtils.toRGBA(c);
        RenderUtils.drawColor(x, y, width, height, color);
    }

    public static void drawColor(double x, double y, double width, double height, int color) {
        float f3 = (float)(color >> 24 & 0xFF) / 255.0f * Math.min(1.0f, Math.max(0.0f, alpha));
        float f = (float)(color >> 16 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f2 = (float)(color & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder bufferbuilder = tessellator.func_178180_c();
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_187428_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.func_179131_c((float)f, (float)f1, (float)f2, (float)f3);
        bufferbuilder.func_181668_a(7, DefaultVertexFormats.field_181705_e);
        bufferbuilder.func_181662_b(x, y + height, 0.0).func_181675_d();
        bufferbuilder.func_181662_b(x + width, y + height, 0.0).func_181675_d();
        bufferbuilder.func_181662_b(x + width, y, 0.0).func_181675_d();
        bufferbuilder.func_181662_b(x, y, 0.0).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }

    public static void scissorBox(float x, float y, float width, float height) {
        float yend = y + height;
        ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x());
        int factor = sr.func_78325_e();
        if (Minecraft.func_71410_x().field_71462_r == null) {
            return;
        }
        float bottomY = (float)Minecraft.func_71410_x().field_71462_r.field_146295_m - yend;
        GL11.glScissor((int)((int)(x * (float)factor)), (int)((int)(bottomY * (float)factor)), (int)((int)(width * (float)factor)), (int)((int)(height * (float)factor)));
    }

    public static int toRGBA(Color c) {
        return c.getBlue() | c.getGreen() << 8 | c.getRed() << 16 | c.getAlpha() << 24;
    }

    public static void bindTexture(float x, float y, float w, float h, float mx, float my, ResourceLocation resourceLocation, ResourceLocation resourceLocation1) {
        if (new Rectangle((int)x, (int)y, (int)w, (int)h).contains(mx, my)) {
            Minecraft.func_71410_x().func_110434_K().func_110577_a(resourceLocation1);
        } else {
            Minecraft.func_71410_x().func_110434_K().func_110577_a(resourceLocation);
        }
    }

    public static void bindTexture(ResourceLocation resourceLocation) {
        Minecraft.func_71410_x().func_110434_K().func_110577_a(resourceLocation);
    }
}

