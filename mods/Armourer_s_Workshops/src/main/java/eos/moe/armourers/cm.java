/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.gameevent.TickEvent$RenderTickEvent
 */
package eos.moe.armourers;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class cm {
    public static float s;
    public static int m;
    public static int j;

    public cm() {
        cm a2;
    }

    @SubscribeEvent
    public void r(TickEvent.RenderTickEvent a2) {
        if (a2.phase == TickEvent.Phase.START) {
            s = a2.renderTickTime;
            m = j;
            j = 0;
        }
    }

    static {
        j = 0;
        m = 0;
    }
}

