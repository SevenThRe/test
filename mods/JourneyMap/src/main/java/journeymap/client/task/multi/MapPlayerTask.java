/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.Cache
 *  com.google.common.cache.CacheBuilder
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.math.ChunkPos
 *  net.minecraft.world.World
 */
package journeymap.client.task.multi;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import journeymap.client.Constants;
import journeymap.client.JourneymapClient;
import journeymap.client.cartography.ChunkRenderController;
import journeymap.client.data.DataCache;
import journeymap.client.feature.Feature;
import journeymap.client.feature.FeatureManager;
import journeymap.client.model.ChunkMD;
import journeymap.client.model.EntityDTO;
import journeymap.client.model.MapType;
import journeymap.client.properties.CoreProperties;
import journeymap.client.task.multi.BaseMapTask;
import journeymap.client.task.multi.ITask;
import journeymap.client.task.multi.ITaskManager;
import journeymap.client.task.multi.RenderSpec;
import journeymap.client.task.multi.TaskBatch;
import journeymap.common.Journeymap;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

public class MapPlayerTask
extends BaseMapTask {
    private static int MAX_STALE_MILLISECONDS = 30000;
    private static int MAX_BATCH_SIZE = 32;
    private static DecimalFormat decFormat = new DecimalFormat("##.#");
    private static volatile long lastTaskCompleted;
    private static long lastTaskTime;
    private static double lastTaskAvgChunkTime;
    private static Cache<String, String> tempDebugLines;
    private final int maxRuntime;
    private int scheduledChunks;
    private long startNs;
    private long elapsedNs;

    private MapPlayerTask(ChunkRenderController chunkRenderController, World world, MapType mapType, Collection<ChunkPos> chunkCoords) {
        super(chunkRenderController, world, mapType, chunkCoords, false, true, 10000);
        this.maxRuntime = Journeymap.getClient().getCoreProperties().renderDelay.get() * 3000;
        this.scheduledChunks = 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void forceNearbyRemap() {
        Class<MapPlayerTask> clazz = MapPlayerTask.class;
        synchronized (MapPlayerTask.class) {
            DataCache.INSTANCE.invalidateChunkMDCache();
            // ** MonitorExit[var0] (shouldn't be in output)
            return;
        }
    }

    public static MapPlayerTaskBatch create(ChunkRenderController chunkRenderController, EntityDTO player) {
        long time;
        boolean surfaceAllowed = FeatureManager.isAllowed(Feature.MapSurface);
        boolean cavesAllowed = FeatureManager.isAllowed(Feature.MapCaves);
        if (!surfaceAllowed && !cavesAllowed) {
            return null;
        }
        EntityLivingBase playerEntity = (EntityLivingBase)player.entityLivingRef.get();
        if (playerEntity == null) {
            return null;
        }
        boolean underground = player.underground;
        MapType mapType = underground ? MapType.underground(player) : ((time = playerEntity.field_70170_p.func_72912_H().func_76073_f() % 24000L) < 13800L ? MapType.day(player) : MapType.night(player));
        ArrayList<ITask> tasks = new ArrayList<ITask>(2);
        tasks.add(new MapPlayerTask(chunkRenderController, playerEntity.field_70170_p, mapType, new ArrayList<ChunkPos>()));
        if (underground) {
            if (surfaceAllowed && Journeymap.getClient().getCoreProperties().alwaysMapSurface.get().booleanValue()) {
                tasks.add(new MapPlayerTask(chunkRenderController, playerEntity.field_70170_p, MapType.day(player), new ArrayList<ChunkPos>()));
            }
        } else if (cavesAllowed && Journeymap.getClient().getCoreProperties().alwaysMapCaves.get().booleanValue()) {
            tasks.add(new MapPlayerTask(chunkRenderController, playerEntity.field_70170_p, MapType.underground(player), new ArrayList<ChunkPos>()));
        }
        if (Journeymap.getClient().getCoreProperties().mapTopography.get().booleanValue()) {
            tasks.add(new MapPlayerTask(chunkRenderController, playerEntity.field_70170_p, MapType.topo(player), new ArrayList<ChunkPos>()));
        }
        return new MapPlayerTaskBatch(tasks);
    }

    public static String[] getDebugStats() {
        try {
            CoreProperties coreProperties = Journeymap.getClient().getCoreProperties();
            boolean underground = DataCache.getPlayer().underground;
            ArrayList lines = new ArrayList(tempDebugLines.asMap().values());
            if (underground || coreProperties.alwaysMapCaves.get().booleanValue()) {
                lines.add(RenderSpec.getUndergroundSpec().getDebugStats());
            }
            if (!underground || coreProperties.alwaysMapSurface.get().booleanValue()) {
                lines.add(RenderSpec.getSurfaceSpec().getDebugStats());
            }
            if (!underground && coreProperties.mapTopography.get().booleanValue()) {
                lines.add(RenderSpec.getTopoSpec().getDebugStats());
            }
            return lines.toArray(new String[lines.size()]);
        }
        catch (Throwable t) {
            logger.error((Object)t);
            return new String[0];
        }
    }

    public static void addTempDebugMessage(String key, String message) {
        if (Minecraft.func_71410_x().field_71474_y.field_181657_aC) {
            tempDebugLines.put((Object)key, (Object)message);
        }
    }

    public static void removeTempDebugMessage(String key) {
        tempDebugLines.invalidate((Object)key);
    }

    public static String getSimpleStats() {
        RenderSpec spec;
        int primaryRenderSize = 0;
        int secondaryRenderSize = 0;
        int totalChunks = 0;
        if ((DataCache.getPlayer().underground.booleanValue() || Journeymap.getClient().getCoreProperties().alwaysMapCaves.get().booleanValue()) && (spec = RenderSpec.getUndergroundSpec()) != null) {
            primaryRenderSize += spec.getPrimaryRenderSize();
            secondaryRenderSize += spec.getLastSecondaryRenderSize();
            totalChunks += spec.getLastTaskChunks();
        }
        if ((!DataCache.getPlayer().underground.booleanValue() || Journeymap.getClient().getCoreProperties().alwaysMapSurface.get().booleanValue()) && (spec = RenderSpec.getSurfaceSpec()) != null) {
            primaryRenderSize += spec.getPrimaryRenderSize();
            secondaryRenderSize += spec.getLastSecondaryRenderSize();
            totalChunks += spec.getLastTaskChunks();
        }
        return Constants.getString("jm.common.renderstats", totalChunks, primaryRenderSize, secondaryRenderSize, lastTaskTime, decFormat.format(lastTaskAvgChunkTime));
    }

    public static long getlastTaskCompleted() {
        return lastTaskCompleted;
    }

    @Override
    public void initTask(Minecraft minecraft, JourneymapClient jm, File jmWorldDir, boolean threadLogging) throws InterruptedException {
        this.startNs = System.nanoTime();
        RenderSpec renderSpec = null;
        renderSpec = this.mapType.isUnderground() ? RenderSpec.getUndergroundSpec() : (this.mapType.isTopo() ? RenderSpec.getTopoSpec() : RenderSpec.getSurfaceSpec());
        long now = System.currentTimeMillis();
        List<ChunkPos> renderArea = renderSpec.getRenderAreaCoords();
        int maxBatchSize = renderArea.size() / 4;
        renderArea.removeIf(chunkPos -> {
            ChunkMD chunkMD = DataCache.INSTANCE.getChunkMD((ChunkPos)chunkPos);
            if (chunkMD == null || !chunkMD.hasChunk() || now - chunkMD.getLastRendered(this.mapType) < 30000L) {
                return true;
            }
            if (chunkMD.getDimension() != this.mapType.dimension) {
                return true;
            }
            chunkMD.resetBlockData(this.mapType);
            return false;
        });
        if (renderArea.size() <= maxBatchSize) {
            this.chunkCoords.addAll(renderArea);
        } else {
            List<ChunkPos> list = Arrays.asList(renderArea.toArray(new ChunkPos[renderArea.size()]));
            this.chunkCoords.addAll(list.subList(0, maxBatchSize));
        }
        this.scheduledChunks = this.chunkCoords.size();
    }

    @Override
    protected void complete(int mappedChunks, boolean cancelled, boolean hadError) {
        this.elapsedNs = System.nanoTime() - this.startNs;
    }

    @Override
    public int getMaxRuntime() {
        return this.maxRuntime;
    }

    static {
        tempDebugLines = CacheBuilder.newBuilder().maximumSize(20L).expireAfterWrite(1500L, TimeUnit.MILLISECONDS).build();
    }

    public static class MapPlayerTaskBatch
    extends TaskBatch {
        public MapPlayerTaskBatch(List<ITask> tasks) {
            super(tasks);
        }

        @Override
        public void performTask(Minecraft mc, JourneymapClient jm, File jmWorldDir, boolean threadLogging) throws InterruptedException {
            if (mc.field_71439_g == null) {
                return;
            }
            this.startNs = System.nanoTime();
            ArrayList tasks = new ArrayList(this.taskList);
            super.performTask(mc, jm, jmWorldDir, threadLogging);
            this.elapsedNs = System.nanoTime() - this.startNs;
            lastTaskTime = TimeUnit.NANOSECONDS.toMillis(this.elapsedNs);
            lastTaskCompleted = System.currentTimeMillis();
            int chunkCount = 0;
            for (ITask task : tasks) {
                if (task instanceof MapPlayerTask) {
                    MapPlayerTask mapPlayerTask = (MapPlayerTask)task;
                    chunkCount += mapPlayerTask.scheduledChunks;
                    if (mapPlayerTask.mapType.isUnderground()) {
                        RenderSpec.getUndergroundSpec().setLastTaskInfo(mapPlayerTask.scheduledChunks, mapPlayerTask.elapsedNs);
                        continue;
                    }
                    if (mapPlayerTask.mapType.isTopo()) {
                        RenderSpec.getTopoSpec().setLastTaskInfo(mapPlayerTask.scheduledChunks, mapPlayerTask.elapsedNs);
                        continue;
                    }
                    RenderSpec.getSurfaceSpec().setLastTaskInfo(mapPlayerTask.scheduledChunks, mapPlayerTask.elapsedNs);
                    continue;
                }
                Journeymap.getLogger().warn("Unexpected task in batch: " + task);
            }
            lastTaskAvgChunkTime = (double)(this.elapsedNs / (long)Math.max(1, chunkCount)) / 1000000.0;
        }
    }

    public static class Manager
    implements ITaskManager {
        final int mapTaskDelay;
        boolean enabled;

        public Manager() {
            this.mapTaskDelay = Journeymap.getClient().getCoreProperties().renderDelay.get() * 1000;
        }

        public Class<? extends BaseMapTask> getTaskClass() {
            return MapPlayerTask.class;
        }

        @Override
        public boolean enableTask(Minecraft minecraft, Object params) {
            this.enabled = true;
            return this.enabled;
        }

        @Override
        public boolean isEnabled(Minecraft minecraft) {
            return this.enabled;
        }

        @Override
        public void disableTask(Minecraft minecraft) {
            this.enabled = false;
        }

        @Override
        public ITask getTask(Minecraft minecraft) {
            if (this.enabled && minecraft.field_71439_g.field_70175_ag && System.currentTimeMillis() - lastTaskCompleted >= (long)this.mapTaskDelay) {
                ChunkRenderController chunkRenderController = Journeymap.getClient().getChunkRenderController();
                return MapPlayerTask.create(chunkRenderController, DataCache.getPlayer());
            }
            return null;
        }

        @Override
        public void taskAccepted(ITask task, boolean accepted) {
        }
    }
}

