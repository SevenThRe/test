/*
 * Decompiled with CFR 0.152.
 */
package journeymap.client.api.display;

import journeymap.client.api.model.MapImage;

public interface IWaypointDisplay {
    public Integer getColor();

    public Integer getBackgroundColor();

    public int[] getDisplayDimensions();

    public MapImage getIcon();
}

