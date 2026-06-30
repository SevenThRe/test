/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.gameevent.TickEvent$RenderTickEvent
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.dga;
import eos.moe.dragoncore.dt;
import eos.moe.dragoncore.xz;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ls {
    public static float y = 0.0f;
    public static float k = 0.0f;
    public static float ALLATORIxDEMO = 0.0f;

    public ls() {
        ls a2;
    }

    public static float ALLATORIxDEMO() {
        return k;
    }

    @SubscribeEvent
    public void ALLATORIxDEMO(TickEvent.RenderTickEvent a2) {
        float a3;
        if (a2.phase == TickEvent.Phase.END) {
            return;
        }
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null) {
            return;
        }
        if (!Minecraft.getMinecraft().isGamePaused()) {
            y = a2.renderTickTime;
        }
        if (k > (a3 = (float)Minecraft.getMinecraft().player.ticksExisted + a2.renderTickTime)) {
            ls.ALLATORIxDEMO();
        }
        if (!Minecraft.getMinecraft().world.isRemote || !Minecraft.getMinecraft().isGamePaused()) {
            ALLATORIxDEMO = Math.min(Math.max(0.0f, a3 - k), 1.0f);
            k = a3;
            for (xz a4 : dt.k.ALLATORIxDEMO.asMap().values()) {
                a4.o.ALLATORIxDEMO(a2.renderTickTime);
            }
            for (xz a4 : dga.y.ALLATORIxDEMO.values()) {
                a4.o.ALLATORIxDEMO(a2.renderTickTime);
            }
        } else {
            ALLATORIxDEMO = 0.0f;
        }
    }

    public static void ALLATORIxDEMO() {
    }

    @SubscribeEvent
    public void ALLATORIxDEMO(TickEvent.ClientTickEvent a2) {
        if (a2.phase == TickEvent.Phase.END || Minecraft.getMinecraft().player == null || Minecraft.getMinecraft().isGamePaused()) {
            return;
        }
        for (xz a3 : dt.k.ALLATORIxDEMO.asMap().values()) {
            a3.o.ALLATORIxDEMO();
        }
        for (xz a3 : dga.y.ALLATORIxDEMO.values()) {
            a3.o.ALLATORIxDEMO();
        }
    }
}

