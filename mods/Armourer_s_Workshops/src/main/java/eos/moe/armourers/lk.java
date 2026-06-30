/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  org.apache.commons.io.IOUtils
 *  org.apache.logging.log4j.Level
 */
package eos.moe.armourers;

import eos.moe.armourers.bm;
import eos.moe.armourers.fg;
import eos.moe.armourers.yl;
import io.netty.buffer.ByteBuf;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Level;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class lk {
    public static void r(ByteBuf a2, byte[] a3) {
        a2.writeInt(a3.length);
        a2.writeBytes(a3);
    }

    public lk() {
        lk a2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static yl r(ByteBuf a2) {
        ByteBuf byteBuf = a2;
        boolean bl = byteBuf.readBoolean();
        Object object = lk.r(byteBuf);
        if (bl) {
            object = lk.r(object);
        }
        if (object == null) {
            bm.r(Level.ERROR, "Failed to decompress skin data.");
            return null;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream((byte[])object);
        object = new DataInputStream(byteArrayInputStream);
        yl yl2 = null;
        try {
            yl2 = fg.r((DataInputStream)object);
            return yl2;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return yl2;
        }
        finally {
            IOUtils.closeQuietly((InputStream)object);
            IOUtils.closeQuietly((InputStream)byteArrayInputStream);
        }
    }

    public static void r(ByteBuf a2, UUID a3) {
        a2.writeLong(a3.getMostSignificantBits());
        a2.writeLong(a3.getLeastSignificantBits());
    }

    public static UUID r(ByteBuf a2) {
        ByteBuf byteBuf = a2;
        long l2 = byteBuf.readLong();
        long l3 = byteBuf.readLong();
        return new UUID(l2, l3);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static byte[] r(byte[] a2) {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        InputStream inputStream = null;
        try {
            IOUtils.copy((InputStream)new GZIPInputStream(new ByteArrayInputStream(a2)), (OutputStream)byteArrayOutputStream2);
            byteArrayOutputStream = byteArrayOutputStream2;
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
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

    public static byte[] r(ByteBuf a2) {
        ByteBuf byteBuf = a2;
        byte[] byArray = new byte[byteBuf.readInt()];
        byteBuf.readBytes(byArray);
        return byArray;
    }
}

