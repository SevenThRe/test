/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.dragoncore;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

public class ap
extends TextureAtlasSprite {
    public BufferedImage y = null;
    public int k = -1;
    public String ALLATORIxDEMO;

    public ap(String a2) {
        super("NONE");
        ap a3;
        a3.ALLATORIxDEMO = a2;
    }

    public boolean hasCustomLoader(IResourceManager a2, ResourceLocation a3) {
        return true;
    }

    public boolean updateIcon(BufferedImage a2) {
        ap a3;
        a3.width = a3.height = Math.max(a2.getHeight(), a2.getWidth());
        if (a2.getWidth() != a2.getHeight()) {
            BufferedImage a4 = new BufferedImage(a3.width, a3.width, 7);
            Graphics2D a5 = a4.createGraphics();
            a5.drawImage((Image)a2, (a3.width - a2.getWidth()) / 2, (a3.width - a2.getHeight()) / 2, null);
            a5.dispose();
            a2 = a4;
        }
        a3.y = a2;
        a3.initSprite(a3.width, a3.height, 0, 0, false);
        a3.onImageUpdate();
        return true;
    }

    public void onRemove() {
        ap a2;
        if (a2.k != -1) {
            TextureUtil.deleteTexture((int)a2.k);
            a2.k = -1;
        }
    }

    public void onPreRender() {
    }

    public int getTextureId() {
        ap a2;
        return a2.k;
    }

    public void onImageUpdate() {
        ap a2;
        if (a2.k != -1) {
            a2.onRemove();
        }
        a2.k = TextureUtil.glGenTextures();
        TextureUtil.uploadTextureImage((int)a2.k, (BufferedImage)a2.y);
    }

    public void initSprite(int a2, int a3, int a4, int a5, boolean a6) {
        ap a7;
        super.initSprite(a2, a3, a4, a5, a6);
        int[][] a8 = new int[][]{new int[a7.getIconWidth() * a7.getIconHeight()]};
        a7.y.getRGB(a4, a5, a7.getIconWidth(), a7.getIconHeight(), a8[0], 0, a7.getIconWidth());
        a7.clearFramesTextureData();
        a7.framesTextureData.add(a8);
    }
}

