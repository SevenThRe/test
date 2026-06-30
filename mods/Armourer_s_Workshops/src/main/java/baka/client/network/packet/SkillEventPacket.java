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
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SkillEventPacket
implements IMessage,
IMessageHandler<SkillEventPacket, IMessage> {
    protected String actionName = "";
    protected String skill = "";

    public SkillEventPacket() {
    }

    public SkillEventPacket(String actionName, String skill) {
        this.actionName = actionName;
        this.skill = skill;
    }

    public void fromBytes(ByteBuf byteBuf) {
    }

    public void toBytes(ByteBuf byteBuf) {
        PacketBuffer packetBuffer = new PacketBuffer(byteBuf);
        packetBuffer.writeString(this.actionName);
        packetBuffer.writeString(this.skill);
    }

    public IMessage onMessage(SkillEventPacket packet, MessageContext messageContext) {
        return null;
    }
}

