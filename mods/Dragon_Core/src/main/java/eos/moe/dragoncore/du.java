/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.b;
import eos.moe.dragoncore.c;
import eos.moe.dragoncore.cx;
import eos.moe.dragoncore.ep;
import eos.moe.dragoncore.ez;
import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.vx;
import java.util.ArrayList;

public class du
implements c {
    public du() {
        du a2;
    }

    @Override
    public b ALLATORIxDEMO(nh a2, vx a3) {
        ArrayList<b> a4 = new ArrayList<b>();
        if (!a2.ALLATORIxDEMO(ez.ua)) {
            while (!a2.ALLATORIxDEMO(ez.ua, false)) {
                a4.add(a2.c(ep.z));
                if (a2.ALLATORIxDEMO(ez.w)) continue;
            }
            a2.ALLATORIxDEMO(ez.ua);
        }
        return new cx(a4.toArray(new b[0]));
    }
}

