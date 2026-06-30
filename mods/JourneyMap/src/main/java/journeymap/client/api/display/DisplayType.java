/*
 * Decompiled with CFR 0.152.
 */
package journeymap.client.api.display;

import java.util.HashMap;
import journeymap.client.api.display.Displayable;
import journeymap.client.api.display.ImageOverlay;
import journeymap.client.api.display.MarkerOverlay;
import journeymap.client.api.display.PolygonOverlay;
import journeymap.client.api.display.Waypoint;
import journeymap.client.api.display.WaypointGroup;

public enum DisplayType {
    Image(ImageOverlay.class),
    Marker(MarkerOverlay.class),
    Polygon(PolygonOverlay.class),
    Waypoint(Waypoint.class),
    WaypointGroup(WaypointGroup.class);

    private static HashMap<Class<? extends Displayable>, DisplayType> reverseLookup;
    private final Class<? extends Displayable> implClass;

    private DisplayType(Class<? extends Displayable> implClass) {
        this.implClass = implClass;
    }

    public static DisplayType of(Class<? extends Displayable> implClass) {
        DisplayType displayType;
        if (reverseLookup == null) {
            reverseLookup = new HashMap();
            for (DisplayType type : DisplayType.values()) {
                reverseLookup.put(type.getImplClass(), type);
            }
        }
        if ((displayType = reverseLookup.get(implClass)) == null) {
            throw new IllegalArgumentException("Not a valid Displayable implementation: " + implClass);
        }
        return displayType;
    }

    public Class<? extends Displayable> getImplClass() {
        return this.implClass;
    }
}

