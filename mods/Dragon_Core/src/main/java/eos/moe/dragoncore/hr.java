/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.api.model.AnimationEntityModel;
import eos.moe.dragoncore.dq;
import eos.moe.dragoncore.ga;
import eos.moe.dragoncore.kq;
import eos.moe.dragoncore.lu;
import eos.moe.dragoncore.xr;
import java.util.Objects;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class hr
extends xr {
    private final int b;
    private final dq o;
    private final kq y;
    private final int ALLATORIxDEMO;

    public hr(int a2, dq a3) {
        a4(null, 0, a2, a3);
        hr a4;
    }

    public hr(kq a2, int a3, int a4, dq a5) {
        super(null, 1.0f, a5);
        hr a6;
        ga.x(a4, 0);
        a6.b = a4;
        a6.o = a5;
        a6.y = a2;
        a6.ALLATORIxDEMO = a3;
        a6.k = lu.ALLATORIxDEMO(a2, a6.c(), a4);
    }

    @Override
    public void ALLATORIxDEMO(AnimationEntityModel a2) {
        hr a3;
        super.ALLATORIxDEMO(a2);
        a3.k = a2 != null ? lu.ALLATORIxDEMO(a3.y, a3.ALLATORIxDEMO, a3.c(), a2.getBaseModel(), a3.b) : lu.ALLATORIxDEMO(a3.y, a3.c(), a3.b);
    }

    @Override
    public xr ALLATORIxDEMO() {
        hr a2;
        return a2.o != null ? new xr(a2.o) : null;
    }

    public kq c() {
        hr a2;
        return a2.o != null ? a2.o.ALLATORIxDEMO() : null;
    }

    @Override
    public int ALLATORIxDEMO() {
        hr a2;
        return a2.b;
    }

    @Override
    public boolean equals(Object a2) {
        hr a3;
        if (a3 == a2) {
            return true;
        }
        if (!(a2 instanceof hr)) {
            return false;
        }
        hr a4 = (hr)a2;
        boolean a5 = false;
        if (a3.o == null && a4.o == null) {
            a5 = true;
        } else if (a3.o != null && a4.o != null && a3.o.ALLATORIxDEMO().equals(a4.o.ALLATORIxDEMO()) && Float.compare(a3.o.ALLATORIxDEMO(), a4.o.ALLATORIxDEMO()) == 0) {
            a5 = true;
        }
        return a5 && a3.b == a4.b && Objects.equals(a3.y, a4.y);
    }

    @Override
    public int hashCode() {
        hr a2;
        kq a3 = a2.c();
        float a4 = a2.o != null ? a2.o.ALLATORIxDEMO() : 0.0f;
        return Objects.hash(super.hashCode(), a2.b, a3, Float.valueOf(a4), a2.y);
    }
}

