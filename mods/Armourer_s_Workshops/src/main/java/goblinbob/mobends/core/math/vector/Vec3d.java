/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.math.vector;

import goblinbob.mobends.core.math.vector.IVec3d;
import goblinbob.mobends.core.math.vector.IVec3fRead;
import goblinbob.mobends.core.math.vector.Vec3dReadonly;

public class Vec3d
implements IVec3d {
    public static final Vec3dReadonly ZERO = new Vec3dReadonly(0.0f, 0.0f, 0.0f);
    public static final Vec3dReadonly ONE = new Vec3dReadonly(1.0f, 1.0f, 1.0f);
    public double x;
    public double y;
    public double z;

    public Vec3d(double x2, double y2, double z2) {
        this.x = x2;
        this.y = y2;
        this.z = z2;
    }

    public Vec3d(IVec3fRead other) {
        this.x = other.getX();
        this.y = other.getY();
        this.z = other.getZ();
    }

    public Vec3d() {
        this(0.0, 0.0, 0.0);
    }

    @Override
    public void set(double x2, double y2, double z2) {
        this.x = x2;
        this.y = y2;
        this.z = z2;
    }

    @Override
    public void setX(double x2) {
        this.x = x2;
    }

    @Override
    public void setY(double y2) {
        this.y = y2;
    }

    @Override
    public void setZ(double z2) {
        this.z = z2;
    }

    @Override
    public void add(double x2, double y2, double z2) {
        this.x += x2;
        this.y += y2;
        this.z += z2;
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

    public boolean isNotEmpty() {
        return this.x != 0.0 || this.y != 0.0 || this.z != 0.0;
    }

    public String toString() {
        return "Vec3d{x=" + this.x + ", y=" + this.y + ", z=" + this.z + '}';
    }
}

