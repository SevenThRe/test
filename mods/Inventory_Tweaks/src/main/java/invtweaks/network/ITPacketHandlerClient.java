/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.channel.SimpleChannelInboundHandler
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.network.NetHandlerPlayClient
 *  net.minecraft.network.INetHandler
 *  net.minecraftforge.fml.common.network.NetworkRegistry
 *  org.jetbrains.annotations.NotNull
 */
package invtweaks.network;

import invtweaks.network.packets.ITPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.INetHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.jetbrains.annotations.NotNull;

public class ITPacketHandlerClient
extends SimpleChannelInboundHandler<ITPacket> {
    protected void channelRead0(@NotNull ChannelHandlerContext ctx, @NotNull ITPacket msg) throws Exception {
        NetHandlerPlayClient handler = (NetHandlerPlayClient)ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
        Minecraft.getMinecraft().addScheduledTask(() -> msg.handle((INetHandler)handler));
    }
}

