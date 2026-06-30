/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.ByteBufInputStream
 *  io.netty.buffer.ByteBufOutputStream
 *  io.netty.handler.codec.EncoderException
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTSizeTracker
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 */
package eos.moe.dragoncore;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.handler.codec.EncoderException;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class bp {
    public bp() {
        bp a2;
    }

    public static NBTTagCompound ALLATORIxDEMO(DataInputStream a2) throws IOException {
        return CompressedStreamTools.read((DataInputStream)a2);
    }

    public static void ALLATORIxDEMO(NBTTagCompound a2, DataOutput a3) throws IOException {
        CompressedStreamTools.write((NBTTagCompound)a2, (DataOutput)a3);
    }

    public static Map<String, NBTBase> ALLATORIxDEMO(NBTTagCompound a2) {
        return (Map)ReflectionHelper.getPrivateValue(NBTTagCompound.class, (Object)a2, (int)2);
    }

    public static NBTBase ALLATORIxDEMO(NBTTagList a2, int a3) {
        List a4 = (List)ReflectionHelper.getPrivateValue(NBTTagList.class, (Object)a2, (int)1);
        return (NBTBase)a4.get(a3);
    }

    public static void ALLATORIxDEMO(NBTTagCompound a2, ByteBuf a3) {
        if (a2 == null) {
            a3.writeByte(0);
        } else {
            try {
                CompressedStreamTools.write((NBTTagCompound)a2, (DataOutput)new ByteBufOutputStream(a3));
            }
            catch (IOException a4) {
                throw new EncoderException((Throwable)a4);
            }
        }
    }

    public static NBTTagCompound ALLATORIxDEMO(ByteBuf a2) {
        int a3 = a2.readerIndex();
        byte a4 = a2.readByte();
        if (a4 == 0) {
            return null;
        }
        a2.readerIndex(a3);
        try {
            return CompressedStreamTools.read((DataInput)new ByteBufInputStream(a2), (NBTSizeTracker)new NBTSizeTracker(0x200000L));
        }
        catch (IOException a5) {
            throw new EncoderException((Throwable)a5);
        }
    }
}

