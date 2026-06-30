/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  rk
 */
package net.optifine.util;

public class MathUtils {
    public static final float PI = (float)Math.PI;
    public static final float PI2 = (float)Math.PI * 2;
    public static final float PId2 = 1.5707964f;
    private static final float[] ASIN_TABLE;

    public static float asin(float value) {
        return ASIN_TABLE[(int)((double)(value + 1.0f) * 32767.5) & 0xFFFF];
    }

    public static float acos(float value) {
        return 1.5707964f - ASIN_TABLE[(int)((double)(value + 1.0f) * 32767.5) & 0xFFFF];
    }

    public static int getAverage(int[] vals) {
        if (vals.length <= 0) {
            return 0;
        }
        int sum = MathUtils.getSum(vals);
        int avg = sum / vals.length;
        return avg;
    }

    public static int getSum(int[] vals) {
        if (vals.length <= 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < vals.length; ++i) {
            int val = vals[i];
            sum += val;
        }
        return sum;
    }

    public static int roundDownToPowerOfTwo(int val) {
        int po2 = rk.c((int)val);
        if (val == po2) {
            return po2;
        }
        return po2 / 2;
    }

    public static boolean equalsDelta(float f1, float f2, float delta) {
        return Math.abs(f1 - f2) <= delta;
    }

    public static float toDeg(float angle) {
        return angle * 180.0f / rk.PI;
    }

    public static float toRad(float angle) {
        return angle / 180.0f * rk.PI;
    }

    public static float roundToFloat(double d2) {
        return (float)((double)Math.round(d2 * 1.0E8) / 1.0E8);
    }

    static {
        int i;
        ASIN_TABLE = new float[65536];
        for (i = 0; i < 65536; ++i) {
            MathUtils.ASIN_TABLE[i] = (float)Math.asin((double)i / 32767.5 - 1.0);
        }
        for (i = -1; i < 2; ++i) {
            MathUtils.ASIN_TABLE[(int)(((double)i + 1.0) * 32767.5) & 0xFFFF] = (float)Math.asin(i);
        }
    }
}

