/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.DynamicTexture
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.ancientdream.client.utils.texture;

import eos.moe.ancientdream.AncientDream;
import eos.moe.ancientdream.client.utils.texture.BuiltinImage;
import eos.moe.ancientdream.client.utils.texture.RemoteImage;
import java.io.File;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

public interface TextureProvider {
    public static final int[] ID = new int[]{0};

    public ResourceLocation getTexture();

    public ResourceLocation getTexture(ResourceLocation var1);

    public ResourceLocation getTexture(ResourceLocation var1, ResourceLocation var2);

    public static TextureProvider of(String path, ResourceLocation missing) {
        if (path.startsWith("http://") || path.startsWith("https://")) {
            return RemoteImage.of(path, missing);
        }
        if (path.startsWith("location:")) {
            return new BuiltinImage(new ResourceLocation(path.substring(9)));
        }
        if (path.startsWith("file:")) {
            File file = new File(path.substring(5));
            if (!file.exists()) {
                AncientDream.getLogger().error("couldn't load image from " + path + ", file not exists");
            } else {
                try {
                    ResourceLocation location = Minecraft.func_71410_x().func_110434_K().func_110578_a("ancientdream_file_image_" + ID[0], new DynamicTexture(ImageIO.read(file)));
                    ID[0] = ID[0] + 1;
                    return new BuiltinImage(location);
                }
                catch (Exception e) {
                    AncientDream.getLogger().error("error while loading image from file " + path, (Throwable)e);
                }
            }
        }
        return new BuiltinImage(path);
    }
}

