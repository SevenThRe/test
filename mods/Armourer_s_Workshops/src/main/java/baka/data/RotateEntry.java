/*
 * Decompiled with CFR 0.152.
 */
package baka.data;

public class RotateEntry {
    protected float x;
    protected float y;
    protected float z;
    protected float v;

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getZ() {
        return this.z;
    }

    public float getV() {
        return this.v;
    }

    public void setX(float x2) {
        this.x = x2;
    }

    public void setY(float y2) {
        this.y = y2;
    }

    public void setZ(float z2) {
        this.z = z2;
    }

    public void setV(float v2) {
        this.v = v2;
    }

    public boolean equals(Object o2) {
        if (o2 == this) {
            return true;
        }
        if (!(o2 instanceof RotateEntry)) {
            return false;
        }
        RotateEntry other = (RotateEntry)o2;
        if (!other.canEqual(this)) {
            return false;
        }
        if (Float.compare(this.getX(), other.getX()) != 0) {
            return false;
        }
        if (Float.compare(this.getY(), other.getY()) != 0) {
            return false;
        }
        if (Float.compare(this.getZ(), other.getZ()) != 0) {
            return false;
        }
        return Float.compare(this.getV(), other.getV()) == 0;
    }

    protected boolean canEqual(Object other) {
        return other instanceof RotateEntry;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + Float.floatToIntBits(this.getX());
        result = result * 59 + Float.floatToIntBits(this.getY());
        result = result * 59 + Float.floatToIntBits(this.getZ());
        result = result * 59 + Float.floatToIntBits(this.getV());
        return result;
    }

    public String toString() {
        return "RotateEntry(x=" + this.getX() + ", y=" + this.getY() + ", z=" + this.getZ() + ", v=" + this.getV() + ")";
    }

    public RotateEntry(float x2, float y2, float z2, float v2) {
        this.x = x2;
        this.y = y2;
        this.z = z2;
        this.v = v2;
    }
}

