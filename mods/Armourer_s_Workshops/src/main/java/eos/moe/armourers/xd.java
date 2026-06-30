/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.util.math.MathHelper
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.armourers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class xd {
    private static float m;
    private static float j;

    public xd() {
        xd a2;
    }

    public static void y(int a2, int a3, int a4, int a5) {
        GL11.glEnable((int)3089);
        GL11.glScissor((int)a2, (int)a3, (int)a4, (int)a5);
    }

    public static byte[] r(byte ... a2) {
        int n2;
        int n3 = n2 = 0;
        while (n3 < a2.length) {
            a2[++n2] = (byte)(a2[n2] - 50);
            n3 = n2;
        }
        return a2;
    }

    public static void x() {
        j = OpenGlHelper.lastBrightnessX;
        m = OpenGlHelper.lastBrightnessY;
        OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)240.0f);
    }

    public static void h() {
        OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)j, (float)m);
    }

    public static void z() {
        xd.r(770, 771);
    }

    public static void r(int a2, int a3, int a4, int a5) {
        Minecraft minecraft = Minecraft.getMinecraft();
        ScaledResolution scaledResolution = new ScaledResolution(minecraft);
        Minecraft minecraft2 = minecraft;
        double d2 = (double)minecraft2.displayWidth / scaledResolution.getScaledWidth_double();
        double d3 = (double)minecraft2.displayHeight / scaledResolution.getScaledHeight_double();
        xd.y(MathHelper.floor((double)((double)a2 * d2)), minecraft.displayHeight - MathHelper.floor((double)(((double)a3 + (double)a5) * d3)), MathHelper.floor((double)((double)a4 * d2)), MathHelper.floor((double)((double)a5 * d3)));
    }

    public static void y() {
        GL11.glDisable((int)3089);
    }

    public static void r() {
        GlStateManager.disableBlend();
    }

    public static void r(int a2, int a3) {
        GlStateManager.enableBlend();
        GlStateManager.blendFunc((int)a2, (int)a3);
    }
}

