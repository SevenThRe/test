/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package eos.moe.dragoncore;

import com.google.common.collect.Lists;
import eos.moe.dragoncore.qg;
import eos.moe.dragoncore.v;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class pt {
    public pt() {
        pt a2;
    }

    public static List<v> c(v ... a2) {
        ArrayList a3 = Lists.newArrayList();
        pt.c(a3, a2);
        return a3;
    }

    private static /* synthetic */ void c(List<v> a2, v ... a3) {
        for (v a4 : a3) {
            if (a4 instanceof qg) {
                qg a5 = (qg)a4;
                a2.addAll(a5.ALLATORIxDEMO());
                continue;
            }
            a2.add(a4);
        }
    }

    public static List<String> ALLATORIxDEMO(v ... a2) {
        ArrayList a3 = Lists.newArrayList();
        pt.ALLATORIxDEMO(a3, a2);
        return a3;
    }

    private static /* synthetic */ void ALLATORIxDEMO(List<String> a2, v ... a3) {
        for (v a4 : a3) {
            if (a4 instanceof qg) {
                qg a5 = (qg)a4;
                a2.addAll(a5.ALLATORIxDEMO().stream().map(v::c).collect(Collectors.toList()));
                continue;
            }
            a2.add(a4.c());
        }
    }
}

