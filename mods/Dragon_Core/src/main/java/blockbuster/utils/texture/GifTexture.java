/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.texture.AbstractTexture
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.ITickableTextureObject
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.opengl.GL11
 */
package blockbuster.utils.texture;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.ITickableTextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class GifTexture
extends AbstractTexture
implements ITickableTextureObject {
    public static int globalTick;
    public ResourceLocation texture;
    public List<GifElement> elements = new ArrayList<GifElement>();
    public int index;
    public int duration;
    public int width;
    public int height;

    public static void bindTexture(ResourceLocation location, int ticks, float partialTicks) {
        ITextureObject object;
        TextureManager textures = Minecraft.func_71410_x().field_71446_o;
        if (location.func_110623_a().endsWith("gif") && (object = textures.func_110581_b(location)) instanceof GifTexture) {
            GifTexture texture = (GifTexture)object;
            int lastIndex = texture.index;
            if (ticks >= 0) {
                texture.calculateIndex(ticks, partialTicks);
            }
            GlStateManager.func_179144_i((int)texture.func_110552_b());
            if (ticks >= 0) {
                texture.index = lastIndex;
            }
            return;
        }
        textures.func_110577_a(location);
    }

    public static void updateTick() {
        ++globalTick;
    }

    public GifTexture(ResourceLocation texture) {
        this.texture = texture;
    }

    public void add(int delay, ByteBuffer buffer) {
        this.elements.add(new GifElement(delay, this.width, this.height, buffer));
    }

    public void calculateDuration() {
        this.duration = 0;
        for (GifElement element : this.elements) {
            this.duration += element.delay;
        }
    }

    public void func_110551_a(IResourceManager resourceManager) throws IOException {
    }

    public void func_110550_d() {
        Minecraft mc2 = Minecraft.func_71410_x();
        if (mc2.field_71439_g == null) {
            return;
        }
        this.calculateIndex(globalTick, 0.0f);
    }

    public void calculateIndex(int ticks, float partial) {
        int tick = (int)(((float)ticks + partial) * 20.0f % (float)this.duration);
        int duration = 0;
        int index = 0;
        this.index = 0;
        for (GifElement element : this.elements) {
            if (tick < (duration += element.delay)) {
                this.index = index;
                break;
            }
            ++index;
        }
    }

    public int func_110552_b() {
        if (this.index < 0 || this.index >= this.elements.size()) {
            return -1;
        }
        return this.elements.get((int)this.index).id;
    }

    public void func_147631_c() {
        for (GifElement element : this.elements) {
            GL11.glDeleteTextures((int)element.id);
            element.id = -1;
        }
    }

    public static class GifElement {
        public int delay;
        public int id;

        public GifElement(int delay, int w2, int h2, ByteBuffer buffer) {
            this.delay = delay;
            this.id = GL11.glGenTextures();
            GlStateManager.func_179144_i((int)this.id);
            GL11.glTexParameteri((int)3553, (int)10241, (int)9728);
            GL11.glTexParameteri((int)3553, (int)10240, (int)9728);
            GL11.glTexImage2D((int)3553, (int)0, (int)32856, (int)w2, (int)h2, (int)0, (int)6408, (int)5121, (ByteBuffer)buffer);
        }
    }
}

