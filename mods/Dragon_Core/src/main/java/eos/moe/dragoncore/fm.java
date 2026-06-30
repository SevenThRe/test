/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.pe;
import eos.moe.dragoncore.qg;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xn;
import java.util.Arrays;

public class fm {
    public fm() {
        fm a2;
    }

    public static v ALLATORIxDEMO(nh a2, v ... a3) {
        if (a3.length > 0) {
            qg a4 = new qg(Arrays.asList(a3));
            Object a5 = a2.ALLATORIxDEMO().ALLATORIxDEMO().ALLATORIxDEMO();
            ((xn)a5).ALLATORIxDEMO("\u5c40\u90e8\u53d8\u91cf.\u53c2\u6570", a4);
            ((xn)a5).ALLATORIxDEMO("\u5c40\u90e8\u53d8\u91cf.args", a4);
            return a2.ALLATORIxDEMO().ALLATORIxDEMO(a2.x(), (xn)a5);
        }
        return a2.ALLATORIxDEMO().ALLATORIxDEMO(a2.x());
    }

    public static v ALLATORIxDEMO(nh a2, pe a3) {
        Object a4 = a2.ALLATORIxDEMO().ALLATORIxDEMO().ALLATORIxDEMO();
        ((xn)a4).ALLATORIxDEMO("\u5c40\u90e8\u53d8\u91cf.\u5f53\u524d\u7ec4\u4ef6", a3);
        ((xn)a4).ALLATORIxDEMO("\u5c40\u90e8\u53d8\u91cf.component", a3);
        return a2.ALLATORIxDEMO().ALLATORIxDEMO(a2.x(), (xn)a4);
    }

    public static v ALLATORIxDEMO(nh a2) {
        return a2.ALLATORIxDEMO().ALLATORIxDEMO(a2.x());
    }
}

