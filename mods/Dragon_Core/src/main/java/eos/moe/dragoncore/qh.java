/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.mh;
import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.ui;
import eos.moe.dragoncore.v;

public class qh
extends mh {
    private final ui k;
    private final String ALLATORIxDEMO;

    public qh(ui a2, String a3, nh a4) {
        super(a4);
        qh a5;
        a5.k = a2;
        a5.ALLATORIxDEMO = a3;
    }

    @Override
    public double c() {
        qh a2;
        v a3 = a2.k.getAnimationValue(a2.ALLATORIxDEMO);
        if (a3 != null) {
            return a3.ALLATORIxDEMO();
        }
        return super.c();
    }
}

