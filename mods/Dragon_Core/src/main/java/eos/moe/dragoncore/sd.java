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
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)ALLATORIxDEMO);
        float a9 = (float)(1.0 / a7);
        float a10 = (float)(1.0 / a8);
        Tessellator a11 = Tessellator.func_178181_a();
        BufferBuilder a12 = a11.func_178180_c();
        a12.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        sd.ALLATORIxDEMO(a12, a2, 0.0, a6, 0.0).func_187315_a(a3 * (double)a9, (a4 + a6) * (double)a10).func_181675_d();
        sd.ALLATORIxDEMO(a12, a2, a5, a6, 0.0).func_187315_a((a3 + a5) * (double)a9, (a4 + a6) * (double)a10).func_181675_d();
        sd.ALLATORIxDEMO(a12, a2, a5, 0.0, 0.0).func_187315_a((a3 + a5) * (double)a9, a4 * (double)a10).func_181675_d();
        sd.ALLATORIxDEMO(a12, a2, 0.0, 0.0, 0.0).func_187315_a(a3 * (double)a9, a4 * (double)a10).func_181675_d();
        a11.func_78381_a();
    }

    public static BufferBuilder ALLATORIxDEMO(BufferBuilder a2, nk a3, double a4, double a5, double a6) {
        ll a7 = new ll((float)a4, (float)a5, (float)a6, 1.0f);
        a7.ALLATORIxDEMO(a3);
        a2.func_181662_b((double)a7.x(), (double)a7.f(), (double)a7.c());
        return a2;
    }

    public static void ALLATORIxDEMO(double a2, double a3, double a4, double a5, double a6, double a7, double a8, double a9) {
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)ALLATORIxDEMO);
        float a10 = (float)(1.0 / a8);
        float a11 = (float)(1.0 / a9);
        Tessellator a12 = Tessellator.func_178181_a();
        BufferBuilder a13 = a12.func_178180_c();
        a13.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        a13.func_181662_b(a2, a3 + a7, 0.0).func_187315_a(a4 * (double)a10, (a5 + a7) * (double)a11).func_181675_d();
        a13.func_181662_b(a2 + a6, a3 + a7, 0.0).func_187315_a((a4 + a6) * (double)a10, (a5 + a7) * (double)a11).func_181675_d();
        a13.func_181662_b(a2 + a6, a3, 0.0).func_187315_a((a4 + a6) * (double)a10, a5 * (double)a11).func_181675_d();
        a13.func_181662_b(a2, a3, 0.0).func_187315_a(a4 * (double)a10, a5 * (double)a11).func_181675_d();
        a12.func_78381_a();
    }

    public static void ALLATORIxDEMO(Color a2, double a3, double a4, double a5, double a6, double a7, double a8, double a9, double a10) {
        if (a2 == null) {
            a2 = Color.WHITE;
        }
        GlStateManager.func_179131_c((float)((float)a2.getRed() / 255.0f), (float)((float)a2.getGreen() / 255.0f), (float)((float)a2.getBlue() / 255.0f), (float)((float)a2.getAlpha() / 255.0f * ALLATORIxDEMO));
        float a11 = (float)(1.0 / a9);
        float a12 = (float)(1.0 / a10);
        Tessellator a13 = Tessellator.func_178181_a();
        BufferBuilder a14 = a13.func_178180_c();
        a14.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        a14.func_181662_b(a3, a4 + a8, 0.0).func_187315_a(a5 * (double)a11, (a6 + a8) * (double)a12).func_181675_d();
        a14.func_181662_b(a3 + a7, a4 + a8, 0.0).func_187315_a((a5 + a7) * (double)a11, (a6 + a8) * (double)a12).func_181675_d();
        a14.func_181662_b(a3 + a7, a4, 0.0).func_187315_a((a5 + a7) * (double)a11, a6 * (double)a12).func_181675_d();
        a14.func_181662_b(a3, a4, 0.0).func_187315_a(a5 * (double)a11, a6 * (double)a12).func_181675_d();
        a13.func_78381_a();
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
        Tessellator a11 = Tessellator.func_178181_a();
        BufferBuilder a12 = a11.func_178180_c();
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_187428_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.func_179131_c((float)a8, (float)a9, (float)a10, (float)a7);
        a12.func_181668_a(7, DefaultVertexFormats.field_181705_e);
        a12.func_181662_b(a2, a3 + a5, 0.0).func_181675_d();
        a12.func_181662_b(a2 + a4, a3 + a5, 0.0).func_181675_d();
        a12.func_181662_b(a2 + a4, a3, 0.0).func_181675_d();
        a12.func_181662_b(a2, a3, 0.0).func_181675_d();
        a11.func_78381_a();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
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
        ScaledResolution a7 = new ScaledResolution(Minecraft.func_71410_x());
        int a8 = a7.func_78325_e();
        int a9 = a7.func_78328_b() - a6;
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
            GlStateManager.func_179101_C();
            RenderHelper.func_74518_a();
            GlStateManager.func_179140_f();
            GlStateManager.func_179097_i();
            int a8 = 0;
            for (String a9 : textLines) {
                a6 = font.func_78256_a(a9);
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
                    List a14 = font.func_78271_c(a13, a8);
                    if (a4 == 0) {
                        a11 = a14.size();
                    }
                    for (String a32 : a14) {
                        a2 = font.func_78256_a(a32);
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
            GlStateManager.func_179145_e();
            GlStateManager.func_179126_j();
            RenderHelper.func_74519_b();
            GlStateManager.func_179091_B();
        }
    }

    public static void ALLATORIxDEMO(@Nullable EntityLivingBase p_184391_1_, ItemStack p_184391_2_, int p_184391_3_, int p_184391_4_) {
        RenderItem a2 = Minecraft.func_71410_x().func_175599_af();
        if (!p_184391_2_.func_190926_b()) {
            a2.field_77023_b += 50.0f;
            try {
                sd.ALLATORIxDEMO(p_184391_2_, p_184391_3_, p_184391_4_, a2.func_184393_a(p_184391_2_, null, p_184391_1_));
            }
            catch (Throwable a3) {
                CrashReport a4 = CrashReport.func_85055_a((Throwable)a3, (String)"Rendering item");
                CrashReportCategory a5 = a4.func_85058_a("Item being rendered");
                a5.func_189529_a("Item Type", (ICrashReportDetail)new io(p_184391_2_));
                a5.func_189529_a("Registry Name", () -> String.valueOf(p_184391_2_.func_77973_b().getRegistryName()));
                a5.func_189529_a("Item Aux", (ICrashReportDetail)new tj(p_184391_2_));
                a5.func_189529_a("Item NBT", (ICrashReportDetail)new lm(p_184391_2_));
                a5.func_189529_a("Item Foil", (ICrashReportDetail)new rm(p_184391_2_));
                throw new ReportedException(a4);
            }
            a2.field_77023_b -= 50.0f;
        }
    }

    public static void ALLATORIxDEMO(ItemStack a2, int a3, int a4, IBakedModel a5) {
        RenderItem a6 = Minecraft.func_71410_x().func_175599_af();
        TextureManager a7 = Minecraft.func_71410_x().func_110434_K();
        GlStateManager.func_179094_E();
        a7.func_110577_a(TextureMap.field_110575_b);
        a7.func_110581_b(TextureMap.field_110575_b).func_174936_b(false, false);
        GlStateManager.func_179091_B();
        GlStateManager.func_179141_d();
        GlStateManager.func_179092_a((int)516, (float)0.1f);
        GlStateManager.func_179147_l();
        GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        sd.ALLATORIxDEMO(a3, a4, a5.func_177556_c());
        a5 = ForgeHooksClient.handleCameraTransforms((IBakedModel)a5, (ItemCameraTransforms.TransformType)ItemCameraTransforms.TransformType.GUI, (boolean)false);
        sd.renderItem(a2, a5);
        GlStateManager.func_179118_c();
        GlStateManager.func_179101_C();
        GlStateManager.func_179140_f();
        GlStateManager.func_179121_F();
        a7.func_110577_a(TextureMap.field_110575_b);
        a7.func_110581_b(TextureMap.field_110575_b).func_174935_a();
    }

    public static void renderItem(ItemStack a2, IBakedModel a3) {
        if (!a2.func_190926_b()) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179109_b((float)-0.5f, (float)-0.5f, (float)-0.5f);
            if (a3.func_188618_c()) {
                GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GlStateManager.func_179091_B();
                a2.func_77973_b().getTileEntityItemStackRenderer().func_179022_a(a2);
            } else {
                int a4 = 0xFFFFFF | (int)(Math.min(1.0f, Math.max(0.0f, ALLATORIxDEMO)) * 255.0f) << 24;
                sd.ALLATORIxDEMO(a3, a4, a2);
            }
            GlStateManager.func_179121_F();
        }
    }

    private static /* synthetic */ void ALLATORIxDEMO(IBakedModel a2, int a3, ItemStack a4) {
        Tessellator a5 = Tessellator.func_178181_a();
        BufferBuilder a6 = a5.func_178180_c();
        a6.func_181668_a(7, DefaultVertexFormats.field_176599_b);
        for (EnumFacing a7 : EnumFacing.values()) {
            sd.ALLATORIxDEMO(a6, a2.func_188616_a(null, a7, 0L), a3, a4);
        }
        sd.ALLATORIxDEMO(a6, a2.func_188616_a(null, null, 0L), a3, a4);
        a5.func_78381_a();
    }

    private static /* synthetic */ void ALLATORIxDEMO(BufferBuilder a2, List<BakedQuad> a3, int a4, ItemStack a5) {
        boolean a6 = a4 == -1 && !a5.func_190926_b();
        int a7 = a3.size();
        for (int a8 = 0; a8 < a7; ++a8) {
            BakedQuad a9 = a3.get(a8);
            int a10 = a4;
            if (a6 && a9.func_178212_b()) {
                a10 = Minecraft.func_71410_x().getItemColors().func_186728_a(a5, a9.func_178211_c());
                if (EntityRenderer.field_78517_a) {
                    a10 = TextureUtil.func_177054_c((int)a10);
                }
                a10 |= 0xFF000000;
            }
            LightUtil.renderQuadColor((BufferBuilder)a2, (BakedQuad)a9, (int)a10);
        }
    }

    private static /* synthetic */ void ALLATORIxDEMO(int a2, int a3, boolean a4) {
        RenderItem a5 = Minecraft.func_71410_x().func_175599_af();
        GlStateManager.func_179109_b((float)a2, (float)a3, (float)(100.0f + a5.field_77023_b));
        GlStateManager.func_179109_b((float)8.0f, (float)8.0f, (float)0.0f);
        GlStateManager.func_179152_a((float)1.0f, (float)-1.0f, (float)1.0f);
        GlStateManager.func_179152_a((float)16.0f, (float)16.0f, (float)16.0f);
        if (a4) {
            GlStateManager.func_179145_e();
        } else {
            GlStateManager.func_179140_f();
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

