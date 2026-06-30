/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package baka.client.network.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class InterruptEventPacket
implements IMessage,
IMessageHandler<InterruptEventPacket, IMessage> {
    public void fromBytes(ByteBuf byteBuf) {
    }

    public void toBytes(ByteBuf byteBuf) {
    }

    public IMessage onMessage(InterruptEventPacket packet, MessageContext messageContext) {
        return null;
    }
}

