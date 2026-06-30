/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  eos.moe.dragongps.iIiIIiIIII
 *  eos.moe.dragongps.iIiIiIiIII
 *  eos.moe.dragongps.iIiiiIIiiI
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.dragongps;

import eos.moe.dragongps.iIiIIiIIII;
import eos.moe.dragongps.iIiIiIiIII;
import eos.moe.dragongps.iIiiiIIiiI;
import eos.moe.dragongps.iiiiiiiiii_0;
import eos.moe.dragongps.iiiiiiiiii_10;
import eos.moe.dragongps.iiiiiiiiii_11;
import eos.moe.dragongps.iiiiiiiiii_14;
import eos.moe.dragongps.iiiiiiiiii_16;
import eos.moe.dragongps.iiiiiiiiii_5;
import eos.moe.dragongps.iiiiiiiiii_6;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.lwjgl.opengl.GL11;

/*
 * Renamed from eos.moe.dragongps.iIIIiiIIIi
 */
@Mod.EventBusSubscriber(modid="dragongps")
public class iiiiiiiiii_9 {
    private static RenderManager IIiIiIIIiI;
    private static Minecraft IIiiiiiIIi;

    static {
        IIiiiiiIIi = Minecraft.func_71410_x();
        IIiIiIIIiI = IIiiiiiIIi.func_175598_ae();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static /* synthetic */ void IIIiiiIiii(iiiiiiiiii_10 IIiiiiiIIi) {
        if (iiiiiiiiii_9.IIiIiIIIiI.field_78734_h == null) {
            return;
        }
        RenderHelper.func_74519_b();
        try {
            String[] IIiiiiiIIi3;
            double IIiiiiiIIi4;
            double IIiiiiiIIi5;
            double IIiiiiiIIi6;
            double IIiiiiiIIi7;
            Vec3d IIiiiiiIIi8 = iiiiiiiiii_9.IIiIiIIIiI.field_78734_h.func_174791_d();
            Vec3d IIiiiiiIIi9 = IIiiiiiIIi.IIIiiiIiii().func_72441_c(0.0, 0.118, 0.0);
            double IIiiiiiIIi10 = IIiiiiiIIi8.func_72438_d(IIiiiiiIIi9);
            int IIiiiiiIIi11 = iiiiiiiiii_5.IIiIiIIIiI;
            if (IIiiiiiIIi.iIIiIIiIii != 0 && IIiiiiiIIi10 > (double)IIiiiiiIIi.iIIiIIiIii) {
                return;
            }
            if ((int)IIiiiiiIIi10 <= IIiiiiiIIi11) {
                return;
            }
            if (iiiiiiiiii_5.iIIiIIiIii) {
                return;
            }
            float IIiiiiiIIi12 = 1.0f;
            if ((int)IIiiiiiIIi10 <= IIiiiiiIIi11 + 4) {
                IIiiiiiIIi12 = (float)(IIiiiiiIIi10 - (double)IIiiiiiIIi11) / 3.0f;
            }
            if ((IIiiiiiIIi7 = IIiiiiiIIi10) > (IIiiiiiIIi6 = (double)(iiiiiiiiii_9.IIiiiiiIIi.field_71474_y.field_151451_c * 16))) {
                Vec3d IIiiiiiIIi13 = IIiiiiiIIi9.func_178788_d(IIiiiiiIIi8).func_72432_b();
                IIiiiiiIIi9 = IIiiiiiIIi8.func_72441_c(IIiiiiiIIi13.field_72450_a * IIiiiiiIIi6, IIiiiiiIIi13.field_72448_b * IIiiiiiIIi6, IIiiiiiIIi13.field_72449_c * IIiiiiiIIi6);
                IIiiiiiIIi7 = IIiiiiiIIi6;
            }
            double IIiiiiiIIi14 = IIiiiiiIIi9.field_72450_a - iiiiiiiiii_9.IIiIiIIIiI.field_78730_l;
            double IIiiiiiIIi15 = IIiiiiiIIi9.field_72448_b - iiiiiiiiii_9.IIiIiIIIiI.field_78731_m;
            double IIiiiiiIIi16 = IIiiiiiIIi9.field_72449_c - iiiiiiiiii_9.IIiIiIIIiI.field_78728_n;
            double IIiiiiiIIi17 = 0.00390625 * ((IIiiiiiIIi7 + 4.0) / 3.0);
            Dimension IIiiiiiIIi18 = iiiiiiiiii_11.IIIiiiIiii(IIiiiiiIIi.iIIIIiiIII);
            double d = IIiiiiiIIi5 = IIiiiiiIIi18 == null ? 0.0 : IIiiiiiIIi18.getHeight() / 2.0;
            if (IIiiiiiIIi.iIIIIiiIII != null && IIiiiiiIIi18 != null) {
                GlStateManager.func_179094_E();
                GlStateManager.func_179140_f();
                GL11.glNormal3d((double)0.0, (double)0.0, (double)(-1.0 * IIiiiiiIIi17));
                GlStateManager.func_179097_i();
                GlStateManager.func_179132_a((boolean)false);
                GlStateManager.func_179137_b((double)IIiiiiiIIi14, (double)IIiiiiiIIi15, (double)IIiiiiiIIi16);
                GlStateManager.func_179114_b((float)(-iiiiiiiiii_9.IIiIiIIIiI.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
                GlStateManager.func_179114_b((float)iiiiiiiiii_9.IIiIiIIIiI.field_78732_j, (float)1.0f, (float)0.0f, (float)0.0f);
                GlStateManager.func_179139_a((double)(-IIiiiiiIIi17 * 2.0), (double)(-IIiiiiiIIi17 * 2.0), (double)(IIiiiiiIIi17 * 2.0));
                GL11.glNormal3d((double)0.0, (double)0.0, (double)(-1.0 * IIiiiiiIIi17 * 2.0));
                iiiiiiiiii_11.IIIiiiIiii(IIiiiiiIIi.iIIIIiiIII);
                GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                Gui.func_146110_a((int)((int)(0.0 - IIiiiiiIIi18.getWidth() / 2.0 + 0.5)), (int)((int)(0.0 - IIiiiiiIIi5 + 0.2 - 40.0)), (float)0.0f, (float)0.0f, (int)IIiiiiiIIi18.width, (int)IIiiiiiIIi18.height, (float)IIiiiiiIIi18.width, (float)IIiiiiiIIi18.height);
                GlStateManager.func_179121_F();
            }
            ArrayList<String> IIiiiiiIIi19 = new ArrayList<String>(IIiiiiiIIi.IIIiiiIiii());
            IIiiiiiIIi19.replaceAll(IIiiiiiIIi2 -> IIiiiiiIIi2.replace("%distance%", String.format("%.2f", IIiiiiiIIi10)));
            GlStateManager.func_179094_E();
            GlStateManager.func_179140_f();
            GL11.glNormal3d((double)0.0, (double)0.0, (double)(-1.0 * IIiiiiiIIi17));
            GlStateManager.func_179137_b((double)IIiiiiiIIi14, (double)IIiiiiiIIi15, (double)IIiiiiiIIi16);
            GlStateManager.func_179114_b((float)(-iiiiiiiiii_9.IIiIiIIIiI.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.func_179114_b((float)iiiiiiiiii_9.IIiIiIIIiI.field_78732_j, (float)1.0f, (float)0.0f, (float)0.0f);
            GlStateManager.func_179139_a((double)(-IIiiiiiIIi17), (double)(-IIiiiiiIIi17), (double)IIiiiiiIIi17);
            GlStateManager.func_179132_a((boolean)true);
            GlStateManager.func_179132_a((boolean)true);
            GlStateManager.func_179126_j();
            int IIiiiiiIIi20 = 3;
            double IIiiiiiIIi21 = IIiiiiiIIi4 = 0.0 - IIiiiiiIIi5 - 8.0;
            for (String IIiiiiiIIi22 : IIiiiiiIIi19) {
                IIiiiiiIIi3 = IIiiiiiIIi22.split("<->");
                if (IIiiiiiIIi3.length == 2) {
                    try {
                        int IIiiiiiIIi23 = Integer.parseInt(IIiiiiiIIi3[0]);
                        iIiIIiIIII.IIIiiiIiii((String)IIiiiiiIIi3[1], (double)1.0, (double)(IIiiiiiIIi4 + (double)IIiiiiiIIi23), (iiiiiiiiii_14)iIiIiIiIII.IIiiiiiIIi, (iiiiiiiiii_16)iIiiiIIiiI.IIiIiIIIiI, (Integer)0, (float)(0.6f * IIiiiiiIIi12), (int)Color.WHITE.getRGB(), (float)IIiiiiiIIi12, (double)IIiiiiiIIi20, (boolean)false);
                        IIiiiiiIIi21 = IIiiiiiIIi4 + (double)IIiiiiiIIi23 + 30.0;
                    }
                    catch (Exception IIiiiiiIIi23) {}
                    continue;
                }
                iIiIIiIIII.IIIiiiIiii((String)IIiiiiiIIi22, (double)1.0, (double)IIiiiiiIIi21, (iiiiiiiiii_14)iIiIiIiIII.IIiiiiiIIi, (iiiiiiiiii_16)iIiiiIIiiI.IIiIiIIIiI, (Integer)0, (float)(0.6f * IIiiiiiIIi12), (int)Color.WHITE.getRGB(), (float)IIiiiiiIIi12, (double)IIiiiiiIIi20, (boolean)false);
                IIiiiiiIIi21 += 30.0;
            }
            GlStateManager.func_179097_i();
            GlStateManager.func_179132_a((boolean)false);
            IIiiiiiIIi21 = IIiiiiiIIi4;
            for (String IIiiiiiIIi22 : IIiiiiiIIi19) {
                IIiiiiiIIi3 = IIiiiiiIIi22.split("<->");
                if (IIiiiiiIIi3.length == 2) {
                    try {
                        int IIiiiiiIIi24 = Integer.parseInt(IIiiiiiIIi3[0]);
                        iIiIIiIIII.IIIiiiIiii((String)IIiiiiiIIi3[1], (double)1.0, (double)(IIiiiiiIIi4 + (double)IIiiiiiIIi24), (iiiiiiiiii_14)iIiIiIiIII.IIiiiiiIIi, (iiiiiiiiii_16)iIiiiIIiiI.IIiIiIIIiI, (Integer)0, (float)(0.6f * IIiiiiiIIi12), (int)Color.WHITE.getRGB(), (float)IIiiiiiIIi12, (double)IIiiiiiIIi20, (boolean)false);
                        IIiiiiiIIi21 = IIiiiiiIIi4 + (double)IIiiiiiIIi24 + 30.0;
                    }
                    catch (Exception IIiiiiiIIi24) {}
                    continue;
                }
                iIiIIiIIII.IIIiiiIiii((String)IIiiiiiIIi22, (double)1.0, (double)IIiiiiiIIi21, (iiiiiiiiii_14)iIiIiIiIII.IIiiiiiIIi, (iiiiiiiiii_16)iIiiiIIiiI.IIiIiIIIiI, (Integer)0, (float)(0.6f * IIiiiiiIIi12), (int)Color.WHITE.getRGB(), (float)IIiiiiiIIi12, (double)IIiiiiiIIi20, (boolean)false);
                IIiiiiiIIi21 += 30.0;
            }
            GlStateManager.func_179121_F();
        }
        finally {
            GlStateManager.func_179132_a((boolean)true);
            GlStateManager.func_179126_j();
            GlStateManager.func_179145_e();
            GlStateManager.func_179132_a((boolean)true);
            GlStateManager.func_179089_o();
            GlStateManager.func_179084_k();
            GlStateManager.func_179106_n();
            RenderHelper.func_74518_a();
        }
    }

    public iiiiiiiiii_9() {
        iiiiiiiiii_9 IIiiiiiIIi;
    }

    @SubscribeEvent
    public static void IIIiiiIiii(RenderWorldLastEvent IIiiiiiIIi2) {
        if (iiiiiiiiii_0.IIiiIiiIII) {
            return;
        }
        if (iiiiiiiiii_9.IIiiiiiIIi.field_71439_g == null || iiiiiiiiii_9.IIiiiiiIIi.field_71474_y.field_74319_N) {
            return;
        }
        GlStateManager.func_179094_E();
        iiiiiiiiii_9.IIiiiiiIIi.field_71424_I.func_76320_a("dragontracker");
        HashSet<String> IIiiiiiIIi3 = new HashSet<String>();
        for (Map.Entry<String, iiiiiiiiii_10> IIiiiiiIIi4 : iiiiiiiiii_6.iIIiIIiIii.entrySet()) {
            if (!iiiiiiiiii_6.iIIIIiiIII.equals(IIiiiiiIIi4.getValue().iiIIIiIiII)) continue;
            iiiiiiiiii_10 IIiiiiiIIi5 = IIiiiiiIIi4.getValue();
            iiiiiiiiii_9.IIIiiiIiii(IIiiiiiIIi5);
            Vec3d IIiiiiiIIi6 = iiiiiiiiii_9.IIiIiIIIiI.field_78734_h.func_174791_d();
            Vec3d IIiiiiiIIi7 = IIiiiiiIIi5.IIIiiiIiii().func_72441_c(0.0, 0.118, 0.0);
            double IIiiiiiIIi8 = IIiiiiiIIi6.func_72438_d(IIiiiiiIIi7);
            if (IIiiiiiIIi5.IIiIiIIIiI <= 0 || (int)IIiiiiiIIi8 > IIiiiiiIIi5.IIiIiIIIiI) continue;
            IIiiiiiIIi3.add(IIiiiiiIIi4.getKey());
        }
        IIiiiiiIIi3.forEach(IIiiiiiIIi -> {
            iiiiiiiiii_6.iIIiIIiIii.remove(IIiiiiiIIi);
            iiiiiiiiii_5.IIiiiiiIIi.sendToServer((IMessage)new iiiiiiiiii_6(2, (String)IIiiiiiIIi));
        });
        iiiiiiiiii_9.IIiiiiiIIi.field_71424_I.func_76319_b();
        GlStateManager.func_179121_F();
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }
}

