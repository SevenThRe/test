/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ThreadDownloadImageData
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.util.ResourceLocation
 */
package goblinbob.mobends.core.pack;

import goblinbob.mobends.core.pack.PackCache;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.util.ResourceLocation;

public class ThumbnailProvider {
    public static final ResourceLocation DEFAULT_THUMBNAIL_LOCATION = new ResourceLocation("mobends", "textures/gui/default_pack_thumbnail.png");
    private final PackCache packCache;

    public ThumbnailProvider(PackCache packCache) {
        this.packCache = packCache;
    }

    public ResourceLocation getThumbnailLocation(String packName, String thumbnailUrl) {
        ResourceLocation resourceLocation = new ResourceLocation("mobends", "bendsPackThumbnails/" + packName);
        ITextureObject itextureobject = Minecraft.func_71410_x().func_110434_K().func_110581_b(resourceLocation);
        if (itextureobject == null) {
            ThreadDownloadImageData threaddownloadimagedata = new ThreadDownloadImageData(this.packCache.getThumbnailFile(packName), thumbnailUrl, DEFAULT_THUMBNAIL_LOCATION, null);
            if (Minecraft.func_71410_x().func_110434_K().func_110579_a(resourceLocation, (ITextureObject)threaddownloadimagedata)) {
                return resourceLocation;
            }
        }
        return DEFAULT_THUMBNAIL_LOCATION;
    }
}

