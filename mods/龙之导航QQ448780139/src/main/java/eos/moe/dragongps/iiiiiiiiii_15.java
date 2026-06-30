/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiChat
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraftforge.client.event.RenderGameOverlayEvent
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.dragongps;

import eos.moe.dragongps.iiiiiiiiii_0;
import eos.moe.dragongps.iiiiiiiiii_1;
import eos.moe.dragongps.iiiiiiiiii_11;
import eos.moe.dragongps.iiiiiiiiii_13;
import eos.moe.dragongps.iiiiiiiiii_19;
import eos.moe.dragongps.iiiiiiiiii_20;
import eos.moe.dragongps.iiiiiiiiii_6;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

/*
 * Renamed from eos.moe.dragongps.iIiIiiiiIi
 */
@Mod.EventBusSubscriber(modid="dragongps")
public class iiiiiiiiii_15 {
    private static int IIIIiiiIII;
    private static List<GuiButton> IIiIIiiIiI;
    private static Minecraft IiIIiiIIiI;
    private static boolean iIIIiIIIIi;
    private static Point iIiIIiIiiI;
    public static boolean iiIIIiIiII;
    private static boolean IIiiIiiIII;
    private static Point iIIiIIiIii;
    private static Rectangle iIIIIiiIII;
    private static boolean IIiIiIIIiI;
    private static Rectangle IIiiiiiIIi;

    private static /* synthetic */ void IiiiiiIIIi() {
        if (Mouse.isButtonDown((int)0)) {
            if (!IIiiIiiIII) {
                iiiiiiiiii_15.IIIiiiIiii(0);
            } else {
                iiiiiiiiii_15.IIIiiiIiii(-1);
            }
            IIiiIiiIII = true;
        } else if (IIiiIiiIII) {
            iiiiiiiiii_15.IIIiiiIiii(2);
            IIiiIiiIII = false;
        }
        if (Mouse.isButtonDown((int)1)) {
            if (!iIIIiIIIIi) {
                iiiiiiiiii_15.IIIiiiIiii(1);
            } else {
                iiiiiiiiii_15.IIIiiiIiii(-1);
            }
            iIIIiIIIIi = true;
        } else if (iIIIiIIIIi) {
            iiiiiiiiii_15.IIIiiiIiii(3);
            iIIIiIIIIi = false;
        }
    }

    private static /* synthetic */ int IIIiiiIiii(Color IIiiiiiIIi) {
        return IIiiiiiIIi.getBlue() | IIiiiiiIIi.getGreen() << 8 | IIiiiiiIIi.getRed() << 16 | IIiiiiiIIi.getAlpha() << 24;
    }

    private static /* synthetic */ void IIiiiIIiiI() {
        String IIiiiiiIIi = "\u00a76\u00a7l\u4efb\u52a1\u4fe1\u606f(\u70b9\u51fb\u663e\u793a)";
        int IIiiiiiIIi2 = iiiiiiiiii_15.IiIIiiIIiI.field_71466_p.func_78256_a(IIiiiiiIIi);
        int IIiiiiiIIi3 = iiiiiiiiii_0.iiIIIiIiII;
        int IIiiiiiIIi4 = iiiiiiiiii_0.iIIiIIiIii;
        iiiiiiiiii_15.IIIiiiIiii(0, IIiiiiiIIi3, IIiiiiiIIi4, IIiiiiiIIi3 + IIiiiiiIIi2 + 4, IIiiiiiIIi4 + 12, iiiiiiiiii_15.IIIiiiIiii(Color.BLACK), iiiiiiiiii_15.IIIiiiIiii(Color.BLACK));
        iiiiiiiiii_15.IiIIiiIIiI.field_71466_p.func_175065_a(IIiiiiiIIi, (float)(IIiiiiiIIi3 + 2), (float)IIiiiiiIIi4 + 1.5f, -1, false);
        iiiiiiiiii_15.IIiiiiiIIi.setBounds(IIiiiiiIIi3, IIiiiiiIIi4, IIiiiiiIIi2, 12);
    }

    private static /* synthetic */ void IiiiiiIIiI() {
        IIiIIiiIiI.clear();
        Rectangle IIiiiiiIIi = iiiiiiiiii_1.IIIiiiIiii();
        iiiiiiiiii_15.IIIiiiIiii();
        if (IIiIiIIIiI) {
            iiiiiiiiii_15.IIiiiIIiiI();
            iiiiiiiiii_15.IiiiiiIIIi();
            return;
        }
        int IIiiiiiIIi3 = iiiiiiiiii_0.iiIIIiIiII;
        int IIiiiiiIIi4 = iiiiiiiiii_0.iIIiIIiIii;
        int IIiiiiiIIi5 = 11;
        String IIiiiiiIIi6 = "\u00a76\u00a7l\u4efb\u52a1\u4fe1\u606f" + (iiiiiiiiii_6.IIiIiIIIiI.size() > 0 ? "" : "(\u5f53\u524d\u65e0\u4efb\u52a1)");
        float IIiiiiiIIi7 = iiiiiiiiii_15.IiIIiiIIiI.field_71466_p.func_78256_a(IIiiiiiIIi6);
        float IIiiiiiIIi8 = 0.0f;
        ArrayList<String> IIiiiiiIIi9 = new ArrayList<String>();
        IIiiiiiIIi9.add(IIiiiiiIIi6);
        for (iiiiiiiiii_19 IIiiiiiIIi10 : new ArrayList<iiiiiiiiii_19>(iiiiiiiiii_6.IIiIiIIIiI)) {
            IIiiiiiIIi9.add(IIiiiiiIIi10.iIIiiiIIiI() + "<-->" + IIiiiiiIIi10.IIIiiiIiii());
            IIiiiiiIIi7 = Math.max(IIiiiiiIIi7, (float)iiiiiiiiii_15.IiIIiiIIiI.field_71466_p.func_78256_a(IIiiiiiIIi10.IIIiiiIiii()) * 1.4f + (float)(iiiiiiiiii_15.IIIiiiIiii(IIiiiiiIIi10.iIIiiiIIiI()) ? iiiiiiiiii_11.IIIiiiIiii((String)"stop.png").width : iiiiiiiiii_11.IIIiiiIiii((String)"start.png").width));
            IIiiiiiIIi8 = (float)((double)IIiiiiiIIi8 + (double)IIiiiiiIIi5 * 1.4);
            for (String IIiiiiiIIi11 : IIiiiiiIIi10.IIIiiiIiii()) {
                IIiiiiiIIi9.add("\u25cf " + IIiiiiiIIi11);
                IIiiiiiIIi7 = Math.max(IIiiiiiIIi7, (float)iiiiiiiiii_15.IiIIiiIIiI.field_71466_p.func_78256_a("\u25cf " + IIiiiiiIIi11));
                IIiiiiiIIi8 += (float)IIiiiiiIIi5;
            }
        }
        IIiiiiiIIi7 = Math.max(IIiiiiiIIi7, (float)iiiiiiiiii_15.IiIIiiIIiI.field_71466_p.func_78256_a(IIiiiiiIIi6)) + 10.0f;
        float IIiiiiiIIi12 = IIiiiiiIIi8 > (float)iiiiiiiiii_0.IIiIiIIIiI ? (float)iiiiiiiiii_0.IIiIiIIIiI : IIiiiiiIIi8;
        iiiiiiiiii_15.IIIiiiIiii(0, IIiiiiiIIi3, IIiiiiiIIi4, (float)IIiiiiiIIi3 + IIiiiiiIIi7, (float)(IIiiiiiIIi4 + 12) + IIiiiiiIIi12, iiiiiiiiii_15.IIIiiiIiii(new Color(0, 0, 0, 40)), iiiiiiiiii_15.IIIiiiIiii(new Color(0, 0, 0, 40)));
        iiiiiiiiii_15.IIIiiiIiii(0, IIiiiiiIIi3, IIiiiiiIIi4, (float)IIiiiiiIIi3 + IIiiiiiIIi7, IIiiiiiIIi4 + 12, iiiiiiiiii_15.IIIiiiIiii(Color.BLACK), iiiiiiiiii_15.IIIiiiIiii(Color.BLACK));
        iiiiiiiiii_15.IiIIiiIIiI.field_71466_p.func_175065_a(IIiiiiiIIi6, (float)IIiiiiiIIi3 + (IIiiiiiIIi7 - (float)iiiiiiiiii_15.IiIIiiIIiI.field_71466_p.func_78256_a(IIiiiiiIIi6)) / 2.0f, (float)IIiiiiiIIi4 + 1.5f, -1, false);
        float IIiiiiiIIi13 = IIiiiiiIIi4 + 12;
        IIiiiiiIIi13 -= (float)IIIIiiiIII / ((float)iiiiiiiiii_0.IIiIiIIIiI * 0.9f) * (IIiiiiiIIi8 - (float)iiiiiiiiii_0.IIiIiIIIiI);
        GL11.glEnable((int)3089);
        iiiiiiiiii_13.IIIiiiIiii(IIiiiiiIIi3, IIiiiiiIIi4 + 12, IIiiiiiIIi3 + (int)IIiiiiiIIi7, IIiiiiiIIi4 + 12 + (int)IIiiiiiIIi12);
        for (int IIiiiiiIIi14 = 1; IIiiiiiIIi14 < IIiiiiiIIi9.size(); ++IIiiiiiIIi14) {
            iiiiiiiiii_20 IIiiiiiIIi15;
            String IIiiiiiIIi11;
            IIiiiiiIIi11 = (String)IIiiiiiIIi9.get(IIiiiiiIIi14);
            if (!IIiiiiiIIi11.contains("<-->")) {
                iiiiiiiiii_15.IiIIiiIIiI.field_71466_p.func_175065_a((String)IIiiiiiIIi9.get(IIiiiiiIIi14), (float)(IIiiiiiIIi3 + 2), IIiiiiiIIi13, -1, false);
                IIiiiiiIIi13 += (float)IIiiiiiIIi5;
                continue;
            }
            String IIiiiiiIIi16 = IIiiiiiIIi11.split("<-->")[0];
            IIiiiiiIIi11 = IIiiiiiIIi11.split("<-->")[1];
            GlStateManager.func_179094_E();
            GlStateManager.func_179109_b((float)(IIiiiiiIIi3 + 2), (float)(IIiiiiiIIi13 + 2.0f), (float)0.0f);
            GlStateManager.func_179139_a((double)1.4, (double)1.4, (double)0.0);
            iiiiiiiiii_15.IiIIiiIIiI.field_71466_p.func_175065_a(IIiiiiiIIi11, 0.0f, 0.0f, -1, false);
            GlStateManager.func_179121_F();
            if (iiiiiiiiii_15.IIIiiiIiii(IIiiiiiIIi16)) {
                IIiiiiiIIi15 = new iiiiiiiiii_20("stop.png", (double)(IIiiiiiIIi3 + 2) + (double)iiiiiiiiii_15.IiIIiiIIiI.field_71466_p.func_78256_a(IIiiiiiIIi11) * 1.4, IIiiiiiIIi13 + 1.0f, "\u53d6\u6d88\u5bfc\u822a", IIiiiiiIIi2 -> {
                    iiiiiiiiii_15.IiIIiiIIiI.field_71439_g.func_71165_d("/dgps remove " + IIiiiiiIIi16);
                    iiiiiiiiii_6.IIiiIiiIII.remove(IIiiiiiIIi16);
                    iiiiiiiiii_6.iIIiIIiIii.remove(IIiiiiiIIi16);
                });
                IIiIIiiIiI.add(IIiiiiiIIi15);
                IIiiiiiIIi15.func_191745_a(IiIIiiIIiI, IIiiiiiIIi.x, IIiiiiiIIi.y, 0.0f);
            } else {
                IIiiiiiIIi15 = new iiiiiiiiii_20("start.png", (double)(IIiiiiiIIi3 + 2) + (double)iiiiiiiiii_15.IiIIiiIIiI.field_71466_p.func_78256_a(IIiiiiiIIi11) * 1.4, IIiiiiiIIi13 + 1.0f, "\u5bfc\u822a", IIiiiiiIIi2 -> iiiiiiiiii_15.IiIIiiIIiI.field_71439_g.func_71165_d("/dgps to " + IIiiiiiIIi16));
                IIiiiiiIIi15.func_191745_a(IiIIiiIIiI, IIiiiiiIIi.x, IIiiiiiIIi.y, 0.0f);
                IIiIIiiIiI.add(IIiiiiiIIi15);
            }
            IIiiiiiIIi13 = (float)((double)IIiiiiiIIi13 + (double)IIiiiiiIIi5 * 1.4);
        }
        if (IIiiiiiIIi12 == (float)iiiiiiiiii_0.IIiIiIIIiI) {
            iIIIIiiIII.setBounds((int)((float)IIiiiiiIIi3 + IIiiiiiIIi7 - 5.0f), IIiiiiiIIi4 + 12 + IIIIiiiIII, 5, iiiiiiiiii_0.IIiIiIIIiI / 10);
            iiiiiiiiii_11.IIIiiiIiii("scrollbag.png");
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            iiiiiiiiii_20.func_146110_a((int)iiiiiiiiii_15.iIIIIiiIII.x, (int)(IIiiiiiIIi4 + 12), (float)0.0f, (float)0.0f, (int)iiiiiiiiii_15.iIIIIiiIII.width, (int)iiiiiiiiii_0.IIiIiIIIiI, (float)iiiiiiiiii_15.iIIIIiiIII.width, (float)iiiiiiiiii_0.IIiIiIIIiI);
            iiiiiiiiii_11.IIIiiiIiii("scrollbar.png");
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            iiiiiiiiii_20.func_146110_a((int)iiiiiiiiii_15.iIIIIiiIII.x, (int)iiiiiiiiii_15.iIIIIiiIII.y, (float)0.0f, (float)0.0f, (int)iiiiiiiiii_15.iIIIIiiIII.width, (int)iiiiiiiiii_15.iIIIIiiIII.height, (float)iiiiiiiiii_15.iIIIIiiIII.width, (float)iiiiiiiiii_15.iIIIIiiIII.height);
        } else {
            iIIIIiiIII.setBounds(0, 0, 0, 0);
        }
        GL11.glDisable((int)3089);
        iiiiiiiiii_15.IIiiiiiIIi.setBounds(IIiiiiiIIi3, IIiiiiiIIi4, (int)IIiiiiiIIi7, (int)IIiiiiiIIi12 + 12);
        iiiiiiiiii_15.IiiiiiIIIi();
    }

    public iiiiiiiiii_15() {
        iiiiiiiiii_15 IIiiiiiIIi;
    }

    private static /* synthetic */ boolean IIIiiiIiii(String IIiiiiiIIi) {
        return iiiiiiiiii_6.iIIiIIiIii.containsKey(IIiiiiiIIi) || iiiiiiiiii_6.IIiiIiiIII.containsKey(IIiiiiiIIi);
    }

    @SubscribeEvent
    public static void IIIiiiIiii(RenderGameOverlayEvent IIiiiiiIIi) {
        if (!iiIIIiIiII) {
            return;
        }
        if (IIiiiiiIIi.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            GL11.glDisable((int)3089);
            GlStateManager.func_179094_E();
            iiiiiiiiii_15.IiiiiiIIiI();
            GlStateManager.func_179121_F();
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
    }

    static {
        IiIIiiIIiI = Minecraft.func_71410_x();
        IIiIiIIIiI = false;
        iiIIIiIiII = false;
        IIiIIiiIiI = new ArrayList<GuiButton>();
        IIiiiiiIIi = new Rectangle();
        iIIIIiiIII = new Rectangle();
    }

    private static /* synthetic */ void iIIiiiIIiI() {
        if (IIIIiiiIII <= 0) {
            IIIIiiiIII = 0;
        }
        if ((double)IIIIiiiIII > (double)iiiiiiiiii_0.IIiIiIIIiI * 0.9) {
            IIIIiiiIII = (int)((double)iiiiiiiiii_0.IIiIiIIIiI * 0.9);
        }
    }

    private static /* synthetic */ void IIIiiiIiii(int IIiiiiiIIi, double IIiiiiiIIi2, double IIiiiiiIIi3, double IIiiiiiIIi4, double IIiiiiiIIi5, int IIiiiiiIIi6, int IIiiiiiIIi7) {
        float IIiiiiiIIi8 = (float)(IIiiiiiIIi6 >> 24 & 0xFF) / 255.0f;
        float IIiiiiiIIi9 = (float)(IIiiiiiIIi6 >> 16 & 0xFF) / 255.0f;
        float IIiiiiiIIi10 = (float)(IIiiiiiIIi6 >> 8 & 0xFF) / 255.0f;
        float IIiiiiiIIi11 = (float)(IIiiiiiIIi6 & 0xFF) / 255.0f;
        float IIiiiiiIIi12 = (float)(IIiiiiiIIi7 >> 24 & 0xFF) / 255.0f;
        float IIiiiiiIIi13 = (float)(IIiiiiiIIi7 >> 16 & 0xFF) / 255.0f;
        float IIiiiiiIIi14 = (float)(IIiiiiiIIi7 >> 8 & 0xFF) / 255.0f;
        float IIiiiiiIIi15 = (float)(IIiiiiiIIi7 & 0xFF) / 255.0f;
        GlStateManager.func_179090_x();
        GlStateManager.func_179147_l();
        GlStateManager.func_179118_c();
        GlStateManager.func_187428_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.func_179103_j((int)7425);
        Tessellator IIiiiiiIIi16 = Tessellator.func_178181_a();
        BufferBuilder IIiiiiiIIi17 = IIiiiiiIIi16.func_178180_c();
        IIiiiiiIIi17.func_181668_a(7, DefaultVertexFormats.field_181706_f);
        IIiiiiiIIi17.func_181662_b(IIiiiiiIIi4, IIiiiiiIIi3, (double)IIiiiiiIIi).func_181666_a(IIiiiiiIIi9, IIiiiiiIIi10, IIiiiiiIIi11, IIiiiiiIIi8).func_181675_d();
        IIiiiiiIIi17.func_181662_b(IIiiiiiIIi2, IIiiiiiIIi3, (double)IIiiiiiIIi).func_181666_a(IIiiiiiIIi9, IIiiiiiIIi10, IIiiiiiIIi11, IIiiiiiIIi8).func_181675_d();
        IIiiiiiIIi17.func_181662_b(IIiiiiiIIi2, IIiiiiiIIi5, (double)IIiiiiiIIi).func_181666_a(IIiiiiiIIi13, IIiiiiiIIi14, IIiiiiiIIi15, IIiiiiiIIi12).func_181675_d();
        IIiiiiiIIi17.func_181662_b(IIiiiiiIIi4, IIiiiiiIIi5, (double)IIiiiiiIIi).func_181666_a(IIiiiiiIIi13, IIiiiiiIIi14, IIiiiiiIIi15, IIiiiiiIIi12).func_181675_d();
        IIiiiiiIIi16.func_78381_a();
        GlStateManager.func_179103_j((int)7424);
        GlStateManager.func_179084_k();
        GlStateManager.func_179141_d();
        GlStateManager.func_179098_w();
    }

    private static /* synthetic */ void IIIiiiIiii() {
        Rectangle IIiiiiiIIi = iiiiiiiiii_1.IIIiiiIiii();
        if (iiiiiiiiii_0.iiIIIiIiII > IIiiiiiIIi.width - 10) {
            iiiiiiiiii_0.iiIIIiIiII = IIiiiiiIIi.width - 10;
            iiiiiiiiii_0.IIIiiiIiii();
        }
        if (iiiiiiiiii_0.iIIiIIiIii > IIiiiiiIIi.height - 20) {
            iiiiiiiiii_0.iIIiIIiIii = IIiiiiiIIi.height - 20;
            iiiiiiiiii_0.IIIiiiIiii();
        }
        if (iiiiiiiiii_0.iiIIIiIiII + iiiiiiiiii_15.IIiiiiiIIi.width < 10) {
            iiiiiiiiii_0.iiIIIiIiII = -iiiiiiiiii_15.IIiiiiiIIi.width + 10;
            iiiiiiiiii_0.IIIiiiIiii();
        }
        if (iiiiiiiiii_0.iIIiIIiIii < 0) {
            iiiiiiiiii_0.iIIiIIiIii = 0;
            iiiiiiiiii_0.IIIiiiIiii();
        }
    }

    public static void IIIiiiIiii(int IIiiiiiIIi) {
        int IIiiiiiIIi2;
        int IIiiiiiIIi3;
        if (!(Minecraft.func_71410_x().field_71462_r instanceof GuiChat)) {
            return;
        }
        GuiScreen IIiiiiiIIi4 = Minecraft.func_71410_x().field_71462_r;
        int IIiiiiiIIi5 = Mouse.getEventX() * IIiiiiiIIi4.field_146294_l / iiiiiiiiii_15.IiIIiiIIiI.field_71443_c;
        if (iiiiiiiiii_15.IIiiiiiIIi.contains(IIiiiiiIIi5, IIiiiiiIIi3 = IIiiiiiIIi4.field_146295_m - Mouse.getEventY() * IIiiiiiIIi4.field_146295_m / iiiiiiiiii_15.IiIIiiIIiI.field_71440_d - 1) && (IIiiiiiIIi2 = Mouse.getEventDWheel()) != 0) {
            if (IIiiiiiIIi2 > 1) {
                IIiiiiiIIi2 = 1;
            }
            if (IIiiiiiIIi2 < -1) {
                IIiiiiiIIi2 = -1;
            }
            IIIIiiiIII -= IIiiiiiIIi2 * 5;
            iiiiiiiiii_15.iIIiiiIIiI();
            return;
        }
        if (IIiiiiiIIi == 0) {
            for (GuiButton IIiiiiiIIi6 : IIiIIiiIiI) {
                if (!IIiiiiiIIi6.func_146116_c(IiIIiiIIiI, IIiiiiiIIi5, IIiiiiiIIi3)) continue;
                return;
            }
        }
        if (IIiiiiiIIi == 2 || IIiiiiiIIi == 3) {
            if (iIiIIiIiiI != null) {
                iIiIIiIiiI = null;
                iiiiiiiiii_0.IIIiiiIiii();
            }
            if (iIIiIIiIii != null) {
                iIIiIIiIii = null;
            }
        }
        if (IIiiiiiIIi == 0 && iIIIIiiIII.contains(IIiiiiiIIi5, IIiiiiiIIi3)) {
            iIIiIIiIii = new Point(IIiiiiiIIi5, IIiiiiiIIi3);
            iiiiiiiiii_15.iIIiIIiIii.y -= IIIIiiiIII;
            return;
        }
        Rectangle IIiiiiiIIi22 = new Rectangle(iiiiiiiiii_15.IIiiiiiIIi);
        IIiiiiiIIi22.height = iiiiiiiiii_15.IiIIiiIIiI.field_71466_p.field_78288_b + 2;
        if (IIiiiiiIIi == 0 && IIiiiiiIIi22.contains(IIiiiiiIIi5, IIiiiiiIIi3)) {
            IIiIiIIIiI = !IIiIiIIIiI;
            return;
        }
        if ((IIiiiiiIIi == 0 || IIiiiiiIIi == 1) && iiiiiiiiii_15.IIiiiiiIIi.contains(IIiiiiiIIi5, IIiiiiiIIi3)) {
            iIiIIiIiiI = new Point(IIiiiiiIIi5 - iiiiiiiiii_0.iiIIIiIiII, IIiiiiiIIi3 - iiiiiiiiii_0.iIIiIIiIii);
            return;
        }
        if (iIiIIiIiiI != null && IIiiiiiIIi == -1) {
            iiiiiiiiii_0.iiIIIiIiII = IIiiiiiIIi5 - iiiiiiiiii_15.iIiIIiIiiI.x;
            iiiiiiiiii_0.iIIiIIiIii = IIiiiiiIIi3 - iiiiiiiiii_15.iIiIIiIiiI.y;
        }
        if (iIIiIIiIii != null && IIiiiiiIIi == -1) {
            IIIIiiiIII = IIiiiiiIIi3 - iiiiiiiiii_15.iIIiIIiIii.y;
            iiiiiiiiii_15.iIIiiiIIiI();
        }
    }
}

