/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  org.lwjgl.opengl.GL11
 */
package journeymap.client.render.draw;

import java.awt.geom.Point2D;
import java.util.List;
import journeymap.client.api.model.ShapeProperties;
import journeymap.client.cartography.color.RGB;
import journeymap.client.render.texture.TextureImpl;
import journeymap.client.ui.theme.Theme;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.lwjgl.opengl.GL11;

public class DrawUtil {
    public static double zLevel = 0.0;
    static Tessellator tessellator = Tessellator.func_178181_a();
    static BufferBuilder worldrenderer = tessellator.func_178180_c();

    public static void drawCenteredLabel(String text, double x, double y, Integer bgColor, float bgAlpha, Integer color, float alpha, double fontScale) {
        DrawUtil.drawLabel(text, x, y, HAlign.Center, VAlign.Middle, bgColor, bgAlpha, color, alpha, fontScale, true, 0.0);
    }

    public static void drawCenteredLabel(String text, double x, double y, Integer bgColor, float bgAlpha, Integer color, float alpha, double fontScale, boolean fontShadow) {
        DrawUtil.drawLabel(text, x, y, HAlign.Center, VAlign.Middle, bgColor, bgAlpha, color, alpha, fontScale, fontShadow, 0.0);
    }

    public static void drawCenteredLabel(String text, double x, double y, Integer bgColor, float bgAlpha, Integer color, float alpha, double fontScale, double rotation) {
        DrawUtil.drawLabel(text, x, y, HAlign.Center, VAlign.Middle, bgColor, bgAlpha, color, alpha, fontScale, true, rotation);
    }

    public static void drawLabel(String text, double x, double y, HAlign hAlign, VAlign vAlign, Integer bgColor, float bgAlpha, int color, float alpha, double fontScale, boolean fontShadow) {
        DrawUtil.drawLabel(text, x, y, hAlign, vAlign, bgColor, bgAlpha, color, alpha, fontScale, fontShadow, 0.0);
    }

    public static void drawLabels(String[] lines, double x, double y, HAlign hAlign, VAlign vAlign, Integer bgColor, float bgAlpha, Integer color, float alpha, double fontScale, boolean fontShadow, double rotation) {
        if (lines.length == 0) {
            return;
        }
        if (lines.length == 1) {
            DrawUtil.drawLabel(lines[0], x, y, hAlign, vAlign, bgColor, bgAlpha, color, alpha, fontScale, fontShadow, rotation);
            return;
        }
        FontRenderer fontRenderer = FMLClientHandler.instance().getClient().field_71466_p;
        double vpad = fontRenderer.func_82883_a() ? 0.0 : (fontShadow ? 6.0 : 4.0);
        double lineHeight = (double)fontRenderer.field_78288_b * fontScale;
        double bgHeight = lineHeight * (double)lines.length + vpad;
        double bgWidth = 0.0;
        if (bgColor != null && bgAlpha > 0.0f) {
            for (String line : lines) {
                bgWidth = Math.max(bgWidth, (double)fontRenderer.func_78256_a(line) * fontScale);
            }
            if (bgWidth % 2.0 == 0.0) {
                bgWidth += 1.0;
            }
        }
        if (lines.length > 1) {
            switch (vAlign) {
                case Above: {
                    y -= lineHeight * (double)lines.length;
                    bgHeight += vpad / 2.0;
                    break;
                }
                case Middle: {
                    y -= bgHeight / 2.0;
                    break;
                }
            }
        }
        for (String line : lines) {
            DrawUtil.drawLabel(line, x, y, hAlign, vAlign, bgColor, bgAlpha, bgWidth, bgHeight, color, alpha, fontScale, fontShadow, rotation);
            bgColor = null;
            y += lineHeight;
        }
    }

    public static void drawLabel(String text, Theme.LabelSpec labelSpec, double x, double y, HAlign hAlign, VAlign vAlign, double fontScale, double rotation) {
        DrawUtil.drawLabel(text, x, y, hAlign, vAlign, labelSpec.background.getColor(), labelSpec.background.alpha, labelSpec.foreground.getColor(), labelSpec.foreground.alpha, fontScale, labelSpec.shadow, rotation);
    }

    public static void drawLabel(String text, double x, double y, HAlign hAlign, VAlign vAlign, Integer bgColor, float bgAlpha, Integer color, float alpha, double fontScale, boolean fontShadow, double rotation) {
        double bgWidth = 0.0;
        double bgHeight = 0.0;
        if (bgColor != null && bgAlpha > 0.0f) {
            FontRenderer fontRenderer = FMLClientHandler.instance().getClient().field_71466_p;
            bgWidth = fontRenderer.func_78256_a(text);
            bgHeight = DrawUtil.getLabelHeight(fontRenderer, fontShadow);
        }
        DrawUtil.drawLabel(text, x, y, hAlign, vAlign, bgColor, bgAlpha, bgWidth, bgHeight, color, alpha, fontScale, fontShadow, rotation);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void drawLabel(String text, double x, double y, HAlign hAlign, VAlign vAlign, Integer bgColor, float bgAlpha, double bgWidth, double bgHeight, Integer color, float alpha, double fontScale, boolean fontShadow, double rotation) {
        int height;
        if (text == null || text.length() == 0) {
            return;
        }
        FontRenderer fontRenderer = FMLClientHandler.instance().getClient().field_71466_p;
        boolean drawRect = bgColor != null && bgAlpha > 0.0f;
        double width = fontRenderer.func_78256_a(text);
        int n = height = drawRect ? DrawUtil.getLabelHeight(fontRenderer, fontShadow) : fontRenderer.field_78288_b;
        if (!drawRect && fontRenderer.func_82883_a()) {
            --height;
        }
        GlStateManager.func_179094_E();
        try {
            if (fontScale != 1.0) {
                x /= fontScale;
                y /= fontScale;
                GlStateManager.func_179139_a((double)fontScale, (double)fontScale, (double)0.0);
            }
            float textX = (float)x;
            float textY = (float)y;
            double rectX = x;
            double rectY = y;
            switch (hAlign) {
                case Left: {
                    textX = (float)(x - width);
                    rectX = textX;
                    break;
                }
                case Center: {
                    textX = (float)(x - width / 2.0 + (fontScale > 1.0 ? 0.5 : 0.0));
                    rectX = (float)(x - Math.max(1.0, bgWidth) / 2.0 + (fontScale > 1.0 ? 0.5 : 0.0));
                    break;
                }
                case Right: {
                    textX = (float)x;
                    rectX = (float)x;
                }
            }
            double vpad = drawRect ? (double)(height - fontRenderer.field_78288_b) / 2.0 : 0.0;
            switch (vAlign) {
                case Above: {
                    rectY = y - (double)height;
                    textY = (float)(rectY + vpad + (double)(!fontRenderer.func_82883_a() ? 1 : 0));
                    break;
                }
                case Middle: {
                    rectY = y - (double)(height / 2) + (fontScale > 1.0 ? 0.5 : 0.0);
                    textY = (float)(rectY + vpad);
                    break;
                }
                case Below: {
                    rectY = y;
                    textY = (float)(rectY + vpad);
                }
            }
            if (rotation != 0.0) {
                GlStateManager.func_179137_b((double)x, (double)y, (double)0.0);
                GlStateManager.func_179114_b((float)((float)(-rotation)), (float)0.0f, (float)0.0f, (float)1.0f);
                GlStateManager.func_179137_b((double)(-x), (double)(-y), (double)0.0);
            }
            if (drawRect) {
                int hpad = 2;
                DrawUtil.drawRectangle(rectX - 2.0 - 0.5, rectY, bgWidth + 4.0, bgHeight, bgColor, bgAlpha);
            }
            if (alpha < 1.0f) {
                color = RGB.toArbg(color, alpha);
            }
            GlStateManager.func_179137_b((double)((double)textX - Math.floor(textX)), (double)((double)textY - Math.floor(textY)), (double)0.0);
            fontRenderer.func_175065_a(text, textX, textY, color.intValue(), fontShadow);
        }
        finally {
            GlStateManager.func_179121_F();
        }
    }

    public static int getLabelHeight(FontRenderer fr, boolean fontShadow) {
        int vpad = fr.func_82883_a() ? 0 : (fontShadow ? 6 : 4);
        return fr.field_78288_b + vpad;
    }

    public static void drawImage(TextureImpl texture, double x, double y, boolean flip, float scale, double rotation) {
        DrawUtil.drawQuad(texture, x, y, (float)texture.getWidth() * scale, (float)texture.getHeight() * scale, flip, rotation);
    }

    public static void drawImage(TextureImpl texture, float alpha, double x, double y, boolean flip, float scale, double rotation) {
        DrawUtil.drawQuad(texture, 0xFFFFFF, alpha, x, y, (float)texture.getWidth() * scale, (float)texture.getHeight() * scale, false, rotation);
    }

    public static void drawClampedImage(TextureImpl texture, double x, double y, float scale, double rotation) {
        DrawUtil.drawClampedImage(texture, 0xFFFFFF, 1.0f, x, y, scale, rotation);
    }

    public static void drawClampedImage(TextureImpl texture, int color, float alpha, double x, double y, float scale, double rotation) {
        DrawUtil.drawQuad(texture, color, alpha, x, y, (float)texture.getWidth() * scale, (float)texture.getHeight() * scale, false, rotation);
    }

    public static void drawColoredImage(TextureImpl texture, int color, float alpha, double x, double y, float scale, double rotation) {
        DrawUtil.drawQuad(texture, color, alpha, x, y, (float)texture.getWidth() * scale, (float)texture.getHeight() * scale, false, rotation);
    }

    public static void drawColoredSprite(TextureImpl texture, double displayWidth, double displayHeight, double spriteX, double spriteY, double spriteWidth, double spriteHeight, Integer color, float alpha, double x, double y, float scale, double rotation) {
        double texWidth = texture.getWidth();
        double texHeight = texture.getHeight();
        double minU = Math.max(0.0, spriteX / texWidth);
        double minV = Math.max(0.0, spriteY / texHeight);
        double maxU = Math.min(1.0, (spriteX + spriteWidth) / texWidth);
        double maxV = Math.min(1.0, (spriteY + spriteHeight) / texHeight);
        DrawUtil.drawQuad(texture, color, alpha, x, y, displayWidth * (double)scale, displayHeight * (double)scale, minU, minV, maxU, maxV, rotation, false, true, 770, 771, false);
    }

    public static void drawColoredImage(TextureImpl texture, int color, float alpha, double x, double y, double rotation) {
        DrawUtil.drawQuad(texture, color, alpha, x, y, texture.getWidth(), texture.getHeight(), false, rotation);
    }

    public static void drawColoredImage(TextureImpl texture, int color, float alpha, double x, double y, int width, int height, double rotation) {
        DrawUtil.drawQuad(texture, color, alpha, x, y, width, height, false, rotation);
    }

    public static void drawQuad(TextureImpl texture, double x, double y, double width, double height, boolean flip, double rotation) {
        DrawUtil.drawQuad(texture, 0xFFFFFF, 1.0f, x, y, width, height, 0.0, 0.0, 1.0, 1.0, rotation, flip, true, 770, 771, false);
    }

    public static void drawQuad(TextureImpl texture, int color, float alpha, double x, double y, double width, double height, boolean flip, double rotation) {
        DrawUtil.drawQuad(texture, color, alpha, x, y, width, height, 0.0, 0.0, 1.0, 1.0, rotation, flip, true, 770, 771, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void drawQuad(TextureImpl texture, int color, float alpha, double x, double y, double width, double height, double minU, double minV, double maxU, double maxV, double rotation, boolean flip, boolean blend, int glBlendSfactor, int glBlendDFactor, boolean clampTexture) {
        GlStateManager.func_179094_E();
        try {
            if (blend) {
                GlStateManager.func_179147_l();
                GlStateManager.func_179120_a((int)glBlendSfactor, (int)glBlendDFactor, (int)1, (int)0);
            }
            GlStateManager.func_179098_w();
            GlStateManager.func_179144_i((int)texture.func_110552_b());
            if (alpha > 1.0f) {
                alpha /= 255.0f;
            }
            if (blend) {
                float[] c = RGB.floats(color);
                GlStateManager.func_179131_c((float)c[0], (float)c[1], (float)c[2], (float)alpha);
            } else {
                GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
            }
            GL11.glTexParameteri((int)3553, (int)10241, (int)9729);
            GL11.glTexParameteri((int)3553, (int)10240, (int)9729);
            int texEdgeBehavior = clampTexture ? 33071 : 10497;
            GL11.glTexParameteri((int)3553, (int)10242, (int)texEdgeBehavior);
            GL11.glTexParameteri((int)3553, (int)10243, (int)texEdgeBehavior);
            if (rotation != 0.0) {
                double transX = x + width / 2.0;
                double transY = y + height / 2.0;
                GlStateManager.func_179137_b((double)transX, (double)transY, (double)0.0);
                GlStateManager.func_179114_b((float)((float)rotation), (float)0.0f, (float)0.0f, (float)1.0f);
                GlStateManager.func_179137_b((double)(-transX), (double)(-transY), (double)0.0);
            }
            double direction = flip ? -maxU : maxU;
            DrawUtil.startDrawingQuads(false);
            DrawUtil.addVertexWithUV(x, height + y, zLevel, minU, maxV);
            DrawUtil.addVertexWithUV(x + width, height + y, zLevel, direction, maxV);
            DrawUtil.addVertexWithUV(x + width, y, zLevel, direction, minV);
            DrawUtil.addVertexWithUV(x, y, zLevel, minU, minV);
            DrawUtil.draw();
            if (blend) {
                GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                if (glBlendSfactor != 770 || glBlendDFactor != 771) {
                    GlStateManager.func_179147_l();
                    GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
                }
            }
        }
        finally {
            GlStateManager.func_179121_F();
        }
    }

    public static void drawRectangle(double x, double y, double width, double height, int color, float alpha) {
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_179118_c();
        GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
        int[] rgba = RGB.ints(color, alpha);
        DrawUtil.startDrawingQuads(true);
        DrawUtil.addVertex(x, height + y, zLevel, rgba);
        DrawUtil.addVertex(x + width, height + y, zLevel, rgba);
        DrawUtil.addVertex(x + width, y, zLevel, rgba);
        DrawUtil.addVertex(x, y, zLevel, rgba);
        DrawUtil.draw();
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.func_179098_w();
        GlStateManager.func_179141_d();
        GlStateManager.func_179084_k();
    }

    public static void drawPolygon(double xOffset, double yOffset, List<Point2D.Double> screenPoints, ShapeProperties shapeProperties) {
        float[] rgba;
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_179141_d();
        GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
        if (shapeProperties.getFillOpacity() >= 0.01f) {
            rgba = RGB.floats(shapeProperties.getFillColor(), shapeProperties.getFillOpacity());
            GlStateManager.func_179131_c((float)rgba[0], (float)rgba[1], (float)rgba[2], (float)rgba[3]);
            int lastIndex = screenPoints.size() - 1;
            GL11.glBegin((int)9);
            for (int i = 0; i <= lastIndex; ++i) {
                int j = i < lastIndex ? i + 1 : 0;
                Point2D.Double first = screenPoints.get(i);
                Point2D.Double second = screenPoints.get(j);
                GL11.glVertex2d((double)(first.getX() + xOffset), (double)(first.getY() + yOffset));
                GL11.glVertex2d((double)(second.getX() + xOffset), (double)(second.getY() + yOffset));
            }
            GL11.glEnd();
        }
        if (shapeProperties.getStrokeOpacity() >= 0.01f && shapeProperties.getStrokeWidth() > 0.0f) {
            rgba = RGB.floats(shapeProperties.getStrokeColor(), shapeProperties.getFillOpacity());
            GlStateManager.func_179131_c((float)rgba[0], (float)rgba[1], (float)rgba[2], (float)rgba[3]);
            float stroke = shapeProperties.getStrokeWidth();
            GL11.glLineWidth((float)stroke);
            int lastIndex = screenPoints.size() - 1;
            GL11.glBegin((int)3);
            for (int i = 0; i <= lastIndex; ++i) {
                int j = i < lastIndex ? i + 1 : 0;
                Point2D.Double first = screenPoints.get(i);
                Point2D.Double second = screenPoints.get(j);
                GL11.glVertex2d((double)(first.getX() + xOffset), (double)(first.getY() + yOffset));
                GL11.glVertex2d((double)(second.getX() + xOffset), (double)(second.getY() + yOffset));
            }
            GL11.glEnd();
        }
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.func_179098_w();
        GlStateManager.func_179141_d();
        GlStateManager.func_179084_k();
    }

    public static void drawGradientRect(double x, double y, double width, double height, int startColor, float startAlpha, int endColor, float endAlpha) {
        if (startAlpha > 1.0f) {
            startAlpha /= 255.0f;
        }
        if (endAlpha > 1.0f) {
            endAlpha /= 255.0f;
        }
        int[] rgbaStart = RGB.ints(startColor, startAlpha);
        int[] rgbaEnd = RGB.ints(endColor, endAlpha);
        GlStateManager.func_179090_x();
        GlStateManager.func_179147_l();
        GlStateManager.func_179118_c();
        GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
        GlStateManager.func_179103_j((int)7425);
        DrawUtil.startDrawingQuads(true);
        DrawUtil.addVertexWithUV(x, height + y, zLevel, 0.0, 1.0, rgbaEnd);
        DrawUtil.addVertexWithUV(x + width, height + y, zLevel, 1.0, 1.0, rgbaEnd);
        DrawUtil.addVertexWithUV(x + width, y, zLevel, 1.0, 0.0, rgbaStart);
        DrawUtil.addVertexWithUV(x, y, zLevel, 0.0, 0.0, rgbaStart);
        DrawUtil.draw();
        GlStateManager.func_179103_j((int)7424);
        GlStateManager.func_179098_w();
        GlStateManager.func_179141_d();
        GlStateManager.func_179147_l();
    }

    public static void drawBoundTexture(double startU, double startV, double startX, double startY, double z, double endU, double endV, double endX, double endY) {
        DrawUtil.startDrawingQuads(false);
        DrawUtil.addVertexWithUV(startX, endY, z, startU, endV);
        DrawUtil.addVertexWithUV(endX, endY, z, endU, endV);
        DrawUtil.addVertexWithUV(endX, startY, z, endU, startV);
        DrawUtil.addVertexWithUV(startX, startY, z, startU, startV);
        DrawUtil.draw();
    }

    public static void drawEntity(double x, double y, double heading, TextureImpl texture, float scale, double rotation) {
        DrawUtil.drawEntity(x, y, heading, texture, 1.0f, scale, rotation);
    }

    public static void drawEntity(double x, double y, double heading, TextureImpl texture, float alpha, float scale, double rotation) {
        double width = (float)texture.getWidth() * scale;
        double height = (float)texture.getHeight() * scale;
        double drawX = x - width / 2.0;
        double drawY = y - height / 2.0;
        DrawUtil.drawImage(texture, alpha, drawX, drawY, false, scale, heading);
    }

    public static void drawColoredEntity(double x, double y, TextureImpl texture, int color, float alpha, float scale, double rotation) {
        double width = (float)texture.getWidth() * scale;
        double height = (float)texture.getHeight() * scale;
        double drawX = x - width / 2.0;
        double drawY = y - height / 2.0;
        DrawUtil.drawColoredImage(texture, color, alpha, drawX, drawY, scale, rotation);
    }

    public static void sizeDisplay(double width, double height) {
        GlStateManager.func_179086_m((int)256);
        GlStateManager.func_179128_n((int)5889);
        GlStateManager.func_179096_D();
        GlStateManager.func_179130_a((double)0.0, (double)width, (double)height, (double)0.0, (double)100.0, (double)3000.0);
        GlStateManager.func_179128_n((int)5888);
        GlStateManager.func_179096_D();
        GlStateManager.func_179109_b((float)0.0f, (float)0.0f, (float)-2000.0f);
    }

    public static void draw() {
        tessellator.func_78381_a();
    }

    public static void startDrawingQuads(boolean useColor) {
        if (useColor) {
            worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181709_i);
        } else {
            worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        }
    }

    public static void addVertexWithUV(double x, double y, double z, double u, double v) {
        worldrenderer.func_181662_b(x, y, z).func_187315_a(u, v).func_181675_d();
    }

    public static void addVertex(double x, double y, double z, int[] rgba) {
        worldrenderer.func_181662_b(x, y, z).func_187315_a(1.0, 1.0).func_181669_b(rgba[0], rgba[1], rgba[2], rgba[3]).func_181675_d();
    }

    public static void addVertexWithUV(double x, double y, double z, double u, double v, int[] rgba) {
        worldrenderer.func_181662_b(x, y, z).func_187315_a(u, v).func_181669_b(rgba[0], rgba[1], rgba[2], rgba[3]).func_181675_d();
    }

    public static enum VAlign {
        Above,
        Middle,
        Below;

    }

    public static enum HAlign {
        Left,
        Center,
        Right;

    }
}

