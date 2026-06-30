/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.bax;
import eos.moe.dragoncore.u;

public class dg
extends bax {
    private final u o;
    private final u y;
    private final u k;
    private boolean ALLATORIxDEMO;

    public dg(u a2, u a3, u a4) {
        dg a5;
        a5.o = a2;
        a5.y = a3;
        a5.k = a4;
    }

    @Override
    public void reverseY() {
        a.ALLATORIxDEMO = true;
    }

    @Override
    public float getX() {
        dg a2;
        return (float)a2.o.ALLATORIxDEMO();
    }

    @Override
    public float getY() {
        dg a2;
        return a2.ALLATORIxDEMO ? -((float)a2.y.ALLATORIxDEMO()) : (float)a2.y.ALLATORIxDEMO();
    }

    @Override
    public float getZ() {
        dg a2;
        return (float)a2.k.ALLATORIxDEMO();
    }

    @Override
    public String toString() {
        dg a2;
        return "ValueVector3f{IValue_1=" + a2.o.ALLATORIxDEMO() + ", IValue_2=" + a2.y.ALLATORIxDEMO() + ", IValue_3=" + a2.k.ALLATORIxDEMO() + ", reverseY=" + a2.ALLATORIxDEMO + '}';
    }
}

