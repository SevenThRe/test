/*
 * Decompiled with CFR 0.152.
 */
package journeymap.server.properties;

import journeymap.common.properties.config.BooleanField;
import journeymap.server.properties.DefaultDimensionProperties;
import journeymap.server.properties.PermissionProperties;
import journeymap.server.properties.PropertiesManager;
import journeymap.server.properties.ServerCategory;

public class DimensionProperties
extends PermissionProperties {
    public final BooleanField enabled = new BooleanField(ServerCategory.General, "Enable Configuration", false).categoryMaster(true);
    protected final Integer dimension;

    public DimensionProperties(Integer dimension) {
        super(String.format("Dimension %s Configuration", dimension), "Overrides the Global Server Configuration for this dimension - sent enable true to override global settings for this dim");
        this.dimension = dimension;
    }

    @Override
    public String getName() {
        return "dim" + this.dimension;
    }

    public Integer getDimension() {
        return this.dimension;
    }

    public DimensionProperties build() {
        DefaultDimensionProperties defaultProp = PropertiesManager.getInstance().getDefaultDimensionProperties();
        this.teleportEnabled.set(defaultProp.teleportEnabled.get());
        this.enabled.set(defaultProp.enabled.get());
        this.opCaveMappingEnabled.set(defaultProp.opCaveMappingEnabled.get());
        this.caveMappingEnabled.set(defaultProp.caveMappingEnabled.get());
        this.opSurfaceMappingEnabled.set(defaultProp.opSurfaceMappingEnabled.get());
        this.surfaceMappingEnabled.set(defaultProp.surfaceMappingEnabled.get());
        this.opTopoMappingEnabled.set(defaultProp.opTopoMappingEnabled.get());
        this.topoMappingEnabled.set(defaultProp.topoMappingEnabled.get());
        this.opRadarEnabled.set(defaultProp.opRadarEnabled.get());
        this.radarEnabled.set(defaultProp.radarEnabled.get());
        this.playerRadarEnabled.set(defaultProp.playerRadarEnabled.get());
        this.villagerRadarEnabled.set(defaultProp.villagerRadarEnabled.get());
        this.animalRadarEnabled.set(defaultProp.animalRadarEnabled.get());
        this.mobRadarEnabled.set(defaultProp.mobRadarEnabled.get());
        this.save();
        return this;
    }
}

