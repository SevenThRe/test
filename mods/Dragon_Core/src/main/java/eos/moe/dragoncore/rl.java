/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package eos.moe.dragoncore;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class rl
implements IMessage {
    private int ALLATORIxDEMO;

    public rl() {
        rl a2;
    }

    public rl(int a2) {
        rl a3;
        a3.ALLATORIxDEMO = a2;
    }

    public void fromBytes(ByteBuf a2) {
        a.ALLATORIxDEMO = a2.readInt();
    }

    public void toBytes(ByteBuf a2) {
        rl a3;
        a2.writeInt(a3.ALLATORIxDEMO);
    }

    public static /* synthetic */ int ALLATORIxDEMO(rl a2) {
        return a2.ALLATORIxDEMO;
    }
}

