/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 */
package journeymap.client.feature;

import java.util.HashMap;
import java.util.Map;
import journeymap.client.data.DataCache;
import journeymap.client.feature.Feature;
import journeymap.client.feature.Policy;
import journeymap.client.model.MapType;
import journeymap.client.ui.fullscreen.Fullscreen;
import journeymap.client.ui.minimap.MiniMap;
import journeymap.common.Journeymap;
import journeymap.server.properties.PermissionProperties;
import net.minecraft.entity.EntityLivingBase;

public enum FeatureManager {
    INSTANCE;

    private final HashMap<Feature, Policy> policyMap = new HashMap();

    private FeatureManager() {
        this.reset();
    }

    public static String getPolicyDetails() {
        StringBuilder sb = new StringBuilder("Features: ");
        for (Feature feature : Feature.values()) {
            boolean single = false;
            boolean multi = false;
            if (FeatureManager.INSTANCE.policyMap.containsKey((Object)feature)) {
                single = FeatureManager.INSTANCE.policyMap.get((Object)((Object)feature)).allowInSingleplayer;
                multi = FeatureManager.INSTANCE.policyMap.get((Object)((Object)feature)).allowInMultiplayer;
            }
            sb.append(String.format("\n\t%s : singleplayer = %s , multiplayer = %s", feature.name(), single, multi));
        }
        return sb.toString();
    }

    public static boolean isAllowed(Feature feature) {
        Policy policy = FeatureManager.INSTANCE.policyMap.get((Object)feature);
        return policy != null && policy.isCurrentlyAllowed();
    }

    public static Map<Feature, Boolean> getAllowedFeatures() {
        HashMap<Feature, Boolean> map = new HashMap<Feature, Boolean>(Feature.values().length * 2);
        for (Feature feature : Feature.values()) {
            map.put(feature, FeatureManager.isAllowed(feature));
        }
        return map;
    }

    public void updateDimensionFeatures(PermissionProperties properties) {
        if (!properties.caveMappingEnabled.get().booleanValue()) {
            Journeymap.getLogger().info("Feature disabled: " + (Object)((Object)Feature.MapCaves));
            this.nextMapType(Feature.MapCaves);
            this.policyMap.put(Feature.MapCaves, new Policy(Feature.MapCaves, false, false));
        } else if (!this.policyMap.get((Object)Feature.MapCaves).isCurrentlyAllowed() && properties.caveMappingEnabled.get().booleanValue()) {
            Journeymap.getLogger().info("Feature enabled: " + (Object)((Object)Feature.MapCaves));
            this.policyMap.put(Feature.MapCaves, new Policy(Feature.MapCaves, true, true));
            if (MapType.none().equals(Fullscreen.state().getMapType())) {
                this.setMapType(MapType.underground(DataCache.getPlayer()));
            }
        }
        if (!properties.topoMappingEnabled.get().booleanValue()) {
            Journeymap.getLogger().info("Feature disabled: " + (Object)((Object)Feature.MapTopo));
            this.nextMapType(Feature.MapTopo);
            this.policyMap.put(Feature.MapTopo, new Policy(Feature.MapTopo, false, false));
        } else if (!this.policyMap.get((Object)Feature.MapTopo).isCurrentlyAllowed() && properties.topoMappingEnabled.get().booleanValue()) {
            Journeymap.getLogger().info("Feature enabled: " + (Object)((Object)Feature.MapTopo));
            this.policyMap.put(Feature.MapTopo, new Policy(Feature.MapTopo, true, true));
            if (MapType.none().equals(Fullscreen.state().getMapType())) {
                this.setMapType(MapType.topo(DataCache.getPlayer()));
            }
        }
        if (!properties.surfaceMappingEnabled.get().booleanValue()) {
            Journeymap.getLogger().info("Feature disabled: " + (Object)((Object)Feature.MapSurface));
            this.nextMapType(Feature.MapSurface);
            this.policyMap.put(Feature.MapSurface, new Policy(Feature.MapSurface, false, false));
        } else if (!this.policyMap.get((Object)Feature.MapSurface).isCurrentlyAllowed() && properties.surfaceMappingEnabled.get().booleanValue()) {
            Journeymap.getLogger().info("Feature enabled: " + (Object)((Object)Feature.MapSurface));
            this.policyMap.put(Feature.MapSurface, new Policy(Feature.MapSurface, true, true));
            if (MapType.none().equals(Fullscreen.state().getMapType())) {
                long time = ((EntityLivingBase)DataCache.getPlayer().entityLivingRef.get()).world.getWorldInfo().getWorldTime() % 24000L;
                MapType mapType = time < 13800L ? MapType.day(DataCache.getPlayer()) : MapType.night(DataCache.getPlayer());
                this.setMapType(mapType);
            }
        }
        if (properties.radarEnabled.get().booleanValue()) {
            this.setMultiplayerFeature(Feature.RadarAnimals, properties.animalRadarEnabled.get());
            this.setMultiplayerFeature(Feature.RadarMobs, properties.mobRadarEnabled.get());
            this.setMultiplayerFeature(Feature.RadarPlayers, properties.playerRadarEnabled.get());
            this.setMultiplayerFeature(Feature.RadarVillagers, properties.villagerRadarEnabled.get());
        } else {
            this.setMultiplayerFeature(Feature.RadarAnimals, false);
            this.setMultiplayerFeature(Feature.RadarMobs, false);
            this.setMultiplayerFeature(Feature.RadarPlayers, false);
            this.setMultiplayerFeature(Feature.RadarVillagers, false);
        }
    }

    private void nextMapType(Feature feature) {
        if (this.policyMap.get((Object)feature).isCurrentlyAllowed() && Fullscreen.state() != null) {
            if (Fullscreen.state().isSurfaceMappingAllowed() && !Feature.MapSurface.equals((Object)feature)) {
                this.setMapType(MapType.day(DataCache.getPlayer()));
                return;
            }
            if (Fullscreen.state().isTopoMappingAllowed() && !Feature.MapTopo.equals((Object)feature)) {
                this.setMapType(MapType.topo(DataCache.getPlayer()));
                return;
            }
            if (Fullscreen.state().isCaveMappingAllowed() && !Feature.MapCaves.equals((Object)feature)) {
                this.setMapType(MapType.underground(DataCache.getPlayer()));
                return;
            }
        }
    }

    private void setMapType(MapType to) {
        Fullscreen.state().setMapType(to);
        MiniMap.state().setMapType(to);
    }

    private void setMultiplayerFeature(Feature feature, boolean enable) {
        if (!enable) {
            Journeymap.getLogger().info("Feature disabled: " + (Object)((Object)feature));
        }
        this.policyMap.put(feature, new Policy(feature, true, enable));
    }

    public void reset() {
        for (Policy policy : Policy.bulkCreate(true, true)) {
            this.policyMap.put(policy.feature, policy);
        }
    }
}

