/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.b;
import eos.moe.dragoncore.d;
import java.util.LinkedList;
import java.util.List;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ot
implements d {
    private final LinkedList<b> k = new LinkedList();
    private b ALLATORIxDEMO;

    public ot() {
        ot a2;
    }

    @Override
    public void c(List<b> a2) {
        ot a3;
        a3.k.clear();
        a3.ALLATORIxDEMO = null;
    }

    @Override
    public Object ALLATORIxDEMO(b a2) {
        ot a3;
        if (!a3.k.isEmpty()) {
            a2.ALLATORIxDEMO().put("parent", a3.k.getLast());
        }
        if (a3.ALLATORIxDEMO != null && a2.ALLATORIxDEMO().get("parent") == a3.ALLATORIxDEMO.ALLATORIxDEMO().get("parent")) {
            a2.ALLATORIxDEMO().put("previous", a3.ALLATORIxDEMO);
            a3.ALLATORIxDEMO.ALLATORIxDEMO().put("next", a2);
        }
        a3.k.add(a2);
        return null;
    }

    @Override
    public void ALLATORIxDEMO(b a2) {
        ot a3;
        a3.ALLATORIxDEMO = a2;
        a3.k.pollLast();
    }
}

