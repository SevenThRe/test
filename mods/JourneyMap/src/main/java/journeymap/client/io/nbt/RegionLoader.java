/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.datafix.DataFixesManager
 *  net.minecraft.util.math.ChunkPos
 *  net.minecraft.world.World
 *  net.minecraft.world.chunk.storage.AnvilChunkLoader
 *  org.apache.logging.log4j.Logger
 */
package journeymap.client.io.nbt;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import journeymap.client.io.FileHandler;
import journeymap.client.io.RegionImageHandler;
import journeymap.client.model.MapType;
import journeymap.client.model.RegionCoord;
import journeymap.client.model.RegionImageCache;
import journeymap.common.Journeymap;
import net.minecraft.client.Minecraft;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import org.apache.logging.log4j.Logger;

public class RegionLoader {
    private static final Pattern anvilPattern = Pattern.compile("r\\.([^\\.]+)\\.([^\\.]+)\\.mca");
    final Logger logger = Journeymap.getLogger();
    final MapType mapType;
    final Stack<RegionCoord> regions;
    final int regionsFound;

    public RegionLoader(Minecraft minecraft, MapType mapType, boolean all) throws IOException {
        this.mapType = mapType;
        this.regions = this.findRegions(minecraft, mapType, all);
        this.regionsFound = this.regions.size();
    }

    public static File getRegionFile(Minecraft minecraft, int dimension, int chunkX, int chunkZ) {
        File regionDir = new File(FileHandler.getWorldSaveDir(minecraft), "region");
        File regionFile = new File(regionDir, String.format("r.%s.%s.mca", chunkX >> 5, chunkZ >> 5));
        return regionFile;
    }

    public static File getRegionFile(Minecraft minecraft, int chunkX, int chunkZ) {
        File regionDir = new File(FileHandler.getWorldSaveDir(minecraft), "region");
        File regionFile = new File(regionDir, String.format("r.%s.%s.mca", chunkX >> 5, chunkZ >> 5));
        return regionFile;
    }

    public Iterator<RegionCoord> regionIterator() {
        return this.regions.iterator();
    }

    public Stack<RegionCoord> getRegions() {
        return this.regions;
    }

    public int getRegionsFound() {
        return this.regionsFound;
    }

    public boolean isUnderground() {
        return this.mapType.isUnderground();
    }

    public Integer getVSlice() {
        return this.mapType.vSlice;
    }

    Stack<RegionCoord> findRegions(Minecraft mc, MapType mapType, boolean all) {
        RegionCoord playerRc;
        File[] anvilFiles;
        File mcWorldDir = FileHandler.getMCWorldDir(mc, mapType.dimension);
        File regionDir = new File(mcWorldDir, "region");
        if (!regionDir.exists() && !regionDir.mkdirs()) {
            this.logger.warn("MC world region directory isn't usable: " + regionDir);
            return new Stack<RegionCoord>();
        }
        RegionImageCache.INSTANCE.flushToDisk(false);
        RegionImageCache.INSTANCE.clear();
        File jmImageWorldDir = FileHandler.getJMWorldDir(mc);
        Stack<RegionCoord> stack = new Stack<RegionCoord>();
        AnvilChunkLoader anvilChunkLoader = new AnvilChunkLoader(FileHandler.getWorldSaveDir(mc), DataFixesManager.createFixer());
        int validFileCount = 0;
        int existingImageCount = 0;
        block0: for (File anvilFile : anvilFiles = regionDir.listFiles()) {
            Matcher matcher = anvilPattern.matcher(anvilFile.getName());
            if (anvilFile.isDirectory() || !matcher.matches()) continue;
            ++validFileCount;
            String x = matcher.group(1);
            String z = matcher.group(2);
            if (x == null || z == null) continue;
            RegionCoord rc = new RegionCoord(jmImageWorldDir, Integer.parseInt(x), Integer.parseInt(z), mapType.dimension);
            if (all) {
                stack.add(rc);
                continue;
            }
            if (!RegionImageHandler.getRegionImageFile(rc, mapType, false).exists()) {
                List<ChunkPos> chunkCoords = rc.getChunkCoordsInRegion();
                for (ChunkPos coord : chunkCoords) {
                    if (!anvilChunkLoader.chunkExists((World)mc.world, coord.x, coord.z)) continue;
                    stack.add(rc);
                    continue block0;
                }
                continue;
            }
            ++existingImageCount;
        }
        if (stack.isEmpty() && validFileCount != existingImageCount) {
            this.logger.warn("Anvil region files in " + regionDir + ": " + validFileCount + ", matching image files: " + existingImageCount + ", but found nothing to do for mapType " + mapType);
        }
        if (stack.contains(playerRc = RegionCoord.fromChunkPos(jmImageWorldDir, mapType, mc.player.chunkCoordX, mc.player.chunkCoordZ))) {
            stack.remove(playerRc);
        }
        Collections.sort(stack, new Comparator<RegionCoord>(){

            @Override
            public int compare(RegionCoord o1, RegionCoord o2) {
                Float d1 = Float.valueOf(this.distanceToPlayer(o1));
                Float d2 = Float.valueOf(this.distanceToPlayer(o2));
                int comp = d2.compareTo(d1);
                if (comp == 0) {
                    return o2.compareTo(o1);
                }
                return comp;
            }

            float distanceToPlayer(RegionCoord rc) {
                float x = rc.regionX - playerRc.regionX;
                float z = rc.regionZ - playerRc.regionZ;
                return x * x + z * z;
            }
        });
        stack.add(playerRc);
        return stack;
    }

    public MapType getMapType() {
        return this.mapType;
    }
}

