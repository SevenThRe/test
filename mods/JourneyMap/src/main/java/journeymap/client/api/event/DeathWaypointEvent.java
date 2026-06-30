/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 *  net.minecraft.util.math.BlockPos
 */
package journeymap.client.api.event;

import com.google.common.base.MoreObjects;
import journeymap.client.api.event.ClientEvent;
import net.minecraft.util.math.BlockPos;

public class DeathWaypointEvent
extends ClientEvent {
    public final BlockPos location;

    public DeathWaypointEvent(BlockPos location, int dimension) {
        super(ClientEvent.Type.DEATH_WAYPOINT, dimension);
        this.location = location;
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object)this).add("location", (Object)this.location).toString();
    }
}

