/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 */
package eos.moe.dragoncore.api;

import eos.moe.dragoncore.api.model.AnimationManager;
import eos.moe.dragoncore.dga;
import eos.moe.dragoncore.raa;
import java.util.UUID;
import net.minecraft.entity.EntityLivingBase;

public class CoreAPI {
    public CoreAPI() {
        CoreAPI a2;
    }

    public static AnimationManager getAnimationManager(UUID a2) {
        return dga.y.ALLATORIxDEMO(a2);
    }

    public static boolean isModelReplace(EntityLivingBase a2) {
        return raa.r.c(a2) != null;
    }
}

