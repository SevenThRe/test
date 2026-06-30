/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import java.math.BigDecimal;

public class lm {
    private static final int j = 16;

    private /* synthetic */ lm() {
        lm a2;
    }

    public static double h(String a2, String a3) {
        BigDecimal bigDecimal = new BigDecimal(a2);
        a3 = new BigDecimal((String)a3);
        return bigDecimal.multiply((BigDecimal)a3).doubleValue();
    }

    public static double h(double a2, double a3) {
        return lm.r(a2, a3, 16);
    }

    public static double z(double a2, double a32) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(a2));
        BigDecimal a32 = new BigDecimal(Double.toString(a32));
        return bigDecimal.add(a32).doubleValue();
    }

    public static double z(String a2, String a3) {
        BigDecimal bigDecimal = new BigDecimal(a2);
        a3 = new BigDecimal((String)a3);
        return bigDecimal.add((BigDecimal)a3).doubleValue();
    }

    public static double y(double a2, double a32) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(a2));
        BigDecimal a32 = new BigDecimal(Double.toString(a32));
        return bigDecimal.multiply(a32).doubleValue();
    }

    public static double r(double a2, double a32, int a4) {
        if (a4 < 0) {
            throw new IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero");
        }
        BigDecimal bigDecimal = new BigDecimal(Double.toString(a2));
        BigDecimal a32 = new BigDecimal(Double.toString(a32));
        return bigDecimal.divide(a32, a4, 4).doubleValue();
    }

    public static double y(String a2, String a3) {
        BigDecimal bigDecimal = new BigDecimal(a2);
        a3 = new BigDecimal((String)a3);
        return bigDecimal.subtract((BigDecimal)a3).doubleValue();
    }

    public static float y(float a2, float a32) {
        BigDecimal bigDecimal = new BigDecimal(Float.toString(a2));
        BigDecimal a32 = new BigDecimal(Float.toString(a32));
        return bigDecimal.subtract(a32).floatValue();
    }

    public static double r(double a2, int a3) {
        if (a3 < 0) {
            throw new IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero");
        }
        BigDecimal bigDecimal = new BigDecimal(Double.toString(a2));
        BigDecimal bigDecimal2 = new BigDecimal("1");
        return bigDecimal.divide(bigDecimal2, a3, 4).doubleValue();
    }

    public static double r(String a2, String a3) {
        BigDecimal bigDecimal = new BigDecimal(a2);
        a3 = new BigDecimal((String)a3);
        return bigDecimal.divide((BigDecimal)a3, 16, 4).doubleValue();
    }

    public static float r(float a2, float a32) {
        BigDecimal bigDecimal = new BigDecimal(Float.toString(a2));
        BigDecimal a32 = new BigDecimal(Float.toString(a32));
        return bigDecimal.multiply(a32).floatValue();
    }

    public static double r(String a2, int a3) {
        if (a3 < 0) {
            throw new IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero");
        }
        BigDecimal bigDecimal = new BigDecimal(a2);
        BigDecimal bigDecimal2 = new BigDecimal("1");
        return bigDecimal.divide(bigDecimal2, a3, 4).doubleValue();
    }

    public static double r(double a2, double a32) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(a2));
        BigDecimal a32 = new BigDecimal(Double.toString(a32));
        return bigDecimal.subtract(a32).doubleValue();
    }
}

