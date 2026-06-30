/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.math.vector;

import goblinbob.mobends.core.math.vector.IVec4d;
import goblinbob.mobends.core.math.vector.IVec4dRead;

public class Vec4d
implements IVec4d {
    public double x;
    public double y;
    public double z;
    public double w;

    public Vec4d(double x2, double y2, double z2, double w2) {
        this.x = x2;
        this.y = y2;
        this.z = z2;
        this.w = w2;
    }

    public Vec4d(IVec4dRead other) {
        this.x = other.getX();
        this.y = other.getY();
        this.z = other.getZ();
        this.w = other.getW();
    }

    public Vec4d() {
        this(0.0, 0.0, 0.0, 0.0);
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
    public double getW() {
        return this.w;
    }

    @Override
    public void set(double x2, double y2, double z2, double w2) {
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
    public void setW(double w2) {
        this.w = w2;
    }

    @Override
    public void add(double x2, double y2, double z2, double w2) {
        this.x += x2;
        this.y += y2;
        this.z += z2;
        this.w += w2;
    }
}

