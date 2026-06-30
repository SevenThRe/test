/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.ByteBufAllocator
 *  io.netty.buffer.ByteBufInputStream
 *  io.netty.buffer.ByteBufOutputStream
 *  io.netty.handler.codec.DecoderException
 *  io.netty.handler.codec.EncoderException
 *  io.netty.util.ByteProcessor
 *  javax.annotation.Nullable
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTSizeTracker
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.PacketBuffer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.ITextComponent$Serializer
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.dragoncore;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.EncoderException;
import io.netty.util.ByteProcessor;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class va
extends PacketBuffer {
    private final ByteBuf ALLATORIxDEMO;

    public va(ByteBuf a2) {
        super(a2);
        va a3;
        a3.ALLATORIxDEMO = a2;
    }

    public static int ALLATORIxDEMO(int a2) {
        for (int a3 = 1; a3 < 5; ++a3) {
            if ((a2 & -1 << a3 * 7) != 0) continue;
            return a3;
        }
        return 5;
    }

    public va writeByteArray(byte[] a2) {
        va a3;
        a3.writeVarInt(a2.length);
        a3.writeBytes(a2);
        return a3;
    }

    public byte[] func_179251_a() {
        va a2;
        return a2.func_189425_b(a2.readableBytes());
    }

    public byte[] func_189425_b(int a2) {
        va a3;
        int a4 = a3.func_150792_a();
        if (a4 > a2) {
            throw new DecoderException("ByteArray with size " + a4 + " is bigger than allowed " + a2);
        }
        byte[] a5 = new byte[a4];
        a3.readBytes(a5);
        return a5;
    }

    public va writeVarIntArray(int[] a2) {
        va a3;
        a3.writeVarInt(a2.length);
        for (int a4 : a2) {
            a3.writeVarInt(a4);
        }
        return a3;
    }

    public int[] func_186863_b() {
        va a2;
        return a2.func_189424_c(a2.readableBytes());
    }

    public int[] func_189424_c(int a2) {
        va a3;
        int a4 = a3.func_150792_a();
        if (a4 > a2) {
            throw new DecoderException("VarIntArray with size " + a4 + " is bigger than allowed " + a2);
        }
        int[] a5 = new int[a4];
        for (int a6 = 0; a6 < a5.length; ++a6) {
            a5[a6] = a3.func_150792_a();
        }
        return a5;
    }

    public va writeLongArray(long[] a2) {
        va a3;
        a3.writeVarInt(a2.length);
        for (long a4 : a2) {
            a3.writeLong(a4);
        }
        return a3;
    }

    @SideOnly(value=Side.CLIENT)
    public long[] func_186873_b(@Nullable long[] a2) {
        return this.func_189423_a(a2, this.readableBytes() / 8);
    }

    @SideOnly(value=Side.CLIENT)
    public long[] func_189423_a(@Nullable long[] array, int a2) {
        int a3 = this.func_150792_a();
        if (array == null || array.length != a3) {
            if (a3 > a2) {
                throw new DecoderException("LongArray with size " + a3 + " is bigger than allowed " + a2);
            }
            array = new long[a3];
        }
        for (int a4 = 0; a4 < array.length; ++a4) {
            array[a4] = this.readLong();
        }
        return array;
    }

    public BlockPos func_179259_c() {
        va a2;
        return BlockPos.func_177969_a((long)a2.readLong());
    }

    public va writeBlockPos(BlockPos a2) {
        va a3;
        a3.writeLong(a2.func_177986_g());
        return a3;
    }

    public ITextComponent func_179258_d() throws IOException {
        va a2;
        return ITextComponent.Serializer.func_150699_a((String)a2.func_150789_c(Short.MAX_VALUE));
    }

    public va writeTextComponent(ITextComponent a2) {
        va a3;
        return a3.writeString(ITextComponent.Serializer.func_150696_a((ITextComponent)a2));
    }

    public <T extends Enum<T>> T func_179257_a(Class<T> a2) {
        va a3;
        return (T)((Enum[])a2.getEnumConstants())[a3.func_150792_a()];
    }

    public va writeEnumValue(Enum<?> a2) {
        va a3;
        return a3.writeVarInt(a2.ordinal());
    }

    public int func_150792_a() {
        byte a2;
        int a3 = 0;
        int a4 = 0;
        do {
            va a5;
            a2 = a5.readByte();
            a3 |= (a2 & 0x7F) << a4++ * 7;
            if (a4 <= 5) continue;
            throw new RuntimeException("VarInt too big");
        } while ((a2 & 0x80) == 128);
        return a3;
    }

    public long func_179260_f() {
        byte a2;
        long a3 = 0L;
        int a4 = 0;
        do {
            va a5;
            a2 = a5.readByte();
            a3 |= (long)(a2 & 0x7F) << a4++ * 7;
            if (a4 <= 10) continue;
            throw new RuntimeException("VarLong too big");
        } while ((a2 & 0x80) == 128);
        return a3;
    }

    public va writeUniqueId(UUID a2) {
        va a3;
        a3.writeLong(a2.getMostSignificantBits());
        a3.writeLong(a2.getLeastSignificantBits());
        return a3;
    }

    public UUID func_179253_g() {
        va a2;
        return new UUID(a2.readLong(), a2.readLong());
    }

    public va writeVarInt(int a2) {
        va a3;
        while ((a2 & 0xFFFFFF80) != 0) {
            a3.writeByte(a2 & 0x7F | 0x80);
            a2 >>>= 7;
        }
        a3.writeByte(a2);
        return a3;
    }

    public va writeVarLong(long a2) {
        va a3;
        while ((a2 & 0xFFFFFFFFFFFFFF80L) != 0L) {
            a3.writeByte((int)(a2 & 0x7FL) | 0x80);
            a2 >>>= 7;
        }
        a3.writeByte((int)a2);
        return a3;
    }

    public va writeCompoundTag(@Nullable NBTTagCompound a2) {
        if (a2 == null) {
            this.writeByte(0);
        } else {
            try {
                CompressedStreamTools.func_74800_a((NBTTagCompound)a2, (DataOutput)new ByteBufOutputStream((ByteBuf)this));
            }
            catch (IOException a3) {
                throw new EncoderException((Throwable)a3);
            }
        }
        return this;
    }

    @Nullable
    public NBTTagCompound func_150793_b() throws IOException {
        va a2;
        int a3 = a2.readerIndex();
        byte a4 = a2.readByte();
        if (a4 == 0) {
            return null;
        }
        a2.readerIndex(a3);
        try {
            return CompressedStreamTools.func_152456_a((DataInput)new ByteBufInputStream((ByteBuf)a2), (NBTSizeTracker)new NBTSizeTracker(0x200000L));
        }
        catch (IOException a5) {
            throw new EncoderException((Throwable)a5);
        }
    }

    public va writeItemStack(ItemStack a2) {
        va a3;
        if (a2.func_190926_b()) {
            a3.writeShort(-1);
        } else {
            a3.writeShort(Item.func_150891_b((Item)a2.func_77973_b()));
            a3.writeByte(a2.func_190916_E());
            a3.writeShort(a2.func_77960_j());
            NBTTagCompound a4 = null;
            if (a2.func_77973_b().func_77645_m() || a2.func_77973_b().func_77651_p()) {
                a4 = a2.func_77973_b().getNBTShareTag(a2);
            }
            a3.writeCompoundTag(a4);
        }
        return a3;
    }

    public ItemStack func_150791_c() throws IOException {
        va a2;
        short a3 = a2.readShort();
        if (a3 < 0) {
            return ItemStack.field_190927_a;
        }
        byte a4 = a2.readByte();
        short a5 = a2.readShort();
        ItemStack a6 = new ItemStack(Item.func_150899_d((int)a3), (int)a4, (int)a5);
        a6.func_77973_b().readNBTShareTag(a6, a2.func_150793_b());
        return a6;
    }

    public String func_150789_c(int a2) {
        va a3;
        return a3.readString();
    }

    public String readString() {
        va a2;
        int a3 = a2.readInt();
        if (a3 < 0) {
            throw new DecoderException("The received encoded string buffer length is less than zero! Weird string!");
        }
        String a4 = a2.toString(a2.readerIndex(), a3, StandardCharsets.UTF_8);
        a2.readerIndex(a2.readerIndex() + a3);
        return a4;
    }

    public va writeString(String a2) {
        va a3;
        byte[] a4 = a2.getBytes(StandardCharsets.UTF_8);
        if (a4.length > Short.MAX_VALUE) {
            throw new EncoderException("String too big (was " + a4.length + " bytes encoded, max " + Short.MAX_VALUE + ")");
        }
        a3.writeVarInt(a4.length);
        a3.writeBytes(a4);
        return a3;
    }

    public ResourceLocation func_192575_l() {
        va a2;
        return new ResourceLocation(a2.func_150789_c(Short.MAX_VALUE));
    }

    public va writeResourceLocation(ResourceLocation a2) {
        va a3;
        a3.writeString(a2.toString());
        return a3;
    }

    public Date func_192573_m() {
        va a2;
        return new Date(a2.readLong());
    }

    public va writeTime(Date a2) {
        va a3;
        a3.writeLong(a2.getTime());
        return a3;
    }

    public int capacity() {
        va a2;
        return a2.ALLATORIxDEMO.capacity();
    }

    public ByteBuf capacity(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.capacity(a2);
    }

    public int maxCapacity() {
        va a2;
        return a2.ALLATORIxDEMO.maxCapacity();
    }

    public ByteBufAllocator alloc() {
        va a2;
        return a2.ALLATORIxDEMO.alloc();
    }

    public ByteOrder order() {
        va a2;
        return a2.ALLATORIxDEMO.order();
    }

    public ByteBuf order(ByteOrder a2) {
        va a3;
        return a3.ALLATORIxDEMO.order(a2);
    }

    public ByteBuf unwrap() {
        va a2;
        return a2.ALLATORIxDEMO.unwrap();
    }

    public boolean isDirect() {
        va a2;
        return a2.ALLATORIxDEMO.isDirect();
    }

    public boolean isReadOnly() {
        va a2;
        return a2.ALLATORIxDEMO.isReadOnly();
    }

    public ByteBuf asReadOnly() {
        va a2;
        return a2.ALLATORIxDEMO.asReadOnly();
    }

    public int readerIndex() {
        va a2;
        return a2.ALLATORIxDEMO.readerIndex();
    }

    public ByteBuf readerIndex(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.readerIndex(a2);
    }

    public int writerIndex() {
        va a2;
        return a2.ALLATORIxDEMO.writerIndex();
    }

    public ByteBuf writerIndex(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.writerIndex(a2);
    }

    public ByteBuf setIndex(int a2, int a3) {
        va a4;
        return a4.ALLATORIxDEMO.setIndex(a2, a3);
    }

    public int readableBytes() {
        va a2;
        return a2.ALLATORIxDEMO.readableBytes();
    }

    public int writableBytes() {
        va a2;
        return a2.ALLATORIxDEMO.writableBytes();
    }

    public int maxWritableBytes() {
        va a2;
        return a2.ALLATORIxDEMO.maxWritableBytes();
    }

    public boolean isReadable() {
        va a2;
        return a2.ALLATORIxDEMO.isReadable();
    }

    public boolean isReadable(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.isReadable(a2);
    }

    public boolean isWritable() {
        va a2;
        return a2.ALLATORIxDEMO.isWritable();
    }

    public boolean isWritable(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.isWritable(a2);
    }

    public ByteBuf clear() {
        va a2;
        return a2.ALLATORIxDEMO.clear();
    }

    public ByteBuf markReaderIndex() {
        va a2;
        return a2.ALLATORIxDEMO.markReaderIndex();
    }

    public ByteBuf resetReaderIndex() {
        va a2;
        return a2.ALLATORIxDEMO.resetReaderIndex();
    }

    public ByteBuf markWriterIndex() {
        va a2;
        return a2.ALLATORIxDEMO.markWriterIndex();
    }

    public ByteBuf resetWriterIndex() {
        va a2;
        return a2.ALLATORIxDEMO.resetWriterIndex();
    }

    public ByteBuf discardReadBytes() {
        va a2;
        return a2.ALLATORIxDEMO.discardReadBytes();
    }

    public ByteBuf discardSomeReadBytes() {
        va a2;
        return a2.ALLATORIxDEMO.discardSomeReadBytes();
    }

    public ByteBuf ensureWritable(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.ensureWritable(a2);
    }

    public int ensureWritable(int a2, boolean a3) {
        va a4;
        return a4.ALLATORIxDEMO.ensureWritable(a2, a3);
    }

    public boolean getBoolean(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.getBoolean(a2);
    }

    public byte getByte(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.getByte(a2);
    }

    public short getUnsignedByte(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.getUnsignedByte(a2);
    }

    public short getShort(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.getShort(a2);
    }

    public short getShortLE(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.getShortLE(a2);
    }

    public int getUnsignedShort(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.getUnsignedShort(a2);
    }

    public int getUnsignedShortLE(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.getUnsignedShortLE(a2);
    }

    public int getMedium(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.getMedium(a2);
    }

    public int getMediumLE(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.getMediumLE(a2);
    }

    public int getUnsignedMedium(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.getUnsignedMedium(a2);
    }

    public int getUnsignedMediumLE(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.getUnsignedMediumLE(a2);
    }

    public int getInt(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.getInt(a2);
    }

    public int getIntLE(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.getIntLE(a2);
    }

    public long getUnsignedInt(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.getUnsignedInt(a2);
    }

    public long getUnsignedIntLE(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.getUnsignedIntLE(a2);
    }

    public long getLong(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.getLong(a2);
    }

    public long getLongLE(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.getLongLE(a2);
    }

    public char getChar(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.getChar(a2);
    }

    public float getFloat(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.getFloat(a2);
    }

    public double getDouble(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.getDouble(a2);
    }

    public ByteBuf getBytes(int a2, ByteBuf a3) {
        va a4;
        return a4.ALLATORIxDEMO.getBytes(a2, a3);
    }

    public ByteBuf getBytes(int a2, ByteBuf a3, int a4) {
        va a5;
        return a5.ALLATORIxDEMO.getBytes(a2, a3, a4);
    }

    public ByteBuf getBytes(int a2, ByteBuf a3, int a4, int a5) {
        va a6;
        return a6.ALLATORIxDEMO.getBytes(a2, a3, a4, a5);
    }

    public ByteBuf getBytes(int a2, byte[] a3) {
        va a4;
        return a4.ALLATORIxDEMO.getBytes(a2, a3);
    }

    public ByteBuf getBytes(int a2, byte[] a3, int a4, int a5) {
        va a6;
        return a6.ALLATORIxDEMO.getBytes(a2, a3, a4, a5);
    }

    public ByteBuf getBytes(int a2, ByteBuffer a3) {
        va a4;
        return a4.ALLATORIxDEMO.getBytes(a2, a3);
    }

    public ByteBuf getBytes(int a2, OutputStream a3, int a4) throws IOException {
        va a5;
        return a5.ALLATORIxDEMO.getBytes(a2, a3, a4);
    }

    public int getBytes(int a2, GatheringByteChannel a3, int a4) throws IOException {
        va a5;
        return a5.ALLATORIxDEMO.getBytes(a2, a3, a4);
    }

    public int getBytes(int a2, FileChannel a3, long a4, int a5) throws IOException {
        va a6;
        return a6.ALLATORIxDEMO.getBytes(a2, a3, a4, a5);
    }

    public CharSequence getCharSequence(int a2, int a3, Charset a4) {
        va a5;
        return a5.ALLATORIxDEMO.getCharSequence(a2, a3, a4);
    }

    public ByteBuf setBoolean(int a2, boolean a3) {
        va a4;
        return a4.ALLATORIxDEMO.setBoolean(a2, a3);
    }

    public ByteBuf setByte(int a2, int a3) {
        va a4;
        return a4.ALLATORIxDEMO.setByte(a2, a3);
    }

    public ByteBuf setShort(int a2, int a3) {
        va a4;
        return a4.ALLATORIxDEMO.setShort(a2, a3);
    }

    public ByteBuf setShortLE(int a2, int a3) {
        va a4;
        return a4.ALLATORIxDEMO.setShortLE(a2, a3);
    }

    public ByteBuf setMedium(int a2, int a3) {
        va a4;
        return a4.ALLATORIxDEMO.setMedium(a2, a3);
    }

    public ByteBuf setMediumLE(int a2, int a3) {
        va a4;
        return a4.ALLATORIxDEMO.setMediumLE(a2, a3);
    }

    public ByteBuf setInt(int a2, int a3) {
        va a4;
        return a4.ALLATORIxDEMO.setInt(a2, a3);
    }

    public ByteBuf setIntLE(int a2, int a3) {
        va a4;
        return a4.ALLATORIxDEMO.setIntLE(a2, a3);
    }

    public ByteBuf setLong(int a2, long a3) {
        va a4;
        return a4.ALLATORIxDEMO.setLong(a2, a3);
    }

    public ByteBuf setLongLE(int a2, long a3) {
        va a4;
        return a4.ALLATORIxDEMO.setLongLE(a2, a3);
    }

    public ByteBuf setChar(int a2, int a3) {
        va a4;
        return a4.ALLATORIxDEMO.setChar(a2, a3);
    }

    public ByteBuf setFloat(int a2, float a3) {
        va a4;
        return a4.ALLATORIxDEMO.setFloat(a2, a3);
    }

    public ByteBuf setDouble(int a2, double a3) {
        va a4;
        return a4.ALLATORIxDEMO.setDouble(a2, a3);
    }

    public ByteBuf setBytes(int a2, ByteBuf a3) {
        va a4;
        return a4.ALLATORIxDEMO.setBytes(a2, a3);
    }

    public ByteBuf setBytes(int a2, ByteBuf a3, int a4) {
        va a5;
        return a5.ALLATORIxDEMO.setBytes(a2, a3, a4);
    }

    public ByteBuf setBytes(int a2, ByteBuf a3, int a4, int a5) {
        va a6;
        return a6.ALLATORIxDEMO.setBytes(a2, a3, a4, a5);
    }

    public ByteBuf setBytes(int a2, byte[] a3) {
        va a4;
        return a4.ALLATORIxDEMO.setBytes(a2, a3);
    }

    public ByteBuf setBytes(int a2, byte[] a3, int a4, int a5) {
        va a6;
        return a6.ALLATORIxDEMO.setBytes(a2, a3, a4, a5);
    }

    public ByteBuf setBytes(int a2, ByteBuffer a3) {
        va a4;
        return a4.ALLATORIxDEMO.setBytes(a2, a3);
    }

    public int setBytes(int a2, InputStream a3, int a4) throws IOException {
        va a5;
        return a5.ALLATORIxDEMO.setBytes(a2, a3, a4);
    }

    public int setBytes(int a2, ScatteringByteChannel a3, int a4) throws IOException {
        va a5;
        return a5.ALLATORIxDEMO.setBytes(a2, a3, a4);
    }

    public int setBytes(int a2, FileChannel a3, long a4, int a5) throws IOException {
        va a6;
        return a6.ALLATORIxDEMO.setBytes(a2, a3, a4, a5);
    }

    public ByteBuf setZero(int a2, int a3) {
        va a4;
        return a4.ALLATORIxDEMO.setZero(a2, a3);
    }

    public int setCharSequence(int a2, CharSequence a3, Charset a4) {
        va a5;
        return a5.ALLATORIxDEMO.setCharSequence(a2, a3, a4);
    }

    public boolean readBoolean() {
        va a2;
        return a2.ALLATORIxDEMO.readBoolean();
    }

    public byte readByte() {
        va a2;
        return a2.ALLATORIxDEMO.readByte();
    }

    public short readUnsignedByte() {
        va a2;
        return a2.ALLATORIxDEMO.readUnsignedByte();
    }

    public short readShort() {
        va a2;
        return a2.ALLATORIxDEMO.readShort();
    }

    public short readShortLE() {
        va a2;
        return a2.ALLATORIxDEMO.readShortLE();
    }

    public int readUnsignedShort() {
        va a2;
        return a2.ALLATORIxDEMO.readUnsignedShort();
    }

    public int readUnsignedShortLE() {
        va a2;
        return a2.ALLATORIxDEMO.readUnsignedShortLE();
    }

    public int readMedium() {
        va a2;
        return a2.ALLATORIxDEMO.readMedium();
    }

    public int readMediumLE() {
        va a2;
        return a2.ALLATORIxDEMO.readMediumLE();
    }

    public int readUnsignedMedium() {
        va a2;
        return a2.ALLATORIxDEMO.readUnsignedMedium();
    }

    public int readUnsignedMediumLE() {
        va a2;
        return a2.ALLATORIxDEMO.readUnsignedMediumLE();
    }

    public int readInt() {
        va a2;
        return a2.ALLATORIxDEMO.readInt();
    }

    public int readIntLE() {
        va a2;
        return a2.ALLATORIxDEMO.readIntLE();
    }

    public long readUnsignedInt() {
        va a2;
        return a2.ALLATORIxDEMO.readUnsignedInt();
    }

    public long readUnsignedIntLE() {
        va a2;
        return a2.ALLATORIxDEMO.readUnsignedIntLE();
    }

    public long readLong() {
        va a2;
        return a2.ALLATORIxDEMO.readLong();
    }

    public long readLongLE() {
        va a2;
        return a2.ALLATORIxDEMO.readLongLE();
    }

    public char readChar() {
        va a2;
        return a2.ALLATORIxDEMO.readChar();
    }

    public float readFloat() {
        va a2;
        return a2.ALLATORIxDEMO.readFloat();
    }

    public double readDouble() {
        va a2;
        return a2.ALLATORIxDEMO.readDouble();
    }

    public ByteBuf readBytes(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.readBytes(a2);
    }

    public ByteBuf readSlice(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.readSlice(a2);
    }

    public ByteBuf readRetainedSlice(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.readRetainedSlice(a2);
    }

    public ByteBuf readBytes(ByteBuf a2) {
        va a3;
        return a3.ALLATORIxDEMO.readBytes(a2);
    }

    public ByteBuf readBytes(ByteBuf a2, int a3) {
        va a4;
        return a4.ALLATORIxDEMO.readBytes(a2, a3);
    }

    public ByteBuf readBytes(ByteBuf a2, int a3, int a4) {
        va a5;
        return a5.ALLATORIxDEMO.readBytes(a2, a3, a4);
    }

    public ByteBuf readBytes(byte[] a2) {
        va a3;
        return a3.ALLATORIxDEMO.readBytes(a2);
    }

    public ByteBuf readBytes(byte[] a2, int a3, int a4) {
        va a5;
        return a5.ALLATORIxDEMO.readBytes(a2, a3, a4);
    }

    public ByteBuf readBytes(ByteBuffer a2) {
        va a3;
        return a3.ALLATORIxDEMO.readBytes(a2);
    }

    public ByteBuf readBytes(OutputStream a2, int a3) throws IOException {
        va a4;
        return a4.ALLATORIxDEMO.readBytes(a2, a3);
    }

    public int readBytes(GatheringByteChannel a2, int a3) throws IOException {
        va a4;
        return a4.ALLATORIxDEMO.readBytes(a2, a3);
    }

    public CharSequence readCharSequence(int a2, Charset a3) {
        va a4;
        return a4.ALLATORIxDEMO.readCharSequence(a2, a3);
    }

    public int readBytes(FileChannel a2, long a3, int a4) throws IOException {
        va a5;
        return a5.ALLATORIxDEMO.readBytes(a2, a3, a4);
    }

    public ByteBuf skipBytes(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.skipBytes(a2);
    }

    public ByteBuf writeBoolean(boolean a2) {
        va a3;
        return a3.ALLATORIxDEMO.writeBoolean(a2);
    }

    public ByteBuf writeByte(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.writeByte(a2);
    }

    public ByteBuf writeShort(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.writeShort(a2);
    }

    public ByteBuf writeShortLE(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.writeShortLE(a2);
    }

    public ByteBuf writeMedium(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.writeMedium(a2);
    }

    public ByteBuf writeMediumLE(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.writeMediumLE(a2);
    }

    public ByteBuf writeInt(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.writeInt(a2);
    }

    public ByteBuf writeIntLE(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.writeIntLE(a2);
    }

    public ByteBuf writeLong(long a2) {
        va a3;
        return a3.ALLATORIxDEMO.writeLong(a2);
    }

    public ByteBuf writeLongLE(long a2) {
        va a3;
        return a3.ALLATORIxDEMO.writeLongLE(a2);
    }

    public ByteBuf writeChar(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.writeChar(a2);
    }

    public ByteBuf writeFloat(float a2) {
        va a3;
        return a3.ALLATORIxDEMO.writeFloat(a2);
    }

    public ByteBuf writeDouble(double a2) {
        va a3;
        return a3.ALLATORIxDEMO.writeDouble(a2);
    }

    public ByteBuf writeBytes(ByteBuf a2) {
        va a3;
        return a3.ALLATORIxDEMO.writeBytes(a2);
    }

    public ByteBuf writeBytes(ByteBuf a2, int a3) {
        va a4;
        return a4.ALLATORIxDEMO.writeBytes(a2, a3);
    }

    public ByteBuf writeBytes(ByteBuf a2, int a3, int a4) {
        va a5;
        return a5.ALLATORIxDEMO.writeBytes(a2, a3, a4);
    }

    public ByteBuf writeBytes(byte[] a2) {
        va a3;
        return a3.ALLATORIxDEMO.writeBytes(a2);
    }

    public ByteBuf writeBytes(byte[] a2, int a3, int a4) {
        va a5;
        return a5.ALLATORIxDEMO.writeBytes(a2, a3, a4);
    }

    public ByteBuf writeBytes(ByteBuffer a2) {
        va a3;
        return a3.ALLATORIxDEMO.writeBytes(a2);
    }

    public int writeBytes(InputStream a2, int a3) throws IOException {
        va a4;
        return a4.ALLATORIxDEMO.writeBytes(a2, a3);
    }

    public int writeBytes(ScatteringByteChannel a2, int a3) throws IOException {
        va a4;
        return a4.ALLATORIxDEMO.writeBytes(a2, a3);
    }

    public int writeBytes(FileChannel a2, long a3, int a4) throws IOException {
        va a5;
        return a5.ALLATORIxDEMO.writeBytes(a2, a3, a4);
    }

    public ByteBuf writeZero(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.writeZero(a2);
    }

    public int writeCharSequence(CharSequence a2, Charset a3) {
        va a4;
        return a4.ALLATORIxDEMO.writeCharSequence(a2, a3);
    }

    public int indexOf(int a2, int a3, byte a4) {
        va a5;
        return a5.ALLATORIxDEMO.indexOf(a2, a3, a4);
    }

    public int bytesBefore(byte a2) {
        va a3;
        return a3.ALLATORIxDEMO.bytesBefore(a2);
    }

    public int bytesBefore(int a2, byte a3) {
        va a4;
        return a4.ALLATORIxDEMO.bytesBefore(a2, a3);
    }

    public int bytesBefore(int a2, int a3, byte a4) {
        va a5;
        return a5.ALLATORIxDEMO.bytesBefore(a2, a3, a4);
    }

    public int forEachByte(ByteProcessor a2) {
        va a3;
        return a3.ALLATORIxDEMO.forEachByte(a2);
    }

    public int forEachByte(int a2, int a3, ByteProcessor a4) {
        va a5;
        return a5.ALLATORIxDEMO.forEachByte(a2, a3, a4);
    }

    public int forEachByteDesc(ByteProcessor a2) {
        va a3;
        return a3.ALLATORIxDEMO.forEachByteDesc(a2);
    }

    public int forEachByteDesc(int a2, int a3, ByteProcessor a4) {
        va a5;
        return a5.ALLATORIxDEMO.forEachByteDesc(a2, a3, a4);
    }

    public ByteBuf copy() {
        va a2;
        return a2.ALLATORIxDEMO.copy();
    }

    public ByteBuf copy(int a2, int a3) {
        va a4;
        return a4.ALLATORIxDEMO.copy(a2, a3);
    }

    public ByteBuf slice() {
        va a2;
        return a2.ALLATORIxDEMO.slice();
    }

    public ByteBuf retainedSlice() {
        va a2;
        return a2.ALLATORIxDEMO.retainedSlice();
    }

    public ByteBuf slice(int a2, int a3) {
        va a4;
        return a4.ALLATORIxDEMO.slice(a2, a3);
    }

    public ByteBuf retainedSlice(int a2, int a3) {
        va a4;
        return a4.ALLATORIxDEMO.retainedSlice(a2, a3);
    }

    public ByteBuf duplicate() {
        va a2;
        return a2.ALLATORIxDEMO.duplicate();
    }

    public ByteBuf retainedDuplicate() {
        va a2;
        return a2.ALLATORIxDEMO.retainedDuplicate();
    }

    public int nioBufferCount() {
        va a2;
        return a2.ALLATORIxDEMO.nioBufferCount();
    }

    public ByteBuffer nioBuffer() {
        va a2;
        return a2.ALLATORIxDEMO.nioBuffer();
    }

    public ByteBuffer nioBuffer(int a2, int a3) {
        va a4;
        return a4.ALLATORIxDEMO.nioBuffer(a2, a3);
    }

    public ByteBuffer internalNioBuffer(int a2, int a3) {
        va a4;
        return a4.ALLATORIxDEMO.internalNioBuffer(a2, a3);
    }

    public ByteBuffer[] nioBuffers() {
        va a2;
        return a2.ALLATORIxDEMO.nioBuffers();
    }

    public ByteBuffer[] nioBuffers(int a2, int a3) {
        va a4;
        return a4.ALLATORIxDEMO.nioBuffers(a2, a3);
    }

    public boolean hasArray() {
        va a2;
        return a2.ALLATORIxDEMO.hasArray();
    }

    public byte[] array() {
        va a2;
        return a2.ALLATORIxDEMO.array();
    }

    public int arrayOffset() {
        va a2;
        return a2.ALLATORIxDEMO.arrayOffset();
    }

    public boolean hasMemoryAddress() {
        va a2;
        return a2.ALLATORIxDEMO.hasMemoryAddress();
    }

    public long memoryAddress() {
        va a2;
        return a2.ALLATORIxDEMO.memoryAddress();
    }

    public String toString(Charset a2) {
        va a3;
        return a3.ALLATORIxDEMO.toString(a2);
    }

    public String toString(int a2, int a3, Charset a4) {
        va a5;
        return a5.ALLATORIxDEMO.toString(a2, a3, a4);
    }

    public int hashCode() {
        va a2;
        return a2.ALLATORIxDEMO.hashCode();
    }

    public boolean equals(Object a2) {
        va a3;
        return a3.ALLATORIxDEMO.equals(a2);
    }

    public int compareTo(ByteBuf a2) {
        va a3;
        return a3.ALLATORIxDEMO.compareTo(a2);
    }

    public String toString() {
        va a2;
        return a2.ALLATORIxDEMO.toString();
    }

    public ByteBuf retain(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.retain(a2);
    }

    public ByteBuf retain() {
        va a2;
        return a2.ALLATORIxDEMO.retain();
    }

    public ByteBuf touch() {
        va a2;
        return a2.ALLATORIxDEMO.touch();
    }

    public ByteBuf touch(Object a2) {
        va a3;
        return a3.ALLATORIxDEMO.touch(a2);
    }

    public int refCnt() {
        va a2;
        return a2.ALLATORIxDEMO.refCnt();
    }

    public boolean release() {
        va a2;
        return a2.ALLATORIxDEMO.release();
    }

    public boolean release(int a2) {
        va a3;
        return a3.ALLATORIxDEMO.release(a2);
    }
}

