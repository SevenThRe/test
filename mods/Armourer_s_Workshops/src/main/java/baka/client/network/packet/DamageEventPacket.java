/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.network.PacketBuffer
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package baka.client.network.packet;

import io.netty.buffer.ByteBuf;
import java.util.List;
import java.util.UUID;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class DamageEventPacket
implements IMessage,
IMessageHandler<DamageEventPacket, IMessage> {
    protected String actionName = "";
    protected List<UUID> entity;

    public DamageEventPacket() {
    }

    public DamageEventPacket(String actionName, List<UUID> entity) {
        this.actionName = actionName;
        this.entity = entity;
    }

    public void fromBytes(ByteBuf byteBuf) {
    }

    public void toBytes(ByteBuf byteBuf) {
        PacketBuffer packetBuffer = new PacketBuffer(byteBuf);
        packetBuffer.func_180714_a(this.actionName);
        packetBuffer.writeInt(this.entity.size());
        for (UUID uuid : this.entity) {
            packetBuffer.func_179252_a(uuid);
        }
    }

    public IMessage onMessage(DamageEventPacket packet, MessageContext messageContext) {
        return null;
    }
}

