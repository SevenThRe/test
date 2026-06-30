/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package goblinbob.mobends.core.bender;

import java.util.HashSet;
import java.util.Set;
import net.minecraft.entity.Entity;

public class PreviewHelper {
    private static final Set<Entity> previewEntities = new HashSet<Entity>();

    public static void registerPreviewEntity(Entity entity) {
        previewEntities.add(entity);
    }

    public static boolean isPreviewEntity(Entity entity) {
        return previewEntities.contains(entity);
    }
}

