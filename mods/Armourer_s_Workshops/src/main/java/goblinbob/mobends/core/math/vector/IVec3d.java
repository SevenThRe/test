/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.math.vector;

import goblinbob.mobends.core.math.vector.IVec3dRead;
import goblinbob.mobends.core.math.vector.IVec3fRead;

public interface IVec3d
extends IVec3dRead {
    public void set(double var1, double var3, double var5);

    public void setX(double var1);

    public void setY(double var1);

    public void setZ(double var1);

    public void add(double var1, double var3, double var5);

    default public void set(IVec3fRead vector) {
        this.set(vector.getX(), vector.getY(), vector.getZ());
    }

    default public void add(IVec3fRead vector) {
        this.add(vector.getX(), vector.getY(), vector.getZ());
    }

    default public void scale(double x2, double y2, double z2) {
        this.set(this.getX() * x2, this.getY() * y2, this.getZ() * z2);
    }

    default public void scale(double a2) {
        this.set(this.getX() * a2, this.getY() * a2, this.getZ() * a2);
    }
}

