/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.projectile.EntityArrow
 */
package goblinbob.mobends.standard.client.renderer.entity;

import goblinbob.mobends.standard.client.renderer.entity.ArrowTrail;
import java.util.HashMap;
import net.minecraft.entity.projectile.EntityArrow;

public class ArrowTrailManager {
    private static HashMap<EntityArrow, ArrowTrail> trailMap = new HashMap();
    public static long time = System.nanoTime() / 1000L;
    public static long lastTime = System.nanoTime() / 1000L;

    public static ArrowTrail getOrMake(EntityArrow arrow) {
        ArrowTrail trail;
        if (!trailMap.containsKey(arrow)) {
            trail = new ArrowTrail(arrow);
            trailMap.put(arrow, trail);
        } else {
            trail = trailMap.get(arrow);
        }
        return trail;
    }

    public static void renderTrail(EntityArrow entity, double x2, double y2, double z2, float partialTicks) {
        ArrowTrailManager.getOrMake(entity).render(x2, y2, z2, partialTicks);
    }

    public static void cleanup() {
        trailMap.entrySet().removeIf(e2 -> ((ArrowTrail)e2.getValue()).shouldBeRemoved());
    }

    public static void onRenderTick() {
        for (ArrowTrail trail : trailMap.values()) {
            trail.onRenderTick();
        }
        ArrowTrailManager.cleanup();
    }
}

