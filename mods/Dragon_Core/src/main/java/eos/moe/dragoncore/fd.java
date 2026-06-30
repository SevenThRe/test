/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.in;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.util.ResourceLocation;

public class fd {
    private static final Map<String, in> ALLATORIxDEMO = new HashMap<String, in>();

    public fd() {
        fd a2;
    }

    public static in ALLATORIxDEMO(String a2) {
        if (!ALLATORIxDEMO.containsKey(a2)) {
            ALLATORIxDEMO.put(a2, new in(new ResourceLocation("dragoncore", a2), 100, 0.15f));
        }
        return ALLATORIxDEMO.get(a2);
    }

    public static void ALLATORIxDEMO() {
        ALLATORIxDEMO.clear();
    }
}

