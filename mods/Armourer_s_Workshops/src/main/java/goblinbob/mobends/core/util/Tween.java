/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.util;

public class Tween {
    public static double easeIn(double a2, double power) {
        return Math.pow(a2, power);
    }

    public static double easeOut(double a2, double power) {
        return 1.0 - Math.pow(1.0 - a2, power);
    }

    public static double easeInOut(double a2, double power) {
        if (a2 < 0.5) {
            a2 *= 2.0;
            a2 = Math.pow(a2, power);
            a2 /= 2.0;
        } else {
            a2 = 1.0 - a2;
            a2 *= 2.0;
            a2 = Math.pow(a2, power);
            a2 /= 2.0;
            a2 = 1.0 - a2;
        }
        return a2;
    }
}

