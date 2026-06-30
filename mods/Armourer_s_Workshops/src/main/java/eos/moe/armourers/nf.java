/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  net.minecraft.client.Minecraft
 *  net.minecraft.network.PacketBuffer
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraftforge.fml.common.network.NetworkRegistry
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraftforge.fml.relauncher.Side
 */
package eos.moe.armourers;

import com.google.common.collect.Lists;
import eos.moe.armourers.kl;
import eos.moe.armourers.zh;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class nf
implements IMessage,
IMessageHandler<nf, IMessage> {
    public static SimpleNetworkWrapper j = NetworkRegistry.INSTANCE.newSimpleChannel(zh.m ? "armourers:main" : "armourers");

    public IMessage onMessage(nf a2, MessageContext a3) {
        Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString("\u00a7c[\u9f99\u4e4b\u65f6\u88c5] \u5f53\u524d\u5ba2\u6237\u7aefmod\u7248\u672c\u8fc7\u4f4e,\u8bf7\u66f4\u65b0mod\u7248\u672c"));
        return null;
    }

    public static List<String> r(ByteBuf a2) {
        int n2;
        PacketBuffer packetBuffer = new PacketBuffer(a2);
        int n3 = packetBuffer.readInt();
        ArrayList arrayList = Lists.newArrayList();
        int n4 = n2 = 0;
        while (n4 < n3) {
            arrayList.add(packetBuffer.func_150789_c(32768));
            n4 = ++n2;
        }
        return arrayList;
    }

    public static void y(String a2) {
        j.sendToServer((IMessage)new kl(10, () -> {
            PacketBuffer packetBuffer = new PacketBuffer(Unpooled.buffer());
            packetBuffer.func_180714_a(a2);
            return packetBuffer;
        }));
    }

    public static void r(int a2, String a3) {
        j.sendToServer((IMessage)new kl(3, () -> {
            PacketBuffer packetBuffer;
            PacketBuffer packetBuffer2 = packetBuffer = new PacketBuffer(Unpooled.buffer());
            packetBuffer.writeInt(a2);
            packetBuffer2.func_180714_a(a3);
            return packetBuffer2;
        }));
    }

    public static ByteBuf r(ByteBuf a2, Collection<String> a3) {
        PacketBuffer packetBuffer = new PacketBuffer(a2);
        packetBuffer.writeInt(a3.size());
        a3 = a3.iterator();
        Object object = a3;
        while (object.hasNext()) {
            String string = (String)a3.next();
            object = a3;
            packetBuffer.func_180714_a(string);
        }
        return packetBuffer;
    }

    public void fromBytes(ByteBuf a2) {
    }

    public static void y(String a2, String a3, String a4) {
        j.sendToServer((IMessage)new kl(2, () -> {
            PacketBuffer packetBuffer = new PacketBuffer(Unpooled.buffer());
            PacketBuffer packetBuffer2 = packetBuffer.func_180714_a(a2);
            PacketBuffer packetBuffer3 = packetBuffer;
            packetBuffer.func_180714_a(a3);
            packetBuffer3.func_180714_a(a4);
            return packetBuffer3;
        }));
    }

    public static void r(String a2, String a3, String a4) {
        j.sendToServer((IMessage)new kl(6, () -> {
            PacketBuffer packetBuffer = new PacketBuffer(Unpooled.buffer());
            PacketBuffer packetBuffer2 = packetBuffer.func_180714_a(a2);
            PacketBuffer packetBuffer3 = packetBuffer;
            packetBuffer.func_180714_a(a3);
            packetBuffer3.func_180714_a(a4);
            return packetBuffer3;
        }));
    }

    public static void r(List<String> a2) {
        j.sendToServer((IMessage)new kl(9, () -> {
            PacketBuffer packetBuffer = new PacketBuffer(Unpooled.buffer());
            nf.r((ByteBuf)packetBuffer, a2);
            return packetBuffer;
        }));
    }

    public static void r() {
        j.registerMessage(kl.class, kl.class, 64, Side.SERVER);
        j.registerMessage(kl.class, kl.class, 64, Side.CLIENT);
    }

    public static void r(HashMap<String, String> a2) {
        j.sendToServer((IMessage)new kl(8, () -> {
            PacketBuffer packetBuffer = new PacketBuffer(Unpooled.buffer());
            packetBuffer.writeInt(a2.size());
            Iterator iterator = a2.entrySet().iterator();
            Iterator iterator2 = iterator;
            while (iterator2.hasNext()) {
                Map.Entry entry = iterator.next();
                packetBuffer.func_180714_a((String)entry.getKey());
                packetBuffer.func_180714_a((String)entry.getValue());
                iterator2 = iterator;
            }
            return packetBuffer;
        }));
    }

    public static void r(UUID a2) {
        j.sendToServer((IMessage)new kl(7, () -> {
            PacketBuffer packetBuffer = new PacketBuffer(Unpooled.buffer());
            packetBuffer.func_179252_a(a2);
            return packetBuffer;
        }));
    }

    public static void r(String a2, int a3) {
        j.sendToServer((IMessage)new kl(11, () -> {
            PacketBuffer packetBuffer;
            PacketBuffer packetBuffer2 = packetBuffer = new PacketBuffer(Unpooled.buffer());
            packetBuffer.func_180714_a(a2);
            packetBuffer2.writeInt(a3);
            return packetBuffer2;
        }));
    }

    public static void r(String a2) {
        j.sendToServer((IMessage)new kl(1, () -> {
            PacketBuffer packetBuffer = new PacketBuffer(Unpooled.buffer());
            packetBuffer.func_180714_a(a2);
            return packetBuffer;
        }));
    }

    public static void r(UUID a2, boolean a3, List<String> a4) {
        j.sendToServer((IMessage)new kl(5, () -> {
            PacketBuffer packetBuffer;
            PacketBuffer packetBuffer2 = packetBuffer = new PacketBuffer(Unpooled.buffer());
            packetBuffer.func_179252_a(a2);
            packetBuffer2.writeBoolean(a3);
            return nf.r((ByteBuf)packetBuffer2, a4);
        }));
    }

    public void toBytes(ByteBuf a2) {
    }

    public nf() {
        nf a2;
    }

    public static void r(String a2, List<String> a3) {
        j.sendToServer((IMessage)new kl(4, () -> {
            a3.add(0, a2);
            return nf.r(Unpooled.buffer(), a3);
        }));
    }
}

