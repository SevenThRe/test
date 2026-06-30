/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.network.INetHandler
 *  org.jetbrains.annotations.NotNull
 */
package invtweaks.network.packets;

import invtweaks.forge.InvTweaksMod;
import invtweaks.network.packets.ITPacket;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.INetHandler;
import org.jetbrains.annotations.NotNull;

public class ITPacketLogin
implements ITPacket {
    public byte protocolVersion = (byte)2;

    @Override
    public void readBytes(@NotNull ByteBuf bytes) {
        this.protocolVersion = bytes.readByte();
    }

    @Override
    public void writeBytes(@NotNull ByteBuf bytes) {
        bytes.writeByte((int)this.protocolVersion);
    }

    @Override
    public void handle(INetHandler handler) {
        if (this.protocolVersion == 2) {
            InvTweaksMod.proxy.setServerHasInvTweaks(true);
        }
    }
}

