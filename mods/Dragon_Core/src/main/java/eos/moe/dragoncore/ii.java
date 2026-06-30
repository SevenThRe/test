/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import java.math.BigDecimal;

public class ii {
    private static final int ALLATORIxDEMO = 16;

    private /* synthetic */ ii() {
        ii a2;
    }

    public static double x(double a2, double a3) {
        BigDecimal a4 = new BigDecimal(Double.toString(a2));
        BigDecimal a5 = new BigDecimal(Double.toString(a3));
        return a4.add(a5).doubleValue();
    }

    public static double x(String a2, String a3) {
        BigDecimal a4 = new BigDecimal(a2);
        BigDecimal a5 = new BigDecimal(a3);
        return a4.add(a5).doubleValue();
    }

    public static double f(double a2, double a3) {
        BigDecimal a4 = new BigDecimal(Double.toString(a2));
        BigDecimal a5 = new BigDecimal(Double.toString(a3));
        return a4.subtract(a5).doubleValue();
    }

    public static float c(float a2, float a3) {
        BigDecimal a4 = new BigDecimal(Float.toString(a2));
        BigDecimal a5 = new BigDecimal(Float.toString(a3));
        return a4.subtract(a5).floatValue();
    }

    public static double f(String a2, String a3) {
        BigDecimal a4 = new BigDecimal(a2);
        BigDecimal a5 = new BigDecimal(a3);
        return a4.subtract(a5).doubleValue();
    }

    public static double c(double a2, double a3) {
        BigDecimal a4 = new BigDecimal(Double.toString(a2));
        BigDecimal a5 = new BigDecimal(Double.toString(a3));
        return a4.multiply(a5).doubleValue();
    }

    public static float ALLATORIxDEMO(float a2, float a3) {
        BigDecimal a4 = new BigDecimal(Float.toString(a2));
        BigDecimal a5 = new BigDecimal(Float.toString(a3));
        return a4.multiply(a5).floatValue();
    }

    public static double c(String a2, String a3) {
        BigDecimal a4 = new BigDecimal(a2);
        BigDecimal a5 = new BigDecimal(a3);
        return a4.multiply(a5).doubleValue();
    }

    public static double ALLATORIxDEMO(double a2, double a3) {
        return ii.ALLATORIxDEMO(a2, a3, 16);
    }

    public static double ALLATORIxDEMO(String a2, String a3) {
        BigDecimal a4 = new BigDecimal(a2);
        BigDecimal a5 = new BigDecimal(a3);
        return a4.divide(a5, 16, 4).doubleValue();
    }

    public static double ALLATORIxDEMO(double a2, double a3, int a4) {
        if (a4 < 0) {
            throw new IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero");
        }
        BigDecimal a5 = new BigDecimal(Double.toString(a2));
        BigDecimal a6 = new BigDecimal(Double.toString(a3));
        return a5.divide(a6, a4, 4).doubleValue();
    }

    public static double ALLATORIxDEMO(double a2, int a3) {
        if (a3 < 0) {
            throw new IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero");
        }
        BigDecimal a4 = new BigDecimal(Double.toString(a2));
        BigDecimal a5 = new BigDecimal("1");
        return a4.divide(a5, a3, 4).doubleValue();
    }

    public static double ALLATORIxDEMO(String a2, int a3) {
        if (a3 < 0) {
            throw new IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero");
        }
        BigDecimal a4 = new BigDecimal(a2);
        BigDecimal a5 = new BigDecimal("1");
        return a4.divide(a5, a3, 4).doubleValue();
    }
}

