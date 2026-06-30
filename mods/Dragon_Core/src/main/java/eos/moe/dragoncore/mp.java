/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.texture.SimpleTexture
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.kz;
import eos.moe.dragoncore.yq;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.HashMap;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class mp
extends SimpleTexture {
    public int q;
    public int b;
    public int o;
    public ByteBuffer[] y = new ByteBuffer[6];
    public static HashMap<ResourceLocation, mp> k = new HashMap();
    public ResourceLocation ALLATORIxDEMO;

    public mp(ResourceLocation a2) throws IOException {
        super(a2);
        mp a3;
        a3.ALLATORIxDEMO = a2;
        BufferedImage a4 = kz.ALLATORIxDEMO(a2);
        a3.initBuffers(a4, a2.toString());
        k.put(a2, a3);
    }

    public void initBuffers(BufferedImage a2, String a3) throws IOException {
        mp a4;
        a4.b = a2.getWidth();
        a4.o = a2.getHeight();
        if ((double)a4.o % 4.0 != 0.0 || (double)a4.b % 3.0 != 0.0 || a4.o / 4 != a4.b / 3) {
            String a5 = "The Dimensions of %s are invalid! A Cubemap image must have an exact width-to-height ratio of 3:4!";
            throw new IOException(String.format(a5, a3));
        }
        a4.q = a4.b / 3;
        BufferedImage a6 = null;
        for (int a7 = 0; a7 < 6; ++a7) {
            ByteBuffer a8;
            switch (a7) {
                case 0: {
                    a6 = kz.ALLATORIxDEMO(a2.getSubimage(2 * a4.q, a4.q, a4.q, a4.q), 90.0);
                    break;
                }
                case 1: {
                    a6 = kz.ALLATORIxDEMO(a2.getSubimage(0, a4.q, a4.q, a4.q), -90.0);
                    break;
                }
                case 2: {
                    a6 = a2.getSubimage(a4.q, a4.q, a4.q, a4.q);
                    break;
                }
                case 3: {
                    a6 = a2.getSubimage(a4.q, 3 * a4.q, a4.q, a4.q);
                    break;
                }
                case 4: {
                    a6 = a2.getSubimage(a4.q, 2 * a4.q, a4.q, a4.q);
                    break;
                }
                case 5: {
                    a6 = kz.ALLATORIxDEMO(a2.getSubimage(a4.q, 0, a4.q, a4.q), 180.0);
                }
            }
            a4.y[a7] = a8 = kz.ALLATORIxDEMO(a6);
            GL11.glTexImage2D((int)(34069 + a7), (int)0, (int)32856, (int)a4.q, (int)a4.q, (int)0, (int)6408, (int)5121, (ByteBuffer)a8);
        }
    }

    public static void c(ResourceLocation a2) {
        if (Minecraft.getMinecraft() != null && !Minecraft.isFancyGraphicsEnabled()) {
            return;
        }
        mp a3 = k.get(a2);
        if (a3 == null) {
            try {
                a3 = new mp(a2);
            }
            catch (IOException a4) {
                a4.printStackTrace();
            }
        }
        a3.start();
    }

    public void newBinding() {
        mp a2;
        int a3 = a2.getGlTextureId();
        GL11.glBindTexture((int)34067, (int)a3);
        for (int a4 = 0; a4 < 6; ++a4) {
            GL11.glTexImage2D((int)(34069 + a4), (int)0, (int)32856, (int)a2.q, (int)a2.q, (int)0, (int)6408, (int)5121, (ByteBuffer)a2.y[a4]);
        }
    }

    public void start() {
        OpenGlHelper.setActiveTexture((int)yq.k);
        GL11.glEnable((int)3168);
        GL11.glEnable((int)3169);
        GL11.glEnable((int)3170);
        GL11.glEnable((int)34067);
        GL11.glEnable((int)2977);
        for (int a2 = 0; a2 < 6; ++a2) {
            mp a3;
            GL11.glTexImage2D((int)(34069 + a2), (int)0, (int)32856, (int)a3.q, (int)a3.q, (int)0, (int)6408, (int)5121, (ByteBuffer)a3.y[a2]);
        }
        GL11.glPixelStorei((int)3317, (int)1);
        GL11.glTexGeni((int)8192, (int)9472, (int)34066);
        GL11.glTexGeni((int)8193, (int)9472, (int)34066);
        GL11.glTexGeni((int)8194, (int)9472, (int)34066);
    }

    public void end() {
        GL11.glPixelStorei((int)3317, (int)1);
        GL11.glDisable((int)3168);
        GL11.glDisable((int)3169);
        GL11.glDisable((int)3170);
        GL11.glDisable((int)34067);
        OpenGlHelper.setActiveTexture((int)OpenGlHelper.defaultTexUnit);
    }

    public static void ALLATORIxDEMO(ResourceLocation a2) {
        Object a3;
        Field a4 = null;
        TextureManager a5 = Minecraft.getMinecraft().renderEngine;
        IResourceManager a6 = null;
        IResource a7 = null;
        BufferedImage a8 = null;
        try {
            a4 = TextureManager.class.getDeclaredField("theResourceManager");
            a4.setAccessible(true);
            a6 = (IResourceManager)a4.get(a5);
            a7 = a6.getResource(a2);
            a3 = a7.getInputStream();
            a8 = ImageIO.read((InputStream)a3);
        }
        catch (Exception a9) {
            a9.printStackTrace();
        }
        a3 = new SimpleTexture(a2);
        System.out.println(a8);
    }

    public String toString() {
        mp a2;
        return "Cubemap(" + a2.q + "x" + a2.q + ";" + a2.ALLATORIxDEMO + ")";
    }

    static {
        GL11.glPixelStorei((int)3317, (int)1);
        GL11.glTexParameteri((int)34067, (int)10242, (int)33071);
        GL11.glTexParameteri((int)34067, (int)10243, (int)33071);
        GL11.glTexParameteri((int)34067, (int)32882, (int)33071);
        GL11.glTexParameteri((int)34067, (int)10240, (int)9728);
        GL11.glTexParameteri((int)34067, (int)10241, (int)9729);
    }
}

