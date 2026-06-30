/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.AbstractTexture
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.client.resources.IResourceManager
 */
package eos.moe.dragoncore;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;

public class eq
extends AbstractTexture {
    private final byte[] ALLATORIxDEMO;

    public eq(byte[] a2) {
        eq a3;
        a3.ALLATORIxDEMO = a2;
    }

    public void loadTexture(IResourceManager a2) throws IOException {
        eq a3;
        a3.deleteGlTexture();
        BufferedImage a4 = TextureUtil.readBufferedImage((InputStream)new ByteArrayInputStream(a3.ALLATORIxDEMO));
        TextureUtil.uploadTextureImageAllocate((int)a3.getGlTextureId(), (BufferedImage)a4, (boolean)false, (boolean)false);
    }
}

