/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package eos.moe.dragoncore;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class fn
implements IMessage {
    private BlockPos ALLATORIxDEMO;

    public fn() {
        fn a2;
    }

    public fn(BlockPos a2) {
        fn a3;
        a3.ALLATORIxDEMO = a2;
    }

    public void fromBytes(ByteBuf a2) {
        a.ALLATORIxDEMO = BlockPos.fromLong((long)a2.readLong());
    }

    public void toBytes(ByteBuf a2) {
        fn a3;
        a2.writeLong(a3.ALLATORIxDEMO.toLong());
    }

    public static /* synthetic */ BlockPos ALLATORIxDEMO(fn a2) {
        return a2.ALLATORIxDEMO;
    }
}

