/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.network.PacketBuffer
 *  net.minecraftforge.fml.common.network.NetworkRegistry
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.teamderpy.shouldersurfing.event;

import com.teamderpy.shouldersurfing.config.Perspective;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkManager
implements IMessage,
IMessageHandler<NetworkManager, IMessage> {
    private static SimpleNetworkWrapper networkWrapper;
    private IPacket packet;

    public static void init() {
        networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("shouldersurfing:main");
        networkWrapper.registerMessage(NetworkManager.class, NetworkManager.class, 64, Side.SERVER);
        networkWrapper.registerMessage(NetworkManager.class, NetworkManager.class, 64, Side.CLIENT);
    }

    public static void sendDirection(double x, double y, double z, boolean hasEntity, double x1, double y1, double z1) {
        networkWrapper.sendToServer((IMessage)new NetworkManager(buffer -> {
            buffer.writeInt(1);
            buffer.writeInt(Perspective.current().getPointOfView());
            buffer.writeDouble(x);
            buffer.writeDouble(y);
            buffer.writeDouble(z);
            buffer.writeBoolean(hasEntity);
            if (hasEntity) {
                buffer.writeDouble(x1);
                buffer.writeDouble(y1);
                buffer.writeDouble(z1);
            }
        }));
    }

    public NetworkManager() {
    }

    public NetworkManager(IPacket packet) {
        this.packet = packet;
    }

    public void fromBytes(ByteBuf buf) {
    }

    public void toBytes(ByteBuf buf) {
        if (this.packet != null) {
            this.packet.write(new PacketBuffer(buf));
        }
    }

    public IMessage onMessage(NetworkManager message, MessageContext ctx) {
        return null;
    }

    public static interface IPacket {
        public void write(PacketBuffer var1);
    }
}

