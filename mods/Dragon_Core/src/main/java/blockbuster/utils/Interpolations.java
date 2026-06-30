/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.MathHelper
 */
package blockbuster.utils;

import net.minecraft.util.math.MathHelper;

public class Interpolations {
    public static float lerp(float a2, float b2, float position) {
        return a2 + (b2 - a2) * position;
    }

    public static float lerpYaw(float a2, float b2, float position) {
        a2 = MathHelper.func_76142_g((float)a2);
        b2 = MathHelper.func_76142_g((float)b2);
        return Interpolations.lerp(a2, Interpolations.normalizeYaw(a2, b2), position);
    }

    public static double cubicHermite(double y0, double y1, double y2, double y3, double x2) {
        double a2 = -0.5 * y0 + 1.5 * y1 - 1.5 * y2 + 0.5 * y3;
        double b2 = y0 - 2.5 * y1 + 2.0 * y2 - 0.5 * y3;
        double c2 = -0.5 * y0 + 0.5 * y2;
        return ((a2 * x2 + b2) * x2 + c2) * x2 + y1;
    }

    public static double cubicHermiteYaw(float y0, float y1, float y2, float y3, float position) {
        y0 = MathHelper.func_76142_g((float)y0);
        y1 = MathHelper.func_76142_g((float)y1);
        y2 = MathHelper.func_76142_g((float)y2);
        y3 = MathHelper.func_76142_g((float)y3);
        y1 = Interpolations.normalizeYaw(y0, y1);
        y2 = Interpolations.normalizeYaw(y1, y2);
        y3 = Interpolations.normalizeYaw(y2, y3);
        return Interpolations.cubicHermite(y0, y1, y2, y3, position);
    }

    public static float cubic(float y0, float y1, float y2, float y3, float x2) {
        float a2 = y3 - y2 - y0 + y1;
        float b2 = y0 - y1 - a2;
        float c2 = y2 - y0;
        return ((a2 * x2 + b2) * x2 + c2) * x2 + y1;
    }

    public static float cubicYaw(float y0, float y1, float y2, float y3, float position) {
        y0 = MathHelper.func_76142_g((float)y0);
        y1 = MathHelper.func_76142_g((float)y1);
        y2 = MathHelper.func_76142_g((float)y2);
        y3 = MathHelper.func_76142_g((float)y3);
        y1 = Interpolations.normalizeYaw(y0, y1);
        y2 = Interpolations.normalizeYaw(y1, y2);
        y3 = Interpolations.normalizeYaw(y2, y3);
        return Interpolations.cubic(y0, y1, y2, y3, position);
    }

    public static float bezierX(float x1, float x2, float t2, float epsilon) {
        float x3 = t2;
        float init = Interpolations.bezier(0.0f, x1, x2, 1.0f, t2);
        float factor = Math.copySign(0.1f, t2 - init);
        while (Math.abs(t2 - init) > epsilon) {
            float oldFactor = factor;
            init = Interpolations.bezier(0.0f, x1, x2, 1.0f, x3 += factor);
            if (Math.copySign(factor, t2 - init) == oldFactor) continue;
            factor *= -0.25f;
        }
        return x3;
    }

    public static float bezierX(float x1, float x2, float t2) {
        return Interpolations.bezierX(x1, x2, t2, 5.0E-4f);
    }

    public static float bezier(float x1, float x2, float x3, float x4, float t2) {
        float t1 = Interpolations.lerp(x1, x2, t2);
        float t22 = Interpolations.lerp(x2, x3, t2);
        float t3 = Interpolations.lerp(x3, x4, t2);
        float t4 = Interpolations.lerp(t1, t22, t2);
        float t5 = Interpolations.lerp(t22, t3, t2);
        return Interpolations.lerp(t4, t5, t2);
    }

    public static float normalizeYaw(float a2, float b2) {
        float diff = a2 - b2;
        if (diff > 180.0f || diff < -180.0f) {
            diff = Math.copySign(360.0f - Math.abs(diff), diff);
            return a2 + diff;
        }
        return b2;
    }

    public static float envelope(float x2, float duration, float fades) {
        return Interpolations.envelope(x2, 0.0f, fades, duration - fades, duration);
    }

    public static float envelope(float x2, float lowIn, float lowOut, float highIn, float highOut) {
        if (x2 < lowIn || x2 > highOut) {
            return 0.0f;
        }
        if (x2 < lowOut) {
            return (x2 - lowIn) / (lowOut - lowIn);
        }
        if (x2 > highIn) {
            return 1.0f - (x2 - highIn) / (highOut - highIn);
        }
        return 1.0f;
    }

    public static double lerp(double a2, double b2, double position) {
        return a2 + (b2 - a2) * position;
    }

    public static double lerpYaw(double a2, double b2, double position) {
        a2 = MathHelper.func_76138_g((double)a2);
        b2 = MathHelper.func_76138_g((double)b2);
        return Interpolations.lerp(a2, Interpolations.normalizeYaw(a2, b2), position);
    }

    public static double cubic(double y0, double y1, double y2, double y3, double x2) {
        double a2 = y3 - y2 - y0 + y1;
        double b2 = y0 - y1 - a2;
        double c2 = y2 - y0;
        return ((a2 * x2 + b2) * x2 + c2) * x2 + y1;
    }

    public static double cubicYaw(double y0, double y1, double y2, double y3, double position) {
        y0 = MathHelper.func_76138_g((double)y0);
        y1 = MathHelper.func_76138_g((double)y1);
        y2 = MathHelper.func_76138_g((double)y2);
        y3 = MathHelper.func_76138_g((double)y3);
        y1 = Interpolations.normalizeYaw(y0, y1);
        y2 = Interpolations.normalizeYaw(y1, y2);
        y3 = Interpolations.normalizeYaw(y2, y3);
        return Interpolations.cubic(y0, y1, y2, y3, position);
    }

    public static double bezierX(double x1, double x2, double t2, double epsilon) {
        double x3 = t2;
        double init = Interpolations.bezier(0.0, x1, x2, 1.0, t2);
        double factor = Math.copySign((double)0.1f, t2 - init);
        while (Math.abs(t2 - init) > epsilon) {
            double oldFactor = factor;
            init = Interpolations.bezier(0.0, x1, x2, 1.0, x3 += factor);
            if (Math.copySign(factor, t2 - init) == oldFactor) continue;
            factor *= -0.25;
        }
        return x3;
    }

    public static double bezierX(double x1, double x2, float t2) {
        return Interpolations.bezierX(x1, x2, (double)t2, (double)5.0E-4f);
    }

    public static double bezier(double x1, double x2, double x3, double x4, double t2) {
        double t1 = Interpolations.lerp(x1, x2, t2);
        double t22 = Interpolations.lerp(x2, x3, t2);
        double t3 = Interpolations.lerp(x3, x4, t2);
        double t4 = Interpolations.lerp(t1, t22, t2);
        double t5 = Interpolations.lerp(t22, t3, t2);
        return Interpolations.lerp(t4, t5, t2);
    }

    public static double normalizeYaw(double a2, double b2) {
        double diff = a2 - b2;
        if (diff > 180.0 || diff < -180.0) {
            diff = Math.copySign(360.0 - Math.abs(diff), diff);
            return a2 + diff;
        }
        return b2;
    }

    public static double envelope(double x2, double duration, double fades) {
        return Interpolations.envelope(x2, 0.0, fades, duration - fades, duration);
    }

    public static double envelope(double x2, double lowIn, double lowOut, double highIn, double highOut) {
        if (x2 < lowIn || x2 > highOut) {
            return 0.0;
        }
        if (x2 < lowOut) {
            return (x2 - lowIn) / (lowOut - lowIn);
        }
        if (x2 > highIn) {
            return 1.0 - (x2 - highIn) / (highOut - highIn);
        }
        return 1.0;
    }
}

