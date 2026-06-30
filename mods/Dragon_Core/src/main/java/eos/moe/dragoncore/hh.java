/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.u;

public class hh
implements u {
    private final double ALLATORIxDEMO;

    public hh(double a2) {
        hh a3;
        a3.ALLATORIxDEMO = a2;
    }

    @Override
    public double ALLATORIxDEMO() {
        hh a2;
        return a2.ALLATORIxDEMO;
    }

    public static hh ALLATORIxDEMO(double a2) {
        return new hh(a2);
    }

    public static hh ALLATORIxDEMO(float a2) {
        return new hh(a2);
    }

    public static hh c(String a2) {
        return new hh(Double.parseDouble(a2));
    }

    public static hh ALLATORIxDEMO(String a2) {
        return new hh(Float.parseFloat(a2));
    }

    public static hh ALLATORIxDEMO(u a2, u a3) {
        return hh.ALLATORIxDEMO(a2.ALLATORIxDEMO() - a3.ALLATORIxDEMO());
    }
}

