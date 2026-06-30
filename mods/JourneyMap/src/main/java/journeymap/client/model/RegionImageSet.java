/*
 * Decompiled with CFR 0.152.
 */
package journeymap.client.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;
import journeymap.client.io.RegionImageHandler;
import journeymap.client.model.ChunkMD;
import journeymap.client.model.ImageHolder;
import journeymap.client.model.ImageSet;
import journeymap.client.model.MapType;
import journeymap.client.model.RegionCoord;
import journeymap.client.model.RegionImageCache;
import journeymap.client.render.ComparableBufferedImage;

public class RegionImageSet
extends ImageSet {
    protected final Key key;

    public RegionImageSet(Key key) {
        this.key = key;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ImageHolder getHolder(MapType mapType) {
        Map map = this.imageHolders;
        synchronized (map) {
            ImageHolder imageHolder = (ImageHolder)this.imageHolders.get(mapType);
            if (imageHolder == null) {
                File imageFile = RegionImageHandler.getRegionImageFile(this.getRegionCoord(), mapType, false);
                imageHolder = this.addHolder(mapType, imageFile);
            }
            return imageHolder;
        }
    }

    public ComparableBufferedImage getChunkImage(ChunkMD chunkMd, MapType mapType) {
        RegionCoord regionCoord = this.getRegionCoord();
        BufferedImage regionImage = this.getHolder(mapType).getImage();
        BufferedImage sub = regionImage.getSubimage(regionCoord.getXOffset(chunkMd.getCoord().x), regionCoord.getZOffset(chunkMd.getCoord().z), 16, 16);
        ComparableBufferedImage chunk = new ComparableBufferedImage(16, 16, regionImage.getType());
        chunk.setData(sub.getData());
        return chunk;
    }

    public void setChunkImage(ChunkMD chunkMd, MapType mapType, ComparableBufferedImage chunkImage) {
        ImageHolder holder = this.getHolder(mapType);
        boolean wasBlank = holder.blank;
        if (chunkImage.isChanged() || wasBlank) {
            RegionCoord regionCoord = this.getRegionCoord();
            holder.partialImageUpdate(chunkImage, regionCoord.getXOffset(chunkMd.getCoord().x), regionCoord.getZOffset(chunkMd.getCoord().z));
        }
        if (wasBlank) {
            holder.getTexture();
            holder.finishPartialImageUpdates();
            RegionImageCache.INSTANCE.getRegionImageSet(this.getRegionCoord());
        }
        chunkMd.setRendered(mapType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean hasChunkUpdates() {
        Map map = this.imageHolders;
        synchronized (map) {
            for (ImageHolder holder : this.imageHolders.values()) {
                if (!holder.partialUpdate) continue;
                return true;
            }
        }
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void finishChunkUpdates() {
        Map map = this.imageHolders;
        synchronized (map) {
            for (ImageHolder holder : this.imageHolders.values()) {
                holder.finishPartialImageUpdates();
            }
        }
    }

    public RegionCoord getRegionCoord() {
        return RegionCoord.fromRegionPos(this.key.worldDir, this.key.regionX, this.key.regionZ, this.key.dimension);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public long getOldestTimestamp() {
        long time = System.currentTimeMillis();
        Map map = this.imageHolders;
        synchronized (map) {
            for (ImageHolder holder : this.imageHolders.values()) {
                if (holder == null) continue;
                time = Math.min(time, holder.getImageTimestamp());
            }
        }
        return time;
    }

    @Override
    public int hashCode() {
        return this.key.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        return this.key.equals(((RegionImageSet)obj).key);
    }

    @Override
    protected int getImageSize() {
        return 512;
    }

    public static class Key {
        private final File worldDir;
        private final int regionX;
        private final int regionZ;
        private final int dimension;

        private Key(File worldDir, int regionX, int regionZ, int dimension) {
            this.worldDir = worldDir;
            this.regionX = regionX;
            this.regionZ = regionZ;
            this.dimension = dimension;
        }

        public static Key from(RegionCoord rCoord) {
            return new Key(rCoord.worldDir, rCoord.regionX, rCoord.regionZ, rCoord.dimension);
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            Key key = (Key)o;
            if (this.dimension != key.dimension) {
                return false;
            }
            if (this.regionX != key.regionX) {
                return false;
            }
            if (this.regionZ != key.regionZ) {
                return false;
            }
            return this.worldDir.equals(key.worldDir);
        }

        public int hashCode() {
            int result = this.worldDir.hashCode();
            result = 31 * result + this.regionX;
            result = 31 * result + this.regionZ;
            result = 31 * result + this.dimension;
            return result;
        }
    }
}

