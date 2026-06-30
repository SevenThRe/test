/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 */
package journeymap.client.model;

import com.google.common.base.MoreObjects;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import journeymap.client.model.ImageHolder;
import journeymap.client.model.MapType;
import journeymap.common.Journeymap;

public abstract class ImageSet {
    protected final Map<MapType, ImageHolder> imageHolders = Collections.synchronizedMap(new HashMap(8));

    protected abstract ImageHolder getHolder(MapType var1);

    public abstract int hashCode();

    public abstract boolean equals(Object var1);

    public BufferedImage getImage(MapType mapType) {
        return this.getHolder(mapType).getImage();
    }

    public int writeToDiskAsync(boolean force) {
        return this.writeToDisk(force, true);
    }

    public int writeToDisk(boolean force) {
        return this.writeToDisk(force, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private int writeToDisk(boolean force, boolean async) {
        long now = System.currentTimeMillis();
        int count = 0;
        try {
            Map<MapType, ImageHolder> map = this.imageHolders;
            synchronized (map) {
                for (ImageHolder imageHolder : this.imageHolders.values()) {
                    if (!imageHolder.isDirty() || !force && now - imageHolder.getImageTimestamp() <= 10000L) continue;
                    imageHolder.writeToDisk(async);
                    ++count;
                }
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().error("Error writing ImageSet to disk: " + t);
        }
        return count;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean updatedSince(MapType mapType, long time) {
        Map<MapType, ImageHolder> map = this.imageHolders;
        synchronized (map) {
            if (mapType == null) {
                for (ImageHolder holder : this.imageHolders.values()) {
                    if (holder == null || holder.getImageTimestamp() < time) continue;
                    return true;
                }
            } else {
                ImageHolder imageHolder = this.imageHolders.get(mapType);
                if (imageHolder != null && imageHolder.getImageTimestamp() >= time) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void clear() {
        Map<MapType, ImageHolder> map = this.imageHolders;
        synchronized (map) {
            for (ImageHolder imageHolder : this.imageHolders.values()) {
                imageHolder.clear();
            }
            this.imageHolders.clear();
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object)this).add("imageHolders", this.imageHolders.entrySet()).toString();
    }

    protected abstract int getImageSize();

    protected ImageHolder addHolder(MapType mapType, File imageFile) {
        return this.addHolder(new ImageHolder(mapType, imageFile, this.getImageSize()));
    }

    protected ImageHolder addHolder(ImageHolder imageHolder) {
        this.imageHolders.put(imageHolder.mapType, imageHolder);
        return imageHolder;
    }
}

