/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.bd;
import eos.moe.dragoncore.bt;
import eos.moe.dragoncore.p;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xn;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class pe
extends xn {
    private final p ALLATORIxDEMO;

    public pe(p a2) {
        pe a3;
        a3.ALLATORIxDEMO = a2;
    }

    @Override
    public v ALLATORIxDEMO(String a2) {
        pe a3;
        return a3.ALLATORIxDEMO(a2, bt.k);
    }

    @Override
    public v ALLATORIxDEMO(String a2, bt a3) {
        pe a4;
        if (a4.ALLATORIxDEMO == null) {
            return a4;
        }
        return bd.ALLATORIxDEMO(a4.ALLATORIxDEMO.getManager(), (v)a4, a2);
    }

    @Override
    public boolean ALLATORIxDEMO(String a2, v a3) {
        pe a4;
        if (a4.ALLATORIxDEMO == null) {
            return false;
        }
        bd.ALLATORIxDEMO(a4.ALLATORIxDEMO.getManager(), a4.ALLATORIxDEMO, a2, a3);
        return true;
    }

    public p c() {
        pe a2;
        return a2.ALLATORIxDEMO;
    }

    @Override
    public p ALLATORIxDEMO() {
        pe a2;
        return a2.ALLATORIxDEMO;
    }

    @Override
    public String ALLATORIxDEMO() {
        return "component";
    }
}

