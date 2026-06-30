/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 *  com.google.common.base.Objects
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 */
package journeymap.client.api.util;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.awt.geom.Rectangle2D;
import javax.annotation.Nullable;
import journeymap.client.api.display.Context;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public final class UIState {
    public final Context.UI ui;
    public final boolean active;
    public final int dimension;
    public final int zoom;
    public final Context.MapType mapType;
    public final BlockPos mapCenter;
    public final Integer chunkY;
    public final AxisAlignedBB blockBounds;
    public final Rectangle2D.Double displayBounds;
    public final double blockSize;

    public UIState(Context.UI ui, boolean active, int dimension, int zoom, @Nullable Context.MapType mapType, @Nullable BlockPos mapCenter, @Nullable Integer chunkY, @Nullable AxisAlignedBB blockBounds, @Nullable Rectangle2D.Double displayBounds) {
        this.ui = ui;
        this.active = active;
        this.dimension = dimension;
        this.zoom = zoom;
        this.mapType = mapType;
        this.mapCenter = mapCenter;
        this.chunkY = chunkY;
        this.blockBounds = blockBounds;
        this.displayBounds = displayBounds;
        this.blockSize = Math.pow(2.0, zoom);
    }

    public static UIState newInactive(Context.UI ui, Minecraft minecraft) {
        BlockPos center = minecraft.field_71441_e == null ? new BlockPos(0, 68, 0) : minecraft.field_71441_e.func_175694_M();
        return new UIState(ui, false, 0, 0, Context.MapType.Day, center, null, null, null);
    }

    public static UIState newInactive(UIState priorState) {
        return new UIState(priorState.ui, false, priorState.dimension, priorState.zoom, priorState.mapType, priorState.mapCenter, priorState.chunkY, priorState.blockBounds, priorState.displayBounds);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        UIState mapState = (UIState)o;
        return Objects.equal((Object)this.active, (Object)mapState.active) && Objects.equal((Object)this.dimension, (Object)mapState.dimension) && Objects.equal((Object)this.zoom, (Object)mapState.zoom) && Objects.equal((Object)this.ui, (Object)mapState.ui) && Objects.equal((Object)this.mapType, (Object)mapState.mapType) && Objects.equal((Object)this.displayBounds, (Object)mapState.displayBounds);
    }

    public int hashCode() {
        return Objects.hashCode((Object[])new Object[]{this.ui, this.active, this.dimension, this.zoom, this.mapType, this.displayBounds});
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object)this).add("ui", (Object)this.ui).add("active", this.active).add("dimension", this.dimension).add("mapType", (Object)this.mapType).add("zoom", this.zoom).add("mapCenter", (Object)this.mapCenter).add("chunkY", (Object)this.chunkY).add("blockBounds", (Object)this.blockBounds).add("displayBounds", (Object)this.displayBounds).add("blockSize", this.blockSize).toString();
    }
}

