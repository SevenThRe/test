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
package eos.moe.armourers;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
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
public class bh {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static /* synthetic */ BufferedImage y(ResourceLocation a2) {
        InputStream inputStream;
        BufferedImage bufferedImage;
        block7: {
            bufferedImage = null;
            InputStream inputStream2 = null;
            try {
                ITextureObject iTextureObject = Minecraft.getMinecraft().getTextureManager().getTexture(a2);
                if (iTextureObject instanceof ThreadDownloadImageData) {
                    ThreadDownloadImageData threadDownloadImageData = (ThreadDownloadImageData)iTextureObject;
                    String[] stringArray = new String[3];
                    stringArray[0] = "bufferedImage";
                    stringArray[1] = "bufferedImage";
                    stringArray[2] = "bpr.h";
                    bufferedImage = (BufferedImage)ObfuscationReflectionHelper.getPrivateValue(ThreadDownloadImageData.class, (Object)threadDownloadImageData, (String[])stringArray);
                    inputStream = inputStream2;
                    break block7;
                }
                inputStream2 = Minecraft.getMinecraft().getResourceManager().getResource(a2).getInputStream();
                bufferedImage = ImageIO.read(inputStream2);
                inputStream = inputStream2;
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
                return bufferedImage;
            }
            finally {
                IOUtils.closeQuietly(inputStream2);
            }
        }
        IOUtils.closeQuietly((InputStream)inputStream);
        return bufferedImage;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static BufferedImage y(GameProfile a2) {
        BufferedImage bufferedImage = null;
        ResourceLocation resourceLocation = DefaultPlayerSkin.getDefaultSkinLegacy();
        InputStream inputStream = null;
        Minecraft minecraft = Minecraft.getMinecraft();
        Map map = minecraft.getSkinManager().loadSkinFromCache(a2);
        try {
            if (map.containsKey(MinecraftProfileTexture.Type.SKIN)) {
                resourceLocation = minecraft.getSkinManager().loadSkin((MinecraftProfileTexture)map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
                ITextureObject iTextureObject = minecraft.getTextureManager().getTexture(resourceLocation);
                if (iTextureObject instanceof ThreadDownloadImageData) {
                    minecraft = (ThreadDownloadImageData)iTextureObject;
                    String[] stringArray = new String[3];
                    stringArray[0] = "bufferedImage";
                    stringArray[1] = "bufferedImage";
                    stringArray[2] = "bpr.h";
                    bufferedImage = (BufferedImage)ObfuscationReflectionHelper.getPrivateValue(ThreadDownloadImageData.class, (Object)minecraft, (String[])stringArray);
                } else {
                    inputStream = Minecraft.getMinecraft().getResourceManager().getResource(resourceLocation).getInputStream();
                    bufferedImage = ImageIO.read(inputStream);
                }
            } else {
                inputStream = Minecraft.getMinecraft().getResourceManager().getResource(resourceLocation).getInputStream();
                bufferedImage = ImageIO.read(inputStream);
            }
            IOUtils.closeQuietly((InputStream)inputStream);
            return bufferedImage;
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            return bufferedImage;
        }
        finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static BufferedImage r(ResourceLocation a2) {
        InputStream inputStream;
        BufferedImage bufferedImage;
        block7: {
            Minecraft minecraft = Minecraft.getMinecraft();
            bufferedImage = null;
            InputStream inputStream2 = null;
            try {
                minecraft = minecraft.getTextureManager().getTexture(a2);
                if (minecraft instanceof ThreadDownloadImageData) {
                    ThreadDownloadImageData threadDownloadImageData = (ThreadDownloadImageData)minecraft;
                    String[] stringArray = new String[3];
                    stringArray[0] = "bufferedImage";
                    stringArray[1] = "bufferedImage";
                    stringArray[2] = "bpr.h";
                    bufferedImage = (BufferedImage)ObfuscationReflectionHelper.getPrivateValue(ThreadDownloadImageData.class, (Object)threadDownloadImageData, (String[])stringArray);
                    inputStream = inputStream2;
                    break block7;
                }
                inputStream2 = Minecraft.getMinecraft().getResourceManager().getResource(a2).getInputStream();
                bufferedImage = ImageIO.read(inputStream2);
                inputStream = inputStream2;
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
                return bufferedImage;
            }
            finally {
                IOUtils.closeQuietly(inputStream2);
            }
        }
        IOUtils.closeQuietly((InputStream)inputStream);
        return bufferedImage;
    }

    public bh() {
        bh a2;
    }

    public static ResourceLocation r(GameProfile a2, MinecraftProfileTexture.Type a3) {
        Minecraft minecraft;
        Map map;
        ResourceLocation resourceLocation = DefaultPlayerSkin.getDefaultSkinLegacy();
        if (a2 != null && (map = (minecraft = Minecraft.getMinecraft()).getSkinManager().loadSkinFromCache(a2)).containsKey(a3)) {
            resourceLocation = minecraft.getSkinManager().loadSkin((MinecraftProfileTexture)map.get(a3), a3);
        }
        return resourceLocation;
    }

    public static KeyPair r() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            int n2 = 1024;
            KeyPairGenerator keyPairGenerator2 = keyPairGenerator;
            keyPairGenerator2.initialize(n2, new SecureRandom());
            return keyPairGenerator2.generateKeyPair();
        }
        catch (Exception exception) {
            return null;
        }
    }

    public static BufferedImage r(GameProfile a2) {
        BufferedImage bufferedImage = null;
        ResourceLocation resourceLocation = DefaultPlayerSkin.getDefaultSkinLegacy();
        if (a2 != null) {
            resourceLocation = AbstractClientPlayer.getLocationSkin((String)a2.getName());
            AbstractClientPlayer.getDownloadImageSkin((ResourceLocation)resourceLocation, (String)a2.getName());
        }
        if ((bufferedImage = bh.y(resourceLocation)) == null) {
            bufferedImage = bh.y(DefaultPlayerSkin.getDefaultSkinLegacy());
        }
        return bufferedImage;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static BufferedImage r(AbstractClientPlayer a2) {
        InputStream inputStream;
        BufferedImage bufferedImage;
        block7: {
            bufferedImage = null;
            ResourceLocation resourceLocation = DefaultPlayerSkin.getDefaultSkinLegacy();
            InputStream inputStream2 = null;
            Minecraft minecraft = Minecraft.getMinecraft();
            resourceLocation = a2.getLocationSkin();
            try {
                minecraft = minecraft.getTextureManager().getTexture(resourceLocation);
                if (minecraft instanceof ThreadDownloadImageData) {
                    ThreadDownloadImageData threadDownloadImageData = (ThreadDownloadImageData)minecraft;
                    String[] stringArray = new String[3];
                    stringArray[0] = "bufferedImage";
                    stringArray[1] = "bufferedImage";
                    stringArray[2] = "bpr.h";
                    bufferedImage = (BufferedImage)ObfuscationReflectionHelper.getPrivateValue(ThreadDownloadImageData.class, (Object)threadDownloadImageData, (String[])stringArray);
                    inputStream = inputStream2;
                    break block7;
                }
                inputStream2 = Minecraft.getMinecraft().getResourceManager().getResource(resourceLocation).getInputStream();
                bufferedImage = ImageIO.read(inputStream2);
                inputStream = inputStream2;
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
                return bufferedImage;
            }
            finally {
                IOUtils.closeQuietly(inputStream2);
            }
        }
        IOUtils.closeQuietly((InputStream)inputStream);
        return bufferedImage;
    }

    public static BufferedImage r(BufferedImage a2) {
        BufferedImage bufferedImage = a2;
        ColorModel colorModel = bufferedImage.getColorModel();
        boolean bl = colorModel.isAlphaPremultiplied();
        WritableRaster writableRaster = bufferedImage.copyData(null);
        return new BufferedImage(colorModel, writableRaster, bl, null);
    }

    public static void r(GameProfile a2) {
        ResourceLocation resourceLocation = DefaultPlayerSkin.getDefaultSkinLegacy();
        if (a2 != null) {
            resourceLocation = bh.r(a2, MinecraftProfileTexture.Type.SKIN);
        }
        Minecraft.getMinecraft().renderEngine.bindTexture(resourceLocation);
    }
}

