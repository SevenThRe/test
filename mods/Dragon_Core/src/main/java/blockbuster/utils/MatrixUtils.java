/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  javax.vecmath.Matrix3f
 *  javax.vecmath.Matrix4f
 *  javax.vecmath.Quat4d
 *  javax.vecmath.SingularMatrixException
 *  javax.vecmath.Tuple3f
 *  javax.vecmath.Vector3f
 *  javax.vecmath.Vector4f
 *  net.minecraft.util.math.MathHelper
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.GL11
 */
package blockbuster.utils;

import java.nio.FloatBuffer;
import javax.annotation.Nullable;
import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4d;
import javax.vecmath.SingularMatrixException;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class MatrixUtils {
    public static final FloatBuffer buffer = BufferUtils.createFloatBuffer((int)16);
    public static final float[] floats = new float[16];
    public static Matrix4f matrix;

    public static Matrix4f readModelView(Matrix4f matrix4f) {
        buffer.clear();
        GL11.glGetFloat((int)2982, (FloatBuffer)buffer);
        buffer.get(floats);
        matrix4f.set(floats);
        matrix4f.transpose();
        return matrix4f;
    }

    public static Matrix4f readModelView() {
        return MatrixUtils.readModelView(new Matrix4f());
    }

    public static void loadModelView(Matrix4f matrix4f) {
        MatrixUtils.matrixToFloat(floats, matrix4f);
        buffer.clear();
        buffer.put(floats);
        buffer.rewind();
        GL11.glLoadMatrix((FloatBuffer)buffer);
    }

    public static void matrixToFloat(float[] floats, Matrix4f matrix4f) {
        floats[0] = matrix4f.m00;
        floats[1] = matrix4f.m01;
        floats[2] = matrix4f.m02;
        floats[3] = matrix4f.m03;
        floats[4] = matrix4f.m10;
        floats[5] = matrix4f.m11;
        floats[6] = matrix4f.m12;
        floats[7] = matrix4f.m13;
        floats[8] = matrix4f.m20;
        floats[9] = matrix4f.m21;
        floats[10] = matrix4f.m22;
        floats[11] = matrix4f.m23;
        floats[12] = matrix4f.m30;
        floats[13] = matrix4f.m31;
        floats[14] = matrix4f.m32;
        floats[15] = matrix4f.m33;
    }

    public static boolean captureMatrix() {
        if (matrix == null) {
            matrix = MatrixUtils.readModelView(new Matrix4f());
            return true;
        }
        return false;
    }

    public static void releaseMatrix() {
        matrix = null;
    }

    public static Quat4d matrixToQuaternion(Matrix3f matrix) {
        double tr2 = matrix.m00 + matrix.m11 + matrix.m22;
        double qw2 = 0.0;
        double qx2 = 0.0;
        double qy2 = 0.0;
        double qz2 = 0.0;
        if (tr2 > 0.0) {
            double S = Math.sqrt(tr2 + 1.0) * 2.0;
            qw2 = 0.25 * S;
            qx2 = (double)(matrix.m21 - matrix.m12) / S;
            qy2 = (double)(matrix.m02 - matrix.m20) / S;
            qz2 = (double)(matrix.m10 - matrix.m01) / S;
        } else if (matrix.m00 > matrix.m11 & matrix.m00 > matrix.m22) {
            double S = Math.sqrt(1.0 + (double)matrix.m00 - (double)matrix.m11 - (double)matrix.m22) * 2.0;
            qw2 = (double)(matrix.m21 - matrix.m12) / S;
            qx2 = 0.25 * S;
            qy2 = (double)(matrix.m01 + matrix.m10) / S;
            qz2 = (double)(matrix.m02 + matrix.m20) / S;
        } else if (matrix.m11 > matrix.m22) {
            double S = Math.sqrt(1.0 + (double)matrix.m11 - (double)matrix.m00 - (double)matrix.m22) * 2.0;
            qw2 = (double)(matrix.m02 - matrix.m20) / S;
            qx2 = (double)(matrix.m01 + matrix.m10) / S;
            qy2 = 0.25 * S;
            qz2 = (double)(matrix.m12 + matrix.m21) / S;
        } else {
            double S = Math.sqrt(1.0 + (double)matrix.m22 - (double)matrix.m00 - (double)matrix.m11) * 2.0;
            qw2 = (double)(matrix.m10 - matrix.m01) / S;
            qx2 = (double)(matrix.m02 + matrix.m20) / S;
            qy2 = (double)(matrix.m12 + matrix.m21) / S;
            qz2 = 0.25 * S;
        }
        return new Quat4d(qw2, qx2, qy2, qz2);
    }

    public static Vector3f getAngularVelocity(Matrix3f rotation) {
        Matrix3f step = new Matrix3f(rotation);
        Matrix3f angularVelocity = new Matrix3f();
        Matrix3f i2 = new Matrix3f();
        i2.setIdentity();
        angularVelocity.setIdentity();
        angularVelocity.mul(2.0f);
        step.add(i2);
        step.invert();
        step.mul(4.0f);
        angularVelocity.sub(step);
        Vector3f angularV = new Vector3f(angularVelocity.m21, -angularVelocity.m20, angularVelocity.m10);
        return angularV;
    }

    public static Matrix3f getZYXrotationMatrix(float x2, float y2, float z2) {
        Matrix3f rotation = new Matrix3f();
        Matrix3f rot = new Matrix3f();
        rotation.setIdentity();
        rot.rotZ(z2);
        rotation.mul(rot);
        rot.rotY(y2);
        rotation.mul(rot);
        rot.rotX(x2);
        rotation.mul(rot);
        return rotation;
    }

    public static Matrix3f getXYZrotationMatrix(float x2, float y2, float z2) {
        Matrix3f rotation = new Matrix3f();
        Matrix3f rot = new Matrix3f();
        rotation.setIdentity();
        rot.rotX(x2);
        rotation.mul(rot);
        rot.rotY(y2);
        rotation.mul(rot);
        rot.rotZ(z2);
        rotation.mul(rot);
        return rotation;
    }

    public static Transformation extractTransformations(@Nullable Matrix4f cameraMatrix, Matrix4f modelView) {
        return MatrixUtils.extractTransformations(cameraMatrix, modelView, MatrixMajor.ROW);
    }

    public static Transformation extractTransformations(@Nullable Matrix4f cameraMatrix, Matrix4f modelView, MatrixMajor major) {
        Matrix4f parent = new Matrix4f(modelView);
        if (cameraMatrix != null) {
            parent.set(cameraMatrix);
            try {
                parent.invert();
            }
            catch (SingularMatrixException e2) {
                Transformation transformation = new Transformation();
                transformation.creationException = (Exception)((Object)e2);
                return transformation;
            }
            parent.mul(modelView);
        }
        Matrix4f translation = new Matrix4f();
        Matrix4f scale = new Matrix4f();
        Matrix4f rotation = new Matrix4f();
        translation.setIdentity();
        rotation.setIdentity();
        scale.setIdentity();
        translation.m03 = parent.m03;
        translation.m13 = parent.m13;
        translation.m23 = parent.m23;
        Vector4f ax2 = new Vector4f(parent.m00, parent.m01, parent.m02, 0.0f);
        Vector4f ay2 = new Vector4f(parent.m10, parent.m11, parent.m12, 0.0f);
        Vector4f az2 = new Vector4f(parent.m20, parent.m21, parent.m22, 0.0f);
        if (major == MatrixMajor.COLUMN) {
            ax2 = new Vector4f(parent.m00, parent.m10, parent.m20, 0.0f);
            ay2 = new Vector4f(parent.m01, parent.m11, parent.m21, 0.0f);
            az2 = new Vector4f(parent.m02, parent.m12, parent.m22, 0.0f);
        }
        ax2.normalize();
        ay2.normalize();
        az2.normalize();
        rotation.setRow(0, ax2);
        rotation.setRow(1, ay2);
        rotation.setRow(2, az2);
        if (major == MatrixMajor.COLUMN) {
            rotation.transpose();
        }
        scale.m00 = (float)Math.sqrt(parent.m00 * parent.m00 + parent.m01 * parent.m01 + parent.m02 * parent.m02);
        scale.m11 = (float)Math.sqrt(parent.m10 * parent.m10 + parent.m11 * parent.m11 + parent.m12 * parent.m12);
        scale.m22 = (float)Math.sqrt(parent.m20 * parent.m20 + parent.m21 * parent.m21 + parent.m22 * parent.m22);
        if (major == MatrixMajor.COLUMN) {
            scale.m00 = (float)Math.sqrt(parent.m00 * parent.m00 + parent.m10 * parent.m10 + parent.m20 * parent.m20);
            scale.m11 = (float)Math.sqrt(parent.m01 * parent.m01 + parent.m11 * parent.m11 + parent.m21 * parent.m21);
            scale.m22 = (float)Math.sqrt(parent.m02 * parent.m02 + parent.m12 * parent.m12 + parent.m22 * parent.m22);
        }
        return new Transformation(translation, rotation, scale);
    }

    public static enum MatrixMajor {
        ROW,
        COLUMN;

    }

    public static class Transformation {
        public Matrix4f translation = new Matrix4f();
        public Matrix4f rotation = new Matrix4f();
        public Matrix4f scale = new Matrix4f();
        private Exception creationException = null;

        public Transformation(Matrix4f translation, Matrix4f rotation, Matrix4f scale) {
            this.translation.set(translation);
            this.rotation.set(rotation);
            this.scale.set(scale);
        }

        public Transformation() {
            this.translation.setIdentity();
            this.rotation.setIdentity();
            this.scale.setIdentity();
        }

        public Matrix3f getScale3f() {
            Matrix3f scale3f = new Matrix3f();
            scale3f.setIdentity();
            scale3f.m00 = this.scale.m00;
            scale3f.m11 = this.scale.m11;
            scale3f.m22 = this.scale.m22;
            return scale3f;
        }

        public Vector3f getTranslation3f() {
            Vector3f translation3f = new Vector3f();
            translation3f.set(this.translation.m03, this.translation.m13, this.translation.m23);
            return translation3f;
        }

        public Matrix3f getRotation3f() {
            Matrix3f rotation3f = new Matrix3f();
            rotation3f.setIdentity();
            rotation3f.setRow(0, this.rotation.m00, this.rotation.m01, this.rotation.m02);
            rotation3f.setRow(1, this.rotation.m10, this.rotation.m11, this.rotation.m12);
            rotation3f.setRow(2, this.rotation.m20, this.rotation.m21, this.rotation.m22);
            return rotation3f;
        }

        public Exception getCreationException() {
            return this.creationException;
        }

        public Vector3f getRotation(RotationOrder order) {
            return this.getRotation(order, null);
        }

        public Vector3f getRotation(RotationOrder order, Vector3f ref) {
            return this.getRotation(order, ref, 0);
        }

        public Vector3f getRotation(RotationOrder order, int invAxis) {
            return this.getRotation(order, null, invAxis);
        }

        public Vector3f getRotation(RotationOrder order, Vector3f ref, int invAxis) {
            Float angle;
            Matrix3f mat = this.getRotation3f();
            float[] rotation = new float[3];
            float[] refFloats = null;
            if (ref != null) {
                refFloats = new float[3];
                ref.get(refFloats);
            }
            Vector3f x2 = new Vector3f(mat.m00, mat.m10, mat.m20);
            Vector3f y2 = new Vector3f(mat.m01, mat.m11, mat.m21);
            Vector3f z2 = new Vector3f(mat.m02, mat.m12, mat.m22);
            Vector3f crossY = new Vector3f();
            Vector3f originalY = new Vector3f();
            originalY.normalize(y2);
            crossY.cross(z2, x2);
            crossY.normalize();
            if (crossY.dot(originalY) < 0.0f) {
                mat.mul(Transformation.getInvertAxisMatrix(invAxis));
            }
            if ((angle = order.doTest(order.thirdIndex, mat)) != null) {
                if (refFloats != null) {
                    angle = Float.valueOf(refFloats[order.thirdIndex] + MathHelper.wrapDegrees((float)(2.0f * (angle.floatValue() - refFloats[order.thirdIndex]))) / 2.0f);
                }
                rotation[order.thirdIndex] = angle.floatValue();
                mat.mul(Transformation.getRotationMatrix(order.thirdIndex, -angle.floatValue()), mat);
            } else if (refFloats != null) {
                angle = Float.valueOf(refFloats[order.thirdIndex]);
                rotation[order.thirdIndex] = angle.floatValue();
                mat.mul(Transformation.getRotationMatrix(order.thirdIndex, -angle.floatValue()), mat);
            }
            angle = order.doTest(order.secondIndex, mat);
            if (angle == null) {
                return null;
            }
            if (refFloats != null) {
                angle = Float.valueOf(refFloats[order.secondIndex] + MathHelper.wrapDegrees((float)(angle.floatValue() - refFloats[order.secondIndex])));
            }
            rotation[order.secondIndex] = angle.floatValue();
            mat.mul(Transformation.getRotationMatrix(order.secondIndex, -angle.floatValue()), mat);
            angle = order.doTest(order.firstIndex, mat);
            if (angle == null) {
                return null;
            }
            if (refFloats != null) {
                angle = Float.valueOf(refFloats[order.firstIndex] + MathHelper.wrapDegrees((float)(angle.floatValue() - refFloats[order.firstIndex])));
            }
            rotation[order.firstIndex] = angle.floatValue();
            return new Vector3f(rotation);
        }

        public Vector3f getScale() {
            return this.getScale(0);
        }

        public Vector3f getScale(int invAxis) {
            Vector3f scale = new Vector3f(this.scale.m00, this.scale.m11, this.scale.m22);
            Vector3f x2 = new Vector3f(this.rotation.m00, this.rotation.m10, this.rotation.m20);
            Vector3f y2 = new Vector3f(this.rotation.m01, this.rotation.m11, this.rotation.m21);
            Vector3f z2 = new Vector3f(this.rotation.m02, this.rotation.m12, this.rotation.m22);
            Vector3f crossY = new Vector3f();
            Vector3f originalY = new Vector3f();
            originalY.normalize(y2);
            crossY.cross(z2, x2);
            crossY.normalize();
            if (crossY.dot(originalY) < 0.0f) {
                Transformation.getInvertAxisMatrix(invAxis).transform((Tuple3f)scale);
            }
            return scale;
        }

        public static Matrix3f getRotationMatrix(int axis, double degrees) {
            Matrix3f mat = new Matrix3f();
            switch (axis) {
                case 0: {
                    mat.rotX((float)Math.toRadians(degrees));
                    break;
                }
                case 1: {
                    mat.rotY((float)Math.toRadians(degrees));
                    break;
                }
                case 2: {
                    mat.rotZ((float)Math.toRadians(degrees));
                }
            }
            return mat;
        }

        public static Matrix3f getInvertAxisMatrix(int axis) {
            Matrix3f mat = new Matrix3f();
            mat.setIdentity();
            switch (axis) {
                case 0: {
                    mat.m00 = -1.0f;
                    break;
                }
                case 1: {
                    mat.m11 = -1.0f;
                    break;
                }
                case 2: {
                    mat.m22 = -1.0f;
                }
            }
            return mat;
        }

        public static enum RotationOrder {
            XYZ,
            XZY,
            YXZ,
            YZX,
            ZXY,
            ZYX;

            public final int firstIndex;
            public final int secondIndex;
            public final int thirdIndex;

            private RotationOrder() {
                String order = this.name().toUpperCase();
                this.firstIndex = order.charAt(0) - 88;
                this.secondIndex = order.charAt(1) - 88;
                this.thirdIndex = order.charAt(2) - 88;
            }

            public Float doTest(int index, Matrix3f test) {
                float[] buffer = new float[3];
                buffer[index == this.firstIndex ? this.secondIndex : this.firstIndex] = 1.0f;
                Vector3f in2 = new Vector3f(buffer);
                Vector3f out = new Vector3f();
                test.transform((Tuple3f)in2, (Tuple3f)out);
                out.get(buffer);
                buffer[index] = 0.0f;
                out.set(buffer);
                if ((double)out.length() < 1.0E-7) {
                    return null;
                }
                out.normalize();
                float cos = in2.dot(out);
                out.cross(in2, out);
                out.get(buffer);
                float sin = out.length() * Math.signum(buffer[index]);
                return Float.valueOf((float)Math.toDegrees(Math.atan2(sin, cos)));
            }
        }
    }
}

