/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.dragongps;

import eos.moe.dragongps.iiiiiiiiii_0;
import eos.moe.dragongps.iiiiiiiiii_11;
import eos.moe.dragongps.iiiiiiiiii_12;
import eos.moe.dragongps.iiiiiiiiii_5;
import eos.moe.dragongps.iiiiiiiiii_6;
import eos.moe.dragongps.iiiiiiiiii_8;
import java.util.HashSet;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.lwjgl.opengl.GL11;

/*
 * Renamed from eos.moe.dragongps.iiIiiIiiII
 */
@Mod.EventBusSubscriber(modid="dragongps")
public class iiiiiiiiii_18 {
    public static double IIiIiIIIiI;
    private static Minecraft IIiiiiiIIi;

    private static /* synthetic */ void IIIiiiIiii(iiiiiiiiii_8 IIiiiiiIIi, double IIiiiiiIIi2, double IIiiiiiIIi3, double IIiiiiiIIi4) {
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)240.0f);
        GL11.glTranslated((double)(IIiiiiiIIi.IIiIiIIIiI - IIiiiiiIIi2), (double)(IIiiiiiIIi.IIiiIiiIII - IIiiiiiIIi3 + 0.4), (double)(IIiiiiiIIi.IIiiiiiIIi - IIiiiiiIIi4));
        GlStateManager.rotate((float)IIiiiiiIIi.iIIiIIiIii, (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)IIiiiiiIIi.iIiIIiIiiI, (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.scale((double)0.004, (double)0.004, (double)0.0);
        GlStateManager.rotate((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GlStateManager.enableBlend();
        double IIiiiiiIIi5 = Math.sin(IIiIiIIIiI - (double)(IIiiiiiIIi.iIIIIiiIII % 10) * 0.65);
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)(1.0f * (float)IIiiiiiIIi5));
        iiiiiiiiii_18.IIIiiiIiii(0, 0, 0.0f, 0.0f, iiiiiiiiii_0.IIiiiiiIIi, iiiiiiiiii_0.iIIIiIIIIi, iiiiiiiiii_0.IIiiiiiIIi, iiiiiiiiii_0.iIIIiIIIIi);
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }

    @SubscribeEvent
    public static void IIIiiiIiii(TickEvent.ClientTickEvent IIiiiiiIIi) {
        if (IIiiiiiIIi.phase == TickEvent.Phase.END && (IIiIiIIIiI += 0.3) > 100.0) {
            IIiIiIIIiI = 0.0;
        }
    }

    static {
        IIiiiiiIIi = Minecraft.getMinecraft();
    }

    public iiiiiiiiii_18() {
        iiiiiiiiii_18 IIiiiiiIIi;
    }

    @SubscribeEvent
    public static void IIIiiiIiii(RenderWorldLastEvent IIiiiiiIIi0) {
        if (Minecraft.getMinecraft().getRenderViewEntity() == null && iiiiiiiiii_0.IIiiIiiIII) {
            return;
        }
        GlStateManager.pushMatrix();
        EntityPlayerSP IIiiiiiIIi1 = iiiiiiiiii_18.IIiiiiiIIi.player;
        double IIiiiiiIIi2 = IIiiiiiIIi1.prevPosX + (IIiiiiiIIi1.posX - IIiiiiiIIi1.prevPosX) * (double)IIiiiiiIIi0.getPartialTicks();
        double IIiiiiiIIi4 = IIiiiiiIIi1.prevPosY + (IIiiiiiIIi1.posY - IIiiiiiIIi1.prevPosY) * (double)IIiiiiiIIi0.getPartialTicks();
        double IIiiiiiIIi6 = IIiiiiiIIi1.prevPosZ + (IIiiiiiIIi1.posZ - IIiiiiiIIi1.prevPosZ) * (double)IIiiiiiIIi0.getPartialTicks();
        HashSet<String> IIiiiiiIIi8 = new HashSet<String>();
        for (Map.Entry<String, iiiiiiiiii_12> IIiiiiiIIi10 : iiiiiiiiii_6.IIiiIiiIII.entrySet()) {
            iiiiiiiiii_12 IIiiiiiIIi11 = IIiiiiiIIi10.getValue();
            iiiiiiiiii_11.IIIiiiIiii(IIiiiiiIIi11.IIIiiiIiii());
            boolean IIiiiiiIIi12 = false;
            for (iiiiiiiiii_8 IIiiiiiIIi14 : IIiiiiiIIi11.IIIiiiIiii()) {
                IIiiiiiIIi12 = iiiiiiiiii_6.iIIIIiiIII.equals(IIiiiiiIIi14.iiIIIiIiII);
                if (!IIiiiiiIIi12) continue;
                iiiiiiiiii_18.IIIiiiIiii(IIiiiiiIIi14, IIiiiiiIIi2, IIiiiiiIIi4, IIiiiiiIIi6);
                iiiiiiiiii_18.IIIiiiIiii(IIiiiiiIIi14, IIiiiiiIIi2, IIiiiiiIIi4 - 0.02, IIiiiiiIIi6);
            }
            if (!IIiiiiiIIi12 || !(iiiiiiiiii_18.IIiiiiiIIi.getRenderManager().renderViewEntity.getPositionVector().distanceTo(IIiiiiiIIi11.IIIiiiIiii()) <= (double)IIiiiiiIIi11.IIIiiiIiii())) continue;
            IIiiiiiIIi8.add(IIiiiiiIIi10.getKey());
        }
        IIiiiiiIIi8.forEach(IIiiiiiIIi -> {
            iiiiiiiiii_6.IIiiIiiIII.remove(IIiiiiiIIi);
            iiiiiiiiii_5.IIiiiiiIIi.sendToServer((IMessage)new iiiiiiiiii_6(1, (String)IIiiiiiIIi));
        });
        GlStateManager.popMatrix();
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private static /* synthetic */ void IIIiiiIiii(int IIiiiiiIIi, int IIiiiiiIIi2, float IIiiiiiIIi3, float IIiiiiiIIi4, int IIiiiiiIIi5, int IIiiiiiIIi6, float IIiiiiiIIi7, float IIiiiiiIIi8) {
        IIiiiiiIIi7 = 1.0f / IIiiiiiIIi7;
        IIiiiiiIIi8 = 1.0f / IIiiiiiIIi8;
        Tessellator IIiiiiiIIi9 = Tessellator.getInstance();
        BufferBuilder IIiiiiiIIi10 = IIiiiiiIIi9.getBuffer();
        IIiiiiiIIi10.begin(7, DefaultVertexFormats.POSITION_TEX);
        IIiiiiiIIi10.pos((double)((float)IIiiiiiIIi - (float)(IIiiiiiIIi5 / 2)), (double)((float)(IIiiiiiIIi2 + IIiiiiiIIi6) - (float)(IIiiiiiIIi6 / 2)), 0.0).tex((double)(IIiiiiiIIi3 * IIiiiiiIIi7), (double)((IIiiiiiIIi4 + (float)IIiiiiiIIi6) * IIiiiiiIIi8)).endVertex();
        IIiiiiiIIi10.pos((double)((float)(IIiiiiiIIi + IIiiiiiIIi5) - (float)(IIiiiiiIIi5 / 2)), (double)((float)(IIiiiiiIIi2 + IIiiiiiIIi6) - (float)(IIiiiiiIIi6 / 2)), 0.0).tex((double)((IIiiiiiIIi3 + (float)IIiiiiiIIi5) * IIiiiiiIIi7), (double)((IIiiiiiIIi4 + (float)IIiiiiiIIi6) * IIiiiiiIIi8)).endVertex();
        IIiiiiiIIi10.pos((double)((float)(IIiiiiiIIi + IIiiiiiIIi5) - (float)(IIiiiiiIIi5 / 2)), (double)((float)IIiiiiiIIi2 - (float)(IIiiiiiIIi6 / 2)), 0.0).tex((double)((IIiiiiiIIi3 + (float)IIiiiiiIIi5) * IIiiiiiIIi7), (double)(IIiiiiiIIi4 * IIiiiiiIIi8)).endVertex();
        IIiiiiiIIi10.pos((double)((float)IIiiiiiIIi - (float)(IIiiiiiIIi5 / 2)), (double)((float)IIiiiiiIIi2 - (float)(IIiiiiiIIi6 / 2)), 0.0).tex((double)(IIiiiiiIIi3 * IIiiiiiIIi7), (double)(IIiiiiiIIi4 * IIiiiiiIIi8)).endVertex();
        IIiiiiiIIi9.draw();
    }
}

