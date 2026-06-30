/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.math.vector;

import goblinbob.mobends.core.math.vector.IVec4fRead;

public interface IVec4f
extends IVec4fRead {
    public void set(float var1, float var2, float var3, float var4);

    public void setX(float var1);

    public void setY(float var1);

    public void setZ(float var1);

    public void setW(float var1);

    public void add(float var1, float var2, float var3, float var4);

    default public void set(IVec4fRead vector) {
        this.set(vector.getX(), vector.getY(), vector.getZ(), vector.getW());
    }

    default public void add(IVec4fRead vector) {
        this.add(vector.getX(), vector.getY(), vector.getZ(), vector.getW());
    }

    default public void scale(float x2, float y2, float z2, float w2) {
        this.set(this.getX() * x2, this.getY() * y2, this.getZ() * z2, this.getW() * w2);
    }

    default public void scale(float a2) {
        this.set(this.getX() * a2, this.getY() * a2, this.getZ() * a2, this.getW() * a2);
    }
}

