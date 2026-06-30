/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import java.util.LinkedHashMap;
import java.util.Map;

public class lo<K, V>
extends LinkedHashMap<K, V> {
    private final int ALLATORIxDEMO;

    public lo(int a2) {
        super(a2, 0.75f, true);
        lo a3;
        a3.ALLATORIxDEMO = a2;
    }

    @Override
    public boolean removeEldestEntry(Map.Entry<K, V> a2) {
        lo a3;
        return a3.size() > a3.ALLATORIxDEMO;
    }
}

