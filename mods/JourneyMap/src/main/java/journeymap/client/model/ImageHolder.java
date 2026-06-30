/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 *  net.minecraft.util.math.ChunkPos
 *  net.minecraft.world.storage.IThreadedFileIO
 *  net.minecraft.world.storage.ThreadedFileIOBase
 *  org.apache.logging.log4j.Level
 *  org.apache.logging.log4j.Logger
 */
package journeymap.client.model;

import com.google.common.base.MoreObjects;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import javax.imageio.ImageIO;
import journeymap.client.io.RegionImageHandler;
import journeymap.client.log.StatTimer;
import journeymap.client.model.MapType;
import journeymap.client.render.texture.RegionTextureImpl;
import journeymap.client.task.main.ExpireTextureTask;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.storage.IThreadedFileIO;
import net.minecraft.world.storage.ThreadedFileIOBase;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

public class ImageHolder
implements IThreadedFileIO {
    static final Logger logger = Journeymap.getLogger();
    final MapType mapType;
    final Path imagePath;
    final int imageSize;
    boolean blank = true;
    boolean dirty = true;
    boolean partialUpdate;
    private volatile ReentrantLock writeLock = new ReentrantLock();
    private volatile RegionTextureImpl texture;
    private boolean debug;
    private HashSet<ChunkPos> updatedChunks = new HashSet();

    ImageHolder(MapType mapType, File imageFile, int imageSize) {
        this.mapType = mapType;
        this.imagePath = imageFile.toPath();
        this.imageSize = imageSize;
        this.debug = logger.isEnabled(Level.DEBUG);
        this.getTexture();
    }

    File getFile() {
        return this.imagePath.toFile();
    }

    MapType getMapType() {
        return this.mapType;
    }

    BufferedImage getImage() {
        return this.texture.getImage();
    }

    void setImage(BufferedImage image) {
        this.texture.setImage(image, true);
        this.setDirty();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void partialImageUpdate(BufferedImage imagePart, int x, int y) {
        this.writeLock.lock();
        StatTimer timer = StatTimer.get("ImageHolder.partialImageUpdate", 5, 500);
        timer.start();
        try {
            if (this.texture != null) {
                this.blank = false;
                int width = imagePart.getWidth();
                int height = imagePart.getHeight();
                int[] updatedPixels = new int[width * height];
                imagePart.getRGB(0, 0, width, height, updatedPixels, 0, width);
                this.texture.getImage().setRGB(x, y, width, height, updatedPixels, 0, width);
                this.partialUpdate = true;
                this.updatedChunks.add(new ChunkPos(x, y));
            } else {
                logger.warn(this + " can't partialImageUpdate without a texture.");
            }
        }
        finally {
            timer.stop();
            this.writeLock.unlock();
        }
    }

    void finishPartialImageUpdates() {
        this.writeLock.lock();
        try {
            if (this.partialUpdate && !this.updatedChunks.isEmpty()) {
                BufferedImage textureImage = this.texture.getImage();
                this.texture.setImage(textureImage, true, this.updatedChunks);
                this.setDirty();
                this.partialUpdate = false;
                this.updatedChunks.clear();
            }
        }
        finally {
            this.writeLock.unlock();
        }
    }

    public boolean hasTexture() {
        return this.texture != null && !this.texture.isDefunct();
    }

    public RegionTextureImpl getTexture() {
        if (!this.hasTexture()) {
            BufferedImage image;
            File temp;
            if (!this.imagePath.toFile().exists() && (temp = new File(this.imagePath.toString() + ".new")).exists()) {
                Journeymap.getLogger().warn("Recovered image file: " + temp);
                temp.renameTo(this.imagePath.toFile());
            }
            if ((image = RegionImageHandler.readRegionImage(this.imagePath.toFile(), false)) == null || image.getWidth() != this.imageSize || image.getHeight() != this.imageSize) {
                image = new BufferedImage(this.imageSize, this.imageSize, 2);
                this.blank = true;
                this.dirty = false;
            } else {
                this.blank = false;
            }
            this.texture = new RegionTextureImpl(image);
            this.texture.setDescription(this.imagePath.toString());
        }
        return this.texture;
    }

    private void setDirty() {
        this.dirty = true;
    }

    boolean isDirty() {
        return this.dirty;
    }

    protected boolean writeToDisk(boolean async) {
        if (this.blank || this.texture == null || !this.texture.hasImage()) {
            return false;
        }
        if (async) {
            ThreadedFileIOBase.getThreadedIOInstance().queueIO((IThreadedFileIO)this);
            return true;
        }
        boolean success = false;
        for (int tries = 0; tries < 5; ++tries) {
            if (this.writeNextIO()) {
                continue;
            }
            success = true;
            break;
        }
        if (!success) {
            Journeymap.getLogger().warn("Couldn't write file after 5 tries: " + this);
        }
        return success;
    }

    public boolean writeNextIO() {
        if (this.texture == null || !this.texture.hasImage()) {
            return false;
        }
        try {
            if (this.writeLock.tryLock(250L, TimeUnit.MILLISECONDS)) {
                this.writeImageToFile();
                this.writeLock.unlock();
                return false;
            }
            logger.warn("Couldn't get write lock for file: " + this.writeLock + " for " + this);
            return true;
        }
        catch (InterruptedException e) {
            logger.warn("Timeout waiting for write lock  " + this.writeLock + " for " + this);
            return true;
        }
    }

    private void writeImageToFile() {
        File imageFile = this.imagePath.toFile();
        try {
            BufferedImage image = this.texture.getImage();
            if (image != null) {
                if (!imageFile.exists()) {
                    imageFile.getParentFile().mkdirs();
                }
                File temp = new File(imageFile.getParentFile(), imageFile.getName() + ".new");
                ImageIO.write((RenderedImage)image, "PNG", temp);
                if (imageFile.exists() && !imageFile.delete()) {
                    logger.warn("Couldn't delete old file " + imageFile.getName());
                }
                if (temp.renameTo(imageFile)) {
                    this.dirty = false;
                } else {
                    logger.warn("Couldn't rename temp file to " + imageFile.getName());
                }
                if (this.debug) {
                    logger.debug("Wrote to disk: " + imageFile);
                }
            }
        }
        catch (IOException e) {
            if (imageFile.exists()) {
                try {
                    logger.error("IOException updating file, will delete and retry: " + this + ": " + LogFormatter.toPartialString(e));
                    imageFile.delete();
                    this.writeImageToFile();
                }
                catch (Throwable e2) {
                    logger.error("Exception after delete/retry: " + this + ": " + LogFormatter.toPartialString(e));
                }
            } else {
                logger.error("IOException creating file: " + this + ": " + LogFormatter.toPartialString(e));
            }
        }
        catch (Throwable e) {
            logger.error("Exception writing to disk: " + this + ": " + LogFormatter.toPartialString(e));
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object)this).add("mapType", (Object)this.mapType).add("textureId", this.texture == null ? null : Integer.valueOf(this.texture.isBound() ? this.texture.getGlTextureId(false) : -1)).add("dirty", this.dirty).add("imagePath", (Object)this.imagePath).toString();
    }

    public void clear() {
        this.writeLock.lock();
        ExpireTextureTask.queue(this.texture);
        this.texture = null;
        this.writeLock.unlock();
    }

    public void finalize() {
        if (this.texture != null) {
            this.clear();
        }
    }

    public long getImageTimestamp() {
        if (this.texture != null) {
            return this.texture.getLastImageUpdate();
        }
        return 0L;
    }
}

