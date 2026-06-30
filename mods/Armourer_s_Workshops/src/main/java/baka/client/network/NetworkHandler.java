/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.network.NetworkRegistry
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraftforge.fml.relauncher.Side
 */
package baka.client.network;

import baka.client.network.packet.DamageEventPacket;
import baka.client.network.packet.FinishEventPacket;
import baka.client.network.packet.InterruptEventPacket;
import baka.client.network.packet.PushPipelinePacket;
import baka.client.network.packet.SkillEventPacket;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {
    public static final String CHANNEL_NAME = "LonaMoBends".toLowerCase() + "|Main";
    public static final SimpleNetworkWrapper channel = NetworkRegistry.INSTANCE.newSimpleChannel(CHANNEL_NAME);
    private static final AtomicInteger packetId = new AtomicInteger(0);

    public static void init() {
        NetworkHandler.registerClientMessage(DamageEventPacket.class, DamageEventPacket.class);
        NetworkHandler.registerClientMessage(FinishEventPacket.class, FinishEventPacket.class);
        NetworkHandler.registerClientMessage(PushPipelinePacket.class, PushPipelinePacket.class);
        NetworkHandler.registerClientMessage(InterruptEventPacket.class, InterruptEventPacket.class);
        NetworkHandler.registerClientMessage(SkillEventPacket.class, SkillEventPacket.class);
    }

    private static <REQ extends IMessage, REPLY extends IMessage> void registerClientMessage(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType) {
        channel.registerMessage(messageHandler, requestMessageType, packetId.getAndIncrement(), Side.CLIENT);
    }

    private static <REQ extends IMessage, REPLY extends IMessage> void registerMessage(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, Side side) {
        channel.registerMessage(messageHandler, requestMessageType, packetId.getAndIncrement(), side);
    }

    public static void damageEvent(String actionName, List<UUID> entity) {
        channel.sendToServer((IMessage)new DamageEventPacket(actionName, entity));
    }

    public static void skillEvent(String actionName, String skill) {
        channel.sendToServer((IMessage)new SkillEventPacket(actionName, skill));
    }

    public static void finishEvent(String actionName) {
        channel.sendToServer((IMessage)new FinishEventPacket(actionName));
    }

    public static void interruptEvent() {
        channel.sendToServer((IMessage)new InterruptEventPacket());
    }
}

