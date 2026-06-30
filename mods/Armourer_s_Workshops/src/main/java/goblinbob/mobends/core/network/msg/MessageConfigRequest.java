/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package goblinbob.mobends.core.network.msg;

import goblinbob.mobends.core.network.msg.MessageConfigResponse;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageConfigRequest
implements IMessage {
    public void fromBytes(ByteBuf buf) {
    }

    public void toBytes(ByteBuf buf) {
    }

    public static class Handler
    implements IMessageHandler<MessageConfigRequest, MessageConfigResponse> {
        public MessageConfigResponse onMessage(MessageConfigRequest message, MessageContext ctx) {
            return new MessageConfigResponse();
        }
    }
}

