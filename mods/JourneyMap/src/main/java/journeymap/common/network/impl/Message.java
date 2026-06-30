/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraftforge.fml.common.network.ByteBufUtils
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package journeymap.common.network.impl;

import io.netty.buffer.ByteBuf;
import journeymap.common.network.impl.NetworkHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class Message
implements IMessage {
    private String message;

    public Message() {
    }

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void fromBytes(ByteBuf buf) {
        try {
            this.message = ByteBufUtils.readUTF8String((ByteBuf)buf);
        }
        catch (Throwable t) {
            NetworkHandler.getLogger().error(String.format("Failed to read message: %s", t));
        }
    }

    public void toBytes(ByteBuf buf) {
        try {
            if (this.message != null) {
                ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.message);
            }
        }
        catch (Throwable t) {
            NetworkHandler.getLogger().error("[toBytes]Failed to read message: " + t);
        }
    }
}

