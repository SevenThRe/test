/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.math.vector;

import goblinbob.mobends.core.math.vector.IVec4fRead;

public class Vec4fReadOnly
implements IVec4fRead {
    private final float x;
    private final float y;
    private final float z;
    private final float w;

    public Vec4fReadOnly(float x2, float y2, float z2, float w2) {
        this.x = x2;
        this.y = y2;
        this.z = z2;
        this.w = w2;
    }

    public Vec4fReadOnly(IVec4fRead other) {
        this.x = other.getX();
        this.y = other.getY();
        this.z = other.getZ();
        this.w = other.getW();
    }

    public Vec4fReadOnly() {
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
}

