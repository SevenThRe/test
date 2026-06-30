/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import java.lang.reflect.Field;
import org.bytedeco.javacpp.indexer.Raw;
import sun.misc.Unsafe;

class UnsafeRaw
extends Raw {
    protected static final Unsafe UNSAFE;
    protected static final long arrayOffset;

    UnsafeRaw() {
    }

    static boolean isAvailable() {
        return UNSAFE != null;
    }

    @Override
    byte getByte(long address) {
        return UNSAFE.getByte(address);
    }

    @Override
    void putByte(long address, byte b2) {
        UNSAFE.putByte(address, b2);
    }

    @Override
    short getShort(long address) {
        return UNSAFE.getShort(address);
    }

    @Override
    void putShort(long address, short s2) {
        UNSAFE.putShort(address, s2);
    }

    @Override
    int getInt(long address) {
        return UNSAFE.getInt(address);
    }

    @Override
    void putInt(long address, int i2) {
        UNSAFE.putInt(address, i2);
    }

    @Override
    long getLong(long address) {
        return UNSAFE.getLong(address);
    }

    @Override
    void putLong(long address, long l2) {
        UNSAFE.putLong(address, l2);
    }

    @Override
    float getFloat(long address) {
        return UNSAFE.getFloat(address);
    }

    @Override
    void putFloat(long address, float f2) {
        UNSAFE.putFloat(address, f2);
    }

    @Override
    double getDouble(long address) {
        return UNSAFE.getDouble(address);
    }

    @Override
    void putDouble(long address, double d2) {
        UNSAFE.putDouble(address, d2);
    }

    @Override
    char getChar(long address) {
        return UNSAFE.getChar(address);
    }

    @Override
    void putChar(long address, char c2) {
        UNSAFE.putChar(address, c2);
    }

    @Override
    boolean getBoolean(long address) {
        return UNSAFE.getByte(address) != 0;
    }

    @Override
    void putBoolean(long address, boolean b2) {
        UNSAFE.putByte(address, b2 ? (byte)1 : 0);
    }

    @Override
    byte getByte(byte[] array, long offset) {
        return UNSAFE.getByte(array, arrayOffset + offset);
    }

    @Override
    void putByte(byte[] array, long offset, byte b2) {
        UNSAFE.putByte(array, arrayOffset + offset, b2);
    }

    @Override
    short getShort(byte[] array, long offset) {
        return UNSAFE.getShort(array, arrayOffset + offset);
    }

    @Override
    void putShort(byte[] array, long offset, short s2) {
        UNSAFE.putShort(array, arrayOffset + offset, s2);
    }

    @Override
    int getInt(byte[] array, long offset) {
        return UNSAFE.getInt(array, arrayOffset + offset);
    }

    @Override
    void putInt(byte[] array, long offset, int i2) {
        UNSAFE.putInt(array, arrayOffset + offset, i2);
    }

    @Override
    long getLong(byte[] array, long offset) {
        return UNSAFE.getLong(array, arrayOffset + offset);
    }

    @Override
    void putLong(byte[] array, long offset, long l2) {
        UNSAFE.putLong(array, arrayOffset + offset, l2);
    }

    @Override
    float getFloat(byte[] array, long offset) {
        return UNSAFE.getFloat(array, arrayOffset + offset);
    }

    @Override
    void putFloat(byte[] array, long offset, float f2) {
        UNSAFE.putFloat(array, arrayOffset + offset, f2);
    }

    @Override
    double getDouble(byte[] array, long offset) {
        return UNSAFE.getDouble(array, arrayOffset + offset);
    }

    @Override
    void putDouble(byte[] array, long offset, double d2) {
        UNSAFE.putDouble(array, arrayOffset + offset, d2);
    }

    @Override
    char getChar(byte[] array, long offset) {
        return UNSAFE.getChar(array, arrayOffset + offset);
    }

    @Override
    void putChar(byte[] array, long offset, char c2) {
        UNSAFE.putChar(array, arrayOffset + offset, c2);
    }

    @Override
    boolean getBoolean(byte[] array, long offset) {
        return UNSAFE.getBoolean(array, arrayOffset + offset);
    }

    @Override
    void putBoolean(byte[] array, long offset, boolean b2) {
        UNSAFE.putBoolean(array, arrayOffset + offset, b2);
    }

    static {
        long offset;
        Unsafe o2;
        try {
            Class<?> c2 = Class.forName("sun.misc.Unsafe");
            Field f2 = c2.getDeclaredField("theUnsafe");
            c2.getDeclaredMethod("getByte", Long.TYPE);
            c2.getDeclaredMethod("getShort", Long.TYPE);
            c2.getDeclaredMethod("getInt", Long.TYPE);
            c2.getDeclaredMethod("getLong", Long.TYPE);
            c2.getDeclaredMethod("getFloat", Long.TYPE);
            c2.getDeclaredMethod("getDouble", Long.TYPE);
            c2.getDeclaredMethod("getChar", Long.TYPE);
            c2.getDeclaredMethod("arrayBaseOffset", Class.class);
            f2.setAccessible(true);
            o2 = (Unsafe)f2.get(null);
            offset = o2.arrayBaseOffset(byte[].class);
        }
        catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException | NoSuchMethodException | SecurityException ex2) {
            o2 = null;
            offset = 0L;
        }
        UNSAFE = o2;
        arrayOffset = offset;
    }
}

