/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.texture.AbstractTexture
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package journeymap.client.render.texture;

import com.google.common.base.MoreObjects;
import java.awt.image.BufferedImage;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import journeymap.client.render.texture.TextureCache;
import journeymap.client.task.main.ExpireTextureTask;
import journeymap.client.task.multi.MapPlayerTask;
import journeymap.common.Journeymap;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TextureImpl
extends AbstractTexture {
    protected final ReentrantLock bufferLock = new ReentrantLock();
    protected BufferedImage image;
    protected boolean retainImage;
    protected int width;
    protected int height;
    protected float alpha;
    protected long lastImageUpdate;
    protected long lastBound;
    protected String description;
    protected ResourceLocation resourceLocation;
    protected List<WeakReference<Listener>> listeners = new ArrayList<WeakReference<Listener>>(0);
    protected ByteBuffer buffer;
    protected boolean bindNeeded;

    public TextureImpl(ResourceLocation resourceLocation) {
        this(null, TextureCache.resolveImage(resourceLocation), false, false);
        this.resourceLocation = resourceLocation;
        this.setDescription(resourceLocation.getPath());
    }

    public TextureImpl(BufferedImage image) {
        this(null, image, false, true);
    }

    public TextureImpl(BufferedImage image, boolean retainImage) {
        this(null, image, retainImage, true);
    }

    public TextureImpl(Integer glId, BufferedImage image, boolean retainImage) {
        this(glId, image, retainImage, true);
    }

    public TextureImpl(Integer glId, BufferedImage image, boolean retainImage, boolean bindImmediately) {
        if (glId != null) {
            this.glTextureId = glId;
        }
        this.retainImage = retainImage;
        if (image != null) {
            this.setImage(image, retainImage);
        }
        if (bindImmediately) {
            this.bindTexture();
            this.buffer = null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setImage(BufferedImage bufferedImage, boolean retainImage) {
        if (bufferedImage == null) {
            return;
        }
        try {
            this.bufferLock.lock();
            this.retainImage = retainImage;
            if (retainImage) {
                this.image = bufferedImage;
            }
            this.width = bufferedImage.getWidth();
            this.height = bufferedImage.getHeight();
            int bufferSize = this.width * this.height * 4;
            if (this.buffer == null || this.buffer.capacity() != bufferSize) {
                this.buffer = ByteBuffer.allocateDirect(bufferSize);
            }
            TextureImpl.loadByteBuffer(bufferedImage, this.buffer);
            this.bindNeeded = true;
        }
        finally {
            this.bufferLock.unlock();
        }
        this.lastImageUpdate = System.currentTimeMillis();
        this.notifyListeners();
    }

    public static void loadByteBuffer(BufferedImage bufferedImage, ByteBuffer buffer) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        buffer.clear();
        int[] pixels = new int[width * height];
        bufferedImage.getRGB(0, 0, width, height, pixels, 0, width);
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                int pixel = pixels[y * width + x];
                buffer.put((byte)(pixel >> 16 & 0xFF));
                buffer.put((byte)(pixel >> 8 & 0xFF));
                buffer.put((byte)(pixel & 0xFF));
                buffer.put((byte)(pixel >> 24 & 0xFF));
            }
        }
        buffer.flip();
        buffer.rewind();
    }

    public void bindTexture() {
        if (!this.bindNeeded) {
            return;
        }
        if (this.bufferLock.tryLock()) {
            if (this.glTextureId > -1) {
                MapPlayerTask.addTempDebugMessage("tex" + this.glTextureId, "Updating: " + this.getDescription());
            }
            try {
                int glErr;
                GlStateManager.bindTexture((int)super.getGlTextureId());
                GL11.glTexParameteri((int)3553, (int)10242, (int)10497);
                GL11.glTexParameteri((int)3553, (int)10243, (int)10497);
                GL11.glTexParameteri((int)3553, (int)10241, (int)9729);
                GL11.glTexParameteri((int)3553, (int)10240, (int)9729);
                GL11.glTexImage2D((int)3553, (int)0, (int)32856, (int)this.width, (int)this.height, (int)0, (int)6408, (int)5121, (ByteBuffer)this.buffer);
                this.bindNeeded = false;
                while ((glErr = GL11.glGetError()) != 0) {
                    Journeymap.getLogger().warn("GL Error in TextureImpl after glTexImage2D: " + glErr + " in " + (Object)((Object)this));
                    this.bindNeeded = true;
                }
                if (!this.bindNeeded) {
                    this.lastBound = System.currentTimeMillis();
                }
            }
            catch (Throwable t) {
                Journeymap.getLogger().warn("Can't bind texture: " + t);
                this.buffer = null;
            }
            finally {
                this.bufferLock.unlock();
            }
        }
    }

    public boolean isBindNeeded() {
        return this.bindNeeded;
    }

    public boolean isBound() {
        return this.glTextureId != -1;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void updateAndBind(BufferedImage image) {
        this.updateAndBind(image, this.retainImage);
    }

    public void updateAndBind(BufferedImage image, boolean retainImage) {
        this.setImage(image, retainImage);
        this.bindTexture();
    }

    public boolean hasImage() {
        return this.image != null;
    }

    public BufferedImage getImage() {
        if (this.image != null) {
            return this.image;
        }
        if (this.resourceLocation != null) {
            return TextureCache.resolveImage(this.resourceLocation);
        }
        return null;
    }

    public boolean isDefunct() {
        return this.glTextureId == -1 && this.image == null && this.buffer == null;
    }

    public int getGlTextureId() {
        if (this.bindNeeded) {
            this.bindTexture();
        }
        return super.getGlTextureId();
    }

    public int getGlTextureId(boolean forceBind) {
        if (forceBind || this.glTextureId == -1) {
            return this.getGlTextureId();
        }
        return this.glTextureId;
    }

    public void clear() {
        this.bufferLock.lock();
        this.buffer = null;
        this.bufferLock.unlock();
        this.image = null;
        this.bindNeeded = false;
        this.lastImageUpdate = 0L;
        this.lastBound = 0L;
        this.glTextureId = -1;
    }

    public void queueForDeletion() {
        ExpireTextureTask.queue(this);
    }

    public long getLastImageUpdate() {
        return this.lastImageUpdate;
    }

    public long getLastBound() {
        return this.lastBound;
    }

    public void loadTexture(IResourceManager par1ResourceManager) {
        if (this.resourceLocation != null) {
            // empty if block
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object)((Object)this)).add("glid", this.glTextureId).add("description", (Object)this.description).add("lastImageUpdate", this.lastImageUpdate).add("lastBound", this.lastBound).toString();
    }

    public void finalize() {
        if (this.isBound()) {
            Journeymap.getLogger().warn("TextureImpl disposed without deleting texture glID: " + (Object)((Object)this));
            ExpireTextureTask.queue(this.glTextureId);
        }
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getAlpha() {
        return this.alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public void addListener(Listener addedListener) {
        Iterator<WeakReference<Listener>> iter = this.listeners.iterator();
        while (iter.hasNext()) {
            WeakReference<Listener> ref = iter.next();
            Listener listener = (Listener)ref.get();
            if (listener == null) {
                iter.remove();
                continue;
            }
            if (addedListener != listener) continue;
            return;
        }
        this.listeners.add(new WeakReference<Listener>(addedListener));
    }

    protected void notifyListeners() {
        Iterator<WeakReference<Listener>> iter = this.listeners.iterator();
        while (iter.hasNext()) {
            WeakReference<Listener> ref = iter.next();
            Listener listener = (Listener)ref.get();
            if (listener == null) {
                iter.remove();
                continue;
            }
            listener.textureImageUpdated(this);
        }
    }

    public static interface Listener<T extends TextureImpl> {
        public void textureImageUpdated(T var1);
    }
}

