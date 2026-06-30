/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.event.world.WorldEvent$Unload
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.oka;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid="dragoncore")
public class kba {
    public static final Map<UUID, oka> ALLATORIxDEMO = new HashMap<UUID, oka>();

    public kba() {
        kba a2;
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(WorldEvent.Unload a2) {
        ALLATORIxDEMO.clear();
    }
}

