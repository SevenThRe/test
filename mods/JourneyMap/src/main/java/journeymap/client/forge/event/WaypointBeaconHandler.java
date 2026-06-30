/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package journeymap.client.forge.event;

import journeymap.client.forge.event.EventHandlerManager;
import journeymap.client.render.ingame.RenderWaypointBeacon;
import journeymap.common.Journeymap;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WaypointBeaconHandler
implements EventHandlerManager.EventHandler {
    final Minecraft mc = FMLClientHandler.instance().getClient();

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void onRenderWorldLastEvent(RenderWorldLastEvent event) {
        if (this.mc.field_71439_g != null && Journeymap.getClient().getWaypointProperties().beaconEnabled.get().booleanValue() && !this.mc.field_71474_y.field_74319_N) {
            this.mc.field_71424_I.func_76320_a("journeymap");
            this.mc.field_71424_I.func_76320_a("beacons");
            RenderWaypointBeacon.renderAll();
            this.mc.field_71424_I.func_76319_b();
            this.mc.field_71424_I.func_76319_b();
        }
    }
}

