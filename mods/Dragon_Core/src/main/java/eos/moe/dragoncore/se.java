/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.p;
import eos.moe.dragoncore.tn;
import eos.moe.dragoncore.v;

public class se
extends tn {
    private final String ALLATORIxDEMO;

    public se(String a2, nh a3, p a4) {
        super(a3, a4);
        se a5;
        a5.ALLATORIxDEMO = a2;
    }

    @Override
    public v c() {
        se a2;
        v a3 = a2.k.getAnimationValue(a2.ALLATORIxDEMO);
        if (a3 != null) {
            return a3;
        }
        return super.c();
    }
}

