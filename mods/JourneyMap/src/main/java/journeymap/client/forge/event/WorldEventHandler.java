/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.world.World
 *  net.minecraftforge.event.world.WorldEvent$Unload
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package journeymap.client.forge.event;

import journeymap.client.feature.FeatureManager;
import journeymap.client.forge.event.EventHandlerManager;
import journeymap.common.Journeymap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class WorldEventHandler
implements EventHandlerManager.EventHandler {
    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void onUnload(WorldEvent.Unload event) {
        try {
            World world = event.getWorld();
            EntityPlayerSP player = Minecraft.getMinecraft().player;
            if (player != null && player.dimension == world.provider.getDimension()) {
                Journeymap.getClient().stopMapping();
                FeatureManager.INSTANCE.reset();
            }
        }
        catch (Exception e) {
            Journeymap.getLogger().error("Error handling WorldEvent.Unload", (Throwable)e);
        }
    }
}

