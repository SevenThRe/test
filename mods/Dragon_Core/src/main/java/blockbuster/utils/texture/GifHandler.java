/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Tuple
 *  org.apache.commons.io.IOUtils
 */
package blockbuster.utils.texture;

import blockbuster.utils.ReflectionUtils;
import blockbuster.utils.texture.GifDecoder;
import blockbuster.utils.texture.GifTexture;
import blockbuster.utils.texture.MipmapTexture;
import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import org.apache.commons.io.IOUtils;

public class GifHandler {
    public static Map<ResourceLocation, GifTexture> gifs = new ConcurrentHashMap<ResourceLocation, GifTexture>();

    public static void registerGif(ResourceLocation rl2, GifTexture texture) {
        GifTexture old = gifs.remove(rl2);
        if (old != null) {
            old.deleteGlTexture();
        }
        gifs.put(rl2, texture);
    }

    public static void removeGif(ResourceLocation location) {
        gifs.remove(location);
    }

    public static void bindTexture(ResourceLocation location, int ticks, float partialTicks) {
        Minecraft mc2 = Minecraft.getMinecraft();
        Map<ResourceLocation, ITextureObject> map = ReflectionUtils.getTextures(mc2.renderEngine);
        if (!map.containsKey(location) && location.getPath().endsWith(".gif")) {
            try {
                InputStream stream = Minecraft.getMinecraft().getResourceManager().getResource(location).getInputStream();
                List<Tuple<Integer, BufferedImage>> gifImages = GifHandler.getGIFImages(stream);
                GifTexture texture = new GifTexture(location);
                texture.width = ((BufferedImage)gifImages.get(0).getSecond()).getWidth();
                texture.height = ((BufferedImage)gifImages.get(0).getSecond()).getHeight();
                int frames = gifImages.size();
                map.put(location, (ITextureObject)texture);
                GifHandler.registerGif(location, texture);
                for (int i2 = 0; i2 < frames; ++i2) {
                    BufferedImage buffer = (BufferedImage)gifImages.get(i2).getSecond();
                    int delay = (Integer)gifImages.get(i2).getFirst();
                    texture.add(delay, MipmapTexture.bytesFromBuffer(buffer));
                }
                texture.calculateDuration();
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        GifTexture.bindTexture(location, ticks, partialTicks);
    }

    private static List<Tuple<Integer, BufferedImage>> getGIFImages(InputStream inputStream) throws NullPointerException {
        GifDecoder decoder = new GifDecoder();
        int status = decoder.read(inputStream);
        IOUtils.closeQuietly((Closeable)inputStream);
        if (status != GifDecoder.STATUS_OK || decoder.getFrameCount() == 0) {
            throw new NullPointerException();
        }
        ArrayList<Tuple<Integer, BufferedImage>> list = new ArrayList<Tuple<Integer, BufferedImage>>();
        for (int i2 = 0; i2 < decoder.getFrameCount(); ++i2) {
            list.add((Tuple<Integer, BufferedImage>)new Tuple((Object)decoder.getDelay(i2), (Object)decoder.getFrame(i2)));
        }
        return list;
    }
}

