/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.RenderItem
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.block.model.BakedQuad
 *  net.minecraft.client.renderer.block.model.IBakedModel
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.crash.CrashReport
 *  net.minecraft.crash.CrashReportCategory
 *  net.minecraft.crash.ICrashReportDetail
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.ReportedException
 *  net.minecraftforge.client.ForgeHooksClient
 *  net.minecraftforge.client.event.RenderTooltipEvent$Color
 *  net.minecraftforge.client.event.RenderTooltipEvent$PostBackground
 *  net.minecraftforge.client.event.RenderTooltipEvent$PostText
 *  net.minecraftforge.client.event.RenderTooltipEvent$Pre
 *  net.minecraftforge.client.model.pipeline.LightUtil
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.client.config.GuiUtils
 *  net.minecraftforge.fml.common.eventhandler.Event
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.io;
import eos.moe.dragoncore.ll;
import eos.moe.dragoncore.lm;
import eos.moe.dragoncore.nk;
import eos.moe.dragoncore.ol;
import eos.moe.dragoncore.rm;
import eos.moe.dragoncore.sj;
import eos.moe.dragoncore.tj;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.ICrashReportDetail;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ReportedException;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.client.model.pipeline.LightUtil;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.lwjgl.opengl.GL11;

public class sd {
    private static float ALLATORIxDEMO;

    public sd() {
        sd a2;
    }

    public static float ALLATORIxDEMO() {
        return ALLATORIxDEMO;
    }

    public static void ALLATORIxDEMO(float a2) {
        ALLATORIxDEMO = Math.min(1.0f, Math.max(0.0f, a2));
    }

    public static void ALLATORIxDEMO(double a2, double a3, double a4, double a5) {
        sd.ALLATORIxDEMO(a2, a3, 0.0, 0.0, a4, a5, a4, a5);
    }

    public static void ALLATORIxDEMO(nk a2, double a3, double a4, double a5, double a6, double a7, double a8) {
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)ALLATORIxDEMO);
        float a9 = (float)(1.0 / a7);
        float a10 = (float)(1.0 / a8);
        Tessellator a11 = Tessellator.getInstance();
        BufferBuilder a12 = a11.getBuffer();
        a12.begin(7, DefaultVertexFormats.POSITION_TEX);
        sd.ALLATORIxDEMO(a12, a2, 0.0, a6, 0.0).tex(a3 * (double)a9, (a4 + a6) * (double)a10).endVertex();
        sd.ALLATORIxDEMO(a12, a2, a5, a6, 0.0).tex((a3 + a5) * (double)a9, (a4 + a6) * (double)a10).endVertex();
        sd.ALLATORIxDEMO(a12, a2, a5, 0.0, 0.0).tex((a3 + a5) * (double)a9, a4 * (double)a10).endVertex();
        sd.ALLATORIxDEMO(a12, a2, 0.0, 0.0, 0.0).tex(a3 * (double)a9, a4 * (double)a10).endVertex();
        a11.draw();
    }

    public static BufferBuilder ALLATORIxDEMO(BufferBuilder a2, nk a3, double a4, double a5, double a6) {
        ll a7 = new ll((float)a4, (float)a5, (float)a6, 1.0f);
        a7.ALLATORIxDEMO(a3);
        a2.pos((double)a7.x(), (double)a7.f(), (double)a7.c());
        return a2;
    }

    public static void ALLATORIxDEMO(double a2, double a3, double a4, double a5, double a6, double a7, double a8, double a9) {
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)ALLATORIxDEMO);
        float a10 = (float)(1.0 / a8);
        float a11 = (float)(1.0 / a9);
        Tessellator a12 = Tessellator.getInstance();
        BufferBuilder a13 = a12.getBuffer();
        a13.begin(7, DefaultVertexFormats.POSITION_TEX);
        a13.pos(a2, a3 + a7, 0.0).tex(a4 * (double)a10, (a5 + a7) * (double)a11).endVertex();
        a13.pos(a2 + a6, a3 + a7, 0.0).tex((a4 + a6) * (double)a10, (a5 + a7) * (double)a11).endVertex();
        a13.pos(a2 + a6, a3, 0.0).tex((a4 + a6) * (double)a10, a5 * (double)a11).endVertex();
        a13.pos(a2, a3, 0.0).tex(a4 * (double)a10, a5 * (double)a11).endVertex();
        a12.draw();
    }

    public static void ALLATORIxDEMO(Color a2, double a3, double a4, double a5, double a6, double a7, double a8, double a9, double a10) {
        if (a2 == null) {
            a2 = Color.WHITE;
        }
        GlStateManager.color((float)((float)a2.getRed() / 255.0f), (float)((float)a2.getGreen() / 255.0f), (float)((float)a2.getBlue() / 255.0f), (float)((float)a2.getAlpha() / 255.0f * ALLATORIxDEMO));
        float a11 = (float)(1.0 / a9);
        float a12 = (float)(1.0 / a10);
        Tessellator a13 = Tessellator.getInstance();
        BufferBuilder a14 = a13.getBuffer();
        a14.begin(7, DefaultVertexFormats.POSITION_TEX);
        a14.pos(a3, a4 + a8, 0.0).tex(a5 * (double)a11, (a6 + a8) * (double)a12).endVertex();
        a14.pos(a3 + a7, a4 + a8, 0.0).tex((a5 + a7) * (double)a11, (a6 + a8) * (double)a12).endVertex();
        a14.pos(a3 + a7, a4, 0.0).tex((a5 + a7) * (double)a11, a6 * (double)a12).endVertex();
        a14.pos(a3, a4, 0.0).tex(a5 * (double)a11, a6 * (double)a12).endVertex();
        a13.draw();
    }

    public static void ALLATORIxDEMO(double a2, double a3, double a4, double a5, Color a6) {
        int a7 = (int)((float)a6.getAlpha() * ALLATORIxDEMO);
        int a8 = sd.ALLATORIxDEMO(new Color(a6.getRed(), a6.getGreen(), a6.getBlue(), a7));
        sd.ALLATORIxDEMO(a2, a3, a4, a5, a8);
    }

    public static void ALLATORIxDEMO(double a2, double a3, double a4, double a5, int a6) {
        float a7 = (float)(a6 >> 24 & 0xFF) / 255.0f * Math.min(1.0f, Math.max(0.0f, ALLATORIxDEMO));
        float a8 = (float)(a6 >> 16 & 0xFF) / 255.0f;
        float a9 = (float)(a6 >> 8 & 0xFF) / 255.0f;
        float a10 = (float)(a6 & 0xFF) / 255.0f;
        Tessellator a11 = Tessellator.getInstance();
        BufferBuilder a12 = a11.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.color((float)a8, (float)a9, (float)a10, (float)a7);
        a12.begin(7, DefaultVertexFormats.POSITION);
        a12.pos(a2, a3 + a5, 0.0).endVertex();
        a12.pos(a2 + a4, a3 + a5, 0.0).endVertex();
        a12.pos(a2 + a4, a3, 0.0).endVertex();
        a12.pos(a2, a3, 0.0).endVertex();
        a11.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void ALLATORIxDEMO(double a2, double a3, double a4, double a5, double a6, Color a7) {
        int a8 = sd.ALLATORIxDEMO(new Color(a7.getRed(), a7.getGreen(), a7.getBlue(), a7.getAlpha()));
        sd.ALLATORIxDEMO(a2, a3, a4, a6, a8);
        sd.ALLATORIxDEMO(a2, a3, a6, a5, a8);
        sd.ALLATORIxDEMO(a2 + a4 - a6, a3, a6, a5, a8);
        sd.ALLATORIxDEMO(a2, a3 + a5 - a6, a4, a6, a8);
    }

    public static void ALLATORIxDEMO(int a2, int a3, int a4, int a5) {
        int a6 = a3 + a5;
        ScaledResolution a7 = new ScaledResolution(Minecraft.getMinecraft());
        int a8 = a7.getScaleFactor();
        int a9 = a7.getScaledHeight() - a6;
        GL11.glScissor((int)(a2 * a8), (int)(a9 * a8), (int)(a4 * a8), (int)(a5 * a8));
    }

    public static int ALLATORIxDEMO(Color a2) {
        return a2.getBlue() | a2.getGreen() << 8 | a2.getRed() << 16 | a2.getAlpha() << 24;
    }

    public static void ALLATORIxDEMO(@Nonnull ItemStack stack, List<String> textLines, int mouseX, int mouseY, int screenWidth, int screenHeight, int maxTextWidth, FontRenderer font) {
        if (!textLines.isEmpty()) {
            int a2;
            String a32;
            int a4;
            int a5;
            int a6;
            RenderTooltipEvent.Pre a7 = new RenderTooltipEvent.Pre(stack, textLines, mouseX, mouseY, screenWidth, screenHeight, maxTextWidth, font);
            if (MinecraftForge.EVENT_BUS.post((Event)a7)) {
                return;
            }
            mouseX = a7.getX();
            mouseY = a7.getY();
            screenWidth = a7.getScreenWidth();
            screenHeight = a7.getScreenHeight();
            maxTextWidth = a7.getMaxWidth();
            font = a7.getFontRenderer();
            GlStateManager.disableRescaleNormal();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            int a8 = 0;
            for (String a9 : textLines) {
                a6 = font.getStringWidth(a9);
                if (a6 <= a8) continue;
                a8 = a6;
            }
            boolean a10 = false;
            int a11 = 1;
            a6 = mouseX + 12;
            if (a6 + a8 + 4 > screenWidth && (a6 = mouseX - 16 - a8) < 4) {
                a8 = mouseX > screenWidth / 2 ? mouseX - 12 - 8 : screenWidth - 16 - mouseX;
                a10 = true;
            }
            if (maxTextWidth > 0 && a8 > maxTextWidth) {
                a8 = maxTextWidth;
                a10 = true;
            }
            if (a10) {
                a5 = 0;
                ArrayList<String> a12 = new ArrayList<String>();
                for (a4 = 0; a4 < textLines.size(); ++a4) {
                    String a13 = textLines.get(a4);
                    List a14 = font.listFormattedStringToWidth(a13, a8);
                    if (a4 == 0) {
                        a11 = a14.size();
                    }
                    for (String a32 : a14) {
                        a2 = font.getStringWidth(a32);
                        if (a2 > a5) {
                            a5 = a2;
                        }
                        a12.add(a32);
                    }
                }
                a8 = a5;
                textLines = a12;
                a6 = mouseX > screenWidth / 2 ? mouseX - 16 - a8 : mouseX + 12;
            }
            a5 = mouseY - 12;
            int a15 = 8;
            if (textLines.size() > 1) {
                a15 += (textLines.size() - 1) * 10;
                if (textLines.size() > a11) {
                    a15 += 2;
                }
            }
            if (a5 < 4) {
                a5 = 4;
            } else if (a5 + a15 + 4 > screenHeight) {
                a5 = screenHeight - a15 - 4;
            }
            a4 = 300;
            int a16 = -267386864;
            int a17 = 0x505000FF;
            int a18 = (a17 & 0xFEFEFE) >> 1 | a17 & 0xFF000000;
            a32 = new RenderTooltipEvent.Color(stack, textLines, a6, a5, font, a16, a17, a18);
            MinecraftForge.EVENT_BUS.post((Event)a32);
            a16 = a32.getBackground();
            a17 = a32.getBorderStart();
            a18 = a32.getBorderEnd();
            GuiUtils.drawGradientRect((int)300, (int)(a6 - 3), (int)(a5 - 4), (int)(a6 + a8 + 3), (int)(a5 - 3), (int)a16, (int)a16);
            GuiUtils.drawGradientRect((int)300, (int)(a6 - 3), (int)(a5 + a15 + 3), (int)(a6 + a8 + 3), (int)(a5 + a15 + 4), (int)a16, (int)a16);
            GuiUtils.drawGradientRect((int)300, (int)(a6 - 3), (int)(a5 - 3), (int)(a6 + a8 + 3), (int)(a5 + a15 + 3), (int)a16, (int)a16);
            GuiUtils.drawGradientRect((int)300, (int)(a6 - 4), (int)(a5 - 3), (int)(a6 - 3), (int)(a5 + a15 + 3), (int)a16, (int)a16);
            GuiUtils.drawGradientRect((int)300, (int)(a6 + a8 + 3), (int)(a5 - 3), (int)(a6 + a8 + 4), (int)(a5 + a15 + 3), (int)a16, (int)a16);
            GuiUtils.drawGradientRect((int)300, (int)(a6 - 3), (int)(a5 - 3 + 1), (int)(a6 - 3 + 1), (int)(a5 + a15 + 3 - 1), (int)a17, (int)a18);
            GuiUtils.drawGradientRect((int)300, (int)(a6 + a8 + 2), (int)(a5 - 3 + 1), (int)(a6 + a8 + 3), (int)(a5 + a15 + 3 - 1), (int)a17, (int)a18);
            GuiUtils.drawGradientRect((int)300, (int)(a6 - 3), (int)(a5 - 3), (int)(a6 + a8 + 3), (int)(a5 - 3 + 1), (int)a17, (int)a17);
            GuiUtils.drawGradientRect((int)300, (int)(a6 - 3), (int)(a5 + a15 + 2), (int)(a6 + a8 + 3), (int)(a5 + a15 + 3), (int)a18, (int)a18);
            MinecraftForge.EVENT_BUS.post((Event)new RenderTooltipEvent.PostBackground(stack, textLines, a6, a5, font, a8, a15));
            a2 = a5;
            for (int a19 = 0; a19 < textLines.size(); ++a19) {
                String a20 = textLines.get(a19);
                ol.ALLATORIxDEMO(a20, null, a6, a5, false, true, false, -1);
                if (a19 + 1 == a11) {
                    a5 += 2;
                }
                a5 += 10;
            }
            MinecraftForge.EVENT_BUS.post((Event)new RenderTooltipEvent.PostText(stack, textLines, a6, a2, font, a8, a15));
            GlStateManager.enableLighting();
            GlStateManager.enableDepth();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.enableRescaleNormal();
        }
    }

    public static void ALLATORIxDEMO(@Nullable EntityLivingBase p_184391_1_, ItemStack p_184391_2_, int p_184391_3_, int p_184391_4_) {
        RenderItem a2 = Minecraft.getMinecraft().getRenderItem();
        if (!p_184391_2_.isEmpty()) {
            a2.zLevel += 50.0f;
            try {
                sd.ALLATORIxDEMO(p_184391_2_, p_184391_3_, p_184391_4_, a2.getItemModelWithOverrides(p_184391_2_, null, p_184391_1_));
            }
            catch (Throwable a3) {
                CrashReport a4 = CrashReport.makeCrashReport((Throwable)a3, (String)"Rendering item");
                CrashReportCategory a5 = a4.makeCategory("Item being rendered");
                a5.addDetail("Item Type", (ICrashReportDetail)new io(p_184391_2_));
                a5.addDetail("Registry Name", () -> String.valueOf(p_184391_2_.getItem().getRegistryName()));
                a5.addDetail("Item Aux", (ICrashReportDetail)new tj(p_184391_2_));
                a5.addDetail("Item NBT", (ICrashReportDetail)new lm(p_184391_2_));
                a5.addDetail("Item Foil", (ICrashReportDetail)new rm(p_184391_2_));
                throw new ReportedException(a4);
            }
            a2.zLevel -= 50.0f;
        }
    }

    public static void ALLATORIxDEMO(ItemStack a2, int a3, int a4, IBakedModel a5) {
        RenderItem a6 = Minecraft.getMinecraft().getRenderItem();
        TextureManager a7 = Minecraft.getMinecraft().getTextureManager();
        GlStateManager.pushMatrix();
        a7.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        a7.getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc((int)516, (float)0.1f);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        sd.ALLATORIxDEMO(a3, a4, a5.isGui3d());
        a5 = ForgeHooksClient.handleCameraTransforms((IBakedModel)a5, (ItemCameraTransforms.TransformType)ItemCameraTransforms.TransformType.GUI, (boolean)false);
        sd.renderItem(a2, a5);
        GlStateManager.disableAlpha();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableLighting();
        GlStateManager.popMatrix();
        a7.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        a7.getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
    }

    public static void renderItem(ItemStack a2, IBakedModel a3) {
        if (!a2.isEmpty()) {
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)-0.5f, (float)-0.5f, (float)-0.5f);
            if (a3.isBuiltInRenderer()) {
                GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GlStateManager.enableRescaleNormal();
                a2.getItem().getTileEntityItemStackRenderer().renderByItem(a2);
            } else {
                int a4 = 0xFFFFFF | (int)(Math.min(1.0f, Math.max(0.0f, ALLATORIxDEMO)) * 255.0f) << 24;
                sd.ALLATORIxDEMO(a3, a4, a2);
            }
            GlStateManager.popMatrix();
        }
    }

    private static /* synthetic */ void ALLATORIxDEMO(IBakedModel a2, int a3, ItemStack a4) {
        Tessellator a5 = Tessellator.getInstance();
        BufferBuilder a6 = a5.getBuffer();
        a6.begin(7, DefaultVertexFormats.ITEM);
        for (EnumFacing a7 : EnumFacing.values()) {
            sd.ALLATORIxDEMO(a6, a2.getQuads(null, a7, 0L), a3, a4);
        }
        sd.ALLATORIxDEMO(a6, a2.getQuads(null, null, 0L), a3, a4);
        a5.draw();
    }

    private static /* synthetic */ void ALLATORIxDEMO(BufferBuilder a2, List<BakedQuad> a3, int a4, ItemStack a5) {
        boolean a6 = a4 == -1 && !a5.isEmpty();
        int a7 = a3.size();
        for (int a8 = 0; a8 < a7; ++a8) {
            BakedQuad a9 = a3.get(a8);
            int a10 = a4;
            if (a6 && a9.hasTintIndex()) {
                a10 = Minecraft.getMinecraft().getItemColors().colorMultiplier(a5, a9.getTintIndex());
                if (EntityRenderer.anaglyphEnable) {
                    a10 = TextureUtil.anaglyphColor((int)a10);
                }
                a10 |= 0xFF000000;
            }
            LightUtil.renderQuadColor((BufferBuilder)a2, (BakedQuad)a9, (int)a10);
        }
    }

    private static /* synthetic */ void ALLATORIxDEMO(int a2, int a3, boolean a4) {
        RenderItem a5 = Minecraft.getMinecraft().getRenderItem();
        GlStateManager.translate((float)a2, (float)a3, (float)(100.0f + a5.zLevel));
        GlStateManager.translate((float)8.0f, (float)8.0f, (float)0.0f);
        GlStateManager.scale((float)1.0f, (float)-1.0f, (float)1.0f);
        GlStateManager.scale((float)16.0f, (float)16.0f, (float)16.0f);
        if (a4) {
            GlStateManager.enableLighting();
        } else {
            GlStateManager.disableLighting();
        }
    }

    public static Color ALLATORIxDEMO(String a2) {
        if (a2.equals("255,255,255,255")) {
            return Color.WHITE;
        }
        String[] a3 = a2.split(",");
        try {
            if (a3.length >= 4) {
                return new Color((int)sj.ALLATORIxDEMO(Double.parseDouble(a3[0]), 0.0, 255.0), (int)sj.ALLATORIxDEMO(Double.parseDouble(a3[1]), 0.0, 255.0), (int)sj.ALLATORIxDEMO(Double.parseDouble(a3[2]), 0.0, 255.0), (int)sj.ALLATORIxDEMO(Double.parseDouble(a3[3]), 0.0, 255.0));
            }
            if (a3.length >= 3) {
                return new Color((int)sj.ALLATORIxDEMO(Double.parseDouble(a3[0]), 0.0, 255.0), (int)sj.ALLATORIxDEMO(Double.parseDouble(a3[1]), 0.0, 255.0), (int)sj.ALLATORIxDEMO(Double.parseDouble(a3[2]), 0.0, 255.0));
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return null;
    }
}

