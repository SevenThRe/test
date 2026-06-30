/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.math.vector;

import goblinbob.mobends.core.math.vector.IVec4f;
import goblinbob.mobends.core.math.vector.IVec4fRead;
import goblinbob.mobends.core.math.vector.Vec4fReadOnly;

public class Vec4f
implements IVec4f {
    public static final Vec4fReadOnly ZERO = new Vec4fReadOnly(0.0f, 0.0f, 0.0f, 0.0f);
    public float x;
    public float y;
    public float z;
    public float w;

    public Vec4f(float x2, float y2, float z2, float w2) {
        this.x = x2;
        this.y = y2;
        this.z = z2;
        this.w = w2;
    }

    public Vec4f(IVec4fRead other) {
        this.x = other.getX();
        this.y = other.getY();
        this.z = other.getZ();
        this.w = other.getW();
    }

    public Vec4f() {
        this(0.0f, 0.0f, 0.0f, 0.0f);
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
    public float getW() {
        return this.w;
    }

    @Override
    public void set(float x2, float y2, float z2, float w2) {
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
    public void setW(float w2) {
        this.w = w2;
    }

    @Override
    public void add(float x2, float y2, float z2, float w2) {
        this.x += x2;
        this.y += y2;
        this.z += z2;
        this.w += w2;
    }
}

