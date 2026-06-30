/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.kotlin.spark.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public final class IOUtils {
    public static final String LINE_SEPARATOR;
    private static final int DEFAULT_BUFFER_SIZE = 4096;

    private IOUtils() {
    }

    public static String toString(InputStream input) throws IOException {
        StringWriter sw = new StringWriter();
        IOUtils.copy(input, (Writer)sw);
        return sw.toString();
    }

    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = input.read(buf);
        while (n != -1) {
            os.write(buf, 0, n);
            n = input.read(buf);
        }
        return os.toByteArray();
    }

    public static int copy(InputStream input, OutputStream output) throws IOException {
        long count = IOUtils.copyLarge(input, output);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int)count;
    }

    public static long copyLarge(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[4096];
        long count = 0L;
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += (long)n;
        }
        return count;
    }

    public static void copy(InputStream input, Writer output) throws IOException {
        InputStreamReader in = new InputStreamReader(input);
        IOUtils.copy(in, output);
    }

    public static int copy(Reader input, Writer output) throws IOException {
        long count = IOUtils.copyLarge(input, output);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int)count;
    }

    public static long copyLarge(Reader input, Writer output) throws IOException {
        char[] buffer = new char[4096];
        long count = 0L;
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += (long)n;
        }
        return count;
    }

    static {
        StringWriter buf = new StringWriter(4);
        PrintWriter out = new PrintWriter(buf);
        out.println();
        LINE_SEPARATOR = buf.toString();
    }
}

