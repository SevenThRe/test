/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.ThreadDownloadImageData
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.common.ObfuscationReflectionHelper
 *  org.apache.commons.io.IOUtils
 */
package eos.moe.armourers;

import eos.moe.armourers.bh;
import eos.moe.armourers.lo;
import eos.moe.armourers.te;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.apache.commons.io.IOUtils;

public class nl {
    private int z;
    private boolean t;
    private int w;
    private Minecraft r;
    private BufferedImage l;
    private int[] c;
    public static int v = 64;
    private BufferedImage s;
    public static int m = 32;
    public static int j = v * m;

    private /* synthetic */ void s() {
        int n2;
        int n3 = n2 = 0;
        while (n3 < v) {
            nl a2;
            int n4;
            int n5 = n4 = 0;
            while (n5 < m && a2.l != null) {
                int n6 = n2;
                a2.s.setRGB(n6, n4, a2.l.getRGB(n6, n4++));
                n5 = n4;
            }
            n3 = ++n2;
        }
    }

    public nl() {
        nl a2;
        nl nl2 = a2;
        nl nl3 = a2;
        nl3.r = Minecraft.func_71410_x();
        nl nl4 = a2;
        nl3.s = new BufferedImage(v, m, 2);
        nl3.w = -1;
        nl2.t = true;
        nl2.z = -1;
        nl2.c = new int[j];
    }

    public void r(int[] a2) {
        nl a3;
        if (!Arrays.equals(a2, a3.c)) {
            a3.c = (int[])a2.clone();
            a3.t = true;
        }
    }

    public void finalize() throws Throwable {
        nl a2;
        nl nl2 = a2;
        nl2.h();
        super.finalize();
    }

    private /* synthetic */ void x() {
        nl a2;
        nl nl2 = a2;
        nl2.h();
        nl2.z = TextureUtil.func_110996_a();
        TextureUtil.func_110987_a((int)nl2.z, (BufferedImage)a2.s);
    }

    private /* synthetic */ void h() {
        nl a2;
        if (a2.z != -1) {
            TextureUtil.func_147942_a((int)a2.z);
            a2.z = -1;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void r(ResourceLocation a2) {
        BufferedImage bufferedImage;
        InputStream inputStream;
        BufferedImage bufferedImage2;
        nl a3;
        block9: {
            nl nl2;
            boolean bl;
            if (a3.w == a2.hashCode()) {
                bl = true;
                nl2 = a3;
            } else {
                bl = false;
                nl2 = a3;
            }
            if (bl & nl2.l != null) {
                return;
            }
            bufferedImage2 = null;
            InputStream inputStream2 = null;
            try {
                ITextureObject iTextureObject = a3.r.func_110434_K().func_110581_b(a2);
                if (iTextureObject instanceof ThreadDownloadImageData) {
                    ThreadDownloadImageData threadDownloadImageData = (ThreadDownloadImageData)iTextureObject;
                    String[] stringArray = new String[3];
                    stringArray[0] = "bufferedImage";
                    stringArray[1] = "field_110560_d";
                    stringArray[2] = "bpr.h";
                    bufferedImage2 = (BufferedImage)ObfuscationReflectionHelper.getPrivateValue(ThreadDownloadImageData.class, (Object)threadDownloadImageData, (String[])stringArray);
                    inputStream = inputStream2;
                    break block9;
                }
                inputStream2 = Minecraft.func_71410_x().func_110442_L().func_110536_a(a2).func_110527_b();
                bufferedImage2 = ImageIO.read(inputStream2);
                inputStream = inputStream2;
            }
            catch (IOException iOException) {
                try {
                    iOException.printStackTrace();
                    bufferedImage = bufferedImage2;
                }
                catch (Throwable throwable) {
                    IOUtils.closeQuietly(inputStream2);
                    throw throwable;
                }
                IOUtils.closeQuietly(inputStream2);
            }
        }
        IOUtils.closeQuietly((InputStream)inputStream);
        bufferedImage = bufferedImage2;
        if (bufferedImage == null) {
            return;
        }
        nl nl3 = a3;
        nl3.l = bh.r(bufferedImage2);
        nl3.w = a2.hashCode();
        a3.t = true;
    }

    private /* synthetic */ void z() {
        nl a2;
        nl nl2 = a2;
        a2.s();
        nl2.y();
        nl2.x();
        nl2.t = false;
    }

    private /* synthetic */ void y() {
        int n2;
        int n3 = n2 = 0;
        while (n3 < v) {
            int n4;
            int n5 = n4 = 0;
            while (n5 < m) {
                nl a2;
                int n6 = a2.c[n2 + n4 * v];
                if (te.y(n6) != te.f) {
                    a2.s.setRGB(n2, n4, lo.r(n6, 0, 255));
                }
                n5 = ++n4;
            }
            n3 = ++n2;
        }
    }

    public void r() {
        nl a2;
        if (a2.t) {
            a2.z();
        }
        GlStateManager.func_179144_i((int)a2.z);
    }
}

