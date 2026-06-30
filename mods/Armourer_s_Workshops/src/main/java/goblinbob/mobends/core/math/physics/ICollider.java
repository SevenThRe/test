/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.math.physics;

import goblinbob.mobends.core.math.physics.Ray;
import goblinbob.mobends.core.math.physics.RayHitInfo;

public interface ICollider {
    public RayHitInfo intersect(Ray var1);
}

