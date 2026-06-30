/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.api.model.AnimationEntityModel;
import eos.moe.dragoncore.dq;
import eos.moe.dragoncore.fw;
import eos.moe.dragoncore.ga;
import eos.moe.dragoncore.hr;
import eos.moe.dragoncore.kq;
import java.util.Objects;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class xr {
    public final fw b;
    public final float o;
    private boolean y = false;
    public kq k;
    private final dq ALLATORIxDEMO;

    public xr(dq a2) {
        a3(a2.ALLATORIxDEMO(), a2.ALLATORIxDEMO(), a2.ALLATORIxDEMO());
        xr a3;
    }

    public xr(kq a2, float a3, dq a4) {
        xr a5;
        ga.ALLATORIxDEMO(a3, 0.0f);
        a5.b = new fw(System.currentTimeMillis());
        a5.k = a2;
        a5.o = a3;
        a5.ALLATORIxDEMO = a4;
    }

    public boolean c() {
        xr a2;
        return !a2.y;
    }

    public void ALLATORIxDEMO(AnimationEntityModel a2) {
        a.y = true;
    }

    public boolean ALLATORIxDEMO() {
        xr a2;
        return a2.ALLATORIxDEMO().ALLATORIxDEMO() || a2.ALLATORIxDEMO().f();
    }

    public xr ALLATORIxDEMO() {
        xr a2;
        if (a2.ALLATORIxDEMO().ALLATORIxDEMO()) {
            return a2;
        }
        if (a2.ALLATORIxDEMO().f()) {
            a2.f();
            return a2;
        }
        if (a2.ALLATORIxDEMO != null) {
            if (a2.ALLATORIxDEMO.ALLATORIxDEMO() == null) {
                return null;
            }
            if (a2.ALLATORIxDEMO.ALLATORIxDEMO() > 0) {
                return new hr(a2.ALLATORIxDEMO(), a2.c(), a2.ALLATORIxDEMO.ALLATORIxDEMO(), a2.ALLATORIxDEMO);
            }
            return new xr(a2.ALLATORIxDEMO);
        }
        return null;
    }

    public boolean ALLATORIxDEMO(long a2) {
        xr a3;
        return a2 > a3.b.ALLATORIxDEMO() + (long)Math.round((float)a3.k.ALLATORIxDEMO() / a3.o);
    }

    public void f() {
        xr a2;
        a2.b.ALLATORIxDEMO(System.currentTimeMillis());
    }

    public kq ALLATORIxDEMO() {
        xr a2;
        return a2.k;
    }

    public int ALLATORIxDEMO(long a2) {
        xr a3;
        return (int)((float)((int)(a2 - a3.b.ALLATORIxDEMO())) * a3.o);
    }

    public int c() {
        xr a2;
        return a2.ALLATORIxDEMO(System.currentTimeMillis());
    }

    public void c() {
        xr a2;
        a2.b.c();
    }

    public void ALLATORIxDEMO() {
        xr a2;
        a2.b.ALLATORIxDEMO();
    }

    public int ALLATORIxDEMO() {
        xr a2;
        return a2.ALLATORIxDEMO().ALLATORIxDEMO();
    }

    public String toString() {
        xr a2;
        return "AnimationWatcher {animation=" + a2.k + ", existingTime=" + a2.c() + ", speed=" + a2.o + ", inited=" + a2.y + '}';
    }

    public boolean equals(Object a2) {
        xr a3;
        if (a3 == a2) {
            return true;
        }
        if (!(a2 instanceof xr)) {
            return false;
        }
        xr a4 = (xr)a2;
        return Float.compare(a4.o, a3.o) == 0 && a3.k.equals(a4.k);
    }

    public int hashCode() {
        xr a2;
        return Objects.hash(Float.valueOf(a2.o), a2.k);
    }
}

