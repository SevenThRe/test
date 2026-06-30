/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraftforge.fml.common.network.NetworkRegistry
 *  net.minecraftforge.fml.common.network.NetworkRegistry$TargetPoint
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraftforge.fml.relauncher.Side
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package journeymap.common.network.impl;

import journeymap.common.network.impl.Message;
import journeymap.common.network.impl.MessageListener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetworkHandler {
    private final String MOD_ID;
    private static NetworkHandler INSTANCE;
    private final SimpleNetworkWrapper NETWORK_CHANNEL;

    public NetworkHandler(String modid) {
        INSTANCE = this;
        this.MOD_ID = modid;
        this.NETWORK_CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel(this.MOD_ID + "_channel");
    }

    public int register() {
        return this.register(0);
    }

    public int register(int discriminator) {
        this.NETWORK_CHANNEL.registerMessage(MessageListener.class, Message.class, discriminator++, Side.SERVER);
        this.NETWORK_CHANNEL.registerMessage(MessageListener.class, Message.class, discriminator++, Side.CLIENT);
        return discriminator;
    }

    public static NetworkHandler getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        }
        NetworkHandler.getLogger().error("Packet Handler not initialized before use.");
        throw new UnsupportedOperationException("Packet Handler not Initialized");
    }

    public static Logger getLogger() {
        return LogManager.getLogger((String)NetworkHandler.INSTANCE.MOD_ID);
    }

    public void sendToServer(Message message) {
        this.NETWORK_CHANNEL.sendToServer((IMessage)message);
    }

    public void sendTo(Message message, EntityPlayerMP player) {
        this.NETWORK_CHANNEL.sendTo((IMessage)message, player);
    }

    public void sendToAll(Message message) {
        this.NETWORK_CHANNEL.sendToAll((IMessage)message);
    }

    public void sendToAllAround(Message message, NetworkRegistry.TargetPoint point) {
        this.NETWORK_CHANNEL.sendToAllAround((IMessage)message, point);
    }

    public void sendToAllTracking(IMessage message, NetworkRegistry.TargetPoint point) {
        this.NETWORK_CHANNEL.sendToAllTracking(message, point);
    }

    public void sendToAllTracking(IMessage message, Entity entity) {
        this.NETWORK_CHANNEL.sendToAllTracking(message, entity);
    }

    public void sendToDimension(IMessage message, int dimensionId) {
        this.NETWORK_CHANNEL.sendToDimension(message, dimensionId);
    }
}

