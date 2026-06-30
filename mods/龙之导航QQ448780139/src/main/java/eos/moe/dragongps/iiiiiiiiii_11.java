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
package eos.moe.dragongps;

import eos.moe.dragongps.iiiiiiiiii_3;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
 * Renamed from eos.moe.dragongps.iIIiiiiiii
 */
public class iiiiiiiiii_11
extends AbstractTexture {
    private int iIiIIiIiiI = -1;
    private long iiIIIiIiII;
    private List<Tuple<Integer, Integer>> IIiiIiiIII = new ArrayList<Tuple<Integer, Integer>>();
    private static Minecraft iIIiIIiIii = Minecraft.getMinecraft();
    private int iIIIIiiIII;
    private String IIiIiIIIiI;
    private int IIiiiiiIIi;

    public int getGlTextureId() {
        iiiiiiiiii_11 IIiiiiiIIi;
        if (IIiiiiiIIi.iiIIIiIiII != 0L) {
            if (IIiiiiiIIi.IIiiIiiIII.size() == 1) {
                return (Integer)IIiiiiiIIi.IIiiIiiIII.get(0).getFirst();
            }
            long IIiiiiiIIi2 = System.currentTimeMillis() - IIiiiiiIIi.iiIIIiIiII;
            int IIiiiiiIIi3 = 0;
            IIiiiiiIIi2 %= (long)IIiiiiiIIi.IIIiiiIiii();
            int IIiiiiiIIi4 = 0;
            while (IIiiiiiIIi4 < IIiiiiiIIi.IIiiIiiIII.size() && IIiiiiiIIi2 > 0L) {
                IIiiiiiIIi2 -= (long)((Integer)IIiiiiiIIi.IIiiIiiIII.get(IIiiiiiIIi4).getSecond()).intValue();
                IIiiiiiIIi3 = IIiiiiiIIi4++;
            }
            return (Integer)IIiiiiiIIi.IIiiIiiIII.get(IIiiiiiIIi3).getFirst();
        }
        return super.getGlTextureId();
    }

    private /* synthetic */ int IIIiiiIiii() {
        iiiiiiiiii_11 IIiiiiiIIi;
        if (IIiiiiiIIi.iIiIIiIiiI != -1) {
            return IIiiiiiIIi.iIiIIiIiiI;
        }
        IIiiiiiIIi.iIiIIiIiiI = 0;
        for (Tuple<Integer, Integer> IIiiiiiIIi2 : IIiiiiiIIi.IIiiIiiIII) {
            IIiiiiiIIi.iIiIIiIiiI += ((Integer)IIiiiiiIIi2.getSecond()).intValue();
        }
        return IIiiiiiIIi.iIiIIiIiiI;
    }

    public iiiiiiiiii_11(String IIiiiiiIIi) {
        iiiiiiiiii_11 IIiiiiiIIi2;
        IIiiiiiIIi2.IIiIiIIIiI = IIiiiiiIIi;
    }

    public void deleteGlTexture() {
        iiiiiiiiii_11 IIiiiiiIIi;
        if (IIiiiiiIIi.IIiiIiiIII.size() > 0) {
            for (Tuple<Integer, Integer> IIiiiiiIIi2 : IIiiiiiIIi.IIiiIiiIII) {
                TextureUtil.deleteTexture((int)((Integer)IIiiiiiIIi2.getFirst()));
            }
            IIiiiiiIIi.IIiiIiiIII.clear();
        }
        super.deleteGlTexture();
    }

    public static Dimension IIIiiiIiii(String IIiiiiiIIi) {
        ITextureObject IIiiiiiIIi2;
        if (IIiiiiiIIi != null && (IIiiiiiIIi2 = iiiiiiiiii_11.IIIiiiIiii(IIiiiiiIIi)) instanceof iiiiiiiiii_11) {
            return new Dimension(((iiiiiiiiii_11)IIiiiiiIIi2).iIIIIiiIII, ((iiiiiiiiii_11)IIiiiiiIIi2).IIiiiiiIIi);
        }
        return null;
    }

    private /* synthetic */ List<Tuple<Integer, BufferedImage>> IIIiiiIiii(String IIiiiiiIIi) throws NullPointerException {
        iiiiiiiiii_11 IIiiiiiIIi2;
        iiiiiiiiii_3 IIiiiiiIIi3 = new iiiiiiiiii_3();
        InputStream IIiiiiiIIi4 = ((Object)((Object)IIiiiiiIIi2)).getClass().getClassLoader().getResourceAsStream(IIiiiiiIIi);
        int IIiiiiiIIi5 = IIiiiiiIIi3.IIIiiiIiii(IIiiiiiIIi4);
        IOUtils.closeQuietly((Closeable)IIiiiiiIIi4);
        if (IIiiiiiIIi5 != 0 || IIiiiiiIIi3.IIiiiIIiiI() == 0) {
            throw new NullPointerException();
        }
        ArrayList<Tuple<Integer, BufferedImage>> IIiiiiiIIi6 = new ArrayList<Tuple<Integer, BufferedImage>>();
        for (int IIiiiiiIIi7 = 0; IIiiiiiIIi7 < IIiiiiiIIi3.IIiiiIIiiI(); ++IIiiiiiIIi7) {
            IIiiiiiIIi6.add((Tuple<Integer, BufferedImage>)new Tuple((Object)IIiiiiiIIi3.IIIiiiIiii(IIiiiiiIIi7), (Object)IIiiiiiIIi3.IIIiiiIiii(IIiiiiiIIi7)));
        }
        return IIiiiiiIIi6;
    }

    public static ITextureObject IIIiiiIiii(String IIiiiiiIIi) {
        ResourceLocation IIiiiiiIIi2 = new ResourceLocation("dragontracker", IIiiiiiIIi);
        Object IIiiiiiIIi3 = iIIiIIiIii.getTextureManager().getTexture(IIiiiiiIIi2);
        if (IIiiiiiIIi3 == null) {
            IIiiiiiIIi3 = new iiiiiiiiii_11(IIiiiiiIIi);
            iIIiIIiIii.getTextureManager().loadTexture(IIiiiiiIIi2, IIiiiiiIIi3);
        }
        return IIiiiiiIIi3;
    }

    public static void IIIiiiIiii(String IIiiiiiIIi) {
        ITextureObject IIiiiiiIIi2 = iiiiiiiiii_11.IIIiiiIiii(IIiiiiiIIi);
        GlStateManager.bindTexture((int)IIiiiiiIIi2.getGlTextureId());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void loadTexture(IResourceManager IIiiiiiIIi) throws IOException {
        InputStream IIiiiiiIIi2;
        block8: {
            iiiiiiiiii_11 IIiiiiiIIi3;
            if (IIiiiiiIIi3.IIiIiIIIiI.endsWith(".gif")) {
                try {
                    List<Tuple<Integer, BufferedImage>> IIiiiiiIIi4 = IIiiiiiIIi3.IIIiiiIiii(IIiiiiiIIi3.IIiIiIIIiI);
                    IIiiiiiIIi3.iIIIIiiIII = ((BufferedImage)IIiiiiiIIi4.get(0).getSecond()).getWidth();
                    IIiiiiiIIi3.IIiiiiiIIi = ((BufferedImage)IIiiiiiIIi4.get(0).getSecond()).getHeight();
                    for (Tuple<Integer, BufferedImage> IIiiiiiIIi5 : IIiiiiiIIi4) {
                        int IIiiiiiIIi6 = TextureUtil.glGenTextures();
                        TextureUtil.uploadTextureImageAllocate((int)IIiiiiiIIi6, (BufferedImage)((BufferedImage)IIiiiiiIIi5.getSecond()), (boolean)false, (boolean)false);
                        IIiiiiiIIi3.IIiiIiiIII.add((Tuple<Integer, Integer>)new Tuple((Object)IIiiiiiIIi6, IIiiiiiIIi5.getFirst()));
                    }
                    IIiiiiiIIi3.iiIIIiIiII = System.currentTimeMillis();
                    return;
                }
                catch (Exception IIiiiiiIIi7) {
                    IIiiiiiIIi7.printStackTrace();
                }
                return;
            }
            IIiiiiiIIi2 = null;
            try {
                IIiiiiiIIi2 = ((Object)((Object)IIiiiiiIIi3)).getClass().getClassLoader().getResourceAsStream(IIiiiiiIIi3.IIiIiIIIiI);
                if (IIiiiiiIIi2 == null) break block8;
                BufferedImage IIiiiiiIIi8 = TextureUtil.readBufferedImage((InputStream)IIiiiiiIIi2);
                IIiiiiiIIi3.iIIIIiiIII = IIiiiiiIIi8.getWidth();
                IIiiiiiIIi3.IIiiiiiIIi = IIiiiiiIIi8.getHeight();
                TextureUtil.uploadTextureImageAllocate((int)IIiiiiiIIi3.getGlTextureId(), (BufferedImage)IIiiiiiIIi8, (boolean)false, (boolean)false);
            }
            catch (Exception IIiiiiiIIi9) {
                try {
                    IIiiiiiIIi9.printStackTrace();
                }
                catch (Throwable throwable) {
                    IOUtils.closeQuietly(IIiiiiiIIi2);
                    throw throwable;
                }
                IOUtils.closeQuietly((Closeable)IIiiiiiIIi2);
                return;
            }
        }
        IOUtils.closeQuietly((Closeable)IIiiiiiIIi2);
        return;
    }
}

