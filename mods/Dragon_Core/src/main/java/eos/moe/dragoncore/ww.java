/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.texture.AbstractTexture
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.ITickable
 *  net.minecraft.client.renderer.texture.PngSizeInfo
 *  net.minecraft.client.renderer.texture.SimpleTexture
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Tuple
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 *  org.apache.commons.io.IOUtils
 *  org.apache.commons.lang3.math.NumberUtils
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ap;
import eos.moe.dragoncore.eh;
import eos.moe.dragoncore.gp;
import eos.moe.dragoncore.gs;
import eos.moe.dragoncore.jt;
import eos.moe.dragoncore.qx;
import eos.moe.dragoncore.rv;
import eos.moe.dragoncore.ud;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.client.renderer.texture.PngSizeInfo;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.math.NumberUtils;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ww
extends AbstractTexture
implements ITickable {
    private static SimpleTexture s;
    public String g;
    public boolean t;
    public boolean r;
    private boolean x;
    private List<qx> v = new CopyOnWriteArrayList<qx>();
    private int m;
    private long c;
    public int q;
    public int b;
    public jt o;
    public static ExecutorService y;
    public static ww k;
    public static Map<String, gs> ALLATORIxDEMO;

    public static void x() {
        ResourceLocation a2 = new ResourceLocation("dragoncore", "alpha0.png");
        s = new SimpleTexture(a2);
        Minecraft.func_71410_x().func_110434_K().func_110579_a(a2, (ITextureObject)s);
    }

    public ww(String a2) {
        ww a3;
        a3.g = a2;
        a3.x = true;
        if (a3.g.isEmpty()) {
            a3.t = true;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void func_110551_a(IResourceManager a2) throws IOException {
        block12: {
            ww a3;
            if (a3.g.startsWith("data:image/") && a3.g.contains(",")) {
                y.submit(() -> {
                    ByteArrayInputStream a2 = null;
                    try {
                        ww a3;
                        String[] a4 = a3.g.split(",", 2);
                        a2 = new ByteArrayInputStream(Base64.getDecoder().decode(a4[1]));
                        a3.ALLATORIxDEMO(a2);
                        IOUtils.closeQuietly((InputStream)a2);
                    }
                    catch (Exception a5) {
                        a5.printStackTrace();
                        a3.t = true;
                    }
                    finally {
                        IOUtils.closeQuietly(a2);
                    }
                });
            } else if (a3.g.startsWith("http")) {
                URL a4 = new URL(a3.g);
                y.submit(() -> {
                    ByteArrayInputStream a3 = null;
                    try {
                        ww a4;
                        a3 = new ByteArrayInputStream(ud.ALLATORIxDEMO(a4.toURI()));
                        a4.ALLATORIxDEMO(a3);
                    }
                    catch (Exception a5) {
                        try {
                            a5.printStackTrace();
                            a4.t = true;
                        }
                        catch (Throwable throwable) {
                            IOUtils.closeQuietly(a3);
                            throw throwable;
                        }
                        IOUtils.closeQuietly((InputStream)a3);
                    }
                    IOUtils.closeQuietly((InputStream)a3);
                });
            } else {
                try {
                    IResource a5 = a2.func_110536_a(new ResourceLocation("dragoncore", a3.g));
                    if (!a5.func_110528_c()) {
                        InputStream a6 = a5.func_110527_b();
                        y.submit(() -> {
                            try {
                                ww a4;
                                a4.ALLATORIxDEMO(a6);
                            }
                            catch (Exception a5) {
                                a5.printStackTrace();
                                a4.t = true;
                            }
                            finally {
                                IOUtils.closeQuietly((Closeable)a5);
                            }
                        });
                        break block12;
                    }
                    try {
                        a3.ALLATORIxDEMO(a5);
                    }
                    catch (Exception a7) {
                        a7.printStackTrace();
                        a3.t = true;
                    }
                    finally {
                        IOUtils.closeQuietly((Closeable)a5);
                    }
                }
                catch (Exception a8) {
                    a3.t = true;
                    throw a8;
                }
            }
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(IResource a2) throws IOException {
        ww a3;
        IResource a4 = Minecraft.func_71410_x().func_110442_L().func_110536_a(new ResourceLocation("dragoncore", a3.g));
        PngSizeInfo a5 = PngSizeInfo.func_188532_a((IResource)a4);
        int a6 = Minecraft.func_71410_x().field_71474_y.field_151442_I;
        a3.o = new jt(a3.g);
        a3.o.func_188538_a(a5, true);
        a3.o.func_188539_a(a2, a6 + 1);
        a3.o.func_147963_d(a6);
        TextureUtil.func_180600_a((int)a3.func_110552_b(), (int)a6, (int)a3.o.func_94211_a(), (int)a3.o.func_94216_b());
        TextureUtil.func_147955_a((int[][])a3.o.func_147965_a(0), (int)a3.o.func_94211_a(), (int)a3.o.func_94216_b(), (int)a3.o.func_130010_a(), (int)a3.o.func_110967_i(), (boolean)false, (boolean)false);
        List a7 = (List)ReflectionHelper.getPrivateValue(TextureManager.class, (Object)Minecraft.func_71410_x().func_110434_K(), (String[])new String[]{"listTickables", "field_110583_b"});
        a7.add(a3);
        GlStateManager.func_179144_i((int)a3.func_110552_b());
        a3.o.func_94219_l();
    }

    public void func_110550_d() {
        ww a2;
        if (a2.o != null) {
            GlStateManager.func_179144_i((int)a2.func_110552_b());
            a2.o.func_94219_l();
        }
    }

    public jt getTextureAtlasSprite() {
        ww a2;
        return a2.o;
    }

    private /* synthetic */ void ALLATORIxDEMO(InputStream a2) throws IOException {
        ww a3;
        if (a3.g.endsWith(".gif") || a3.g.contains("image/gif")) {
            a3.loadGif(a2);
        } else {
            a3.loadPng(a2);
        }
        a3.r = true;
    }

    public void loadPng(InputStream a2) throws IOException {
        ww a3;
        BufferedImage a4 = ImageIO.read(a2);
        a3.q = a4.getWidth();
        a3.b = a4.getHeight();
        int[] a5 = a3.getBufferImageData(a4);
        qx a6 = new qx(a4, 1000, -1);
        a3.v.add(a6);
        Minecraft.func_71410_x().func_152344_a(() -> {
            ww a4;
            qx.ALLATORIxDEMO(a6, TextureUtil.func_110996_a());
            a4.updateDynamicTexture(qx.c(a6), a5, a4.q, a4.b);
        });
    }

    public void loadGif(InputStream a2) throws IOException {
        byte[] a3 = IOUtils.toByteArray((InputStream)a2);
        a2 = new ByteArrayInputStream(a3);
        List<Tuple<Integer, BufferedImage>> a4 = ww.ALLATORIxDEMO(a2);
        a6.q = ((BufferedImage)a4.get(0).func_76340_b()).getWidth();
        a6.b = ((BufferedImage)a4.get(0).func_76340_b()).getHeight();
        for (Tuple<Integer, BufferedImage> a5 : a4) {
            ww a6;
            int[] a7 = a6.getBufferImageData((BufferedImage)a5.func_76340_b());
            qx a8 = new qx((BufferedImage)a5.func_76340_b(), (Integer)a5.func_76341_a(), -1);
            a6.v.add(a8);
            Minecraft.func_71410_x().func_152344_a(() -> {
                ww a4;
                qx.ALLATORIxDEMO(a8, TextureUtil.func_110996_a());
                a4.updateDynamicTexture(qx.c(a8), a7, a4.q, a4.b);
            });
        }
    }

    public int[] getBufferImageData(BufferedImage a2) {
        int[] a3 = new int[a2.getWidth() * a2.getHeight()];
        a2.getRGB(0, 0, a2.getWidth(), a2.getHeight(), a3, 0, a2.getWidth());
        return a3;
    }

    public void updateDynamicTexture(int a2, int[] a3, int a4, int a5) {
        TextureUtil.func_110991_a((int)a2, (int)a4, (int)a5);
        TextureUtil.func_110988_a((int)a2, (int[])a3, (int)a4, (int)a5);
    }

    private static /* synthetic */ List<Tuple<Integer, BufferedImage>> ALLATORIxDEMO(InputStream a2) throws NullPointerException {
        eh a3 = new eh();
        int a4 = a3.ALLATORIxDEMO(a2);
        IOUtils.closeQuietly((Closeable)a2);
        if (a4 != eh.za || a3.d() == 0) {
            throw new NullPointerException();
        }
        ArrayList<Tuple<Integer, BufferedImage>> a5 = new ArrayList<Tuple<Integer, BufferedImage>>();
        for (int a6 = 0; a6 < a3.d(); ++a6) {
            a5.add((Tuple<Integer, BufferedImage>)new Tuple((Object)a3.ALLATORIxDEMO(a6), (Object)a3.ALLATORIxDEMO(a6)));
        }
        return a5;
    }

    public int func_110552_b() {
        ww a2;
        if (a2.t) {
            return -1;
        }
        int a3 = -1;
        if (a2.o != null) {
            a3 = super.func_110552_b();
        }
        if (a2.v.size() > 0) {
            a2.f();
            a3 = qx.c(a2.v.get(a2.m));
        }
        return a3 == -1 ? s.func_110552_b() : a3;
    }

    public void func_147631_c() {
        ww a2;
        super.func_147631_c();
        for (qx a3 : a2.v) {
            if (qx.c(a3) == -1) continue;
            TextureUtil.func_147942_a((int)qx.c(a3));
        }
    }

    public ap toATextureCIItem() {
        ww a2;
        if (a2.v.size() > 0) {
            a2.f();
            ap a3 = new ap(a2.g);
            a3.updateIcon(qx.ALLATORIxDEMO(a2.v.get(a2.m)));
            return a3;
        }
        return null;
    }

    public boolean isLoaded() {
        ww a2;
        return a2.r;
    }

    private /* synthetic */ void f() {
        ww a2;
        long a3 = System.currentTimeMillis();
        if (a2.x && a3 > a2.c) {
            ++a2.m;
            if (a2.m >= a2.v.size()) {
                a2.m = 0;
            }
            a2.c = a3 + (long)qx.ALLATORIxDEMO(a2.v.get(a2.m));
        }
    }

    public static void c() {
        y.shutdownNow();
    }

    public static void ALLATORIxDEMO(String a2) {
        GlStateManager.func_179144_i((int)ww.ALLATORIxDEMO(a2).func_110552_b());
    }

    public static ww ALLATORIxDEMO(String a2) {
        ResourceLocation a3;
        a2 = ww.ALLATORIxDEMO(a2);
        TextureManager a4 = Minecraft.func_71410_x().func_110434_K();
        Object a5 = a4.func_110581_b(a3 = new ResourceLocation("dragoncore", a2));
        if (a5 == null) {
            a5 = new ww(a2);
            a4.func_110579_a(a3, a5);
        }
        if (a5 == TextureUtil.field_111001_a) {
            a5 = k;
        }
        return (ww)((Object)a5);
    }

    public static ITextureObject ALLATORIxDEMO(String a2) {
        ResourceLocation a3;
        a2 = ww.ALLATORIxDEMO(a2);
        TextureManager a4 = Minecraft.func_71410_x().func_110434_K();
        Object a5 = a4.func_110581_b(a3 = new ResourceLocation("dragoncore", a2));
        if (a5 == null) {
            a5 = new ww(a2);
            a4.func_110579_a(a3, a5);
        }
        if (a5 == TextureUtil.field_111001_a) {
            a5 = k;
        }
        return a5;
    }

    private static /* synthetic */ String ALLATORIxDEMO(String a2) {
        if (!a2.contains("|")) {
            return a2;
        }
        gs a3 = ALLATORIxDEMO.get(a2);
        if (a3 == null) {
            String[] a4;
            ArrayList<gp> a5 = new ArrayList<gp>();
            for (String a6 : a4 = a2.split("\\|")) {
                String[] a7 = a6.split(",");
                String a8 = a7[0];
                int a9 = 1000;
                if (a7.length == 2) {
                    a9 = Math.max(1, NumberUtils.toInt((String)a7[1], (int)1000));
                }
                a5.add(new gp(a8, a9));
            }
            a3 = new gs(a5);
            ALLATORIxDEMO.put(a2, a3);
        }
        if (gs.ALLATORIxDEMO(a3).size() == 0) {
            return "unknown.png";
        }
        return a3.ALLATORIxDEMO();
    }

    public List<qx> getImageList() {
        ww a2;
        return a2.v;
    }

    public int getImageIndex() {
        ww a2;
        return a2.m;
    }

    public long getNextTime() {
        ww a2;
        return a2.c;
    }

    public void setImageList(List<qx> a2) {
        a.v = a2;
    }

    public void setImageIndex(int a2) {
        a.m = a2;
    }

    public void setNextTime(long a2) {
        a.c = a2;
    }

    public boolean isPlay() {
        ww a2;
        return a2.x;
    }

    public void setPlay(boolean a2) {
        a.x = a2;
    }

    static {
        k = new ww("");
        y = Executors.newCachedThreadPool(new rv());
        ALLATORIxDEMO = new HashMap<String, gs>();
    }
}

