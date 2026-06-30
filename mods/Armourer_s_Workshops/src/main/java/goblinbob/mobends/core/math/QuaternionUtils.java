/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.math;

import goblinbob.mobends.core.math.Quaternion;
import goblinbob.mobends.core.math.matrix.IMat4x4d;
import goblinbob.mobends.core.math.vector.IVec3f;
import goblinbob.mobends.core.math.vector.Vec3f;
import goblinbob.mobends.core.math.vector.VectorUtils;
import java.nio.FloatBuffer;

public class QuaternionUtils {
    public static final float PI = (float)Math.PI;

    public static void multiply(IVec3f vector, Quaternion quat, IVec3f dest) {
        Vec3f u2 = new Vec3f(-quat.x, quat.y, quat.z);
        Vec3f crossResult = new Vec3f();
        float s2 = -quat.w;
        float x2 = vector.getX();
        float y2 = vector.getY();
        float z2 = vector.getZ();
        float dotUU = VectorUtils.dot(u2, u2);
        float dotUV = VectorUtils.dot(u2, vector);
        VectorUtils.cross(u2, vector, crossResult);
        dest.set(u2);
        dest.scale(2.0f * dotUV);
        dest.add(x2 * (s2 * s2 - dotUU), y2 * (s2 * s2 - dotUU), z2 * (s2 * s2 - dotUU));
        crossResult.scale(2.0f * s2);
        dest.add(crossResult);
    }

    public static Quaternion rotate(Quaternion quat, float angle, float x2, float y2, float z2, Quaternion dest) {
        dest.set(quat);
        dest.rotate(x2, y2, z2, angle / 180.0f * (float)Math.PI);
        return dest;
    }

    public static Quaternion rotate(Quaternion quat, float angle, float x2, float y2, float z2) {
        quat.rotate(x2, y2, z2, angle / 180.0f * (float)Math.PI);
        return quat;
    }

    public static FloatBuffer quatToGlMatrix(FloatBuffer buffer, Quaternion quaternionIn) {
        buffer.clear();
        float f2 = quaternionIn.x * quaternionIn.x;
        float f1 = quaternionIn.x * quaternionIn.y;
        float f22 = quaternionIn.x * quaternionIn.z;
        float f3 = quaternionIn.x * quaternionIn.w;
        float f4 = quaternionIn.y * quaternionIn.y;
        float f5 = quaternionIn.y * quaternionIn.z;
        float f6 = quaternionIn.y * quaternionIn.w;
        float f7 = quaternionIn.z * quaternionIn.z;
        float f8 = quaternionIn.z * quaternionIn.w;
        buffer.put(1.0f - 2.0f * (f4 + f7));
        buffer.put(2.0f * (f1 + f8));
        buffer.put(2.0f * (f22 - f6));
        buffer.put(0.0f);
        buffer.put(2.0f * (f1 - f8));
        buffer.put(1.0f - 2.0f * (f2 + f7));
        buffer.put(2.0f * (f5 + f3));
        buffer.put(0.0f);
        buffer.put(2.0f * (f22 + f6));
        buffer.put(2.0f * (f5 - f3));
        buffer.put(1.0f - 2.0f * (f2 + f4));
        buffer.put(0.0f);
        buffer.put(0.0f);
        buffer.put(0.0f);
        buffer.put(0.0f);
        buffer.put(1.0f);
        buffer.rewind();
        return buffer;
    }

    public static void quatToMat(Quaternion quaternionIn, IMat4x4d dest) {
        float f2 = quaternionIn.x * quaternionIn.x;
        float f1 = quaternionIn.x * quaternionIn.y;
        float f22 = quaternionIn.x * quaternionIn.z;
        float f3 = quaternionIn.x * quaternionIn.w;
        float f4 = quaternionIn.y * quaternionIn.y;
        float f5 = quaternionIn.y * quaternionIn.z;
        float f6 = quaternionIn.y * quaternionIn.w;
        float f7 = quaternionIn.z * quaternionIn.z;
        float f8 = quaternionIn.z * quaternionIn.w;
        dest.setFields(1.0f - 2.0f * (f4 + f7), 2.0f * (f1 + f8), 2.0f * (f22 - f6), 0.0, 2.0f * (f1 - f8), 1.0f - 2.0f * (f2 + f7), 2.0f * (f5 + f3), 0.0, 2.0f * (f22 + f6), 2.0f * (f5 - f3), 1.0f - 2.0f * (f2 + f4), 0.0, 0.0, 0.0, 0.0, 1.0);
    }
}

