/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.client.Minecraft
 *  net.minecraft.server.integrated.IntegratedServer
 *  net.minecraft.server.management.PlayerList
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.command;

import com.mojang.authlib.GameProfile;
import java.util.TreeSet;
import journeymap.client.model.Waypoint;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.client.Minecraft;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.server.management.PlayerList;
import net.minecraftforge.fml.client.FMLClientHandler;

public class CmdTeleportWaypoint {
    final Minecraft mc = FMLClientHandler.instance().getClient();
    final Waypoint waypoint;

    public CmdTeleportWaypoint(Waypoint waypoint) {
        this.waypoint = waypoint;
    }

    public static boolean isPermitted(Minecraft mc) {
        if (mc.func_71401_C() != null) {
            IntegratedServer mcServer = mc.func_71401_C();
            PlayerList configurationManager = null;
            GameProfile profile = null;
            try {
                profile = new GameProfile(mc.field_71439_g.func_110124_au(), mc.field_71439_g.func_70005_c_());
                configurationManager = mcServer.func_184103_al();
                return configurationManager.func_152596_g(profile) || Journeymap.getClient().isTeleportEnabled();
            }
            catch (Exception e) {
                e.printStackTrace();
                try {
                    if (profile != null && configurationManager != null) {
                        return mcServer.func_71264_H() && mcServer.field_71305_c[0].func_72912_H().func_76086_u() && mcServer.func_71214_G().equalsIgnoreCase(profile.getName());
                    }
                    Journeymap.getLogger().warn("Failed to check teleport permission both ways: " + LogFormatter.toString(e) + ", and profile or configManager were null.");
                }
                catch (Exception e2) {
                    Journeymap.getLogger().warn("Failed to check teleport permission. Both ways failed: " + LogFormatter.toString(e) + ", and " + LogFormatter.toString(e2));
                }
            }
        }
        if (Journeymap.getClient().isJourneyMapServerConnection()) {
            return Journeymap.getClient().isTeleportEnabled();
        }
        return true;
    }

    public void run() {
        String teleportCommand;
        double x = this.waypoint.getBlockCenteredX();
        double z = this.waypoint.getBlockCenteredZ();
        TreeSet dim = (TreeSet)this.waypoint.getDimensions();
        if ((Integer)dim.first() == -1 && this.mc.field_71439_g.field_71093_bK != -1) {
            x /= 8.0;
            z /= 8.0;
        } else if ((Integer)dim.first() != -1 && this.mc.field_71439_g.field_71093_bK == -1) {
            x *= 8.0;
            z *= 8.0;
        }
        if (Journeymap.getClient().isJourneyMapServerConnection() || FMLClientHandler.instance().getClient().func_71356_B()) {
            teleportCommand = String.format("/jtp %s %s %s %s", x, this.waypoint.getY(), z, dim.first());
            Journeymap.getLogger().info("Attempting jtp teleport with command: " + teleportCommand);
        } else {
            teleportCommand = Journeymap.getClient().getWaypointProperties().teleportCommand.getAsString();
            teleportCommand = teleportCommand.replace("{name}", this.mc.field_71439_g.func_70005_c_()).replace("{x}", String.valueOf(this.waypoint.getX())).replace("{y}", String.valueOf(this.waypoint.getY())).replace("{z}", String.valueOf(this.waypoint.getZ())).replace("{dim}", String.valueOf(dim.first()));
            Journeymap.getLogger().info("Attempting tp teleport with command: " + teleportCommand);
        }
        this.mc.field_71439_g.func_71165_d(teleportCommand);
    }
}

