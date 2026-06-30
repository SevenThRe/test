/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.network.PacketBuffer
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package journeymap.common.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.netty.buffer.ByteBuf;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import journeymap.client.io.FileHandler;
import journeymap.client.model.Waypoint;
import journeymap.client.ui.UIManager;
import journeymap.client.waypoint.JmReader;
import journeymap.client.waypoint.WaypointStore;
import journeymap.common.Journeymap;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketWaypoint
implements IMessage {
    public static final String CHANNEL_NAME = "world_waypoint";
    private final List<String> waypointList = new ArrayList<String>();

    public void fromBytes(ByteBuf buf) {
        PacketBuffer packetBuffer = new PacketBuffer(buf);
        try {
            int i = packetBuffer.readInt();
            if (i == -1) {
                Minecraft.func_71410_x().func_152343_a(UIManager.INSTANCE::openFullscreenMap);
                return;
            }
            for (int i1 = 0; i1 < i; ++i1) {
                this.waypointList.add(packetBuffer.func_150789_c(32768));
            }
        }
        catch (Throwable var3) {
            Journeymap.getLogger().error(String.format("Failed to read message: %s", var3));
        }
    }

    public void toBytes(ByteBuf buf) {
    }

    public List<String> getWaypointList() {
        return this.waypointList;
    }

    public static class Listener
    implements IMessageHandler<PacketWaypoint, IMessage> {
        private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

        public IMessage onMessage(PacketWaypoint message, MessageContext ctx) {
            if (ctx.side.isClient()) {
                ArrayList<Waypoint> waypoints1 = new ArrayList<Waypoint>(WaypointStore.INSTANCE.getAll());
                JmReader jmReader = new JmReader();
                Collection<Waypoint> waypoints = jmReader.loadWaypoints(FileHandler.getWaypointDir());
                for (Waypoint waypoint : waypoints) {
                    if (!waypoint.getName().endsWith("\u00a7b\u00a7b")) continue;
                    new File(FileHandler.getWaypointDir(), waypoint.getFileName()).delete();
                }
                for (Waypoint waypoint : WaypointStore.INSTANCE.getAll()) {
                    if (!waypoint.getName().endsWith("\u00a7b\u00a7b")) continue;
                    WaypointStore.INSTANCE.remove(waypoint);
                }
                for (String s : message.getWaypointList()) {
                    Waypoint waypoint = (Waypoint)gson.fromJson(s, Waypoint.class);
                    if (waypoint == null) continue;
                    waypoint.setName(waypoint.getName() + "\u00a7b\u00a7b");
                    Waypoint waypoint1 = this.searchEqualIdWaypoint(waypoints1, waypoint.getId());
                    if (waypoint1 != null) {
                        waypoint.setEnable(waypoint1.isEnable());
                    }
                    WaypointStore.INSTANCE.save(waypoint);
                    WaypointStore.INSTANCE.add(waypoint);
                }
            }
            return null;
        }

        private Waypoint searchEqualIdWaypoint(Collection<Waypoint> list, String id) {
            for (Waypoint waypoint : list) {
                if (!waypoint.getId().equals(id)) continue;
                return waypoint;
            }
            return null;
        }
    }
}

