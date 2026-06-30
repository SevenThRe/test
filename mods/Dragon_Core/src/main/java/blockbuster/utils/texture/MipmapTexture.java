/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GLAllocation
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.texture.SimpleTexture
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.apache.commons.io.IOUtils
 *  org.lwjgl.opengl.GL11
 */
package blockbuster.utils.texture;

import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.io.IOUtils;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class MipmapTexture
extends SimpleTexture {
    public static ByteBuffer bytesFromBuffer(BufferedImage image) {
        int w2 = image.getWidth();
        int h2 = image.getHeight();
        ByteBuffer buffer = GLAllocation.func_74524_c((int)(w2 * h2 * 4));
        int[] pixels = new int[w2 * h2];
        image.getRGB(0, 0, w2, h2, pixels, 0, w2);
        for (int y2 = 0; y2 < h2; ++y2) {
            for (int x2 = 0; x2 < w2; ++x2) {
                int pixel = pixels[y2 * w2 + x2];
                buffer.put((byte)(pixel >> 16 & 0xFF));
                buffer.put((byte)(pixel >> 8 & 0xFF));
                buffer.put((byte)(pixel & 0xFF));
                buffer.put((byte)(pixel >> 24 & 0xFF));
            }
        }
        buffer.flip();
        return buffer;
    }

    public MipmapTexture(ResourceLocation textureResourceLocation) {
        super(textureResourceLocation);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void func_110551_a(IResourceManager resourceManager) throws IOException {
        IResource resource = null;
        try {
            resource = resourceManager.func_110536_a(this.field_110568_b);
            BufferedImage image = TextureUtil.func_177053_a((InputStream)resource.func_110527_b());
            int id2 = this.func_110552_b();
            int w2 = image.getWidth();
            int h2 = image.getHeight();
            GlStateManager.func_179144_i((int)id2);
            GlStateManager.func_187421_b((int)3553, (int)33082, (int)0);
            GlStateManager.func_187421_b((int)3553, (int)33083, (int)3);
            GlStateManager.func_187421_b((int)3553, (int)33085, (int)3);
            GlStateManager.func_187403_b((int)3553, (int)34049, (float)0.0f);
            GlStateManager.func_187403_b((int)3553, (int)33169, (float)1.0f);
            GlStateManager.func_187403_b((int)3553, (int)10241, (float)9986.0f);
            GlStateManager.func_187403_b((int)3553, (int)10240, (float)9986.0f);
            GL11.glTexImage2D((int)3553, (int)0, (int)32856, (int)w2, (int)h2, (int)0, (int)6408, (int)5121, (ByteBuffer)MipmapTexture.bytesFromBuffer(image));
        }
        catch (Throwable throwable) {
            IOUtils.closeQuietly(resource);
            throw throwable;
        }
        IOUtils.closeQuietly((Closeable)resource);
    }
}

