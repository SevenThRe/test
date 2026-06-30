/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.math.vector;

import goblinbob.mobends.core.math.vector.IVec3fRead;

public class Vec3fReadonly
implements IVec3fRead {
    private final float x;
    private final float y;
    private final float z;

    public Vec3fReadonly(float x2, float y2, float z2) {
        this.x = x2;
        this.y = y2;
        this.z = z2;
    }

    public Vec3fReadonly(IVec3fRead other) {
        this.x = other.getX();
        this.y = other.getY();
        this.z = other.getZ();
    }

    public Vec3fReadonly() {
        this(0.0f, 0.0f, 0.0f);
    }

    @Override
    public float getX() {
        return this.x;
    }

    @Override
    public float getY() {
        return this.y;
    }

    @Override
    public float getZ() {
        return this.z;
    }

    @Override
    public float lengthSq() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }
}

