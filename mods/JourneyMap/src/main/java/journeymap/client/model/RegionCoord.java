/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.Cache
 *  net.minecraft.util.math.ChunkPos
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.model;

import com.google.common.cache.Cache;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import journeymap.client.data.DataCache;
import journeymap.client.io.nbt.RegionLoader;
import journeymap.client.model.MapType;
import net.minecraft.util.math.ChunkPos;
import net.minecraftforge.fml.client.FMLClientHandler;

public class RegionCoord
implements Comparable<RegionCoord> {
    public static final transient int SIZE = 5;
    private static final transient int chunkSqRt = (int)Math.pow(2.0, 5.0);
    public final File worldDir;
    public final Path dimDir;
    public final int regionX;
    public final int regionZ;
    public final int dimension;
    private final int theHashCode;
    private final String theCacheKey;

    public RegionCoord(File worldDir, int regionX, int regionZ, int dimension) {
        this.worldDir = worldDir;
        this.dimDir = RegionCoord.getDimPath(worldDir, dimension);
        this.regionX = regionX;
        this.regionZ = regionZ;
        this.dimension = dimension;
        this.theCacheKey = RegionCoord.toCacheKey(this.dimDir, regionX, regionZ);
        this.theHashCode = this.theCacheKey.hashCode();
    }

    public static RegionCoord fromChunkPos(File worldDir, MapType mapType, int chunkX, int chunkZ) {
        int regionX = RegionCoord.getRegionPos(chunkX);
        int regionZ = RegionCoord.getRegionPos(chunkZ);
        return RegionCoord.fromRegionPos(worldDir, regionX, regionZ, mapType.dimension);
    }

    public static RegionCoord fromRegionPos(File worldDir, int regionX, int regionZ, int dimension) {
        Cache<String, RegionCoord> cache = DataCache.INSTANCE.getRegionCoords();
        RegionCoord regionCoord = (RegionCoord)cache.getIfPresent((Object)RegionCoord.toCacheKey(RegionCoord.getDimPath(worldDir, dimension), regionX, regionZ));
        if (regionCoord == null || regionX != regionCoord.regionX || regionZ != regionCoord.regionZ || dimension != regionCoord.dimension) {
            regionCoord = new RegionCoord(worldDir, regionX, regionZ, dimension);
            cache.put((Object)regionCoord.theCacheKey, (Object)regionCoord);
        }
        return regionCoord;
    }

    public static Path getDimPath(File worldDir, int dimension) {
        return new File(worldDir, "DIM" + dimension).toPath();
    }

    public static int getMinChunkX(int rX) {
        return rX << 5;
    }

    public static int getMaxChunkX(int rX) {
        return RegionCoord.getMinChunkX(rX) + (int)Math.pow(2.0, 5.0) - 1;
    }

    public static int getMinChunkZ(int rZ) {
        return rZ << 5;
    }

    public static int getMaxChunkZ(int rZ) {
        return RegionCoord.getMinChunkZ(rZ) + (int)Math.pow(2.0, 5.0) - 1;
    }

    public static int getRegionPos(int chunkPos) {
        return chunkPos >> 5;
    }

    public static String toCacheKey(Path dimDir, int regionX, int regionZ) {
        return regionX + dimDir.toString() + regionZ;
    }

    public boolean exists() {
        return RegionLoader.getRegionFile(FMLClientHandler.instance().getClient(), this.getMinChunkX(), this.getMinChunkZ()).exists();
    }

    public int getXOffset(int chunkX) {
        if (chunkX >> 5 != this.regionX) {
            throw new IllegalArgumentException("chunkX " + chunkX + " out of bounds for regionX " + this.regionX);
        }
        int offset = chunkX % chunkSqRt * 16;
        if (offset < 0) {
            offset = chunkSqRt * 16 + offset;
        }
        return offset;
    }

    public int getZOffset(int chunkZ) {
        if (RegionCoord.getRegionPos(chunkZ) != this.regionZ) {
            throw new IllegalArgumentException("chunkZ " + chunkZ + " out of bounds for regionZ " + this.regionZ);
        }
        int offset = chunkZ % chunkSqRt * 16;
        if (offset < 0) {
            offset = chunkSqRt * 16 + offset;
        }
        return offset;
    }

    public int getMinChunkX() {
        return RegionCoord.getMinChunkX(this.regionX);
    }

    public int getMaxChunkX() {
        return RegionCoord.getMaxChunkX(this.regionX);
    }

    public int getMinChunkZ() {
        return RegionCoord.getMinChunkZ(this.regionZ);
    }

    public int getMaxChunkZ() {
        return RegionCoord.getMaxChunkZ(this.regionZ);
    }

    public ChunkPos getMinChunkCoord() {
        return new ChunkPos(this.getMinChunkX(), this.getMinChunkZ());
    }

    public ChunkPos getMaxChunkCoord() {
        return new ChunkPos(this.getMaxChunkX(), this.getMaxChunkZ());
    }

    public List<ChunkPos> getChunkCoordsInRegion() {
        ArrayList<ChunkPos> list = new ArrayList<ChunkPos>(1024);
        ChunkPos min = this.getMinChunkCoord();
        ChunkPos max = this.getMaxChunkCoord();
        for (int x = min.field_77276_a; x <= max.field_77276_a; ++x) {
            for (int z = min.field_77275_b; z <= max.field_77275_b; ++z) {
                list.add(new ChunkPos(x, z));
            }
        }
        return list;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RegionCoord [");
        builder.append(this.regionX);
        builder.append(",");
        builder.append(this.regionZ);
        builder.append("]");
        return builder.toString();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        RegionCoord that = (RegionCoord)o;
        if (this.dimension != that.dimension) {
            return false;
        }
        if (this.regionX != that.regionX) {
            return false;
        }
        if (this.regionZ != that.regionZ) {
            return false;
        }
        if (!this.dimDir.equals(that.dimDir)) {
            return false;
        }
        return this.worldDir.equals(that.worldDir);
    }

    public String cacheKey() {
        return this.theCacheKey;
    }

    public int hashCode() {
        return this.theHashCode;
    }

    @Override
    public int compareTo(RegionCoord o) {
        int cx = Double.compare(this.regionX, o.regionX);
        return cx == 0 ? Double.compare(this.regionZ, o.regionZ) : cx;
    }
}

