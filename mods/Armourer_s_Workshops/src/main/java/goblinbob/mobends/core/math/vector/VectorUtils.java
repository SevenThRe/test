/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.math.vector;

import goblinbob.mobends.core.math.vector.IVec3d;
import goblinbob.mobends.core.math.vector.IVec3dRead;
import goblinbob.mobends.core.math.vector.IVec3f;
import goblinbob.mobends.core.math.vector.IVec3fRead;
import goblinbob.mobends.core.math.vector.Vec3d;
import goblinbob.mobends.core.math.vector.Vec3f;

public class VectorUtils {
    public static void normalize(IVec3fRead v2, IVec3f dest) throws IllegalArgumentException {
        float length = v2.length();
        if (length == 0.0f) {
            throw new IllegalArgumentException("A zero vector cannot be normalized.");
        }
        dest.set(v2.getX() / length, v2.getY() / length, v2.getZ() / length);
    }

    public static void normalize(IVec3dRead v2, IVec3d dest) throws IllegalArgumentException {
        double length = v2.length();
        if (length == 0.0) {
            throw new IllegalArgumentException("A zero vector cannot be normalized.");
        }
        dest.set(v2.getX() / length, v2.getY() / length, v2.getZ() / length);
    }

    public static void normalize(IVec3f vec) throws IllegalArgumentException {
        VectorUtils.normalize(vec, vec);
    }

    public static void normalize(IVec3d vec) throws IllegalArgumentException {
        VectorUtils.normalize(vec, vec);
    }

    public static Vec3f getNormalized(IVec3fRead src) throws IllegalArgumentException {
        Vec3f vec = new Vec3f();
        VectorUtils.normalize(src, vec);
        return vec;
    }

    public static Vec3d getNormalized(IVec3dRead src) throws IllegalArgumentException {
        Vec3d vec = new Vec3d();
        VectorUtils.normalize(src, vec);
        return vec;
    }

    public static Vec3f getScaled(IVec3fRead vec, float a2) {
        return new Vec3f(vec.getX() * a2, vec.getY() * a2, vec.getZ() * a2);
    }

    public static Vec3d getScaled(IVec3dRead vec, double a2) {
        return new Vec3d(vec.getX() * a2, vec.getY() * a2, vec.getZ() * a2);
    }

    public static float dot(IVec3fRead left, IVec3fRead right) {
        return left.getX() * right.getX() + left.getY() * right.getY() + left.getZ() * right.getZ();
    }

    public static double dot(IVec3dRead left, IVec3dRead right) {
        return left.getX() * right.getX() + left.getY() * right.getY() + left.getZ() * right.getZ();
    }

    public static IVec3f multiply(IVec3fRead left, IVec3fRead right, IVec3f dest) {
        dest.setX(left.getX() * right.getX());
        dest.setY(left.getY() * right.getY());
        dest.setZ(left.getZ() * right.getZ());
        return dest;
    }

    public static IVec3fRead cross(IVec3fRead left, IVec3fRead right, IVec3f dest) {
        dest.set(left.getY() * right.getZ() - left.getZ() * right.getY(), right.getX() * left.getZ() - right.getZ() * left.getX(), left.getX() * right.getY() - left.getY() * right.getX());
        return dest;
    }

    public static IVec3fRead cross(float lx, float ly, float lz, float rx, float ry, float rz, IVec3f dest) {
        dest.set(ly * rz - lz * ry, rx * lz - rz * lx, lx * ry - ly * rx);
        return dest;
    }

    public static float distanceSq(IVec3fRead left, IVec3fRead right) {
        float dx = left.getX() - right.getX();
        float dy = left.getY() - right.getY();
        float dz = left.getZ() - right.getZ();
        return dx * dx + dy * dy + dz * dz;
    }

    public static float distance(IVec3fRead left, IVec3fRead right) {
        return (float)Math.sqrt(VectorUtils.distanceSq(left, right));
    }

    public static void subtract(IVec3fRead a2, IVec3fRead b2, IVec3f dest) {
        dest.set(a2.getX() - b2.getX(), a2.getY() - b2.getY(), a2.getZ() - b2.getZ());
    }

    public static void subtract(IVec3dRead a2, IVec3dRead b2, IVec3d dest) {
        dest.set(a2.getX() - b2.getX(), a2.getY() - b2.getY(), a2.getZ() - b2.getZ());
    }

    public static Vec3f subtract(IVec3fRead a2, IVec3fRead b2) {
        Vec3f vec = new Vec3f();
        VectorUtils.subtract(a2, b2, vec);
        return vec;
    }

    public static Vec3d subtract(IVec3dRead a2, IVec3dRead b2) {
        Vec3d vec = new Vec3d();
        VectorUtils.subtract(a2, b2, vec);
        return vec;
    }

    public static void slerp(IVec3dRead a2, IVec3dRead b2, double t2, IVec3d dest) {
        double cosAlpha = VectorUtils.dot(a2, b2);
        double alpha = (float)Math.acos(cosAlpha);
        double sinAlpha = Math.sin(alpha);
        double t1 = Math.sin((1.0 - t2) * alpha) / sinAlpha;
        double t22 = Math.sin(t2 * alpha) / sinAlpha;
        dest.set(a2.getX() * t1 + b2.getX() * t22, a2.getY() * t1 + b2.getY() * t22, a2.getZ() * t1 + b2.getZ() * t22);
    }

    public static Vec3d slerp(IVec3dRead a2, IVec3dRead b2, double t2) {
        Vec3d vec = new Vec3d();
        VectorUtils.slerp(a2, b2, t2, vec);
        return vec;
    }
}

