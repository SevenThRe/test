/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.PacketBuffer
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package baka.client.network.packet;

import baka.animation.bit.AnimationPipeline;
import baka.animation.controller.CPCManager;
import baka.client.network.NetworkHandler;
import baka.util.BBUtil;
import goblinbob.mobends.standard.animation.controller.PlayerController;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.UUID;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PushPipelinePacket
implements IMessage,
IMessageHandler<PushPipelinePacket, IMessage> {
    UUID uuid;
    protected boolean shouldInterrupt = false;
    private AnimationPipeline pipeline = null;
    private ItemStack itemStack;

    public void fromBytes(ByteBuf byteBuf) {
        PacketBuffer packetBuffer = new PacketBuffer(byteBuf);
        this.uuid = UUID.fromString(packetBuffer.func_150789_c(256));
        boolean b2 = packetBuffer.readBoolean();
        if (b2) {
            try {
                this.itemStack = packetBuffer.func_150791_c();
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
            return;
        }
        this.shouldInterrupt = BBUtil.readBool(byteBuf);
        this.pipeline = AnimationPipeline.from(byteBuf);
    }

    public void toBytes(ByteBuf byteBuf) {
    }

    public IMessage onMessage(PushPipelinePacket packet, MessageContext messageContext) {
        PlayerController controller = CPCManager.get(packet.uuid);
        if (controller == null) {
            return null;
        }
        if (packet.itemStack != null) {
            if (packet.itemStack.func_77973_b() == Items.field_190931_a) {
                packet.itemStack = ItemStack.field_190927_a;
            }
            controller.setActiveItem(packet.itemStack);
        } else if (packet.shouldInterrupt) {
            NetworkHandler.interruptEvent();
            controller.interrupt(packet.pipeline);
        } else {
            controller.queueIfEmpty(packet.pipeline);
        }
        return null;
    }
}

