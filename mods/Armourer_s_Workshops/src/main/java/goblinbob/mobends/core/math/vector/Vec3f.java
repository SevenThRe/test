/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.math.vector;

import goblinbob.mobends.core.math.vector.IVec3f;
import goblinbob.mobends.core.math.vector.IVec3fRead;
import goblinbob.mobends.core.math.vector.Vec3fReadonly;

public class Vec3f
implements IVec3f {
    public static final Vec3fReadonly ZERO = new Vec3fReadonly(0.0f, 0.0f, 0.0f);
    public float x;
    public float y;
    public float z;

    public Vec3f(float x2, float y2, float z2) {
        this.x = x2;
        this.y = y2;
        this.z = z2;
    }

    public Vec3f(IVec3fRead other) {
        this.x = other.getX();
        this.y = other.getY();
        this.z = other.getZ();
    }

    public Vec3f() {
        this(0.0f, 0.0f, 0.0f);
    }

    @Override
    public void set(float x2, float y2, float z2) {
        this.x = x2;
        this.y = y2;
        this.z = z2;
    }

    @Override
    public void setX(float x2) {
        this.x = x2;
    }

    @Override
    public void setY(float y2) {
        this.y = y2;
    }

    @Override
    public void setZ(float z2) {
        this.z = z2;
    }

    @Override
    public void add(float x2, float y2, float z2) {
        this.x += x2;
        this.y += y2;
        this.z += z2;
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

    public String toString() {
        return this.x + ", " + this.z;
    }
}

