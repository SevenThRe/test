/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.kca;
import java.util.concurrent.ConcurrentHashMap;

public class oga {
    public static oga k = new oga();
    private ConcurrentHashMap<Character, kca> ALLATORIxDEMO = new ConcurrentHashMap();

    public oga() {
        oga a2;
    }

    public ConcurrentHashMap<Character, kca> ALLATORIxDEMO() {
        oga a2;
        return a2.ALLATORIxDEMO;
    }

    public kca ALLATORIxDEMO(char a2) {
        oga a3;
        return a3.ALLATORIxDEMO.get(Character.valueOf(a2));
    }
}

