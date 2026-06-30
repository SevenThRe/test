/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 */
package imcheck;

import eos.moe.dragoncore.v;
import eos.moe.dragoncore.wi;
import imcheck.IMManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid="dragoncore")
public class f {
    public static boolean ALLATORIxDEMO = false;

    public f() {
        f a2;
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(TickEvent.ClientTickEvent a2) {
        if (a2.phase != TickEvent.Phase.START) {
            return;
        }
        if (!ALLATORIxDEMO) {
            return;
        }
        try {
            IMManager.ALLATORIxDEMO(Minecraft.func_71410_x().field_71462_r != null);
        }
        catch (Throwable a3) {
            ALLATORIxDEMO = false;
            wi.b.ALLATORIxDEMO("imcheckstateerror", new v[0]);
        }
    }
}

