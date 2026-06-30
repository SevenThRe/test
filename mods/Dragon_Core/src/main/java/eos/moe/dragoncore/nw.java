/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.fml.common.network.NetworkRegistry
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraftforge.fml.relauncher.Side
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.bp;
import eos.moe.dragoncore.bv;
import eos.moe.dragoncore.yy;
import io.netty.buffer.ByteBuf;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.zip.GZIPOutputStream;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class nw {
    public static SimpleNetworkWrapper ALLATORIxDEMO = NetworkRegistry.INSTANCE.newSimpleChannel("dragoncore:main");

    public nw() {
        nw a2;
    }

    public static void ALLATORIxDEMO() {
        ALLATORIxDEMO.registerMessage(bv.class, bv.class, 64, Side.SERVER);
        ALLATORIxDEMO.registerMessage(bv.class, bv.class, 64, Side.CLIENT);
        ALLATORIxDEMO.registerMessage(yy.class, yy.class, 63, Side.CLIENT);
    }

    public static void ALLATORIxDEMO(HashMap<String, String> a2) {
        ALLATORIxDEMO.sendToServer((IMessage)new bv(3, a3 -> {
            a3.writeInt(a2.size());
            for (Map.Entry a4 : a2.entrySet()) {
                a3.writeString((String)a4.getKey());
                a3.writeString((String)a4.getValue());
            }
        }));
    }

    public static void c(UUID a2) {
        ALLATORIxDEMO.sendToServer((IMessage)new bv(4, a3 -> a3.writeUniqueId(a2)));
    }

    public static void ALLATORIxDEMO(UUID a2) {
        ALLATORIxDEMO.sendToServer((IMessage)new bv(11, a3 -> a3.writeUniqueId(a2)));
    }

    public static void ALLATORIxDEMO(String a2, Set<String> a3) {
        ALLATORIxDEMO.sendToServer((IMessage)new bv(5, a4 -> {
            a4.writeString(a2);
            a4.writeInt(a3.size());
            for (String a5 : a3) {
                a4.writeString(a5);
            }
        }));
    }

    public static void ALLATORIxDEMO(String a2) {
        ALLATORIxDEMO.sendToServer((IMessage)new bv(12, a3 -> a3.writeString(a2)));
    }

    public static void ALLATORIxDEMO(String a2, String a3) {
        ALLATORIxDEMO.sendToServer((IMessage)new bv(6, a4 -> {
            a4.writeString(a2);
            a4.writeString(a3);
        }));
    }

    public static void ALLATORIxDEMO(int a2, String a3) {
        ALLATORIxDEMO.sendToServer((IMessage)new bv(7, a4 -> {
            a4.writeInt(a2);
            a4.writeString(a3);
        }));
    }

    public static void ALLATORIxDEMO(int a2, int a3, Vec3d a4) {
        ALLATORIxDEMO.sendToServer((IMessage)new bv(9, a5 -> {
            a5.writeInt(a2);
            a5.writeInt(a3);
            if (a4 != null) {
                a5.writeFloat((float)a4.x);
                a5.writeFloat((float)a4.y);
                a5.writeFloat((float)a4.z);
            }
        }));
    }

    public static void ALLATORIxDEMO(int a2, int a3) {
        ALLATORIxDEMO.sendToServer((IMessage)new bv(10, a4 -> {
            a4.writeInt(a2);
            a4.writeInt(a3);
        }));
    }

    public static void ALLATORIxDEMO(String a2, String ... a3) {
        ALLATORIxDEMO.sendToServer((IMessage)new bv(100, a4 -> {
            a4.writeString(a2);
            a4.writeInt(a3.length);
            for (String a5 : a3) {
                a4.writeString(a5);
            }
        }));
    }

    public static void ALLATORIxDEMO(BlockPos a2) {
        ALLATORIxDEMO.sendToServer((IMessage)new bv(1001, a3 -> a3.writeLong(a2.toLong())));
    }

    public static void ALLATORIxDEMO(int a2) {
        ALLATORIxDEMO.sendToServer((IMessage)new bv(1002, a3 -> a3.writeInt(a2)));
    }

    public static void ALLATORIxDEMO(BlockPos a2, NBTTagCompound a3) {
        ALLATORIxDEMO.sendToServer((IMessage)new bv(1003, a4 -> {
            a4.writeLong(a2.toLong());
            bp.ALLATORIxDEMO(a3, (ByteBuf)a4);
        }));
    }

    public static void ALLATORIxDEMO(int a2, NBTTagCompound a3) {
        ALLATORIxDEMO.sendToServer((IMessage)new bv(1004, a4 -> {
            a4.writeInt(a2);
            bp.ALLATORIxDEMO(a3, (ByteBuf)a4);
        }));
    }

    public static void ALLATORIxDEMO(NBTTagCompound a2) {
        ALLATORIxDEMO.sendToServer((IMessage)new bv(1005, a3 -> bp.ALLATORIxDEMO(a2, (ByteBuf)a3)));
    }

    public static void ALLATORIxDEMO(Map<String, String> a2) {
        StringBuilder a4 = new StringBuilder();
        for (Map.Entry<String, String> entry : a2.entrySet()) {
            a4.append(entry.getKey()).append(",").append(entry.getValue()).append("|");
        }
        byte[] a5 = nw.ALLATORIxDEMO(a4.toString().getBytes(StandardCharsets.UTF_8));
        if (a5 == null) {
            return;
        }
        List<byte[]> list = nw.ALLATORIxDEMO(a5, 30000);
        if (list.size() == 0) {
            nw.ALLATORIxDEMO.sendToServer((IMessage)new bv(13, a4 -> {
                a4.writeBoolean(a3);
                a4.writeInt(a2.length);
                a4.writeBytes(a2);
            }));
        } else {
            for (int a6 = 0; a6 < list.size(); ++a6) {
                nw.ALLATORIxDEMO.sendToServer((IMessage)new bv(13, a4 -> {
                    a4.writeBoolean(a3);
                    a4.writeInt(a2.length);
                    a4.writeBytes(a2);
                }));
            }
        }
    }

    public static List<byte[]> ALLATORIxDEMO(byte[] a2, int a3) {
        ArrayList<byte[]> a4 = new ArrayList<byte[]>();
        int a5 = 0;
        while (a5 < a2.length) {
            int a6 = a5;
            a5 = Math.min(a5 + a3, a2.length);
            byte[] a7 = Arrays.copyOfRange(a2, a6, a5);
            a4.add(a7);
        }
        return a4;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static byte[] ALLATORIxDEMO(byte[] a2) {
        ByteArrayOutputStream a3 = new ByteArrayOutputStream();
        GZIPOutputStream a4 = null;
        try {
            a4 = new GZIPOutputStream(a3);
            a4.write(a2);
            a4.close();
        }
        catch (IOException a5) {
            a5.printStackTrace();
            nw.ALLATORIxDEMO(a3);
            nw.ALLATORIxDEMO(a4);
            byte[] byArray = null;
            return byArray;
        }
        finally {
            nw.ALLATORIxDEMO(a3);
            nw.ALLATORIxDEMO(a4);
        }
        return a3.toByteArray();
    }

    public static void ALLATORIxDEMO(Closeable a2) {
        try {
            if (a2 != null) {
                a2.close();
            }
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }
}

