/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package goblinbob.mobends.core.client.event;

import goblinbob.mobends.core.network.NetworkConfiguration;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldJoinHandler {
    @SubscribeEvent
    public void onPlayerJoinedServer(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof AbstractClientPlayer) {
            NetworkConfiguration.instance.onWorldJoin();
        }
    }
}

