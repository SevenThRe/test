/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.bp;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class lh
implements IMessage {
    public BlockPos k;
    public NBTTagCompound ALLATORIxDEMO;

    public lh() {
        lh a2;
    }

    public lh(BlockPos a2, NBTTagCompound a3) {
        lh a4;
        a4.k = a2;
        a4.ALLATORIxDEMO = a3;
    }

    public void fromBytes(ByteBuf a2) {
        a.k = BlockPos.fromLong((long)a2.readLong());
        a.ALLATORIxDEMO = bp.ALLATORIxDEMO(a2);
    }

    public void toBytes(ByteBuf a2) {
        lh a3;
        a2.writeLong(a3.k.toLong());
        bp.ALLATORIxDEMO(a3.ALLATORIxDEMO, a2);
    }
}

