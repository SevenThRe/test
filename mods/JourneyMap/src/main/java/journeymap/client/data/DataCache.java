/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.Cache
 *  com.google.common.cache.CacheBuilder
 *  com.google.common.cache.CacheLoader
 *  com.google.common.cache.CacheStats
 *  com.google.common.cache.LoadingCache
 *  com.google.common.collect.Sets
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.ChunkPos
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.data;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import journeymap.client.data.AllData;
import journeymap.client.data.AnimalsData;
import journeymap.client.data.MessagesData;
import journeymap.client.data.MobsData;
import journeymap.client.data.PlayerData;
import journeymap.client.data.PlayersData;
import journeymap.client.data.VillagersData;
import journeymap.client.data.WaypointsData;
import journeymap.client.data.WorldData;
import journeymap.client.io.nbt.ChunkLoader;
import journeymap.client.model.BlockMD;
import journeymap.client.model.ChunkMD;
import journeymap.client.model.EntityDTO;
import journeymap.client.model.MapType;
import journeymap.client.model.RegionCoord;
import journeymap.client.model.RegionImageCache;
import journeymap.client.model.RegionImageSet;
import journeymap.client.model.Waypoint;
import journeymap.client.render.draw.DrawEntityStep;
import journeymap.client.render.draw.DrawWayPointStep;
import journeymap.client.waypoint.WaypointStore;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

public enum DataCache {
    INSTANCE;

    final LoadingCache<Long, Map> all;
    final LoadingCache<Class, Map<String, EntityDTO>> animals;
    final LoadingCache<Class, Map<String, EntityDTO>> mobs;
    final LoadingCache<Class, Map<String, EntityDTO>> players;
    final LoadingCache<Class, Map<String, EntityDTO>> villagers;
    final LoadingCache<Class, Collection<Waypoint>> waypoints;
    final LoadingCache<Class, EntityDTO> player;
    final LoadingCache<Class, WorldData> world;
    final LoadingCache<RegionImageSet.Key, RegionImageSet> regionImageSets;
    final LoadingCache<Class, Map<String, Object>> messages;
    final LoadingCache<EntityLivingBase, DrawEntityStep> entityDrawSteps;
    final LoadingCache<Waypoint, DrawWayPointStep> waypointDrawSteps;
    final LoadingCache<EntityLivingBase, EntityDTO> entityDTOs;
    final Cache<String, RegionCoord> regionCoords;
    final Cache<String, MapType> mapTypes;
    final LoadingCache<IBlockState, BlockMD> blockMetadata;
    final Cache<ChunkPos, ChunkMD> chunkMetadata;
    final HashMap<Cache, String> managedCaches = new HashMap();
    private final int chunkCacheExpireSeconds = 30;
    private final int defaultConcurrencyLevel = 2;

    private DataCache() {
        AllData allData = new AllData();
        this.all = this.getCacheBuilder().maximumSize(1L).expireAfterWrite(allData.getTTL(), TimeUnit.MILLISECONDS).build((CacheLoader)allData);
        this.managedCaches.put((Cache)this.all, "AllData (web)");
        AnimalsData animalsData = new AnimalsData();
        this.animals = this.getCacheBuilder().expireAfterWrite(animalsData.getTTL(), TimeUnit.MILLISECONDS).build((CacheLoader)animalsData);
        this.managedCaches.put((Cache)this.animals, "Animals");
        MobsData mobsData = new MobsData();
        this.mobs = this.getCacheBuilder().expireAfterWrite(mobsData.getTTL(), TimeUnit.MILLISECONDS).build((CacheLoader)mobsData);
        this.managedCaches.put((Cache)this.mobs, "Mobs");
        PlayerData playerData = new PlayerData();
        this.player = this.getCacheBuilder().expireAfterWrite(playerData.getTTL(), TimeUnit.MILLISECONDS).build((CacheLoader)playerData);
        this.managedCaches.put((Cache)this.player, "Player");
        PlayersData playersData = new PlayersData();
        this.players = this.getCacheBuilder().expireAfterWrite(playersData.getTTL(), TimeUnit.MILLISECONDS).build((CacheLoader)playersData);
        this.managedCaches.put((Cache)this.players, "Players");
        VillagersData villagersData = new VillagersData();
        this.villagers = this.getCacheBuilder().expireAfterWrite(villagersData.getTTL(), TimeUnit.MILLISECONDS).build((CacheLoader)villagersData);
        this.managedCaches.put((Cache)this.villagers, "Villagers");
        WaypointsData waypointsData = new WaypointsData();
        this.waypoints = this.getCacheBuilder().expireAfterWrite(waypointsData.getTTL(), TimeUnit.MILLISECONDS).build((CacheLoader)waypointsData);
        this.managedCaches.put((Cache)this.waypoints, "Waypoints");
        WorldData worldData = new WorldData();
        this.world = this.getCacheBuilder().expireAfterWrite(worldData.getTTL(), TimeUnit.MILLISECONDS).build((CacheLoader)worldData);
        this.managedCaches.put((Cache)this.world, "World");
        MessagesData messagesData = new MessagesData();
        this.messages = this.getCacheBuilder().expireAfterWrite(messagesData.getTTL(), TimeUnit.MILLISECONDS).build((CacheLoader)messagesData);
        this.managedCaches.put((Cache)this.messages, "Messages (web)");
        this.entityDrawSteps = this.getCacheBuilder().weakKeys().build((CacheLoader)new DrawEntityStep.SimpleCacheLoader());
        this.managedCaches.put((Cache)this.entityDrawSteps, "DrawEntityStep");
        this.waypointDrawSteps = this.getCacheBuilder().weakKeys().build((CacheLoader)new DrawWayPointStep.SimpleCacheLoader());
        this.managedCaches.put((Cache)this.waypointDrawSteps, "DrawWaypointStep");
        this.entityDTOs = this.getCacheBuilder().weakKeys().build((CacheLoader)new EntityDTO.SimpleCacheLoader());
        this.managedCaches.put((Cache)this.entityDTOs, "EntityDTO");
        this.regionImageSets = RegionImageCache.INSTANCE.initRegionImageSetsCache(this.getCacheBuilder());
        this.managedCaches.put((Cache)this.regionImageSets, "RegionImageSet");
        this.blockMetadata = this.getCacheBuilder().weakKeys().build((CacheLoader)new BlockMD.CacheLoader());
        this.managedCaches.put((Cache)this.blockMetadata, "BlockMD");
        this.chunkMetadata = this.getCacheBuilder().expireAfterAccess(30L, TimeUnit.SECONDS).build();
        this.managedCaches.put(this.chunkMetadata, "ChunkMD");
        this.regionCoords = this.getCacheBuilder().expireAfterAccess(30L, TimeUnit.SECONDS).build();
        this.managedCaches.put(this.regionCoords, "RegionCoord");
        this.mapTypes = this.getCacheBuilder().build();
        this.managedCaches.put(this.mapTypes, "MapType");
    }

    public static EntityDTO getPlayer() {
        return INSTANCE.getPlayer(false);
    }

    private CacheBuilder<Object, Object> getCacheBuilder() {
        CacheBuilder builder = CacheBuilder.newBuilder();
        builder.concurrencyLevel(2);
        if (Journeymap.getClient().getCoreProperties().recordCacheStats.get().booleanValue()) {
            builder.recordStats();
        }
        return builder;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Map getAll(long since) {
        LoadingCache<Long, Map> loadingCache = this.all;
        synchronized (loadingCache) {
            try {
                return (Map)this.all.get((Object)since);
            }
            catch (ExecutionException e) {
                Journeymap.getLogger().error("ExecutionException in getAll: " + LogFormatter.toString(e));
                return Collections.EMPTY_MAP;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Map<String, EntityDTO> getAnimals(boolean forceRefresh) {
        LoadingCache<Class, Map<String, EntityDTO>> loadingCache = this.animals;
        synchronized (loadingCache) {
            try {
                if (forceRefresh) {
                    this.animals.invalidateAll();
                }
                return (Map)this.animals.get(AnimalsData.class);
            }
            catch (ExecutionException e) {
                Journeymap.getLogger().error("ExecutionException in getAnimals: " + LogFormatter.toString(e));
                return Collections.EMPTY_MAP;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Map<String, EntityDTO> getMobs(boolean forceRefresh) {
        LoadingCache<Class, Map<String, EntityDTO>> loadingCache = this.mobs;
        synchronized (loadingCache) {
            try {
                if (forceRefresh) {
                    this.mobs.invalidateAll();
                }
                return (Map)this.mobs.get(MobsData.class);
            }
            catch (ExecutionException e) {
                Journeymap.getLogger().error("ExecutionException in getMobs: " + LogFormatter.toString(e));
                return Collections.EMPTY_MAP;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Map<String, EntityDTO> getPlayers(boolean forceRefresh) {
        LoadingCache<Class, Map<String, EntityDTO>> loadingCache = this.players;
        synchronized (loadingCache) {
            try {
                if (forceRefresh) {
                    this.players.invalidateAll();
                }
                return (Map)this.players.get(PlayersData.class);
            }
            catch (ExecutionException e) {
                Journeymap.getLogger().error("ExecutionException in getPlayers: " + LogFormatter.toString(e));
                return Collections.EMPTY_MAP;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public EntityDTO getPlayer(boolean forceRefresh) {
        LoadingCache<Class, EntityDTO> loadingCache = this.player;
        synchronized (loadingCache) {
            try {
                if (forceRefresh) {
                    this.player.invalidateAll();
                }
                return (EntityDTO)this.player.get(PlayerData.class);
            }
            catch (Exception e) {
                Journeymap.getLogger().error("ExecutionException in getPlayer: " + LogFormatter.toString(e));
                return null;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Map<String, EntityDTO> getVillagers(boolean forceRefresh) {
        LoadingCache<Class, Map<String, EntityDTO>> loadingCache = this.villagers;
        synchronized (loadingCache) {
            try {
                if (forceRefresh) {
                    this.villagers.invalidateAll();
                }
                return (Map)this.villagers.get(VillagersData.class);
            }
            catch (ExecutionException e) {
                Journeymap.getLogger().error("ExecutionException in getVillagers: " + LogFormatter.toString(e));
                return Collections.EMPTY_MAP;
            }
        }
    }

    public MapType getMapType(MapType.Name name, Integer vSlice, int dimension) {
        vSlice = name != MapType.Name.underground ? null : vSlice;
        MapType mapType = (MapType)this.mapTypes.getIfPresent((Object)MapType.toCacheKey(name, vSlice, dimension));
        if (mapType == null) {
            mapType = new MapType(name, vSlice, dimension);
            this.mapTypes.put((Object)mapType.toCacheKey(), (Object)mapType);
        }
        return mapType;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Collection<Waypoint> getWaypoints(boolean forceRefresh) {
        LoadingCache<Class, Collection<Waypoint>> loadingCache = this.waypoints;
        synchronized (loadingCache) {
            if (WaypointsData.isManagerEnabled()) {
                return WaypointStore.INSTANCE.getAll();
            }
            return Collections.EMPTY_LIST;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Map<String, Object> getMessages(boolean forceRefresh) {
        LoadingCache<Class, Map<String, Object>> loadingCache = this.messages;
        synchronized (loadingCache) {
            try {
                if (forceRefresh) {
                    this.messages.invalidateAll();
                }
                return (Map)this.messages.get(MessagesData.class);
            }
            catch (ExecutionException e) {
                Journeymap.getLogger().error("ExecutionException in getMessages: " + LogFormatter.toString(e));
                return Collections.EMPTY_MAP;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public WorldData getWorld(boolean forceRefresh) {
        LoadingCache<Class, WorldData> loadingCache = this.world;
        synchronized (loadingCache) {
            try {
                if (forceRefresh) {
                    this.world.invalidateAll();
                }
                return (WorldData)((Object)this.world.get(WorldData.class));
            }
            catch (ExecutionException e) {
                Journeymap.getLogger().error("ExecutionException in getWorld: " + LogFormatter.toString(e));
                return new WorldData();
            }
        }
    }

    public void resetRadarCaches() {
        this.animals.invalidateAll();
        this.mobs.invalidateAll();
        this.players.invalidateAll();
        this.villagers.invalidateAll();
        this.entityDrawSteps.invalidateAll();
        this.entityDTOs.invalidateAll();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public DrawEntityStep getDrawEntityStep(EntityLivingBase entity) {
        LoadingCache<EntityLivingBase, DrawEntityStep> loadingCache = this.entityDrawSteps;
        synchronized (loadingCache) {
            return (DrawEntityStep)this.entityDrawSteps.getUnchecked((Object)entity);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public EntityDTO getEntityDTO(EntityLivingBase entity) {
        LoadingCache<EntityLivingBase, EntityDTO> loadingCache = this.entityDTOs;
        synchronized (loadingCache) {
            return (EntityDTO)this.entityDTOs.getUnchecked((Object)entity);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public DrawWayPointStep getDrawWayPointStep(Waypoint waypoint) {
        LoadingCache<Waypoint, DrawWayPointStep> loadingCache = this.waypointDrawSteps;
        synchronized (loadingCache) {
            return (DrawWayPointStep)this.waypointDrawSteps.getUnchecked((Object)waypoint);
        }
    }

    public boolean hasBlockMD(IBlockState aBlockState) {
        try {
            return this.blockMetadata.getIfPresent((Object)aBlockState) != null;
        }
        catch (Exception e) {
            return false;
        }
    }

    public BlockMD getBlockMD(IBlockState blockState) {
        try {
            return (BlockMD)this.blockMetadata.get((Object)blockState);
        }
        catch (Exception e) {
            Journeymap.getLogger().error("Error in getBlockMD() for " + blockState + ": " + e);
            return BlockMD.AIRBLOCK;
        }
    }

    public int getBlockMDCount() {
        return this.blockMetadata.asMap().size();
    }

    public Set<BlockMD> getLoadedBlockMDs() {
        return Sets.newHashSet(this.blockMetadata.asMap().values());
    }

    public void resetBlockMetadata() {
        this.blockMetadata.invalidateAll();
    }

    public ChunkMD getChunkMD(BlockPos blockPos) {
        return this.getChunkMD(new ChunkPos(blockPos.getX() >> 4, blockPos.getZ() >> 4));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ChunkMD getChunkMD(ChunkPos coord) {
        Cache<ChunkPos, ChunkMD> cache = this.chunkMetadata;
        synchronized (cache) {
            ChunkMD chunkMD = null;
            try {
                chunkMD = (ChunkMD)this.chunkMetadata.getIfPresent((Object)coord);
                if (chunkMD != null && chunkMD.hasChunk()) {
                    return chunkMD;
                }
                chunkMD = ChunkLoader.getChunkMdFromMemory((World)FMLClientHandler.instance().getClient().world, coord.x, coord.z);
                if (chunkMD != null && chunkMD.hasChunk()) {
                    this.chunkMetadata.put((Object)coord, (Object)chunkMD);
                    return chunkMD;
                }
                if (chunkMD != null) {
                    this.chunkMetadata.invalidate((Object)coord);
                }
                return null;
            }
            catch (Throwable e) {
                Journeymap.getLogger().warn("Unexpected error getting ChunkMD from cache: " + e);
                return null;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addChunkMD(ChunkMD chunkMD) {
        Cache<ChunkPos, ChunkMD> cache = this.chunkMetadata;
        synchronized (cache) {
            this.chunkMetadata.put((Object)chunkMD.getCoord(), (Object)chunkMD);
        }
    }

    public void invalidateChunkMDCache() {
        this.chunkMetadata.invalidateAll();
    }

    public void stopChunkMDRetention() {
        for (ChunkMD chunkMD : this.chunkMetadata.asMap().values()) {
            if (chunkMD == null) continue;
            chunkMD.stopChunkRetention();
        }
    }

    public LoadingCache<RegionImageSet.Key, RegionImageSet> getRegionImageSets() {
        return this.regionImageSets;
    }

    public Cache<String, RegionCoord> getRegionCoords() {
        return this.regionCoords;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void purge() {
        RegionImageCache.INSTANCE.flushToDisk(false);
        this.resetBlockMetadata();
        HashMap<Cache, String> hashMap = this.managedCaches;
        synchronized (hashMap) {
            for (Cache cache : this.managedCaches.keySet()) {
                try {
                    cache.invalidateAll();
                }
                catch (Exception e) {
                    Journeymap.getLogger().warn("Couldn't purge managed cache: " + cache);
                }
            }
        }
    }

    public String getDebugHtml() {
        StringBuffer sb = new StringBuffer();
        if (Journeymap.getClient().getCoreProperties().recordCacheStats.get().booleanValue()) {
            this.appendDebugHtml(sb, "Managed Caches", this.managedCaches);
        } else {
            sb.append("<b>Cache stat recording disabled.  Set config/journeymap.core.config 'recordCacheStats' to 1.</b>");
        }
        return sb.toString();
    }

    private void appendDebugHtml(StringBuffer sb, String name, Map<Cache, String> cacheMap) {
        ArrayList<Map.Entry<Cache, String>> list = new ArrayList<Map.Entry<Cache, String>>(cacheMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Cache, String>>(){

            @Override
            public int compare(Map.Entry<Cache, String> o1, Map.Entry<Cache, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        sb.append("<b>").append(name).append(":</b>");
        sb.append("<pre>");
        for (Map.Entry<Cache, String> entry : list) {
            sb.append(this.toString(entry.getValue(), entry.getKey()));
        }
        sb.append("</pre>");
    }

    private String toString(String label, Cache cache) {
        double avgLoadMillis = 0.0;
        CacheStats cacheStats = cache.stats();
        if (cacheStats.totalLoadTime() > 0L && cacheStats.loadSuccessCount() > 0L) {
            avgLoadMillis = (double)TimeUnit.NANOSECONDS.toMillis(cacheStats.totalLoadTime()) * 1.0 / (double)cacheStats.loadSuccessCount();
        }
        return String.format("%s<b>%20s:</b> Size: %9s, Hits: %9s, Misses: %9s, Loads: %9s, Errors: %9s, Avg Load Time: %1.2fms", LogFormatter.LINEBREAK, label, cache.size(), cacheStats.hitCount(), cacheStats.missCount(), cacheStats.loadCount(), cacheStats.loadExceptionCount(), avgLoadMillis);
    }
}

