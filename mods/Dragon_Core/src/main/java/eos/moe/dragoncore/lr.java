/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.bt;
import eos.moe.dragoncore.ca;
import eos.moe.dragoncore.m;
import eos.moe.dragoncore.pf;
import eos.moe.dragoncore.v;
import java.util.Map;
import java.util.function.Function;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class lr
implements m {
    private String k;
    private final Map<String, Function<bt, Object>> ALLATORIxDEMO;

    public lr(String a2, Map<String, Function<bt, Object>> a3) {
        lr a4;
        a4.k = a2;
        a4.ALLATORIxDEMO = a3;
    }

    public lr(Map<String, Function<bt, Object>> a2) {
        lr a3;
        a3.ALLATORIxDEMO = a2;
    }

    @Override
    public v ALLATORIxDEMO(String a2, bt a3) {
        lr a4;
        if (a4.ALLATORIxDEMO.containsKey(a2)) {
            return v.ALLATORIxDEMO(a4.ALLATORIxDEMO.get(a2).apply(a3));
        }
        if (a4.k != null) {
            ca.l.z("\u914d\u7f6e\u6587\u4ef6[" + a4.k + "]\u5b58\u5728\u9519\u8bef\u7684\u65b9\u6cd5\u540d: " + a2);
        } else {
            ca.l.z("\u9519\u8bef\u7684\u65b9\u6cd5\u540d: " + a2);
        }
        return pf.y;
    }

    @Override
    public void ALLATORIxDEMO(String a2, v a3) {
        throw new RuntimeException("Cannot set a value in query struct");
    }

    @Override
    public void ALLATORIxDEMO() {
        lr a2;
        a2.ALLATORIxDEMO.clear();
    }

    @Override
    public Map<String, Function<bt, Object>> ALLATORIxDEMO() {
        lr a2;
        return a2.ALLATORIxDEMO;
    }

    @Override
    public String ALLATORIxDEMO() {
        return "query";
    }
}

