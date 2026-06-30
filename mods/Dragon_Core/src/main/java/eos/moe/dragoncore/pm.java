/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.EntityList;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class pm {
    public pm() {
        pm a2;
    }

    public static EntityLivingBase ALLATORIxDEMO(UUID a2) {
        if (a2 == null) {
            return null;
        }
        return EntityList.getLivingEntityByUUID(a2);
    }

    public static Entity ALLATORIxDEMO(UUID a2) {
        return EntityList.getEntityByUUID(a2);
    }
}

