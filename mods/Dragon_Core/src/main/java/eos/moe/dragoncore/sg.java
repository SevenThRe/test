/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import java.util.Random;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class sg {
    public static Random ALLATORIxDEMO = new Random();

    public sg() {
        sg a2;
    }

    public static double ALLATORIxDEMO(double a2, double a3) {
        return ALLATORIxDEMO.nextDouble() * (a3 - a2) + a2;
    }

    public static double ALLATORIxDEMO(double a2) {
        return ALLATORIxDEMO.nextDouble() * a2;
    }

    public static long ALLATORIxDEMO() {
        return (long)ALLATORIxDEMO.nextDouble();
    }

    public static long ALLATORIxDEMO(long a2, long a3) {
        return (long)(ALLATORIxDEMO.nextDouble() * (double)(a3 - a2) + (double)a2);
    }

    public static long ALLATORIxDEMO(long a2) {
        return (long)(ALLATORIxDEMO.nextDouble() * (double)a2);
    }

    public static double ALLATORIxDEMO() {
        return ALLATORIxDEMO.nextDouble();
    }

    public static float ALLATORIxDEMO(float a2, float a3) {
        return ALLATORIxDEMO.nextFloat() * (a3 - a2) + a2;
    }

    public static float ALLATORIxDEMO(float a2) {
        return ALLATORIxDEMO.nextFloat() * a2;
    }

    public static float ALLATORIxDEMO() {
        return ALLATORIxDEMO.nextFloat();
    }

    public static int ALLATORIxDEMO(int a2, int a3) {
        return (int)(ALLATORIxDEMO.nextDouble() * (double)(a3 - a2) + (double)a2);
    }

    public static int ALLATORIxDEMO(int a2) {
        return (int)(ALLATORIxDEMO.nextDouble() * (double)a2);
    }

    public static int ALLATORIxDEMO() {
        return (int)ALLATORIxDEMO.nextDouble();
    }

    public static boolean ALLATORIxDEMO() {
        return ALLATORIxDEMO.nextBoolean();
    }
}

