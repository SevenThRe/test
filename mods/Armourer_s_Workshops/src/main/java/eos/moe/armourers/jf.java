/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.primitives.Bytes
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.armourers;

import com.google.common.primitives.Bytes;
import eos.moe.armourers.ij;
import eos.moe.armourers.mi;
import eos.moe.armourers.nk;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class jf
implements IMessage,
IMessageHandler<jf, IMessage> {
    private static byte[] m;
    private int j;

    public void toBytes(ByteBuf a2) {
    }

    @SideOnly(value=Side.CLIENT)
    public void handleDownload(Thread a2) {
        mi.v.r(a2);
    }

    public IMessage onMessage(jf a2, MessageContext a3) {
        return null;
    }

    public jf(int a2) {
        jf a3;
        a3.j = a2;
    }

    public jf() {
        jf a2;
    }

    public static byte[] r(ByteBuf a2) {
        ByteBuf byteBuf = a2;
        byte[] byArray = new byte[byteBuf.readableBytes()];
        byteBuf.getBytes(byteBuf.readerIndex(), byArray);
        return byArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void fromBytes(ByteBuf a2) {
        switch (a2.readInt()) {
            case 1: {
                if (m == null) {
                    m = jf.r(a2);
                    return;
                }
                byte[][] byArrayArray = new byte[2][];
                byArrayArray[0] = m;
                byArrayArray[1] = jf.r(a2);
                m = Bytes.concat((byte[][])byArrayArray);
                return;
            }
            case 2: {
                jf a3;
                if (a3.j == 0) {
                    Thread thread = new Thread((Runnable)new nk(Unpooled.wrappedBuffer((byte[])m)), "Skin download thread.");
                    a3.handleDownload(thread);
                } else if (a3.j == 1) {
                    Thread thread = new Thread((Runnable)new ij(Unpooled.wrappedBuffer((byte[])m)), "Skin download thread.");
                    a3.handleDownload(thread);
                }
                m = null;
                return;
            }
            case 0: {
                jf a3;
                if (a3.j == 0) {
                    Thread thread = new Thread((Runnable)new nk(Unpooled.wrappedBuffer((ByteBuf)a2)), "Skin download thread.");
                    a3.handleDownload(thread);
                    return;
                }
                if (a3.j != 1) return;
                Thread thread = new Thread((Runnable)new ij(Unpooled.wrappedBuffer((ByteBuf)a2)), "Skin download thread.");
                a3.handleDownload(thread);
                return;
            }
        }
    }
}

