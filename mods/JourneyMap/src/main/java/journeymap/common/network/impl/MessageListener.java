/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonObject
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  org.apache.logging.log4j.Logger
 */
package journeymap.common.network.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import journeymap.common.network.impl.CompressedPacket;
import journeymap.common.network.impl.Message;
import journeymap.common.network.impl.MessageProcessor;
import journeymap.common.network.impl.NetworkHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.apache.logging.log4j.Logger;

public class MessageListener
implements IMessageHandler<Message, IMessage> {
    private Logger logger = NetworkHandler.getLogger();

    public IMessage onMessage(Message message, MessageContext ctx) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        try {
            JsonObject response = (JsonObject)gson.fromJson(message.getMessage(), JsonObject.class);
            String clazz = response.get("container_object").getAsString();
            Class<?> requestObject = Class.forName(clazz);
            if (requestObject.getSuperclass() == MessageProcessor.class || requestObject.getSuperclass() == CompressedPacket.class) {
                MessageProcessor.process(response, ctx, requestObject);
            } else {
                String error = String.format("Bad Network request: %s attempted to send an unqualified packet request.", ctx.side.isClient() ? "THE SERVER" : ctx.getServerHandler().player.getName());
                this.logger.error(error);
            }
        }
        catch (ClassNotFoundException e) {
            this.logger.warn("Message processor not found: ", (Throwable)e);
        }
        catch (NullPointerException e) {
            this.logger.warn("Null Response: ", (Throwable)e);
        }
        return null;
    }
}

