/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.annotations.Since
 *  javax.annotation.ParametersAreNonnullByDefault
 */
package journeymap.client.model;

import com.google.common.base.MoreObjects;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Since;
import javax.annotation.ParametersAreNonnullByDefault;
import journeymap.client.Constants;
import journeymap.client.cartography.color.RGB;
import journeymap.client.waypoint.WaypointGroupStore;

@ParametersAreNonnullByDefault
public class WaypointGroup
implements Comparable<WaypointGroup> {
    public static final WaypointGroup DEFAULT = new WaypointGroup("journeymap", Constants.getString("jm.config.category.waypoint")).setEnable(true);
    public static final double VERSION = 5.2;
    public static final Gson GSON = new GsonBuilder().setVersion(5.2).create();
    @Since(value=5.2)
    protected String name;
    @Since(value=5.2)
    protected String origin;
    @Since(value=5.2)
    protected String icon;
    @Since(value=5.2)
    protected String color;
    @Since(value=5.2)
    protected boolean enable;
    @Since(value=5.2)
    protected int order;
    protected transient boolean dirty;
    protected transient Integer colorInt;

    public WaypointGroup(String origin, String name) {
        this.setOrigin(origin).setName(name);
    }

    public String getName() {
        return this.name;
    }

    public WaypointGroup setName(String name) {
        this.name = name;
        return this.setDirty();
    }

    public String getOrigin() {
        return this.origin;
    }

    public WaypointGroup setOrigin(String origin) {
        this.origin = origin;
        return this.setDirty();
    }

    public String getIcon() {
        return this.icon;
    }

    public WaypointGroup setIcon(String icon) {
        this.icon = icon;
        return this.setDirty();
    }

    public int getColor() {
        if (this.colorInt == null) {
            if (this.color == null) {
                this.color = RGB.toHexString(RGB.randomColor());
            }
            this.colorInt = RGB.hexToInt(this.color);
        }
        return this.colorInt;
    }

    public WaypointGroup setColor(String color) {
        this.colorInt = RGB.hexToInt(color);
        this.color = RGB.toHexString(this.colorInt);
        return this.setDirty();
    }

    public WaypointGroup setColor(int color) {
        this.color = RGB.toHexString(color);
        this.colorInt = color;
        return this.setDirty();
    }

    public boolean isEnable() {
        return this.enable;
    }

    public WaypointGroup setEnable(boolean enable) {
        this.enable = enable;
        return this.setDirty();
    }

    public boolean isDirty() {
        return this.dirty;
    }

    public WaypointGroup setDirty() {
        return this.setDirty(true);
    }

    public WaypointGroup setDirty(boolean dirty) {
        this.dirty = dirty;
        return this;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        WaypointGroup group = (WaypointGroup)o;
        if (!this.name.equals(group.name)) {
            return false;
        }
        return this.origin.equals(group.origin);
    }

    public int hashCode() {
        int result = this.name.hashCode();
        result = 31 * result + this.origin.hashCode();
        return result;
    }

    @Override
    public int compareTo(WaypointGroup o) {
        int result = Integer.compare(this.order, o.order);
        if (result == 0) {
            result = this.name.compareTo(o.name);
        }
        return result;
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object)this).add("name", (Object)this.name).add("origin", (Object)this.origin).toString();
    }

    public String getKey() {
        return String.format("%s:%s", this.origin, this.name);
    }

    public static WaypointGroup getNamedGroup(String origin, String groupName) {
        return WaypointGroupStore.INSTANCE.get(origin, groupName);
    }
}

