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
        this.mc.field_71424_I.func_76320_a("journeymap");
        if (this.mc.field_71439_g != null && this.mc.field_71439_g.field_70128_L) {
            if (!this.deathpointCreated) {
                this.deathpointCreated = true;
                this.createDeathpoint();
            }
        } else {
            this.deathpointCreated = false;
        }
        if (!javaChecked && this.mc.field_71439_g != null && !this.mc.field_71439_g.field_70128_L) {
            this.checkJava();
        }
        try {
            if (this.counter == 20) {
                this.mc.field_71424_I.func_76320_a("mainTasks");
                Journeymap.getClient().performMainThreadTasks();
                this.counter = 0;
                this.mc.field_71424_I.func_76319_b();
            } else if (this.counter == 10) {
                this.mc.field_71424_I.func_76320_a("multithreadTasks");
                if (Journeymap.getClient().isMapping().booleanValue() && this.mc.field_71441_e != null) {
                    Journeymap.getClient().performMultithreadTasks();
                }
                ++this.counter;
                this.mc.field_71424_I.func_76319_b();
            } else if (this.counter == 5 || this.counter == 15) {
                this.mc.field_71424_I.func_76320_a("clientApiEvents");
                ClientAPI.INSTANCE.getClientEventManager().fireNextClientEvents();
                ++this.counter;
                this.mc.field_71424_I.func_76319_b();
            } else {
                ++this.counter;
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().warn("Error during onClientTick: " + LogFormatter.toPartialString(t));
        }
        finally {
            this.mc.field_71424_I.func_76319_b();
        }
    }

    private void createDeathpoint() {
        try {
            EntityPlayerSP player = this.mc.field_71439_g;
            if (player == null) {
                Journeymap.getLogger().error("Lost reference to player before Deathpoint could be created");
                return;
            }
            WaypointProperties waypointProperties = Journeymap.getClient().getWaypointProperties();
            boolean enabled = waypointProperties.managerEnabled.get() != false && waypointProperties.createDeathpoints.get() != false;
            boolean cancelled = false;
            double playerY = MathHelper.func_76128_c((double)player.field_70163_u) > 2 ? (double)MathHelper.func_76128_c((double)player.field_70163_u) : 2.0;
            BlockPos pos = new BlockPos((double)MathHelper.func_76128_c((double)player.field_70165_t), playerY, (double)MathHelper.func_76128_c((double)player.field_70161_v));
            if (enabled) {
                int dim = FMLClientHandler.instance().getClient().field_71439_g.field_70170_p.field_73011_w.getDimension();
                DeathWaypointEvent event = new DeathWaypointEvent(pos, dim);
                ClientAPI.INSTANCE.getClientEventManager().fireDeathpointEvent(event);
                if (!event.isCancelled()) {
                    Waypoint deathpoint = Waypoint.at(pos, Waypoint.Type.Death, dim);
                    WaypointStore.INSTANCE.save(deathpoint);
                } else {
                    cancelled = true;
                }
            }
            Journeymap.getLogger().info(String.format("%s died at %s. Deathpoints enabled: %s. Deathpoint created: %s", player.func_70005_c_(), pos, enabled, cancelled ? "cancelled" : Boolean.valueOf(true)));
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
                String error = I18n.func_135052_a((String)"jm.error.java6", (Object[])new Object[0]);
                FMLClientHandler.instance().getClient().field_71456_v.func_146158_b().func_146227_a((ITextComponent)new TextComponentString(error));
                Journeymap.getLogger().fatal("JourneyMap requires Java 7 or Java 8. Update your launcher profile to use a newer version of Java.");
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
            Journeymap.getClient().disable();
        }
    }
}

