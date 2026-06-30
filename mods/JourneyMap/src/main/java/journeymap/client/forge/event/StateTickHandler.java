/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package journeymap.client.forge.event;

import journeymap.client.api.event.DeathWaypointEvent;
import journeymap.client.api.impl.ClientAPI;
import journeymap.client.forge.event.EventHandlerManager;
import journeymap.client.model.Waypoint;
import journeymap.client.properties.WaypointProperties;
import journeymap.client.waypoint.WaypointStore;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class StateTickHandler
implements EventHandlerManager.EventHandler {
    static boolean javaChecked = false;
    Minecraft mc = FMLClientHandler.instance().getClient();
    int counter = 0;
    private boolean deathpointCreated;

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            return;
        }
        this.mc.profiler.startSection("journeymap");
        if (this.mc.player != null && this.mc.player.isDead) {
            if (!this.deathpointCreated) {
                this.deathpointCreated = true;
                this.createDeathpoint();
            }
        } else {
            this.deathpointCreated = false;
        }
        if (!javaChecked && this.mc.player != null && !this.mc.player.isDead) {
            this.checkJava();
        }
        try {
            if (this.counter == 20) {
                this.mc.profiler.startSection("mainTasks");
                Journeymap.getClient().performMainThreadTasks();
                this.counter = 0;
                this.mc.profiler.endSection();
            } else if (this.counter == 10) {
                this.mc.profiler.startSection("multithreadTasks");
                if (Journeymap.getClient().isMapping().booleanValue() && this.mc.world != null) {
                    Journeymap.getClient().performMultithreadTasks();
                }
                ++this.counter;
                this.mc.profiler.endSection();
            } else if (this.counter == 5 || this.counter == 15) {
                this.mc.profiler.startSection("clientApiEvents");
                ClientAPI.INSTANCE.getClientEventManager().fireNextClientEvents();
                ++this.counter;
                this.mc.profiler.endSection();
            } else {
                ++this.counter;
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().warn("Error during onClientTick: " + LogFormatter.toPartialString(t));
        }
        finally {
            this.mc.profiler.endSection();
        }
    }

    private void createDeathpoint() {
        try {
            EntityPlayerSP player = this.mc.player;
            if (player == null) {
                Journeymap.getLogger().error("Lost reference to player before Deathpoint could be created");
                return;
            }
            WaypointProperties waypointProperties = Journeymap.getClient().getWaypointProperties();
            boolean enabled = waypointProperties.managerEnabled.get() != false && waypointProperties.createDeathpoints.get() != false;
            boolean cancelled = false;
            double playerY = MathHelper.floor((double)player.posY) > 2 ? (double)MathHelper.floor((double)player.posY) : 2.0;
            BlockPos pos = new BlockPos((double)MathHelper.floor((double)player.posX), playerY, (double)MathHelper.floor((double)player.posZ));
            if (enabled) {
                int dim = FMLClientHandler.instance().getClient().player.world.provider.getDimension();
                DeathWaypointEvent event = new DeathWaypointEvent(pos, dim);
                ClientAPI.INSTANCE.getClientEventManager().fireDeathpointEvent(event);
                if (!event.isCancelled()) {
                    Waypoint deathpoint = Waypoint.at(pos, Waypoint.Type.Death, dim);
                    WaypointStore.INSTANCE.save(deathpoint);
                } else {
                    cancelled = true;
                }
            }
            Journeymap.getLogger().info(String.format("%s died at %s. Deathpoints enabled: %s. Deathpoint created: %s", player.getName(), pos, enabled, cancelled ? "cancelled" : Boolean.valueOf(true)));
        }
        catch (Throwable t) {
            Journeymap.getLogger().error("Unexpected Error in createDeathpoint(): " + LogFormatter.toString(t));
        }
    }

    private void checkJava() {
        javaChecked = true;
        try {
            Class.forName("java.util.Objects");
        }
        catch (ClassNotFoundException e) {
            try {
                String error = I18n.format((String)"jm.error.java6", (Object[])new Object[0]);
                FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage((ITextComponent)new TextComponentString(error));
                Journeymap.getLogger().fatal("JourneyMap requires Java 7 or Java 8. Update your launcher profile to use a newer version of Java.");
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
            Journeymap.getClient().disable();
        }
    }
}

