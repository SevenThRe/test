/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ni;
import eos.moe.dragoncore.nz;
import eos.moe.dragoncore.sj;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid="dragoncore")
public class wia {
    public static int k;
    public static long ALLATORIxDEMO;

    public wia() {
        wia a2;
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(TickEvent.ClientTickEvent a2) {
        if (a2.phase == TickEvent.Phase.START) {
            if (++k % 40 == 0) {
                sj.ALLATORIxDEMO.clear();
                ni.ALLATORIxDEMO.clear();
            }
            nz.ALLATORIxDEMO(a2);
        }
    }
}

