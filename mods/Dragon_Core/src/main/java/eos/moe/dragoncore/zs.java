/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.kq;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.util.ResourceLocation;

public class zs {
    private static final Map<ResourceLocation, kq> ALLATORIxDEMO = new HashMap<ResourceLocation, kq>();

    public zs() {
        zs a2;
    }

    public static kq ALLATORIxDEMO(kq a2) {
        if (ALLATORIxDEMO.put(a2.ALLATORIxDEMO(), a2) != null) {
            // empty if block
        }
        return a2;
    }

    public static kq ALLATORIxDEMO(ResourceLocation a2) {
        return ALLATORIxDEMO.get(a2);
    }
}

