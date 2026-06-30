/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ArrayListMultimap
 *  com.google.common.collect.ImmutableListMultimap$Builder
 *  com.google.common.collect.ListMultimap
 *  com.google.common.collect.Multimap
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.ChunkPos
 */
package journeymap.client.task.multi;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import journeymap.client.Constants;
import journeymap.client.data.DataCache;
import journeymap.client.model.MapType;
import journeymap.client.properties.CoreProperties;
import journeymap.client.ui.option.KeyedEnum;
import journeymap.common.Journeymap;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.ChunkPos;

public class RenderSpec {
    private static DecimalFormat decFormat = new DecimalFormat("##.#");
    private static volatile RenderSpec lastSurfaceRenderSpec;
    private static volatile RenderSpec lastTopoRenderSpec;
    private static volatile RenderSpec lastUndergroundRenderSpec;
    private static Minecraft minecraft;
    private final EntityPlayer player;
    private final MapType mapType;
    private final int primaryRenderDistance;
    private final int maxSecondaryRenderDistance;
    private final RevealShape revealShape;
    private ListMultimap<Integer, Offset> offsets = null;
    private ArrayList<ChunkPos> primaryRenderCoords;
    private Comparator<ChunkPos> comparator;
    private int lastSecondaryRenderDistance;
    private ChunkPos lastPlayerCoord;
    private long lastTaskTime;
    private int lastTaskChunks;
    private double lastTaskAvgChunkTime;

    private RenderSpec(Minecraft minecraft, MapType mapType) {
        int mapRenderDistanceMax;
        this.player = minecraft.player;
        CoreProperties props = Journeymap.getClient().getCoreProperties();
        int gameRenderDistance = Math.max(1, minecraft.gameSettings.renderDistanceChunks - 1);
        int mapRenderDistanceMin = mapRenderDistanceMax = (mapType.isUnderground() ? props.renderDistanceCaveMax.get() : props.renderDistanceSurfaceMax.get()).intValue();
        this.mapType = mapType;
        int rdMin = Math.min(gameRenderDistance, mapRenderDistanceMin);
        int rdMax = Math.min(gameRenderDistance, Math.max(rdMin, mapRenderDistanceMax));
        if (rdMin + 1 == rdMax) {
            ++rdMin;
        }
        this.primaryRenderDistance = rdMin;
        this.maxSecondaryRenderDistance = rdMax;
        this.revealShape = (RevealShape)Journeymap.getClient().getCoreProperties().revealShape.get();
        this.lastPlayerCoord = new ChunkPos(minecraft.player.chunkCoordX, minecraft.player.chunkCoordZ);
        this.lastSecondaryRenderDistance = this.primaryRenderDistance;
    }

    private static Double blockDistance(ChunkPos playerCoord, ChunkPos coord) {
        int x = (playerCoord.x << 4) + 8 - ((coord.x << 4) + 8);
        int z = (playerCoord.z << 4) + 8 - ((coord.z << 4) + 8);
        return Math.sqrt(x * x + z * z);
    }

    private static Double chunkDistance(ChunkPos playerCoord, ChunkPos coord) {
        int x = playerCoord.x - coord.x;
        int z = playerCoord.z - coord.z;
        return Math.sqrt(x * x + z * z);
    }

    static boolean inRange(ChunkPos playerCoord, ChunkPos coord, int renderDistance, RevealShape revealShape) {
        if (revealShape == RevealShape.Circle) {
            double distance = RenderSpec.blockDistance(playerCoord, coord);
            double diff = distance - (double)(renderDistance * 16);
            return diff <= 8.0;
        }
        float x = Math.abs(playerCoord.x - coord.x);
        float z = Math.abs(playerCoord.z - coord.z);
        return x <= (float)renderDistance && z <= (float)renderDistance;
    }

    private static ListMultimap<Integer, Offset> calculateOffsets(int minOffset, int maxOffset, RevealShape revealShape) {
        ArrayListMultimap multimap = ArrayListMultimap.create();
        boolean baseX = false;
        boolean baseZ = false;
        ChunkPos baseCoord = new ChunkPos(0, 0);
        for (int offset = maxOffset; offset >= minOffset; --offset) {
            for (int x = 0 - offset; x <= 0 + offset; ++x) {
                for (int z = 0 - offset; z <= 0 + offset; ++z) {
                    ChunkPos coord = new ChunkPos(x, z);
                    if (revealShape != RevealShape.Square && !RenderSpec.inRange(baseCoord, coord, offset, revealShape)) continue;
                    multimap.put((Object)offset, (Object)new Offset(coord.x, coord.z));
                }
            }
            if (offset >= maxOffset) continue;
            List oneUp = multimap.get((Object)(offset + 1));
            oneUp.removeAll(multimap.get((Object)offset));
        }
        for (int i = minOffset; i <= maxOffset; ++i) {
            multimap.get((Object)i).sort((o1, o2) -> Double.compare(o1.distance(), o2.distance()));
        }
        return new ImmutableListMultimap.Builder().putAll((Multimap)multimap).build();
    }

    public static RenderSpec getSurfaceSpec() {
        if (lastSurfaceRenderSpec == null || RenderSpec.lastSurfaceRenderSpec.lastPlayerCoord.x != RenderSpec.minecraft.player.chunkCoordX || RenderSpec.lastSurfaceRenderSpec.lastPlayerCoord.z != RenderSpec.minecraft.player.chunkCoordZ) {
            RenderSpec newSpec = new RenderSpec(minecraft, MapType.day(DataCache.getPlayer()));
            newSpec.copyLastStatsFrom(lastSurfaceRenderSpec);
            lastSurfaceRenderSpec = newSpec;
        }
        return lastSurfaceRenderSpec;
    }

    public static RenderSpec getTopoSpec() {
        if (lastTopoRenderSpec == null || RenderSpec.lastTopoRenderSpec.lastPlayerCoord.x != RenderSpec.minecraft.player.chunkCoordX || RenderSpec.lastTopoRenderSpec.lastPlayerCoord.z != RenderSpec.minecraft.player.chunkCoordZ) {
            RenderSpec newSpec = new RenderSpec(minecraft, MapType.topo(DataCache.getPlayer()));
            newSpec.copyLastStatsFrom(lastTopoRenderSpec);
            lastTopoRenderSpec = newSpec;
        }
        return lastTopoRenderSpec;
    }

    public static RenderSpec getUndergroundSpec() {
        if (lastUndergroundRenderSpec == null || RenderSpec.lastUndergroundRenderSpec.lastPlayerCoord.x != RenderSpec.minecraft.player.chunkCoordX || RenderSpec.lastUndergroundRenderSpec.lastPlayerCoord.z != RenderSpec.minecraft.player.chunkCoordZ) {
            RenderSpec newSpec = new RenderSpec(minecraft, MapType.underground(DataCache.getPlayer()));
            newSpec.copyLastStatsFrom(lastUndergroundRenderSpec);
            lastUndergroundRenderSpec = newSpec;
        }
        return lastUndergroundRenderSpec;
    }

    public static void resetRenderSpecs() {
        lastUndergroundRenderSpec = null;
        lastSurfaceRenderSpec = null;
        lastTopoRenderSpec = null;
    }

    protected List<ChunkPos> getRenderAreaCoords() {
        if (this.offsets == null) {
            this.offsets = RenderSpec.calculateOffsets(this.primaryRenderDistance, this.maxSecondaryRenderDistance, this.revealShape);
        }
        DataCache dataCache = DataCache.INSTANCE;
        if (this.lastPlayerCoord == null || this.lastPlayerCoord.x != this.player.chunkCoordX || this.lastPlayerCoord.z != this.player.chunkCoordZ) {
            this.primaryRenderCoords = null;
            this.lastSecondaryRenderDistance = this.primaryRenderDistance;
        }
        this.lastPlayerCoord = new ChunkPos(RenderSpec.minecraft.player.chunkCoordX, RenderSpec.minecraft.player.chunkCoordZ);
        if (this.primaryRenderCoords == null || this.primaryRenderCoords.isEmpty()) {
            List primaryOffsets = this.offsets.get((Object)this.primaryRenderDistance);
            this.primaryRenderCoords = new ArrayList(primaryOffsets.size());
            for (Offset offset : primaryOffsets) {
                ChunkPos primaryCoord = offset.from(this.lastPlayerCoord);
                this.primaryRenderCoords.add(primaryCoord);
                dataCache.getChunkMD(primaryCoord);
            }
        }
        if (this.maxSecondaryRenderDistance == this.primaryRenderDistance) {
            return new ArrayList<ChunkPos>(this.primaryRenderCoords);
        }
        if (this.lastSecondaryRenderDistance == this.maxSecondaryRenderDistance) {
            this.lastSecondaryRenderDistance = this.primaryRenderDistance;
        }
        ++this.lastSecondaryRenderDistance;
        List secondaryOffsets = this.offsets.get((Object)this.lastSecondaryRenderDistance);
        ArrayList<ChunkPos> renderCoords = new ArrayList<ChunkPos>(this.primaryRenderCoords.size() + secondaryOffsets.size());
        for (Offset offset : secondaryOffsets) {
            ChunkPos secondaryCoord = offset.from(this.lastPlayerCoord);
            renderCoords.add(secondaryCoord);
            dataCache.getChunkMD(secondaryCoord);
        }
        renderCoords.addAll(0, this.primaryRenderCoords);
        return renderCoords;
    }

    public Boolean isUnderground() {
        return this.mapType.isUnderground();
    }

    public Boolean isTopo() {
        return this.mapType.isTopo();
    }

    public Boolean getSurface() {
        return this.mapType.isSurface();
    }

    public int getPrimaryRenderDistance() {
        return this.primaryRenderDistance;
    }

    public int getMaxSecondaryRenderDistance() {
        return this.maxSecondaryRenderDistance;
    }

    public int getLastSecondaryRenderDistance() {
        return this.lastSecondaryRenderDistance;
    }

    public RevealShape getRevealShape() {
        return this.revealShape;
    }

    public int getLastSecondaryRenderSize() {
        if (this.primaryRenderDistance == this.maxSecondaryRenderDistance) {
            return 0;
        }
        return this.offsets == null ? 0 : this.offsets.get((Object)this.lastSecondaryRenderDistance).size();
    }

    public int getPrimaryRenderSize() {
        return this.offsets == null ? 0 : this.offsets.get((Object)this.primaryRenderDistance).size();
    }

    public void setLastTaskInfo(int chunks, long elapsedNs) {
        this.lastTaskChunks = chunks;
        this.lastTaskTime = TimeUnit.NANOSECONDS.toMillis(elapsedNs);
        this.lastTaskAvgChunkTime = (double)(elapsedNs / (long)Math.max(1, chunks)) / 1000000.0;
    }

    public int getLastTaskChunks() {
        return this.lastTaskChunks;
    }

    public void copyLastStatsFrom(RenderSpec other) {
        if (other != null) {
            this.lastTaskChunks = other.lastTaskChunks;
            this.lastTaskTime = other.lastTaskTime;
            this.lastTaskAvgChunkTime = other.lastTaskAvgChunkTime;
        }
    }

    public String getDebugStats() {
        String debugString = this.isUnderground() != false ? "jm.common.renderstats_debug_cave" : (this.isTopo() != false ? "jm.common.renderstats_debug_topo" : "jm.common.renderstats_debug_surface");
        debugString = debugString + "_simple";
        return Constants.getString(debugString, this.primaryRenderDistance, this.lastTaskChunks, this.lastTaskTime, decFormat.format(this.lastTaskAvgChunkTime));
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        RenderSpec that = (RenderSpec)o;
        if (this.maxSecondaryRenderDistance != that.maxSecondaryRenderDistance) {
            return false;
        }
        if (this.primaryRenderDistance != that.primaryRenderDistance) {
            return false;
        }
        if (this.revealShape != that.revealShape) {
            return false;
        }
        return this.mapType.equals(that.mapType);
    }

    public int hashCode() {
        int result = this.mapType.hashCode();
        result = 31 * result + this.primaryRenderDistance;
        result = 31 * result + this.maxSecondaryRenderDistance;
        result = 31 * result + this.revealShape.hashCode();
        return result;
    }

    static {
        minecraft = Minecraft.getMinecraft();
    }

    private static class Offset {
        final int x;
        final int z;

        private Offset(int x, int z) {
            this.x = x;
            this.z = z;
        }

        ChunkPos from(ChunkPos coord) {
            return new ChunkPos(coord.x + this.x, coord.z + this.z);
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            Offset offset = (Offset)o;
            if (this.x != offset.x) {
                return false;
            }
            return this.z == offset.z;
        }

        public double distance() {
            return Math.sqrt(this.x * this.x + this.z * this.z);
        }

        public int hashCode() {
            int result = this.x;
            result = 31 * result + this.z;
            return result;
        }
    }

    public static enum RevealShape implements KeyedEnum
    {
        Square("jm.minimap.shape_square"),
        Circle("jm.minimap.shape_circle");

        public final String key;

        private RevealShape(String key) {
            this.key = key;
        }

        @Override
        public String getKey() {
            return this.key;
        }

        public String toString() {
            return Constants.getString(this.key);
        }
    }
}

