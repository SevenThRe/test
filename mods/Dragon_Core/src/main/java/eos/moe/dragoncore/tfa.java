/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.client.event.GuiScreenEvent$DrawScreenEvent$Post
 *  net.minecraftforge.client.event.RenderTooltipEvent$Pre
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.dj;
import eos.moe.dragoncore.eba;
import eos.moe.dragoncore.hja;
import eos.moe.dragoncore.pf;
import eos.moe.dragoncore.qg;
import eos.moe.dragoncore.uha;
import eos.moe.dragoncore.ui;
import eos.moe.dragoncore.vk;
import eos.moe.dragoncore.wi;
import java.awt.geom.Point2D;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class tfa {
    private Minecraft z = Minecraft.getMinecraft();
    private ItemStack s = ItemStack.EMPTY;
    private int g;
    private int t;
    public List<String> r;
    public FontRenderer x;
    public ItemStack v;
    private int m;
    private int c;
    private int q;
    private int b;
    private int o;
    private int y;
    private int k;
    private int ALLATORIxDEMO;

    public tfa() {
        tfa a2;
    }

    @SubscribeEvent
    public void ALLATORIxDEMO(RenderTooltipEvent.Pre a2) {
        tfa a3;
        if (a3.z.player == null) {
            return;
        }
        a3.s = a2.getStack();
        a3.g = 0;
        a3.t = 0;
        ui a4 = hja.c.ALLATORIxDEMO(a2.getStack());
        if (a4 != null) {
            a3.x = a2.getFontRenderer();
            a3.r = a2.getLines();
            a3.g = a2.getX();
            a3.t = a2.getY();
            a2.setCanceled(true);
            return;
        }
        eba a5 = hja.c.ALLATORIxDEMO(a2.getStack());
        if (a5 != null) {
            a2.setCanceled(true);
            GlStateManager.pushMatrix();
            a3.ALLATORIxDEMO(a5, a2.getStack(), a2.getLines(), a2.getX(), a2.getY(), a2.getScreenWidth(), a2.getScreenHeight(), -1, a2.getFontRenderer());
            GlStateManager.popMatrix();
        }
    }

    @SubscribeEvent
    public void ALLATORIxDEMO(GuiScreenEvent.DrawScreenEvent.Post a2) {
        tfa a3;
        ItemStack a4 = a3.s;
        a3.s = ItemStack.EMPTY;
        wi.b.ALLATORIxDEMO("mouse", a4);
        ui a5 = hja.c.ALLATORIxDEMO(a4);
        if (a5 != null) {
            a3.q = a3.c(a3.r);
            a3.b = a3.ALLATORIxDEMO(a3.r);
            a3.o = a3.c(dj.f(a4));
            a3.y = a3.ALLATORIxDEMO(dj.f(a4));
            List<String> a6 = dj.c(a4);
            a5.getMoLangRuntime().ALLATORIxDEMO.ALLATORIxDEMO("x", pf.ALLATORIxDEMO(a3.g));
            a5.getMoLangRuntime().ALLATORIxDEMO.ALLATORIxDEMO("y", pf.ALLATORIxDEMO(a3.t));
            a5.getMoLangRuntime().ALLATORIxDEMO.ALLATORIxDEMO("w", pf.ALLATORIxDEMO(a3.q));
            a5.getMoLangRuntime().ALLATORIxDEMO.ALLATORIxDEMO("h", pf.ALLATORIxDEMO(a3.b));
            a5.getMoLangRuntime().ALLATORIxDEMO.ALLATORIxDEMO("iw", pf.ALLATORIxDEMO(a3.o));
            a5.getMoLangRuntime().ALLATORIxDEMO.ALLATORIxDEMO("ih", pf.ALLATORIxDEMO(a3.y));
            a5.getMoLangRuntime().ALLATORIxDEMO.ALLATORIxDEMO("lines", new qg(a3.r, false));
            a5.getMoLangRuntime().ALLATORIxDEMO.ALLATORIxDEMO("lores", new qg(a6, false));
            if (!a5.isLoaded()) {
                a5.open();
            }
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)0.0f, (float)0.0f, (float)200.0f);
            a5.drawScreen(a3.g, a3.t, 0.0f);
            GlStateManager.popMatrix();
            return;
        }
    }

    /*
     * WARNING - void declaration
     */
    public void ALLATORIxDEMO(eba a2, ItemStack a3, List<String> a4, int a5, int a6, int a7, int a8, int a9, FontRenderer a10) {
        void var15_19;
        tfa a11;
        GlStateManager.disableRescaleNormal();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        a11.x = a10;
        a11.v = a3;
        a11.r = a4;
        a11.m = a5;
        a11.c = a6;
        a11.q = a11.c(a4);
        a11.b = a11.ALLATORIxDEMO(a4);
        a11.o = a11.c(dj.f(a3));
        a11.y = a11.ALLATORIxDEMO(dj.f(a3));
        a11.k = a7;
        a11.ALLATORIxDEMO = a8;
        a2.ALLATORIxDEMO(a11);
        double a12 = a2.c();
        double a13 = a2.ALLATORIxDEMO();
        if ((double)a11.m + a12 > (double)a7) {
            GlStateManager.translate((double)(-a2.f()), (double)0.0, (double)0.0);
        }
        if ((double)a11.c + a13 > (double)a8) {
            GlStateManager.translate((double)0.0, (double)(-((double)a11.c + a13 - (double)a8)), (double)0.0);
        }
        for (uha uha2 : a2.b) {
            uha2.ALLATORIxDEMO();
        }
        for (int a15 = 0; a15 < a4.size(); ++a15) {
            Point2D.Float float_ = a2.c(a15);
            a10.drawStringWithShadow(a4.get(a15), float_.x, float_.y, -1);
        }
        List<String> a16 = dj.c(a11.v);
        boolean bl2 = false;
        while (var15_19 < a16.size()) {
            Point2D.Float a18 = a2.ALLATORIxDEMO((int)var15_19);
            a10.drawStringWithShadow(a16.get((int)var15_19), a18.x, a18.y, -1);
            ++var15_19;
        }
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enableRescaleNormal();
    }

    public double ALLATORIxDEMO(String a2) {
        tfa a3;
        a2 = a2.replace("sw", String.valueOf(a3.k)).replace("sh", String.valueOf(a3.ALLATORIxDEMO)).replace("iw", String.valueOf(a3.o)).replace("ih", String.valueOf(a3.y)).replace("w", String.valueOf(a3.q)).replace("h", String.valueOf(a3.b)).replace("x", String.valueOf(a3.m)).replace("y", String.valueOf(a3.c));
        return vk.f(a2);
    }

    public int c(List<String> a2) {
        int a3 = 0;
        for (String a4 : a2) {
            tfa a5;
            int a6 = a5.x.getStringWidth(a4);
            if (a6 <= a3) continue;
            a3 = a6;
        }
        return a3;
    }

    public int ALLATORIxDEMO(List<String> a2) {
        int a3 = 8;
        if (a2.size() > 1) {
            a3 += (a2.size() - 1) * 10 + 2;
        }
        return a3;
    }

    public static void ALLATORIxDEMO(int a2, int a3, int a4, int a5, int a6, int a7, int a8) {
        float a9 = (float)(a7 >> 24 & 0xFF) / 255.0f;
        float a10 = (float)(a7 >> 16 & 0xFF) / 255.0f;
        float a11 = (float)(a7 >> 8 & 0xFF) / 255.0f;
        float a12 = (float)(a7 & 0xFF) / 255.0f;
        float a13 = (float)(a8 >> 24 & 0xFF) / 255.0f;
        float a14 = (float)(a8 >> 16 & 0xFF) / 255.0f;
        float a15 = (float)(a8 >> 8 & 0xFF) / 255.0f;
        float a16 = (float)(a8 & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel((int)7425);
        Tessellator a17 = Tessellator.getInstance();
        BufferBuilder a18 = a17.getBuffer();
        a18.begin(7, DefaultVertexFormats.POSITION_COLOR);
        a18.pos((double)a5, (double)a4, (double)a2).color(a10, a11, a12, a9).endVertex();
        a18.pos((double)a3, (double)a4, (double)a2).color(a10, a11, a12, a9).endVertex();
        a18.pos((double)a3, (double)a6, (double)a2).color(a14, a15, a16, a13).endVertex();
        a18.pos((double)a5, (double)a6, (double)a2).color(a14, a15, a16, a13).endVertex();
        a17.draw();
        GlStateManager.shadeModel((int)7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
}

