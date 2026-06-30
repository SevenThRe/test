/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.ParametersAreNonnullByDefault
 *  net.minecraft.util.math.BlockPos
 */
package journeymap.client.api.display;

import javax.annotation.ParametersAreNonnullByDefault;
import journeymap.client.api.display.Overlay;
import journeymap.client.api.model.MapImage;
import net.minecraft.util.math.BlockPos;

@ParametersAreNonnullByDefault
public final class MarkerOverlay
extends Overlay {
    private BlockPos point;
    private MapImage icon;

    public MarkerOverlay(String modId, String markerId, BlockPos point, MapImage icon) {
        super(modId, markerId);
        this.setPoint(point);
        this.setIcon(icon);
    }

    public BlockPos getPoint() {
        return this.point;
    }

    public MarkerOverlay setPoint(BlockPos point) {
        this.point = point;
        return this;
    }

    public MapImage getIcon() {
        return this.icon;
    }

    public MarkerOverlay setIcon(MapImage icon) {
        this.icon = icon;
        return this;
    }

    public String toString() {
        return this.toStringHelper(this).add("icon", (Object)this.icon).add("point", (Object)this.point).toString();
    }
}

