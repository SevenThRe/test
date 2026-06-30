/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.texture.AbstractTexture
 *  net.minecraft.client.renderer.texture.ITickable
 *  net.minecraft.client.renderer.texture.PngSizeInfo
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.client.resources.SimpleResource
 *  net.minecraft.client.resources.data.AnimationMetadataSection
 *  net.minecraft.client.resources.data.AnimationMetadataSectionSerializer
 *  net.minecraft.client.resources.data.IMetadataSectionSerializer
 *  net.minecraft.client.resources.data.MetadataSerializer
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 */
package eos.moe.armourers;

import eos.moe.armourers.ModelData;
import eos.moe.armourers.ch;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.client.renderer.texture.PngSizeInfo;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.SimpleResource;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.client.resources.data.AnimationMetadataSectionSerializer;
import net.minecraft.client.resources.data.IMetadataSectionSerializer;
import net.minecraft.client.resources.data.MetadataSerializer;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class zj
extends AbstractTexture
implements ITickable {
    private static MetadataSerializer s;
    private ch m;
    private final ModelData.Texture j;

    public void tick() {
        zj a2;
        if (a2.m != null) {
            zj zj2 = a2;
            GlStateManager.bindTexture((int)zj2.getGlTextureId());
            zj2.m.updateAnimation();
        }
    }

    public zj(ModelData.Texture a2) {
        zj a3;
        a3.j = a2;
    }

    public void loadTexture(IResourceManager a22) throws IOException {
        PngSizeInfo pngSizeInfo;
        zj a3;
        block5: {
            block4: {
                if (s == null) {
                    s = new MetadataSerializer();
                    s.registerMetadataSectionType((IMetadataSectionSerializer)new AnimationMetadataSectionSerializer(), AnimationMetadataSection.class);
                }
                zj zj2 = a3;
                zj2.deleteGlTexture();
                a22 = zj2.j.getMeta();
                pngSizeInfo = new PngSizeInfo((InputStream)new ByteArrayInputStream(a3.j.getData()));
                if (a22 == null) break block4;
                PngSizeInfo pngSizeInfo2 = pngSizeInfo;
                if (pngSizeInfo2.pngHeight / pngSizeInfo2.pngWidth >= 2) break block5;
            }
            BufferedImage bufferedImage = TextureUtil.readBufferedImage((InputStream)new ByteArrayInputStream(a3.j.getData()));
            TextureUtil.uploadTextureImageAllocate((int)a3.getGlTextureId(), (BufferedImage)bufferedImage, (boolean)false, (boolean)false);
            return;
        }
        SimpleResource simpleResource = new SimpleResource(a3.j.getName(), null, (InputStream)new ByteArrayInputStream(a3.j.getData()), (InputStream)new ByteArrayInputStream(((String)a22).getBytes(StandardCharsets.UTF_8)), s);
        int a22 = Minecraft.getMinecraft().gameSettings.mipmapLevels;
        zj zj3 = a3;
        a3.m = new ch(a3.j.getName());
        zj3.m.loadSprite(pngSizeInfo, true);
        zj3.m.loadSpriteFrames((IResource)simpleResource, a22 + 1);
        zj3.m.generateMipmaps(a22);
        TextureUtil.allocateTextureImpl((int)zj3.getGlTextureId(), (int)a22, (int)a3.m.getIconWidth(), (int)a3.m.getIconHeight());
        TextureUtil.uploadTextureMipmap((int[][])zj3.m.getFrameTextureData(0), (int)a3.m.getIconWidth(), (int)a3.m.getIconHeight(), (int)a3.m.getOriginX(), (int)a3.m.getOriginY(), (boolean)false, (boolean)false);
        String[] stringArray = new String[2];
        stringArray[0] = "listTickables";
        stringArray[1] = "listTickables";
        ((List)ReflectionHelper.getPrivateValue(TextureManager.class, (Object)Minecraft.getMinecraft().getTextureManager(), (String[])stringArray)).add(a3);
    }
}

