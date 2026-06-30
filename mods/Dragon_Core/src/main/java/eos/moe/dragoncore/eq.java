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

    public void func_110551_a(IResourceManager a2) throws IOException {
        eq a3;
        a3.func_147631_c();
        BufferedImage a4 = TextureUtil.func_177053_a((InputStream)new ByteArrayInputStream(a3.ALLATORIxDEMO));
        TextureUtil.func_110989_a((int)a3.func_110552_b(), (BufferedImage)a4, (boolean)false, (boolean)false);
    }
}

