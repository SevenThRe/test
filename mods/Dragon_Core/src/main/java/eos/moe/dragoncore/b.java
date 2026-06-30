/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.br;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xn;
import java.util.HashMap;
import java.util.Map;

public interface b {
    public static final Map<String, Object> ALLATORIxDEMO = new HashMap<String, Object>();

    default public Map<String, Object> ALLATORIxDEMO() {
        return ALLATORIxDEMO;
    }

    public v ALLATORIxDEMO(br var1, xn var2);

    default public void ALLATORIxDEMO(br a2, xn a3, v a4) {
        b a5;
        new RuntimeException("Cannot assign a value to " + a5.getClass()).printStackTrace();
    }
}

