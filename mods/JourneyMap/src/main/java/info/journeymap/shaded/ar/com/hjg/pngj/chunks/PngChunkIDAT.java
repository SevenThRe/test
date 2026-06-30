/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.ar.com.hjg.pngj.chunks;

import info.journeymap.shaded.ar.com.hjg.pngj.ImageInfo;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.ChunkRaw;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunk;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkMultiple;

public class PngChunkIDAT
extends PngChunkMultiple {
    public static final String ID = "IDAT";

    public PngChunkIDAT(ImageInfo i) {
        super(ID, i);
    }

    public PngChunk.ChunkOrderingConstraint getOrderingConstraint() {
        return PngChunk.ChunkOrderingConstraint.NA;
    }

    public ChunkRaw createRawChunk() {
        return null;
    }

    public void parseFromRaw(ChunkRaw c) {
    }
}

