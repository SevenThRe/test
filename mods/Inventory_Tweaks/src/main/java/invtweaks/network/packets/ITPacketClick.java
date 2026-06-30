/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.network.INetHandler
 *  net.minecraft.network.NetHandlerPlayServer
 *  org.jetbrains.annotations.NotNull
 */
package invtweaks.network.packets;

import invtweaks.network.packets.ITPacket;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ClickType;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import org.jetbrains.annotations.NotNull;

public class ITPacketClick
implements ITPacket {
    public int slot;
    public int data;
    public ClickType action;
    public int window;

    public ITPacketClick() {
    }

    public ITPacketClick(int _slot, int _data, ClickType _action, int _window) {
        this.slot = _slot;
        this.data = _data;
        this.action = _action;
        this.window = _window;
    }

    @Override
    public void readBytes(@NotNull ByteBuf bytes) {
        this.slot = bytes.readInt();
        this.data = bytes.readInt();
        this.action = ClickType.values()[bytes.readInt()];
        this.window = bytes.readByte();
    }

    @Override
    public void writeBytes(@NotNull ByteBuf bytes) {
        bytes.writeInt(this.slot);
        bytes.writeInt(this.data);
        bytes.writeInt(this.action.ordinal());
        bytes.writeByte(this.window);
    }

    @Override
    public void handle(INetHandler handler) {
        if (handler instanceof NetHandlerPlayServer) {
            NetHandlerPlayServer serverHandler = (NetHandlerPlayServer)handler;
            EntityPlayerMP player = serverHandler.field_147369_b;
            if (player.field_71070_bA.field_75152_c == this.window) {
                player.field_71070_bA.func_184996_a(this.slot, this.data, this.action, (EntityPlayer)player);
            }
        }
    }
}

