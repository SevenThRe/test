/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.math;

import goblinbob.mobends.core.math.Quaternion;
import goblinbob.mobends.core.math.QuaternionUtils;
import goblinbob.mobends.core.math.matrix.IMat4x4d;
import goblinbob.mobends.core.math.matrix.Mat4x4d;
import goblinbob.mobends.core.math.matrix.MatrixUtils;
import goblinbob.mobends.core.math.vector.IVec3d;
import goblinbob.mobends.core.math.vector.IVec3dRead;
import goblinbob.mobends.core.math.vector.IVec4d;
import goblinbob.mobends.core.math.vector.IVec4dRead;

public class TransformUtils {
    public static void translate(IMat4x4d src, double x2, double y2, double z2, IMat4x4d dest) {
        double[] srcFields = src.getFields();
        dest.copyFrom(src);
        dest.set(3, 0, srcFields[0] * x2 + srcFields[4] * y2 + srcFields[8] * z2 + srcFields[12]);
        dest.set(3, 1, srcFields[1] * x2 + srcFields[5] * y2 + srcFields[9] * z2 + srcFields[13]);
        dest.set(3, 2, srcFields[2] * x2 + srcFields[6] * y2 + srcFields[10] * z2 + srcFields[14]);
        dest.set(3, 3, srcFields[3] * x2 + srcFields[7] * y2 + srcFields[11] * z2 + srcFields[15]);
    }

    public static void translate(IMat4x4d src, double x2, double y2, double z2) {
        TransformUtils.translate(src, x2, y2, z2, src);
    }

    public static void rotateX(IVec3dRead src, double angle, IVec3d dest) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        dest.setY(src.getY() * cos - src.getZ() * sin);
        dest.setZ(src.getY() * sin + src.getZ() * cos);
    }

    public static void rotateY(IVec3dRead src, double angle, IVec3d dest) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        dest.setX(src.getX() * cos + src.getZ() * sin);
        dest.setZ(-src.getX() * sin + src.getZ() * cos);
    }

    public static void rotateZ(IVec3dRead src, double angle, IVec3d dest) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        dest.setX(src.getX() * cos - src.getY() * sin);
        dest.setY(src.getX() * sin + src.getY() * cos);
    }

    public static void rotateX(IVec3d vec, double angle) {
        TransformUtils.rotateX(vec, angle, vec);
    }

    public static void rotateY(IVec3d vec, double angle) {
        TransformUtils.rotateY(vec, angle, vec);
    }

    public static void rotateZ(IVec3d vec, double angle) {
        TransformUtils.rotateZ(vec, angle, vec);
    }

    public static void rotate(IMat4x4d src, double angle, double axisX, double axisY, double axisZ, IMat4x4d dest) {
        double[] srcFields = src.getFields();
        double a2 = angle;
        double c2 = Math.cos(a2);
        double s2 = Math.sin(a2);
        double tempX = axisX * (1.0 - c2);
        double tempY = axisY * (1.0 - c2);
        double tempZ = axisZ * (1.0 - c2);
        double[] rotate = new double[]{c2 + tempX * axisX, tempX * axisY + s2 * axisZ, tempX * axisZ - s2 * axisY, 0.0, tempY * axisX - s2 * axisZ, c2 + tempY * axisY, tempY * axisZ + s2 * axisX, 0.0, tempZ * axisX + s2 * axisY, tempZ * axisY - s2 * axisX, c2 + tempZ * axisZ, 0.0, 0.0, 0.0, 0.0, 0.0};
        dest.setFields(srcFields[0] * rotate[0] + srcFields[4] * rotate[1] + srcFields[8] * rotate[2], srcFields[1] * rotate[0] + srcFields[5] * rotate[1] + srcFields[9] * rotate[2], srcFields[2] * rotate[0] + srcFields[6] * rotate[1] + srcFields[10] * rotate[2], srcFields[3] * rotate[0] + srcFields[7] * rotate[1] + srcFields[11] * rotate[2], srcFields[0] * rotate[4] + srcFields[4] * rotate[5] + srcFields[8] * rotate[6], srcFields[1] * rotate[4] + srcFields[5] * rotate[5] + srcFields[9] * rotate[6], srcFields[2] * rotate[4] + srcFields[6] * rotate[5] + srcFields[10] * rotate[6], srcFields[3] * rotate[4] + srcFields[7] * rotate[5] + srcFields[11] * rotate[6], srcFields[0] * rotate[8] + srcFields[4] * rotate[9] + srcFields[8] * rotate[10], srcFields[1] * rotate[8] + srcFields[5] * rotate[9] + srcFields[9] * rotate[10], srcFields[2] * rotate[8] + srcFields[6] * rotate[9] + srcFields[10] * rotate[10], srcFields[3] * rotate[8] + srcFields[7] * rotate[9] + srcFields[11] * rotate[10], srcFields[12], srcFields[13], srcFields[14], srcFields[15]);
    }

    public static void rotate(IMat4x4d src, double angle, IVec3dRead axis, IMat4x4d dest) {
        TransformUtils.rotate(src, angle, axis.getX(), axis.getY(), axis.getZ(), dest);
    }

    public static void rotate(double angle, IVec3dRead axis, IMat4x4d dest) {
        TransformUtils.rotate(Mat4x4d.ONE, angle, axis, dest);
    }

    public static void transform(IVec3dRead src, IMat4x4d mat, IVec3d dest) {
        double[] fields = mat.getFields();
        double x2 = src.getX();
        double y2 = src.getY();
        double z2 = src.getZ();
        dest.setX(x2 * fields[0] + y2 * fields[4] + z2 * fields[8] + fields[12]);
        dest.setY(x2 * fields[1] + y2 * fields[5] + z2 * fields[9] + fields[13]);
        dest.setZ(x2 * fields[2] + y2 * fields[6] + z2 * fields[10] + fields[14]);
    }

    public static void transform(IVec4dRead src, IMat4x4d mat, IVec4d dest) {
        double[] fields = mat.getFields();
        double x2 = src.getX();
        double y2 = src.getY();
        double z2 = src.getZ();
        double w2 = src.getW();
        dest.setX(x2 * fields[0] + y2 * fields[4] + z2 * fields[8] + w2 * fields[12]);
        dest.setY(x2 * fields[1] + y2 * fields[5] + z2 * fields[9] + w2 * fields[13]);
        dest.setZ(x2 * fields[2] + y2 * fields[6] + z2 * fields[10] + w2 * fields[14]);
        dest.setW(x2 * fields[3] + y2 * fields[7] + z2 * fields[11] + w2 * fields[15]);
    }

    public static void rotate(IMat4x4d src, Quaternion quat, IMat4x4d dest) {
        Mat4x4d rotation = new Mat4x4d(Mat4x4d.IDENTITY);
        QuaternionUtils.quatToMat(quat, rotation);
        MatrixUtils.multiply(src, rotation, dest);
    }

    public static void rotate(IMat4x4d src, Quaternion quat) {
        TransformUtils.rotate(src, quat, src);
    }

    public static void scale(IMat4x4d src, double x2, double y2, double z2, IMat4x4d dest) {
        Mat4x4d scaleMat = new Mat4x4d(new double[]{x2, 0.0, 0.0, 0.0, 0.0, y2, 0.0, 0.0, 0.0, 0.0, z2, 0.0, 0.0, 0.0, 0.0, 1.0});
        MatrixUtils.multiply(src, scaleMat, dest);
    }

    public static void scale(IMat4x4d src, IVec3dRead scale, IMat4x4d dest) {
        TransformUtils.scale(src, scale.getX(), scale.getY(), scale.getZ(), dest);
    }

    public static void scale(IMat4x4d src, double x2, double y2, double z2) {
        TransformUtils.scale(src, x2, y2, z2, src);
    }
}

