/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.hl;
import eos.moe.dragoncore.v;
import java.util.Map;

public class vp
extends hl {
    public vp() {
        vp a2;
    }

    public vp(Map<String, v> a2) {
        super(a2);
        vp a3;
    }

    @Override
    public void ALLATORIxDEMO(String a2, v a3) {
        vp a4;
        CharSequence[] a5 = a2.split("\\.");
        a5[a5.length - 1] = String.valueOf(Integer.parseInt(a5[a5.length - 1]));
        super.ALLATORIxDEMO(String.join((CharSequence)".", a5), a3);
    }

    @Override
    public String ALLATORIxDEMO() {
        return "array";
    }
}

