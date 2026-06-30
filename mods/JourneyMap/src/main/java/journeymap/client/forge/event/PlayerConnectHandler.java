/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.network.FMLNetworkEvent$ClientConnectedToServerEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package journeymap.client.forge.event;

import journeymap.client.forge.event.EventHandlerManager;
import journeymap.common.Journeymap;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class PlayerConnectHandler
implements EventHandlerManager.EventHandler {
    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void onConnect(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        try {
            if ("MODDED".equals(event.getConnectionType())) {
                Journeymap.getClient().setForgeServerConnection(true);
            }
        }
        catch (Exception e) {
            Journeymap.getLogger().error("Error handling WorldEvent.Unload", (Throwable)e);
        }
    }
}

