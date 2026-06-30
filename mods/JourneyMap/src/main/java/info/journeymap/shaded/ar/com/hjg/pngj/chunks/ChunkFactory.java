/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.ar.com.hjg.pngj.chunks;

import info.journeymap.shaded.ar.com.hjg.pngj.IChunkFactory;
import info.journeymap.shaded.ar.com.hjg.pngj.ImageInfo;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.ChunkRaw;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunk;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkBKGD;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkCHRM;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkGAMA;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkHIST;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkICCP;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkIDAT;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkIEND;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkIHDR;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkITXT;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkOFFS;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkPHYS;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkPLTE;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkSBIT;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkSPLT;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkSRGB;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkSTER;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkTEXT;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkTIME;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkTRNS;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkUNKNOWN;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkZTXT;

public class ChunkFactory
implements IChunkFactory {
    boolean parse;

    public ChunkFactory() {
        this(true);
    }

    public ChunkFactory(boolean parse) {
        this.parse = parse;
    }

    public final PngChunk createChunk(ChunkRaw chunkRaw, ImageInfo imgInfo) {
        PngChunk c = this.createEmptyChunkKnown(chunkRaw.id, imgInfo);
        if (c == null) {
            c = this.createEmptyChunkExtended(chunkRaw.id, imgInfo);
        }
        if (c == null) {
            c = this.createEmptyChunkUnknown(chunkRaw.id, imgInfo);
        }
        c.setRaw(chunkRaw);
        if (this.parse && chunkRaw.data != null) {
            c.parseFromRaw(chunkRaw);
        }
        return c;
    }

    protected final PngChunk createEmptyChunkKnown(String id, ImageInfo imgInfo) {
        if (id.equals("IDAT")) {
            return new PngChunkIDAT(imgInfo);
        }
        if (id.equals("IHDR")) {
            return new PngChunkIHDR(imgInfo);
        }
        if (id.equals("PLTE")) {
            return new PngChunkPLTE(imgInfo);
        }
        if (id.equals("IEND")) {
            return new PngChunkIEND(imgInfo);
        }
        if (id.equals("tEXt")) {
            return new PngChunkTEXT(imgInfo);
        }
        if (id.equals("iTXt")) {
            return new PngChunkITXT(imgInfo);
        }
        if (id.equals("zTXt")) {
            return new PngChunkZTXT(imgInfo);
        }
        if (id.equals("bKGD")) {
            return new PngChunkBKGD(imgInfo);
        }
        if (id.equals("gAMA")) {
            return new PngChunkGAMA(imgInfo);
        }
        if (id.equals("pHYs")) {
            return new PngChunkPHYS(imgInfo);
        }
        if (id.equals("iCCP")) {
            return new PngChunkICCP(imgInfo);
        }
        if (id.equals("tIME")) {
            return new PngChunkTIME(imgInfo);
        }
        if (id.equals("tRNS")) {
            return new PngChunkTRNS(imgInfo);
        }
        if (id.equals("cHRM")) {
            return new PngChunkCHRM(imgInfo);
        }
        if (id.equals("sBIT")) {
            return new PngChunkSBIT(imgInfo);
        }
        if (id.equals("sRGB")) {
            return new PngChunkSRGB(imgInfo);
        }
        if (id.equals("hIST")) {
            return new PngChunkHIST(imgInfo);
        }
        if (id.equals("sPLT")) {
            return new PngChunkSPLT(imgInfo);
        }
        return null;
    }

    protected final PngChunk createEmptyChunkUnknown(String id, ImageInfo imgInfo) {
        return new PngChunkUNKNOWN(id, imgInfo);
    }

    protected PngChunk createEmptyChunkExtended(String id, ImageInfo imgInfo) {
        if (id.equals("oFFs")) {
            return new PngChunkOFFS(imgInfo);
        }
        if (id.equals("sTER")) {
            return new PngChunkSTER(imgInfo);
        }
        return null;
    }
}

