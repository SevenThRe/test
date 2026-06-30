/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.gd;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class um {
    public um() {
        um a2;
    }

    public static float x(float a2, float a3, float a4) {
        return a2 + (a3 - a2) * a4;
    }

    public static float f(float a2, float a3, float a4) {
        a2 = gd.ALLATORIxDEMO(a2);
        a3 = gd.ALLATORIxDEMO(a3);
        return um.x(a2, um.ALLATORIxDEMO(a2, a3), a4);
    }

    public static double d(double a2, double a3, double a4, double a5, double a6) {
        double a7 = -0.5 * a2 + 1.5 * a3 - 1.5 * a4 + 0.5 * a5;
        double a8 = a2 - 2.5 * a3 + 2.0 * a4 - 0.5 * a5;
        double a9 = -0.5 * a2 + 0.5 * a4;
        return ((a7 * a6 + a8) * a6 + a9) * a6 + a3;
    }

    public static double ALLATORIxDEMO(float a2, float a3, float a4, float a5, float a6) {
        a2 = gd.ALLATORIxDEMO(a2);
        a3 = gd.ALLATORIxDEMO(a3);
        a4 = gd.ALLATORIxDEMO(a4);
        a5 = gd.ALLATORIxDEMO(a5);
        a3 = um.ALLATORIxDEMO(a2, a3);
        a4 = um.ALLATORIxDEMO(a3, a4);
        a5 = um.ALLATORIxDEMO(a4, a5);
        return um.d(a2, a3, a4, a5, a6);
    }

    public static float x(float a2, float a3, float a4, float a5, float a6) {
        float a7 = a5 - a4 - a2 + a3;
        float a8 = a2 - a3 - a7;
        float a9 = a4 - a2;
        return ((a7 * a6 + a8) * a6 + a9) * a6 + a3;
    }

    public static float f(float a2, float a3, float a4, float a5, float a6) {
        a2 = gd.ALLATORIxDEMO(a2);
        a3 = gd.ALLATORIxDEMO(a3);
        a4 = gd.ALLATORIxDEMO(a4);
        a5 = gd.ALLATORIxDEMO(a5);
        a3 = um.ALLATORIxDEMO(a2, a3);
        a4 = um.ALLATORIxDEMO(a3, a4);
        a5 = um.ALLATORIxDEMO(a4, a5);
        return um.x(a2, a3, a4, a5, a6);
    }

    public static float ALLATORIxDEMO(float a2, float a3, float a4, float a5) {
        float a6 = a4;
        float a7 = um.c(0.0f, a2, a3, 1.0f, a4);
        float a8 = Math.copySign(0.1f, a4 - a7);
        while (Math.abs(a4 - a7) > a5) {
            float a9 = a8;
            a7 = um.c(0.0f, a2, a3, 1.0f, a6 += a8);
            if (Math.copySign(a8, a4 - a7) == a9) continue;
            a8 *= -0.25f;
        }
        return a6;
    }

    public static float c(float a2, float a3, float a4) {
        return um.ALLATORIxDEMO(a2, a3, a4, 5.0E-4f);
    }

    public static float c(float a2, float a3, float a4, float a5, float a6) {
        float a7 = um.x(a2, a3, a6);
        float a8 = um.x(a3, a4, a6);
        float a9 = um.x(a4, a5, a6);
        float a10 = um.x(a7, a8, a6);
        float a11 = um.x(a8, a9, a6);
        return um.x(a10, a11, a6);
    }

    public static float ALLATORIxDEMO(float a2, float a3) {
        float a4 = a2 - a3;
        if (a4 > 180.0f || a4 < -180.0f) {
            a4 = Math.copySign(360.0f - Math.abs(a4), a4);
            return a2 + a4;
        }
        return a3;
    }

    public static float ALLATORIxDEMO(float a2, float a3, float a4) {
        return um.ALLATORIxDEMO(a2, 0.0f, a4, a3 - a4, a3);
    }

    public static float ALLATORIxDEMO(float a2, float a3, float a4, float a5, float a6) {
        if (a2 < a3 || a2 > a6) {
            return 0.0f;
        }
        if (a2 < a4) {
            return (a2 - a3) / (a4 - a3);
        }
        if (a2 > a5) {
            return 1.0f - (a2 - a5) / (a6 - a5);
        }
        return 1.0f;
    }

    public static double f(double a2, double a3, double a4) {
        return a2 + (a3 - a2) * a4;
    }

    public static double c(double a2, double a3, double a4) {
        a2 = gd.ALLATORIxDEMO(a2);
        a3 = gd.ALLATORIxDEMO(a3);
        return um.f(a2, um.ALLATORIxDEMO(a2, a3), a4);
    }

    public static double x(double a2, double a3, double a4, double a5, double a6) {
        double a7 = a5 - a4 - a2 + a3;
        double a8 = a2 - a3 - a7;
        double a9 = a4 - a2;
        return ((a7 * a6 + a8) * a6 + a9) * a6 + a3;
    }

    public static double f(double a2, double a3, double a4, double a5, double a6) {
        a2 = gd.ALLATORIxDEMO(a2);
        a3 = gd.ALLATORIxDEMO(a3);
        a4 = gd.ALLATORIxDEMO(a4);
        a5 = gd.ALLATORIxDEMO(a5);
        a3 = um.ALLATORIxDEMO(a2, a3);
        a4 = um.ALLATORIxDEMO(a3, a4);
        a5 = um.ALLATORIxDEMO(a4, a5);
        return um.x(a2, a3, a4, a5, a6);
    }

    public static double ALLATORIxDEMO(double a2, double a3, double a4, double a5) {
        double a6 = a4;
        double a7 = um.c(0.0, a2, a3, 1.0, a4);
        double a8 = Math.copySign((double)0.1f, a4 - a7);
        while (Math.abs(a4 - a7) > a5) {
            double a9 = a8;
            a7 = um.c(0.0, a2, a3, 1.0, a6 += a8);
            if (Math.copySign(a8, a4 - a7) == a9) continue;
            a8 *= -0.25;
        }
        return a6;
    }

    public static double ALLATORIxDEMO(double a2, double a3, float a4) {
        return um.ALLATORIxDEMO(a2, a3, (double)a4, (double)5.0E-4f);
    }

    public static double c(double a2, double a3, double a4, double a5, double a6) {
        double a7 = um.f(a2, a3, a6);
        double a8 = um.f(a3, a4, a6);
        double a9 = um.f(a4, a5, a6);
        double a10 = um.f(a7, a8, a6);
        double a11 = um.f(a8, a9, a6);
        return um.f(a10, a11, a6);
    }

    public static double ALLATORIxDEMO(double a2, double a3) {
        double a4 = a2 - a3;
        if (a4 > 180.0 || a4 < -180.0) {
            a4 = Math.copySign(360.0 - Math.abs(a4), a4);
            return a2 + a4;
        }
        return a3;
    }

    public static double ALLATORIxDEMO(double a2, double a3, double a4) {
        return um.ALLATORIxDEMO(a2, 0.0, a4, a3 - a4, a3);
    }

    public static double ALLATORIxDEMO(double a2, double a3, double a4, double a5, double a6) {
        if (a2 < a3 || a2 > a6) {
            return 0.0;
        }
        if (a2 < a4) {
            return (a2 - a3) / (a4 - a3);
        }
        if (a2 > a5) {
            return 1.0 - (a2 - a5) / (a6 - a5);
        }
        return 1.0;
    }
}

