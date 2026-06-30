/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bus
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.GL11
 */
package net.optifine.shaders;

import java.nio.ByteBuffer;
import net.optifine.shaders.ICustomTexture;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class HFNoiseTexture
implements ICustomTexture {
    private int texID = GL11.glGenTextures();
    private int textureUnit = 15;

    public HFNoiseTexture(int width, int height) {
        byte[] image = this.genHFNoiseImage(width, height);
        ByteBuffer data = BufferUtils.createByteBuffer((int)image.length);
        data.put(image);
        data.flip();
        bus.i((int)this.texID);
        GL11.glTexImage2D((int)3553, (int)0, (int)6407, (int)width, (int)height, (int)0, (int)6407, (int)5121, (ByteBuffer)data);
        GL11.glTexParameteri((int)3553, (int)10242, (int)10497);
        GL11.glTexParameteri((int)3553, (int)10243, (int)10497);
        GL11.glTexParameteri((int)3553, (int)10240, (int)9729);
        GL11.glTexParameteri((int)3553, (int)10241, (int)9729);
        bus.i((int)0);
    }

    public int getID() {
        return this.texID;
    }

    @Override
    public void deleteTexture() {
        bus.h((int)this.texID);
        this.texID = 0;
    }

    private int random(int seed) {
        seed ^= seed << 13;
        seed ^= seed >> 17;
        seed ^= seed << 5;
        return seed;
    }

    private byte random(int x, int y, int z) {
        int seed = (this.random(x) + this.random(y * 19)) * this.random(z * 23) - z;
        return (byte)(this.random(seed) % 128);
    }

    private byte[] genHFNoiseImage(int width, int height) {
        byte[] image = new byte[width * height * 3];
        int index = 0;
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                for (int z = 1; z < 4; ++z) {
                    image[index++] = this.random(x, y, z);
                }
            }
        }
        return image;
    }

    @Override
    public int getTextureId() {
        return this.texID;
    }

    @Override
    public int getTextureUnit() {
        return this.textureUnit;
    }
}

