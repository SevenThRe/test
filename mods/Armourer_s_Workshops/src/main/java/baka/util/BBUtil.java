/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 */
package baka.util;

import io.netty.buffer.ByteBuf;

public abstract class BBUtil {
    public static void writeBool(ByteBuf buf, boolean value) {
        buf.writeByte(value ? 1 : 0);
    }

    public static boolean readBool(ByteBuf buf) {
        return buf.readByte() == 1;
    }
}

