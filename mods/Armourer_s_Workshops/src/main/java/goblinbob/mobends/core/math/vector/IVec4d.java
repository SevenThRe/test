/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.math.vector;

import goblinbob.mobends.core.math.vector.IVec4dRead;

public interface IVec4d
extends IVec4dRead {
    public void set(double var1, double var3, double var5, double var7);

    public void setX(double var1);

    public void setY(double var1);

    public void setZ(double var1);

    public void setW(double var1);

    public void add(double var1, double var3, double var5, double var7);

    default public void set(IVec4dRead vector) {
        this.set(vector.getX(), vector.getY(), vector.getZ(), vector.getW());
    }

    default public void add(IVec4dRead vector) {
        this.add(vector.getX(), vector.getY(), vector.getZ(), vector.getW());
    }

    default public void scale(double x2, double y2, double z2, double w2) {
        this.set(this.getX() * x2, this.getY() * y2, this.getZ() * z2, this.getW() * w2);
    }

    default public void scale(double a2) {
        this.set(this.getX() * a2, this.getY() * a2, this.getZ() * a2, this.getW() * a2);
    }
}

