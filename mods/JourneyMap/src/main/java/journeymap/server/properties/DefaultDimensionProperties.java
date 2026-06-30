/*
 * Decompiled with CFR 0.152.
 */
package journeymap.server.properties;

import journeymap.common.properties.config.BooleanField;
import journeymap.server.properties.PermissionProperties;
import journeymap.server.properties.ServerCategory;

public class DefaultDimensionProperties
extends PermissionProperties {
    public final BooleanField enabled = new BooleanField(ServerCategory.General, "Enable Configuration", false).categoryMaster(true);

    protected DefaultDimensionProperties() {
        super("default", "New Dimension properties will be based on this file.");
    }

    @Override
    public String getName() {
        return "default";
    }
}

