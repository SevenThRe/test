/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.bp;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class fl
implements IMessage {
    public int k;
    public NBTTagCompound ALLATORIxDEMO;

    public fl() {
        fl a2;
    }

    public fl(int a2, NBTTagCompound a3) {
        fl a4;
        a4.k = a2;
        a4.ALLATORIxDEMO = a3;
    }

    public void fromBytes(ByteBuf a2) {
        a.k = a2.readInt();
        a.ALLATORIxDEMO = bp.ALLATORIxDEMO(a2);
    }

    public void toBytes(ByteBuf a2) {
        fl a3;
        a2.writeInt(a3.k);
        bp.ALLATORIxDEMO(a3.ALLATORIxDEMO, a2);
    }
}

