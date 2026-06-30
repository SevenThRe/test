/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Objects
 *  com.google.gson.annotations.Since
 *  javax.annotation.ParametersAreNonnullByDefault
 */
package journeymap.client.api.display;

import com.google.common.base.Objects;
import com.google.gson.annotations.Since;
import java.util.UUID;
import javax.annotation.ParametersAreNonnullByDefault;
import journeymap.client.api.display.IWaypointDisplay;
import journeymap.client.api.model.WaypointBase;

@ParametersAreNonnullByDefault
public class WaypointGroup
extends WaypointBase<WaypointGroup> {
    public static final double VERSION = 1.4;
    @Since(value=1.4)
    protected final double version = 1.4;
    @Since(value=1.4)
    protected int order;
    protected transient IWaypointDisplay defaultDisplay;

    public WaypointGroup(String modId, String name) {
        this(modId, UUID.randomUUID().toString(), name);
    }

    public WaypointGroup(String modId, String id, String name) {
        super(modId, id, name);
    }

    public WaypointGroup setDefaultDisplay(IWaypointDisplay defaultDisplay) {
        if (defaultDisplay == this) {
            throw new IllegalArgumentException("WaypointGroup may not use itself as a defaultDisplay");
        }
        this.defaultDisplay = defaultDisplay;
        return this;
    }

    @Override
    protected IWaypointDisplay getDelegate() {
        return this.defaultDisplay;
    }

    @Override
    protected boolean hasDelegate() {
        return this.defaultDisplay != null;
    }

    @Override
    public int getDisplayOrder() {
        return this.order;
    }

    public WaypointGroup setDisplayOrder(int order) {
        this.order = order;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WaypointGroup)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        WaypointGroup that = (WaypointGroup)o;
        return this.order == that.order && Double.compare(that.version, 1.4) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode((Object[])new Object[]{super.hashCode(), 1.4});
    }
}

