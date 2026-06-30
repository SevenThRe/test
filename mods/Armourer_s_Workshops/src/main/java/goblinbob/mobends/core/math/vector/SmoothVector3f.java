/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.util.vector.Vector3f
 */
package goblinbob.mobends.core.math.vector;

import goblinbob.mobends.core.math.vector.Vec3f;
import goblinbob.mobends.core.math.vector.VectorUtils;
import goblinbob.mobends.core.util.EnumAxis;
import org.lwjgl.util.vector.Vector3f;

public class SmoothVector3f {
    public Vec3f start = new Vec3f(0.0f, 0.0f, 0.0f);
    public Vec3f end = new Vec3f(0.0f, 0.0f, 0.0f);
    public Vec3f smoothness = new Vec3f(1.0f, 1.0f, 1.0f);
    public Vec3f completion = new Vec3f(0.0f, 0.0f, 0.0f);
    private Vector3f vector3f;

    public SmoothVector3f() {
    }

    public SmoothVector3f(SmoothVector3f src) {
        this.set(src);
    }

    public void slideTo(float x2, float y2, float z2, float smoothness) {
        if (this.end.x != x2 || this.end.y != y2 || this.end.z != z2) {
            this.start.set(this.getX1(), this.getY1(), this.getZ1());
            this.end.set(x2, y2, z2);
            this.completion.set(0.0f, 0.0f, 0.0f);
            this.smoothness.set(smoothness, smoothness, smoothness);
        }
    }

    public void slideTo(Vec3f orientation, float smoothness) {
        this.slideTo(orientation.x, orientation.y, orientation.z, smoothness);
    }

    public void slideTo(Vec3f orientation) {
        this.slideTo(orientation, 1.0f);
    }

    public void slideToZero(float smoothness) {
        this.slideTo(0.0f, 0.0f, 0.0f, smoothness);
    }

    public void slideToZero() {
        this.slideToZero(1.0f);
    }

    public void slideY(float argY, float argSmooth) {
        if (this.end.y != argY) {
            this.start.y = this.getY1();
            this.end.y = argY;
            this.completion.y = 0.0f;
        }
        this.smoothness.y = argSmooth;
    }

    public void slideZ(float argZ, float argSmooth) {
        if (this.end.z != argZ) {
            this.start.z = this.getZ1();
            this.end.z = argZ;
            this.completion.z = 0.0f;
        }
        this.smoothness.z = argSmooth;
    }

    public void slideY(float y2) {
        this.slideY(y2, 0.6f);
    }

    public void add(float x2, float y2, float z2) {
        this.start.set(this.getX1(), this.getY1(), this.getZ1());
        this.completion.set(0.0f, 0.0f, 0.0f);
        this.end.x += x2;
        this.end.y += y2;
        this.end.z += z2;
    }

    public void setX(float x2) {
        this.start.x = x2;
        this.end.x = x2;
        this.completion.x = 1.0f;
    }

    public void setY(float y2) {
        this.start.y = y2;
        this.end.y = y2;
        this.completion.y = 1.0f;
    }

    public void setZ(float z2) {
        this.start.z = z2;
        this.end.z = z2;
        this.completion.z = 1.0f;
    }

    public void set(float x2, float y2, float z2) {
        this.start.set(x2, y2, z2);
        this.end.set(this.start);
        this.completion.set(1.0f, 1.0f, 1.0f);
    }

    public void set(SmoothVector3f other) {
        this.completion.set(other.completion);
        this.smoothness.set(other.smoothness);
        this.end.set(other.end);
        this.start.set(other.start);
    }

    public void limitDistanceTo(SmoothVector3f other, float maxDistance) {
        Vec3f diff = new Vec3f(this.end.x - other.end.x, this.end.y - other.end.y, this.end.z - other.end.z);
        float sqDist = diff.lengthSq();
        if (sqDist > maxDistance * maxDistance) {
            VectorUtils.normalize(diff);
            this.end.set(other.end.x + diff.x * maxDistance, other.end.y + diff.y * maxDistance, other.end.z + diff.z * maxDistance);
        }
    }

    public void lock(float x2, float y2, float z2) {
        this.vector3f = new Vector3f(x2, y2, z2);
    }

    public void unlock() {
        this.vector3f = null;
    }

    public float getX() {
        if (this.vector3f != null) {
            return this.vector3f.x;
        }
        return this.getX1();
    }

    public float getY() {
        if (this.vector3f != null) {
            return this.vector3f.y;
        }
        return this.getY1();
    }

    public float getZ() {
        if (this.vector3f != null) {
            return this.vector3f.z;
        }
        return this.getZ1();
    }

    public float getX1() {
        return this.start.x + (this.end.x - this.start.x) * this.completion.x;
    }

    public float getY1() {
        return this.start.y + (this.end.y - this.start.y) * this.completion.y;
    }

    public float getZ1() {
        return this.start.z + (this.end.z - this.start.z) * this.completion.z;
    }

    public void update(float ticksPerFrame) {
        this.completion.x += ticksPerFrame * this.smoothness.x;
        this.completion.y += ticksPerFrame * this.smoothness.y;
        this.completion.z += ticksPerFrame * this.smoothness.z;
        this.completion.x = Math.min(this.completion.x, 1.0f);
        this.completion.y = Math.min(this.completion.y, 1.0f);
        this.completion.z = Math.min(this.completion.z, 1.0f);
    }

    public float getNextX(float ticksPerFrame) {
        float c2 = this.completion.x + ticksPerFrame * this.smoothness.x;
        float v2 = this.start.x + (this.end.x - this.start.x) * this.completion.x;
        if (this.completion.x >= 1.0f) {
            v2 = this.end.x;
        }
        return v2;
    }

    public float getNextY(float ticksPerFrame) {
        float c2 = this.completion.y + ticksPerFrame * this.smoothness.y;
        float v2 = this.start.y + (this.end.y - this.start.y) * this.completion.y;
        if (this.completion.y >= 1.0f) {
            v2 = this.end.y;
        }
        return v2;
    }

    public float getNextZ(float ticksPerFrame) {
        float c2 = this.completion.z + ticksPerFrame * this.smoothness.z;
        float v2 = this.start.z + (this.end.z - this.start.z) * this.completion.z;
        if (this.completion.z >= 1.0f) {
            v2 = this.end.z;
        }
        return v2;
    }

    public void finish() {
        this.set(this.end.x, this.end.y, this.end.z);
    }

    public float getEnd(EnumAxis axis) {
        return axis == EnumAxis.X ? this.end.x : (axis == EnumAxis.Y ? this.end.y : this.end.z);
    }
}

