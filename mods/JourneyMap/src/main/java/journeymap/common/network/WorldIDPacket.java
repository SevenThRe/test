/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraftforge.fml.common.network.ByteBufUtils
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package journeymap.common.network;

import io.netty.buffer.ByteBuf;
import journeymap.common.Journeymap;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

@Deprecated
public class WorldIDPacket
implements IMessage {
    public static final String CHANNEL_NAME = "world_info";
    private String worldID;

    public WorldIDPacket() {
    }

    public WorldIDPacket(String worldID) {
        this.worldID = worldID;
    }

    public String getWorldID() {
        return this.worldID;
    }

    public void fromBytes(ByteBuf buf) {
        try {
            this.worldID = ByteBufUtils.readUTF8String((ByteBuf)buf);
        }
        catch (Throwable t) {
            Journeymap.getLogger().error(String.format("Failed to read message: %s", t));
        }
    }

    public void toBytes(ByteBuf buf) {
        try {
            if (this.worldID != null) {
                ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.worldID);
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().error("[toBytes]Failed to read message: " + t);
        }
    }

    public static class Listener
    implements IMessageHandler<WorldIDPacket, IMessage> {
        public IMessage onMessage(WorldIDPacket message, MessageContext ctx) {
            if (ctx.side.isClient()) {
                Journeymap.getClient().setCurrentWorldId(message.getWorldID());
            }
            return null;
        }
    }
}

