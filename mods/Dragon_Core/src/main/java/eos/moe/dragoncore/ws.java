/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraftforge.fml.common.network.ByteBufUtils
 *  org.apache.commons.io.IOUtils
 */
package eos.moe.dragoncore;

import io.netty.buffer.ByteBuf;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import org.apache.commons.io.IOUtils;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ws {
    public ws() {
        ws a2;
    }

    public static void ALLATORIxDEMO(ByteBuf a2, UUID a3) {
        a2.writeLong(a3.getMostSignificantBits());
        a2.writeLong(a3.getLeastSignificantBits());
    }

    public static UUID ALLATORIxDEMO(ByteBuf a2) {
        long a3 = a2.readLong();
        long a4 = a2.readLong();
        return new UUID(a3, a4);
    }

    public static void ALLATORIxDEMO(ByteBuf a2, String[] a3) {
        a2.writeInt(a3.length);
        for (int a4 = 0; a4 < a3.length; ++a4) {
            ByteBufUtils.writeUTF8String((ByteBuf)a2, (String)a3[a4]);
        }
    }

    public static String[] ALLATORIxDEMO(ByteBuf a2) {
        int a3 = a2.readInt();
        String[] a4 = new String[a3];
        for (int a5 = 0; a5 < a3; ++a5) {
            a4[a5] = ByteBufUtils.readUTF8String((ByteBuf)a2);
        }
        return a4;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static /* synthetic */ byte[] c(byte[] a2) {
        ByteArrayOutputStream a3 = new ByteArrayOutputStream();
        GZIPOutputStream a4 = null;
        try {
            a4 = new GZIPOutputStream(a3);
            a4.write(a2);
            a4.close();
        }
        catch (IOException a5) {
            byte[] byArray;
            try {
                a5.printStackTrace();
                IOUtils.closeQuietly((OutputStream)a4);
                IOUtils.closeQuietly((OutputStream)a3);
                byArray = null;
            }
            catch (Throwable throwable) {
                IOUtils.closeQuietly(a4);
                IOUtils.closeQuietly((OutputStream)a3);
                throw throwable;
            }
            IOUtils.closeQuietly((OutputStream)a4);
            IOUtils.closeQuietly((OutputStream)a3);
            return byArray;
        }
        IOUtils.closeQuietly((OutputStream)a4);
        IOUtils.closeQuietly((OutputStream)a3);
        byte[] a6 = a3.toByteArray();
        return a6;
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
            a5.printStackTrace();
            byte[] byArray = null;
            return byArray;
        }
        finally {
            IOUtils.closeQuietly(a4);
            IOUtils.closeQuietly((OutputStream)a3);
        }
        return a3.toByteArray();
    }

    public static void ALLATORIxDEMO(ByteBuf a2, byte[] a3) {
        a2.writeInt(a3.length);
        a2.writeBytes(a3);
    }

    public static byte[] ALLATORIxDEMO(ByteBuf a2) {
        int a3 = a2.readInt();
        byte[] a4 = new byte[a3];
        a2.readBytes(a4);
        return a4;
    }

    private static /* synthetic */ void ALLATORIxDEMO(DataOutputStream a2, byte[] a3) throws IOException {
        a2.writeInt(a3.length);
        a2.write(a3);
    }

    private static /* synthetic */ byte[] ALLATORIxDEMO(DataInputStream a2) throws IOException {
        int a3 = a2.readInt();
        byte[] a4 = new byte[a3];
        a2.read(a4);
        return a4;
    }
}

