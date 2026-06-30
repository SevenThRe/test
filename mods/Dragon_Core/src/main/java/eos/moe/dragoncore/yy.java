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

public class yy
implements IMessage,
IMessageHandler<yy, IMessage> {
    private static final Map<Integer, Method> k = new HashMap<Integer, Method>();
    private static ConcurrentHashMap<Integer, byte[]> ALLATORIxDEMO;

    private static /* synthetic */ void ALLATORIxDEMO(Class<?> a2) {
        Method[] a3;
        for (Method a4 : a3 = a2.getDeclaredMethods()) {
            if (!Modifier.isStatic(a4.getModifiers()) || !a4.isAnnotationPresent(h.class)) continue;
            a4.setAccessible(true);
            h a5 = a4.getAnnotation(h.class);
            if (k.containsKey(a5.c())) {
                throw new RuntimeException("\u91cd\u590did" + a2.getSimpleName() + " :" + a4.getName());
            }
            k.put(a5.c(), a4);
        }
    }

    public yy() {
        yy a2;
    }

    public void fromBytes(ByteBuf a2) {
        yy a3;
        Object a4;
        boolean a5 = a2.readBoolean();
        byte[] a6 = yy.ALLATORIxDEMO(a2);
        if (a5) {
            a6 = yy.ALLATORIxDEMO(a6);
        }
        if (a6 == null) {
            return;
        }
        va a7 = new va(Unpooled.wrappedBuffer((byte[])a6));
        int a8 = a7.readInt();
        int a9 = a7.readInt();
        if (a9 == 1) {
            a4 = ALLATORIxDEMO.remove(a8);
            if (a4 != null) {
                a7 = new va(Unpooled.wrappedBuffer((byte[])a4));
            }
        } else {
            byte[] a10 = ALLATORIxDEMO.getOrDefault(a8, new byte[0]);
            a10 = Bytes.concat((byte[][])new byte[][]{a10, yy.ALLATORIxDEMO((ByteBuf)a7)});
            ALLATORIxDEMO.put(a8, a10);
            return;
        }
        a4 = (Object)a7;
        Minecraft.getMinecraft().addScheduledTask(() -> a3.ALLATORIxDEMO(a8, (va)((Object)a4)));
    }

    public static byte[] ALLATORIxDEMO(ByteBuf a2) {
        byte[] a3 = new byte[a2.readableBytes()];
        int a4 = a2.readerIndex();
        a2.getBytes(a4, a3);
        return a3;
    }

    public void toBytes(ByteBuf a2) {
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

    public IMessage onMessage(yy a2, MessageContext a3) {
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private /* synthetic */ void c(int a2, va a3) {
        try {
            Method a4 = k.get(a2);
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
                    a6[a7] = a3.readUniqueId();
                } else if (a8 == va.class) {
                    a6[a7] = a3;
                } else if (a8 == ItemStack.class) {
                    a6[a7] = a3.readItemStack();
                } else if (a8 == BlockPos.class) {
                    a6[a7] = BlockPos.fromLong((long)a3.readLong());
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
        yy a4;
        a4.c(a2, a3);
    }

    static {
        yy.ALLATORIxDEMO(yi.class);
        yy.ALLATORIxDEMO(ch.class);
        ALLATORIxDEMO = new ConcurrentHashMap();
    }
}

