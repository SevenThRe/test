/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.CacheBuilder
 *  com.google.common.cache.CacheLoader
 *  com.google.common.cache.LoadingCache
 *  com.google.common.cache.RemovalListener
 *  com.google.common.cache.RemovalNotification
 *  javax.annotation.ParametersAreNonnullByDefault
 *  net.minecraft.client.Minecraft
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  org.apache.logging.log4j.Level
 *  org.apache.logging.log4j.Logger
 */
package journeymap.client.model;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.ParametersAreNonnullByDefault;
import journeymap.client.data.DataCache;
import journeymap.client.io.FileHandler;
import journeymap.client.io.RegionImageHandler;
import journeymap.client.model.ChunkMD;
import journeymap.client.model.MapState;
import journeymap.client.model.MapType;
import journeymap.client.model.RegionCoord;
import journeymap.client.model.RegionImageSet;
import journeymap.common.Journeymap;
import net.minecraft.client.Minecraft;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

public enum RegionImageCache {
    INSTANCE;

    public long firstFileFlushIntervalSecs = 5L;
    public long flushFileIntervalSecs = 60L;
    public long textureCacheAgeSecs = 30L;
    static final Logger logger;
    private volatile long lastFlush = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(this.firstFileFlushIntervalSecs);

    public LoadingCache<RegionImageSet.Key, RegionImageSet> initRegionImageSetsCache(CacheBuilder<Object, Object> builder) {
        return builder.expireAfterAccess(this.textureCacheAgeSecs, TimeUnit.SECONDS).removalListener((RemovalListener)new RemovalListener<RegionImageSet.Key, RegionImageSet>(){

            @ParametersAreNonnullByDefault
            public void onRemoval(RemovalNotification<RegionImageSet.Key, RegionImageSet> notification) {
                RegionImageSet regionImageSet = (RegionImageSet)notification.getValue();
                if (regionImageSet != null) {
                    int count = regionImageSet.writeToDisk(false);
                    if (count > 0 && Journeymap.getLogger().isDebugEnabled()) {
                        Journeymap.getLogger().debug("Wrote to disk before removal from cache: " + regionImageSet);
                    }
                    regionImageSet.clear();
                }
            }
        }).build((CacheLoader)new CacheLoader<RegionImageSet.Key, RegionImageSet>(){

            @ParametersAreNonnullByDefault
            public RegionImageSet load(RegionImageSet.Key key) throws Exception {
                return new RegionImageSet(key);
            }
        });
    }

    public RegionImageSet getRegionImageSet(ChunkMD chunkMd, MapType mapType) {
        if (chunkMd.hasChunk()) {
            Minecraft mc = FMLClientHandler.instance().getClient();
            Chunk chunk = chunkMd.getChunk();
            RegionCoord rCoord = RegionCoord.fromChunkPos(FileHandler.getJMWorldDir(mc), mapType, chunk.field_76635_g, chunk.field_76647_h);
            return this.getRegionImageSet(rCoord);
        }
        return null;
    }

    public RegionImageSet getRegionImageSet(RegionCoord rCoord) {
        return (RegionImageSet)DataCache.INSTANCE.getRegionImageSets().getUnchecked((Object)RegionImageSet.Key.from(rCoord));
    }

    public RegionImageSet getRegionImageSet(RegionImageSet.Key rCoordKey) {
        return (RegionImageSet)DataCache.INSTANCE.getRegionImageSets().getUnchecked((Object)rCoordKey);
    }

    private Collection<RegionImageSet> getRegionImageSets() {
        return DataCache.INSTANCE.getRegionImageSets().asMap().values();
    }

    public void updateTextures(boolean forceFlush, boolean async) {
        for (RegionImageSet regionImageSet : this.getRegionImageSets()) {
            regionImageSet.finishChunkUpdates();
        }
        if (forceFlush || this.lastFlush + TimeUnit.SECONDS.toMillis(this.flushFileIntervalSecs) < System.currentTimeMillis()) {
            if (!forceFlush && logger.isEnabled(Level.DEBUG)) {
                logger.debug("RegionImageCache auto-flushing");
            }
            if (async) {
                this.flushToDiskAsync(false);
            } else {
                this.flushToDisk(false);
            }
        }
    }

    public void flushToDiskAsync(boolean force) {
        int count = 0;
        for (RegionImageSet regionImageSet : this.getRegionImageSets()) {
            count += regionImageSet.writeToDiskAsync(force);
        }
        this.lastFlush = System.currentTimeMillis();
    }

    public void flushToDisk(boolean force) {
        for (RegionImageSet regionImageSet : this.getRegionImageSets()) {
            regionImageSet.writeToDisk(force);
        }
        this.lastFlush = System.currentTimeMillis();
    }

    public long getLastFlush() {
        return this.lastFlush;
    }

    public List<RegionCoord> getChangedSince(MapType mapType, long time) {
        ArrayList<RegionCoord> list = new ArrayList<RegionCoord>();
        for (RegionImageSet regionImageSet : this.getRegionImageSets()) {
            if (!regionImageSet.updatedSince(mapType, time)) continue;
            list.add(regionImageSet.getRegionCoord());
        }
        if (logger.isEnabled(Level.DEBUG)) {
            logger.debug("Dirty regions: " + list.size() + " of " + DataCache.INSTANCE.getRegionImageSets().size());
        }
        return list;
    }

    public boolean isDirtySince(RegionCoord rc, MapType mapType, long time) {
        RegionImageSet ris = this.getRegionImageSet(rc);
        if (ris == null) {
            return false;
        }
        return ris.updatedSince(mapType, time);
    }

    public void clear() {
        for (RegionImageSet regionImageSet : this.getRegionImageSets()) {
            regionImageSet.clear();
        }
        DataCache.INSTANCE.getRegionImageSets().invalidateAll();
        DataCache.INSTANCE.getRegionImageSets().cleanUp();
    }

    public boolean deleteMap(MapState state, boolean allDims) {
        RegionCoord fakeRc = new RegionCoord(state.getWorldDir(), 0, 0, state.getDimension());
        File imageDir = RegionImageHandler.getImageDir(fakeRc, MapType.day(state.getDimension())).getParentFile();
        if (!imageDir.getName().startsWith("DIM")) {
            logger.error("Expected DIM directory, got " + imageDir);
            return false;
        }
        File[] dirs = allDims ? imageDir.getParentFile().listFiles(new FilenameFilter(){

            @Override
            public boolean accept(File dir, String name) {
                return dir.isDirectory() && name.startsWith("DIM");
            }
        }) : new File[]{imageDir};
        if (dirs != null && dirs.length > 0) {
            this.clear();
            boolean result = true;
            for (File dir : dirs) {
                if (!dir.exists()) continue;
                FileHandler.delete(dir);
                logger.info(String.format("Deleted image directory %s: %s", dir, !dir.exists()));
                if (!dir.exists()) continue;
                result = false;
            }
            logger.info("Done deleting directories");
            return result;
        }
        logger.info("Found no DIM directories in " + imageDir);
        return true;
    }

    static {
        logger = Journeymap.getLogger();
    }
}

