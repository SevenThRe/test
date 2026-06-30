/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.fq;
import eos.moe.dragoncore.hq;
import eos.moe.dragoncore.jv;
import eos.moe.dragoncore.kq;
import eos.moe.dragoncore.so;
import eos.moe.dragoncore.st;
import eos.moe.dragoncore.ww;
import eos.moe.dragoncore.xz;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.model.ModelBase;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ny {
    private String b;
    private ModelBase o;
    private Map<String, kq> y;
    private String k;
    private Map<String, xz> ALLATORIxDEMO;

    public ny(ModelBase a2) {
        ny a3;
        a3.o = a2;
    }

    public void ALLATORIxDEMO(String a2, Map<String, kq> a3, String a4) {
        a.b = a2;
        a.y = a3;
        a.k = a4;
        a.ALLATORIxDEMO = new HashMap<String, xz>();
    }

    public ModelBase ALLATORIxDEMO() {
        ny a2;
        return a2.o;
    }

    public Map<String, kq> ALLATORIxDEMO() {
        ny a2;
        return a2.y;
    }

    public String ALLATORIxDEMO() {
        ny a2;
        return a2.k;
    }

    private /* synthetic */ void f(String a2) {
        ny a3;
        if (!a3.ALLATORIxDEMO.containsKey(a2)) {
            fq a4 = fq.c();
            a4.ALLATORIxDEMO();
            a4.ALLATORIxDEMO(a3.y);
            a3.ALLATORIxDEMO.put(a2, a4.ALLATORIxDEMO());
        }
    }

    public void c(String a2) {
        ny a3;
        if (a3.y == null) {
            return;
        }
        a3.f(a2);
        xz a4 = a3.ALLATORIxDEMO.get(a2);
        if (!ny.ALLATORIxDEMO(a4)) {
            a3.ALLATORIxDEMO(a2, "idle", 1.0f);
        }
    }

    public void ALLATORIxDEMO(String a2, String a3, float a4) {
        ny a5;
        if (a5.y == null) {
            return;
        }
        a5.f(a2);
        xz a6 = a5.ALLATORIxDEMO.get(a2);
        kq a7 = a5.y.get(a3);
        if (a7 != null) {
            st a8 = new st(a7, 0);
            a8.ALLATORIxDEMO(a4);
            if (a5.y.containsKey("idle")) {
                kq a9 = a5.y.get("idle");
                st a10 = new st(a9, 0);
                a8.ALLATORIxDEMO(a10);
            }
            a6.ALLATORIxDEMO(a8, "base");
        }
    }

    public void ALLATORIxDEMO(String a2) {
        ny a3;
        if (a3.o instanceof hq) {
            ((hq)a3.o).setAnimation("idle");
        } else if (a3.o instanceof jv) {
            if (a3.y == null) {
                return;
            }
            xz a4 = a3.ALLATORIxDEMO.get(a2);
            a4.ALLATORIxDEMO((jv)a3.o);
        }
    }

    public static boolean ALLATORIxDEMO(xz a2) {
        so a3 = a2.ALLATORIxDEMO().get("base");
        return a3 != null && a3.ALLATORIxDEMO() != null;
    }

    public void ALLATORIxDEMO() {
        ny a2;
        ww.ALLATORIxDEMO("models/items/" + a2.b + "/texture.png");
    }

    public boolean ALLATORIxDEMO() {
        ny a2;
        if (a2.k == null) {
            return false;
        }
        ww.ALLATORIxDEMO("models/items/" + a2.b + "/" + a2.k);
        return true;
    }
}

