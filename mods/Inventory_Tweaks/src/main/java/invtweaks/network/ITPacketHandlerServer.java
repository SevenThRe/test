/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.channel.SimpleChannelInboundHandler
 *  net.minecraft.network.INetHandler
 *  net.minecraft.network.NetHandlerPlayServer
 *  net.minecraftforge.fml.common.network.NetworkRegistry
 *  org.jetbrains.annotations.NotNull
 */
package invtweaks.network;

import invtweaks.network.packets.ITPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.jetbrains.annotations.NotNull;

public class ITPacketHandlerServer
extends SimpleChannelInboundHandler<ITPacket> {
    protected void channelRead0(@NotNull ChannelHandlerContext ctx, @NotNull ITPacket msg) throws Exception {
        NetHandlerPlayServer handler = (NetHandlerPlayServer)ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
        handler.player.server.addScheduledTask(() -> msg.handle((INetHandler)handler));
    }
}

