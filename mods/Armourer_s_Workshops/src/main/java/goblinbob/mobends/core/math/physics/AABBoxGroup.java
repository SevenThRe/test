/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.math.physics;

import goblinbob.mobends.core.math.physics.AABBox;
import goblinbob.mobends.core.math.physics.IAABBox;
import goblinbob.mobends.core.math.physics.ICollider;
import goblinbob.mobends.core.math.physics.Physics;
import goblinbob.mobends.core.math.physics.Ray;
import goblinbob.mobends.core.math.physics.RayHitInfo;

public class AABBoxGroup
implements ICollider {
    private AABBox[] boxes;

    public AABBoxGroup(IAABBox[] boxes) {
        this.boxes = new AABBox[boxes.length];
        for (int i2 = 0; i2 < boxes.length; ++i2) {
            this.boxes[i2] = new AABBox(boxes[i2]);
        }
    }

    @Override
    public RayHitInfo intersect(Ray ray) {
        for (AABBox box : this.boxes) {
            RayHitInfo info = Physics.intersect(ray, box);
            if (info == null) continue;
            return info;
        }
        return null;
    }
}

