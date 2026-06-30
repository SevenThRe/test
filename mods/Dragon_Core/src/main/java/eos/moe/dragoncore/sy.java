/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.bt;
import eos.moe.dragoncore.lr;
import eos.moe.dragoncore.vo;
import java.util.Random;

public class sy {
    public static final lr ALLATORIxDEMO = new lr(new vo());

    public sy() {
        sy a2;
    }

    public static double ALLATORIxDEMO(bt a2) {
        double a3;
        Random a4 = new Random();
        if (a2.ALLATORIxDEMO().size() >= 3) {
            a4.setSeed((long)a2.ALLATORIxDEMO(2));
            a3 = a4.nextDouble();
        } else {
            a3 = Math.random();
        }
        if (a2.ALLATORIxDEMO().size() >= 2) {
            double a5 = a2.ALLATORIxDEMO(0);
            double a6 = a2.ALLATORIxDEMO(1);
            double a7 = Math.min(a5, a6);
            double a8 = Math.max(a5, a6);
            a3 = a3 * (a8 - a7) + a7;
        } else if (a2.ALLATORIxDEMO().size() >= 1) {
            a3 *= a2.ALLATORIxDEMO(0);
        }
        return a3;
    }

    public static double ALLATORIxDEMO(double a2, double a3) {
        return a2 + Math.random() * (a3 - a2);
    }

    public static int ALLATORIxDEMO(int a2, int a3) {
        return (int)Math.round((double)a2 + Math.random() * (double)(a3 - a2));
    }

    public static double f(double a2, double a3, double a4) {
        int a5 = 0;
        int a6 = 0;
        while (true) {
            int n2 = a5++;
            if (!((double)n2 < a2)) break;
            a6 = (int)((double)a6 + sy.ALLATORIxDEMO(a3, a4));
        }
        return a6;
    }

    public static int ALLATORIxDEMO(int a2, int a3, int a4) {
        int a5 = 0;
        int a6 = 0;
        while (a5++ < a2) {
            a6 += sy.ALLATORIxDEMO(a3, a4);
        }
        return a6;
    }

    public static int ALLATORIxDEMO(int a2) {
        return 3 * a2 ^ 2 - 2 * a2 ^ 3;
    }

    public static double c(double a2, double a3, double a4) {
        a4 = Math.max(0.0, Math.min(1.0, a4));
        return a2 + (a3 - a2) * a4;
    }

    public static double ALLATORIxDEMO(double a2, double a3, double a4) {
        if ((a2 = sy.ALLATORIxDEMO(a2)) > (a3 = sy.ALLATORIxDEMO(a3))) {
            double a5 = a2;
            a2 = a3;
            a3 = a5;
        }
        if (a3 - a2 > 180.0) {
            return sy.ALLATORIxDEMO(a3 + a4 * (360.0 - (a3 - a2)));
        }
        return a2 + a4 * (a3 - a2);
    }

    public static double ALLATORIxDEMO(double a2) {
        return ((a2 + 180.0) % 360.0 + 180.0) % 360.0;
    }

    static {
        ALLATORIxDEMO.ALLATORIxDEMO().put("\u53d6\u968f\u673a\u6570", sy::ALLATORIxDEMO);
    }
}

