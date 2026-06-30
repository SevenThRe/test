/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.bn;
import eos.moe.dragoncore.i;
import eos.moe.dragoncore.ml;
import eos.moe.dragoncore.n;
import eos.moe.dragoncore.pe;
import eos.moe.dragoncore.r;
import eos.moe.dragoncore.rj;
import eos.moe.dragoncore.ui;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.vi;
import java.util.Locale;

public class lg {
    public lg() {
        lg a2;
    }

    @i(f={"\u64ad\u653e\u52a8\u753b", "PlayAnimation"})
    public static void ALLATORIxDEMO(ui a2, v ... a3) {
        String a4;
        int a5 = 0;
        Object a6 = null;
        if (a3.length == 9) {
            if (a3[0] instanceof pe) {
                a6 = a3[0].ALLATORIxDEMO();
            } else {
                a4 = a3[0].c();
                a6 = a2.findComponent(a4);
            }
            ++a5;
        } else if (a3.length == 8) {
            a6 = a2;
            a4 = a3[0].c();
            if (a4.contains(".")) {
                a6 = new vi(a4, a2);
            }
        }
        if (a6 == null) {
            return;
        }
        a4 = a3[a5++].c();
        double a7 = a3[a5++].ALLATORIxDEMO();
        double a8 = a3[a5++].ALLATORIxDEMO();
        int a9 = (int)a3[a5++].ALLATORIxDEMO();
        boolean a10 = a3[a5++].ALLATORIxDEMO();
        int a11 = (int)a3[a5++].ALLATORIxDEMO();
        int a12 = (int)a3[a5++].ALLATORIxDEMO();
        String a13 = a3[a5++].c();
        bn a14 = new bn(a4, (r)a6, new ml(a7, a8), a9, a10, a11, a12, n.ALLATORIxDEMO.getOrDefault(a13.toUpperCase(Locale.ROOT), n.b));
        rj.ALLATORIxDEMO().c(a14);
    }
}

