/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.al;
import eos.moe.dragoncore.dj;
import eos.moe.dragoncore.zca;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.item.ItemStack;

public class uja {
    public static ConcurrentHashMap<String, zca> ALLATORIxDEMO = new ConcurrentHashMap();

    public uja() {
        uja a2;
    }

    public static zca ALLATORIxDEMO(ItemStack a2) {
        if (a2.isEmpty()) {
            return null;
        }
        String a3 = dj.ALLATORIxDEMO(a2, false, false);
        for (zca a4 : ALLATORIxDEMO.values()) {
            if (!al.ALLATORIxDEMO(zca.ALLATORIxDEMO(a4), a3)) continue;
            return a4;
        }
        return null;
    }
}

