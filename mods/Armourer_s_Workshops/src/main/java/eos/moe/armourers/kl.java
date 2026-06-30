/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiInventory
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.network.PacketBuffer
 *  net.minecraft.util.Tuple
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  org.apache.commons.io.IOUtils
 */
package eos.moe.armourers;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eos.moe.armourers.af;
import eos.moe.armourers.al;
import eos.moe.armourers.b;
import eos.moe.armourers.de;
import eos.moe.armourers.fk;
import eos.moe.armourers.jf;
import eos.moe.armourers.jn;
import eos.moe.armourers.nf;
import eos.moe.armourers.pi;
import eos.moe.armourers.rg;
import eos.moe.armourers.zg;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Tuple;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.apache.commons.io.IOUtils;

public class kl
implements IMessage,
IMessageHandler<kl, IMessage> {
    public static boolean s = true;
    private b m;
    private int j;

    public kl(int a2, b a3) {
        kl a4;
        kl kl2 = a4;
        kl2.j = a2;
        kl2.m = a3;
    }

    public Gson buildGson() {
        return new GsonBuilder().create();
    }

    public static byte[] r(ByteBuf a2) {
        ByteBuf byteBuf = a2;
        byte[] byArray = new byte[byteBuf.readableBytes()];
        byteBuf.getBytes(byteBuf.readerIndex(), byArray);
        return byArray;
    }

    public void toBytes(ByteBuf a2) {
        kl a3;
        a2.writeInt(a3.j);
        a2.writeBytes(a3.m.r());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void fromBytes(ByteBuf a2) {
        byte[] byArray = kl.r(kl.r(a2));
        a2 = byArray;
        if (byArray == null || !s) {
            return;
        }
        a2 = new PacketBuffer(Unpooled.wrappedBuffer((byte[])a2));
        switch (a2.readInt()) {
            case 0: {
                new jf(1).fromBytes((ByteBuf)a2);
                return;
            }
            case 1: {
                new jf().fromBytes((ByteBuf)a2);
                return;
            }
            case 2: {
                Object object = a2;
                while (false) {
                }
                UUID uUID = object.func_179253_g();
                HashSet hashSet = Sets.newHashSet(nf.r(object));
                zg.m.put(uUID, hashSet.stream().map(fk::new).collect(Collectors.toList()));
                return;
            }
            case 3: {
                int n2;
                zg.c.clear();
                int n3 = a2.readInt();
                int n4 = n2 = 0;
                while (n4 < n3) {
                    Object object = a2;
                    String string = object.func_150789_c(1024);
                    List<String> list = nf.r(object);
                    zg.c.put(string, list.stream().map(fk::new).collect(Collectors.toList()));
                    n4 = ++n2;
                }
                al.y();
                return;
            }
            case 4: {
                int n5;
                zg.j.clear();
                int n6 = a2.readInt();
                int n7 = n5 = 0;
                while (n7 < n6) {
                    Object object = a2;
                    String string = object.func_150789_c(1024);
                    String string2 = object.func_150789_c(1024);
                    zg.j.put(string, string2);
                    n7 = ++n5;
                }
                return;
            }
            case 5: {
                List<String> list = nf.r(a2);
                zg.r = list.stream().map(fk::new).collect(Collectors.toList());
                return;
            }
            case 6: {
                return;
            }
            case 7: {
                List<String> list = af.l;
                synchronized (list) {
                    af.l.clear();
                    af.l.addAll(nf.r(a2));
                    return;
                }
            }
            case 8: {
                List<Tuple<String, Integer>> list = af.s;
                synchronized (list) {
                    int n8;
                    af.s.clear();
                    Object object = a2;
                    af.c = object.func_150789_c(1024);
                    int n9 = object.readInt();
                    int n10 = n8 = 0;
                    while (n10 < n9) {
                        Object object2 = a2;
                        String string = object2.func_150789_c(1024);
                        int n11 = object2.readInt();
                        af.s.add((Tuple<String, Integer>)new Tuple((Object)string, (Object)n11));
                        n10 = ++n8;
                    }
                    return;
                }
            }
            case 9: {
                kl a3;
                Object object = a2;
                String string = object.func_150789_c(1024);
                String string3 = object.func_150789_c(32768);
                rg rg2 = (rg)a3.buildGson().fromJson(string3, rg.class);
                if (rg2.y()) {
                    rg rg3 = rg2;
                    rg3.r(string);
                    rg3.r(0);
                }
                rg2.r();
                af.m.put(string, rg2);
                return;
            }
            case 12: {
                int n12;
                int n13 = a2.readInt();
                int n14 = n12 = 0;
                while (n14 < n13) {
                    jn.r(a2.func_150789_c(1024), a2.func_150789_c(1024));
                    n14 = ++n12;
                }
                return;
            }
            case 13: {
                float f2 = a2.readFloat();
                pi.v.j = new de(Minecraft.func_71410_x().field_71439_g, f2);
                return;
            }
            case 14: {
                pi.v.j = null;
                return;
            }
            case 15: {
                af.m.clear();
                return;
            }
            case 17: {
                Minecraft.func_71410_x().func_152344_a(() -> Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiInventory((EntityPlayer)Minecraft.func_71410_x().field_71439_g)));
                return;
            }
        }
    }

    public kl() {
        kl a2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static /* synthetic */ byte[] r(byte[] a2) {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        InputStream inputStream = null;
        try {
            IOUtils.copy((InputStream)new GZIPInputStream(new ByteArrayInputStream(a2)), (OutputStream)byteArrayOutputStream2);
            byteArrayOutputStream = byteArrayOutputStream2;
        }
        catch (IOException iOException) {
            byte[] byArray = null;
            return byArray;
        }
        finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly((OutputStream)byteArrayOutputStream2);
        }
        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly((OutputStream)byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public IMessage onMessage(kl a2, MessageContext a3) {
        return null;
    }
}

