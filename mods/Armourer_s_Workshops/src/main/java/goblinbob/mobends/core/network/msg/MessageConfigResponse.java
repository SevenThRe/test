/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.fml.common.network.ByteBufUtils
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package goblinbob.mobends.core.network.msg;

import goblinbob.mobends.core.Core;
import goblinbob.mobends.core.network.NetworkConfiguration;
import goblinbob.mobends.core.network.SharedProperty;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageConfigResponse
implements IMessage {
    public void toBytes(ByteBuf buf) {
        NBTTagCompound tag = new NBTTagCompound();
        NetworkConfiguration.instance.getSharedConfig().writeToNBT(tag);
        ByteBufUtils.writeTag((ByteBuf)buf, (NBTTagCompound)tag);
    }

    public void fromBytes(ByteBuf buf) {
        NBTTagCompound tag = ByteBufUtils.readTag((ByteBuf)buf);
        if (tag == null) {
            Core.LOG.severe("An error occurred while receiving server configuration.");
            return;
        }
        NetworkConfiguration.instance.getSharedConfig().readFromNBT(tag);
    }

    public static class Handler
    implements IMessageHandler<MessageConfigResponse, IMessage> {
        public IMessage onMessage(MessageConfigResponse message, MessageContext ctx) {
            StringBuilder builder = new StringBuilder("Received Mo' Bends server configuration.\n");
            Iterable<SharedProperty<?>> properties = NetworkConfiguration.instance.getSharedConfig().getProperties();
            for (SharedProperty<?> property : properties) {
                builder.append(String.format(" - %s: %b\n", property.getKey(), property.getValue()));
            }
            Core.LOG.info(builder.toString());
            return null;
        }
    }
}

