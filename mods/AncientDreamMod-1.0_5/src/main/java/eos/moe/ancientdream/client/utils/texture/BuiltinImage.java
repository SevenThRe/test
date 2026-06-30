/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.ancientdream.client.utils.texture;

import eos.moe.ancientdream.client.utils.texture.TextureProvider;
import net.minecraft.util.ResourceLocation;

public class BuiltinImage
implements TextureProvider {
    private ResourceLocation res;

    public BuiltinImage(String res) {
        this(BuiltinImage.ofRes(res));
    }

    public BuiltinImage(ResourceLocation res) {
        this.res = res;
    }

    @Override
    public ResourceLocation getTexture() {
        return this.res;
    }

    @Override
    public ResourceLocation getTexture(ResourceLocation loading) {
        return this.getTexture();
    }

    @Override
    public ResourceLocation getTexture(ResourceLocation loading, ResourceLocation error) {
        return this.getTexture();
    }

    public static BuiltinImage of(String domain, String path) {
        return new BuiltinImage(new ResourceLocation(domain, path));
    }

    public static ResourceLocation ofRes(String resourcePathIn) {
        return new ResourceLocation("ancientdream", resourcePathIn);
    }
}

