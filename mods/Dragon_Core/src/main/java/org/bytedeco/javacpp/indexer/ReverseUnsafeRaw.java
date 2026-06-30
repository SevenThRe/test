/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.indexer.UnsafeRaw;

class ReverseUnsafeRaw
extends UnsafeRaw {
    ReverseUnsafeRaw() {
    }

    @Override
    short getShort(long address) {
        return Short.reverseBytes(super.getShort(address));
    }

    @Override
    void putShort(long address, short s2) {
        super.putShort(address, Short.reverseBytes(s2));
    }

    @Override
    int getInt(long address) {
        return Integer.reverseBytes(super.getInt(address));
    }

    @Override
    void putInt(long address, int i2) {
        super.putInt(address, Integer.reverseBytes(i2));
    }

    @Override
    long getLong(long address) {
        return Long.reverseBytes(super.getLong(address));
    }

    @Override
    void putLong(long address, long l2) {
        super.putLong(address, Long.reverseBytes(l2));
    }

    @Override
    float getFloat(long address) {
        return Float.intBitsToFloat(Integer.reverseBytes(super.getInt(address)));
    }

    @Override
    void putFloat(long address, float f2) {
        super.putFloat(address, Integer.reverseBytes(Float.floatToRawIntBits(f2)));
    }

    @Override
    double getDouble(long address) {
        return Double.longBitsToDouble(Long.reverseBytes(super.getLong(address)));
    }

    @Override
    void putDouble(long address, double d2) {
        super.putDouble(address, Long.reverseBytes(Double.doubleToRawLongBits(d2)));
    }

    @Override
    char getChar(long address) {
        return Character.reverseBytes(super.getChar(address));
    }

    @Override
    void putChar(long address, char c2) {
        super.putChar(address, Character.reverseBytes(c2));
    }

    @Override
    short getShort(byte[] array, long offset) {
        return Short.reverseBytes(super.getShort(array, offset));
    }

    @Override
    void putShort(byte[] array, long offset, short s2) {
        super.putShort(array, offset, Short.reverseBytes(s2));
    }

    @Override
    int getInt(byte[] array, long offset) {
        return Integer.reverseBytes(super.getInt(array, offset));
    }

    @Override
    void putInt(byte[] array, long offset, int i2) {
        super.putInt(array, offset, Integer.reverseBytes(i2));
    }

    @Override
    long getLong(byte[] array, long offset) {
        return Long.reverseBytes(super.getLong(array, offset));
    }

    @Override
    void putLong(byte[] array, long offset, long l2) {
        super.putLong(array, offset, Long.reverseBytes(l2));
    }

    @Override
    float getFloat(byte[] array, long offset) {
        return Float.intBitsToFloat(Integer.reverseBytes(super.getInt(array, offset)));
    }

    @Override
    void putFloat(byte[] array, long offset, float f2) {
        super.putFloat(array, offset, Integer.reverseBytes(Float.floatToRawIntBits(f2)));
    }

    @Override
    double getDouble(byte[] array, long offset) {
        return Double.longBitsToDouble(Long.reverseBytes(super.getLong(array, offset)));
    }

    @Override
    void putDouble(byte[] array, long offset, double d2) {
        super.putDouble(array, offset, Long.reverseBytes(Double.doubleToRawLongBits(d2)));
    }

    @Override
    char getChar(byte[] array, long offset) {
        return Character.reverseBytes(super.getChar(array, offset));
    }

    @Override
    void putChar(byte[] array, long offset, char c2) {
        super.putChar(array, offset, Character.reverseBytes(c2));
    }
}

