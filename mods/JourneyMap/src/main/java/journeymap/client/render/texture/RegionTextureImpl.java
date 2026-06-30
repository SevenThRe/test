/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.util.math.ChunkPos
 *  org.lwjgl.opengl.GL11
 */
package journeymap.client.render.texture;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;
import journeymap.client.render.texture.TextureImpl;
import journeymap.client.task.multi.MapPlayerTask;
import journeymap.common.Journeymap;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.math.ChunkPos;
import org.lwjgl.opengl.GL11;

public class RegionTextureImpl
extends TextureImpl {
    protected HashSet<ChunkPos> dirtyChunks = new HashSet();

    public RegionTextureImpl(BufferedImage image) {
        super(null, image, true, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setImage(BufferedImage bufferedImage, boolean retainImage, HashSet<ChunkPos> updatedChunks) {
        if (updatedChunks.size() > 15) {
            super.setImage(bufferedImage, retainImage);
        } else {
            this.dirtyChunks.addAll(updatedChunks);
            this.bindNeeded = true;
            try {
                this.bufferLock.lock();
                this.retainImage = retainImage;
                if (retainImage) {
                    this.image = bufferedImage;
                }
                this.width = bufferedImage.getWidth();
                this.height = bufferedImage.getHeight();
            }
            finally {
                this.bufferLock.unlock();
            }
        }
        this.lastImageUpdate = System.currentTimeMillis();
        this.notifyListeners();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void bindTexture() {
        if (!this.bindNeeded) {
            return;
        }
        if (this.glTextureId == -1) {
            this.glTextureId = TextureUtil.glGenTextures();
        }
        if (this.lastBound == 0L || this.dirtyChunks.isEmpty()) {
            super.bindTexture();
            return;
        }
        if (this.bufferLock.tryLock()) {
            MapPlayerTask.addTempDebugMessage("tex" + this.glTextureId, "Updating " + this.dirtyChunks.size() + " chunks within: " + this.getDescription());
            GlStateManager.bindTexture((int)this.glTextureId);
            GL11.glTexParameteri((int)3553, (int)10242, (int)10497);
            GL11.glTexParameteri((int)3553, (int)10243, (int)10497);
            GL11.glTexParameteri((int)3553, (int)10241, (int)9729);
            GL11.glTexParameteri((int)3553, (int)10240, (int)9729);
            try {
                boolean glErrors = false;
                ByteBuffer chunkBuffer = ByteBuffer.allocateDirect(1024);
                for (ChunkPos pos : this.dirtyChunks) {
                    int err;
                    BufferedImage chunkImage = this.getImage().getSubimage(pos.x, pos.z, 16, 16);
                    RegionTextureImpl.loadByteBuffer(chunkImage, chunkBuffer);
                    GL11.glTexSubImage2D((int)3553, (int)0, (int)pos.x, (int)pos.z, (int)16, (int)16, (int)6408, (int)5121, (ByteBuffer)chunkBuffer);
                    while ((err = GL11.glGetError()) != 0) {
                        glErrors = true;
                        Journeymap.getLogger().warn("GL Error in RegionTextureImpl after glTexSubImage2D: " + err + " for " + pos + " in " + (Object)((Object)this));
                    }
                    if (!glErrors) continue;
                    break;
                }
                this.dirtyChunks.clear();
                if (glErrors) {
                    this.bindNeeded = true;
                } else {
                    this.bindNeeded = false;
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

    public Set<ChunkPos> getDirtyAreas() {
        return this.dirtyChunks;
    }
}

