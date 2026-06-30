/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.codec.binary.Base64
 *  org.apache.commons.io.IOUtils
 */
package journeymap.common.network.impl.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

public class CompressionUtils {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String decompress(String zippedBase64Str) throws IOException {
        String result = null;
        byte[] bytes = Base64.decodeBase64((String)zippedBase64Str);
        GZIPInputStream zi = null;
        try {
            zi = new GZIPInputStream(new ByteArrayInputStream(bytes));
            result = IOUtils.toString((InputStream)zi, (Charset)StandardCharsets.UTF_8);
        }
        catch (Throwable throwable) {
            IOUtils.closeQuietly(zi);
            throw throwable;
        }
        IOUtils.closeQuietly((InputStream)zi);
        return result;
    }

    public static String compress(String str) throws IOException {
        ByteArrayOutputStream rstBao = new ByteArrayOutputStream();
        GZIPOutputStream zos = new GZIPOutputStream(rstBao);
        zos.write(str.getBytes());
        IOUtils.closeQuietly((OutputStream)zos);
        byte[] bytes = rstBao.toByteArray();
        return Base64.encodeBase64String((byte[])bytes);
    }
}

