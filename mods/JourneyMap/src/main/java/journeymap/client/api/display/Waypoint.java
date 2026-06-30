/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 *  com.google.common.base.Objects
 *  com.google.common.primitives.Ints
 *  com.google.gson.annotations.Since
 *  javax.annotation.Nullable
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 */
package journeymap.client.api.display;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.primitives.Ints;
import com.google.gson.annotations.Since;
import java.util.Arrays;
import javax.annotation.Nullable;
import journeymap.client.api.display.WaypointGroup;
import journeymap.client.api.model.WaypointBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class Waypoint
extends WaypointBase<Waypoint> {
    public static final double VERSION = 1.4;
    protected final transient CachedDimPosition cachedDimPosition = new CachedDimPosition();
    @Since(value=1.4)
    protected final double version = 1.4;
    @Since(value=1.4)
    protected int dim;
    @Since(value=1.4)
    protected BlockPos pos;
    @Since(value=1.4)
    protected WaypointGroup group;
    @Since(value=1.4)
    protected boolean persistent = true;
    @Since(value=1.4)
    protected boolean editable = true;

    public Waypoint(String modId, String name, int dimension, BlockPos position) {
        super(modId, name);
        this.setPosition(dimension, position);
    }

    public Waypoint(String modId, String id, String name, int dimension, BlockPos position) {
        super(modId, id, name);
        this.setPosition(dimension, position);
    }

    public final WaypointGroup getGroup() {
        return this.group;
    }

    public Waypoint setGroup(@Nullable WaypointGroup group) {
        this.group = group;
        return (Waypoint)this.setDirty();
    }

    public final int getDimension() {
        return this.dim;
    }

    public final BlockPos getPosition() {
        return this.pos;
    }

    public BlockPos getPosition(int targetDimension) {
        return this.cachedDimPosition.getPosition(targetDimension);
    }

    private BlockPos getInternalPosition(int targetDimension) {
        if (this.dim != targetDimension) {
            if (this.dim == -1) {
                this.pos = new BlockPos(this.pos.func_177958_n() * 8, this.pos.func_177956_o(), this.pos.func_177952_p() * 8);
            } else if (targetDimension == -1) {
                this.pos = new BlockPos((double)this.pos.func_177958_n() / 8.0, (double)this.pos.func_177956_o(), (double)this.pos.func_177952_p() / 8.0);
            }
        }
        return this.pos;
    }

    public Waypoint setPosition(int dimension, BlockPos position) {
        if (position == null) {
            throw new IllegalArgumentException("position may not be null");
        }
        this.dim = dimension;
        this.pos = position;
        this.cachedDimPosition.reset();
        return (Waypoint)this.setDirty();
    }

    public Vec3d getVec(int dimension) {
        return this.cachedDimPosition.getVec(dimension);
    }

    public Vec3d getCenteredVec(int dimension) {
        return this.cachedDimPosition.getCenteredVec(dimension);
    }

    public final boolean isPersistent() {
        return this.persistent;
    }

    public final Waypoint setPersistent(boolean persistent) {
        this.persistent = persistent;
        if (!persistent) {
            this.dirty = false;
        }
        return (Waypoint)this.setDirty();
    }

    public final boolean isEditable() {
        return this.editable;
    }

    public final Waypoint setEditable(boolean editable) {
        this.editable = editable;
        return (Waypoint)this.setDirty();
    }

    public final boolean isTeleportReady(int targetDimension) {
        BlockPos pos = this.getPosition(targetDimension);
        return pos != null && pos.func_177956_o() >= 0;
    }

    @Override
    protected WaypointGroup getDelegate() {
        return this.getGroup();
    }

    @Override
    protected boolean hasDelegate() {
        return this.group != null;
    }

    @Override
    public int[] getDisplayDimensions() {
        int[] dims = super.getDisplayDimensions();
        if (dims == null) {
            this.setDisplayDimensions(this.dim);
        }
        return this.displayDims;
    }

    @Override
    public int getDisplayOrder() {
        return this.group != null ? this.group.getDisplayOrder() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Waypoint)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Waypoint that = (Waypoint)o;
        return this.isPersistent() == that.isPersistent() && this.isEditable() == that.isEditable() && Objects.equal((Object)this.getDimension(), (Object)that.getDimension()) && Objects.equal((Object)this.getColor(), (Object)that.getColor()) && Objects.equal((Object)this.getBackgroundColor(), (Object)that.getBackgroundColor()) && Objects.equal((Object)this.getName(), (Object)that.getName()) && Objects.equal((Object)this.getPosition(), (Object)that.getPosition()) && Objects.equal((Object)this.getIcon(), (Object)that.getIcon()) && Arrays.equals(this.getDisplayDimensions(), that.getDisplayDimensions());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode((Object[])new Object[]{super.hashCode(), this.getName()});
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object)this).add("name", (Object)this.name).add("dim", this.dim).add("pos", (Object)this.pos).add("group", (Object)this.group).add("icon", (Object)this.icon).add("color", (Object)this.color).add("bgColor", (Object)this.bgColor).add("displayDims", this.displayDims == null ? null : Ints.asList((int[])this.displayDims)).add("editable", this.editable).add("persistent", this.persistent).add("dirty", this.dirty).toString();
    }

    class CachedDimPosition {
        Integer cachedDim;
        BlockPos cachedPos;
        Vec3d cachedVec;
        Vec3d cachedCenteredVec;

        CachedDimPosition() {
        }

        CachedDimPosition reset() {
            this.cachedDim = null;
            this.cachedPos = null;
            this.cachedVec = null;
            this.cachedCenteredVec = null;
            return this;
        }

        private CachedDimPosition ensure(int dimension) {
            if (this.cachedDim != dimension) {
                this.cachedDim = dimension;
                this.cachedPos = Waypoint.this.getInternalPosition(dimension);
                this.cachedVec = new Vec3d((double)this.cachedPos.func_177958_n(), (double)this.cachedPos.func_177956_o(), (double)this.cachedPos.func_177952_p());
                this.cachedCenteredVec = this.cachedVec.func_72441_c(0.5, 0.5, 0.5);
            }
            return this;
        }

        public BlockPos getPosition(int dimension) {
            return this.ensure((int)dimension).cachedPos;
        }

        public Vec3d getVec(int dimension) {
            return this.ensure((int)dimension).cachedVec;
        }

        public Vec3d getCenteredVec(int dimension) {
            return this.ensure((int)dimension).cachedCenteredVec;
        }
    }
}

