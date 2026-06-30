/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.util.datafix.DataFixesManager
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.ChunkPos
 *  net.minecraft.world.World
 *  net.minecraft.world.chunk.storage.AnvilChunkLoader
 *  org.apache.logging.log4j.Logger
 */
package journeymap.client.task.multi;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import journeymap.client.Constants;
import journeymap.client.JourneymapClient;
import journeymap.client.api.display.Context;
import journeymap.client.api.display.DisplayType;
import journeymap.client.api.display.PolygonOverlay;
import journeymap.client.api.impl.ClientAPI;
import journeymap.client.api.model.MapPolygon;
import journeymap.client.api.model.ShapeProperties;
import journeymap.client.api.model.TextProperties;
import journeymap.client.cartography.ChunkRenderController;
import journeymap.client.data.DataCache;
import journeymap.client.feature.Feature;
import journeymap.client.feature.FeatureManager;
import journeymap.client.io.FileHandler;
import journeymap.client.io.nbt.ChunkLoader;
import journeymap.client.io.nbt.RegionLoader;
import journeymap.client.log.ChatLog;
import journeymap.client.model.ChunkMD;
import journeymap.client.model.EntityDTO;
import journeymap.client.model.MapType;
import journeymap.client.model.RegionCoord;
import journeymap.client.model.RegionImageCache;
import journeymap.client.task.multi.BaseMapTask;
import journeymap.client.task.multi.ITask;
import journeymap.client.task.multi.ITaskManager;
import journeymap.client.ui.fullscreen.Fullscreen;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import org.apache.logging.log4j.Logger;

public class MapRegionTask
extends BaseMapTask {
    private static final int MAX_RUNTIME = 30000;
    private static final Logger logger = Journeymap.getLogger();
    private static volatile long lastTaskCompleted;
    public static MapType MAP_TYPE;
    final PolygonOverlay regionOverlay;
    final RegionCoord rCoord;
    final Collection<ChunkPos> retainedCoords;

    private MapRegionTask(ChunkRenderController renderController, World world, MapType mapType, RegionCoord rCoord, Collection<ChunkPos> chunkCoords, Collection<ChunkPos> retainCoords) {
        super(renderController, world, mapType, chunkCoords, true, false, 5000);
        this.rCoord = rCoord;
        this.retainedCoords = retainCoords;
        this.regionOverlay = this.createOverlay();
    }

    public static BaseMapTask create(ChunkRenderController renderController, RegionCoord rCoord, MapType mapType, Minecraft minecraft) {
        WorldClient world = minecraft.world;
        List<ChunkPos> renderCoords = rCoord.getChunkCoordsInRegion();
        ArrayList<ChunkPos> retainedCoords = new ArrayList<ChunkPos>(renderCoords.size());
        HashMap<RegionCoord, Boolean> existingRegions = new HashMap<RegionCoord, Boolean>();
        for (ChunkPos coord : renderCoords) {
            for (ChunkPos keepAliveOffset : keepAliveOffsets) {
                ChunkPos keepAliveCoord = new ChunkPos(coord.x + keepAliveOffset.x, coord.z + keepAliveOffset.z);
                RegionCoord neighborRCoord = RegionCoord.fromChunkPos(rCoord.worldDir, mapType, keepAliveCoord.x, keepAliveCoord.z);
                if (!existingRegions.containsKey(neighborRCoord)) {
                    existingRegions.put(neighborRCoord, neighborRCoord.exists());
                }
                if (renderCoords.contains(keepAliveCoord) || !((Boolean)existingRegions.get(neighborRCoord)).booleanValue()) continue;
                retainedCoords.add(keepAliveCoord);
            }
        }
        return new MapRegionTask(renderController, (World)world, mapType, rCoord, renderCoords, retainedCoords);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void performTask(Minecraft mc, JourneymapClient jm, File jmWorldDir, boolean threadLogging) throws InterruptedException {
        block6: {
            block5: {
                ChunkMD chunkMD;
                ClientAPI.INSTANCE.show(this.regionOverlay);
                AnvilChunkLoader loader = new AnvilChunkLoader(FileHandler.getWorldSaveDir(mc), DataFixesManager.createFixer());
                int missing = 0;
                for (ChunkPos coord : this.retainedCoords) {
                    chunkMD = ChunkLoader.getChunkMD(loader, mc, coord, true);
                    if (chunkMD == null || !chunkMD.hasChunk()) continue;
                    DataCache.INSTANCE.addChunkMD(chunkMD);
                }
                for (ChunkPos coord : this.chunkCoords) {
                    chunkMD = ChunkLoader.getChunkMD(loader, mc, coord, true);
                    if (chunkMD != null && chunkMD.hasChunk()) {
                        DataCache.INSTANCE.addChunkMD(chunkMD);
                        continue;
                    }
                    ++missing;
                }
                if (this.chunkCoords.size() - missing <= 0) break block5;
                try {
                    logger.info(String.format("Potential chunks to map in %s: %s of %s", this.rCoord, this.chunkCoords.size() - missing, this.chunkCoords.size()));
                    super.performTask(mc, jm, jmWorldDir, threadLogging);
                    this.regionOverlay.getShapeProperties().setFillColor(0xFFFFFF).setFillOpacity(0.15f).setStrokeColor(0xFFFFFF);
                }
                catch (Throwable throwable) {
                    this.regionOverlay.getShapeProperties().setFillColor(0xFFFFFF).setFillOpacity(0.15f).setStrokeColor(0xFFFFFF);
                    String label = String.format("%s\nRegion [%s,%s]", Constants.getString("jm.common.automap_region_complete"), this.rCoord.regionX, this.rCoord.regionZ);
                    this.regionOverlay.setLabel(label);
                    this.regionOverlay.flagForRerender();
                    throw throwable;
                }
                String label = String.format("%s\nRegion [%s,%s]", Constants.getString("jm.common.automap_region_complete"), this.rCoord.regionX, this.rCoord.regionZ);
                this.regionOverlay.setLabel(label);
                this.regionOverlay.flagForRerender();
                break block6;
            }
            logger.info(String.format("Skipping empty region: %s", this.rCoord));
        }
    }

    protected PolygonOverlay createOverlay() {
        String displayId = "AutoMap" + this.rCoord;
        String groupName = "AutoMap";
        String label = String.format("%s\nRegion [%s,%s]", Constants.getString("jm.common.automap_region_start"), this.rCoord.regionX, this.rCoord.regionZ);
        ShapeProperties shapeProps = new ShapeProperties().setStrokeWidth(2.0f).setStrokeColor(255).setStrokeOpacity(0.7f).setFillColor(65280).setFillOpacity(0.2f);
        TextProperties textProps = new TextProperties().setBackgroundColor(34).setBackgroundOpacity(0.5f).setColor(65280).setOpacity(1.0f).setFontShadow(true);
        int x = this.rCoord.getMinChunkX() << 4;
        int y = 70;
        int z = this.rCoord.getMinChunkZ() << 4;
        int maxX = (this.rCoord.getMaxChunkX() << 4) + 15;
        int maxZ = (this.rCoord.getMaxChunkZ() << 4) + 15;
        BlockPos sw = new BlockPos(x, y, maxZ);
        BlockPos se = new BlockPos(maxX, y, maxZ);
        BlockPos ne = new BlockPos(maxX, y, z);
        BlockPos nw = new BlockPos(x, y, z);
        MapPolygon polygon = new MapPolygon(sw, se, ne, nw);
        PolygonOverlay regionOverlay = new PolygonOverlay("journeymap", displayId, this.rCoord.dimension, shapeProps, polygon);
        regionOverlay.setOverlayGroupName(groupName).setLabel(label).setTextProperties(textProps).setActiveUIs(EnumSet.of(Context.UI.Fullscreen, Context.UI.Webmap)).setActiveMapTypes(EnumSet.of(Context.MapType.Any));
        return regionOverlay;
    }

    @Override
    protected void complete(int mappedChunks, boolean cancelled, boolean hadError) {
        lastTaskCompleted = System.currentTimeMillis();
        RegionImageCache.INSTANCE.flushToDiskAsync(true);
        DataCache.INSTANCE.stopChunkMDRetention();
        if (hadError || cancelled) {
            logger.warn("MapRegionTask cancelled %s hadError %s", (Object)cancelled, (Object)hadError);
        } else {
            logger.info(String.format("Actual chunks mapped in %s: %s ", this.rCoord, mappedChunks));
            this.regionOverlay.setTitle(Constants.getString("jm.common.automap_region_chunks", mappedChunks));
        }
        long usedPct = this.getMemoryUsage();
        if (usedPct >= 85L) {
            logger.warn(String.format("Memory usage at %2d%%, forcing garbage collection", usedPct));
            System.gc();
            usedPct = this.getMemoryUsage();
        }
        logger.info(String.format("Memory usage at %2d%%", usedPct));
    }

    private long getMemoryUsage() {
        long max = Runtime.getRuntime().maxMemory();
        long total = Runtime.getRuntime().totalMemory();
        long free = Runtime.getRuntime().freeMemory();
        return (total - free) * 100L / max;
    }

    @Override
    public int getMaxRuntime() {
        return 30000;
    }

    public static class Manager
    implements ITaskManager {
        final int mapTaskDelay = 0;
        RegionLoader regionLoader;
        boolean enabled;

        @Override
        public Class<? extends ITask> getTaskClass() {
            return MapRegionTask.class;
        }

        @Override
        public boolean enableTask(Minecraft minecraft, Object params) {
            EntityDTO player = DataCache.getPlayer();
            boolean cavesAllowed = FeatureManager.isAllowed(Feature.MapCaves);
            boolean underground = player.underground;
            if (underground && !cavesAllowed) {
                logger.info("Cave mapping not permitted.");
                return false;
            }
            boolean bl = this.enabled = params != null;
            if (!this.enabled) {
                return false;
            }
            if (System.currentTimeMillis() - lastTaskCompleted < (long)Journeymap.getClient().getCoreProperties().autoMapPoll.get().intValue()) {
                return false;
            }
            this.enabled = false;
            if (minecraft.isIntegratedServerRunning()) {
                try {
                    MapType mapType = MAP_TYPE;
                    if (mapType == null) {
                        mapType = Fullscreen.state().getMapType();
                    }
                    Boolean mapAll = params == null ? false : (Boolean)params;
                    this.regionLoader = new RegionLoader(minecraft, mapType, mapAll);
                    if (this.regionLoader.getRegionsFound() == 0) {
                        this.disableTask(minecraft);
                    } else {
                        this.enabled = true;
                    }
                }
                catch (Throwable t) {
                    String error = "Couldn't Auto-Map: " + t.getMessage();
                    ChatLog.announceError(error);
                    logger.error(error + ": " + LogFormatter.toString(t));
                }
            }
            return this.enabled;
        }

        @Override
        public boolean isEnabled(Minecraft minecraft) {
            return this.enabled;
        }

        @Override
        public void disableTask(Minecraft minecraft) {
            if (this.regionLoader != null) {
                if (this.regionLoader.isUnderground()) {
                    ChatLog.announceI18N("jm.common.automap_complete_underground", this.regionLoader.getVSlice());
                } else {
                    ChatLog.announceI18N("jm.common.automap_complete", new Object[0]);
                }
            }
            this.enabled = false;
            if (this.regionLoader != null) {
                RegionImageCache.INSTANCE.flushToDisk(true);
                RegionImageCache.INSTANCE.clear();
                this.regionLoader.getRegions().clear();
                this.regionLoader = null;
            }
            ClientAPI.INSTANCE.removeAll("journeymap", DisplayType.Polygon);
        }

        @Override
        public BaseMapTask getTask(Minecraft minecraft) {
            if (!this.enabled) {
                return null;
            }
            if (this.regionLoader.getRegions().isEmpty()) {
                this.disableTask(minecraft);
                return null;
            }
            RegionCoord rCoord = this.regionLoader.getRegions().peek();
            ChunkRenderController chunkRenderController = Journeymap.getClient().getChunkRenderController();
            BaseMapTask baseMapTask = MapRegionTask.create(chunkRenderController, rCoord, this.regionLoader.getMapType(), minecraft);
            return baseMapTask;
        }

        @Override
        public void taskAccepted(ITask task, boolean accepted) {
            if (accepted) {
                this.regionLoader.getRegions().pop();
                float total = 1.0f * (float)this.regionLoader.getRegionsFound();
                float remaining = total - (float)this.regionLoader.getRegions().size();
                String percent = new DecimalFormat("##.#").format(remaining * 100.0f / total) + "%";
                if (this.regionLoader.isUnderground()) {
                    ChatLog.announceI18N("jm.common.automap_status_underground", this.regionLoader.getVSlice(), percent);
                } else {
                    ChatLog.announceI18N("jm.common.automap_status", percent);
                }
            }
        }
    }
}

