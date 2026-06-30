/*
 * Decompiled with CFR 0.152.
 */
package journeymap.server.properties;

import journeymap.common.properties.config.BooleanField;
import journeymap.server.properties.ServerCategory;
import journeymap.server.properties.ServerPropertiesBase;

public abstract class PermissionProperties
extends ServerPropertiesBase {
    public final BooleanField teleportEnabled = new BooleanField(ServerCategory.General, "Enable Players to teleport", false);
    public final BooleanField opSurfaceMappingEnabled = new BooleanField(ServerCategory.Surface, "Enable Op surface maps", true);
    public final BooleanField surfaceMappingEnabled = new BooleanField(ServerCategory.Surface, "Enable surface maps", true);
    public final BooleanField opTopoMappingEnabled = new BooleanField(ServerCategory.Topo, "Enable Op topo maps", true);
    public final BooleanField topoMappingEnabled = new BooleanField(ServerCategory.Topo, "Enable topo maps", true);
    public final BooleanField opCaveMappingEnabled = new BooleanField(ServerCategory.Cave, "Enable Op cave maps", true);
    public final BooleanField caveMappingEnabled = new BooleanField(ServerCategory.Cave, "Enable cave maps", true);
    public final BooleanField opRadarEnabled = new BooleanField(ServerCategory.Radar, "Enable Op radar", true);
    public final BooleanField radarEnabled = new BooleanField(ServerCategory.Radar, "Enable radar", true);
    public final BooleanField playerRadarEnabled = new BooleanField(ServerCategory.Radar, "Enable player radar", true);
    public final BooleanField villagerRadarEnabled = new BooleanField(ServerCategory.Radar, "Enable villager radar", true);
    public final BooleanField animalRadarEnabled = new BooleanField(ServerCategory.Radar, "Enable animal radar", true);
    public final BooleanField mobRadarEnabled = new BooleanField(ServerCategory.Radar, "Enable mob radar", true);

    protected PermissionProperties(String displayName, String description) {
        super(displayName, description);
    }
}

