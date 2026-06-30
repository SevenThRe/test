/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.fq;
import eos.moe.dragoncore.jv;
import eos.moe.dragoncore.kq;
import eos.moe.dragoncore.so;
import eos.moe.dragoncore.st;
import eos.moe.dragoncore.xz;
import java.util.HashMap;
import java.util.Map;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ox {
    private final jv y;
    private final Map<String, kq> k;
    private final Map<String, xz> ALLATORIxDEMO;

    public ox(jv a2, Map<String, kq> a3) {
        ox a4;
        a4.y = a2;
        a4.k = a3;
        a4.ALLATORIxDEMO = new HashMap<String, xz>();
    }

    public jv ALLATORIxDEMO() {
        ox a2;
        return a2.y;
    }

    public Map<String, kq> ALLATORIxDEMO() {
        ox a2;
        return a2.k;
    }

    public void c() {
        ox a2;
        a2.y.render(0.0625f);
    }

    public void ALLATORIxDEMO() {
        ox a2;
        a2.y.clearData();
    }

    private /* synthetic */ void f(String a2) {
        ox a3;
        if (!a3.ALLATORIxDEMO.containsKey(a2)) {
            fq a4 = fq.c();
            a4.ALLATORIxDEMO();
            a4.ALLATORIxDEMO(a3.k);
            a3.ALLATORIxDEMO.put(a2, a4.ALLATORIxDEMO());
        }
    }

    public void c(String a2) {
        ox a3;
        if (a3.k == null) {
            return;
        }
        a3.f(a2);
        xz a4 = a3.ALLATORIxDEMO.get(a2);
        if (!ox.ALLATORIxDEMO(a4)) {
            a3.ALLATORIxDEMO(a2, "idle");
        }
    }

    public void ALLATORIxDEMO(String a2, String a3) {
        ox a4;
        if (a4.k == null) {
            return;
        }
        a4.f(a2);
        xz a5 = a4.ALLATORIxDEMO.get(a2);
        kq a6 = a4.k.get(a3);
        if (a6 != null) {
            st a7 = new st(a6, 0);
            if (a4.k.containsKey("idle")) {
                kq a8 = a4.k.get("idle");
                st a9 = new st(a8, 0);
                a7.ALLATORIxDEMO(a9);
            }
            a5.ALLATORIxDEMO(a7, "base");
        }
    }

    public void ALLATORIxDEMO(String a2) {
        ox a3;
        if (a3.k == null) {
            return;
        }
        xz a4 = a3.ALLATORIxDEMO.get(a2);
        a4.ALLATORIxDEMO(a3.y);
    }

    public static boolean ALLATORIxDEMO(xz a2) {
        so a3 = a2.ALLATORIxDEMO().get("base");
        return a3 != null && a3.ALLATORIxDEMO() != null;
    }
}

