/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.Vec3d
 *  org.lwjgl.util.vector.Matrix4f
 *  org.lwjgl.util.vector.Vector3f
 *  org.lwjgl.util.vector.Vector4f
 */
package eos.moe.dragoncore;

import java.lang.reflect.Field;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class iq {
    public static Vector3f b;
    public static Vector3f o;
    public static Vector3f y;
    public static float k;
    public static float ALLATORIxDEMO;

    public iq() {
        iq a2;
    }

    public static Vec3d ALLATORIxDEMO(Vec3d a2, Vec3d a3) {
        return a2.add(a3.x, a3.y, a3.z);
    }

    public static double[] ALLATORIxDEMO(double a2, double a3, double a4) {
        double a5 = Math.cos(a4);
        double a6 = Math.sin(a4);
        double a7 = a2 * a5 - a3 * a6;
        double a8 = a3 * a5 + a2 * a6;
        return new double[]{a7, a8};
    }

    public static Vector3f ALLATORIxDEMO(Number a2, Number a3, Number a4) {
        if (a2 == null || a3 == null || a4 == null) {
            return null;
        }
        return new Vector3f(a2.floatValue(), a3.floatValue(), a4.floatValue());
    }

    public static void ALLATORIxDEMO(Vec3d a2) {
        System.out.println("Vector x = " + a2.x);
        System.out.println("Vector y = " + a2.y);
        System.out.println("Vector z = " + a2.z);
    }

    public static void ALLATORIxDEMO(Object a2) {
        System.out.println(a2);
    }

    public static void ALLATORIxDEMO(Matrix4f a2) {
        Field[] a3 = a2.getClass().getFields();
        System.out.println("MATRIX DATA");
        System.out.println("~~~Standard Print~~~");
        iq.ALLATORIxDEMO((Object)a2);
        System.out.println("~~~In-Depth Print~~~");
        for (Field a4 : a3) {
            String a5 = a4.getName() + " = ";
            try {
                a5 = a5 + a4.getFloat(a2);
            }
            catch (Exception a6) {
                a5 = a5 + "ERROR";
            }
            System.out.println(a5);
        }
    }

    public static Matrix4f ALLATORIxDEMO(float a2, float a3, float a4, float a5, float a6, float a7) {
        Vector3f a8 = new Vector3f(a2, a3, a4);
        Matrix4f a9 = new Matrix4f();
        a9.translate(a8);
        a9.rotate(a7, y);
        a9.rotate(a6, o);
        a9.rotate(a5, b);
        return a9;
    }

    public static Matrix4f ALLATORIxDEMO(float[] a2) {
        return iq.ALLATORIxDEMO(a2[0], a2[1], a2[2], a2[3], a2[4], a2[5]);
    }

    public static Matrix4f ALLATORIxDEMO(float a2) {
        return iq.ALLATORIxDEMO(a2, a2, a2, a2, a2, a2);
    }

    public static Vector4f ALLATORIxDEMO(Vector4f a2, float a3, Vector4f a4) {
        if (a4 == null) {
            a4 = new Vector4f();
        }
        a4.x = a2.x * a3;
        a4.y = a2.y * a3;
        a4.z = a2.z * a3;
        a4.w = a2.w * a3;
        return a4;
    }

    public static Matrix4f ALLATORIxDEMO(Matrix4f a2, float a3, Matrix4f a4) {
        if (a4 == null) {
            a4 = new Matrix4f();
        }
        a4.m00 = a2.m00 * a3;
        a4.m01 = a2.m01 * a3;
        a4.m02 = a2.m02 * a3;
        a4.m02 = a2.m03 * a3;
        a4.m10 = a2.m10 * a3;
        a4.m11 = a2.m11 * a3;
        a4.m12 = a2.m12 * a3;
        a4.m13 = a2.m13 * a3;
        a4.m20 = a2.m20 * a3;
        a4.m21 = a2.m21 * a3;
        a4.m22 = a2.m22 * a3;
        a4.m23 = a2.m23 * a3;
        a4.m30 = a2.m30 * a3;
        a4.m31 = a2.m31 * a3;
        a4.m32 = a2.m32 * a3;
        a4.m33 = a2.m33 * a3;
        return a2;
    }

    public static float[] ALLATORIxDEMO(Matrix4f a2) {
        return new float[]{a2.m30, a2.m31, a2.m32};
    }

    public static Vector3f ALLATORIxDEMO(Matrix4f a2) {
        return new Vector3f(a2.m30, a2.m31, a2.m32);
    }

    public static Vector3f ALLATORIxDEMO(Vector3f a2) {
        return new Vector3f(-a2.x, -a2.y, -a2.z);
    }

    public static Vector3f ALLATORIxDEMO(String a2, String a3, String a4) {
        float a5 = Float.parseFloat(a2);
        float a6 = Float.parseFloat(a3);
        float a7 = Float.parseFloat(a4);
        return new Vector3f(a5, a6, a7);
    }

    public static Vector4f ALLATORIxDEMO(Vector4f a2) {
        return new Vector4f(a2.x, a2.y, a2.z, a2.w);
    }

    static {
        k = 57.29578f;
        ALLATORIxDEMO = (float)Math.PI / 180;
        b = new Vector3f(1.0f, 0.0f, 0.0f);
        o = new Vector3f(0.0f, 1.0f, 0.0f);
        y = new Vector3f(0.0f, 0.0f, 1.0f);
    }
}

