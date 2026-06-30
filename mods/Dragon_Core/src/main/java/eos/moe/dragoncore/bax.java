/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.util.vector.ReadableVector3f
 *  org.lwjgl.util.vector.Vector
 *  org.lwjgl.util.vector.WritableVector3f
 */
package eos.moe.dragoncore;

import java.io.Serializable;
import java.nio.FloatBuffer;
import org.lwjgl.util.vector.ReadableVector3f;
import org.lwjgl.util.vector.Vector;
import org.lwjgl.util.vector.WritableVector3f;

public class bax
extends Vector
implements Serializable,
ReadableVector3f,
WritableVector3f {
    private static final long serialVersionUID = -3627581338777397405L;
    public float x;
    public float y;
    public float z;

    public bax() {
        bax a2;
    }

    public bax(ReadableVector3f a2) {
        bax a3;
        a3.set(a2);
    }

    public bax(float a2, float a3, float a4) {
        bax a5;
        a5.set(a2, a3, a4);
    }

    public void set(float a2, float a3) {
        a.x = a2;
        a.y = a3;
    }

    public void reverseY() {
        bax a2;
        a2.y = -a2.y;
    }

    public void set(float a2, float a3, float a4) {
        a.x = a2;
        a.y = a3;
        a.z = a4;
    }

    public bax set(ReadableVector3f a2) {
        bax a3;
        a3.x = a2.getX();
        a3.y = a2.getY();
        a3.z = a2.getZ();
        return a3;
    }

    public float lengthSquared() {
        bax a2;
        return a2.x * a2.x + a2.y * a2.y + a2.z * a2.z;
    }

    public bax translate(float a2, float a3, float a4) {
        bax a5;
        a5.x += a2;
        a5.y += a3;
        a5.z += a4;
        return a5;
    }

    public static bax add(bax a2, bax a3, bax a4) {
        if (a4 == null) {
            return new bax(a2.x + a3.x, a2.y + a3.y, a2.z + a3.z);
        }
        a4.set(a2.x + a3.x, a2.y + a3.y, a2.z + a3.z);
        return a4;
    }

    public static bax sub(bax a2, bax a3, bax a4) {
        if (a4 == null) {
            return new bax(a2.x - a3.x, a2.y - a3.y, a2.z - a3.z);
        }
        a4.set(a2.x - a3.x, a2.y - a3.y, a2.z - a3.z);
        return a4;
    }

    public static bax cross(bax a2, bax a3, bax a4) {
        if (a4 == null) {
            a4 = new bax();
        }
        a4.set(a2.y * a3.z - a2.z * a3.y, a3.x * a2.z - a3.z * a2.x, a2.x * a3.y - a2.y * a3.x);
        return a4;
    }

    public Vector negate() {
        bax a2;
        a2.x = -a2.x;
        a2.y = -a2.y;
        a2.z = -a2.z;
        return a2;
    }

    public bax negate(bax a2) {
        bax a3;
        if (a2 == null) {
            a2 = new bax();
        }
        a2.x = -a3.x;
        a2.y = -a3.y;
        a2.z = -a3.z;
        return a2;
    }

    public bax normalise(bax a2) {
        bax a3;
        float a4 = a3.length();
        if (a2 == null) {
            a2 = new bax(a3.x / a4, a3.y / a4, a3.z / a4);
        } else {
            a2.set(a3.x / a4, a3.y / a4, a3.z / a4);
        }
        return a2;
    }

    public static float dot(bax a2, bax a3) {
        return a2.x * a3.x + a2.y * a3.y + a2.z * a3.z;
    }

    public static float angle(bax a2, bax a3) {
        float a4 = bax.dot(a2, a3) / (a2.length() * a3.length());
        if (a4 < -1.0f) {
            a4 = -1.0f;
        } else if (a4 > 1.0f) {
            a4 = 1.0f;
        }
        return (float)Math.acos(a4);
    }

    public Vector load(FloatBuffer a2) {
        bax a3;
        a3.x = a2.get();
        a3.y = a2.get();
        a3.z = a2.get();
        return a3;
    }

    public Vector scale(float a2) {
        bax a3;
        a3.x *= a2;
        a3.y *= a2;
        a3.z *= a2;
        return a3;
    }

    public Vector store(FloatBuffer a2) {
        bax a3;
        a2.put(a3.x);
        a2.put(a3.y);
        a2.put(a3.z);
        return a3;
    }

    public String toString() {
        bax a2;
        StringBuilder a3 = new StringBuilder(64);
        a3.append("Vector3f[");
        a3.append(a2.x);
        a3.append(", ");
        a3.append(a2.y);
        a3.append(", ");
        a3.append(a2.z);
        a3.append(']');
        return a3.toString();
    }

    public float getX() {
        bax a2;
        return a2.x;
    }

    public float getY() {
        bax a2;
        return a2.y;
    }

    public void setX(float a2) {
        a.x = a2;
    }

    public void setY(float a2) {
        a.y = a2;
    }

    public void setZ(float a2) {
        a.z = a2;
    }

    public float getZ() {
        bax a2;
        return a2.z;
    }

    public boolean equals(Object a2) {
        bax a3;
        if (a3 == a2) {
            return true;
        }
        if (a2 == null) {
            return false;
        }
        if (a3.getClass() != a2.getClass()) {
            return false;
        }
        bax a4 = (bax)a2;
        return a3.x == a4.x && a3.y == a4.y && a3.z == a4.z;
    }
}

