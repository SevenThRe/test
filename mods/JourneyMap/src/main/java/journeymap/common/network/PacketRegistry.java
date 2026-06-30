/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraftforge.fml.common.network.NetworkRegistry
 *  net.minecraftforge.fml.relauncher.Side
 */
package journeymap.common.network;

import journeymap.common.Journeymap;
import journeymap.common.network.LegacyServerPackets;
import journeymap.common.network.PacketWaypoint;
import journeymap.common.network.WorldIDPacket;
import journeymap.common.network.impl.NetworkHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class PacketRegistry {
    private static PacketRegistry INSTANCE;

    public static void init() {
        INSTANCE = new PacketRegistry();
        NetworkHandler handler = new NetworkHandler("journeymap");
        NetworkRegistry.INSTANCE.newSimpleChannel("world_info").registerMessage(WorldIDPacket.Listener.class, WorldIDPacket.class, 0, Side.CLIENT);
        NetworkRegistry.INSTANCE.newSimpleChannel("jm_dim_permission").registerMessage(LegacyServerPackets.Listener.class, LegacyServerPackets.class, 0, Side.CLIENT);
        NetworkRegistry.INSTANCE.newSimpleChannel("jm_init_login").registerMessage(LegacyServerPackets.Listener.class, LegacyServerPackets.class, 0, Side.CLIENT);
        NetworkRegistry.INSTANCE.newSimpleChannel("world_waypoint").registerMessage(PacketWaypoint.Listener.class, PacketWaypoint.class, 0, Side.CLIENT);
        handler.register();
    }

    public static PacketRegistry getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        }
        Journeymap.getLogger().error("Packet Handler not initialized before use.");
        throw new UnsupportedOperationException("Packet Handler not Initialized");
    }

    public void versionMismatch() {
        try {
            TextComponentString text1 = new TextComponentString("Disabling Journeymap for this server.");
            TextComponentString text2 = new TextComponentString("This client cannot connect to servers running versions older than Journeymap 5.5.5");
            TextComponentString text3 = new TextComponentString("Please downgrade to Journeymap 5.5.4 to connect to this server or ask the server admin to update Journeymap.");
            Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)text1);
            Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)text2);
            Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)text3);
        }
        catch (Exception exception) {
            // empty catch block
        }
        Journeymap.getClient().disable();
    }
}

