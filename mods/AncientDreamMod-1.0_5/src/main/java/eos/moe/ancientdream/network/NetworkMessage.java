/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  net.minecraft.network.PacketBuffer
 *  net.minecraftforge.fml.common.network.NetworkRegistry
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraftforge.fml.relauncher.Side
 *  org.apache.commons.io.IOUtils
 */
package eos.moe.ancientdream.network;

import eos.moe.ancientdream.network.IPacket;
import eos.moe.ancientdream.network.receive.BetonQuestHandler;
import eos.moe.ancientdream.network.receive.GuildHandler;
import eos.moe.ancientdream.network.receive.MessageHandler;
import eos.moe.ancientdream.network.receive.OutfitHandler;
import eos.moe.ancientdream.network.receive.OutfitYamlHandler;
import eos.moe.ancientdream.network.receive.PlayerDataHandler;
import eos.moe.ancientdream.network.receive.SkillMenuHandler;
import eos.moe.ancientdream.network.receive.TeamGuiHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.commons.io.IOUtils;

public class NetworkMessage
implements IMessage,
IMessageHandler<NetworkMessage, IMessage> {
    public static SimpleNetworkWrapper networkWrapper;
    private static Map<Integer, MessageHandler> messageHandlerMap;
    private int packetID;
    private IPacket iPacket;

    public NetworkMessage() {
    }

    public NetworkMessage(int packetID, IPacket iPacket) {
        this.packetID = packetID;
        this.iPacket = iPacket;
    }

    public static void init() {
        networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("ancientdream:main");
        networkWrapper.registerMessage(NetworkMessage.class, NetworkMessage.class, 64, Side.SERVER);
        networkWrapper.registerMessage(NetworkMessage.class, NetworkMessage.class, 64, Side.CLIENT);
        messageHandlerMap = new HashMap<Integer, MessageHandler>();
        messageHandlerMap.put(0, new SkillMenuHandler());
        messageHandlerMap.put(1, new PlayerDataHandler());
        messageHandlerMap.put(10, new TeamGuiHandler());
        messageHandlerMap.put(50, new GuildHandler());
        messageHandlerMap.put(40, new BetonQuestHandler());
        messageHandlerMap.put(2333, new OutfitYamlHandler());
        messageHandlerMap.put(2334, new OutfitHandler());
    }

    public void fromBytes(ByteBuf byteBuf) {
        byte[] bytes = NetworkMessage.readBytes(byteBuf);
        if ((bytes = NetworkMessage.decompressByteArray(bytes)) == null) {
            return;
        }
        PacketBuffer buffer = new PacketBuffer(Unpooled.wrappedBuffer((byte[])bytes));
        int packetID = buffer.readInt();
        MessageHandler messageHandler = messageHandlerMap.get(packetID);
        if (messageHandler != null) {
            try {
                messageHandler.readBuffer(buffer);
            }
            catch (Exception e) {
                System.out.println("\u672a\u77e5\u5f02\u5e38");
                e.printStackTrace();
            }
        } else {
            System.out.println("\u672a\u77e5\u6570\u636e\u5305ID: " + packetID);
        }
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.packetID);
        if (this.iPacket != null) {
            this.iPacket.write(new PacketBuffer(buf));
        }
    }

    public IMessage onMessage(NetworkMessage message, MessageContext ctx) {
        return null;
    }

    public static byte[] readBytes(ByteBuf buf) {
        byte[] bytes = new byte[buf.readableBytes()];
        int readerIndex = buf.readerIndex();
        buf.getBytes(readerIndex, bytes);
        return bytes;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static byte[] decompressByteArray(byte[] compressedData) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream gzis = null;
        try {
            IOUtils.copy((InputStream)new GZIPInputStream(new ByteArrayInputStream(compressedData)), (OutputStream)baos);
        }
        catch (IOException e) {
            byte[] byArray = null;
            return byArray;
        }
        finally {
            IOUtils.closeQuietly(gzis);
            IOUtils.closeQuietly((OutputStream)baos);
        }
        return baos.toByteArray();
    }
}

