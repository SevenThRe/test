/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 */
package eos.moe.dragongps;

import eos.moe.dragongps.iiiiiiiiii_11;
import java.awt.Dimension;
import java.util.function.Consumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

/*
 * Renamed from eos.moe.dragongps.iiiiiIiIii
 */
public class iiiiiiiiii_20
extends GuiButton {
    private int iiIIIiIiII;
    private Consumer<iiiiiiiiii_20> IIiiIiiIII;
    private int iIIiIIiIii;
    private String iIIIIiiIII;
    private double IIiIiIIIiI;
    private double IIiiiiiIIi;

    public iiiiiiiiii_20(String IIiiiiiIIi, double IIiiiiiIIi2, double IIiiiiiIIi3, String IIiiiiiIIi4, Consumer<iiiiiiiiii_20> IIiiiiiIIi5) {
        super(0, (int)IIiiiiiIIi2, (int)IIiiiiiIIi3, 0, 0, IIiiiiiIIi4);
        iiiiiiiiii_20 IIiiiiiIIi6;
        IIiiiiiIIi6.iIIIIiiIII = IIiiiiiIIi;
        Dimension IIiiiiiIIi7 = iiiiiiiiii_11.IIIiiiIiii(IIiiiiiIIi);
        IIiiiiiIIi6.IIiiiiiIIi = IIiiiiiIIi2;
        IIiiiiiIIi6.IIiIiIIIiI = IIiiiiiIIi3;
        IIiiiiiIIi6.iIIiIIiIii = IIiiiiiIIi7.width;
        IIiiiiiIIi6.iiIIIiIiII = IIiiiiiIIi7.height;
        IIiiiiiIIi6.IIiiIiiIII = IIiiiiiIIi5;
    }

    public void drawButton(Minecraft IIiiiiiIIi, int IIiiiiiIIi2, int IIiiiiiIIi3, float IIiiiiiIIi4) {
        iiiiiiiiii_20 IIiiiiiIIi5;
        if (IIiiiiiIIi5.visible) {
            FontRenderer IIiiiiiIIi6 = IIiiiiiIIi.fontRenderer;
            iiiiiiiiii_11.IIIiiiIiii(IIiiiiiIIi5.iIIIIiiIII);
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            IIiiiiiIIi5.hovered = (double)IIiiiiiIIi2 >= IIiiiiiIIi5.IIiiiiiIIi && (double)IIiiiiiIIi3 >= IIiiiiiIIi5.IIiIiIIIiI && (double)IIiiiiiIIi2 < IIiiiiiIIi5.IIiiiiiIIi + (double)IIiiiiiIIi5.iIIiIIiIii && (double)IIiiiiiIIi3 < IIiiiiiIIi5.IIiIiIIIiI + (double)IIiiiiiIIi5.iiIIIiIiII;
            int IIiiiiiIIi7 = IIiiiiiIIi5.getHoverState(IIiiiiiIIi5.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            iiiiiiiiii_20.IIIiiiIiii(IIiiiiiIIi5.IIiiiiiIIi, IIiiiiiIIi5.IIiIiIIIiI, 0.0f, 0.0f, IIiiiiiIIi5.iIIiIIiIii, IIiiiiiIIi5.iiIIIiIiII, IIiiiiiIIi5.iIIiIIiIii, IIiiiiiIIi5.iiIIIiIiII);
            IIiiiiiIIi5.mouseDragged(IIiiiiiIIi, IIiiiiiIIi2, IIiiiiiIIi3);
            int IIiiiiiIIi8 = 0xE0E0E0;
            if (IIiiiiiIIi5.packedFGColour != 0) {
                IIiiiiiIIi8 = IIiiiiiIIi5.packedFGColour;
            } else if (!IIiiiiiIIi5.enabled) {
                IIiiiiiIIi8 = 0xA0A0A0;
            } else if (IIiiiiiIIi5.hovered) {
                IIiiiiiIIi8 = 0xFFFFA0;
            }
            IIiiiiiIIi6.drawStringWithShadow(IIiiiiiIIi5.displayString, (float)(IIiiiiiIIi5.IIiiiiiIIi + (double)((float)IIiiiiiIIi5.iIIiIIiIii / 2.0f) - (double)((float)IIiiiiiIIi6.getStringWidth(IIiiiiiIIi5.displayString) / 2.0f)), (float)(IIiiiiiIIi5.IIiIiIIIiI + (double)((float)(IIiiiiiIIi5.iiIIIiIiII - 8) / 2.0f)), IIiiiiiIIi8);
        }
    }

    public boolean mousePressed(Minecraft IIiiiiiIIi, int IIiiiiiIIi2, int IIiiiiiIIi3) {
        iiiiiiiiii_20 IIiiiiiIIi4;
        boolean IIiiiiiIIi5;
        boolean bl = IIiiiiiIIi5 = IIiiiiiIIi4.enabled && IIiiiiiIIi4.visible && (double)IIiiiiiIIi2 >= IIiiiiiIIi4.IIiiiiiIIi && (double)IIiiiiiIIi3 >= IIiiiiiIIi4.IIiIiIIIiI && (double)IIiiiiiIIi2 < IIiiiiiIIi4.IIiiiiiIIi + (double)IIiiiiiIIi4.iIIiIIiIii && (double)IIiiiiiIIi3 < IIiiiiiIIi4.IIiIiIIIiI + (double)IIiiiiiIIi4.iiIIIiIiII;
        if (IIiiiiiIIi5) {
            IIiiiiiIIi4.IIiiIiiIII.accept(IIiiiiiIIi4);
            return true;
        }
        return false;
    }

    public static void IIIiiiIiii(double IIiiiiiIIi, double IIiiiiiIIi2, float IIiiiiiIIi3, float IIiiiiiIIi4, int IIiiiiiIIi5, int IIiiiiiIIi6, float IIiiiiiIIi7, float IIiiiiiIIi8) {
        float IIiiiiiIIi9 = 1.0f / IIiiiiiIIi7;
        float IIiiiiiIIi10 = 1.0f / IIiiiiiIIi8;
        Tessellator IIiiiiiIIi11 = Tessellator.getInstance();
        BufferBuilder IIiiiiiIIi12 = IIiiiiiIIi11.getBuffer();
        IIiiiiiIIi12.begin(7, DefaultVertexFormats.POSITION_TEX);
        IIiiiiiIIi12.pos(IIiiiiiIIi, IIiiiiiIIi2 + (double)IIiiiiiIIi6, 0.0).tex((double)(IIiiiiiIIi3 * IIiiiiiIIi9), (double)((IIiiiiiIIi4 + (float)IIiiiiiIIi6) * IIiiiiiIIi10)).endVertex();
        IIiiiiiIIi12.pos(IIiiiiiIIi + (double)IIiiiiiIIi5, IIiiiiiIIi2 + (double)IIiiiiiIIi6, 0.0).tex((double)((IIiiiiiIIi3 + (float)IIiiiiiIIi5) * IIiiiiiIIi9), (double)((IIiiiiiIIi4 + (float)IIiiiiiIIi6) * IIiiiiiIIi10)).endVertex();
        IIiiiiiIIi12.pos(IIiiiiiIIi + (double)IIiiiiiIIi5, IIiiiiiIIi2, 0.0).tex((double)((IIiiiiiIIi3 + (float)IIiiiiiIIi5) * IIiiiiiIIi9), (double)(IIiiiiiIIi4 * IIiiiiiIIi10)).endVertex();
        IIiiiiiIIi12.pos(IIiiiiiIIi, IIiiiiiIIi2, 0.0).tex((double)(IIiiiiiIIi3 * IIiiiiiIIi9), (double)(IIiiiiiIIi4 * IIiiiiiIIi10)).endVertex();
        IIiiiiiIIi11.draw();
    }
}

