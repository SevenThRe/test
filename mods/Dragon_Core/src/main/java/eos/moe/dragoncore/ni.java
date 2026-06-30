/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ke;
import eos.moe.dragoncore.kn;
import eos.moe.dragoncore.ol;
import eos.moe.dragoncore.qd;
import eos.moe.dragoncore.sj;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xf;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ni {
    public static Map<String, Integer> ALLATORIxDEMO = new ConcurrentHashMap<String, Integer>();

    public ni() {
        ni a2;
    }

    public static qd<Double, Double> ALLATORIxDEMO(ke a2, int a3) {
        if (a3 == 1) {
            return qd.ALLATORIxDEMO((double)ni.ALLATORIxDEMO(a2).size() * a2.b.c() * a2.ba.c(), 0.0);
        }
        List<String> a4 = ni.c(a2);
        qd<Double, Double> a5 = new qd<Double, Double>(0.0, 0.0);
        if (a3 == 0 || a3 == 2) {
            String a6 = a2.oa.c();
            double a7 = a2.ba.c();
            String a8 = String.join((CharSequence)"\n", a4) + "<->" + a6;
            if (ALLATORIxDEMO.containsKey(a8)) {
                a5.c((double)ALLATORIxDEMO.get(a8).intValue() * a7);
            } else {
                int a9 = 0;
                for (String a10 : a4) {
                    a9 = Math.max(a9, ol.ALLATORIxDEMO(a10, a6, false));
                }
                ALLATORIxDEMO.put(a8, a9);
                a5.c((double)a9 * a7);
            }
        }
        if (a3 == 2) {
            a5.ALLATORIxDEMO((double)ni.ALLATORIxDEMO(a2).size() * a2.b.c() * a2.ba.c());
        }
        return a5;
    }

    public static List<String> c(ke a3) {
        boolean a4 = a3.q.c();
        List<String> a5 = ni.ALLATORIxDEMO(a3).stream().map(v::c).collect(Collectors.toList());
        if (a4) {
            a5.replaceAll(a2 -> a2.replace("&", "\u00a7"));
        }
        return a5;
    }

    public static List<v> ALLATORIxDEMO(ke a2) {
        String a3 = a2.oa.c();
        List<v> a4 = a2.fa.z();
        double a5 = a2.ka.c();
        ArrayList<v> a6 = new ArrayList<v>();
        for (v a7 : a4) {
            if (a7 instanceof kn) {
                a6.addAll(sj.ALLATORIxDEMO(a7.ALLATORIxDEMO(), (int)a5, a3).stream().map(kn::new).collect(Collectors.toList()));
                continue;
            }
            a6.addAll(sj.ALLATORIxDEMO(a7.c(), (int)a5, a3).stream().map(xf::new).collect(Collectors.toList()));
        }
        return a6;
    }
}

