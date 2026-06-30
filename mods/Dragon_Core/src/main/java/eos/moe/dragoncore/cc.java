/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ec;
import eos.moe.dragoncore.ta;
import eos.moe.dragoncore.wc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class cc {
    private List<ec> y = new ArrayList<ec>();
    private Map<String, ec> k = new HashMap<String, ec>();
    private wc ALLATORIxDEMO = new wc();

    public cc() {
        cc a2;
    }

    public List<ec> ALLATORIxDEMO() {
        cc a2;
        return a2.y;
    }

    public void ALLATORIxDEMO(List<ec> a2) {
        a4.y = a2;
        a4.k = new HashMap<String, ec>();
        for (ec a3 : a2) {
            cc a4;
            String a5 = a3.ALLATORIxDEMO();
            if (!ta.ALLATORIxDEMO(a5)) continue;
            a4.k.put(a5.toLowerCase(Locale.ROOT), a3);
        }
    }

    public Map<String, ec> ALLATORIxDEMO() {
        cc a2;
        return a2.k;
    }

    public wc ALLATORIxDEMO() {
        cc a2;
        return a2.ALLATORIxDEMO;
    }

    public void ALLATORIxDEMO(wc a2) {
        a.ALLATORIxDEMO = a2;
    }
}

