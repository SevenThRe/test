/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.bt;
import eos.moe.dragoncore.cz;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xn;
import eos.moe.dragoncore.xs;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class pr
extends xn {
    private final cz ALLATORIxDEMO;

    public pr(cz a2) {
        pr a3;
        a3.ALLATORIxDEMO = a2;
    }

    @Override
    public v ALLATORIxDEMO(String a2) {
        pr a3;
        return a3.ALLATORIxDEMO(a2, bt.k);
    }

    @Override
    public v ALLATORIxDEMO(String a2, bt a3) {
        pr a4;
        if (a4.ALLATORIxDEMO == null) {
            return a4;
        }
        return xs.ALLATORIxDEMO(a4.ALLATORIxDEMO.c, a4, a2);
    }

    @Override
    public boolean ALLATORIxDEMO(String a2, v a3) {
        pr a4;
        if (a4.ALLATORIxDEMO == null) {
            return false;
        }
        xs.ALLATORIxDEMO(a4.ALLATORIxDEMO.c, a4.ALLATORIxDEMO, a2, a3);
        return true;
    }

    public cz c() {
        pr a2;
        return a2.ALLATORIxDEMO;
    }

    @Override
    public cz ALLATORIxDEMO() {
        pr a2;
        return a2.ALLATORIxDEMO;
    }

    @Override
    public String ALLATORIxDEMO() {
        return "tagcomponent";
    }
}

