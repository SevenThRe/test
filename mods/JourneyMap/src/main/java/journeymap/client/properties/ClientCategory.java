/*
 * Decompiled with CFR 0.152.
 */
package journeymap.client.properties;

import java.util.Arrays;
import java.util.List;
import journeymap.client.Constants;
import journeymap.common.properties.Category;

public class ClientCategory {
    private static int order = 1;
    public static final Category MiniMap1 = ClientCategory.create("MiniMap1", "jm.config.category.minimap");
    public static final Category MiniMap2 = ClientCategory.create("MiniMap2", "jm.config.category.minimap2");
    public static final Category FullMap = ClientCategory.create("FullMap", "jm.config.category.fullmap");
    public static final Category WebMap = ClientCategory.create("WebMap", "jm.config.category.webmap");
    public static final Category Waypoint = ClientCategory.create("Waypoint", "jm.config.category.waypoint");
    public static final Category WaypointBeacon = ClientCategory.create("WaypointBeacon", "jm.config.category.waypoint_beacons");
    public static final Category Cartography = ClientCategory.create("Cartography", "jm.config.category.cartography");
    public static final Category Advanced = ClientCategory.create("Advanced", "jm.config.category.advanced");
    public static final List<Category> values = Arrays.asList(Category.Inherit, Category.Hidden, MiniMap1, MiniMap2, FullMap, WebMap, Waypoint, WaypointBeacon, Cartography, Advanced);

    private static Category create(String name, String key) {
        return new Category(name, order++, Constants.getString(key), Constants.getString(key + ".tooltip"));
    }

    public static Category valueOf(String name) {
        for (Category category : values) {
            if (!category.getName().equalsIgnoreCase(name)) continue;
            return category;
        }
        return null;
    }
}

