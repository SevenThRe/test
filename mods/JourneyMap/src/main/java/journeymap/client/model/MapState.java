/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Objects
 *  com.google.common.collect.Iterables
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.WorldProviderHell
 */
package journeymap.client.model;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import journeymap.client.api.impl.ClientAPI;
import journeymap.client.data.DataCache;
import journeymap.client.feature.Feature;
import journeymap.client.feature.FeatureManager;
import journeymap.client.io.FileHandler;
import journeymap.client.log.StatTimer;
import journeymap.client.model.EntityDTO;
import journeymap.client.model.EntityHelper;
import journeymap.client.model.MapType;
import journeymap.client.properties.CoreProperties;
import journeymap.client.properties.InGameMapProperties;
import journeymap.client.properties.MapProperties;
import journeymap.client.render.draw.DrawStep;
import journeymap.client.render.draw.DrawWayPointStep;
import journeymap.client.render.draw.RadarDrawStepFactory;
import journeymap.client.render.draw.WaypointDrawStepFactory;
import journeymap.client.render.map.GridRenderer;
import journeymap.client.task.multi.MapPlayerTask;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import journeymap.common.properties.Category;
import journeymap.common.properties.config.IntegerField;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldProviderHell;

public class MapState {
    public final int minZoom = 0;
    public final int maxZoom = 5;
    public AtomicBoolean follow = new AtomicBoolean(true);
    public String playerLastPos = "0,0";
    private StatTimer refreshTimer = StatTimer.get("MapState.refresh");
    private StatTimer generateDrawStepsTimer = StatTimer.get("MapState.generateDrawSteps");
    private MapType lastMapType;
    private File worldDir = null;
    private long lastRefresh = 0L;
    private long lastMapTypeChange = 0L;
    private IntegerField lastSlice = new IntegerField(Category.Hidden, "", 0, 15, 4);
    private boolean surfaceMappingAllowed = false;
    private boolean caveMappingAllowed = false;
    private boolean caveMappingEnabled = false;
    private boolean topoMappingAllowed = false;
    private List<DrawStep> drawStepList = new ArrayList<DrawStep>();
    private List<DrawWayPointStep> drawWaypointStepList = new ArrayList<DrawWayPointStep>();
    private String playerBiome = "";
    private InGameMapProperties lastMapProperties = null;
    private List<EntityDTO> entityList = new ArrayList<EntityDTO>(32);
    private int lastPlayerChunkX = 0;
    private int lastPlayerChunkY = 0;
    private int lastPlayerChunkZ = 0;
    private boolean highQuality;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void refresh(Minecraft mc, EntityPlayer player, InGameMapProperties mapProperties) {
        WorldClient world = mc.field_71441_e;
        if (world == null || world.field_73011_w == null) {
            return;
        }
        this.refreshTimer.start();
        try {
            CoreProperties coreProperties = Journeymap.getClient().getCoreProperties();
            this.lastMapProperties = mapProperties;
            this.worldDir = FileHandler.getJMWorldDir(mc);
            if (world != null && world.func_72940_L() != 256 && this.lastSlice.getMaxValue() != 15) {
                int maxSlice = world.func_72940_L() / 16 - 1;
                int seaLevel = Math.round(world.func_181545_F() / 16);
                int currentSlice = this.lastSlice.get();
                this.lastSlice = new IntegerField(Category.Hidden, "", 0, maxSlice, seaLevel);
                this.lastSlice.set(currentSlice);
            }
            boolean hasSurface = !(world.field_73011_w instanceof WorldProviderHell);
            this.caveMappingAllowed = FeatureManager.isAllowed(Feature.MapCaves);
            this.caveMappingEnabled = this.caveMappingAllowed && mapProperties.showCaves.get() != false;
            this.surfaceMappingAllowed = hasSurface && FeatureManager.isAllowed(Feature.MapSurface);
            this.topoMappingAllowed = hasSurface && FeatureManager.isAllowed(Feature.MapTopo) && coreProperties.mapTopography.get() != false;
            this.highQuality = coreProperties.tileHighDisplayQuality.get();
            this.lastPlayerChunkX = player.field_70176_ah;
            this.lastPlayerChunkY = player.field_70162_ai;
            this.lastPlayerChunkZ = player.field_70164_aj;
            EntityDTO playerDTO = DataCache.getPlayer();
            this.playerBiome = playerDTO.biome;
            if (this.lastMapType != null) {
                if (player.field_71093_bK != this.lastMapType.dimension) {
                    this.lastMapType = null;
                } else if (this.caveMappingEnabled && this.follow.get() && playerDTO.underground.booleanValue() && !this.lastMapType.isUnderground()) {
                    this.lastMapType = null;
                } else if (!this.lastMapType.isAllowed()) {
                    this.lastMapType = null;
                }
            }
            this.lastMapType = this.getMapType();
            this.updateLastRefresh();
        }
        catch (Exception e) {
            Journeymap.getLogger().error("Error refreshing MapState: " + LogFormatter.toPartialString(e));
        }
        finally {
            this.refreshTimer.stop();
        }
    }

    public MapType setMapType(MapType.Name mapTypeName) {
        return this.setMapType(MapType.from(mapTypeName, DataCache.getPlayer()));
    }

    public MapType toggleMapType() {
        MapType.Name next = this.getNextMapType(this.getMapType().name);
        return this.setMapType(next);
    }

    public MapType.Name getNextMapType(MapType.Name name) {
        EntityDTO player = DataCache.getPlayer();
        EntityLivingBase playerEntity = (EntityLivingBase)player.entityLivingRef.get();
        if (playerEntity == null) {
            return name;
        }
        ArrayList<MapType.Name> types = new ArrayList<MapType.Name>(4);
        if (this.surfaceMappingAllowed) {
            types.add(MapType.Name.day);
            types.add(MapType.Name.night);
        }
        if (this.caveMappingAllowed && (player.underground.booleanValue() || name == MapType.Name.underground)) {
            types.add(MapType.Name.underground);
        }
        if (this.topoMappingAllowed) {
            types.add(MapType.Name.topo);
        }
        if (name == MapType.Name.none && !types.isEmpty()) {
            return (MapType.Name)((Object)types.get(0));
        }
        if (types.contains((Object)name)) {
            Iterator cyclingIterator = Iterables.cycle(types).iterator();
            while (cyclingIterator.hasNext()) {
                MapType.Name current = (MapType.Name)((Object)cyclingIterator.next());
                if (current != name) continue;
                return (MapType.Name)((Object)cyclingIterator.next());
            }
        }
        return name;
    }

    public MapType setMapType(MapType mapType) {
        if (!mapType.isAllowed() && !(mapType = MapType.from(this.getNextMapType(mapType.name), DataCache.getPlayer())).isAllowed()) {
            mapType = MapType.none();
        }
        EntityDTO player = DataCache.getPlayer();
        if (player.underground.booleanValue() != mapType.isUnderground()) {
            this.follow.set(false);
        }
        if (mapType.isUnderground()) {
            if (player.chunkCoordY != mapType.vSlice) {
                this.follow.set(false);
            }
            this.lastSlice.set(mapType.vSlice);
        } else if (this.lastMapProperties != null && mapType.name != MapType.Name.none && this.lastMapProperties.preferredMapType.get() != mapType.name) {
            this.lastMapProperties.preferredMapType.set(mapType.name);
            this.lastMapProperties.save();
        }
        this.setLastMapTypeChange(mapType);
        return this.lastMapType;
    }

    public MapType getMapType() {
        if (this.lastMapType == null) {
            EntityDTO player = DataCache.getPlayer();
            MapType mapType = null;
            try {
                if (this.caveMappingEnabled && player.underground.booleanValue()) {
                    mapType = MapType.underground(player);
                } else if (this.follow.get() && this.surfaceMappingAllowed && !player.underground.booleanValue()) {
                    mapType = MapType.day(player);
                }
                if (mapType == null) {
                    mapType = MapType.from((MapType.Name)((Object)this.lastMapProperties.preferredMapType.get()), player);
                }
            }
            catch (Exception e) {
                mapType = MapType.day(player);
            }
            this.setMapType(mapType);
        }
        return this.lastMapType;
    }

    public long getLastMapTypeChange() {
        return this.lastMapTypeChange;
    }

    private void setLastMapTypeChange(MapType mapType) {
        if (!Objects.equal((Object)mapType, (Object)this.lastMapType)) {
            this.lastMapTypeChange = System.currentTimeMillis();
            this.requireRefresh();
        }
        this.lastMapType = mapType;
    }

    public boolean isUnderground() {
        return this.getMapType().isUnderground();
    }

    public File getWorldDir() {
        return this.worldDir;
    }

    public String getPlayerBiome() {
        return this.playerBiome;
    }

    public List<? extends DrawStep> getDrawSteps() {
        return this.drawStepList;
    }

    public List<DrawWayPointStep> getDrawWaypointSteps() {
        return this.drawWaypointStepList;
    }

    public void generateDrawSteps(Minecraft mc, GridRenderer gridRenderer, WaypointDrawStepFactory waypointRenderer, RadarDrawStepFactory radarRenderer, InGameMapProperties mapProperties, boolean checkWaypointDistance) {
        this.generateDrawStepsTimer.start();
        this.lastMapProperties = mapProperties;
        this.drawStepList.clear();
        this.drawWaypointStepList.clear();
        this.entityList.clear();
        ClientAPI.INSTANCE.getDrawSteps(this.drawStepList, gridRenderer.getUIState());
        if (FeatureManager.isAllowed(Feature.RadarAnimals) && (mapProperties.showAnimals.get().booleanValue() || mapProperties.showPets.get().booleanValue())) {
            this.entityList.addAll(DataCache.INSTANCE.getAnimals(false).values());
        }
        if (FeatureManager.isAllowed(Feature.RadarVillagers) && mapProperties.showVillagers.get().booleanValue()) {
            this.entityList.addAll(DataCache.INSTANCE.getVillagers(false).values());
        }
        if (FeatureManager.isAllowed(Feature.RadarMobs) && mapProperties.showMobs.get().booleanValue()) {
            this.entityList.addAll(DataCache.INSTANCE.getMobs(false).values());
        }
        if (FeatureManager.isAllowed(Feature.RadarPlayers) && mapProperties.showPlayers.get().booleanValue()) {
            this.entityList.addAll(DataCache.INSTANCE.getPlayers(false).values());
        }
        if (!this.entityList.isEmpty()) {
            Collections.sort(this.entityList, EntityHelper.entityMapComparator);
            this.drawStepList.addAll(radarRenderer.prepareSteps(this.entityList, gridRenderer, mapProperties));
        }
        if (mapProperties.showWaypoints.get().booleanValue()) {
            boolean showLabel = mapProperties.showWaypointLabels.get();
            this.drawWaypointStepList.addAll(waypointRenderer.prepareSteps(DataCache.INSTANCE.getWaypoints(false), gridRenderer, checkWaypointDistance, showLabel));
        }
        this.generateDrawStepsTimer.stop();
    }

    public boolean zoomIn() {
        if (this.lastMapProperties.zoomLevel.get() < 5) {
            return this.setZoom(this.lastMapProperties.zoomLevel.get() + 1);
        }
        return false;
    }

    public boolean zoomOut() {
        if (this.lastMapProperties.zoomLevel.get() > 0) {
            return this.setZoom(this.lastMapProperties.zoomLevel.get() - 1);
        }
        return false;
    }

    public boolean setZoom(int zoom) {
        if (zoom > 5 || zoom < 0 || zoom == this.lastMapProperties.zoomLevel.get()) {
            return false;
        }
        this.lastMapProperties.zoomLevel.set(zoom);
        this.requireRefresh();
        return true;
    }

    public int getZoom() {
        return this.lastMapProperties.zoomLevel.get();
    }

    public void requireRefresh() {
        this.lastRefresh = 0L;
    }

    public void updateLastRefresh() {
        this.lastRefresh = System.currentTimeMillis();
    }

    public boolean shouldRefresh(Minecraft mc, MapProperties mapProperties) {
        if (ClientAPI.INSTANCE.isDrawStepsUpdateNeeded()) {
            return true;
        }
        if (MapPlayerTask.getlastTaskCompleted() - this.lastRefresh > 500L) {
            return true;
        }
        if (this.lastMapType == null) {
            return true;
        }
        EntityDTO player = DataCache.getPlayer();
        if (this.getMapType().dimension != player.dimension) {
            return true;
        }
        double d0 = this.lastPlayerChunkX - player.chunkCoordX;
        double d1 = this.lastPlayerChunkY - player.chunkCoordY;
        double d2 = this.lastPlayerChunkZ - player.chunkCoordZ;
        double diff = MathHelper.func_76133_a((double)(d0 * d0 + d1 * d1 + d2 * d2));
        if (diff > 2.0) {
            return true;
        }
        return this.lastMapProperties == null || !this.lastMapProperties.equals(mapProperties);
    }

    public boolean isHighQuality() {
        return this.highQuality;
    }

    public boolean isCaveMappingAllowed() {
        return this.caveMappingAllowed;
    }

    public boolean isCaveMappingEnabled() {
        return this.caveMappingEnabled;
    }

    public boolean isSurfaceMappingAllowed() {
        return this.surfaceMappingAllowed;
    }

    public boolean isTopoMappingAllowed() {
        return this.topoMappingAllowed;
    }

    public int getDimension() {
        return this.getMapType().dimension;
    }

    public IntegerField getLastSlice() {
        return this.lastSlice;
    }

    public void resetMapType() {
        this.lastMapType = null;
    }
}

