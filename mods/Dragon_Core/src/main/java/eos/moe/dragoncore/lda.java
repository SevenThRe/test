/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraftforge.client.event.InputUpdateEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.dga;
import eos.moe.dragoncore.om;
import eos.moe.dragoncore.so;
import eos.moe.dragoncore.xz;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
@Mod.EventBusSubscriber(modid="dragoncore")
public class lda {
    public static Map<String, Long> k = new HashMap<String, Long>();
    private static long ALLATORIxDEMO = 0L;

    public lda() {
        lda a2;
    }

    public static float ALLATORIxDEMO(EntityLivingBase a2) {
        xz a3 = dga.y.ALLATORIxDEMO.get(a2.func_110124_au());
        if (a3 != null) {
            for (so a4 : a3.ALLATORIxDEMO().values()) {
                if (!a4.c() || !a4.ALLATORIxDEMO()) continue;
                return 1.0f;
            }
        }
        return a2.field_70733_aJ;
    }

    public static boolean ALLATORIxDEMO(EntityLivingBase a2) {
        xz a3 = dga.y.ALLATORIxDEMO.get(a2.func_110124_au());
        if (a3 != null) {
            for (so a4 : a3.ALLATORIxDEMO().values()) {
                if (!a4.c() || a4.ALLATORIxDEMO() == null) continue;
                return true;
            }
        }
        return false;
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(InputUpdateEvent a2) {
        if (k.getOrDefault("move", 0L) > System.currentTimeMillis()) {
            a2.getMovementInput().field_192832_b = 0.0f;
            a2.getMovementInput().field_78902_a = 0.0f;
            a2.getMovementInput().field_187256_d = false;
            a2.getMovementInput().field_187255_c = false;
            a2.getMovementInput().field_187257_e = false;
            a2.getMovementInput().field_187258_f = false;
        }
        if (k.getOrDefault("jump", 0L) > System.currentTimeMillis()) {
            a2.getMovementInput().field_78901_c = false;
        }
        if (k.getOrDefault("sneak", 0L) > System.currentTimeMillis()) {
            a2.getMovementInput().field_78899_d = false;
        }
        if (om.y > 0 && a2.getMovementInput().field_78899_d) {
            if (ALLATORIxDEMO == 0L || System.currentTimeMillis() < ALLATORIxDEMO + (long)om.y) {
                if (ALLATORIxDEMO == 0L) {
                    ALLATORIxDEMO = System.currentTimeMillis();
                }
                a2.getMovementInput().field_78902_a = (float)((double)a2.getMovementInput().field_78902_a / 0.3);
                a2.getMovementInput().field_192832_b = (float)((double)a2.getMovementInput().field_192832_b / 0.3);
            }
        } else if (ALLATORIxDEMO != 0L) {
            ALLATORIxDEMO = 0L;
        }
    }

    public static boolean ALLATORIxDEMO() {
        return k.getOrDefault("turn", 0L) > System.currentTimeMillis();
    }
}

