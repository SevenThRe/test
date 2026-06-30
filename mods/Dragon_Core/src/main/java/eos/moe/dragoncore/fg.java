/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture$Type
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.ThreadDownloadImageData
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.resources.DefaultPlayerSkin
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.common.ObfuscationReflectionHelper
 *  org.apache.commons.io.IOUtils
 */
package eos.moe.dragoncore;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.apache.commons.io.IOUtils;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class fg {
    public fg() {
        fg a2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static BufferedImage ALLATORIxDEMO(AbstractClientPlayer a2) {
        BufferedImage a3 = null;
        ResourceLocation a4 = DefaultPlayerSkin.func_177335_a();
        InputStream a5 = null;
        Minecraft a6 = Minecraft.func_71410_x();
        a4 = a2.func_110306_p();
        try {
            ITextureObject a7 = a6.func_110434_K().func_110581_b(a4);
            if (a7 instanceof ThreadDownloadImageData) {
                ThreadDownloadImageData a8 = (ThreadDownloadImageData)a7;
                a3 = (BufferedImage)ObfuscationReflectionHelper.getPrivateValue(ThreadDownloadImageData.class, (Object)a8, (String[])new String[]{"bufferedImage", "field_110560_d", "bpr.h"});
            } else {
                a5 = Minecraft.func_71410_x().func_110442_L().func_110536_a(a4).func_110527_b();
                a3 = ImageIO.read(a5);
            }
            IOUtils.closeQuietly((InputStream)a5);
        }
        catch (IOException a9) {
            a9.printStackTrace();
        }
        finally {
            IOUtils.closeQuietly(a5);
        }
        return a3;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static BufferedImage c(ResourceLocation a2) {
        Minecraft a3 = Minecraft.func_71410_x();
        BufferedImage a4 = null;
        InputStream a5 = null;
        try {
            ITextureObject a6 = a3.func_110434_K().func_110581_b(a2);
            if (a6 instanceof ThreadDownloadImageData) {
                ThreadDownloadImageData a7 = (ThreadDownloadImageData)a6;
                a4 = (BufferedImage)ObfuscationReflectionHelper.getPrivateValue(ThreadDownloadImageData.class, (Object)a7, (String[])new String[]{"bufferedImage", "field_110560_d", "bpr.h"});
            } else {
                a5 = Minecraft.func_71410_x().func_110442_L().func_110536_a(a2).func_110527_b();
                a4 = ImageIO.read(a5);
            }
            IOUtils.closeQuietly((InputStream)a5);
        }
        catch (IOException a8) {
            a8.printStackTrace();
        }
        finally {
            IOUtils.closeQuietly(a5);
        }
        return a4;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static BufferedImage c(GameProfile a2) {
        BufferedImage a3 = null;
        ResourceLocation a4 = DefaultPlayerSkin.func_177335_a();
        InputStream a5 = null;
        Minecraft a6 = Minecraft.func_71410_x();
        Map a7 = a6.func_152342_ad().func_152788_a(a2);
        try {
            if (a7.containsKey(MinecraftProfileTexture.Type.SKIN)) {
                a4 = a6.func_152342_ad().func_152792_a((MinecraftProfileTexture)a7.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
                ITextureObject a8 = a6.func_110434_K().func_110581_b(a4);
                if (a8 instanceof ThreadDownloadImageData) {
                    ThreadDownloadImageData a9 = (ThreadDownloadImageData)a8;
                    a3 = (BufferedImage)ObfuscationReflectionHelper.getPrivateValue(ThreadDownloadImageData.class, (Object)a9, (String[])new String[]{"bufferedImage", "field_110560_d", "bpr.h"});
                } else {
                    a5 = Minecraft.func_71410_x().func_110442_L().func_110536_a(a4).func_110527_b();
                    a3 = ImageIO.read(a5);
                }
            } else {
                a5 = Minecraft.func_71410_x().func_110442_L().func_110536_a(a4).func_110527_b();
                a3 = ImageIO.read(a5);
            }
            IOUtils.closeQuietly((InputStream)a5);
        }
        catch (IOException a10) {
            a10.printStackTrace();
        }
        finally {
            IOUtils.closeQuietly(a5);
        }
        return a3;
    }

    public static BufferedImage ALLATORIxDEMO(GameProfile a2) {
        BufferedImage a3 = null;
        ResourceLocation a4 = DefaultPlayerSkin.func_177335_a();
        if (a2 != null) {
            a4 = AbstractClientPlayer.func_110311_f((String)a2.getName());
            AbstractClientPlayer.func_110304_a((ResourceLocation)a4, (String)a2.getName());
        }
        if ((a3 = fg.ALLATORIxDEMO(a4)) == null) {
            a3 = fg.ALLATORIxDEMO(DefaultPlayerSkin.func_177335_a());
        }
        return a3;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static /* synthetic */ BufferedImage ALLATORIxDEMO(ResourceLocation a2) {
        BufferedImage a3 = null;
        InputStream a4 = null;
        try {
            ITextureObject a5 = Minecraft.func_71410_x().func_110434_K().func_110581_b(a2);
            if (a5 instanceof ThreadDownloadImageData) {
                ThreadDownloadImageData a6 = (ThreadDownloadImageData)a5;
                a3 = (BufferedImage)ObfuscationReflectionHelper.getPrivateValue(ThreadDownloadImageData.class, (Object)a6, (String[])new String[]{"bufferedImage", "field_110560_d", "bpr.h"});
            } else {
                a4 = Minecraft.func_71410_x().func_110442_L().func_110536_a(a2).func_110527_b();
                a3 = ImageIO.read(a4);
            }
            IOUtils.closeQuietly((InputStream)a4);
        }
        catch (IOException a7) {
            a7.printStackTrace();
        }
        finally {
            IOUtils.closeQuietly(a4);
        }
        return a3;
    }

    public static void ALLATORIxDEMO(GameProfile a2) {
        ResourceLocation a3 = DefaultPlayerSkin.func_177335_a();
        if (a2 != null) {
            a3 = fg.ALLATORIxDEMO(a2, MinecraftProfileTexture.Type.SKIN);
        }
        Minecraft.func_71410_x().field_71446_o.func_110577_a(a3);
    }

    public static ResourceLocation ALLATORIxDEMO(GameProfile a2, MinecraftProfileTexture.Type a3) {
        Minecraft a4;
        Map a5;
        ResourceLocation a6 = DefaultPlayerSkin.func_177335_a();
        if (a2 != null && (a5 = (a4 = Minecraft.func_71410_x()).func_152342_ad().func_152788_a(a2)).containsKey(a3)) {
            a6 = a4.func_152342_ad().func_152792_a((MinecraftProfileTexture)a5.get(a3), a3);
        }
        return a6;
    }

    public static BufferedImage ALLATORIxDEMO(BufferedImage a2) {
        ColorModel a3 = a2.getColorModel();
        boolean a4 = a3.isAlphaPremultiplied();
        WritableRaster a5 = a2.copyData(null);
        return new BufferedImage(a3, a5, a4, null);
    }
}

