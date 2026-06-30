/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

public class gd {
    public gd() {
        gd a2;
    }

    public static float ALLATORIxDEMO(float a2) {
        if ((a2 %= 360.0f) >= 180.0f) {
            a2 -= 360.0f;
        }
        if (a2 < -180.0f) {
            a2 += 360.0f;
        }
        return a2;
    }

    public static double ALLATORIxDEMO(double a2) {
        if ((a2 %= 360.0) >= 180.0) {
            a2 -= 360.0;
        }
        if (a2 < -180.0) {
            a2 += 360.0;
        }
        return a2;
    }

    public static int ALLATORIxDEMO(int a2) {
        if ((a2 %= 360) >= 180) {
            a2 -= 360;
        }
        if (a2 < -180) {
            a2 += 360;
        }
        return a2;
    }
}

