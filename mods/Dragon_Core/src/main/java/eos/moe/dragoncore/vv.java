/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.hl;
import eos.moe.dragoncore.v;
import java.util.Map;

public class vv
extends hl {
    public vv() {
        vv a2;
    }

    public vv(Map<String, v> a2) {
        super(a2);
        vv a3;
    }

    @Override
    public void ALLATORIxDEMO(String a2, v a3) {
        throw new RuntimeException("Tried to set a value in read-only context struct");
    }

    @Override
    public String ALLATORIxDEMO() {
        return "context";
    }
}

