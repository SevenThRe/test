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
import journeymap.common.network.PacketRegistry;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

@Deprecated
public class LegacyServerPackets
implements IMessage {
    public static final String CHANNEL_NAME_LOGIN = "jm_init_login";
    public static final String CHANNEL_NAME_PROP = "jm_dim_permission";
    private String packet = "";

    public LegacyServerPackets() {
    }

    public LegacyServerPackets(Object packet) {
        this.packet = packet.toString();
    }

    public String getPacket() {
        return this.packet;
    }

    public void fromBytes(ByteBuf buf) {
        try {
            this.packet = ByteBufUtils.readUTF8String((ByteBuf)buf);
        }
        catch (Throwable t) {
            Journeymap.getLogger().error(String.format("Failed to read message: %s", t));
        }
    }

    public void toBytes(ByteBuf buf) {
        try {
            if (this.packet != null) {
                ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.packet);
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().error("[toBytes]Failed to read message: " + t);
        }
    }

    public static class Listener
    implements IMessageHandler<LegacyServerPackets, IMessage> {
        public IMessage onMessage(LegacyServerPackets message, MessageContext ctx) {
            PacketRegistry.getInstance().versionMismatch();
            return null;
        }
    }
}

