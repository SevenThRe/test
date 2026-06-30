/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xf;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class pf
implements v {
    public static final pf y = new pf(0.0);
    public static final pf k = new pf(1.0);
    private final double ALLATORIxDEMO;

    public pf(Object a2) {
        pf a3;
        a3.ALLATORIxDEMO = a2 instanceof Boolean ? ((Boolean)a2 != false ? 1.0 : 0.0) : (a2 instanceof Number ? ((Number)a2).doubleValue() : 1.0);
    }

    @Override
    public Double ALLATORIxDEMO() {
        pf a2;
        return a2.ALLATORIxDEMO;
    }

    @Override
    public String c() {
        pf a2;
        return Double.toString(a2.ALLATORIxDEMO);
    }

    @Override
    public double ALLATORIxDEMO() {
        pf a2;
        return a2.ALLATORIxDEMO;
    }

    public static pf ALLATORIxDEMO(Object a2) {
        return new pf(a2);
    }

    public boolean equals(Object a2) {
        pf a3;
        if (a3 == a2) {
            return true;
        }
        if (a2 == null) {
            return false;
        }
        if (a2 instanceof pf) {
            pf a4 = (pf)a2;
            return Double.compare(a4.ALLATORIxDEMO(), a3.ALLATORIxDEMO()) == 0;
        }
        if (a2 instanceof xf) {
            xf a5 = (xf)a2;
            return Double.compare(a5.ALLATORIxDEMO(), a3.ALLATORIxDEMO()) == 0;
        }
        return false;
    }

    @Override
    public String ALLATORIxDEMO() {
        return "double";
    }
}

