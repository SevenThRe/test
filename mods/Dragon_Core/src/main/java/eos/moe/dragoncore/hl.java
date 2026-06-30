/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.bt;
import eos.moe.dragoncore.m;
import eos.moe.dragoncore.pf;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xn;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class hl
implements m {
    private Map<String, v> ALLATORIxDEMO;

    public hl() {
        hl a2;
        a2.ALLATORIxDEMO = new HashMap<String, v>();
    }

    public hl(Map<String, v> a2) {
        hl a3;
        a3.ALLATORIxDEMO = a2;
    }

    @Override
    public void ALLATORIxDEMO(String a2, v a3) {
        hl a4;
        LinkedList<String> a5 = new LinkedList<String>(Arrays.asList(a2.split("\\.")));
        String a6 = a5.poll();
        if (a5.size() > 0 && a6 != null) {
            v a7 = a4.ALLATORIxDEMO(a6, bt.k);
            if (a7 instanceof xn) {
                ((xn)a7).ALLATORIxDEMO(String.join((CharSequence)".", a5), a3);
                return;
            }
            if (!(a7 instanceof m)) {
                a7 = new hl();
            }
            String a8 = a5.poll();
            ((m)a7).ALLATORIxDEMO(a8, a3);
            a4.ALLATORIxDEMO.put(a6, (m)a7);
        } else {
            a4.ALLATORIxDEMO.put(a2, a3);
        }
    }

    @Override
    public v ALLATORIxDEMO(String a2, bt a3) {
        hl a4;
        LinkedList<String> a5 = new LinkedList<String>(Arrays.asList(a2.split("\\.")));
        String a6 = a5.poll();
        if (a5.size() > 0 && a6 != null) {
            v a7 = a4.ALLATORIxDEMO.get(a6);
            if (a7 instanceof xn) {
                return ((xn)a7).ALLATORIxDEMO(a5.poll());
            }
            if (a7 instanceof m) {
                return ((m)a7).ALLATORIxDEMO(a5.poll(), bt.k);
            }
        }
        return a4.ALLATORIxDEMO.getOrDefault(a2, pf.y);
    }

    public void c(String a2) {
        hl a3;
        a3.ALLATORIxDEMO.remove(a2);
    }

    public void ALLATORIxDEMO(String a2) {
        hl a4;
        a4.ALLATORIxDEMO.entrySet().removeIf(a3 -> ((String)a3.getKey()).startsWith(a2));
    }

    @Override
    public void ALLATORIxDEMO() {
        hl a2;
        a2.ALLATORIxDEMO.clear();
    }

    @Override
    public Map<String, v> ALLATORIxDEMO() {
        hl a2;
        return a2.ALLATORIxDEMO;
    }

    @Override
    public Collection<v> ALLATORIxDEMO() {
        hl a2;
        return a2.ALLATORIxDEMO.values();
    }

    public void ALLATORIxDEMO(Map<String, v> a2) {
        a.ALLATORIxDEMO = a2;
    }

    @Override
    public String ALLATORIxDEMO() {
        return "variable";
    }
}

