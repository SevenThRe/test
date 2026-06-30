/*
 * Decompiled with CFR 0.152.
 */
package baka.data;

public class ScaleEntry {
    protected float x;
    protected float y;
    protected float z;
    protected float v;

    public ScaleEntry(float x2, float y2, float z2, float v2) {
        this.x = x2;
        this.y = y2;
        this.z = z2;
        this.v = v2;
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x2) {
        this.x = x2;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y2) {
        this.y = y2;
    }

    public float getZ() {
        return this.z;
    }

    public void setZ(float z2) {
        this.z = z2;
    }

    public float getV() {
        return this.v;
    }

    public void setV(float v2) {
        this.v = v2;
    }
}

