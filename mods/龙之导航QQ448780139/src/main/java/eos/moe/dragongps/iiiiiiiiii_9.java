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
        IIiiiiiIIi = Minecraft.getMinecraft();
        IIiIiIIIiI = IIiiiiiIIi.getRenderManager();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static /* synthetic */ void IIIiiiIiii(iiiiiiiiii_10 IIiiiiiIIi) {
        if (iiiiiiiiii_9.IIiIiIIIiI.renderViewEntity == null) {
            return;
        }
        RenderHelper.enableStandardItemLighting();
        try {
            String[] IIiiiiiIIi3;
            double IIiiiiiIIi4;
            double IIiiiiiIIi5;
            double IIiiiiiIIi6;
            double IIiiiiiIIi7;
            Vec3d IIiiiiiIIi8 = iiiiiiiiii_9.IIiIiIIIiI.renderViewEntity.getPositionVector();
            Vec3d IIiiiiiIIi9 = IIiiiiiIIi.IIIiiiIiii().add(0.0, 0.118, 0.0);
            double IIiiiiiIIi10 = IIiiiiiIIi8.distanceTo(IIiiiiiIIi9);
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
            if ((IIiiiiiIIi7 = IIiiiiiIIi10) > (IIiiiiiIIi6 = (double)(iiiiiiiiii_9.IIiiiiiIIi.gameSettings.renderDistanceChunks * 16))) {
                Vec3d IIiiiiiIIi13 = IIiiiiiIIi9.subtract(IIiiiiiIIi8).normalize();
                IIiiiiiIIi9 = IIiiiiiIIi8.add(IIiiiiiIIi13.x * IIiiiiiIIi6, IIiiiiiIIi13.y * IIiiiiiIIi6, IIiiiiiIIi13.z * IIiiiiiIIi6);
                IIiiiiiIIi7 = IIiiiiiIIi6;
            }
            double IIiiiiiIIi14 = IIiiiiiIIi9.x - iiiiiiiiii_9.IIiIiIIIiI.viewerPosX;
            double IIiiiiiIIi15 = IIiiiiiIIi9.y - iiiiiiiiii_9.IIiIiIIIiI.viewerPosY;
            double IIiiiiiIIi16 = IIiiiiiIIi9.z - iiiiiiiiii_9.IIiIiIIIiI.viewerPosZ;
            double IIiiiiiIIi17 = 0.00390625 * ((IIiiiiiIIi7 + 4.0) / 3.0);
            Dimension IIiiiiiIIi18 = iiiiiiiiii_11.IIIiiiIiii(IIiiiiiIIi.iIIIIiiIII);
            double d = IIiiiiiIIi5 = IIiiiiiIIi18 == null ? 0.0 : IIiiiiiIIi18.getHeight() / 2.0;
            if (IIiiiiiIIi.iIIIIiiIII != null && IIiiiiiIIi18 != null) {
                GlStateManager.pushMatrix();
                GlStateManager.disableLighting();
                GL11.glNormal3d((double)0.0, (double)0.0, (double)(-1.0 * IIiiiiiIIi17));
                GlStateManager.disableDepth();
                GlStateManager.depthMask((boolean)false);
                GlStateManager.translate((double)IIiiiiiIIi14, (double)IIiiiiiIIi15, (double)IIiiiiiIIi16);
                GlStateManager.rotate((float)(-iiiiiiiiii_9.IIiIiIIIiI.playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
                GlStateManager.rotate((float)iiiiiiiiii_9.IIiIiIIIiI.playerViewX, (float)1.0f, (float)0.0f, (float)0.0f);
                GlStateManager.scale((double)(-IIiiiiiIIi17 * 2.0), (double)(-IIiiiiiIIi17 * 2.0), (double)(IIiiiiiIIi17 * 2.0));
                GL11.glNormal3d((double)0.0, (double)0.0, (double)(-1.0 * IIiiiiiIIi17 * 2.0));
                iiiiiiiiii_11.IIIiiiIiii(IIiiiiiIIi.iIIIIiiIII);
                GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                Gui.drawModalRectWithCustomSizedTexture((int)((int)(0.0 - IIiiiiiIIi18.getWidth() / 2.0 + 0.5)), (int)((int)(0.0 - IIiiiiiIIi5 + 0.2 - 40.0)), (float)0.0f, (float)0.0f, (int)IIiiiiiIIi18.width, (int)IIiiiiiIIi18.height, (float)IIiiiiiIIi18.width, (float)IIiiiiiIIi18.height);
                GlStateManager.popMatrix();
            }
            ArrayList<String> IIiiiiiIIi19 = new ArrayList<String>(IIiiiiiIIi.IIIiiiIiii());
            IIiiiiiIIi19.replaceAll(IIiiiiiIIi2 -> IIiiiiiIIi2.replace("%distance%", String.format("%.2f", IIiiiiiIIi10)));
            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();
            GL11.glNormal3d((double)0.0, (double)0.0, (double)(-1.0 * IIiiiiiIIi17));
            GlStateManager.translate((double)IIiiiiiIIi14, (double)IIiiiiiIIi15, (double)IIiiiiiIIi16);
            GlStateManager.rotate((float)(-iiiiiiiiii_9.IIiIiIIIiI.playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.rotate((float)iiiiiiiiii_9.IIiIiIIIiI.playerViewX, (float)1.0f, (float)0.0f, (float)0.0f);
            GlStateManager.scale((double)(-IIiiiiiIIi17), (double)(-IIiiiiiIIi17), (double)IIiiiiiIIi17);
            GlStateManager.depthMask((boolean)true);
            GlStateManager.depthMask((boolean)true);
            GlStateManager.enableDepth();
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
            GlStateManager.disableDepth();
            GlStateManager.depthMask((boolean)false);
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
            GlStateManager.popMatrix();
        }
        finally {
            GlStateManager.depthMask((boolean)true);
            GlStateManager.enableDepth();
            GlStateManager.enableLighting();
            GlStateManager.depthMask((boolean)true);
            GlStateManager.enableCull();
            GlStateManager.disableBlend();
            GlStateManager.disableFog();
            RenderHelper.disableStandardItemLighting();
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
        if (iiiiiiiiii_9.IIiiiiiIIi.player == null || iiiiiiiiii_9.IIiiiiiIIi.gameSettings.hideGUI) {
            return;
        }
        GlStateManager.pushMatrix();
        iiiiiiiiii_9.IIiiiiiIIi.profiler.startSection("dragontracker");
        HashSet<String> IIiiiiiIIi3 = new HashSet<String>();
        for (Map.Entry<String, iiiiiiiiii_10> IIiiiiiIIi4 : iiiiiiiiii_6.iIIiIIiIii.entrySet()) {
            if (!iiiiiiiiii_6.iIIIIiiIII.equals(IIiiiiiIIi4.getValue().iiIIIiIiII)) continue;
            iiiiiiiiii_10 IIiiiiiIIi5 = IIiiiiiIIi4.getValue();
            iiiiiiiiii_9.IIIiiiIiii(IIiiiiiIIi5);
            Vec3d IIiiiiiIIi6 = iiiiiiiiii_9.IIiIiIIIiI.renderViewEntity.getPositionVector();
            Vec3d IIiiiiiIIi7 = IIiiiiiIIi5.IIIiiiIiii().add(0.0, 0.118, 0.0);
            double IIiiiiiIIi8 = IIiiiiiIIi6.distanceTo(IIiiiiiIIi7);
            if (IIiiiiiIIi5.IIiIiIIIiI <= 0 || (int)IIiiiiiIIi8 > IIiiiiiIIi5.IIiIiIIIiI) continue;
            IIiiiiiIIi3.add(IIiiiiiIIi4.getKey());
        }
        IIiiiiiIIi3.forEach(IIiiiiiIIi -> {
            iiiiiiiiii_6.iIIiIIiIii.remove(IIiiiiiIIi);
            iiiiiiiiii_5.IIiiiiiIIi.sendToServer((IMessage)new iiiiiiiiii_6(2, (String)IIiiiiiIIi));
        });
        iiiiiiiiii_9.IIiiiiiIIi.profiler.endSection();
        GlStateManager.popMatrix();
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }
}

