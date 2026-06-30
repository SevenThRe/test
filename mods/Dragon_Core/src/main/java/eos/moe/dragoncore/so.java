/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.api.model.AnimationEntityModel;
import eos.moe.dragoncore.do;
import eos.moe.dragoncore.dq;
import eos.moe.dragoncore.ea;
import eos.moe.dragoncore.hr;
import eos.moe.dragoncore.kq;
import eos.moe.dragoncore.kw;
import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.qw;
import eos.moe.dragoncore.rz;
import eos.moe.dragoncore.xr;
import java.util.HashSet;
import java.util.Set;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class so
implements ea {
    private String x;
    private xr v;
    private rz m;
    private nh c;
    private int q;
    private boolean b;
    private float o;
    private final Set<kw> y;
    private final Set<kw> k;
    private final Set<kw> ALLATORIxDEMO;

    public so(String a2, rz a3, float a4) {
        so a5;
        a5.y = new HashSet<kw>();
        a5.k = new HashSet<kw>();
        a5.ALLATORIxDEMO = new HashSet<kw>();
        a5.x = a2;
        a5.o = do.c(a4, 0.0f, 1.0f);
        a5.m = a3;
    }

    public so(String a2, rz a3, float a4, nh a5, int a6, boolean a7) {
        so a8;
        a8.y = new HashSet<kw>();
        a8.k = new HashSet<kw>();
        a8.ALLATORIxDEMO = new HashSet<kw>();
        a8.x = a2;
        a8.o = do.c(a4, 0.0f, 1.0f);
        a8.m = a3;
        a8.c = a5;
        a8.q = a6;
        a8.b = a7;
    }

    public void ALLATORIxDEMO(boolean a2) {
        a.b = a2;
    }

    public boolean c() {
        so a2;
        return a2.b;
    }

    @Override
    public float ALLATORIxDEMO() {
        so a2;
        return a2.o;
    }

    @Override
    public void ALLATORIxDEMO(float a2) {
        a.o = a2;
    }

    @Override
    public rz ALLATORIxDEMO() {
        so a2;
        return a2.m;
    }

    @Override
    public void ALLATORIxDEMO(rz a2) {
        a.m = a2;
    }

    @Override
    public kq ALLATORIxDEMO() {
        so a2;
        return a2.ALLATORIxDEMO() ? a2.ALLATORIxDEMO().ALLATORIxDEMO() : null;
    }

    @Override
    public boolean ALLATORIxDEMO() {
        so a2;
        return a2.ALLATORIxDEMO() != null && a2.ALLATORIxDEMO().ALLATORIxDEMO() != null;
    }

    @Override
    public boolean ALLATORIxDEMO(kw a2) {
        so a3;
        return a3.y.contains(a2) || a3.ALLATORIxDEMO.contains(a2);
    }

    @Override
    public void ALLATORIxDEMO(kw a2) {
        so a3;
        if (a3.y.contains(a2) || a3.ALLATORIxDEMO.contains(a2)) {
            return;
        }
        a3.ALLATORIxDEMO.add(a2);
    }

    public void ALLATORIxDEMO(dq a2) {
        so a3;
        a3.k.addAll(a3.y);
        a3.ALLATORIxDEMO.clear();
        a3.v = a2.ALLATORIxDEMO() <= 0 ? new xr(a2.ALLATORIxDEMO(), a2.ALLATORIxDEMO(), a2.ALLATORIxDEMO()) : (a3.v == null ? new hr(a2.ALLATORIxDEMO(), a2) : new hr(a3.v.ALLATORIxDEMO(), a3.v.c(), a2.ALLATORIxDEMO(), a2));
    }

    public void ALLATORIxDEMO(int a2, dq a3) {
        so a4;
        a4.k.addAll(a4.y);
        a4.ALLATORIxDEMO.clear();
        if (a4.v != null) {
            if (a2 <= 0) {
                a4.v = null;
            } else if (!(a4.v instanceof hr) || ((hr)a4.v).c() != null) {
                a4.v = new hr(a4.v.ALLATORIxDEMO(), a4.v.c(), a2, a3);
            }
        }
    }

    public void ALLATORIxDEMO(qw a2, AnimationEntityModel a3, long a4) {
        so a5;
        xr a6 = a5.ALLATORIxDEMO();
        if (a6 != null) {
            a6.ALLATORIxDEMO();
            if (a6.c()) {
                a6.ALLATORIxDEMO(a3);
            }
            if (a6.ALLATORIxDEMO(a4)) {
                if (!a6.ALLATORIxDEMO().ALLATORIxDEMO()) {
                    a2.ALLATORIxDEMO(a3, a5, a6);
                }
                if (a6 == a5.v) {
                    if ((a6 = a6.ALLATORIxDEMO()) != null && a6.c()) {
                        a6.ALLATORIxDEMO(a3);
                    }
                    a5.ALLATORIxDEMO(a6);
                }
            }
        }
    }

    public nh ALLATORIxDEMO() {
        so a2;
        return a2.c;
    }

    public int ALLATORIxDEMO() {
        so a2;
        return a2.q;
    }

    @Override
    public xr ALLATORIxDEMO() {
        so a2;
        return a2.v;
    }

    public void ALLATORIxDEMO(xr a2) {
        so a3;
        if (a3.v != a2) {
            a3.k.addAll(a3.y);
            a3.ALLATORIxDEMO.clear();
        }
        a3.v = a2;
    }

    @Override
    public String ALLATORIxDEMO() {
        so a2;
        return a2.x;
    }

    public so ALLATORIxDEMO() throws CloneNotSupportedException {
        so a2;
        if (a2.v != null) {
            throw new CloneNotSupportedException("clone\u5931\u8d25\uff0c\u5f53\u524d\u52a8\u753b\u5c42\u6b63\u5728\u64ad\u653e\u4e2d");
        }
        so a3 = (so)super.clone();
        a3.x = a2.x;
        a3.m = a2.m;
        a3.o = a2.o;
        return a3;
    }

    public Set<kw> f() {
        so a2;
        return a2.y;
    }

    public Set<kw> c() {
        so a2;
        return a2.k;
    }

    public Set<kw> ALLATORIxDEMO() {
        so a2;
        return a2.ALLATORIxDEMO;
    }
}

