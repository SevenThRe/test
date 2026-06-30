/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.math.physics;

import goblinbob.mobends.core.math.matrix.IMat4x4d;
import goblinbob.mobends.core.math.vector.IVec3fRead;

public interface IOBBox {
    public IVec3fRead getMin();

    public IVec3fRead getMax();

    public IMat4x4d getTransform();
}

