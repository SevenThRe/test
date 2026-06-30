/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.primitives.Bytes
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  net.minecraft.client.Minecraft
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  org.apache.commons.io.IOUtils
 */
package eos.moe.dragoncore;

import com.google.common.primitives.Bytes;
import eos.moe.dragoncore.bp;
import eos.moe.dragoncore.ch;
import eos.moe.dragoncore.h;
import eos.moe.dragoncore.na;
import eos.moe.dragoncore.va;
import eos.moe.dragoncore.yi;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.GZIPInputStream;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.apache.commons.io.IOUtils;

public class bv
implements IMessage,
IMessageHandler<bv, IMessage> {
    private static final Map<Integer, Method> o = new HashMap<Integer, Method>();
    private int y;
    private na k;
    private static ConcurrentHashMap<Integer, byte[]> ALLATORIxDEMO;

    private static /* synthetic */ void ALLATORIxDEMO(Class<?> a2) {
        Method[] a3;
        for (Method a4 : a3 = a2.getDeclaredMethods()) {
            if (!Modifier.isStatic(a4.getModifiers()) || !a4.isAnnotationPresent(h.class)) continue;
            a4.setAccessible(true);
            h a5 = a4.getAnnotation(h.class);
            if (o.containsKey(a5.c())) {
                throw new RuntimeException("\u91cd\u590did" + a2.getSimpleName() + " :" + a4.getName());
            }
            o.put(a5.c(), a4);
        }
    }

    public bv() {
        bv a2;
    }

    public bv(int a2, na a3) {
        bv a4;
        a4.y = a2;
        a4.k = a3;
    }

    public void fromBytes(ByteBuf a2) {
        bv a3;
        Object a4;
        byte[] a5 = bv.ALLATORIxDEMO(a2);
        if ((a5 = bv.ALLATORIxDEMO(a5)) == null) {
            return;
        }
        va a6 = new va(Unpooled.wrappedBuffer((byte[])a5));
        int a7 = a6.readInt();
        int a8 = a6.readInt();
        if (a8 == 1) {
            a4 = ALLATORIxDEMO.remove(a7);
            if (a4 != null) {
                a6 = new va(Unpooled.wrappedBuffer((byte[])a4));
            }
        } else {
            byte[] a9 = ALLATORIxDEMO.getOrDefault(a7, new byte[0]);
            a9 = Bytes.concat((byte[][])new byte[][]{a9, bv.ALLATORIxDEMO((ByteBuf)a6)});
            ALLATORIxDEMO.put(a7, a9);
            return;
        }
        a4 = (Object)a6;
        Minecraft.func_71410_x().func_152344_a(() -> a3.ALLATORIxDEMO(a7, (va)((Object)a4)));
    }

    public static byte[] ALLATORIxDEMO(ByteBuf a2) {
        byte[] a3 = new byte[a2.readableBytes()];
        int a4 = a2.readerIndex();
        a2.getBytes(a4, a3);
        return a3;
    }

    public void toBytes(ByteBuf a2) {
        bv a3;
        a2.writeInt(a3.y);
        a3.k.ALLATORIxDEMO(new va(a2));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static /* synthetic */ byte[] ALLATORIxDEMO(byte[] a2) {
        ByteArrayOutputStream a3 = new ByteArrayOutputStream();
        InputStream a4 = null;
        try {
            IOUtils.copy((InputStream)new GZIPInputStream(new ByteArrayInputStream(a2)), (OutputStream)a3);
        }
        catch (IOException a5) {
            byte[] byArray = null;
            return byArray;
        }
        finally {
            IOUtils.closeQuietly(a4);
            IOUtils.closeQuietly((OutputStream)a3);
        }
        return a3.toByteArray();
    }

    public IMessage onMessage(bv a2, MessageContext a3) {
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private /* synthetic */ void c(int a2, va a3) {
        try {
            Method a4 = o.get(a2);
            Class<?>[] a5 = a4.getParameterTypes();
            Object[] a6 = new Object[a5.length];
            int a7 = 0;
            for (Class<?> a8 : a5) {
                if (a8 == String.class) {
                    a6[a7] = a3.readString();
                } else if (a8 == Integer.TYPE) {
                    a6[a7] = a3.readInt();
                } else if (a8 == Float.TYPE) {
                    a6[a7] = Float.valueOf(a3.readFloat());
                } else if (a8 == Double.TYPE) {
                    a6[a7] = a3.readDouble();
                } else if (a8 == Boolean.TYPE) {
                    a6[a7] = a3.readBoolean();
                } else if (a8 == Long.TYPE) {
                    a6[a7] = a3.readLong();
                } else if (a8 == UUID.class) {
                    a6[a7] = a3.func_179253_g();
                } else if (a8 == va.class) {
                    a6[a7] = a3;
                } else if (a8 == ItemStack.class) {
                    a6[a7] = a3.func_150791_c();
                } else if (a8 == BlockPos.class) {
                    a6[a7] = BlockPos.func_177969_a((long)a3.readLong());
                } else if (a8 == NBTTagCompound.class) {
                    a6[a7] = bp.ALLATORIxDEMO((ByteBuf)a3);
                }
                ++a7;
            }
            a4.invoke(null, a6);
        }
        catch (Throwable a9) {
            a9.printStackTrace();
        }
        finally {
            a3.release();
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(int a2, va a3) {
        bv a4;
        a4.c(a2, a3);
    }

    static {
        bv.ALLATORIxDEMO(yi.class);
        bv.ALLATORIxDEMO(ch.class);
        ALLATORIxDEMO = new ConcurrentHashMap();
    }
}

