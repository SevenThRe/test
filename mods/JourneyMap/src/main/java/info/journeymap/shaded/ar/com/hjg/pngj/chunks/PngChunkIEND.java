/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.ar.com.hjg.pngj.chunks;

import info.journeymap.shaded.ar.com.hjg.pngj.ImageInfo;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.ChunkHelper;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.ChunkRaw;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunk;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkSingle;

public class PngChunkIEND
extends PngChunkSingle {
    public static final String ID = "IEND";

    public PngChunkIEND(ImageInfo info) {
        super(ID, info);
    }

    public PngChunk.ChunkOrderingConstraint getOrderingConstraint() {
        return PngChunk.ChunkOrderingConstraint.NA;
    }

    public ChunkRaw createRawChunk() {
        ChunkRaw c = new ChunkRaw(0, ChunkHelper.b_IEND, false);
        return c;
    }

    public void parseFromRaw(ChunkRaw c) {
    }
}

