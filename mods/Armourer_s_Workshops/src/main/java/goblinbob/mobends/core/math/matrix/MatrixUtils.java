/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.math.matrix;

import goblinbob.mobends.core.math.matrix.IMat4x4d;
import goblinbob.mobends.core.math.matrix.IMatd;
import goblinbob.mobends.core.math.matrix.Mat4x4d;
import goblinbob.mobends.core.math.vector.Vec4d;
import java.nio.FloatBuffer;

public class MatrixUtils {
    public static FloatBuffer matToGlMatrix(IMat4x4d matIn, FloatBuffer destBuffer) {
        double[] fields;
        destBuffer.clear();
        for (double f2 : fields = matIn.getFields()) {
            destBuffer.put((float)f2);
        }
        destBuffer.rewind();
        return destBuffer;
    }

    public static void identity(IMat4x4d dest) {
        dest.setFields(1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0);
    }

    public static void inverse(IMat4x4d src, IMat4x4d dest) {
        double[] f2 = src.getFields();
        double Coef00 = f2[10] * f2[15] - f2[14] * f2[11];
        double Coef02 = f2[6] * f2[15] - f2[14] * f2[7];
        double Coef03 = f2[6] * f2[11] - f2[10] * f2[7];
        double Coef04 = f2[9] * f2[15] - f2[13] * f2[11];
        double Coef06 = f2[5] * f2[15] - f2[13] * f2[7];
        double Coef07 = f2[5] * f2[11] - f2[9] * f2[7];
        double Coef08 = f2[9] * f2[14] - f2[13] * f2[10];
        double Coef10 = f2[5] * f2[14] - f2[13] * f2[6];
        double Coef11 = f2[5] * f2[10] - f2[9] * f2[6];
        double Coef12 = f2[8] * f2[15] - f2[12] * f2[11];
        double Coef14 = f2[4] * f2[15] - f2[12] * f2[7];
        double Coef15 = f2[4] * f2[11] - f2[8] * f2[7];
        double Coef16 = f2[8] * f2[14] - f2[12] * f2[10];
        double Coef18 = f2[4] * f2[14] - f2[12] * f2[6];
        double Coef19 = f2[4] * f2[10] - f2[8] * f2[6];
        double Coef20 = f2[8] * f2[13] - f2[12] * f2[9];
        double Coef22 = f2[4] * f2[13] - f2[12] * f2[5];
        double Coef23 = f2[4] * f2[9] - f2[8] * f2[5];
        Vec4d Fac0 = new Vec4d(Coef00, Coef00, Coef02, Coef03);
        Vec4d Fac1 = new Vec4d(Coef04, Coef04, Coef06, Coef07);
        Vec4d Fac2 = new Vec4d(Coef08, Coef08, Coef10, Coef11);
        Vec4d Fac3 = new Vec4d(Coef12, Coef12, Coef14, Coef15);
        Vec4d Fac4 = new Vec4d(Coef16, Coef16, Coef18, Coef19);
        Vec4d Fac5 = new Vec4d(Coef20, Coef20, Coef22, Coef23);
        Vec4d Vec0 = new Vec4d(f2[4], f2[0], f2[0], f2[0]);
        Vec4d Vec1 = new Vec4d(f2[5], f2[1], f2[1], f2[1]);
        Vec4d Vec2 = new Vec4d(f2[6], f2[2], f2[2], f2[2]);
        Vec4d Vec3 = new Vec4d(f2[7], f2[3], f2[3], f2[3]);
        Vec4d Inv0 = new Vec4d(Vec1.x * Fac0.x - Vec2.x * Fac1.x + Vec3.x * Fac2.x, Vec1.y * Fac0.y - Vec2.y * Fac1.y + Vec3.y * Fac2.y, Vec1.z * Fac0.z - Vec2.z * Fac1.z + Vec3.z * Fac2.z, Vec1.w * Fac0.w - Vec2.w * Fac1.w + Vec3.w * Fac2.w);
        Vec4d Inv1 = new Vec4d(Vec0.x * Fac0.x - Vec2.x * Fac3.x + Vec3.x * Fac4.x, Vec0.y * Fac0.y - Vec2.y * Fac3.y + Vec3.y * Fac4.y, Vec0.z * Fac0.z - Vec2.z * Fac3.z + Vec3.z * Fac4.z, Vec0.w * Fac0.w - Vec2.w * Fac3.w + Vec3.w * Fac4.w);
        Vec4d Inv2 = new Vec4d(Vec0.x * Fac1.x - Vec1.x * Fac3.x + Vec3.x * Fac5.x, Vec0.y * Fac1.y - Vec1.y * Fac3.y + Vec3.y * Fac5.y, Vec0.z * Fac1.z - Vec1.z * Fac3.z + Vec3.z * Fac5.z, Vec0.w * Fac1.w - Vec1.w * Fac3.w + Vec3.w * Fac5.w);
        Vec4d Inv3 = new Vec4d(Vec0.x * Fac2.x - Vec1.x * Fac4.x + Vec2.x * Fac5.x, Vec0.y * Fac2.y - Vec1.y * Fac4.y + Vec2.y * Fac5.y, Vec0.z * Fac2.z - Vec1.z * Fac4.z + Vec2.z * Fac5.z, Vec0.w * Fac2.w - Vec1.w * Fac4.w + Vec2.w * Fac5.w);
        Vec4d SignA = new Vec4d(1.0, -1.0, 1.0, -1.0);
        Vec4d SignB = new Vec4d(-1.0, 1.0, -1.0, 1.0);
        double[] inverseFields = new double[]{Inv0.x * SignA.x, Inv0.y * SignA.y, Inv0.z * SignA.z, Inv0.w * SignA.w, Inv1.x * SignB.x, Inv1.y * SignB.y, Inv1.z * SignB.z, Inv1.w * SignB.w, Inv2.x * SignA.x, Inv2.y * SignA.y, Inv2.z * SignA.z, Inv2.w * SignA.w, Inv3.x * SignB.x, Inv3.y * SignB.y, Inv3.z * SignB.z, Inv3.w * SignB.w};
        Mat4x4d Inverse = new Mat4x4d(inverseFields);
        Vec4d Dot0 = new Vec4d(f2[0] * inverseFields[0], f2[1] * inverseFields[4], f2[2] * inverseFields[8], f2[3] * inverseFields[12]);
        double Dot1 = Dot0.x + Dot0.y + (Dot0.z + Dot0.w);
        double OneOverDeterminant = 1.0 / Dot1;
        dest.copyFrom(Inverse);
        dest.scale(OneOverDeterminant);
    }

    public static void multiply(IMatd a2, IMatd b2, IMatd dest) {
        int aCols = a2.getCols();
        int bCols = b2.getCols();
        int aRows = a2.getRows();
        int bRows = b2.getRows();
        double[] aFields = a2.getFields();
        double[] bFields = b2.getFields();
        if (aCols != bRows) {
            return;
        }
        double[] newFields = new double[bCols * aRows];
        for (int i2 = 0; i2 < bCols; ++i2) {
            for (int j2 = 0; j2 < aRows; ++j2) {
                double dot = 0.0;
                newFields[i2 * aRows + j2] = 0.0;
                for (int k2 = 0; k2 < aCols; ++k2) {
                    int n2 = i2 * aRows + j2;
                    newFields[n2] = newFields[n2] + aFields[k2 * aRows + j2] * bFields[i2 * bRows + k2];
                }
            }
        }
        dest.setFields(newFields);
    }

    public static String toString(IMatd a2) {
        double[] fields = a2.getFields();
        int cols = a2.getCols();
        int rows = a2.getRows();
        StringBuilder builder = new StringBuilder();
        int maxRowLength = 0;
        for (int r2 = 0; r2 < rows; ++r2) {
            int rowLength = 4;
            builder.append("[ ");
            for (int c2 = 0; c2 < cols; ++c2) {
                double cell = fields[c2 * rows + r2];
                String cellString = String.valueOf(cell);
                builder.append(cellString);
                rowLength += cellString.length();
                if (c2 >= cols - 1) continue;
                builder.append(", ");
                rowLength += 2;
            }
            builder.append(" ]\n");
            if (rowLength <= maxRowLength) continue;
            maxRowLength = rowLength;
        }
        builder.insert(0, '\n');
        for (int i2 = 0; i2 < maxRowLength; ++i2) {
            builder.insert(0, '-');
            builder.append('-');
        }
        return builder.toString();
    }
}

