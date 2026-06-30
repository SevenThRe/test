/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.math.vector;

import goblinbob.mobends.core.math.vector.IVec3fRead;

public interface IVec3f
extends IVec3fRead {
    public void set(float var1, float var2, float var3);

    public void setX(float var1);

    public void setY(float var1);

    public void setZ(float var1);

    public void add(float var1, float var2, float var3);

    default public void set(IVec3fRead vector) {
        this.set(vector.getX(), vector.getY(), vector.getZ());
    }

    default public void add(IVec3fRead vector) {
        this.add(vector.getX(), vector.getY(), vector.getZ());
    }

    default public void scale(float x2, float y2, float z2) {
        this.set(this.getX() * x2, this.getY() * y2, this.getZ() * z2);
    }

    default public void scale(float a2) {
        this.set(this.getX() * a2, this.getY() * a2, this.getZ() * a2);
    }
}

