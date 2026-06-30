/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.ar.com.hjg.pngj.chunks;

import info.journeymap.shaded.ar.com.hjg.pngj.ImageInfo;
import info.journeymap.shaded.ar.com.hjg.pngj.PngjException;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.ChunkRaw;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunk;
import info.journeymap.shaded.ar.com.hjg.pngj.chunks.PngChunkSingle;

public class PngChunkSTER
extends PngChunkSingle {
    public static final String ID = "sTER";
    private byte mode;

    public PngChunkSTER(ImageInfo info) {
        super(ID, info);
    }

    public PngChunk.ChunkOrderingConstraint getOrderingConstraint() {
        return PngChunk.ChunkOrderingConstraint.BEFORE_IDAT;
    }

    public ChunkRaw createRawChunk() {
        ChunkRaw c = this.createEmptyChunk(1, true);
        c.data[0] = this.mode;
        return c;
    }

    public void parseFromRaw(ChunkRaw chunk) {
        if (chunk.len != 1) {
            throw new PngjException("bad chunk length " + chunk);
        }
        this.mode = chunk.data[0];
    }

    public byte getMode() {
        return this.mode;
    }

    public void setMode(byte mode) {
        this.mode = mode;
    }
}

