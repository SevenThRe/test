/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Vector3f
 */
package goblinbob.mobends.core.math;

import javax.vecmath.Vector3f;

public class Quaternion {
    public float x;
    public float y;
    public float z;
    public float w;

    public Quaternion(float x2, float y2, float z2, float w2) {
        this.x = x2;
        this.y = y2;
        this.z = z2;
        this.w = w2;
    }

    public Quaternion(Vector3f pRotationAxis, float pRotationAngle, boolean pDegrees) {
        if (pDegrees) {
            pRotationAngle *= (float)Math.PI / 180;
        }
        float f2 = Quaternion.sin(pRotationAngle / 2.0f);
        this.x = pRotationAxis.getX() * f2;
        this.y = pRotationAxis.getY() * f2;
        this.z = pRotationAxis.getZ() * f2;
        this.w = Quaternion.cos(pRotationAngle / 2.0f);
    }

    private static float sin(float pAngle) {
        return (float)Math.sin(pAngle);
    }

    private static float cos(float pAngle) {
        return (float)Math.cos(pAngle);
    }

    public Quaternion() {
        this(0.0f, 0.0f, 0.0f, 1.0f);
    }

    public float lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
    }

    public float length() {
        return (float)Math.sqrt(this.lengthSquared());
    }

    public void set(float x2, float y2, float z2, float w2) {
        this.x = x2;
        this.y = y2;
        this.z = z2;
        this.w = w2;
    }

    public void set(Quaternion quat) {
        this.x = quat.x;
        this.y = quat.y;
        this.z = quat.z;
        this.w = quat.w;
    }

    public void setIdentity() {
        this.set(0.0f, 0.0f, 0.0f, 1.0f);
    }

    public void normalise() {
        float length = this.length();
        if (length != 0.0f) {
            float inv_l = 1.0f / length;
            this.x *= inv_l;
            this.y *= inv_l;
            this.z *= inv_l;
            this.w *= inv_l;
        }
    }

    public void negate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
    }

    public void setFromAxisAngle(float x2, float y2, float z2, float angle) {
        float n2 = (float)Math.sqrt(x2 * x2 + y2 * y2 + z2 * z2);
        float s2 = (float)(Math.sin(0.5 * (double)angle) / (double)n2);
        this.x = x2 * s2;
        this.y = y2 * s2;
        this.z = z2 * s2;
        this.w = (float)Math.cos(0.5 * (double)angle);
    }

    public void rotate(float x2, float y2, float z2, float angle) {
        float n2 = (float)Math.sqrt(x2 * x2 + y2 * y2 + z2 * z2);
        float s2 = (float)(Math.sin(0.5 * (double)angle) / (double)n2);
        float x22 = x2 * s2;
        float y22 = y2 * s2;
        float z22 = z2 * s2;
        float w2 = (float)Math.cos(0.5 * (double)angle);
        Quaternion.mul(x22, y22, z22, w2, this.x, this.y, this.z, this.w, this);
    }

    public static Quaternion mul(Quaternion left, Quaternion right, Quaternion dest) {
        dest.set(left.x * right.w + left.w * right.x + left.y * right.z - left.z * right.y, left.y * right.w + left.w * right.y + left.z * right.x - left.x * right.z, left.z * right.w + left.w * right.z + left.x * right.y - left.y * right.x, left.w * right.w - left.x * right.x - left.y * right.y - left.z * right.z);
        return dest;
    }

    public static Quaternion mul(float x1, float y1, float z1, float w1, float x2, float y2, float z2, float w2, Quaternion dest) {
        dest.set(x1 * w2 + w1 * x2 + y1 * z2 - z1 * y2, y1 * w2 + w1 * y2 + z1 * x2 - x1 * z2, z1 * w2 + w1 * z2 + x1 * y2 - y1 * x2, w1 * w2 - x1 * x2 - y1 * y2 - z1 * z2);
        return dest;
    }

    public String toString() {
        return "{x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", w=" + this.w + '}';
    }
}

