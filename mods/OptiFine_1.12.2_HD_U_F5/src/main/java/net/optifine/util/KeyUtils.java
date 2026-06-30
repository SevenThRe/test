/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bhy
 */
package net.optifine.util;

import java.util.Arrays;
import java.util.HashSet;

public class KeyUtils {
    public static void fixKeyConflicts(bhy[] keys, bhy[] keysPrio) {
        HashSet<Integer> keyPrioCodes = new HashSet<Integer>();
        for (int i = 0; i < keysPrio.length; ++i) {
            bhy keyPrio = keysPrio[i];
            keyPrioCodes.add(keyPrio.j());
        }
        HashSet<bhy> setKeys = new HashSet<bhy>(Arrays.asList(keys));
        setKeys.removeAll(Arrays.asList(keysPrio));
        for (bhy key : setKeys) {
            Integer code = key.j();
            if (!keyPrioCodes.contains(code)) continue;
            key.b(0);
        }
    }
}

