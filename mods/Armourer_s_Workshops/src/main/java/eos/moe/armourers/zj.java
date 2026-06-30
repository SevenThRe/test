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

    public void func_110550_d() {
        zj a2;
        if (a2.m != null) {
            zj zj2 = a2;
            GlStateManager.func_179144_i((int)zj2.func_110552_b());
            zj2.m.func_94219_l();
        }
    }

    public zj(ModelData.Texture a2) {
        zj a3;
        a3.j = a2;
    }

    public void func_110551_a(IResourceManager a22) throws IOException {
        PngSizeInfo pngSizeInfo;
        zj a3;
        block5: {
            block4: {
                if (s == null) {
                    s = new MetadataSerializer();
                    s.func_110504_a((IMetadataSectionSerializer)new AnimationMetadataSectionSerializer(), AnimationMetadataSection.class);
                }
                zj zj2 = a3;
                zj2.func_147631_c();
                a22 = zj2.j.getMeta();
                pngSizeInfo = new PngSizeInfo((InputStream)new ByteArrayInputStream(a3.j.getData()));
                if (a22 == null) break block4;
                PngSizeInfo pngSizeInfo2 = pngSizeInfo;
                if (pngSizeInfo2.field_188534_b / pngSizeInfo2.field_188533_a >= 2) break block5;
            }
            BufferedImage bufferedImage = TextureUtil.func_177053_a((InputStream)new ByteArrayInputStream(a3.j.getData()));
            TextureUtil.func_110989_a((int)a3.func_110552_b(), (BufferedImage)bufferedImage, (boolean)false, (boolean)false);
            return;
        }
        SimpleResource simpleResource = new SimpleResource(a3.j.getName(), null, (InputStream)new ByteArrayInputStream(a3.j.getData()), (InputStream)new ByteArrayInputStream(((String)a22).getBytes(StandardCharsets.UTF_8)), s);
        int a22 = Minecraft.func_71410_x().field_71474_y.field_151442_I;
        zj zj3 = a3;
        a3.m = new ch(a3.j.getName());
        zj3.m.func_188538_a(pngSizeInfo, true);
        zj3.m.func_188539_a((IResource)simpleResource, a22 + 1);
        zj3.m.func_147963_d(a22);
        TextureUtil.func_180600_a((int)zj3.func_110552_b(), (int)a22, (int)a3.m.func_94211_a(), (int)a3.m.func_94216_b());
        TextureUtil.func_147955_a((int[][])zj3.m.func_147965_a(0), (int)a3.m.func_94211_a(), (int)a3.m.func_94216_b(), (int)a3.m.func_130010_a(), (int)a3.m.func_110967_i(), (boolean)false, (boolean)false);
        String[] stringArray = new String[2];
        stringArray[0] = "listTickables";
        stringArray[1] = "field_110583_b";
        ((List)ReflectionHelper.getPrivateValue(TextureManager.class, (Object)Minecraft.func_71410_x().func_110434_K(), (String[])stringArray)).add(a3);
    }
}

