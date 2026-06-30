/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.network.INetHandler
 */
package invtweaks.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.INetHandler;

public interface ITPacket {
    public void readBytes(ByteBuf var1);

    public void writeBytes(ByteBuf var1);

    public void handle(INetHandler var1);
}

