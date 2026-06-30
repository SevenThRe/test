/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.math.physics;

import goblinbob.mobends.core.math.vector.IVec3fRead;
import goblinbob.mobends.core.math.vector.Vec3fReadonly;

public class RayHitInfo {
    public final Vec3fReadonly hitPoint;

    public RayHitInfo(IVec3fRead hitPoint) {
        this.hitPoint = new Vec3fReadonly(hitPoint);
    }

    public RayHitInfo(float x2, float y2, float z2) {
        this.hitPoint = new Vec3fReadonly(x2, y2, z2);
    }
}

