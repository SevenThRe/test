/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  eos.moe.dragongps.iIiIiIiIII
 *  eos.moe.dragongps.iIiiiIIiiI
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.dragongps;

import eos.moe.dragongps.iIiIiIiIII;
import eos.moe.dragongps.iIiiiIIiiI;
import eos.moe.dragongps.iiiiiiiiii_14;
import eos.moe.dragongps.iiiiiiiiii_16;
import eos.moe.dragongps.iiiiiiiiii_4;
import eos.moe.dragongps.iiiiiiiiii_7;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.lwjgl.opengl.GL11;

/*
 * Renamed from eos.moe.dragongps.iIiIIiIIII
 */
public class iiiiiiiiii_13 {
    public static double iIIIIiiIII = 0.0;
    public static BufferBuilder IIiIiIIIiI;
    public static Tessellator IIiiiiiIIi;

    public static void IIIiiiIiii(int IIiiiiiIIi, double IIiiiiiIIi2, double IIiiiiiIIi3, double IIiiiiiIIi4, double IIiiiiiIIi5) {
        iiiiiiiiii_13.IIIiiiIiii(IIiiiiiIIi, 0xFFFFFF, 1.0f, IIiiiiiIIi2, IIiiiiiIIi3, IIiiiiiIIi4, IIiiiiiIIi5, 0.0, 0.0, 1.0, 1.0, 0.0, false, true, 770, 771, false);
    }

    static {
        IIiiiiiIIi = Tessellator.func_178181_a();
        IIiIiIIIiI = IIiiiiiIIi.func_178180_c();
    }

    public static void IIIiiiIiii(String IIiiiiiIIi, double IIiiiiiIIi2, double IIiiiiiIIi3, iiiiiiiiii_14 IIiiiiiIIi4, iiiiiiiiii_16 IIiiiiiIIi5, Integer IIiiiiiIIi6, float IIiiiiiIIi7, int IIiiiiiIIi8, float IIiiiiiIIi9, double IIiiiiiIIi10, boolean IIiiiiiIIi11) {
        iiiiiiiiii_13.IIIiiiIiii(IIiiiiiIIi, IIiiiiiIIi2, IIiiiiiIIi3, IIiiiiiIIi4, IIiiiiiIIi5, IIiiiiiIIi6, IIiiiiiIIi7, (Integer)IIiiiiiIIi8, IIiiiiiIIi9, IIiiiiiIIi10, IIiiiiiIIi11, 0.0);
    }

    public static void IIIiiiIiii() {
        IIiiiiiIIi.func_78381_a();
    }

    public static void IIIiiiIiii(String IIiiiiiIIi, double IIiiiiiIIi2, double IIiiiiiIIi3, iiiiiiiiii_14 IIiiiiiIIi4, iiiiiiiiii_16 IIiiiiiIIi5, Integer IIiiiiiIIi6, float IIiiiiiIIi7, Integer IIiiiiiIIi8, float IIiiiiiIIi9, double IIiiiiiIIi10, boolean IIiiiiiIIi11, double IIiiiiiIIi12) {
        double IIiiiiiIIi13 = 0.0;
        double IIiiiiiIIi14 = 0.0;
        if (IIiiiiiIIi6 != null && IIiiiiiIIi7 > 0.0f) {
            FontRenderer IIiiiiiIIi15 = FMLClientHandler.instance().getClient().field_71466_p;
            IIiiiiiIIi13 = IIiiiiiIIi15.func_78256_a(IIiiiiiIIi);
            IIiiiiiIIi14 = iiiiiiiiii_13.IIIiiiIiii(IIiiiiiIIi15, IIiiiiiIIi11);
        }
        iiiiiiiiii_13.IIIiiiIiii(IIiiiiiIIi, IIiiiiiIIi2, IIiiiiiIIi3, IIiiiiiIIi4, IIiiiiiIIi5, IIiiiiiIIi6, IIiiiiiIIi7, IIiiiiiIIi13, IIiiiiiIIi14, IIiiiiiIIi8, IIiiiiiIIi9, IIiiiiiIIi10, IIiiiiiIIi11, IIiiiiiIIi12);
    }

    public static int IIIiiiIiii(FontRenderer IIiiiiiIIi, boolean IIiiiiiIIi2) {
        int IIiiiiiIIi3 = IIiiiiiIIi.func_82883_a() ? 0 : (IIiiiiiIIi2 ? 6 : 4);
        return IIiiiiiIIi.field_78288_b + IIiiiiiIIi3;
    }

    public static void IIIiiiIiii(double IIiiiiiIIi, double IIiiiiiIIi2, double IIiiiiiIIi3, double IIiiiiiIIi4, double IIiiiiiIIi5) {
        IIiIiIIIiI.func_181662_b(IIiiiiiIIi, IIiiiiiIIi2, IIiiiiiIIi3).func_187315_a(IIiiiiiIIi4, IIiiiiiIIi5).func_181675_d();
    }

    public static void IIIiiiIiii(int IIiiiiiIIi, int IIiiiiiIIi2, int IIiiiiiIIi3, int IIiiiiiIIi4) {
        int IIiiiiiIIi5 = IIiiiiiIIi3 - IIiiiiiIIi;
        int IIiiiiiIIi6 = IIiiiiiIIi4 - IIiiiiiIIi2;
        ScaledResolution IIiiiiiIIi7 = new ScaledResolution(Minecraft.func_71410_x());
        int IIiiiiiIIi8 = IIiiiiiIIi7.func_78325_e();
        int IIiiiiiIIi9 = IIiiiiiIIi7.func_78328_b() - IIiiiiiIIi4;
        GL11.glScissor((int)(IIiiiiiIIi * IIiiiiiIIi8), (int)(IIiiiiiIIi9 * IIiiiiiIIi8), (int)(IIiiiiiIIi5 * IIiiiiiIIi8), (int)(IIiiiiiIIi6 * IIiiiiiIIi8));
    }

    public static void IIIiiiIiii(String IIiiiiiIIi, double IIiiiiiIIi2, double IIiiiiiIIi3, Integer IIiiiiiIIi4, float IIiiiiiIIi5, Integer IIiiiiiIIi6, float IIiiiiiIIi7, double IIiiiiiIIi8, boolean IIiiiiiIIi9) {
        iiiiiiiiii_13.IIIiiiIiii(IIiiiiiIIi, IIiiiiiIIi2, IIiiiiiIIi3, iIiIiIiIII.IIiiiiiIIi, iIiiiIIiiI.IIiiiiiIIi, IIiiiiiIIi4, IIiiiiiIIi5, IIiiiiiIIi6, IIiiiiiIIi7, IIiiiiiIIi8, IIiiiiiIIi9, 0.0);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void IIIiiiIiii(String IIiiiiiIIi, double IIiiiiiIIi2, double IIiiiiiIIi3, iiiiiiiiii_14 IIiiiiiIIi4, iiiiiiiiii_16 IIiiiiiIIi5, Integer IIiiiiiIIi6, float IIiiiiiIIi7, double IIiiiiiIIi8, double IIiiiiiIIi9, Integer IIiiiiiIIi10, float IIiiiiiIIi11, double IIiiiiiIIi12, boolean IIiiiiiIIi13, double IIiiiiiIIi14) {
        int IIiiiiiIIi15;
        if (IIiiiiiIIi == null || IIiiiiiIIi.length() == 0) {
            return;
        }
        FontRenderer IIiiiiiIIi16 = FMLClientHandler.instance().getClient().field_71466_p;
        boolean IIiiiiiIIi17 = IIiiiiiIIi6 != null && IIiiiiiIIi7 > 0.0f;
        double IIiiiiiIIi18 = IIiiiiiIIi16.func_78256_a(IIiiiiiIIi);
        int n = IIiiiiiIIi15 = IIiiiiiIIi17 ? iiiiiiiiii_13.IIIiiiIiii(IIiiiiiIIi16, IIiiiiiIIi13) : IIiiiiiIIi16.field_78288_b;
        if (!IIiiiiiIIi17 && IIiiiiiIIi16.func_82883_a()) {
            --IIiiiiiIIi15;
        }
        GlStateManager.func_179094_E();
        try {
            if (IIiiiiiIIi12 != 1.0) {
                IIiiiiiIIi2 /= IIiiiiiIIi12;
                IIiiiiiIIi3 /= IIiiiiiIIi12;
                GlStateManager.func_179139_a((double)IIiiiiiIIi12, (double)IIiiiiiIIi12, (double)0.0);
            }
            float IIiiiiiIIi19 = (float)IIiiiiiIIi2;
            float IIiiiiiIIi20 = (float)IIiiiiiIIi3;
            double IIiiiiiIIi21 = IIiiiiiIIi2;
            double IIiiiiiIIi22 = IIiiiiiIIi3;
            switch (iiiiiiiiii_7.IIiiiiiIIi[IIiiiiiIIi4.ordinal()]) {
                case 1: {
                    IIiiiiiIIi19 = (float)(IIiiiiiIIi2 - IIiiiiiIIi18);
                    IIiiiiiIIi21 = IIiiiiiIIi19;
                    break;
                }
                case 2: {
                    IIiiiiiIIi19 = (float)(IIiiiiiIIi2 - IIiiiiiIIi18 / 2.0 + (IIiiiiiIIi12 > 1.0 ? 0.5 : 0.0));
                    IIiiiiiIIi21 = (float)(IIiiiiiIIi2 - Math.max(1.0, IIiiiiiIIi8) / 2.0 + (IIiiiiiIIi12 > 1.0 ? 0.5 : 0.0));
                    break;
                }
                case 3: {
                    IIiiiiiIIi19 = (float)IIiiiiiIIi2;
                    IIiiiiiIIi21 = (float)IIiiiiiIIi2;
                }
            }
            double IIiiiiiIIi23 = IIiiiiiIIi17 ? (double)(IIiiiiiIIi15 - IIiiiiiIIi16.field_78288_b) / 2.0 : 0.0;
            switch (iiiiiiiiii_7.IIiIiIIIiI[IIiiiiiIIi5.ordinal()]) {
                case 1: {
                    IIiiiiiIIi22 = IIiiiiiIIi3 - (double)IIiiiiiIIi15;
                    IIiiiiiIIi20 = (float)(IIiiiiiIIi22 + IIiiiiiIIi23 + (double)(!IIiiiiiIIi16.func_82883_a() ? 1 : 0));
                    break;
                }
                case 2: {
                    IIiiiiiIIi22 = IIiiiiiIIi3 - (double)(IIiiiiiIIi15 / 2) + (IIiiiiiIIi12 > 1.0 ? 0.5 : 0.0);
                    IIiiiiiIIi20 = (float)(IIiiiiiIIi22 + IIiiiiiIIi23);
                    break;
                }
                case 3: {
                    IIiiiiiIIi22 = IIiiiiiIIi3;
                    IIiiiiiIIi20 = (float)(IIiiiiiIIi22 + IIiiiiiIIi23);
                }
            }
            if (IIiiiiiIIi14 != 0.0) {
                GlStateManager.func_179137_b((double)IIiiiiiIIi2, (double)IIiiiiiIIi3, (double)0.0);
                GlStateManager.func_179114_b((float)((float)(-IIiiiiiIIi14)), (float)0.0f, (float)0.0f, (float)1.0f);
                GlStateManager.func_179137_b((double)(-IIiiiiiIIi2), (double)(-IIiiiiiIIi3), (double)0.0);
            }
            if (IIiiiiiIIi11 < 1.0f) {
                IIiiiiiIIi10 = iiiiiiiiii_4.iIIiiiIIiI((int)IIiiiiiIIi10, IIiiiiiIIi11);
            }
            GlStateManager.func_179137_b((double)((double)IIiiiiiIIi19 - Math.floor(IIiiiiiIIi19)), (double)((double)IIiiiiiIIi20 - Math.floor(IIiiiiiIIi20)), (double)0.0);
            IIiiiiiIIi16.func_175065_a(IIiiiiiIIi, IIiiiiiIIi19, IIiiiiiIIi20, IIiiiiiIIi10.intValue(), IIiiiiiIIi13);
        }
        finally {
            GlStateManager.func_179121_F();
        }
    }

    public static void IIIiiiIiii(double IIiiiiiIIi, double IIiiiiiIIi2, double IIiiiiiIIi3, double IIiiiiiIIi4, double IIiiiiiIIi5, int[] IIiiiiiIIi6) {
        IIiIiIIIiI.func_181662_b(IIiiiiiIIi, IIiiiiiIIi2, IIiiiiiIIi3).func_187315_a(IIiiiiiIIi4, IIiiiiiIIi5).func_181669_b(IIiiiiiIIi6[0], IIiiiiiIIi6[1], IIiiiiiIIi6[2], IIiiiiiIIi6[3]).func_181675_d();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void IIIiiiIiii(int IIiiiiiIIi, int IIiiiiiIIi2, float IIiiiiiIIi3, double IIiiiiiIIi4, double IIiiiiiIIi5, double IIiiiiiIIi6, double IIiiiiiIIi7, double IIiiiiiIIi8, double IIiiiiiIIi9, double IIiiiiiIIi10, double IIiiiiiIIi11, double IIiiiiiIIi12, boolean IIiiiiiIIi13, boolean IIiiiiiIIi14, int IIiiiiiIIi15, int IIiiiiiIIi16, boolean IIiiiiiIIi17) {
        GlStateManager.func_179094_E();
        try {
            double IIiiiiiIIi18;
            if (IIiiiiiIIi14) {
                GlStateManager.func_179147_l();
                GlStateManager.func_179120_a((int)IIiiiiiIIi15, (int)IIiiiiiIIi16, (int)1, (int)0);
            }
            GlStateManager.func_179098_w();
            GlStateManager.func_179144_i((int)IIiiiiiIIi);
            if (IIiiiiiIIi3 > 1.0f) {
                IIiiiiiIIi3 /= 255.0f;
            }
            if (IIiiiiiIIi14) {
                float[] IIiiiiiIIi19 = iiiiiiiiii_4.IIIiiiIiii(IIiiiiiIIi2);
                GlStateManager.func_179131_c((float)IIiiiiiIIi19[0], (float)IIiiiiiIIi19[1], (float)IIiiiiiIIi19[2], (float)IIiiiiiIIi3);
            } else {
                GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)IIiiiiiIIi3);
            }
            GL11.glTexParameteri((int)3553, (int)10241, (int)9729);
            GL11.glTexParameteri((int)3553, (int)10240, (int)9729);
            int IIiiiiiIIi20 = IIiiiiiIIi17 ? 33071 : 10497;
            GL11.glTexParameteri((int)3553, (int)10242, (int)IIiiiiiIIi20);
            GL11.glTexParameteri((int)3553, (int)10243, (int)IIiiiiiIIi20);
            if (IIiiiiiIIi12 != 0.0) {
                IIiiiiiIIi18 = IIiiiiiIIi4 + IIiiiiiIIi6 / 2.0;
                double IIiiiiiIIi21 = IIiiiiiIIi5 + IIiiiiiIIi7 / 2.0;
                GlStateManager.func_179137_b((double)IIiiiiiIIi18, (double)IIiiiiiIIi21, (double)0.0);
                GlStateManager.func_179114_b((float)((float)IIiiiiiIIi12), (float)0.0f, (float)0.0f, (float)1.0f);
                GlStateManager.func_179137_b((double)(-IIiiiiiIIi18), (double)(-IIiiiiiIIi21), (double)0.0);
            }
            IIiiiiiIIi18 = IIiiiiiIIi13 ? -IIiiiiiIIi10 : IIiiiiiIIi10;
            iiiiiiiiii_13.IIIiiiIiii(false);
            iiiiiiiiii_13.IIIiiiIiii(IIiiiiiIIi4, IIiiiiiIIi7 + IIiiiiiIIi5, iIIIIiiIII, IIiiiiiIIi8, IIiiiiiIIi11);
            iiiiiiiiii_13.IIIiiiIiii(IIiiiiiIIi4 + IIiiiiiIIi6, IIiiiiiIIi7 + IIiiiiiIIi5, iIIIIiiIII, IIiiiiiIIi18, IIiiiiiIIi11);
            iiiiiiiiii_13.IIIiiiIiii(IIiiiiiIIi4 + IIiiiiiIIi6, IIiiiiiIIi5, iIIIIiiIII, IIiiiiiIIi18, IIiiiiiIIi9);
            iiiiiiiiii_13.IIIiiiIiii(IIiiiiiIIi4, IIiiiiiIIi5, iIIIIiiIII, IIiiiiiIIi8, IIiiiiiIIi9);
            iiiiiiiiii_13.IIIiiiIiii();
            if (IIiiiiiIIi14) {
                GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                if (IIiiiiiIIi15 != 770 || IIiiiiiIIi16 != 771) {
                    GlStateManager.func_179147_l();
                    GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
                }
            }
        }
        finally {
            GlStateManager.func_179121_F();
        }
    }

    public static void IIIiiiIiii(String[] IIiiiiiIIi, double IIiiiiiIIi2, double IIiiiiiIIi3, iiiiiiiiii_14 IIiiiiiIIi4, iiiiiiiiii_16 IIiiiiiIIi5, Integer IIiiiiiIIi6, float IIiiiiiIIi7, Integer IIiiiiiIIi8, float IIiiiiiIIi9, double IIiiiiiIIi10, boolean IIiiiiiIIi11, double IIiiiiiIIi12) {
        if (IIiiiiiIIi.length == 0) {
            return;
        }
        if (IIiiiiiIIi.length == 1) {
            iiiiiiiiii_13.IIIiiiIiii(IIiiiiiIIi[0], IIiiiiiIIi2, IIiiiiiIIi3, IIiiiiiIIi4, IIiiiiiIIi5, IIiiiiiIIi6, IIiiiiiIIi7, IIiiiiiIIi8, IIiiiiiIIi9, IIiiiiiIIi10, IIiiiiiIIi11, IIiiiiiIIi12);
            return;
        }
        FontRenderer IIiiiiiIIi13 = FMLClientHandler.instance().getClient().field_71466_p;
        double IIiiiiiIIi14 = IIiiiiiIIi13.func_82883_a() ? 0.0 : (IIiiiiiIIi11 ? 6.0 : 4.0);
        double IIiiiiiIIi15 = (double)IIiiiiiIIi13.field_78288_b * IIiiiiiIIi10;
        double IIiiiiiIIi16 = IIiiiiiIIi15 * (double)IIiiiiiIIi.length + IIiiiiiIIi14;
        double IIiiiiiIIi17 = 0.0;
        if (IIiiiiiIIi6 != null && IIiiiiiIIi7 > 0.0f) {
            for (String IIiiiiiIIi18 : IIiiiiiIIi) {
                IIiiiiiIIi17 = Math.max(IIiiiiiIIi17, (double)IIiiiiiIIi13.func_78256_a(IIiiiiiIIi18) * IIiiiiiIIi10);
            }
            if (IIiiiiiIIi17 % 2.0 == 0.0) {
                IIiiiiiIIi17 += 1.0;
            }
        }
        if (IIiiiiiIIi.length > 1) {
            switch (iiiiiiiiii_7.IIiIiIIIiI[IIiiiiiIIi5.ordinal()]) {
                case 1: {
                    IIiiiiiIIi3 -= IIiiiiiIIi15 * (double)IIiiiiiIIi.length;
                    IIiiiiiIIi16 += IIiiiiiIIi14 / 2.0;
                    break;
                }
                case 2: {
                    IIiiiiiIIi3 -= IIiiiiiIIi16 / 2.0;
                }
            }
        }
        for (String IIiiiiiIIi18 : IIiiiiiIIi) {
            iiiiiiiiii_13.IIIiiiIiii(IIiiiiiIIi18, IIiiiiiIIi2, IIiiiiiIIi3, IIiiiiiIIi4, IIiiiiiIIi5, IIiiiiiIIi6, IIiiiiiIIi7, IIiiiiiIIi17, IIiiiiiIIi16, IIiiiiiIIi8, IIiiiiiIIi9, IIiiiiiIIi10, IIiiiiiIIi11, IIiiiiiIIi12);
            IIiiiiiIIi6 = null;
            IIiiiiiIIi3 += IIiiiiiIIi15;
        }
    }

    public static void IIIiiiIiii(double IIiiiiiIIi, double IIiiiiiIIi2, double IIiiiiiIIi3, int[] IIiiiiiIIi4) {
        IIiIiIIIiI.func_181662_b(IIiiiiiIIi, IIiiiiiIIi2, IIiiiiiIIi3).func_187315_a(1.0, 1.0).func_181669_b(IIiiiiiIIi4[0], IIiiiiiIIi4[1], IIiiiiiIIi4[2], IIiiiiiIIi4[3]).func_181675_d();
    }

    public static void IIIiiiIiii(int IIiiiiiIIi, int IIiiiiiIIi2, float IIiiiiiIIi3, double IIiiiiiIIi4, double IIiiiiiIIi5, double IIiiiiiIIi6, double IIiiiiiIIi7, boolean IIiiiiiIIi8, double IIiiiiiIIi9) {
        iiiiiiiiii_13.IIIiiiIiii(IIiiiiiIIi, IIiiiiiIIi2, IIiiiiiIIi3, IIiiiiiIIi4, IIiiiiiIIi5, IIiiiiiIIi6, IIiiiiiIIi7, 0.0, 0.0, 1.0, 1.0, IIiiiiiIIi9, IIiiiiiIIi8, true, 770, 771, false);
    }

    public static void IIIiiiIiii(String IIiiiiiIIi, double IIiiiiiIIi2, double IIiiiiiIIi3, Integer IIiiiiiIIi4, float IIiiiiiIIi5, Integer IIiiiiiIIi6, float IIiiiiiIIi7, double IIiiiiiIIi8, double IIiiiiiIIi9) {
        iiiiiiiiii_13.IIIiiiIiii(IIiiiiiIIi, IIiiiiiIIi2, IIiiiiiIIi3, iIiIiIiIII.IIiiiiiIIi, iIiiiIIiiI.IIiiiiiIIi, IIiiiiiIIi4, IIiiiiiIIi5, IIiiiiiIIi6, IIiiiiiIIi7, IIiiiiiIIi8, true, IIiiiiiIIi9);
    }

    public static void IIIiiiIiii(String IIiiiiiIIi, double IIiiiiiIIi2, double IIiiiiiIIi3, Integer IIiiiiiIIi4, float IIiiiiiIIi5, Integer IIiiiiiIIi6, float IIiiiiiIIi7, double IIiiiiiIIi8) {
        iiiiiiiiii_13.IIIiiiIiii(IIiiiiiIIi, IIiiiiiIIi2, IIiiiiiIIi3, iIiIiIiIII.IIiiiiiIIi, iIiiiIIiiI.IIiiiiiIIi, IIiiiiiIIi4, IIiiiiiIIi5, IIiiiiiIIi6, IIiiiiiIIi7, IIiiiiiIIi8, true, 0.0);
    }

    public iiiiiiiiii_13() {
        iiiiiiiiii_13 IIiiiiiIIi;
    }

    public static void IIIiiiIiii(boolean IIiiiiiIIi) {
        if (IIiiiiiIIi) {
            IIiIiIIIiI.func_181668_a(7, DefaultVertexFormats.field_181709_i);
        } else {
            IIiIiIIIiI.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        }
    }
}

