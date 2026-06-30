/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.ar.com.hjg.pngj.chunks;

import info.journeymap.shaded.ar.com.hjg.pngj.PngHelperInternal;
import info.journeymap.shaded.ar.com.hjg.pngj.PngjException;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.ChunkLoadBehaviour;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.ChunkPredicate;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngBadCharsetException;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunk;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkSPLT;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkTextVar;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkUNKNOWN;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ChunkHelper {
    public static final String IHDR = "IHDR";
    public static final String PLTE = "PLTE";
    public static final String IDAT = "IDAT";
    public static final String IEND = "IEND";
    public static final String cHRM = "cHRM";
    public static final String gAMA = "gAMA";
    public static final String iCCP = "iCCP";
    public static final String sBIT = "sBIT";
    public static final String sRGB = "sRGB";
    public static final String bKGD = "bKGD";
    public static final String hIST = "hIST";
    public static final String tRNS = "tRNS";
    public static final String pHYs = "pHYs";
    public static final String sPLT = "sPLT";
    public static final String tIME = "tIME";
    public static final String iTXt = "iTXt";
    public static final String tEXt = "tEXt";
    public static final String zTXt = "zTXt";
    public static final byte[] b_IHDR = ChunkHelper.toBytes("IHDR");
    public static final byte[] b_PLTE = ChunkHelper.toBytes("PLTE");
    public static final byte[] b_IDAT = ChunkHelper.toBytes("IDAT");
    public static final byte[] b_IEND = ChunkHelper.toBytes("IEND");
    private static byte[] tmpbuffer = new byte[4096];

    ChunkHelper() {
    }

    public static byte[] toBytes(String x) {
        try {
            return x.getBytes(PngHelperInternal.charsetLatin1name);
        }
        catch (UnsupportedEncodingException e) {
            throw new PngBadCharsetException(e);
        }
    }

    public static String toString(byte[] x) {
        try {
            return new String(x, PngHelperInternal.charsetLatin1name);
        }
        catch (UnsupportedEncodingException e) {
            throw new PngBadCharsetException(e);
        }
    }

    public static String toString(byte[] x, int offset, int len) {
        try {
            return new String(x, offset, len, PngHelperInternal.charsetLatin1name);
        }
        catch (UnsupportedEncodingException e) {
            throw new PngBadCharsetException(e);
        }
    }

    public static byte[] toBytesUTF8(String x) {
        try {
            return x.getBytes(PngHelperInternal.charsetUTF8name);
        }
        catch (UnsupportedEncodingException e) {
            throw new PngBadCharsetException(e);
        }
    }

    public static String toStringUTF8(byte[] x) {
        try {
            return new String(x, PngHelperInternal.charsetUTF8name);
        }
        catch (UnsupportedEncodingException e) {
            throw new PngBadCharsetException(e);
        }
    }

    public static String toStringUTF8(byte[] x, int offset, int len) {
        try {
            return new String(x, offset, len, PngHelperInternal.charsetUTF8name);
        }
        catch (UnsupportedEncodingException e) {
            throw new PngBadCharsetException(e);
        }
    }

    public static boolean isCritical(String id) {
        return Character.isUpperCase(id.charAt(0));
    }

    public static boolean isPublic(String id) {
        return Character.isUpperCase(id.charAt(1));
    }

    public static boolean isSafeToCopy(String id) {
        return !Character.isUpperCase(id.charAt(3));
    }

    public static boolean isUnknown(PngChunk c) {
        return c instanceof PngChunkUNKNOWN;
    }

    public static int posNullByte(byte[] b) {
        for (int i = 0; i < b.length; ++i) {
            if (b[i] != 0) continue;
            return i;
        }
        return -1;
    }

    public static boolean shouldLoad(String id, ChunkLoadBehaviour behav) {
        if (ChunkHelper.isCritical(id)) {
            return true;
        }
        switch (behav) {
            case LOAD_CHUNK_ALWAYS: {
                return true;
            }
            case LOAD_CHUNK_IF_SAFE: {
                return ChunkHelper.isSafeToCopy(id);
            }
            case LOAD_CHUNK_NEVER: {
                return false;
            }
        }
        return false;
    }

    public static final byte[] compressBytes(byte[] ori, boolean compress) {
        return ChunkHelper.compressBytes(ori, 0, ori.length, compress);
    }

    public static byte[] compressBytes(byte[] ori, int offset, int len, boolean compress) {
        try {
            ByteArrayInputStream inb = new ByteArrayInputStream(ori, offset, len);
            InputStream in = compress ? inb : new InflaterInputStream(inb);
            ByteArrayOutputStream outb = new ByteArrayOutputStream();
            OutputStream out = compress ? new DeflaterOutputStream(outb) : outb;
            ChunkHelper.shovelInToOut(in, out);
            in.close();
            out.close();
            return outb.toByteArray();
        }
        catch (Exception e) {
            throw new PngjException(e);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void shovelInToOut(InputStream in, OutputStream out) throws IOException {
        byte[] byArray = tmpbuffer;
        synchronized (tmpbuffer) {
            int len;
            while ((len = in.read(tmpbuffer)) > 0) {
                out.write(tmpbuffer, 0, len);
            }
            // ** MonitorExit[var2_2] (shouldn't be in output)
            return;
        }
    }

    public static List<PngChunk> filterList(List<PngChunk> target, ChunkPredicate predicateKeep) {
        ArrayList<PngChunk> result = new ArrayList<PngChunk>();
        for (PngChunk element : target) {
            if (!predicateKeep.match(element)) continue;
            result.add(element);
        }
        return result;
    }

    public static int trimList(List<PngChunk> target, ChunkPredicate predicateRemove) {
        Iterator<PngChunk> it = target.iterator();
        int cont = 0;
        while (it.hasNext()) {
            PngChunk c = it.next();
            if (!predicateRemove.match(c)) continue;
            it.remove();
            ++cont;
        }
        return cont;
    }

    public static final boolean equivalent(PngChunk c1, PngChunk c2) {
        if (c1 == c2) {
            return true;
        }
        if (c1 == null || c2 == null || !c1.id.equals(c2.id)) {
            return false;
        }
        if (c1.crit) {
            return false;
        }
        if (c1.getClass() != c2.getClass()) {
            return false;
        }
        if (!c2.allowsMultiple()) {
            return true;
        }
        if (c1 instanceof PngChunkTextVar) {
            return ((PngChunkTextVar)c1).getKey().equals(((PngChunkTextVar)c2).getKey());
        }
        if (c1 instanceof PngChunkSPLT) {
            return ((PngChunkSPLT)c1).getPalName().equals(((PngChunkSPLT)c2).getPalName());
        }
        return false;
    }

    public static boolean isText(PngChunk c) {
        return c instanceof PngChunkTextVar;
    }
}

