/*
 * Decompiled with CFR 0.152.
 */
package journeymap.server.properties;

import java.util.Arrays;
import java.util.List;
import journeymap.common.properties.Category;

public class ServerCategory {
    private static int order = 1;
    public static final Category General = ServerCategory.create("General", "General Configuration");
    public static final Category Radar = ServerCategory.create("Radar", "Radar Features");
    public static final Category Cave = ServerCategory.create("Cave", "Cave Mapping");
    public static final Category Surface = ServerCategory.create("Surface", "Surface Mapping");
    public static final Category Topo = ServerCategory.create("Topo", "Topo Mapping");
    public static final List<Category> values = Arrays.asList(Category.Inherit, Category.Hidden, General, Radar, Cave, Surface, Topo);

    public static Category valueOf(String name) {
        for (Category category : values) {
            if (!category.getName().equalsIgnoreCase(name)) continue;
            return category;
        }
        return null;
    }

    private static Category create(String name, String label) {
        return ServerCategory.create(name, label, null);
    }

    private static Category create(String name, String label, String tooltip) {
        return new Category(name, order++, label, tooltip);
    }
}

