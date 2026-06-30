/*
 * Decompiled with CFR 0.152.
 */
package journeymap.server.properties;

import journeymap.common.network.Constants;
import journeymap.common.properties.config.BooleanField;
import journeymap.common.properties.config.IntegerField;
import journeymap.server.properties.PermissionProperties;
import journeymap.server.properties.ServerCategory;

public class GlobalProperties
extends PermissionProperties {
    public final BooleanField useWorldId = new BooleanField(ServerCategory.General, "Use world id", false);
    public final BooleanField playerTrackingEnabled = new BooleanField(ServerCategory.General, "Enable player tracking", true);
    public final BooleanField opPlayerTrackingEnabled = new BooleanField(ServerCategory.General, "Enable player tracking by Ops and Admins", true);
    public final IntegerField playerTrackingUpdateTime = new IntegerField(ServerCategory.General, "Player tracking update time in ticks, 1-20", Constants.TRACKING_MIN, Constants.TRACKING_MAX, Constants.TRACKING_DEFUALT);

    public GlobalProperties() {
        super("Global Server Configuration", "Applies to all dimensions unless overridden.");
    }

    @Override
    public String getName() {
        return "global";
    }

    @Override
    protected void postLoad(boolean isNew) {
        super.postLoad(isNew);
    }

    @Override
    protected void preSave() {
        super.preSave();
    }
}

