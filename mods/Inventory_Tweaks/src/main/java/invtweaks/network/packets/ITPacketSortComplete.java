/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.network.INetHandler
 *  net.minecraft.network.NetHandlerPlayServer
 */
package invtweaks.network.packets;

import invtweaks.network.packets.ITPacket;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;

public class ITPacketSortComplete
implements ITPacket {
    @Override
    public void readBytes(ByteBuf bytes) {
    }

    @Override
    public void writeBytes(ByteBuf bytes) {
    }

    @Override
    public void handle(INetHandler handler) {
        if (handler instanceof NetHandlerPlayServer) {
            NetHandlerPlayServer serverHandler = (NetHandlerPlayServer)handler;
            EntityPlayerMP player = serverHandler.field_147369_b;
            player.func_71120_a(player.field_71070_bA);
        }
    }
}

