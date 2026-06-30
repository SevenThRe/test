/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.cz;
import eos.moe.dragoncore.i;
import eos.moe.dragoncore.kh;
import eos.moe.dragoncore.kp;
import eos.moe.dragoncore.pr;
import eos.moe.dragoncore.qg;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xf;
import java.util.stream.Collectors;

public class xs {
    public xs() {
        xs a2;
    }

    @i(f={"\u53d6\u7ec4\u4ef6", "Component_Find"})
    public static pr ALLATORIxDEMO(kp a2, String a3) {
        return new pr(a2.ALLATORIxDEMO().get(a3));
    }

    @i(f={"\u53d6\u7ec4\u4ef6\u503c", "Component_Get"})
    public static v ALLATORIxDEMO(kp a2, v a3, String a4) {
        Object a5 = a3 instanceof pr ? a3.ALLATORIxDEMO() : a2.ALLATORIxDEMO().get(a3.c());
        if (a5 != null) {
            Object a6 = ((cz)a5).ALLATORIxDEMO(a4);
            if (a6 instanceof String) {
                return new xf((String)a6);
            }
            return (v)a6;
        }
        return xf.y;
    }

    @i(f={"\u53d6\u6240\u6709\u7ec4\u4ef6", "Component_FindAll"})
    public static qg ALLATORIxDEMO(kp a2) {
        return new qg(a2.ALLATORIxDEMO().values().stream().map(pr::new).collect(Collectors.toList()));
    }

    @i(f={"\u8bbe\u7f6e\u7ec4\u4ef6\u503c", "\u7f6e\u7ec4\u4ef6\u503c", "Component_Set"})
    public static void ALLATORIxDEMO(kp a2, v a3, String a4, v ... a5) {
        Object a6 = a3 instanceof pr ? a3.ALLATORIxDEMO() : a2.ALLATORIxDEMO().get(a3.c());
        if (a6 != null && a5.length >= 1) {
            xs.ALLATORIxDEMO(a2, (cz)a6, a4, a5);
        }
    }

    public static void ALLATORIxDEMO(kp a2, cz a3, String a4, v ... a5) {
        a4 = kh.ALLATORIxDEMO(a4);
        String a6 = String.valueOf(System.currentTimeMillis());
        String a7 = a5[0].c().replace("%time%", a6);
        a3.ALLATORIxDEMO(a4, a7);
    }
}

