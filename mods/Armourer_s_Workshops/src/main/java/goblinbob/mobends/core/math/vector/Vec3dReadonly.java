/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.math.vector;

import goblinbob.mobends.core.math.vector.IVec3dRead;

public class Vec3dReadonly
implements IVec3dRead {
    private final double x;
    private final double y;
    private final double z;

    public Vec3dReadonly(float x2, float y2, float z2) {
        this.x = x2;
        this.y = y2;
        this.z = z2;
    }

    public Vec3dReadonly(IVec3dRead other) {
        this.x = other.getX();
        this.y = other.getY();
        this.z = other.getZ();
    }

    public Vec3dReadonly() {
        this(0.0f, 0.0f, 0.0f);
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public double getZ() {
        return this.z;
    }

    @Override
    public double lengthSq() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }
}

