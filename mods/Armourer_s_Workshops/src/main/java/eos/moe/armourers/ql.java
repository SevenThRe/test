/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.texture.AbstractTexture
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Tuple
 *  org.apache.commons.io.IOUtils
 */
package eos.moe.armourers;

import eos.moe.armourers.bm;
import eos.moe.armourers.vg;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import org.apache.commons.io.IOUtils;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ql
extends AbstractTexture {
    private HashMap<Integer, BufferedImage> g;
    private static Minecraft z;
    private int t;
    private BufferedImage w;
    private List<Tuple<Integer, Integer>> r;
    private boolean l;
    private int c;
    private String v;
    private static final ScheduledExecutorService s;
    private int m;
    private long j;

    private /* synthetic */ void h(InputStream a2) throws Exception {
        ql a3;
        a2 = a3.r((InputStream)((Object)a2));
        a3.c = ((BufferedImage)a2.get(0).getSecond()).getWidth();
        a3.m = ((BufferedImage)((Tuple)a2.get(0)).getSecond()).getHeight();
        Iterator iterator = a2 = a2.iterator();
        while (iterator.hasNext()) {
            int n2;
            Tuple tuple = (Tuple)a2.next();
            int n3 = n2 = TextureUtil.glGenTextures();
            a3.g.put(n3, (BufferedImage)tuple.getSecond());
            TextureUtil.uploadTextureImageAllocate((int)n3, (BufferedImage)((BufferedImage)tuple.getSecond()), (boolean)false, (boolean)false);
            a3.r.add((Tuple<Integer, Integer>)new Tuple((Object)n2, tuple.getFirst()));
            iterator = a2;
        }
        a3.j = System.currentTimeMillis();
    }

    private /* synthetic */ void z(InputStream a2) {
        ql ql2;
        InputStream inputStream;
        ql a3;
        block7: {
            block6: {
                if (a2 == null) {
                    bm.r(new StringBuilder().insert(0, "[102]\u56fe\u7247InputStream\u4e3anull,\u65e0\u6cd5\u52a0\u8f7d: ").append(a3.v).toString());
                    a3.l = true;
                    return;
                }
                if (!a3.v.endsWith(".gif")) break block6;
                InputStream inputStream2 = a2;
                inputStream = inputStream2;
                a3.h(inputStream2);
                break block7;
            }
            try {
                a3.y(a2);
                inputStream = a2;
            }
            catch (Exception exception) {
                try {
                    bm.r(new StringBuilder().insert(0, "[101]\u65e0\u6cd5\u89e3\u6790InputStream\u4e3a\u56fe\u7247: ").append(a3.v).toString());
                    exception.printStackTrace();
                    ql2 = a3;
                }
                catch (Throwable throwable) {
                    IOUtils.closeQuietly((Closeable)a2);
                    throw throwable;
                }
                IOUtils.closeQuietly((Closeable)a2);
            }
        }
        IOUtils.closeQuietly((Closeable)inputStream);
        ql2 = a3;
        ql2.l = true;
    }

    public boolean isLoadedBufferedImage() {
        ql a2;
        return a2.w != null || a2.j > 0L;
    }

    static {
        s = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        z = Minecraft.getMinecraft();
    }

    public ql(String a2) {
        ql a3;
        ql ql2 = a3;
        a3.t = -1;
        ql ql3 = a3;
        ql2.r = new ArrayList<Tuple<Integer, Integer>>();
        ql2.g = new HashMap();
        ql2.v = a2;
    }

    public void loadTexture(IResourceManager a2) throws IOException {
        ql a3;
        if (a3.v.startsWith("http")) {
            a3.z();
            return;
        }
        a3.r();
    }

    public static void r(String a2) {
        if (a2 == null || a2.isEmpty()) {
            return;
        }
        GlStateManager.bindTexture((int)ql.r(a2).getGlTextureId());
    }

    private /* synthetic */ void z() {
        ql a2;
        CompletableFuture.runAsync(() -> {
            ql a2;
            try {
                InputStream inputStream = new URL(a2.v).openStream();
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    ql a3;
                    a3.z(inputStream);
                });
                return;
            }
            catch (Exception exception) {
                bm.r(new StringBuilder().insert(0, "[103]\u52a0\u8f7d\u7f51\u7edc\u56fe\u7247\u8d44\u6e90\u5931\u8d25: ").append(a2.v).toString());
                exception.printStackTrace();
                return;
            }
        });
    }

    private /* synthetic */ void y(InputStream a2) throws Exception {
        ql a3;
        a3.w = a2 = TextureUtil.readBufferedImage((InputStream)a2);
        a3.c = ((BufferedImage)a3.w).getWidth();
        a3.m = ((BufferedImage)a2).getHeight();
        TextureUtil.uploadTextureImageAllocate((int)a3.getGlTextureId(), (BufferedImage)a2, (boolean)false, (boolean)false);
    }

    public static ql r(String a2) {
        ResourceLocation resourceLocation = new ResourceLocation("dragonarmourers", a2);
        ql ql2 = (ql)z.getTextureManager().getTexture(resourceLocation);
        if (ql2 == null) {
            ql2 = new ql(a2);
            z.getTextureManager().loadTexture(resourceLocation, (ITextureObject)ql2);
        }
        return ql2;
    }

    private /* synthetic */ void r() {
        ql a2;
        ql ql2 = a2;
        ql2.z(((Object)((Object)a2)).getClass().getClassLoader().getResourceAsStream(ql2.v));
    }

    public static Dimension r(String a2) {
        if (a2 != null) {
            ql ql2;
            ql ql3 = ql2 = ql.r(a2);
            return new Dimension(ql3.c, ql3.m);
        }
        return null;
    }

    private /* synthetic */ List<Tuple<Integer, BufferedImage>> r(InputStream a222) throws NullPointerException {
        int n2;
        vg vg2 = new vg();
        InputStream inputStream = a222;
        int a222 = vg2.r(inputStream);
        IOUtils.closeQuietly((Closeable)inputStream);
        if (a222 != vg.u || vg2.h() == 0) {
            throw new NullPointerException();
        }
        ArrayList<Tuple<Integer, BufferedImage>> a222 = new ArrayList<Tuple<Integer, BufferedImage>>();
        int n3 = n2 = 0;
        while (n3 < vg2.h()) {
            a222.add((Tuple<Integer, BufferedImage>)new Tuple((Object)vg2.r(n2), (Object)vg2.r(n2++)));
            n3 = n2;
        }
        return a222;
    }

    public void deleteGlTexture() {
        ql a2;
        if (a2.r.size() > 0) {
            Iterator<Tuple<Integer, Integer>> iterator;
            Iterator<Tuple<Integer, Integer>> iterator2 = iterator = a2.r.iterator();
            while (iterator2.hasNext()) {
                TextureUtil.deleteTexture((int)((Integer)iterator.next().getFirst()));
                iterator2 = iterator;
            }
            a2.r.clear();
        }
        super.deleteGlTexture();
    }

    private /* synthetic */ int r() {
        Iterator<Tuple<Integer, Integer>> iterator;
        ql a2;
        if (a2.t != -1) {
            return a2.t;
        }
        a2.t = 0;
        Iterator<Tuple<Integer, Integer>> iterator2 = iterator = a2.r.iterator();
        while (iterator2.hasNext()) {
            Tuple<Integer, Integer> tuple = iterator.next();
            a2.t += ((Integer)tuple.getSecond()).intValue();
            iterator2 = iterator;
        }
        return a2.t;
    }

    public int getGlTextureId() {
        ql a2;
        if (a2.j != 0L) {
            int n2;
            if (a2.r.size() == 1) {
                return (Integer)a2.r.get(0).getFirst();
            }
            long l2 = System.currentTimeMillis() - a2.j;
            int n3 = 0;
            l2 %= (long)a2.r();
            int n4 = n2 = 0;
            while (n4 < a2.r.size() && l2 > 0L) {
                l2 -= (long)((Integer)a2.r.get(n2).getSecond()).intValue();
                n3 = n2++;
                n4 = n2;
            }
            return (Integer)a2.r.get(n3).getFirst();
        }
        return super.getGlTextureId();
    }
}

